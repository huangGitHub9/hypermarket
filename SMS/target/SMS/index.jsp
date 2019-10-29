<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>海玩超市</title>
    <meta name="keywords" content="海玩超市"/>
    <meta name="description" content="海玩超市"/>
    <link href="<%=basePath%>Css/layout.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>Css/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>Js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>Js/js.js"></script>
</head>
<style>
    .ibar {
        display: none;
    }
</style>
<body class="login-bg">
<div class="main ">
    <!--登录-->
    <div class="login-dom login-max">
        <div class="logo text-center">
            <a href="#">
                <img src="<%=basePath%>Images/logo.png" width="180px" height="180px">
            </a>
        </div>
        <div class="login container " id="login">
            <p class="text-big text-center logo-color">
                海玩超市,购买一切
            </p>
            <p class=" text-center margin-small-top logo-color text-small">
                购物 | 游乐 | 阅读 | 电竞
            </p>
            <form class="login-form" action="index.html" method="post" autocomplete="off">
                <div class="login-box border text-small" id="box">
                    <div class="name border-bottom">
                        <input type="text" placeholder="手机 / 邮箱 / 某某账号" id="username" name="username" datatype="*"
                               nullmsg="请填写帐号信息">
                    </div>
                    <div class="pwd">
                        <input type="password" placeholder="密码" datatype="*" id="password" name="password"
                               nullmsg="请填写帐号密码">
                    </div>
                </div>
                <input type="hidden" name="formhash" value="5abb5d21"/>
                <input type="submit" class="btn text-center login-btn" value="立即登录">
            </form>
            <div class="forget">
                <a href="repassword.html" class="forget-pwd text-small fl">忘记登录密码？</a><a href="register.html"
                                                                                         class="forget-new text-small fr"
                                                                                         id="forget-new">创建一个新账号</a>
            </div>
        </div>
    </div>

    <div class="footer text-center text-small ie">
        江西科技师范大学 20162568 黄旭林 <a href="#" target="_blank">2629301367@qq.com</a>
        <span class="margin-left margin-right">|</span>
        <script src="#" language="JavaScript"></script>
    </div>
    <div class="popupDom">
        <div class="popup text-default">
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>Js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
    function popup_msg(msg) {
        $(".popup").html("" + msg + "");
        $(".popupDom").animate({
            "top": "0px"
        }, 400);
        setTimeout(function () {
            $(".popupDom").animate({
                "top": "-40px"
            }, 400);
        }, 2000);
    }

    /*动画（注册）*/
    $(document).ready(function (e) {
        // $("input[name=username]").focus();
        // $('.login-form').Validform({
        // 	ajaxPost: true,
        // 	tiptype: function(msg) {
        // 		if (msg) popup_msg('' + msg + '');
        // 	},
        // 	callback: function(ret) {
        // 		popup_msg('' + ret.info + '');
        // 		if (ret.status == 1) {
        // 			if (ret.uc_user_synlogin) {
        // 				$("body").append(ret.uc_user_synlogin);
        // 			}
        // 			setTimeout("window.location='" + ret.url + "'", 2000);
        // 		}
        // 	}
        // })
    });
</script>
</html>