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
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/uploadAjax.js"></script>
		<style type="text/css">
			#pat{width:400px;height:25px; border:2px solid blue;}
			#son {width:0; height:100%; background-color:green; text-align:center; color:yellow;line-height: 25px;}
		</style>
		<script>
				var proName = "${pageContext.request.contextPath}";
		</script>
	</head>
	<body>
		<div style="width:600px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					添加视频
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="myForm" action="<c:url value="/admin/movieAddA.action"/>"
						  method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label  class="col-sm-2 control-label">视频名称:</label>
							<div class="col-sm-8">
								<input type="text"  name="movieName" class="form-control"  placeholder="视频名称">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">导演名称:</label>
							<div class="col-sm-8">
								<input type="text" name="movieDirector" class="form-control"  placeholder="导演名称">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">上映时间:</label>
							<div class="col-sm-8">
								 <input type="text" name="movieTime" class="layui-input form-control" id="birthday" placeholder="yyyy-MM-dd">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">视频类型:</label>
							<div class="checkbox"  style="width: 373px; margin: 0 auto;">
								<c:forEach items="${requestScope.movieTypes}" var="movieType">
									<label><input type="checkbox" name="movieTypeName"
										value="${movieType.movieTypeId}"
									>${movieType.movieTypeName}</label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">视频等级</label>
							<div style="float: left;margin-left: 15px;">
								<select class="form-control" name="movieGradeId" >
									<option value="">——请选择电影等级——</option>
									<!--电影级别默认选择 电影几倍Id为1即 普通电影-->
									<c:forEach items="${requestScope.movieGradeList}" var="mg">
										<option value="${mg.movieGradeId}"
										<c:if test="${mg.movieGradeId eq '1'}">selected="selected"</c:if>
										>${mg.movieGradeName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">视频封面:</label>
							<div class="col-sm-10">
								<input type="file" name="movieImgFile"/>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">上传视频:</label>
							<div class="col-sm-10" >
								<input type="file" name="movieFile" id="movieFile" onchange="movieUpload()" />
							</div>
						</div>
						<div class="form-group">
							<div style="float: left;margin-left:26px;width: 50px; font-weight: bold;">进度条:</div>
							<div id="pat" style="margin-left: 116px;">
								<div id="son" align="center">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">主演人员:</label>
							<div class="layui-input-block">
								<textarea placeholder="例入：张三 王五 赵四"
									name="movieProtagonist"	  class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">内容简介:</label>
							<div class="layui-input-block">
								<textarea placeholder="内容简介" name="movieIntro" class="layui-textarea"></textarea>
							</div>
						</div>
						<input type="hidden" name="moviePath" id="moviePath">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn btn-primary">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  class="btn btn-info" onclick="javascript:history.go(-1)" >返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>