<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
	</head>

	<body>
		<div style="width:600px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					添加角色
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" action="<c:url value='/admin/roleEditA.action'/>">
						<input type="hidden" name="roleId" value="${role.roleId}">
						<div class="form-group">
							<label  class="col-sm-2 control-label">角色名称:</label>
							<div class="col-sm-10">
								<input type="text" name="roleName" class="form-control" value="${role.roleName}">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">权限选项:</label>
							<div class="checkbox" name="leixng" style="width: 373px; margin: 0 auto;">
								<c:forEach items="${requestScope.privileges}" var="p" >
									<label>
										<input type="checkbox" name="privilegeIds" value="${p.privilegeId}"
										<c:forEach  items="${requestScope.role.privilegeList}" var="rp">
										<c:if test="${p.privilegeId eq rp.privilegeId}">
											   checked="checked"
										</c:if>
										</c:forEach>
										>${p.privilegeName}
									</label>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">已有角色:</label>
							<div class="layui-input-block">
								<textarea disabled="disabled" class="layui-textarea"><c:forEach items="${requestScope.roles}" var="r">${r.roleName},&nbsp;</c:forEach>
								</textarea>
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