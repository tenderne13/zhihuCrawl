package com.lxp.controller;

import com.alibaba.fastjson.JSON;
import com.lxp.mapper.ArticleMapper;
import com.lxp.mapper.PersonMapper;
import com.lxp.mapper.UserMapper;
import com.lxp.util.es.BatchIndexThread;
import com.lxp.util.thread.TopAnswerThread;
import com.lxp.vo.Article;
import com.lxp.vo.Person;
import org.apache.http.client.methods.HttpGet;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Controller
public class TestController {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public PersonMapper personMapper;

    @RequestMapping("msg")
    @ResponseBody
    public String msg(){
        Settings settings=Settings.settingsBuilder().put("cluster.name","lxp-app").build();
        Client client=null;
        List<String> userCodes=new ArrayList<String>();
        userCodes.add("110229002004");
        userCodes.add("110229002010");
        try {
            client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),9300));
            //new BatchIndexThread(personMapper,client,"110106009023").start();
            for(String s : userCodes){
                new BatchIndexThread(personMapper,client,s).start();
            }
            return "success";
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "error";
        }



    }

    @RequestMapping("topAnswers")
    @ResponseBody
    public String topAnswers(String topicId,String pageNo){
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

        Set<Object> objects = zSet.reverseRange("score:" + topicId, 0, 10);
        Iterator<Object> iterator = objects.iterator();
        List<Article> list =new ArrayList<Article>();
        while (iterator.hasNext()){
            Article article=new Article();
            String  resourceId = (String) iterator.next();
            String zan=(String) hash.get("article:" + resourceId, "zan");
            String content = (String) hash.get("article:" + resourceId, "content");
            article.setZan(zan);
            article.setContent(content);
            list.add(article);
        }
        return JSON.toJSONString(list);
    }
    private ApplicationContext applicationContext;

    @Before
    public void setUp()throws Exception{
        applicationContext=new ClassPathXmlApplicationContext("applicationContext-common.xml");
    }


    public void testRedis(){
        RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
        //SetOperations<String, Object> ops = redisTemplate.opsForSet();
        //ops.add("jiaxinqi","123");
        //Long jiaxinqi = ops.size("jiaxinqi");
          UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
          Integer count = userMapper.selectCount();
          System.out.println(count);

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        HttpGet httpGet=new HttpGet("https://www.zhihu.com/topic/19561132/top-answers?page=1");
        //new TopAnswerThread("",ops).start();
    }

    public static  void main (String[] arcgs){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext-common.xml");
        RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String answer = (String) ops.get("answer");
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

//        new TopAnswerThread("19552832",zSet,hash).start();
//        new TopAnswerThread("19552417",zSet,hash).start();

          Set<Object> set = zSet.reverseRange("score:19561132", 0, 10);
         Iterator<Object> iterator = set.iterator();
         String resourceId = (String) iterator.next();
          System.out.println(set.size());
//        Iterator<Object> iterator = set.iterator();
//        String resourceId = (String) iterator.next();
//        System.out.println("resourceId为"+resourceId);
//        String content = (String) hash.get("article:" + resourceId,"content");
//        System.out.println(content);

//        Document document = Jsoup.parse(answer);
//        Elements elements = document.select("div.feed-main");
//        for(Element ele : elements){
//            String resourceId = ele.select("div.zm-item-rich-text.expandable.js-collapse-body").attr("data-resourceid");
//            System.out.println(resourceId);
//        }
        //System.out.println(elements);
        //ArticleMapper articleMapper= (ArticleMapper) applicationContext.getBean("articleMapper");
        //new TopAnswerThread("19561132",null,articleMapper).start();

    }
}
