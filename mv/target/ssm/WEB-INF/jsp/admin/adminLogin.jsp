<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<script>
			//切换验证码
			function changeCaptcha(){
			    var times = new Date().getTime();
				$("#captcha").attr({src:"<c:url value='/captcha/code.action?time="+times+"'/>"});
			}
		</script>
	</head>
	<body>
		<div style="width:500px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					管理员登录
				</div>

				<div class="panel-body">
					<form class="form-horizontal" role="form"  action="<c:url value='/admin/adminLogin.action'/>" method="post">
						<div class="form-group">
							<label  class="col-sm-2 control-label isEmpty">管理员名称:</label>
							<div class="col-sm-10">
								<input type="text"  name="adminName" class="form-control"  placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
							<div class="col-sm-10">
								<input type="password"  name= "adminPassword" class="form-control"  placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">

							<label  class="col-sm-2 control-label">验证码:</label>

							<img id="captcha" src="<c:url value='/captcha/code.action'/>"
								 onclick="changeCaptcha()" width="100px" height="35px" title="点击更图片"/>
							<div class="col-sm-10" style="width: 150px;">
								<input type="text" name="captcha"  class="form-control"  placeholder="请输入验证码">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success">登录</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn btn-primary">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>