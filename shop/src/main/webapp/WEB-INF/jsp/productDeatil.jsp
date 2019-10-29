<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="header.jsp" %>
    <link href="/static/css/cloudzoom.css" rel="stylesheet"/>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/cloudzoom.js"></script>
    <script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        CloudZoom.quickStart();
    </script>
    <style type="text/css">
        .jqueryzoom {
            position: relative;
            padding: 0; border: solid 1px #eaeaea;
            width: 300px;
            height:300px;
            overflow: hidden;
            display: inline-block; }
        /*放大镜是基于图片大小自动生成的，每次都要把jqueryzoom中img的大小和jqueryzoom盒子大小设置为一样的，不然放大镜滑块有可能溢出*/
        .jqueryzoom img { width: 300px; }
    </style>
    <title>海玩商城</title>
</head>
<body>
<div id="searchBar">
    <%@ include file="searchBar.jsp" %>
</div>
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <%@ include file="categoryBar.jsp" %>
        <!--End 商品分类详情 End-->
    </div>
</div>

<div class="i_bg">
    <div class="postion">
    </div>
    <div class="content">
        <div id="tsShopContainer" style="float:left;">
                <div class="jqueryzoom">
                    <img id="img" class="cloudzoom" src="/images/${product.fileName }"
                         data-cloudzoom="zoomSizeMode:'image',zoomImage: '/images/${product.fileName }',autoInside: 30"
                         alt="加载中..." title=""/> </div>
                </div>
        <div class="pro_des">
            <div class="des_name">
                <input type="hidden" value="${product.id}"  name="entityId" class="n_ipt"/>
                <p>${product.name }</p>
            </div>
            <div class="des_price">
                本店价格：<b>￥${product.price }</b></br>
                发货地区：<img src="/images/address.png" alt="加载中" width="15px" height="15px"/> 上海
            </div>
           <div class="des_price">
               库存：<b>${product.count }</b></br>
               服务: <img src="/images/suc.png" alt="加载中..." width="10px" height="10px"/>15天内发货
               <img src="/images/suc.png" alt="加载中..." width="10px" height="10px"/>7天无理由退货
               <img src="/images/suc.png" alt="加载中..." width="10px" height="10px"/>卖家为您购买的商品投保退货运险费
            </div>
            <div class="des_choice">
                <span class="fl">型号选择：</span>
                <ul>
                    <li class="checked">30ml
                        <div class="ch_img"></div>
                    </li>
                    <li>50ml
                        <div class="ch_img"></div>
                    </li>
                    <li>100ml
                        <div class="ch_img"></div>
                    </li>
                </ul>
            </div>
            <div class="des_choice">
                <span class="fl">颜色选择：</span>
                <ul>
                    <li>红色
                        <div class="ch_img"></div>
                    </li>
                    <li class="checked">白色
                        <div class="ch_img"></div>
                    </li>
                    <li>黑色
                        <div class="ch_img"></div>
                    </li>
                </ul>
            </div>
                <div class="des_join">
                    <div class="j_nums">
                        <input type="text"   value="1"  name="quantity" class="n_ipt"/>
                        <input type="button" value="" onclick="addUpdate(${product.id},${product.count});" class="n_btn_1"/>
                        <input type="button" value="" onclick="jianUpdate(${product.id});" class="n_btn_2"/>
                            <%--<input type="hidden" name="productStock" value="100">--%>
                    </div>
                    <c:if test="${user!=null}">
                    <spn class="fl">
                        <img src="/images/j_car.png" onclick="addOrder(${product.id},${product.count})"/>
                    </spn>
                    </c:if>
                    <c:if test="${user==null}">
                        <img src="/images/j_car.png" onclick="loginOrder();"/>
                    </c:if>
                </div>
        </div>
    </div>
    <div class="content mar_20">
        <div class="l_list">
            <div class="des_border">
                <div class="des_tit">
                    <ul>
                        <li class="current"><a href="#p_attribute">商品参数</a></li>
                    </ul>
                </div>
                <div class="des_con" id="p_attribute">
                    <table border="0" align="center" style="width:100%; font-family:'宋体'; margin:10px auto;"
                           cellspacing="0" cellpadding="0">
                        <tr>
                            <td>商品名称：${product.name }</td>
                            <td>商品价格：${product.price }</td>
                            <td>品牌： 迪奥（Dior）</td>
                            <td>上架时间：2019-09-06 09:19:09 </td>
                        </tr>
                        <tr>
                            <td>商品毛重：160.00g</td>
                            <td>商品产地：法国</td>
                            <td>香调：果香调香型：淡香水/香露EDT</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>容量：1ml-15ml </td>
                            <td>类型：女士香水，Q版香水，组合套装</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    /*添加到购物车*/
    function addOrder(productId,stock) {
        var count = $("input[name='quantity']").val();
        if(count > stock){
            alert("商品数量不足！");
            return false;
        }else if (count == 0 || count == null){
            alert("商品数量不能为0！");
            return false;
        } else{
            window.location.href="/user/addToCar.action?productId="
                +productId+"&count="+count;
        }
    }
    /*加数量*/
    function addUpdate(entityId, stock) {
        // 获取当前输入框的商品数量
        var quantity = $("input[name='quantity']").val();
        if (stock <= quantity) {
            alert("商品数量不足！");
            return false;
        }
        quantity = ++quantity;
        // 将数据写入到原有的输入框内
        $("input[name='quantity']").val(quantity);
    }

    /*减数量*/
    function jianUpdate(entityId) {
        var quantity = $("input[name='quantity']").val();
        quantity = --quantity;
        if (quantity == 0) {
            quantity = 1;
        }
        // 将数据写入到原有的输入框内
        $("input[name='quantity']").val(quantity);
    }

    function loginOrder() {
        alert("请先登录！")
    }
</script>
</body>
</html>
