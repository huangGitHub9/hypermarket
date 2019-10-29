<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/cart.js"></script>
<script type="text/javascript" src="/js/operaPageCss.js"></script>
<title>海玩商城</title>
</head>
<body>
	<!--Begin Header Begin-->
	<div id="searchBar">
		<%@ include file="searchBar.jsp"%>
	</div>
	<div class="menu_bg">
		<div class="menu">
			<!--Begin 商品分类详情 Begin-->
			<%@ include file="categoryBar.jsp"%>
		</div>
	</div>
	<!--End Menu End-->
	<div class="i_bg">
		<div id="settlement">
			<div class="content mar_20">
				<img src="/images/img1.jpg" />
			</div>
			<!--Begin 第一步：查看购物车 Begin -->
			<div class="content mar_20">
				<table border="0" class="car_tab"
					style="width: 1200px; margin-bottom: 50px;" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="car_th" width="80"><input id="checkAll" type="checkbox" /></td>
						<td class="car_th" width="80" style="font-weight: bold">商品图片</td>
						<td class="car_th" width="120" style="font-weight: bold">商品名称</td>
						<td class="car_th" width="150" style="font-weight: bold">单价</td>
						<td class="car_th" width="150" style="font-weight: bold">购买数量</td>
						<td class="car_th" width="130" style="font-weight: bold">小计</td>
						<td class="car_th" width="150" style="font-weight: bold">操作</td>
					</tr>
					<c:forEach items="${sessionScope.orders }" var="order"  varStatus="stat">
						<tr id="dataTr_${stat.index}" class="goods">
							<td align="center"><input type="checkbox" name="box" id="box_${stat.index}" value="${order.product.id}"></td>
							<td align="center">
								<div class="c_s_img">
									<a href="/user/getProductDetaile.action?productId=${order.product.id}"><img
										src="/images/${order.product.fileName }" width="73" height="73" /></a>
								</div>
							</td>
							<td align="center">${order.product.name }</td>
							<td align="center" style="color: #ff4e00;" id="price">￥${order.product.price}</td>
							<td align="center">
								<div class="c_num">
									<input type="button" value=""  onclick="downCount(this,${order.id},${order.product.price});" class="car_btn_1" />
									<input type="text"  value="${order.count}" name="quantit" class="car_ipt" readonly="readonly" />
									<input type="button" value="" onclick="addCount(this,${order.id},${order.product.price});" class="car_btn_2" />
								</div>
							</td>
							<td align="center" style="color: #ff4e00;" class="qprice">${order.product.price*order.count}</td>
							<td align="center"><a href="/user/deleteCarProduct.action?orderId=${order.id}"
								class="delete"><strong>删除</strong></a></td>
						</tr>
					</c:forEach>
					<tr height="70">
						<td colspan="6"
							style="font-family: 'Microsoft YaHei'; border-bottom: 0;"><span
							class="fr"><strong style="color: orangered">商品总价：</strong><b
                                style="font-size: 22px; color: #ff4e00;"  id="totleprice">￥<font class="allMoney">${money }</font></b></span></td>
					</tr>
					<tr valign="top" height="150">
						<td colspan="6" align="right">
						<!-- 继续购物 -->
						<a href="/user/main.action"><img src="/images/buy1.gif" /></a> 
						<!-- 确认结算 -->
						<a href="javascript:void(0);" onclick="buy();"><img src="/images/buy2.gif" /></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<script type="text/javascript">
            function buy() {
				//获取选中的checkbox
				var boxes = $("input[name='box']:checked");
				if(boxes.length == 0){
					/*layui.use('layer', function () {
						var layer = layui.layer;
						layer.msg('请选择需要购买的商品！', {time: 1000,icon: 5})
					});*/
					alert("请选择需要购买的商品！");
				}else{
					var arr = new Array();
					$.each(boxes,function(){
						arr.push(this.value);
					});
					//发送请求更新用户信息
					/*alert(arr);*/
					window.location = "/user/getUserAddress.action?productIds="+arr;

				}
            }
		</script>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
