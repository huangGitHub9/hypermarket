<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<%@include file="/common/baseHeader.jsp"%>
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/index.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pccs/userIndex.css" />
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/userIndex.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/Mydate.js"></script>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/pcjs/pageNavigation.js" ></script>


		<script>
            //切换验证码
            function changeCaptcha(){
                var times = new Date().getTime();
                $("#captcha").attr({src:"<c:url value='/captcha/code.action?time="+times+"'/>"});
            }

            var _showIndex ="${requestScope.showIndex}"
            var showIndex = 0;
			if(_showIndex != ""){
				showIndex = _showIndex;
			}
			console.log(showIndex+"首次执行的下标识");
            function delCollectM(movieId){
                //在加上你本次要显示的内容
                var urlPath = "${pageContext.servletContext.contextPath}/user/delCollectMovie.action"
				window.location.href= urlPath+"?movieId="+movieId+"&handleT=2";
			}

            function delhistoryM(movieId){
                var urlPath = "${pageContext.servletContext.contextPath}/user/delUHistoryRecord.action"
                window.location.href= urlPath+"?movieId="+movieId+"&handleT=3";
            }
            function clearHistoryM(){
                var urlPath = "${pageContext.servletContext.contextPath}/user/clearUHistoryRecord.action"
                window.location.href= urlPath+"?handleT=3";
            }



            //为分页提供帮助属性
            var counts = "${requestScope.counts}";
            var url ="${pageContext.servletContext.contextPath}/user/userIndexUI.action";
            var pCur = "${requestScope.pCur}";
            var pSize = "${requestScope.pSize}";

		</script>
	</head>

	<body>
		<div class="panel panel-info" style="width:1180px ; margin: auto;">

			<div class="panel-heading" style="background-image: url(<c:url value='/img/beijing.png'/>);">
				<h3 class="panel-title">
					<div>
						<c:choose>
							<c:when test="${fn:contains(sessionScope.session_user.userHeadImgPath, 'https:')}">
								<img src="${sessionScope.session_user.userHeadImgPath}'/>"
									 width="130px" height="130px" class="img-circle"/>
							</c:when>
							<c:otherwise>
								<img src="<c:url value='/${sessionScope.session_user.userHeadImgPath}'/>"
									 width="130px" height="130px" class="img-circle"/>
							</c:otherwise>
						</c:choose>


					</div>

					<div style="margin-left: 125px; margin-top: -50px;">
						<font size="6em">${sessionScope.session_user.userName}</font>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					<ul class="layui-tab-title">
						<li  class="layui-this">个人信息</li>
						<li >修改信息</li>
						<li >视频收藏</li>
						<li >历史记录</li>
						<li >vip选项</li>
						<li >消费记录</li>
					</ul>
					<div class="layui-tab-content">
						<!--用户的个人信息开始 -->
						<div class="layui-tab-item layui-show ">
							<form class="form-horizontal " role="form ">
								<!--设置隐藏域 为收藏分页提供-->
								<input type="hidden" name="pCur" value="1" id="pCurId">
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">用户名:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " placeholder="${sessionScope.session_user.userName} " disabled="disabled">
									</div>
								</div>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " placeholder="${sessionScope.session_user.userPassword} " disabled="disabled">
									</div>
								</div>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">年&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " placeholder="${sessionScope.session_user.userAge} " disabled="disabled">
									</div>
								</div>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">生&nbsp;&nbsp;&nbsp;&nbsp;日:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " placeholder="<fmt:formatDate
											value='${sessionScope.session_user.userBirthday}'
											type="date"
											dateStyle="medium"
									/> " disabled="disabled">
									</div>
								</div>
								<c:if test="${sessionScope.session_user.userMemEndTime != null}">
									<div class="form-group ">
										<label  class="col-sm-2 control-label ">会员期限：</label>
										<div class="col-sm-3 ">
											<input class="form-control "  type="text " placeholder="<fmt:formatDate
											value='${sessionScope.session_user.userMemEndTime}'
											type="date"
											dateStyle="medium"
									/> " disabled="disabled">
										</div>
									</div>
								</c:if>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">性&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " placeholder="${sessionScope.session_user.userGender} " disabled="disabled">
									</div>
								</div>
							</form>
						</div>
						<!--用户的个人信息结束-->
						<!--用户的个人信息修改开始-->
						<div class="layui-tab-item ">
							<form class="form-horizontal " role="form " action="<c:url value="/user/edit.action"/>" method="post" enctype="multipart/form-data">
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">用户名:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " name="userName" placeholder="${sessionScope.session_user.userName} "
											value="${sessionScope.session_user.userName}">
									</div>
								</div>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="password " name="userPassword" placeholder="${sessionScope.session_user.userPassword} "
											   value="${sessionScope.session_user.userPassword}"
										>
									</div>
								</div>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">年&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
									<div class="col-sm-3 ">
										<input class="form-control "  type="text " name="userAge" placeholder="${sessionScope.session_user.userAge} "
											value="${sessionScope.session_user.userAge} ">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">生&nbsp;&nbsp;&nbsp;&nbsp;日:</label>
									<div class="col-sm-3">
										<input type="text" class="layui-input form-control"  name="userBirthday"  id="birthday" placeholder="<fmt:formatDate
											value='${sessionScope.session_user.userBirthday}'
											type="date"
											dateStyle="medium"
									/>" value="<fmt:formatDate
											value='${sessionScope.session_user.userBirthday}'
											type='date'
											dateStyle='medium'/> ">
									</div>
								</div>
								<div class="form-group">
									<label  class="col-sm-2 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
									<div class="radio">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label>
											<input type="radio" name="userGender"  value="男"
											<c:if test="${sessionScope.session_user.userGender eq '男'}">
												   checked="checked"
											</c:if>
											>男
										</label>
										<label>
											<input type="radio" name="userGender" value="女"
											<c:if test="${sessionScope.session_user.userGender eq '女'}">
												   checked="checked"
											</c:if>
											>女
										</label>
									</div>
								</div>
								<div class="form-group ">
									<label  class="col-sm-2 control-label ">头&nbsp;&nbsp;&nbsp;&nbsp;像:</label>
									<div class="col-sm-10 ">
										<input type="file" name="headImgFile" value="${sessionScope.session_user.userHeadImgPath}"/>
									</div>
								</div>
								<div class="form-group ">
									<label for="firstname" class="col-sm-2 control-label">验证码:</label>
									<img id="captcha" src="<c:url value='/captcha/code.action'/>"
										 onclick="changeCaptcha()" width="100px" height="35px" title="点击更图片"/>
									<div class="col-sm-10" style="width: 150px;">
										<input type="text" name="captcha" class="form-control" id="firstname" placeholder="请输入验证码">
									</div>
								</div>

								<div class="form-group ">
									<div class="col-sm-offset-2 col-sm-10 ">
										<button type="submit " class="btn btn-success ">确定</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="reset " class="btn btn-primary ">重置</button>
									</div>
								</div>
							</form>
						</div>
						<!--用户的个人信息修改结束-->
						<!--收藏视频开始-->
						<div class="layui-tab-item ">
							<div class="panel panel-default">
								<div class="panel-body" style="width:1180px; margin-left:65px;" >
									<div  class="show_movie_list2">
										<ul  class="movie_list2">
											<c:forEach items="${requestScope.collectMovies}" var="collectM">
											<li class="movie_li " style="list-style: none;" >
												<a href="<c:url value='/movie/movieDetailUI.action?movieId=${collectM.movieId}'/> "
												   title="${collectM.movieName}" target="_blank ">
													<img src="<c:url value='/${collectM.movieImgPath}'/> " alt=" "/>
												</a>
												<p class="movie_li_p_name " style="list-style: none; ">
													<a href="<c:url value='/movie/movieDetailUI.action?movieId=${collectM.movieId}'/> "
													   style="color:#333; text-decoration:none; "  title="${collectM.movieName}" target="_blank ">
														${collectM.movieName}
													</a>
												</p>
												<a href="javascript: " class="remove_item_remove">
													<span class="delIcon"
														  onclick="delCollectM('${collectM.movieId}')">
														×
													</span>
												</a>
											</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							<div id="page_foot">
								<div id="fenye"></div>
							</div>
						</div>
						<!--收藏视频开始结束-->
						<!--浏览的历史记录开始-->
						<div class="layui-tab-item">
							<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
								<legend>
									浏览历史记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" class="btn btn-info btn-lg" onclick="clearHistoryM()">
										<span class="glyphicon glyphicon-trash"></span>CLEAR
									</a>
								</legend>
							</fieldset>
							<ul class="layui-timeline">
							<c:forEach items="${requestScope.historyGBMovies}" var="historyMMKV">
								<li class="layui-timeline-item">
									<i class="layui-icon layui-timeline-axis"></i>
									<div class="layui-timeline-content layui-text">
										<h3 class="layui-timeline-title">${historyMMKV.key}</h3>
										<div class="panel panel-default">
											<div class="panel-body" style="width:1180px; margin-left:65px;">
												<div class="show_movie_list2">
													<ul id="movie_list2_Id" class="movie_list2">
														<c:forEach items="${historyMMKV.value}" var="history">
														<li id="movie_li_Id" class="movie_li " style="list-style: none;" id="li1">
															<a href="<c:url value='/movie/movieDetailUI.action?movieId=${history.movie.movieId}'/> "
															   title="${history.movie.movieName} " target="_blank ">
																<img src="<c:url value='/${history.movie.movieImgPath} '/> " alt=" "/>
															</a>
															<p class="movie_li_p_name " style="list-style: none; ">
																<a href="<c:url value='/movie/movieDetailUI.action?movieId=${history.movie.movieId}'/> "
																   style="color:#333; text-decoration:none;"
																   title="${history.movie.movieName} "
																   target="_blank ">${history.movie.movieName}</a>
															</p>
															<a  href="javascript: " class="remove_item_remove remove_item_remove2" style="text-decoration:none;color:orange">
																<sapn class="delIcon"  onclick="delhistoryM('${history.movie.movieId}')">
																	×
																</sapn>
															</a>
														</li>
														</c:forEach>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</li>
							</c:forEach>
							</ul>
						</div>
						<!--浏览的历史记录结束-->
						<!--vip选择开始-->
						<div class="layui-tab-item ">
							<form class="form-horizontal " role="form " action="<c:url value="/alipay/setRequestParam.action"/>" method="post">
								<div class="form-group">
									<label  class="col-sm-2 control-label">VIP套餐:</label>
									<div style="float: left;margin-left: 15px;">
										<select class="form-control" name="memPackageId" >
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
										<button type="submit " class="btn btn-success ">确定</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</div>
							</form>
						</div>
						<!--消费记录-->
						<div class="layui-tab-item ">
							<table class="table table-bordered" align="center">
								<thead >
								<tr >
									<th style="text-align:center">价钱</th>
									<th style="text-align:center">时间</th>
									<th style="text-align:center">消费类型</th>
								</tr>
								</thead>
								<tbody align="center">
								<c:forEach items="${requestScope.consumes}" var="con">
									<tr>
										<td>${con.customePrice}</td>
										<td>${con.customeTime}</td>
										<td>充值会员</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</dvi>
			</div>
		</div>
<style>
	/*分页*/
	.layui-laypage a, .layui-laypage span {
		display: inline-block; vertical-align: middle;padding: 0 25px;height: 38px;line-height: 38px;
		margin: 0 -1px 5px 0;background-color: #fff;color: #333;font-size: 14px;font-weight: bold;}
</style>

	</body>

</html>

