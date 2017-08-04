<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
    String path = request.getContextPath();
    String basePath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登陆页面</title>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css"/>
    <script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
    <style>
        html{background-color: #D9D9D9;}
    </style>
</head>
<body>
<script type="text/javascript">
    layui.use(['layer'],function(){
        window.layer=layer;
    })

    function login() {
        var username=$("#username").val();
        var password=$("#password").val();
        if(username!='' && password != ''){
            $.post(
                "${ctx}/doLogin",{
                    username:username,
                    password:password
                },function(data){
                    data=JSON.parse(data);
                    var resultCode=data.retcode;
                    if(resultCode==20000000){
                        layer.alert("登录成功！");
                        window.location.href="${ctx}/index";
                    }else{
                        layer.alert("登录失败");
                    }
                }

            );
        }
    }

</script>
<div style="margin: 300px auto; text-align: center; font-size: 20px;">
    <form action="#" method="post">
        用户名:
        <input type="text" name="username" id="username"><br>
        密码:
        <input name="password" type="password" id="password"><br>
        <input type="button" onclick="login()" value="登录">
    </form>
</div>
</body>
</html>
