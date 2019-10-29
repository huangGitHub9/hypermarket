<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>网上商城</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%@ include file="header.jsp" %>
    <script type="text/javascript">
        $(function () {
            $(".nav").hover(function () {
                $(this).find(".leftNav").show();
            }, function () {
                $(this).find(".leftNav").hide();
            });
            $(".leftNav ul li").hover(function () {
                $(this).find(".fj").addClass("nuw");
                $(this).find(".zj").show();
            }, function () {
                $(this).find(".fj").removeClass("nuw");
                $(this).find(".zj").hide();
            });
        })
    </script>
    <title>网上商城</title>
</head>
<body>
<!--Begin Header Begin-->

<div id="searchBar">
    <%@ include file="searchBar.jsp" %>
</div>
<!--End Header End-->
<!--Begin Menu Begin-->
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <%@ include file="categoryBar.jsp" %>
        <!--End 商品分类详情 End-->
    </div>
</div>
<!--End Menu End-->
<div class="i_bg">
    <!--Begin 筛选条件 Begin-->
    <!--End 筛选条件 End-->
    <div class="content mar_20">
        <div id="favoriteList"></div>
        <div class="l_list">
            <div class="list_t">
                <span class="fr" style="font-size: 16px; font-weight: bold; color: red">国庆快乐</span>
            </div>
            <div class="list_c">
                <ul class="cate_list">
                    <c:forEach items="${type1Product}" var="product">
                        <li>
                            <div class="name">
                                <a href="#">${product.name}</a>
                            </div>
                            <div class="price">
                                <font>￥<span>${product.price}</span></font> &nbsp;
                            </div>
                                <%--点击图片进入购物车--%>
                            <div class="img">
                                <a href="/user/getProductDetaile.action?productId=${product.id}">
                                    <img src="/images/${product.fileName} " width="195"
                                         height="185"/>
                                </a>
                            </div>
                            <div class="carbg">
                                <a href="/user/doCollect.action?productId=${product.id}" class="ss">收藏</a>
                                <a href="/user/getProductDetaile.action?productId=${product.id}" class="j_car">进入店铺</a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
