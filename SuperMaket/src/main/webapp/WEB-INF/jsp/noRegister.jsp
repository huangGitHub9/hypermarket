<%--
  Created by IntelliJ IDEA.
  User: 落叶
  Date: 2019/9/10
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>404创意页面</title>

    <link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="/css/style404.css" rel="stylesheet"/>

</head>

<body>

<!-- Start 404 Area -->
<div class="section-not-found">
    <div class="not-found-page-wrapper bg_image-errer text-center">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ht-not-found not-found-default">
                        <div class="content">
                            <img src="/images/404.png" alt="404 image" width="560" height="428"/>
                            <h2>手机号或验证码不存在，请进行正确的验证操作！</h2>
                            <a class="page-back-btn" href="${ctx}login"><span>返回首页</span></a>
                        </div>
                        <div id="clouds">
                            <div class="cloud cloud-1"></div>
                            <div class="cloud cloud-2"></div>
                            <div class="cloud cloud-3"></div>
                            <div class="cloud cloud-4"></div>
                            <div class="cloud cloud-5"></div>
                            <div class="cloud cloud-6"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End 404 Area -->

</body>
</html>
