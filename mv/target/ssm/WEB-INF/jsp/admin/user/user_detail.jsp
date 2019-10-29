<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/Mydate.js"></script>
	</head>

	<body>
		<div style="width:500px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<c:choose>
						<c:when test="${fn:contains(sessionScope.session_user.userHeadImgPath, 'https:')}">
							<img src="${requestScope.user.userHeadImgPath}"
								 width="50px" height="50px" class="img-circle">
						</c:when>
						<c:otherwise>
							<img src="<c:url value='/${requestScope.user.userHeadImgPath}'/>"
								 width="50px" height="50px" class="img-circle">
						</c:otherwise>
					</c:choose>
					头像
				</div>
				<div class="panel-body">
					<form class="form-horizontal " role="form ">
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">用户名:</label>
							<div class="col-sm-5 ">
								<input class="form-control "  type="text " placeholder="${requestScope.user.userName} " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
							<div class="col-sm-5 ">
								<input class="form-control "  type="text " placeholder="${requestScope.user.userPassword} " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">年&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
							<div class="col-sm-5 ">
								<input class="form-control "  type="text " placeholder="${requestScope.user.userAge} " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">生&nbsp;&nbsp;&nbsp;&nbsp;日:</label>
							<div class="col-sm-5 ">
								<input class="form-control "  type="text " placeholder="<fmt:formatDate
											value='${requestScope.user.userBirthday}'
											type="date"
											dateStyle="medium"
									/> " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">性&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
							<div class="col-sm-5 ">
								<input class="form-control "  type="text " placeholder="${requestScope.user.userGender} " disabled="disabled">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;色:</label>
							<div class="checkbox"  style="width: 373px; margin: 0 auto;">
								&nbsp;&nbsp;
								<c:forEach items="${requestScope.roleList}" var="role">
									<label>
										<input type="checkbox" name="roleIds" value="${role.roleId}"
										<c:forEach items="${requestScope.user.roleList}" var="r">
										<c:if test="${r.roleId eq role.roleId}">
											   checked="checked"
										</c:if>
										</c:forEach>
										>${role.roleName}
									</label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">套&nbsp;&nbsp;&nbsp;&nbsp;餐:</label>
							<div class="col-sm-5 ">
								<input class="form-control "  type="text " placeholder="${requestScope.user.memPackage.memPackageDetail} " disabled="disabled">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  class="btn btn-info" onclick="javascript:history.go(-1)" >返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>