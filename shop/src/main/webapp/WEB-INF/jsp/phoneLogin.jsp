<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css"/>
    <script type="text/javascript" src="/layui/js/jquery.js"></script>
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript">
        function login(){
            $(function () {
                layui.use(['form', 'jquery', 'layer'], function () {
                    var $ = layui.jquery,
                        layer = layui.layer;
                    var phone = $('#mobile').val(); // 取得用户输入的手机号码
                    var send = $("#send").val();
                    if(send != "发送验证码") {
                        if (/^1[34578]\d{9}$/.test(phone)) { // 验证通过
                                $.ajax({
                                    url: '/user/doPhoneLogin.action',
                                    type: 'POST',
                                    data: $('.layui-form').serialize(),
                                    success: function (data) {
                                        /*alert(data);*/
                                        if (data == "success") {
                                            window.location = "/user/main.action";
                                        } else {
                                            layui.use('layer', function () {
                                                layer.msg('登录失败！', {time: 2000, icon: 2});
                                            });
                                            return false;
                                        }
                                    }
                            });
                        } else {
                            layui.use('layer', function () {
                                layer.msg('您输入的手机号码有误！', {time: 1000, icon: 2})
                            });
                            return false;
                        }
                    } else {
                        layui.use('layer', function () {
                            layer.msg('请您先发送验证码！', {time: 1000, icon: 2})
                        });
                        return false;
                    }
                });
            });
        }
    </script>
    <%@ include file="header.jsp" %>
    <title>网上商城</title>
</head>
<body>
<!--Begin Login Begin-->
<div class="log_bg">
    <div class="top">
        <div class="logo"><a href="/main/getAll.action"><img src="/images/logo.png" /></a></div>
    </div>
    <div class="login">
        <div class="log_img"><img src="/images/l_img.png" width="611" height="425" /></div>
        <div class="log_c">
            <form class="layui-form" method="post">
                <p></p>
                <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
                    <tr height="50" valign="top">
                        <td width="55">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;color: orangered"><strong>短信登录</strong></span>
                            <span class="fr">还没有商城账号，<a href="/user/registerA.action" style="color:#ff4e00;">立即注册</a></span>
                        </td>
                    </tr>
                    <tr height="70">
                        <td>手机号&nbsp;</td>
                        <td><input type="text" name="mobile" id="mobile" autocomplete="off" class="l_tel" required/></td>
                    </tr>
                    <tr height="70">
                        <td>验证码&nbsp;</td>
                        <td><input type="text" name="code" id="code" autocomplete="off"  class="code"/>
                            <input type="button" class="codeA" onclick="javascript:sendSms();" id="send" value="发送验证码"/></td>
                    </tr>
                    <tr height="60">
                        <td>&nbsp;</td>
                        <td><input id="quickLogin" onclick="javascript:login();" type="button" value="登录" class="log_btn" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<!--End Login End-->
<!--Begin Footer Begin-->
<div class="btmbg">
    <div class="btm">
        黄旭林 20162568 微信V600003 qq2629301367 <br />
        <img src="/images/b_1.gif" width="98" height="33" /><img src="/images/b_2.gif" width="98" height="33" /><img src="/images/b_3.gif" width="98" height="33" /><img src="/images/b_4.gif" width="98" height="33" /><img src="/images/b_5.gif" width="98" height="33" /><img src="/images/b_6.gif" width="98" height="33" />
    </div>
</div>
<script type="text/javascript">
    var countTime = 60; //设置倒计时60s
    function sendSms() {
        var phone = $('#mobile').val(); // 取得用户输入的手机号码
        if (/^1[34578]\d{9}$/.test(phone)) { // 验证通过
            $.post("/user/phoneLoginA.action", {"mobile": phone}, function (obj) {
                //alert("------------"+obj);
                if (obj == "success") {
                    // 调用倒计时方法
                    // 信息提示
                    showTime();
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('验证码发送成功！', {time: 1000, icon: 1})
                    })
                }
            }, 'text');
        } else { //手机号码有误
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('你输入的手机号码有误！', {time: 1000, icon: 2})
            })
        }
    };

    function showTime() {
        if (countTime == 0) {
            $("#send").attr('disabled', false);
            $("#send").val('重新发送');
            countTime = 60;
            return false;
        } else {
            $("#send").attr('disabled', true);
            $("#send").val('正在发送' + countTime + "s");
            countTime--;
        }
        setTimeout(function () {
            showTime()
        }, 1000);
    }
</script>
</body>
</html>