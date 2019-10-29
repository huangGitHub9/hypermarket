<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>微信支付页</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/webbase.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/pages-weixinpay.css"/>
    <link href="${ctx}/static/css/taobao.css" rel="stylesheet"/>

    <script type="text/javascript" src="${ctx}/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/pay/jquery-qrcode-0.14.0/jquery-qrcode-0.14.0.js"></script>
    <script type="text/javascript">
        $(function () {
            var recommend_code = "${recommendCode}";    //微信扫码关注
            $("#qrcode_div").qrcode({
                render: "canvas",        //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
                text: recommend_code,    //扫描了二维码后的内容显示,在这里也可以直接填一个网址，扫描二维码后
                size: 150,               //大小
                background: "#ffffff"    //二维码的后景色
            });

            //调用后台
            //检测是否支付成功
            $.ajax({
                type: "POST",
                url: "${ctx}/user/queryPayStatus.action",
                data: "orderNo=${serialNumber}",
                success: function (msg) {

                    if (msg == "ok") {
                        window.location = "${ctx}/user/paySuccess.action";
                    } else {
                        /*alert(msg);*/
                        window.location = "${ctx}/user/payfail.action";
                    }
                }
            });
        })
    </script>
</head>

<body>
<!-- 横幅导航条开始 -->
<div class="cart py-container">
    <!--logoArea-->
    <div class="logoArea">
        <div class="fl"><img src="${ctx}/images/pay/Logo.png" style="width:200px;height:100px"/><span
                class="title">收银台</span></div>
    </div>
    <!--主内容-->
    <div class="checkout py-container  pay">
        <div class="checkout-tit">
            <h4 class="fl tit-txt"><span class="success-icon"></span><span
                    class="success-info">订单提交成功，请您及时付款！订单号：${serialNumber}</span></h4>
            <span class="fr"><em class="sui-lead">应付金额：</em><em class="orange money">￥${zfmoney}</em>元
                    </span>
            <div class="clearfix"></div>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="font-weight: bold;font-size: 14px">收货地址：</span>${newAddress}
        </div>
        <div class="checkout-steps">
            <div class="fl weixin">微信支付</div>
            <div class="fl sao">
                <p class="red"></p>
                <div class="fl code">
                    <div id="qrcode_div"></div>
                    <div class="saosao" style="width: 170px">
                        <p>请使用微信扫一扫</p>
                        <p>扫描二维码支付</p>
                    </div>
                </div>
                <div class="fl phone"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--页面底部END-->
</body>
</html>