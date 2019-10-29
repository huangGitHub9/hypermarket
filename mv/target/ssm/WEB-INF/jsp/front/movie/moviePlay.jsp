<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>视频播放</title>
		<%@include file="/common/baseHeader.jsp"%>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/moviePlay.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/moviePlay2.js" ></script>
	</head>
	<script>
		//收集视频的 请求的路径
        var urlCollectPath = "${pageContext.servletContext.contextPath}/user/collectMovie.action";
        //添加弹幕的请求的路径
        var urlAddBarragePath = "${pageContext.servletContext.contextPath}/barrage/addBarrage.action";
        //查询该电影的弹幕的路径你
        var urlFindBarragePath = "${pageContext.servletContext.contextPath}/barrage/findBByMIdLimit.action";
        var movieId = "${requestScope.movie.movieId}";
		var userMemEndTime = "${sessionScope.session_user.userMemEndTime}";
        //判读该用户是否能看该视频
        var vipMem ="YES";
	</script>


	<body onkeydown="enterSendMsg();">
			<div class="panel panel-default" style="width:800px;margin:0 auto;">
				<div class="panel-body">
					<div id="player-area">
						<div id="player-viedo-wrap">
							<div id="player-viedo-danmuku">
							</div>
							<div id="player-video" >

								<video id="media" autoplay="autoplay"  src="<c:url value='/${requestScope.movie.moviePath}'/>"
									   controls="controls"  >
									该浏览器不支持
								</video>
							</div>
						</div>
						<%--说明是vip或者是付费电影--%>
						<c:if test="${requestScope.movie.movieGrade.movieGradeName ne '普通'}">
							<!--再看看它是不是会员-->
							<c:if test="${sessionScope.session_user ==null || sessionScope.session_user.userMemEndTime == null}">
								<div class="alert_content">
										<%--<span class="alert_content" >--%>
									可试看2分钟，
									<a href="#"style="color:#FF920B;text-decoration: none;"
									   onclick="payVip()">开通vip</a>
									&nbsp;免费看
										<script>vipMem = "NOT"</script>
								</div>
							</c:if>
						</c:if>
						<div id="player-viedo-sendbar">
							<div class="panel panel-default">
								<div class="panel-body" style="width: 800px;">
									 <form class="form-inline" role="form">
										<div class="form-group">
											<label class="sr-only" for="barrageContent">名称</label>
											<c:choose>
												<c:when test="${ empty sessionScope.session_user}">
													<input type="text" class="form-control" id="name"
														   placeholder="请先登录或者注册" disabled="disabled" style="width: 400px;">
												</c:when>
												<c:otherwise>
													<input type="text" class="form-control" id="barrageContent"
														   placeholder="请输入弹幕信息" name="barrageContent" style="width: 400px;">
												</c:otherwise>
											</c:choose>
										</div>
										 <c:choose>
											 <c:when test="${ empty sessionScope.session_user}">
												 <button id="sendDanmu" type="button" class="btn btn-default" disabled="disabled">发送弹幕</button>
												 <button id="collect" type="button" class="btn btn-default" disabled="disabled">收藏</button>
												 <button id="colseAndStartDanmu" type="button" disabled="disabled" class="btn btn-default" >关闭弹幕</button>
											 </c:when>
											 <c:otherwise>
												 <button id="sendDanmu" type="button" class="btn btn-default"
														 onclick="addBarrage('${requestScope.movie.movieId}','${sessionScope.session_user.userMemEndTime}')" >发送弹幕</button>
												 <button id="collect" type="button" class="btn btn-default"
														 onclick="collectionMovie('${requestScope.movie.movieId}')">
													 <c:choose>
														 <c:when test="${ empty requestScope.exist}">
															 <sapn id="collectId">收藏</sapn>
														 </c:when>
														 <c:otherwise>
															 <sapn id="collectId">取消收藏</sapn>
														 </c:otherwise>
													 </c:choose>
												 </button>
												 <button id="colseAndStartDanmu" type="button" class="btn btn-default" >关闭弹幕</button>
											 </c:otherwise>
										 </c:choose>
									 </form>
								</div>
							</div>
						</div>
					</div>
					<!--模态框弹出的内容-->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">VIP的充值</h4>
								</div>
								<div class="modal-body">
									<form class="form-horizontal " role="form " action="<c:url value="/alipay/setRequestParam.action"/>" method="post">
										<div class="form-group">
											<label class="col-sm-2 control-label">VIP套餐:</label>
											<div style="float: left;margin-left: 15px;">
												<select class="form-control" name="memPackageId">
													<option value="">——请选择VIP套餐——</option>
													<c:forEach items="${requestScope.memPackages}" var="mp">
														<option value="${mp.memPackageId}">
																${mp.memPackageMonth}月&nbsp;&nbsp;${mp.memPackageResPrice}元
														</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group ">
											<div class="col-sm-offset-2 col-sm-10 ">
												<button type="submit " class="btn btn-success ">确定</button>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
		    </div>
		</div>
	</body>
</html>
