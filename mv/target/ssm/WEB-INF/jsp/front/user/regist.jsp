<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>用户注册</title>
        <%@include file="/common/baseHeader.jsp"%>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/Mydate.js"></script>

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
					用户注册
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" action="<c:url value='/user/regist.action'/>" method="post"
						enctype="multipart/form-data">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">用户名:</label>
							<div class="col-sm-10">
								<input type="text"  name="userName" class="form-control"  placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
							<div class="col-sm-10">
								<input type="password" name="userPassword" class="form-control"  placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">年&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
							<div class="col-sm-10">
								<input type="text" name="userAge" class="form-control"  placeholder="请输入年龄">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">生&nbsp;&nbsp;&nbsp;&nbsp;日:</label>
							<div class="col-sm-10">
								 <input type="text"  name="userBirthday" class="layui-input form-control" id="birthday" placeholder="yyyy-MM-dd">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
							<div class="radio">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label>
							        <input type="radio" name="userGender"  value="男" checked>男
							    </label>
								<label>
							        <input type="radio" name="userGender"  value="女" checked>女
							    </label>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">头&nbsp;&nbsp;&nbsp;&nbsp;像:</label>
							<div class="col-sm-10" >
								<input type="file" name="headImgFile" />
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">验证码:</label>
							<img id="captcha" src="<c:url value='/captcha/code.action'/>"
								 onclick="changeCaptcha()" width="100px" height="35px" title="点击更图片"/>
							<div class="col-sm-10" style="width: 150px;">
								<input type="text" name="captcha" class="form-control" id="firstname" placeholder="请输入验证码">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success">确定</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn btn-primary">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>