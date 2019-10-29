<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>账户安全</title>
    <%--头部的导航框--%>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script type="text/javascript">
        var countTime = 60;
        function activeCode() {
            var email = $("#email").val();
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.com)+/;
            if(reg.test(email)){
                // 调用倒计时方法
                // 信息提示
                showTime();
                $.ajax({
                    type: "POST",//请求的方式
                    url: "/user/addEmail.action",//请求的地址
                    data: "email="+email,//需要传递至后台的参数
                    success: function(msg){ //如果后台正常处理数据，没有出现异常则会自动回调 success中的函数，后台会将消息赋给  msg
                        alert(msg);
                        return false;
                    },error:function(){ //如果后台出现异常
                        alert("网络出现异常！");
                        return false;
                    }
                });
                return false;
            }else{
                alert("请输入正确的邮箱格式！");
                return false;
            }
        }

        function doAddEmail() {
            var send = $("#send").val();
            if(send != "发送邮件"){
                var email = $("#email").val();
                var emailCode = $("#emailCode").val();
                var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.com)+/;
                if(reg.test(email)){
                    if (emailCode.length == 6) {
                        window.location = "/user/doAddEmail.action?email="+email+"&emailCode="+emailCode;
                    }else {
                        alert("请输入正确的验证码！");
                        return false;
                    }
                }else{
                    alert("请输入正确的邮箱格式！");
                    return false;
                }
            }else{
                alert("请先发送邮件！")
            }
        }

        function showTime() {
            if (countTime == 0) {
                $("#send").attr('disabled', false);
                $("#send").val('重新发送');
                countTime = 60;
                return false;
            } else {
                $("#send").attr('disabled', true);
                $("#send").val(countTime + "s");
                countTime--;
            }
            setTimeout(function () {
                showTime()
            }, 1000);
        }
    </script>
</head>
<body>
<%--搜索框--%>
<%@ include file="/WEB-INF/jsp/searchBar.jsp" %>
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/WEB-INF/jsp/leftBar.jsp" %>
        <div class="m_right" id="content">
            <p></p>
            <div class="mem_tit">账户安全</div>

            <%--绑定邮箱--%>
            <div class="m_des">
                <form>
                    <table border="0" style="width:880px;" cellspacing="0" cellpadding="0">
                        <tr height="45">
                            <td width="80" align="right">邮箱 &nbsp; &nbsp;</td>
                            <td><input type="text" value="" id="email" name="email" autocomplete="off" class="add_ipt" style="width:180px;"/>&nbsp; <font
                                    color="#ff4e00">${noNullemail}</font></td>
                        </tr>
                        <tr height="45">
                            <td align="right">验证码 &nbsp; &nbsp;</td>
                            <td><input type="text" value="" id="emailCode" autocomplete="off" name="emailCode" class="add_ipt" style="width:90px;"/>
                                <input type="button" id="send" onclick="javascript:activeCode();" value="发送邮件" class="btn_tj" style="width:85px;"/>
                        </tr>
                        <tr height="50">
                            <td>&nbsp;</td>
                            <td><input type="button" onclick="javascript:doAddEmail();" value="绑定邮箱" class="btn_tj"/></td>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="m_des">
                <form>
                    <table border="0" style="width:880px;" cellspacing="0" cellpadding="0">
                        <tr height="45">
                            <td width="80" align="right">原手机 &nbsp; &nbsp;</td>
                            <td><input type="text" value="" class="add_ipt" style="width:180px;"/>&nbsp; <font
                                    color="#ff4e00">*</font></td>
                        </tr>
                        <tr height="45">
                            <td align="right">验证码 &nbsp; &nbsp;</td>
                            <td><input type="text" value="" class="add_ipt" style="width:90px;"/>
                                <input type="button" value="发送短信" class="btn_tj" style="width:85px;"/></td>
                        </tr>
                        <tr height="45">
                            <td align="right">新手机 &nbsp; &nbsp;</td>
                            <td><input type="text" value="" class="add_ipt" style="width:180px;"/>&nbsp; <font
                                    color="#ff4e00">*</font></td>
                        </tr>
                        <tr height="50">
                            <td>&nbsp;</td>
                            <td><input type="submit" value="确认修改" class="btn_tj"/></td>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="m_des">
                <form>
                    <table border="0" style="width:880px;" cellspacing="0" cellpadding="0">
                        <tr height="45">
                            <td width="80" align="right">原密码 &nbsp; &nbsp;</td>
                            <td><input type="password" value="" class="add_ipt" style="width:180px;"/>&nbsp; <font
                                    color="#ff4e00">*</font></td>
                        </tr>
                        <tr height="45">
                            <td align="right">新密码 &nbsp; &nbsp;</td>
                            <td><input type="password" value="" class="add_ipt" style="width:180px;"/>&nbsp; <font
                                    color="#ff4e00">*</font></td>
                        </tr>
                        <tr height="45">
                            <td align="right">确认密码 &nbsp; &nbsp;</td>
                            <td><input type="password" value="" class="add_ipt" style="width:180px;"/>&nbsp; <font
                                    color="#ff4e00">*</font></td>
                        </tr>
                        <tr height="50">
                            <td>&nbsp;</td>
                            <td><input type="submit" value="确认修改" class="btn_tj"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
