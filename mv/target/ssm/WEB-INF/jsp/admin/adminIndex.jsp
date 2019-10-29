<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>后台管理系统</title>
		<%--<%@include file="/common/baseHeader.jsp"%>--%>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/layui/css/layui.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.2.1.min.js" ></script>
		<script src="${pageContext.servletContext.contextPath}/layui/layui.all.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/admin_index.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/layui/layui.js" ></script>
	</head>
	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo">视频管理系统后台</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item">
						<a href="">首页</a>
					</li>
				</ul>
				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;">
							<img src="${pageContext.servletContext.contextPath}/upload/user/default2.jpg" width="50px" height="50px" class="img-circle">
							${sessionScope.session_admin.adminName}
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<c:url value='/admin/adminDescUI.action'/>" target="main">基本资料</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="<c:url value='/admin/quit.action'/>">注销</a>
					</li>
				</ul>
			</div>

			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
					<ul class="layui-nav layui-nav-tree" lay-filter="glmk">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;" >用户管理</a>
							<dl class="layui-nav-child">
								<!--添加用户的事情就留给前台来做-->
								<%--<dd>
									<a href="<c:url value="/admin/userAddAUI.action"/>" target="main">添加用户</a>
								</dd>--%>
								<!--用户列表包含：用户的详细信息、和删除用户，编辑用户，分页查询用户，-->
								<dd>
									<a href="<c:url value="/admin/userListAUI.action"/>" target="main">用户列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">视频管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value="/admin/movieAddAUI.action"/>" target="main">添加视频</a>
								</dd>
								<!--视频列表包含 上传-->
								<dd>
									<a href="<c:url value="/admin/movieListAUI.action"/>" target="main">视频列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">视频分类管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value="/admin/movieTypeAddAUI.action"/>" target="main">添加分类</a>
								</dd>
								<dd>
									<a href="<c:url value="/admin/movieTypeListAUI.action"/>" target="main">视频分类列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">权限管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value="/admin/privilegeAddAUI.action"/>" target="main">添加权限</a>
								</dd>
								<dd>
									<a href="<c:url value="/admin/privilegeListAUI.action"/>" target="main">权限列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">角色管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value="/admin/roleAddAUI.action"/>" target="main">添加角色</a>
								</dd>
								<dd>
									<a href="<c:url value="/admin/roleListAUI.action"/>" target="main">角色列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">会员套餐管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value="/admin/memPackageAddAUI.action"/>" target="main">添加套餐</a>
								</dd>
								<dd>
									<a href="<c:url value="/admin/memPackageListAUI.action"/>" target="main">套餐列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">电影等级管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value="/admin/movieGradeAddAUI.action"/>" target="main">添加电影等级</a>
								</dd>
								<dd>
									<a href="<c:url value="/admin/movieGradeListAUI.action"/>" target="main">电影等级列表</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" lay-filter="glmk">
							<a href="javascript:;">其他</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="<c:url value='/admin/mTClickCountsUI.action'/>" target="main">视频分类点击量</a>
								</dd>
								<dd>
									<a href="<c:url value="/admin/accountInfoUI.action"/>" target="main">账户信息</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-body">
				<!-- 内容主体区域 -->
				<div style="padding: 15px;" >
					<iframe name="main" frameborder="0" style="width: 100%;height: 1000px;"
							src="<c:url value="/admin/welcomeAUI.action"/>">
					</iframe>
					<!--<h1 style="text-align: center; position: relative; top: 50%; ">
						欢迎你来到视频管理后台系统
					</h1>-->
				</div>
			</div>
			<!-- 底部固定区域 -->
			<!--<div class="layui-footer">
				
			</div>-->
		</div>
	</body>
</html>