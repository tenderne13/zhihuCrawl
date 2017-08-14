package com.lxp.util.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
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
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class EsSearch {

    private Client client;

    @Before
    public void init() throws UnknownHostException {
        Settings settings=Settings.settingsBuilder().put("cluster.name","lxp-app").build();
        client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
    }


    @Test
    public void IndexTest(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("buildingId","110229002004001012");
        map.put("name","招联余");
        map.put("objectId","17639");
        map.put("telephone","69177538");
        map.put("idcard","110229195605237546");

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
        map.put("birthday","1993-03-07");
        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1").setDoc(map).get();
        System.out.println("处理结果为:"+response.getGetResult()+"\n是否创建:"+response.isCreated());

        client.close();
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
}
