<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户信息</title>
    <%@ include file="header.jsp" %>
    <%--<script type="text/javascript" src="/static/js/tupian.js"></script>--%>
    <script type="text/javascript">
        var countTime = 60;
        function activeCode(obj) {
            // 调用倒计时方法
            // 信息提示
            showTime();
            $.ajax({
                type: "POST",//请求的方式
                url: "/user/active.action",//请求的地址
                data: "id="+obj,//需要传递至后台的参数
                success: function(msg){ //如果后台正常处理数据，没有出现异常则会自动回调 success中的函数，后台会将消息赋给  msg
                        alert(msg);
                        return false;
                },error:function(){ //如果后台出现异常
                    alert("网络出现异常！");
                    return false;
                }
            });
        }

        function showTime() {
            if (countTime == 0) {
                $("#send").attr('disabled', false);
                $("#send").val('重新发送');
                countTime = 60;
                return false;
            } else {
                $("#send").attr('disabled', true);
                $("#send").val('请稍后...' + countTime + "s");
                countTime--;
            }
            setTimeout(function () {
                showTime()
            }, 1000);
        }
    </script>
</head>
<body>
<%@ include file="searchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="leftBar.jsp" %>
        <div class="m_right" id="content">
            <div class="m_des">
                <table border="0" style="width:870px; line-height:22px;" cellspacing="0" cellpadding="0">
                    <tr valign="top">
                        <td width="115" id="crop-avatar">
                            <div id="preview" style="width: 90px;height: 90px">
                                <img id="imghead" src="/images/${sessionScope.user.fileName }"
                                     onClick="$('#previewImg').click();" width="90" height="90"/>
                            </div>
                            <form action="/user/modifyUserPhoto.action?id=${sessionScope.user.id}" enctype="multipart/form-data" method="post">
                            <input name="previewImg" id="previewImg" type="file" onChange="/*previewImage(this);*/$('#save').click();" style="display: none"/>
                            <input type="submit" value="保存" id="save" style="display: none"/>
                            </form>
                        </td>
                        <td>
                            <div class="m_user">${sessionScope.user.username }</div>
                            <br/><span style="color:#ff4e00;">${codeError}</span>
                            <p>等级：注册用户 </p>
                            <p><font color="#ff4e00">您还差 270 积分达到&nbsp;1000积分</font></p>
                            <p>上一次登录时间: 2019-09-28 18:19:47</p>
                            <c:if test="${sessionScope.user.activeCode == 0 || sessionScope.user.activeCode == 2}">
                                <p>您还没有通过邮件认证 &nbsp;<input type="button" id="send" class="btn_tj" value="点此发送认证邮件" onclick="activeCode(${sessionScope.user.id});" /></p>
                            </c:if>
                            <c:if test="${sessionScope.user.activeCode == 1}">
                                <p style="color:#ff4e00;">海玩商城认证</p>
                            </c:if>
                            <div class="m_notice">用户中心公告！</div>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mem_t">资产信息</div>
            <table border="0" class="mon_tab" style="width:870px; margin-bottom:20px;" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="33%">用户等级：<span style="color:#555555;">普通会员</span></td>
                    <td width="33%">消费金额：<span>￥200元</span></td>
                    <td width="33%">返还积分：<span>99R</span></td>
                </tr>
                <tr>
                    <td>账户余额：<span>￥200元</span></td>
                    </td>
                    <td>红包个数：<span style="color:#555555;">3个</span></td>
                    </td>
                    <td>红包价值：<span>￥50元</span></td>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">订单提醒：
                        <font style="font-family:'宋体';">待付款(<span>0</span>) &nbsp; &nbsp; &nbsp; &nbsp;
                            待收货(<span>2</span>) &nbsp; &nbsp; &nbsp; &nbsp; 待评论(<span>1</span>)</font>
                    </td>
                </tr>
            </table>

            <div class="mem_t">账号信息</div>
            <table border="0" class="acc_tab" style="width:870px;" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td_l">用户ID：</td>
                    <td>${sessionScope.user.username}</td>
                </tr>
                <tr>
                    <td class="td_l b_none">身份证号：</td>
                    <td>${sessionScope.user.identityCode.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2")}</td>
                </tr>
                <tr>
                    <td class="td_l b_none">电 话：</td>
                    <td>${sessionScope.user.mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2")}</td>
                </tr>
                <tr>
                    <td class="td_l">邮 箱：</td>
                    <td>
                        ${fn:substring(sessionScope.user.email,0,3)}****${fn:substring(sessionScope.user.email,fn:length(sessionScope.user.email)-7,(fn:length(sessionScope.user.email)))}
                    </td>
                </tr>
                <tr>
                    <td class="td_l b_none">注册时间：</td>
                    <td>2019/10/10</td>
                </tr>
                <tr>
                    <td class="td_l">完成订单：</td>
                    <td>10</td>
                </tr>
                <tr>
                    <td class="td_l b_none">邀请人：</td>
                    <td>黄**</td>
                </tr>
                <tr>
                    <td class="td_l">登录次数：</td>
                    <td>3</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</div>
</body>
</html>

















