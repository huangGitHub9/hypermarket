<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fk" uri="/haiwan" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!-- 引入分页样式 -->
<link rel="stylesheet" href="${path}/statics/pager.css">
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>职员管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="${path }/sys/admin/user">
            <span>职员名称：</span>
            <input name="queryname" class="input-text" type="text" value="${userName }">

            <span>角色：</span>
            <select name="queryUserRole">
                <c:if test="${roleList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="role" items="${roleList}">
                        <option
                                <c:if test="${role.id == userRole }">selected="selected"</c:if>
                                value="${role.id}">${role.roleName}</option>
                    </c:forEach>
                </c:if>
            </select>

            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${path}/sys/admin/useradd">添加职员</a>
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr" style="font-size: 13px">
            <th width="10%">职员编码</th>
            <th width="20%">职员名称</th>
            <th width="10%">性别</th>
            <th width="10%">年龄</th>
            <th width="10%">电话</th>
            <th width="10%">角色</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach var="user" items="${userList }" varStatus="status">
            <tr>
                <td>
                    <span style="font-size: 12px">${user.userCode }</span>
                </td>
                <td>
                    <span style="font-size: 12px">${user.userName }</span>
                </td>
                <td>
							<span style="font-size: 12px">
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
                </td>
                <td>
                    <span style="font-size: 12px">${user.age}</span>
                </td>
                <td>
                    <span style="font-size: 12px">${user.phone}</span>
                </td>
                <td>
                    <span style="font-size: 12px">${user.role.roleName}</span>
                </td>
                <td>
                    <span><a class="viewUser" href="javascript:;" userid=${user.id } username=${user.userName }><img
                            src="${path }/statics/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyUser" href="javascript:;" userid=${user.id } username=${user.userName }><img
                            src="${path}/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }><img
                            src="${path}/statics/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- 分页标签区 -->
    <br/>
    <fk:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" pageStyle="badoo"
              totalNum="${pageModel.recordCount}"
              submitUrl="${path}/sys/admin/user?pageIndex={0}&queryname=${userName}&queryUserRole=${userRole}"></fk:pager>

</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${path }/statics/js/userlist.js"></script>
