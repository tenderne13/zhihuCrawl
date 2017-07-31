package com.lxp.util.thread;

import com.lxp.util.httpClient.Static;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TopAnswersThread extends Thread{

    private CloseableHttpClient httpClient;
    private HttpClientContext context;
    private HttpGet httpGet;
    private String collectId;
    private String baseUrl="https://www.zhihu.com/collection/";


    public TopAnswersThread(CloseableHttpClient httpClient,String collectId) {
        this.httpClient=httpClient;
        this.collectId=collectId;
    }

    @Override
    public void run() {


        try {
            String url=baseUrl+collectId;
            httpGet=new HttpGet(url);
            httpGet.setHeader("Host","www.zhihu.com");
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
            httpGet.setHeader("Cookie","\t\n" +
                    "q_c1=20b239c057c94c8388a4ab7f3acd1b19|1501338992000|1501338992000; q_c1=198228ae719f4d2ea6db984c2242390d\n" +
                    "|1501338992000|1501338992000; _zap=5d6e5e24-cdf9-4e7b-a5da-29fc49b7dfa7; capsion_ticket=\"2|1:0|10:1501339171\n" +
                    "|14:capsion_ticket|44:NTQwYzUzMmE3NmY2NGI1MzlmNGNhNGVhNDY2MGYxYWY=|145724ff233e4862aa07dbceb493f20953fa4ea70f8b647a9de4ebb28a94b58e\"\n" +
                    "; l_cap_id=\"NmMyZWEyZTdmZDQ2NDg1NDhhM2RhYzdkZGQxOTkxZTg=|1501418160|1bf83c50d8211af9cef6d6732367c54301de8c2a\"\n" +
                    "; cap_id=\"YTU1MDE5NWUxOWQwNDJjOWE0MGRlYTA5NmMzZTQ2NTk=|1501418160|8fca96240e49f8b300d7533099bfa683a17b3e4a\"\n" +
                    "; d_c0=\"AJCC_6DAJAyPTlLfrWuiY6lcs-XY7ZtfYLk=|1501404845\"; __utma=51854390.338588429.1501404618.1501417757\n" +
                    ".1501420551.3; __utmz=51854390.1501420551.3.3.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct\n" +
                    "=/; __utmv=51854390.100--|2=registration_date=20170730=1^3=entry_date=20170729=1; aliyungf_tc=AQAAAPSG9WcR2QUAC1hI33yD6ZKBqN0E\n" +
                    "; _xsrf=2821c074cddfb01f92d085a7123b8326; __utmc=51854390; auth_type=cXFjb25u|1501418301|b08112d16d97e7c05316759c27396213d0749f10\n" +
                    "; token=\"MjREMzdERkM0ODQwRkMxMEJFODJERjM4MTIyODc4OEE=|1501418301|4cff6d9d3bc701ada018144d56c281ea4b652f9e\"\n" +
                    "; client_id=\"N0VGNEExRTNEOUU4MTExRDRGMDA0RTRCN0ZENDMxOTg=|1501418301|7b698b71515b9f930038e94723a930de449552ec\"\n" +
                    "; _xsrf=2821c074cddfb01f92d085a7123b8326; z_c0="+ Static.getCookie()+"; __utmb=51854390.0.10.1501420551");

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                String result= EntityUtils.toString(entity,"UTF-8");
                System.out.println(result);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static  void main(String[] args){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        new TopAnswersThread(httpClient,"38645820").start();
    }
}
