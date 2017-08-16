package com.lxp.util.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxp.mapper.PersonMapper;
import com.lxp.vo.Person;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EsSearch {

    private Client client;
    private ApplicationContext applicationContext;


    public static ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>();

    @Before
    public void init() throws UnknownHostException {
        Settings settings=Settings.settingsBuilder().put("cluster.name","lxp-app").build();
        client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));

        applicationContext=new ClassPathXmlApplicationContext("applicationContext-common.xml");
    }


    @Test
    public void IndexTest(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("buildingId","110229002004001012");
        map.put("name","李小朋");
        map.put("objectId","17801");
        map.put("telephone","69177538");
        map.put("idcard","130132199303070097");

        IndexRequestBuilder builder = client.prepareIndex();
        builder.setIndex("110106009001");
        builder.setType("person");
        builder.setId("110229002004001011040412_1_04");
        builder.setSource(map);

        IndexResponse response = builder.get();
        System.out.println("index为:"+response.getIndex()+"\n" +
                "是否创建:"+response.isCreated()+"\n" +
                "Id为:"+response.getId());
        client.close();
    }

    @Test
    public void DeleteTest(){
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "AV3fZXDW8W2X-090iBlU").get();
        System.out.println("处理结果为:"+response.getId()+","+response.isFound());
        client.close();
    }


    @Test
    public void GetTest(){
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
        Map<String, Object> source = response.getSource();
        System.out.println(source);
        client.close();
    }

    @Test
    public void UpdateTest(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("buildingId","110229002004001012");
        map.put("name","招联余");
        map.put("objectId","17639");
        map.put("telephone","69177538");
        map.put("idcard","110229195605237546");
        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1").setDoc(map).get();
        System.out.println("处理结果为:"+response.getGetResult()+"\n是否创建:"+response.isCreated());

        client.close();
    }

    @Test
    public void UpsertTest(){

    }


    @Test
    public void SearchTest(){
        SearchRequestBuilder builder = client.prepareSearch("110106009*").setTypes("person");
        builder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        //builder.setQuery(QueryBuilders.matchQuery("username","李朋"));
        builder.setQuery(QueryBuilders.queryStringQuery("连雨110229195605237546").field("name").field("idcard").analyzer("ik"));
        builder.addHighlightedField("name");
        builder.addHighlightedField("idcard");
        builder.setHighlighterPreTags("<font color=\"green\">");
        builder.setHighlighterPostTags("</font>");
        SearchResponse response = builder.get();
        SearchHits hits = response.getHits();
        for(SearchHit hit : hits.getHits()){
            Map<String, Object> source = hit.getSource();
            Map<String, HighlightField> fields = hit.highlightFields();
            System.out.print("评分为:"+hit.score());
            System.out.println(source);
            System.out.println("高亮数据为:"+fields);
        }
        client.close();
    }



    @Test
    public void bulkProTest(){
        String userCode="110106009023";
        Map<String,Object> parmap=new HashMap<String, Object>();
        parmap.put("userCode",userCode);
        PersonMapper personMaper = (PersonMapper) applicationContext.getBean("personMapper");
        List<Person> personList = personMaper.getPersonList(parmap);
        if(personList!=null && personList.size()>0){
            for(Person person : personList){
                queue.add(JSON.toJSONString(person));
            }
            System.out.println("----------队列插入完毕，数量为:"+queue.size()+"-------------------");
            System.out.println("-------------------开始创建索引任务------------------------");

            //System.out.println(queue);
            BulkProcessor processor= BulkProcessor.builder(client, new BulkProcessor.Listener() {
                public void beforeBulk(long l, BulkRequest bulkRequest) {

                }

                public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                    System.out.println("请求数量为:"+bulkRequest.numberOfActions()+"\n是否有失败:"+bulkResponse.hasFailures()+"" +
                            "\n失败原因:"+bulkResponse.buildFailureMessage());

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

//                if(queue.isEmpty()){
//                    processor.flush();
//                    processor.close();
//                    System.out.println("-----------------创建索引完毕----------------------");
//                    break;
//                }
            }
        }


    }



    @Test
    public void ThreadTest(){
        PersonMapper personMaper = (PersonMapper) applicationContext.getBean("personMapper");
        String userCode="110106009023";
        new PersonIndexThread(personMaper,client,userCode).start();
        System.out.println("---------------------------");
    }
}
