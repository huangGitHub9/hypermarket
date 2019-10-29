<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					管理员的详细信息
				</div>
				<div class="panel-body">
					<form class="form-horizontal " role="form ">
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">用户名:</label>
							<div class="col-sm-6 ">
								<input class="form-control "  type="text "  placeholder="${sessionScope.session_admin.adminName} " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
							<div class="col-sm-6 ">
								<input class="form-control "  type="text " placeholder="${sessionScope.session_admin.adminPassword} " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">年&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
							<div class="col-sm-6 ">
								<input class="form-control "  type="text " placeholder="${sessionScope.session_admin.adminAge} " disabled="disabled">
							</div>
						</div>
						<div class="form-group ">
							<label  class="col-sm-2 control-label ">性&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
							<div class="col-sm-6 ">
								<input class="form-control " id="disabledInput " type="text "
									   placeholder="${sessionScope.session_admin.adminGender} " disabled="disabled">
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