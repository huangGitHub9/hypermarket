<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户收货地址</title>
    <%--头部的导航框--%>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
<%--搜索框--%>
<%@ include file="/WEB-INF/jsp/searchBar.jsp" %>
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/WEB-INF/jsp/leftBar.jsp" %>
        <div class="m_right" id="content">
            <p></p>
            <div class="mem_tit">收货地址</div>
            <c:if test="${delete == 2}">
                <div class="address">
                    <div class="a_close"><a href="/user/deleteAddress.action?id=${id}"><img src="/images/a_close.png"/></a>
                    </div>
                    <table border="0" class="add_t" align="center" style="width:98%; margin:10px auto;" cellspacing="0"
                           cellpadding="0">
                        <tr>
                            <td colspan="2" style="font-size:14px; color:#ff4e00;">默认地址</td>
                        </tr>
                        <tr>
                            <td align="right" width="80">收货人姓名：</td>
                            <td>${userName}</td>
                        </tr>
                        <tr>
                            <td align="right">详细地址：</td>
                            <td>${address}</td>
                        </tr>
                        <tr>
                            <td align="right">标志建筑：</td>
                            <td>华隆首府</td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <div class="address">
                <%--<div class="a_close"><a href="#"><img src="/images/a_close.png" /></a></div>--%>
                <table border="0" class="add_t" align="center" style="width:98%; margin:10px auto;" cellspacing="0"
                       cellpadding="0">
                    <tr>
                        <td align="center" width="80px">收货人姓名</td>
                        <td align="center" width="180px">详细地址</td>
                        <td align="center" width="80px">标志建筑</td>
                        <td align="center" width="120px">操作</td>
                    </tr>
                    <c:forEach items="${addressList }" var="useraddress" varStatus="stat">
                        <tr>
                            <td align="center">
                                <div>${userName }</div>
                            </td>
                            <td align="center">${useraddress.address }</td>
                            <td align="center">华隆首府</td>
                            <td align="center"><a href="/user/addDeleteAddress.action?id=${useraddress.id}"
                                                  style="color:#ff4e00;">设为默认</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="mem_tit">
                <a href="#"><img src="/images/add_ad.gif" /></a>
            </div>
            <table border="0" class="add_tab" style="width:930px;"  cellspacing="0" cellpadding="0">
                <tr>
                    <td width="135" align="right">配送地区</td>
                    <td colspan="3" style="font-family:'宋体';">
                        <select class="jj" name="province">
                            <option value="0" selected="selected">请选择</option>
                            <option value="1">江西</option>
                            <option value="2">重庆</option>
                            <option value="3">北京</option>
                            <option value="4">云南</option>
                        </select>
                        <select class="jj" name="city">
                            <option value="0" selected="selected">请选择</option>
                            <option value="1">成都</option>
                            <option value="2">宜宾</option>
                            <option value="3">南充</option>
                            <option value="4">绵阳</option>
                        </select>
                        <select class="jj" name="area">
                            <option value="0" selected="selected">请选择</option>
                            <option value="1">武侯区</option>
                            <option value="2">成华区</option>
                            <option value="3">锦江区</option>
                            <option value="4">青羊区</option>
                        </select>
                        （必填）
                    </td>
                </tr>
                <tr>
                    <td align="right">收货人姓名</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" />（必填）</td>
                    <td align="right">电子邮箱</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" />（必填）</td>
                </tr>
                <tr>
                    <td align="right">详细地址</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" />（必填）</td>
                    <td align="right">邮政编码</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" /></td>
                </tr>
                <tr>
                    <td align="right">手机</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" />（必填）</td>
                    <td align="right">标志建筑</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" /></td>
                </tr>
                <tr>
                    <td align="right">最佳送货时间</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt" /></td>
                </tr>
            </table>
            <p align="right">
                <a href="#">删除</a>&nbsp; &nbsp; <a href="#" class="add_b">确认添加</a>
            </p>
        </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
