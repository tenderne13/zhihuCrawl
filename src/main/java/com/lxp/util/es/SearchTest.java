package com.lxp.util.es;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class SearchTest {

    public static void testMutiSearch(String userCode,String[] type,String keyword){
        Client client = EsClient.getInstance().getClient();
        SearchRequestBuilder builder = client.prepareSearch(userCode).setTypes("installation");

        builder.setQuery(QueryBuilders.boolQuery()
                .must(QueryBuilders.termsQuery("type",type))
                .must(
                        QueryBuilders.queryStringQuery(keyword).field("deviceName").field("location"))
                        );
        SearchResponse response = builder.get();
        SearchHit[] hits = response.getHits().getHits();
        for(SearchHit hit : hits){
            System.out.println(hit.getSource());
        }

    }

    public static void main(String[] ar){
        String[] types={"22","29","26"};
        String userCode="110229002010";
        String keyword="门房";
        testMutiSearch(userCode,types,keyword);

        String type="1";
        String[] split = type.split(",");
        System.out.println(split.length);
        System.out.println(split[0]);
    }
}
