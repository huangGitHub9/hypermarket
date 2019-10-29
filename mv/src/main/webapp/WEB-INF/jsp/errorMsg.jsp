<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误消息提示页面</title>
</head>
<body>
    <dvi>
        <p> <h1><front color="red">${requestScope.errorMsg}</front></h1></p>
        <p><button type="button" onclick="javascript:history.go(-1)" >返回</button></p>
    </dvi>
</body>
</html>
