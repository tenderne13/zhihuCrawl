package com.lxp.controller;

import com.lxp.util.weibo.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        return "login";
    }


    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(String username, String password, HttpSession httpSession){
        System.out.println("用户名为:"+username);
        System.out.println("密码为:"+password);
        Map<String,String> params;
        params = new HashMap<String, String>();
        params.put("client_id","");
        params.put("code","");
        params.put("ec","0");
        params.put("entry","mweibo");
        params.put("hff","");
        params.put("hfp","");
        params.put("loginfrom","");
        params.put("mainpageflag","1");
        params.put("pagerefer","");
        params.put("password",password);
        params.put("qq","");
        params.put("r","http://m.weibo.cn/");
        params.put("savestate","1");
        params.put("username",username);
        params.put("wentry","");
        String url="https://passport.weibo.cn/sso/login";
        String result = LoginUtil.login(url, params);

        httpSession.setAttribute("userId",username);
        return result;
    }


    @RequestMapping(value = "userData")
    @ResponseBody
    public String userData(){
        String url="https://m.weibo.cn/home/me?format=cards";
        return LoginUtil.homeData(url,null);
    }

    @RequestMapping(value = "index")
    public String index(){
        return "home";
    }
}
