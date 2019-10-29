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
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>layui/css/layui.css"/>
    <script type="text/javascript" src="<%=basePath%>layui/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>register.js"></script>
    <title>用户注册</title>
</head>
<body style="padding-top: 35px">
<div class="layui-container">
    <div class="layui-col-md4">&nbsp;</div>
    <div class="layui-col-md4">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" id="phone"
                           lay-verify="required|phone" placeholder="请输入手机号码！"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">登录密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" id="password" lay-verify="required" placeholder="请输入登录密码！"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验&nbsp;&nbsp;证&nbsp;&nbsp;码</label>
                <div class="layui-input-block">
                    <div class="layui-col-md6">
                        <input type="text" name="code" id="code"
                               lay-verify="required" placeholder="请输入验证码！"
                               class="layui-input">
                    </div>
                    <div class="layui-col-md1">&nbsp;</div>
                    <div class="layui-col-md1">
                        <input type="button" class="layui-btn layui-btn-normal" onclick="sendMsg()" id="btn"
                               value="发送验证码"/>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" lay-submit lay-filter="submitBtn"
                           class="layui-btn layui-btn-success layui-btn-sm layui-col-md-offset6" value="注册">
                    <button type="reset" class="layui-btn layui-btn-danger layui-btn-sm">重置</button>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-col-md4">&nbsp;</div>
</div>
</body>
<script type="text/javascript">
    var countTime = 60; //设置倒计时60s
    function sendMsg() {
        var phone = $('#phone').val(); // 取得用户输入的手机号码
        if (/^1[3456789]\d{9}$/.test(phone)) { // 验证通过
            $.post("/RegisterServlet/sendSms", {"phone": phone}, function (obj) {
                if (obj == "success") {
                    // 调用倒计时方法
                    showTime();
                    // 信息提示
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
            $("#send").val('发送验证码');
            countTime = 60;
            return false;
        } else {
            $("#send").attr('disabled', true);
            $("#send").val('还是剩' + countTime + '秒!');
            countTime--;
        }
        setTimeout(function () {
            showTime()
        }, 1000)
    }
</script>
</html>