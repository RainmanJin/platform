<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>主页</title>
    <meta charset="utf-8">
    <!-- 视图适配  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- 360浏览器默认视图 -->
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body>
<div class="pane ui-layout-west">
    <div class="nano">
        <div class="nano-content">
            <div class="sidebar">
                <div class="sidebar-nav">
                    <ul id="side-menu" class="metismenu">

                        <c:forEach var="menu_1" items="${menus['-1']}">
                            <li>
                                <a href="javascript:void(0)"><i class="fa fa-edit"></i> <span
                                        class="nav-label">${menu_1.name}</span><span class="nav-arrow"></span></a>

                                <c:if test="${!empty menus[menu_1.id]}">
                                    <ul class="nav nav-second-level collapse">

                                        <c:forEach var="menu_2" items="${menus[menu_1.id]}">

                                            <c:if test="${empty menus[menu_2.id]}"> <%--不存在三级目录 则二级目录触发--%>
                                                <li><a class="J_menuItem" href="${menu_2.url}"
                                                >${menu_2.name}</a></li>
                                            </c:if>

                                            <c:if test="${!empty menus[menu_2.id]}"> <%--存在三级目录 则二级目录不触发 三级目录触发--%>
                                                <li>
                                                    <a href="javascript:void(0)">${menu_2.name}<span class="nav-arrow"></span></a>
                                                    <ul class="nav nav-third-level collapse">
                                                        <c:forEach var="menu_3" items="${menus[menu_2.id]}">
                                                            <li><a class="J_menuItem" href="${menu_3.url}"
                                                            >${menu_3.name}</a></li>
                                                        </c:forEach>
                                                    </ul>

                                                </li>
                                            </c:if>

                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- 通用页面 -->
<jsp:include page="common/common.jsp"/>
<script src="<c:url value='/js/client/home/home_main.js'/>"></script>
</body>
</html>
