<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<div class="right" style="background-image:url('${pageContext.request.contextPath }/statics/images/bg1.jpg');">
    <div id="header" class="location">
        <strong>你现在所在的位置是:</strong>
        <span>个人中心界面</span>
    </div>
    <div style="margin-left: 60px;margin-top: 20px;">
        <h1 style="font-size: 24px;">欢迎来到${user.userName }的空间</h1>
        <p style="font-size: 18px">姓名：<span style="color: blue;">${user.userName }</span></p>
        <p style="font-size: 18px">年龄：<span style="color: blue;">${user.age }</span></p>
        <p style="font-size: 18px">电话：<span style="color: blue;">${user.phone }</span></p>
        <p style="font-size: 18px">身份：
            <span style="color: blue;">
			<c:if test="${user.userRole==1}">系统管理员</c:if>
			<c:if test="${user.userRole==2}">经理</c:if>
			<c:if test="${user.userRole==3}">普通员工</c:if>
			</span>
        </p>
        <p style="font-size: 18px">头像： </p>
        <img alt="加载中..." class="photo"
             src="${pageContext.request.contextPath }/statics/upload/${user.userPhoto }"/>
        <form action="${pageContext.request.contextPath }/sys/userPhotoModify?userid=${user.id }"
              enctype="multipart/form-data" method="post">
            <a class="file" style="width: 75px;">
                选择上传文件<input type="file" name="photo" id="photo">
            </a>
            <br>
            <input type="submit" value="保 存" style="font-size: 14px;width: 70px;text-align: center; margin-left: 60px;">
        </form>
    </div>
    <!-- <footer style="width: 100%;line-height: 40px;text-align: center;color: white;">版权</footer> -->
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>