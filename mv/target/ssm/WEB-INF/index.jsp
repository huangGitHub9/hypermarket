<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/index.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/pageNavigation.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/index.js"></script>
	</head>

	<script>
        //定义全局变量
        var counts = "${requestScope.counts}";
        var url ="${pageContext.servletContext.contextPath}/sys/indexUI.action";
        var pCur = "${requestScope.pCur}";
        var pSize = "${requestScope.pSize}";

        //获取你选中的电影类型个
		var selectMovieTypeId =  "${requestScope.movieForm.movieTypeId}";
        //电影首页搜索的时候要清除
        function Search(){
            document.forms[0].action=url;
            document.forms[0].submit();
        }
	</script>
	<body>
<!--导航栏开始-->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid" style="margin-left: 80px;" >
			    <div class="navbar-header">
			        <a class="navbar-brand" href="#">视频网站</a>
			         <a class="navbar-brand " href="#">首页</a> 
			    </div>

			    <div style="margin-left: 400px;" >
			        <form class="navbar-form navbar-left" role="search" method="post">
			            <div class="form-group">

			                <input type="text" name="movieName" value="${requestScope.movieForm.movieName}" class="form-control" placeholder="Search">
			            </div>
						<input type="hidden" id="movieTypeId" name="movieTypeId" value="${requestScope.movieForm.movieTypeId}">
						<input type="hidden" name="pCur" value="1" id="pCurId">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary"  onclick="Search()">搜索</button>
			        </form>
			    </div>
		      	<div>
			      <ul class="nav navbar-nav navbar-right">
					  <c:if test="${sessionScope.session_user.userMemEndTime != null }">
						  <li>
							<span style="display: inline-block;   margin-top: 15px;">
								<img src="<c:url value='/img/memVip.png'/>" width="20px" height="20px">
								<font color="#ffcf5d" style="font-size: 14px">VIP</font>
							</span>
						  </li>
					  </c:if>
				    	<li>
							<c:choose>
								<c:when test="${empty sessionScope.session_user }">
									<a href="<c:url value='/user/loginUI.action'/>" target="_blank">
										<img src="<c:url value='/img/user.png'/>" width="40px" height="40px"
											 class="img-circle" style="margin-top: -15px;"/>
										登录
									</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/user/userIndexUI.action'/>" target="_blank">
										<c:choose>
											<c:when test="${fn:contains(sessionScope.session_user.userHeadImgPath, 'https:')}">
												<img src="${sessionScope.session_user.userHeadImgPath}"
													 width="40px" height="40px" class="img-circle" style="margin-top: -15px;"/>
											</c:when>
											<c:otherwise>
												<img src="<c:url value='/${sessionScope.session_user.userHeadImgPath}'/>"
													 width="40px" height="40px" class="img-circle" style="margin-top: -15px;"/>
											</c:otherwise>
										</c:choose>
										个人信息
									</a>
								</c:otherwise>
							</c:choose>
						</li>
				      	<li>
						  <c:choose>
							  <c:when test="${ empty sessionScope.session_user}">
								  <a href="<c:url value='/user/registUI.action'/>" target="_blank">
									  <button type="button" class="btn btn-default btn-sm" style="background-color: #F9F9F9;border: none; margin-top: -3px;">
										  <span class="glyphicon glyphicon-log-in"></span>&nbsp;注册
									  </button>
								  </a>
							  </c:when>
							  <c:otherwise>
								  <a href="<c:url value='/user/quit.action'/>" >
									  <button type="button" class="btn btn-default btn-sm" style="background-color: #F9F9F9;border: none; margin-top: -3px;">
										  <span class="glyphicon glyphicon-off"></span>&nbsp;退出
									  </button>
								  </a>
							  </c:otherwise>
						  </c:choose>
				      	</li>
				    </ul>
				</div> 
			</div>
		</nav>
<!--导航栏结束-->
	<div class="panel panel-default" style="width:1180px ; margin: auto;">
		<div class="panel-heading">
        <h3 class="panel-title">按电影的类型分类</h3>
    	</div>
	    <div class="panel-body">
<!--类型开始-->
	        <div id="fenglei">
	        	<span><a href="#" disabled="true">类型</a></span>
				<span><a href="#" name="SearchAll">全部</a></span>
				<c:forEach items="${requestScope.movieTypes}" var="movieType">
	        		<span>
						<a href="#" name="${movieType.movieTypeId}" >${movieType.movieTypeName}</a>
					</span>
				</c:forEach>
			</div>
<!--类型结束-->
	    </div>
	</div>
<!--电影列表展示开始-->
	<div class="panel panel-default" style="width:1180px; margin: 0 auto;">
	    <div class="panel-body" >
			<div id="show_movie_list" >
				<ul id="movie_list" style="margin:0 auto">
					<c:forEach items="${requestScope.movies}" var="movie">
					<li class="movie_li" >
						<a href="<c:url value='/movie/movieDetailUI.action?movieId=${movie.movieId}'/>" title="${movie.movieName}"
						   target="_blank">
							<img src="<c:url value='/${movie.movieImgPath}'/>" alt=""/>
						</a>
						<!--显示电影的几倍 vip还是付费 还是独播-->
						<i class="mark-v"><img src="<c:url value='/${movie.movieGrade.movieGradeImgPath}'/>" alt=""></i>
						<p class="movie_li_p_name">
							<a href="<c:url value='/movie/movieDetailUI.action?movieId=${movie.movieId}'/>"
							   title="${movie.movieName}" target="_blank">${movie.movieName}</a>
						</p>
					</li>
					</c:forEach>
				</ul>
			</div>
	    </div>
	</div>
<!--电影列表展示结束-->
		<div id="page_foot">
			<div id="fenye" ></div>
		</div>

<style>
/*分页*/
.layui-laypage a, .layui-laypage span {
display: inline-block; vertical-align: middle;padding: 0 25px;height: 38px;line-height: 38px;
margin: 0 -1px 5px 0;background-color: #fff;color: #333;font-size: 14px;font-weight: bold;}
</style>
	</body>
</html>
