<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>海玩商城</title>
    <meta name="keywords" content="海玩商城"/>
    <meta name="description" content="海玩商城"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css"/>
    <style type="text/css">
        .btn_tab_login {
            float: right;
            margin-top: 48px;
        }

        .btn_tab_login li {
            display: inline-block;
            margin-left: 30px;
            font-size: 14px;
        }

        .btn_tab_login li.cur a {
            color: #d00;
        }
    </style>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
    <style type="text/css">
        #weixin_login_container iframe {
            width: 158px;
            height: 158px;
        }
    </style>
</head>
<body>
<div id="header">
</div>
<div class="login-wrap">
    <div class="wrap clearfix">
        <div class="form-box fr loginV2" style="display:block;">

            <div class="form-con">

                <%--账号密码登录--%>
                <div class="login-normal" style="display:block;">
                    <div class="login-short clearfix">
                        <div class="short-left">
                            <h3>使用合作账号登录：</h3>
                            <ul class="clearfix">
                                <li class="qq"><a href="javascript:;" tjjj="passport.login.thd.login.qq"></a></li>
                                <li class="sina"><a href="javascript:;" tjjj="passport.login.thd.login.sina"></a></li>
                                <li class="weixin"><a href="javascript:;" tjjj="passport.login.thd.login.weixin"></a>
                                </li>
                            </ul>
                        </div>
                        <div class="short-right">
                            <h3>您还可以选择：</h3>
                            <p class="phone-short clearfix">
                                <i class="phone"></i>
                                <a href="javascript:;" tjjj="" class="txt phoneLogin">手机快捷登录</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- -快捷登录 -->
        <div class="form-box fr shortLogin" style="display:none;">
            <h5 class="title">快捷登录</h5>
            <div class="form-con">
                <form id="mobileLoginForm" class="layui-form" method="post" onsubmit="return mobileLoginCheck();">
                    <div class="form-error" style=""><i></i><label class="text"></label></div>
                    <%--<span style="color: red;font-size: 14px">${error}</span>--%>
                    <dl class="clearfix">
                        <dt>手机号：</dt>
                        <dd><input name="phone" type="text" id="phone" autocomplete="off" class="input-text mobile"
                                   maxlength="11" <%--onblur="mobileCheck(this);"--%> placeholder="请输入手机号"></dd>
                    </dl>
                    <dl class="top1 clearfix">
                        <dt>验证码：</dt>
                        <dd>
                            <input name="smsCaptcha" type="text" id="partnerYzm" class="input-yzm"
                                   onblur="captchCheck(this);" maxlength="4" autocomplete="off"/>
                            <span class="span-yzm">
									<img id="smsCaptchaImage" src="images/code.jpg" title="点击图片刷新校验码" alt="点击图片刷新校验码"
                                         onclick="changeCode('smsCaptchaImage','partnerYzm');"/>
									<a href="javascript:changeCode('smsCaptchaImage','partnerYzm');"
                                       class="forget-pass">换一张</a>
								</span>
                        </dd>
                    </dl>
                    <dl class="top2 clearfix">
                        <dt>校验码：</dt>
                        <dd>
                            <input name="code" type="text" id="code" class="input-jym" maxlength="4"
                                   autocomplete="off"/>
                            <input type="button" class="span-jym disabled" onclick="sendSms()" id="send" value="发送验证码"/>
                            <%--<a id="send" href="javascript:sendSms();" class="span-jym disabled" tjjj="passport.send.msg"></a>--%>
                        </dd>
                    </dl>
                    <div class="form-remember"></div>
                    <div class="btn-box clearfix">
                        <input id="quickLogin" lay-submit lay-filter="submitBtn" class="btn-settlement" type="submit"
                               value="立即登录" tjjj="passport.quick.button.login">
                    </div>
                    <div class="link-box clearfix">
                        <a href="javascript:;" class="backLogin">返回账号登录>></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="jia_footer">
    <div class="jia_foot_info">
        <p>
            版权所有 ? 2005-2016 www.17sucai.com All rights reserved
        </p>
        <p>
            沪ICP备xxxxxx号 沪B2-xxxx 组织机构代码证：xxxxx—1
        </p>
        <p>
            中国互联网协会信用评价中心网信认证 网信编码:xxxxxx1
        </p>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    var _wx_server_qr_code_count = 0;
    var _wx_server_qr_code_loaded = false;
    var _qr_code_limited = '';
    var _qr_code_wait_time = 20;
    var flashQrCodeWaitingTimer = null;
    var getQrCodeStatusTimer = null;
    var getQrCodeTimer = null;

    $(function () {
        $(".form-tab li").on("click", function () {
            var index = $(this).index();
            $(this).addClass("cur").siblings().removeClass("cur");
            $(".form-con>div").hide().eq(index).show();
            if (index == 0) {
                $(".form-foot").hide();
            } else {
                $(".form-foot").show();
            }
            $(".form-error").hide();
        });
        $(".weixin-login .help-a").hover(
            function () {
                $(".wx-img-box,.wx-image").stop();
                $(this).parents(".weixin-login").find(".wx-img-box").animate({"marginLeft": "15px"}, 300, function () {
                    $(this).parents(".weixin-login").find(".wx-image").animate({"opacity": 1}, 300);
                });
            },
            function () {
                $(".wx-img-box,.wx-image").stop();
                $(this).parents(".weixin-login").find(".wx-image").stop().animate({"opacity": 0}, 300, function () {
                    $(this).parents(".weixin-login").find(".wx-img-box").animate({"marginLeft": "110px"}, 300);
                });
            }
        );


    });

    $('.jia_foot_open').click(function () {
        $('.footnone').slideToggle();
        $(this).find('i').toggleClass('footnow');
    });
    $('.phoneLogin').click(function () {
        $('.loginV2').hide();
        $('.shortLogin').show();
        $('.form-error').hide();
    });
    $('.backLogin').click(function () {
        $('.login-normal').show();
        $('.loginV2').show();
        $('.shortLogin').hide();
        $('.form-error').hide();
    });

    //开启错误提示
    function showError(error) {
        $(".form-error").find("label").html(error);
        $(".form-error").show();
    }
</script>
