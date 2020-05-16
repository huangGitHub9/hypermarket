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
        <span>供应商管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="${path }/sys/provider">
            <span>供应商编码：</span>
            <input name="queryProCode" type="text" value="${queryProCode }">

            <span>供应商名称：</span>
            <input name="queryProName" type="text" value="${queryProName }">

            <input value="查 询" type="submit" id="searchbutton">
            <a href="${path }/sys/provideradd">添加供应商</a>
        </form>
    </div>
    <!--供应商操作表格-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr" style="font-size: 13px">
            <th width="10%">供应商编码</th>
            <th width="20%">供应商名称</th>
            <th width="10%">联系人</th>
            <th width="10%">联系电话</th>
            <th width="10%">传真</th>
            <th width="10%">创建时间</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach var="provider" items="${proList }" varStatus="status">
            <tr>
                <td>
                    <span style="font-size: 12px">${provider.providerCode }</span>
                </td>
                <td>
                    <span style="font-size: 12px">${provider.providerName }</span>
                </td>
                <td>
                    <span style="font-size: 12px">${provider.providerContact}</span>
                </td>
                <td>
                    <span style="font-size: 12px">${provider.providerPhone}</span>
                </td>
                <td>
                    <span style="font-size: 12px">${provider.providerFax}</span>
                </td>
                <td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
                <td>
                    <span><a class="viewProvider" href="javascript:;"
                             proid=${provider.id } proname=${provider.providerName }><img
                            src="${path}/statics/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyProvider" href="javascript:;"
                             proid=${provider.id } proname=${provider.providerName }><img
                            src="${path}/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteProvider" href="javascript:;"
                             proid=${provider.id } proname=${provider.providerName }><img
                            src="${path}/statics/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- 分页标签区 -->
    <br/>
    <fk:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" totalNum="${pageModel.recordCount}"
              pageStyle="badoo"
              submitUrl="${path}/sys/provider?pageIndex={0}&queryProCode=${queryProCode }&queryProName=${queryProName }"></fk:pager>
</div>
</section>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该供应商吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${path}/statics/js/providerlist.js"></script>