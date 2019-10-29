<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8" />
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/movieType_list.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/pageNavigation.js"></script>
	</head>

	<script>
        //定义全局变量
        var counts = "${requestScope.counts}";
        var url ="${pageContext.servletContext.contextPath}/admin/movieTypeListAUI.action";
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
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<button type="button" class="btn btn-default">分类名:</button>
						<input type="text" name="movieTypeName" value="${requestScope.movieTypeForm.movieTypeName}" class="form-control" placeholder="Search">
					</div>
					<input type="hidden" name="pCur" value="1" id="pCurId">
					&nbsp;&nbsp;
					<button type="button" onclick="Search()" class="btn btn-primary">搜索</button>
				</form>
			</div>
		</nav>
		<div class="panel panel-default" style="margin-top: -10px;">
			<div class="panel-heading">
				<h3 class="panel-title">
		            	视频分类列表
		        </h3>
			</div>
			<div class="panel-body">
				<div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1" style="margin: 0 auto;width: 750px;">
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
										<th >
											<div class="layui-table-cell laytable-cell-1-typeName">
												<span>分类名称</span>
											</div>
										</th>
										<th >
											<div class="layui-table-cell laytable-cell-1-handle">
												<span>操作</span>
											</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="layui-table-body layui-table-main">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<tbody>
								<c:forEach items="${requestScope.movieTypes}" var="movieType" varStatus="status">
									<tr>
										<td data-field="name">
											<div class="layui-table-cell laytable-cell-1-id">${status.index+1}</div>
										</td>
										<td data-field="gender">
											<div class="layui-table-cell laytable-cell-1-typeName">${movieType.movieTypeName}</div>
										</td>
										<td data-field="handle" align="center" data-off="true">

											<div class="layui-table-cell laytable-cell-1-handle" >
												&nbsp;&nbsp;&nbsp;&nbsp;
												<a class="layui-btn layui-btn-primary layui-btn-xs"
												   href=" <c:url value='/admin/movieTypeDetailAUI.action?movieTypeId=${movieType.movieTypeId}'/>">查看</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<a class="layui-btn layui-btn-xs"
												   href=" <c:url value='/admin/movieTypeEditAUI.action?movieTypeId=${movieType.movieTypeId}'/>">编辑</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<a class="layui-btn layui-btn-danger layui-btn-xs"
												   href="<c:url value='/admin/movieTypeDeleteA.action?movieTypeId=${movieType.movieTypeId}'/>"
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