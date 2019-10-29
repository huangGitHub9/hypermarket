<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<%@ include file="header.jsp" %>
	<title>海玩商城</title>
</head>
<body>
<div id="searchBar">
	<%@ include file="searchBar.jsp" %>
</div>
<div class="menu_bg">
	<div class="menu">
		<%@ include file="categoryBar.jsp" %>
	</div>
</div>
<div class="i_bg">
	<div id="settlement">

		<div class="content mar_20">
			<img src="/images/img3.jpg"/>
		</div>
		<div class="content mar_20">
			<div class="warning">
				<table border="0" style="width:1000px; text-align:center;" cellspacing="0" cellpadding="0">
					<tr height="35">
						<td style="font-size:18px;">
							<img src="/static/images/pay/fail.png" width="20" height="20">支付失败，请稍后再试</font>
						</td>
					</tr>
					<tr>
						<td style="font-size:14px; font-family:'宋体'; padding:10px 0 20px 0; border-bottom:1px solid #b6b6b6;">
                            失败原因：支付超时！
						</td>
					</tr>
					<tr>
						<td style="padding:20px 0 30px 0; font-family:'宋体';">
                            您可以先去　<a href="/user/main.action" target="_blank">欢乐购首页</a>　逛逛
						</td>
					</tr>
					<tr>
						<td>
							<a href="/user/main.action">首页</a>     <a href="/user/userInfo.action">用户中心</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script>
	</script>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>
