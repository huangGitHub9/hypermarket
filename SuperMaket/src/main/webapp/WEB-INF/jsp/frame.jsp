<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>

<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath }/statics/images/clock.jpg" alt=""/>
    <div class="wFont">
        <h2>${user.userName }</h2>
        <p>欢迎来到海玩商城管理系统!</p>
    </div>
</div>
<!-- include指令：当JSP转换成Servlet时引入指定文件 -->
<%-- <%@include file="/WEB-INF/jsp/common/foot.jsp" %> --%>
