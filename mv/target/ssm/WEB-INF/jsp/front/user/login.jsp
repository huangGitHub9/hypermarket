<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<script type="application/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/fastLogin.js" ></script>
		<script>
			//切换验证码
			function changeCaptcha(){
                var times = new Date().getTime();
                var url = "${pageContext.servletContext.contextPath}/captcha/code.action?time="+times;
				$("#captcha").attr({src:url});
			}
		</script>
	</head>
	<body>
		<div style="width:500px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					用户登录
				</div>

				<div class="panel-body">

					<form class="form-horizontal" role="form"  action="<c:url value='/user/login.action'/>" method="post">
						<div class="form-group">
							<label  class="col-sm-2 control-label isEmpty">用户名:</label>
							<div class="col-sm-10">
								<input type="text"  name="userName" class="form-control"  placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
							<div class="col-sm-10">
								<input type="password"  name= "userPassword" class="form-control"  placeholder="请输入密码">
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
					<div>
						<span>快速登录</span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span>
							<a href="#" id="qqLoginId" >
								<img src="<c:url value='/img/qq.png'/>" width="30px" height="30px" class="img-circle">
							</a>
							&nbsp;&nbsp;
							<a href="#" id="gitLoginId">
								<img src="<c:url value='/img/github.png'/>" width="26px" height="26px" class="img-circle">
							</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>