/**
 * 商品列表页添加到购物车
 * 
 * @param entityId
 *            商品id
 * @param quantity
 */
function  addCount(obj,orderId,productPrice) {
    var index = $(".car_btn_2").index(obj);
    var count = $("input[name= 'quantit']").eq(index).val();
    var  m = $(".allMoney").html();
    var allMoney = parseInt(m)+ parseInt(productPrice);
    $(".allMoney").html(allMoney);
    $(".carMoney").html(allMoney);
    count  =  ++count;
    var money = count*productPrice;
        $("input[name='quantit']").eq(index).val(count);
    $(".qprice").eq(index).html(money);
    $(".count").eq(index).html(count);
    // $(".car_ipt").eq(index).val(count);
    $.ajax({
        "url" : "/user/updateOrderCount.action",
        "data" : "orderId="+orderId+"&count="+count,
        "type" : "post",
        "dataType" : "text"
    })
}

function downCount(obj,orderId,productPrice) {
    var index = $(".car_btn_1").index(obj);
    var count = $("input[name= 'quantit']").eq(index).val();
    var  m = $(".carMoney").html();
    count  =  --count;
    //js的等于和java中的一样是双等
    if(count == 0){
        count = 1;
        return false;
    }
    var allMoney = m-productPrice;
    $(".allMoney").html(allMoney);
    $(".carMoney").html(allMoney);
    var money = count*productPrice;
   $(".qprice").eq(index).html(money);
    $(".count").eq(index).html(count);
    $("input[name='quantit']").eq(index).val(count);
    // $(".car_ipt").eq(index).val(count);
    $.ajax({
        "url" : "/user/updateOrderCount.action",
        "data" : "orderId="+orderId+"&count="+count,
        "type" : "post",
        "dataType" : "text"
    })
}

function addCartByParam(entityId, quantity) {
	// 添加到购物车
	$.ajax({
		"url" : "cart_add",
		"type" : "post",
		"data" : "entityId=" + entityId + "&quantity=" + quantity,
		"dataType" : "json",
		success : function(jsonStr) {

			var j = JSON.stringify(jsonStr);
			alert(j);
			window.location.href = "/user/toSettlement.action?product="
					+ jsonStr;

			// var result = eval("(" + jsonStr + ")");
			// // 状态判断
			// if (result.status == 1) {
			// showMessage("操作成功");
			// refreshCart();
			// } else {
			// showMessage(result.message);
			// }
		}
	})
}

function addQuantity(obj,price,entityId, stock) {
	//获取被点击的事件在数组之中的小标  obj代表当前的点击事件的input输入框
	var index = $(".car_btn_2").index(obj);
//	alert(index);
//	var index2 = $(".qprice").index();
//	alert(index2);
	// 获取当前输入框的商品数量
	var quantity = $("input[name='quantit']").eq(index).val();
	if (stock <= quantity) {
		alert("商品数量不足");
		return false;
	}
	quantity = ++quantity;
	// 将数据写入到原有的输入框内
	$("input[name='quantit']").eq(index).val(quantity);
//	var prices = quantity*price;
//	$(".qprice").eq(index).html("￥"+prices); 
	
	//将数据持久化到购物车之中
	$.ajax({
		"url" : "cart_addQuantity", 
		"type" : "post",
		"data" : "quantity="+quantity+"&entityId="+entityId,
		"dataType" : "text",
		success : function(jsonStr) {
			//获取到总价格  写入到相应的地方
//			$("#totleprice").html("￥"+jsonStr); 
		}
	});
	window.location.href = "toSettlement";
}
/**
 * 减去 数量减
 * 
 * @param obj
 * @param entityId
 */
function subQuantity(obj,price,entityId) {
	//获取被点击的事件在数组之中的小标  obj代表当前的点击事件的input输入框
	var index = $(".car_btn_1").index(obj);
//	alert(index);
	var quantity = $("input[name='quantit']").eq(index).val();
	quantity = --quantity;
	if (quantity == 0) {
		return false;
	}
	// 将数据写入到原有的输入框内
	$("input[name='quantit']").eq(index).val(quantity);
	//改变小计的价格 
//	var prices = quantity*price;
//	$(".qprice").eq(index).html("￥"+prices); 
	
	//将数据持久化到购物车之中 
	$.ajax({
		"url" : "cart_addQuantity",
		"type" : "post",
		"data" : "quantity="+quantity+"&entityId="+entityId,
		"dataType" : "text",
		success : function(jsonStr) {
			//获取到总价格  写入到相应的地方
//			$("#totleprice").html("￥"+jsonStr); 
		}
	});
	window.location.href = "toSettlement";
}
