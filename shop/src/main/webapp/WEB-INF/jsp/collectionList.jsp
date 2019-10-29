<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="searchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="leftBar.jsp" %>
        <div class="m_right" id="content">
            <p></p>
            <div class="mem_tit">收藏夹</div>
            <div><img src="/images/mban_1.jpg" height="80px" width="1000px"/></div>
            <div class="list_c">
                <ul class="cate_list">
                    <c:forEach items="${collection}" var="favorite">
                        <li>
                            <div class="name">
                                <a href="#">${favorite.product.name}</a>
                            </div>
                            <div class="price">
                                <font>￥<span>${favorite.product.price}</span></font> &nbsp;
                            </div>
                                <%--点击图片进入购物车--%>
                            <div class="img">
                                <a href="/user/getProductDetaile.action?productId=${favorite.product.id}">
                                    <img src="/images/${favorite.product.fileName} " width="185"
                                         height="155"/>
                                </a>
                            </div>
                            <div class="carbg">
                                <a href="/user/remove.action?favoriteId=${favorite.id}" class="ss">取消</a>
                                <a href="/user/getProductDetaile.action?productId=${favorite.product.id}" class="j_car">立即购买</a>
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


