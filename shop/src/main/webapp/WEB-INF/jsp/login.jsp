<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
            <form action="/user/login.action" method="post">
                <p></p>
            <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr height="50" valign="top">
                <td width="55">&nbsp;</td>
                <td>
                    <span class="fl" style="font-size:24px;color: orangered"><strong>登录</strong></span>
                    <span class="fr">扫码登录点击这里>>><a href="/user/weChat.action" style="color:#ff4e00;">微信扫码</a></span>
                </td>
            </tr>
              <tr height="70">
                <td style="font-weight: bold">昵称&nbsp;</td>
                <td><input type="text" name="username" id="loginName" class="l_user" required/></td>
              </tr>
              <tr height="70">
                <td style="font-weight: bold">密码&nbsp;</td>
                <td><input type="password" name="password" id="password"  class="l_pwd" required/></td>
              </tr>
              <tr height="60">
              	<td>&nbsp;</td>
                <td><input type="submit" value="登录" class="log_btn" /></td>
              </tr>
            </table>
            	<!-- 购物车结算的时候跳转的页面 -->
            <input type="hidden" value="${flag }" name="flag"/>
            </form>
        </div>
        <div style="padding-left: 900px">
            <strong style="font-size: 14px;color: red">${error}</strong></div>
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
<%--<script type="text/javascript">
    setTimeout(function(){document.getElementById("dialog").style.display="none";},4000);
</script>--%>
</body>
</html>