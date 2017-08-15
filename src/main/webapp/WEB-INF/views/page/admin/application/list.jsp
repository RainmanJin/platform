<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>应用管理</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>"/>
</head>
<body>
<div class="app-manage app-table">
    <p>
        <a class="btn btn-primary" href="<c:url value='/client/application/toadd'/>">新增应用</a>
        <a id="publish" class="btn btn-default">发布</a>
        <a id="offShelves" class="btn btn-default">下架应用</a>
        <a id="classify" class="btn btn-default">归类</a>
        <a id="delete" class="btn btn-default">删除</a>
    </p>
    <table class="com-table table table-bordered">
        <thead>
        <tr>
            <th width="3.6%"><input type="checkbox"></th>
            <th width="4.5%">序号</th>
            <th width="10.7%">应用名称</th>
            <th>应用版本</th>
            <%--<th>应用类别</th>--%>
            <th>应用类型</th>
            <th width="12.8%">打开浏览器类型</th>
            <th width="11%">单点登录<br>状态</th>
            <th>发布状态</th>
            <th>发布日期</th>
            <th width="8%">操作员</th>
            <th width="7.1%">操作</th>
        <tr/>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="app" varStatus="status">
            <tr>
                <td><input class="js-check" type="checkbox" value="${app}" data-id="${app.id}"></td>
                <td>${ status.index + 1}</td>
                <td><i class="app-icon"><img src="${app.appIconUrl}" alt=""></i>${app.appName}</td>
                <td>${app.appVersion}</td>
                    <%--<td data-cateId="${app.appCategoryId}">${app.appCategoryName}</td>--%>
                <td value="${app.appType}">
                    <c:if test="${app.appType==0}">浏览器应用</c:if>
                    <c:if test="${app.appType==1}">插件应用</c:if>
                </td>
                <td value="${app.browserType}">
                    <c:if test="${app.browserType==null}"></c:if>
                    <c:if test="${app.browserType==0}">本地IE浏览器</c:if>
                    <c:if test="${app.browserType==1}">本地默认浏览器</c:if>
                </td>
                <td value="${app.ssoStatus}">
                    <c:if test="${app.ssoStatus==0}">未接入</c:if>
                    <c:if test="${app.ssoStatus==1}">已接入</c:if>
                </td>
                <td value="${app.publishStatus}">
                    <c:if test="${app.publishStatus==0}">未发布</c:if>
                    <c:if test="${app.publishStatus==1}">已发布</c:if>
                </td>
                <td>
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${app.publishDate}"/>
                </td>
                <td>${app.operator}</td>
                <td>
                    <a href="${app.appUrl}" target="_blank">预览</a>
                    <c:if test="${app.publishStatus==0}">
                        <a class="edit"
                           href="<c:url value='/admin/sys/application/toedit/${app.id}'/>">修改</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/admin/application/controller/listController.js'/>"></script>
</body>
</html>
