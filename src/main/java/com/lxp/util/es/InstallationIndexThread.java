package com.lxp.util.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxp.mapper.InstallationMapper;
import com.lxp.mapper.PersonMapper;
import com.lxp.vo.Installation;
import com.lxp.vo.Person;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class InstallationIndexThread extends Thread{

    private InstallationMapper installationMapper;
    private Client client;
    private Map<String,Object> parmap;
    private ConcurrentLinkedQueue<String> queue;
    private String userCode;
    private AtomicBoolean isDone = new AtomicBoolean(false);

    public InstallationIndexThread(InstallationMapper installationMapper, Client client, String userCode){
        this.installationMapper=installationMapper;
        this.client=client;
        this.userCode=userCode;
        this.parmap=new HashMap<String, Object>();
        this.queue=new ConcurrentLinkedQueue<String>();
        parmap.put("userCode",userCode);

    }

    @Override
    public void run() {
        List<Installation> installationList = installationMapper.getInstallationIndexList(parmap);
        if(installationList!=null && installationList.size()>0){
            for(Installation installation : installationList){
                queue.add(JSON.toJSONString(installation));
            }
            System.out.println("-----------队列插入完毕，数量为:"+queue.size()+"-------------------");
            System.out.println("-------------------开始创建索引任务------------------------");

            BulkProcessor processor= BulkProcessor.builder(client, new BulkProcessor.Listener() {
                public void beforeBulk(long l, BulkRequest bulkRequest) {

                }

                public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                    System.out.println("请求数量为:"+bulkRequest.numberOfActions()+"\n是否有失败:"+bulkResponse.hasFailures());
                    if(bulkResponse.hasFailures()){
                        System.out.println("失败原因:"+bulkResponse.buildFailureMessage());
                    }
                    isDone=new AtomicBoolean(true);

                }

                public void afterBulk(long l, BulkRequest bulkRequest, Throwable failure) {
                    System.out.println("失败原因:"+failure);
                }
            }).setBulkActions(10000)
                    .setBulkSize(new ByteSizeValue(100, ByteSizeUnit.MB))
                    .setFlushInterval(TimeValue.timeValueSeconds(5))
                    .setConcurrentRequests(1)
                    .setBackoffPolicy(
                            BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100),3)
                    )
                    .build();


            while (true){
                if(!queue.isEmpty()){
                    String installation = queue.poll();
                    JSONObject object=JSON.parseObject(installation);
                    Integer id= (Integer) object.get("id");
                    object.remove("id");
                    processor.add(new IndexRequest(userCode,"installation").id(id.toString()).source(object));
                }


                if(queue.isEmpty() && isDone.get()){
                    processor.flush();
                    processor.close();
                    System.out.println("-----------------物类索引创建完毕----------------------");
                    break;
                }
            }
        }
    }


}
