<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/echarts.js" ></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/shapeDataLoad.js" ></script>
		<script>
			var movieTypeClickCountsUrl = "${pageContext.servletContext.contextPath}/shape/movieTypeClickCounts.action";
		</script>
	</head>

	<body>
		<div class="panel panel-default" style="margin-top: -10px;">
			<div class="panel-heading">
				<h3 class="panel-title">
		            	电影类型点击量展示
		        </h3>
			</div>
			<div class="panel-body">
				<div id="main" style="width: 600px;height:400px;margin: 0 auto;">
				</div>
			</div>
		</div>
	</body>

</html>