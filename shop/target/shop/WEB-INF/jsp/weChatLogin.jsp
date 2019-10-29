<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>微信扫码</title>
    <script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
    <!--微信登录，生成二维码，修改了谷歌浏览器不能使用的bug-->
    <script src="/js/wxlogin.js"></script>
    <!--	微信提供的js，可以生成二维码，测试谷歌浏览器不好使，需要添加属性
    <script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
-->
    <style type="text/css">
        #weChat{
            margin: 0 auto;
        }
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<!--
<p>微信扫描登录</p>
<img src="erweimaServlet" />
-->
<div id="weChat">
    <!--生成微信登录二维码-->
    <div id="weixin"></div>
</div>
<script>
    $(function () {
        // 生成微信二维码，工具类，不是我提供，从腾讯下载的
        // 127.0.0.1/loginServlet
        var obj = new WxLogin({
            id: "weixin",
            appid: "wx7287a60bb700fd21",
            scope: "snsapi_login",
            redirect_uri: "http://www.txjava.cn/user/weChatLogin.action"
        });
    });

</script>
</body>
</html>
