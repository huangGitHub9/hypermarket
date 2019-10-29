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
        <span>订单管理页面</span>
    </div>
    <div class="search">
        <%-- 点击查询后提交的位置为${path}/sys/bill --%>
        <form method="get" action="${path}/sys/bill">
            <span>商品名称：</span>
            <input name="productName" type="text" value="${productName }">

            <span>供应商：</span>
            <select name="providerId">
                <c:if test="${providerList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="provider" items="${providerList}">
                        <option
                                <c:if test="${provider.id == providerId }">selected="selected"</c:if>
                                value="${provider.id}">${provider.providerName}</option>
                    </c:forEach>
                </c:if>
            </select>

            <span>是否付款：</span>
            <select name="isPayment">
                <option value="0">--请选择--</option>
                <option value="1" ${isPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
                <option value="2" ${isPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
            </select>

            <input value="查 询" type="submit" id="searchbutton">
            <a href="${path}/sys/billadd">添加订单</a>
        </form>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr" style="font-size: 13px">
            <th width="10%">订单编码</th>
            <th width="15">商品图片</th>
            <th width="15%">商品名称</th>
            <th width="20%">供应商</th>
            <th width="10%">订单金额</th>
            <th width="8%">是否付款</th>
            <th width="10%">创建时间</th>
            <th width="12%">操作</th>
        </tr>
        <c:forEach var="bill" items="${billList }" varStatus="status">
            <tr>
                <td>
                    <span style="font-size: 12px">${bill.billCode }</span>
                </td>
                <td>
                    <img alt="加载中..." class="photoBill"
                         src="${pageContext.request.contextPath }/statics/upload/${bill.billPhoto }"/>
                </td>
                <td>
                    <span style="font-size: 12px">${bill.productName }</span>
                </td>
                <td>
                    <!-- bill.provider.providerName -->
                    <span style="font-size: 12px">${bill.provider.providerName }</span>
                </td>
                <td>
                    <span style="font-size: 12px">${bill.totalPrice }</span>
                </td>
                <td>
					<span style="font-size: 12px">
						<c:if test="${bill.isPayment == 1}">未付款</c:if>
						<c:if test="${bill.isPayment == 2}">已付款</c:if>
					</span>
                </td>
                <td>
					<span style="font-size: 12px">
					<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
                <td>
                    <span><a class="viewBill" href="${path }/sys/billview?billid=${bill.id }"><img
                            src="${path}/statics/images/read.png" alt="查看" title="查看"/></a></span>
                    <c:if test="${bill.isPayment==1 }">
                        <span><a class="modifyBill" href="${path }/sys/billmodify?billid=${bill.id }"><img
                                src="${path}/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    </c:if>
                    <span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }
                             isPayment=${bill.isPayment }><img src="${path}/statics/images/schu.png" alt="删除"
                                                               title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!-- 分页标签区 -->
    <br/>
    <fk:pager pageIndex="${pageModel.pageIndex}" pageSize="${pageModel.pageSize}" totalNum="${pageModel.recordCount}"
              pageStyle="badoo"
              submitUrl="${path}/sys/bill?pageIndex={0}&productName=${productName}&providerId=${providerId}&isPayment=${isPayment}"></fk:pager>

</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${path}/statics/js/billlist.js"></script>