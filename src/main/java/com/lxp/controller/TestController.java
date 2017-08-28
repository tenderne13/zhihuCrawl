package com.lxp.controller;

import com.alibaba.fastjson.JSON;
import com.lxp.mapper.InstallationMapper;
import com.lxp.mapper.PersonMapper;
import com.lxp.mapper.UserMapper;
import com.lxp.util.es.EsClient;
import com.lxp.util.es.InstallationIndexThread;
import com.lxp.util.es.PersonIndexThread;
import com.lxp.vo.Article;
import org.apache.http.client.methods.HttpGet;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class TestController {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public PersonMapper personMapper;

    @Autowired
    public InstallationMapper installationMapper;


    @RequestMapping("postStr")
    @ResponseBody
    public String postStr(String type){
        System.out.println("类型为:"+type);
        return type;
    }



    @RequestMapping("es")
    @ResponseBody
    public String es() throws InterruptedException {
        long start = System.currentTimeMillis();
        Client client = EsClient.getInstance().getClient();
        List<String> userCodes=new ArrayList<String>();
        userCodes.add("110229002004");
        userCodes.add("110229002010");
        //userCodes.add("110106009023");


        ExecutorService executorService = Executors.newFixedThreadPool(userCodes.size()*2);
        for(String s : userCodes){
            executorService.execute(new PersonIndexThread(personMapper,client,s));
            executorService.execute(new InstallationIndexThread(installationMapper,client,s));
        }
        executorService.shutdown();
        while (true){
            if(executorService.isTerminated()){
                long end=System.currentTimeMillis();
                System.out.println("索引同步完毕，用时:"+(end-start)/1000+"秒");
                return "索引同步完毕，用时:"+(end-start)/1000+"秒";
            }

            //每隔200毫秒检测一下所有线程是否执行完。
            Thread.sleep(200);
        }
    }


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
                new PersonIndexThread(personMapper,client,s).start();
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
