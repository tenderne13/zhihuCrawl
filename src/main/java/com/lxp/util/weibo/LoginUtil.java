package com.lxp.util.weibo;


import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginUtil {

    public static String login(String url,Map<String, String> params){
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost =new HttpPost(url);
        //httpPost.setHeader("Host", "passport.weibo.cn");
        httpPost.setHeader("Referer","https://passport.weibo.cn/signin/login");
        //httpPost.setHeader("Cookie","_T_WM=9d3bd7f799a86165aba81bfa2f1a9aa3; SUB=_2AkMu345DdcPxrAZQn_4Uz27qboxH-jydCue1An7oJhMyPRh77nEjqScI9HvuMh4VM06Q_m5CGe-OY9zB2A\n" +
        //        "..; SUHB=02MvftJezJHymY; SCF=AmOUTsFT64uXliMakSFVGKeo1ZSHzZj9Lp6i1SDaj-xjmWCHvtepZm7U943LwIs-husWGlpLYkLXkNUoWaC_AtM\n" +
        //        ".; H5_INDEX=3; H5_INDEX_TITLE=dfe32555db27407e");
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        //httpPost.setHeader("Content-Length","168");
        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpPost.setHeader("Connection","close");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
        String result=null;
        try {
            if(params!=null){
                List<NameValuePair> list=new ArrayList<NameValuePair>();
                Set<String> keySet=params.keySet();
                for(String key:keySet){
                    list.add(new BasicNameValuePair(key, params.get(key)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
            }
            HttpResponse response=httpClient.execute(httpPost);
            Header[] headers = response.getAllHeaders();
            for( Header header :headers){
                System.out.println(header.getName()+":"+header.getValue());
            }
            result= EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("响应的内容为:"+result);
        return result;
    }
}
