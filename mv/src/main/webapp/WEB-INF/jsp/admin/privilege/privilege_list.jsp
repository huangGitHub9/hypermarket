<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		<meta charset="utf-8" />
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/privilege.css" />

	</head>

	<body>
		<div class="panel panel-default" style="margin-top: -10px;">
			<div class="panel-heading">
				<h3 class="panel-title">
		            	权限列表
		        </h3>
			</div>
			<div class="panel-body">
				<div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1" style="margin: 0 auto;width: 750px;">
					<div class="layui-table-box">
						<div class="layui-table-header">
							<table cellspacing="0" cellpadding="0" border="0" class="layui-table">
								<thead>
									<tr>
										<th>
											<div class="layui-table-cell laytable-cell-1-id">
												<span>序号</span>
											</div>
										</th>
										<th>
											<div class="layui-table-cell laytable-cell-1-privilegeName">
												<span>权限名称</span>
											</div>
										</th>
										<th>
											<div class="layui-table-cell laytable-cell-1-privilegeNameAs">
												<span>权限别名</span>
											</div>
										</th>
										<th>
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
									<c:forEach items="${requestScope.privileges}" var="privilege" varStatus="status">
									<tr>
										<td data-field="name">
											<div class="layui-table-cell laytable-cell-1-id">${status.index+1}</div>
										</td>
										<td data-field="gender">
											<div class="layui-table-cell laytable-cell-1-privilegeName">${privilege.privilegeName}</div>
										</td>
										<td data-field="gender">
											<div class="layui-table-cell laytable-cell-1-privilegeNameAs">${privilege.privilegeNameAs}</div>
										</td>
										<td data-field="handle" align="center" data-off="true">
											<div class="layui-table-cell laytable-cell-1-handle">
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-primary layui-btn-xs"
												   href="<c:url value="/admin/privilegeDetailAUI.action?privilegeId=${privilege.privilegeId}"/>"
													>查看</a>
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-xs"
												   href="<c:url value="/admin/privilegeEditAUI.action?privilegeId=${privilege.privilegeId}"/>"
													>编辑</a>
												&nbsp;&nbsp;
												<a class="layui-btn layui-btn-danger layui-btn-xs"
												   href="<c:url value="/admin/privilegeEditAUI.action?privilegeId=${privilege.privilegeId}"/>"
													onclick="return confirm('你真的要删除？')">删除</a>
											</div>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>