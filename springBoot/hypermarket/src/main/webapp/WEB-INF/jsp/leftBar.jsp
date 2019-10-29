<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="m_left">
    <div class="left_n">管理中心</div>
    <div class="left_m">
        <div class="left_m_t t_bg1">订单中心</div>
        <ul>
            <li><a href="/user/getMyHasPayedOrder.action" >已付款</a></li>
            <li><a href="/user/address.action">收货地址</a></li>
        </ul>
    </div>
    <div class="left_m">
        <div class="left_m_t t_bg2">会员中心</div>
        <ul>
            <li><a href="/user/userInfo.action" >用户信息</a></li>
            <li><a href="/user/getCollection.action">我的收藏</a></li>
        </ul>
    </div>
    <div class="left_m">
        <div class="left_m_t t_bg3">账户中心</div>
        <ul>
            <li><a href="/main/getAllNews.action" >资讯列表</a></li>
            <li><a href="/user/safe.action" >账户安全</a></li>
        </ul>
    </div>
</div>