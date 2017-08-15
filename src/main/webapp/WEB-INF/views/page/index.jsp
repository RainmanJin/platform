<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 视图适配  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- 360浏览器默认视图 -->
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>">
</head>
<body>
<h2>Hello World!</h2>
<%--<a href="<c:url value='/client/tologin'/>">登录</a>--%>
<%--<a type="button" href="<c:url value='/client/toregister'/>">注册</a>--%>
<!-- js文件 -->
<script src="<c:url value='/js/client/index/index_main.js'/>"/>
</body>
</html>
