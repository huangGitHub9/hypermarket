<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>海玩商城管理系统</title>
    <link type="text/css" rel="stylesheet" href="${path}/statics/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="${path}/statics/css/public.css"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>海玩商城管理系统</h1>
    <div class="publicHeaderR">
        <input type="hidden" name="id" value="${user.id }" id="id">
        <p><span>下午好！欢迎</span><span style="color: #fff21b">
            	<c:if test="${user.userRole==1}">管理员</c:if> 
            	<c:if test="${user.userRole==2}">经理</c:if> 
            	<c:if test="${user.userRole==3}">普通员工</c:if> 
            	— ${user.userName }&nbsp;</span>
            <img alt="加载中..." class="photo" style="width: 25px;height: 25px;"
                 src="${path }/statics/upload/${user.userPhoto }"/>
        </p>
        <a href="${path}/logout">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="${path}/sys/bill">订单管理</a></li>
                <li><a href="${path }/sys/provider">供应商管理</a></li>
                <li><a href="${path }/sys/admin/user">职员管理</a></li>
                <li><a href="${path }/sys/person">个人中心</a></li>
                <li><a href="${path }/sys/skin">换肤特效</a></li>
                <li><a href="${path }/sys/pwdmodify">密码修改</a></li>
                <li><a href="${path }/logout">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>
</section>
     
     
     