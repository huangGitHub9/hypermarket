<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>购物车</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <script type="text/javascript" charset="UTF-8" src="/js/cart.js"></script>
</head>

<body>
<div class="top">
    <div class="logo">
        <a href="/main/getAll.action"><img src="/images/logo.png"></a>
    </div>
    <div class="search">
        <form action="/main/searchProduct.action" method="post">
            <input type="text"  name="keyWord" class="s_ipt">
            <input type="submit" value="搜索" class="s_btn">
        </form>
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone maxS</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
        <div class="car_t">
            购物车 [
            <span>
                <c:if test="${sessionScope.orders!=null && sessionScope.orders.size()>0}">
                    ${sessionScope.orders.size()}
                </c:if>
                <c:if test="${sessionScope.orders==null || sessionScope.orders.size()<=0}">
                    空
                </c:if>
            </span>]
        </div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->

                <div class="un_login"><strong style="color: orangered">我的购物车</strong></div>

            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:forEach items="${sessionScope.orders}" var="order">
                        <li>
                            <div class="img"><a href="/user/getProductDetaile.action?productId=${order.product.id}"><img src="/images/${order.product.fileName }" width="58" height="58" /></a></div>
                            <div class="name">${order.product.name}</div>
                            <div class="price"><font color="#ff4e00">￥${order.product.price}</font> X<span class="count">${order.count}</span></div>
                        </li>
                </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp;<font color="#ff4e00">￥</font><span class="carMoney">${sessionScope.money}</span></div>
            <c:if test="${sessionScope.user==null}">
                <div class="price_a"><a href="/user/loginA.action">去登录</a></div>
            </c:if>
            <c:if test="${sessionScope.user!=null}">
                <c:if test="${sessionScope.money !=0}">
                    <div class="price_a"><a href="/user/toSettlement.action">去购物车结算</a></div>
                </c:if>
            </c:if>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>

</body>
</html>

