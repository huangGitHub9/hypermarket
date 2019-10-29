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
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/user_list.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/pageNavigation.js"></script>
	</head>

	<script  >
        //定义全局变量
        var counts = "${requestScope.counts}";
        var url ="${pageContext.servletContext.contextPath}/admin/userListAUI.action";
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
						<button type="button" class="btn btn-default">用户名:</button>
						<input type="text" name="userName" value="${requestScope.userForm.userName}" class="form-control" placeholder="Search">
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">性别:</button>
						<input type="text"  name="userGender" value="${requestScope.userForm.userGender}" class="form-control" placeholder="Search">
					</div>
					<input type="hidden" name="pCur" value="1" id="pCurId">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<button type="button" class="btn btn-default">角色类型:</button>
						<select class="form-control" name="roleId" >
							<option value="">——选角色类型——</option>
							<c:forEach items="${requestScope.roles}" var="r">
								<option value="${r.roleId}"
									<c:if test="${r.roleId eq requestScope.userForm.roleId}">selected="selected"</c:if>
								>${r.roleName}</option>
							</c:forEach>
						</select>
					</div>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" onclick="Search()">搜索</button>
				</form>
			</div>
		</nav>
		<div class="panel panel-default" style="margin-top: -10px;">
			<div class="panel-heading">
				<h3 class="panel-title">
		            	用户列表
		        </h3>
			</div>
			<div class="panel-body">
				<div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1" style="margin: 0 auto;width: 750px;">
					<div class="layui-table-box">
						<div class="layui-table-header">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<thead>
									<tr>
										<th data-field="name">
											<div class="layui-table-cell laytable-cell-1-number">
												<span>序号</span>
											</div>
										</th>
										<th data-field="name">
											<div class="layui-table-cell laytable-cell-1-name">
												<span>姓名</span>
											</div>
										</th>
										<th data-field="gender">
											<div class="layui-table-cell laytable-cell-1-gender">
												<span>性别</span>
											</div>
										</th>
										<th data-field="password">
											<div class="layui-table-cell laytable-cell-1-password"><span>密码</span></div>
										</th>
										<th data-field="age">
											<div class="layui-table-cell laytable-cell-1-age"><span>年龄</span></div>
										</th>
										<th data-field="birthday">
											<div class="layui-table-cell laytable-cell-1-birthday"><span>生日</span></div>
										</th>
										<th data-field="handle">
											<div class="layui-table-cell laytable-cell-1-handle"><span>操作</span></div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="layui-table-body layui-table-main">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<tbody>
								<c:forEach items="${requestScope.users}" var="user" varStatus="status">
									<tr>
										<td >
											<div class="layui-table-cell laytable-cell-1-number">${status.index+1}</div>
										</td>
										<td data-field="name">
											<div class="layui-table-cell laytable-cell-1-name">${user.userName}</div>
										</td>
										<td data-field="gender">
											<div class="layui-table-cell laytable-cell-1-gender">${user.userGender}</div>
										</td>
										<td data-field="password">
											<div class="layui-table-cell laytable-cell-1-password">${user.userPassword}</div>
										</td>
										<td data-field="age">
											<div class="layui-table-cell laytable-cell-1-age">${user.userAge}</div>
										</td>
										<td data-field="birthday">
											<div class="layui-table-cell laytable-cell-1-birthday"><fmt:formatDate
													value='${user.userBirthday}'
													type="date"
													dateStyle="medium"
											/> </div>
										</td>
										<td data-field="handle" align="center" data-off="true">

											<div class="layui-table-cell laytable-cell-1-3">
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-primary layui-btn-xs"
												   href="<c:url value='/admin/userDetailAUI.action?userId=${user.userId}'/>">查看</a>
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-xs"
												   href="<c:url value='/admin/userEditAUI.action?userId=${user.userId}'/>">编辑</a>
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-danger layui-btn-xs"
												   href="<c:url value='/admin/userDeleteA.action?userId=${user.userId}'/>"
												   onclick="return confirm('你真的要删除吗？')"
												   >删除</a>
											</div>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="fenye" style="width: 750px;margin:auto;"></div>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
