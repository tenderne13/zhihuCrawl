package com.lxp.util.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class EsClient {
    private Settings settings;
    private Map<String,Client> clientMap =new ConcurrentHashMap<String, Client>();
    //private Map<String,Integer> ips = new HashMap<String, Integer>();
    private String clusterName;
    private String ip;
    private Integer port;

    private EsClient(){
        init();
        addClient(settings);
    }

    public static  final EsClient getInstance(){
        return ClientHolder.instance;
    }


    private static class ClientHolder{
        private static final EsClient instance = new EsClient();
    }


    //初始化数据
    private void  init(){
        Properties properties = new Properties();
        try {
            properties.load(EsClient.class.getClassLoader().getResourceAsStream("es.properties"));
            clusterName=properties.getProperty("es.cluster.name");
            ip=properties.getProperty("es.cluster.ip");
            port=Integer.parseInt(properties.getProperty("es.cluster.port"));
            //ips.put(ip,port);
            settings=Settings.settingsBuilder().put("cluster.name",clusterName).build();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient(){
        return getClient(clusterName);
    }

    public Client getClient(String clusterName){
        return clientMap.get(clusterName);
    }



    //将client放入map中
    private  void   addClient(Settings settings){
        try {
            Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip),port));
            clientMap.put(settings.get("cluster.name"),client);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static  void main (String[] a){
        EsClient esClient= new EsClient();

    }
}
