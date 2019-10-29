<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>

	</head>

	<body>
		<div style="width:600px ; margin: auto;">
			<div class="panel panel-default">
				<div class="panel-heading">
					会员套餐详情
				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label  class="col-sm-2 control-label">套餐月数:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${requestScope.memPackage.memPackageMonth}"
									disabled="disabled">
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">套餐价格:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${requestScope.memPackage.memPackagePrice}"
									disabled="disabled" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">套餐折扣:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"  placeholder="套餐折扣"
									    disabled="disabled"  value="${requestScope.memPackage.memPackageDiscount}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">真实价格:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"  placeholder="真实价格"
									   disabled="disabled" value="${requestScope.memPackage.memPackageResPrice}">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  class="btn btn-info" onclick="javascript:history.go(-1)" >返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>