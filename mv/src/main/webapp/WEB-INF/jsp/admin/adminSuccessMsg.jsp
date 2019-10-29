<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>成功消息提示页面</title>
</head>
<body>
<%
    HttpServletResponse res = (HttpServletResponse)pageContext.getResponse();
    String path = pageContext.getRequest().getServletContext().getContextPath();
    //res.setHeader("Refresh", "5;URL=http://www.xupenghao.xin:9998/dyglxt/admin/adminIndexUI.action");
    res.setHeader("Refresh", "5;URL="+path+"/admin/adminIndexUI.action");
%>
    <dvi style="margin: 0 auto">
        <p> <h1><front color="red">${requestScope.adminSuccessMsg}</front></h1></p>
       <%-- <p><a href="<c:url value='/sys/indexUI.action'/>">首页</a></p>--%>
        <p>5s后自动到系统首页</p>
    </dvi>
</body>
</html>
