package com.lxp.util.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxp.mapper.PersonMapper;
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

public class BatchIndexThread extends Thread{

    private PersonMapper personMapper;
    private Client client;
    private Map<String,Object> parmap;
    private ConcurrentLinkedQueue<String> queue;
    private String userCode;
    static AtomicBoolean isDone = new AtomicBoolean(false);

    public BatchIndexThread(PersonMapper personMapper,Client client,String userCode){
        this.personMapper=personMapper;
        this.client=client;
        this.userCode=userCode;
        this.parmap=new HashMap<String, Object>();
        this.queue=new ConcurrentLinkedQueue<String>();
        parmap.put("userCode",userCode);

    }

    @Override
    public void run() {
        List<Person> personList = personMapper.getPersonList(parmap);
        if(personList!=null && personList.size()>0){
            for(Person person : personList){
                queue.add(JSON.toJSONString(person));
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
                    String person = queue.poll();
                    JSONObject object=JSON.parseObject(person);
                    String id= (String) object.get("id");
                    processor.add(new IndexRequest(userCode,"person").id(id).source(person));
                }


                if(queue.isEmpty() && isDone.get()){
                    processor.flush();
                    processor.close();
                    System.out.println("-----------------创建索引完毕----------------------");
                    break;
                }
            }
        }
    }


}
