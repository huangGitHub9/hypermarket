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
					修改会员套餐
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" action="<c:url value='/admin/memPackageEditA.action'/>"
						  method="post">
						<input type="hidden" name="memPackageId" value="${requestScope.memPackage.memPackageId}"/>
						<div class="form-group">
							<label  class="col-sm-2 control-label">套餐月份:</label>
							<div style="float: left;margin-left: 15px;">
								<select class="form-control" name="memPackageMonth" >
									<option value="">——请选择套餐月份——</option>
									<c:forEach items="${requestScope.monthList}" var="month">
										<option value="${month}"
										<c:if test="${requestScope.memPackage.memPackageMonth eq month }">selected="selected"</c:if>
										>${month}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">套餐价格:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="memPackagePrice"
								  	value="${requestScope.memPackage.memPackagePrice}"	 >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">套餐折扣:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"  placeholder="套餐折扣"
									   name="memPackageDiscount" value="${requestScope.memPackage.memPackageDiscount}">
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