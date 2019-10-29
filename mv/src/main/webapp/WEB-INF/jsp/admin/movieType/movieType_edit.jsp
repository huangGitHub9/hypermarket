<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
	</head>

	<body>
		<div style="width:600px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					修改视频分类
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" action="<c:url value="/admin/movieTypeEditA.action"/>" method="post">
						<input type="hidden" name="movieTypeId" value="${requestScope.movieType.movieTypeId}">
						<div class="form-group">
							<label  class="col-sm-2 control-label">分类名:</label>
							<div class="col-sm-10">
								<input type="text" name="movieTypeName" class="form-control"
									   placeholder="${requestScope.movieType.movieTypeName}">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">已有分类:</label>
							<div class="layui-input-block">
								<textarea disabled="disabled" placeholder="${requestScope.movieTypeNames}"
									 class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success">确定</button> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn btn-primary">重置</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  class="btn btn-info" onclick="javascript:history.go(-1)" >返回</button>
							</div>
							
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>