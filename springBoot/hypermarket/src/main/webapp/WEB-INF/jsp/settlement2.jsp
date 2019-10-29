<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="header.jsp" %>
	<link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <title>海玩商城</title>
</head>
<body>
<!--Begin Header Begin-->
<div id="searchBar">
    <%@ include file="searchBar.jsp" %>
</div>
<!--End Header End-->
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <%@ include file="categoryBar.jsp" %>
        <!--End 商品分类详情 End-->
    </div>
</div>
<!--End Menu End-->
<div class="i_bg">
    <div id="settlement">

			<div class="content mar_20">
				<%--支付进度条图片--%>
			    <img src="/images/img2.jpg"/>
			</div>
			<div class="content mar_20">
			    <div class="two_bg">
			        <div class="two_t">
			            <span class="fr"><a href="/user/toSettlement.action"><input type="button" value="返回购物车" class="historyBack"/></a></span><strong>商品列表</strong>
			        </div>
<%--商品信息开始---------------------------------------------------------------%>&nbsp;
			        <table border="0" class="car_tab" style="width:1110px;" cellpacing="0" cellpadding="0">
			            <tr>
							<td class="car_th" width="80"><strong>商品图片</strong></td>
			                <td class="car_th" width="100"><strong>商品名称</strong></td>
			                <td class="car_th" width="100"><strong>购买数量</strong></td>
			                <td class="car_th" width="130"><strong>小计</strong></td>
                            <td class="car_th" width="130"><strong>操作</strong></td>
			            </tr>
			            <c:forEach items="${orderProductList }" var="order" varStatus="stat">
			            	<tr>
			                    <td align="center"><img src="/images/${order.product.fileName }" width="73" height="73"/></td>
								<td align="center" style="font-weight: bold"><div>${order.product.name }</div></td>
			                    <td align="center" style="font-weight: bold">${order.count}</td>
			                    <td align="center" style="color:#ff4e00;font-size: 16px" class="qprice">￥${order.count*order.product.price  }</td>
                                <td align="center" style="font-size: 16px;font-weight: bold"><a href="/user/deleteOrder.action?orderId=${order.id}" class="delete">删除</a></td>
			                </tr>
			            </c:forEach>
			        </table>
<%--商品信息结束----------------------------------------------------------------------%>
			        <div class="two_t">
			            <span class="fr"></span><strong>收货人信息</strong>
			        </div>
			        <table border="0" class="peo_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
			            <tr>
			                <td class="p_td" width="160">用户名称</td>
			                <td width="395">${user.name }</td>
			                <td class="p_td">登录名称</td>
			                <td>${user.username }</td>
			            </tr>
			            <tr>
			                <td class="p_td">手机</td>
			                <td>${user.mobile }</td>
			                <td class="p_td" width="160">电子邮件</td>
			                <td width="395">${user.email }</td>
			            </tr>
			        </table>
			        <div class="two_t">
			            <span class="fr"></span><strong>选择收货地址</strong>
			        </div>
<%--------------------------------------%>
			        <table border="0" class="peo_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
						<c:if test="${aId == 2}">
						<tr>
							<td class="p_td" width="160">
								快速添加地址&nbsp;&nbsp;&nbsp;
							</td>
							<td><input type="text" style="height: 20px" class= "addNewAddress"/></td>
							<td><input type="button" value="提交" onclick="javascript:addNewAddress();"/></td>
						</tr>
						</c:if>
                            <c:if test="${aId == 1}">
			            	<tr>
			                    <td class="p_td" width="160">
									默认地址&nbsp;&nbsp;&nbsp;<input type="radio"  name="selectAddress" value="${addressId }" checked>
			                    </td>
			                    <td>${address }</td>
								<td align="center" style="width: 50px"><a href="/user/address.action" style="color: red;">修改</a></td>
			                </tr>
                            </c:if>
			        </table>

					<div class="two_t"><strong>支付方式</strong></div>
					<ul class="pay">
						<li class="checked">微信支付<div class="ch_img"></div></li>
						<li>银行亏款/转账<div class="ch_img"></div></li>
						<li>货到付款<div class="ch_img"></div></li>
						<li>支付宝<div class="ch_img"></div></li>
					</ul>

					<div class="two_t"><strong>商品包装</strong></div>
					<table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
						<tr>
							<td class="car_th" width="80"></td>
							<td class="car_th" width="490">名称</td>
							<td class="car_th" width="180">费用</td>
							<td class="car_th" width="180">免费额度</td>
							<td class="car_th" width="180">图片</td>
						</tr>
						<tr>
							<td align="center"><input type="radio" name="pack" value="0.00" checked/></td>
							<td><b style="font-size:14px;">不要包装</b></td>
							<td align="center">￥0.00</td>
							<td align="center">￥0.00</td>
							<td align="center"></td>
						</tr>
						<tr>
							<td align="center"><input type="radio" name="pack" value="15.00"/></td>
							<td><b style="font-size:14px;">精品包装</b></td>
							<td align="center">￥15.00</td>
							<td align="center">￥0.00</td>
							<td align="center"><a href="/images/baozhuanghe.jpg" style="color:#ff4e00;">查看</a></td>
						</tr>
					</table>

<%-------------------------付款栏--------------------------%>
			        <table border="0" style="width:900px; margin-top:20px;" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right">
								该订单完成后，您将获得 <font color="#ff4e00">${moneyOrder / 10}</font> 积分 ，以及价值 <font color="#ff4e00">￥0.00</font> 的红包 <br />
								商品总价: <font color="#ff4e00">￥${moneyOrder }</font>  + 包装费用: <font color="#ff4e00">￥0.00</font>
							</td>
						</tr>
			            <tr height="70">
			                <td align="right">
			                    <b style="font-size:14px;">应付款金额：<span
										style="font-size:22px; color:#ff4e00;">￥<font class="zfmoney">${moneyOrder }</font></span></b>
			                </td>
			            </tr>
			            <tr height="70">
                           <td align="right"><a href="javascript:void(0)" onclick="settlement3();"><img
                                    src="/images/btn_sure.gif"/></a></td>
			            </tr>
			        </table>
<%---------------------------------------------------------------%>
			    </div>
			</div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
<script type="text/javascript">
	/**
	 * 结算 订单支付提醒
	 */
	function settlement3() {
		// 判断地址 redio 类型的选择框可以选择标签后：checcked  表示被选择的那个input输入框
		var addressId = $("input[name='selectAddress']:checked").val();
		var zfmoney = $(".zfmoney").html();
		if (addressId == "" || addressId == null) {
			alert("收货地址不能为空！");
			return false;
		}
		//有默认地址
        if (zfmoney>0){
            if (addressId > 0){
                window.location = "/user/updateOrderAddress.action?addressId="+addressId+"&zfmoney="+zfmoney;
            }
        } else{
            alert("订单为空，请添加订单哦！")
        }

	}

	function addNewAddress() {
		var addNewAddress = $(".addNewAddress").val();
		if (addNewAddress == null || addNewAddress == "" ) {
			alert("添加的地址不能为空！");
			return false;
		}else{
			window.location = "/user/addNewAddress.action?addNewAddress="+addNewAddress;
		}
	}
</script>

</html>
