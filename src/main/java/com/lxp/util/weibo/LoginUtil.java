package com.lxp.util.weibo;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class LoginUtil {

    public static StringBuffer cookieHeader=new StringBuffer();

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
                //System.out.println(header.getName()+":"+header.getValue());
                if(header.getName().contains("Set-Cookie")){
                    String hea=header.getValue().split(";")[0]+";";
                    cookieHeader.append(hea);
                }
            }
            System.out.println("最后响应的cookie为："+cookieHeader.toString());
            result= EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("响应的内容为:"+result);
        return result;
    }


    public static String homeData(String url,Map<String, String> params){
        HttpClient httpClient = HttpClients.createDefault();
        if(params!=null){
            url+="?";
            Set<String> keySet = params.keySet();
            for(String key : keySet){
                url+=key+"="+params.get(key)+"&";
            }
        }
        HttpGet httpGet=new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
        httpGet.setHeader("Cookie",cookieHeader.toString());

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            return result;
        } catch (IOException e) {
            System.out.println("异常:"+e);
        }
        return "";
    }


    public static void main (String ar[]){
        List<String> list=new ArrayList<String>();
        list.add("lxp");
        list.add("jxq");
        list.add("zyq");
        System.out.println(list);
        String jxq2 = list.set(1, "jxq2");
        System.out.println("旧数据为"+jxq2);
        System.out.println(list);

        List<String> list1=new LinkedList<String>();
        list1.add("");
    }
}
