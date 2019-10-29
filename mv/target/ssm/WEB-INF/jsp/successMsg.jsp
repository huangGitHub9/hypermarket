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
    HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
    String jumpView = (String)req.getAttribute("jumpView");
    String path = req.getServletContext().getContextPath();
    res.sendRedirect(path+"/sys/"+jumpView);
    //res.setHeader("Refresh", "5;URL=http://localhost:8080/dyglxt/sys/"+jumpView+"");

%>
    <dvi style="margin: 0 auto">
        <p> <h1><front color="red">${requestScope.successMsg}</front></h1></p>
       <%-- <p><a href="<c:url value='/sys/indexUI.action'/>">首页</a></p>--%>
        <p>请你等候5s 自动完成跳转 </p>
    </dvi>

</body>
</html>
