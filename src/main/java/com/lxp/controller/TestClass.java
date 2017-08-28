package com.lxp.controller;

import com.lxp.util.httpClient.PostUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClass extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("这个是doGet方法");
        out.flush();
        out.close();
        System.out.println("doGet方法束");
    }

    @Test
    public void testJsoup(){
        String url = "https://www.zhihu.com/topics";
        String result = PostUtil.doGetStr(url);
        //System.out.println(result);
        Document dos = Jsoup.parse(result);
        Elements lis = dos.select("li[data-id]");
        System.out.println(lis);
        for( Element ele:lis){
            String attr = ele.attr("data-id");
            System.out.println("dataId为："+attr);
        }
    }

    public static  void say(){
        System.out.println("你好");
    }


    public static void main(String[] ar) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String className=TestClass.class.getName();
        Method say = TestClass.class.getMethod("say",null);
        say.invoke(null);
        System.out.println(className);
        StringBuffer buffer=new StringBuffer();
    }
}
