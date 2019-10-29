<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理员登录页面</title>
    <base href="<%=basePath%>">
<style type="text/css">
body {
	font-family: "microsoft yahei";
	width: 100%;
	height: 100%;
	background: #272C2C;
    overflow: hidden;
}

header {
	width: 300px;
	height: 100px;
	margin: 100px auto;
	margin-bottom: 20px;
	text-align: center;
}

header p {
	font-size: 18px;
	color: #ccc;
}

header img {
	width: 310px;
}

section {
	width: 310px;
	height: 220px;
	border: 1px solid #aaa;
	margin: 50px auto;
	border-radius: 10px;
	box-shadow: 5px 5px 5px #aaa;
}

#adminname, #adminpwd {
	/*宽度，右内边距，边框，背景*/
	width: 200px;
	padding-left: 13px;
	border: 1px solid #ccc;
	border-radius: 8px;
	height: 33px;
	background: #272C2C;
	color: #ccc;
	font-family: "microsoft yahei";
	margin-bottom: 15px;
}

#adminname {
    margin-top: 50px;
}
.error{
    font-size: 14px;
    color: red;
    margin-left: 65px;
    position:absolute;
}
input {
	margin-left: 53px;
}

input[type="submit"] {
	width: 200px;
	height: 33px;
	border: 1px solid #03A0D5;
	border-radius: 8px;
	background: #03A0D5;
	font-family: "microsoft yahei";
    font-size: 15px;
	color: #111;
}

input[type="submit"]:hover {
	background: #03A0a0;
    cursor: pointer;
}
footer {
    margin-top: 120px;
}
footer img{
    width:90px;
    height:35px;
    border-radius: 3px;
}
footer p {
    color:#ccc;
    font-size: 12px;
    text-align: center;
}
</style>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
    src="<c:url value='/static/jquery/jquery-1.5.1.js'/>"></script>

</head>

<body>
    <header> <img src="<c:url value='/static/images/ybig_logo.png'/> ">
    <p>管理员登录</p>
    </header>
    <section>
     <p class="error" style="font-size:14px; color: red">${msg }</p>
    <form action="/admin/admin/login.do"
        method="post" target="_top">
        <input type="text" name="adminname" value="${admin.adminname }${adminname}" id="adminname"
            placeholder="管理员账户" /><br /> 
        <input type="password" name="adminpwd" id="adminpwd" placeholder="密码" /><br /> 
        <input type="submit" value="登录后台" />
    </form>
    </section>
    <footer>
        <p>Copyright &copy; 海玩网 2016-2020, All Rights Reserved&nbsp;&nbsp;|&nbsp;&nbsp;20162568黄旭林</p>
        <p><img src="<c:url value='/static/images/beian.png'/> ">&nbsp;&nbsp;<img src="<c:url value='/static/images/kexin.png'/> "></p>
    </footer>
</body>
</html>
