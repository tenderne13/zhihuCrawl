package com.lxp.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @RequestMapping("msg")
    @ResponseBody
    public String msg(){
        return "你好啊，这里是testController";
    }

    private ApplicationContext applicationContext;

    @Before
    public void setUp()throws Exception{
        applicationContext=new ClassPathXmlApplicationContext("applicationContext-redis.xml");
    }

    @Test
    public void testRedis(){
        RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
        SetOperations<String, Object> ops = redisTemplate.opsForSet();
        Long jiaxinqi = ops.size("jiaxinqi");
        System.out.println(jiaxinqi);
    }
}
