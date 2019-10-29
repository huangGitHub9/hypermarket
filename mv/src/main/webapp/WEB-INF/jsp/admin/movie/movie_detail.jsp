<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.min.js" ></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/movieDetial.css" />
	</head>
	<body>
		<div class="panel panel-default" style="width: 1180px; margin: auto;">
			<div class="panel-heading">
				<h3 class="panel-title">
	            	视频详情
	        </h3>
			</div>
			<div class="panel-body">
				<div>
					<div class="movieDetial_float">

						<img src="<c:url value='/${requestScope.movie.movieImgPath}'/>" width="258px" height="360" alt="" style="display: block;">
					</div>
					<div class="movieDetial_float movieDetial_float2" style="width:800px;height: 500px;border: solid white 1px;">
						<h1>${requestScope.movie.movieName}</h1>
						<dl>
							<dt>上&nbsp; 映：</dt>
							<dd id=""><fmt:formatDate
									value='${movie.movieTime}'
									type='date'
									dateStyle='medium'/></dd>
							<dt>地&nbsp; 区：</dt>
							<dd>内&nbsp;地</dd>
							<dt>类 &nbsp;型：</dt>
							<dd>
								<c:forEach items="${requestScope.movie.movieTypes}" var="movieType">
									${movieType.movieTypeName}&nbsp;
								</c:forEach>
							</dd>
							<dt>导&nbsp; 演：</dt>
							<dd>${requestScope.movie.movieDirector}</dd>
							<dt>主&nbsp; 演：</dt>
							<dd>${requestScope.movie.movieProtagonist}</dd>
							<dt>简&nbsp; 介：</dt>
							<dd>
								${requestScope.movie.movieIntro}
							</dd>
							<dt>等&nbsp; 级：</dt>
							<dd>
								${requestScope.movie.movieGrade.movieGradeName}
							</dd>
						</dl>

						<a href="<c:url value="/movie/moviePlayUI.action"/>" target="_blank">
							<div id="look_button" style="display: inline;">
								<button type="button" class="btn btn-success">播放</button>
							</div>
						</a>
						<button type="button"  class="btn btn-info" onclick="javascript:history.go(-1)" >返回</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>