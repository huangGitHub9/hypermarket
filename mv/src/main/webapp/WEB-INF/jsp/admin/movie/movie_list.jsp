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
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/movie_list.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/Mydate.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/movieDate.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/pageNavigation.js"></script>
	</head>

	<script>
        //定义全局变量
        var counts = "${requestScope.counts}";
        var url ="${pageContext.servletContext.contextPath}/admin/movieListAUI.action";
        var pCur = "${requestScope.pCur}";
        var pSize = "${requestScope.pSize}";
        //用户搜索
        function Search(){
            document.forms[0].action=url;
            document.forms[0].submit();
        }

	</script>

	<body>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">搜索栏</a>
				</div>
				<form class="navbar-form navbar-left" role="search" method="post">
					<div class="form-group">
						<button type="button" class="btn btn-default">视频名称:</button>
						<input type="text"  name="movieName" value="${requestScope.movieForm.movieName}" class="form-control " placeholder="Search">
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">导演名称:</button>
						<input type="text"  name="movieDirector" value="${requestScope.movieForm.movieDirector}"
							   class="form-control" placeholder="Search">
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">主演人员:</button>
						<input type="text" name="movieProtagonist" value="${requestScope.movieForm.movieProtagonist}"
							   class="form-control" placeholder="Search">
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">视频类型:</button>
						<select class="form-control" name="movieTypeId">
							<option name="movieTypeId" value="">——选视频类型——</option>
							<c:forEach items="${requestScope.movieTypes}" var="movieType">
							<option name="movieTypeId" value="${movieType.movieTypeId}"
									<c:forEach items="${requestScope.movieForm.movieTypes}" var="mTForm">
										<c:if test="${mTForm.movieTypeId eq movieType.movieTypeId }">
											selected="selected"
										</c:if>
									</c:forEach>
							>${movieType.movieTypeName}</option>
							</c:forEach>
						</select>
					</div>
					<!--电影的等级-->
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">视频等级:</button>
						<select class="form-control" name="movieGradeId">
							<option  value="">——选择视频等级——</option>
							<c:forEach items="${requestScope.movieGradeList}" var="mg">
								<option value="${mg.movieGradeId}"
										<c:if test="${mg.movieGradeId eq requestScope.movieForm.movieGradeId}">
											selected="selected"
										</c:if>
								>${mg.movieGradeName}</option>
							</c:forEach>
						</select>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">上映时间:</button>
						<input type="text"  name="movieTimeText" value="${requestScope.movieForm.movieTimeText}"
							   class="layui-input form-control" id="DateRange" placeholder="yyyy-MM-dd">
					</div>
					<input type="hidden" name="pCur" value="1" id="pCurId">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary"  onclick="Search()">搜索</button>
				</form>
			</div>
		</nav>
		<div class="panel panel-default" style="margin-top: -10px;">
			<div class="panel-heading">
				<h3 class="panel-title">
		            	视频列表
		        </h3>
			</div>
			<div class="panel-body">
				<div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1" style="margin: 0 auto;width: 880px;">
					<div class="layui-table-box">
						<div class="layui-table-header">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<thead>
									<tr>
										<th >
											<div class="layui-table-cell laytable-cell-1-id">
												<span>序号</span>
											</div>
										</th>
										<th>
											<div class="layui-table-cell laytable-cell-1-movie-name">
												<span>视频名称</span>
											</div>
										</th>
										<th >
											<div class="layui-table-cell laytable-cell-1-movie-leaderName">
												<span>导演名称</span>
											</div>
										</th>
										<th >
											<div class="layui-table-cell laytable-cell-1-palyTime">
												<span>上映时间</span>
											</div>
										</th>
										<th >
											<div class="layui-table-cell laytable-cell-1-member"><span>主演人员</span></div>
										</th>
										<th >
											<div class="layui-table-cell laytable-cell-1-handle"><span>操作</span></div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="layui-table-body layui-table-main">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<tbody>
								<c:forEach items="${requestScope.movies}" var="movie" varStatus="status">
									<tr>
										<td data-field="name">
											<div class="layui-table-cell laytable-cell-1-id">${status.index+1}</div>
										</td>
										<td >
											<div class="layui-table-cell laytable-cell-1-movie-name">${movie.movieName}</div>
										</td>
										<td >
											<div class="layui-table-cell laytable-cell-1-movie-leaderName">${movie.movieDirector}</div>
										</td>
										<td >
											<div class="layui-table-cell laytable-cell-1-palyTime">
												<fmt:formatDate
														value='${movie.movieTime}'
														type='date'
														dateStyle='medium'/>
											</div>
										</td>
										<td >
											<div class="layui-table-cell laytable-cell-1-member">${movie.movieProtagonist}</div>
										</td>
										<td data-field="handle" align="center" data-off="true">
											<div class="layui-table-cell laytable-cell-1-handle">
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-primary layui-btn-xs"
												   href="<c:url value='/admin/movieDetailAUI.action?movieId=${movie.movieId}'/>"
													target="main">查看</a>
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-xs"
												   href="<c:url value='/admin/movieEditAUI.action?movieId=${movie.movieId}'/>" target="main">编辑</a>
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-danger layui-btn-xs"
												   href="<c:url value='/admin/movieEditAUI.action?movieId=${movie.movieId}'/>"
													onclick="return confirm('你真的要删除吗？')"
												>删除</a>
											</div>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="fenye" style="width: 750px;"></div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>