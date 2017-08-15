<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新用户注册</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>"/>
</head>
<body>
<div class="mod-register">
    <form id="js-form">
        <div class="form-group">
            <label for="loginName" class="col-sm-4">用户名：</label>
            <input name="loginName" id="loginName" placeholder="请输入用户名" class="form-control"/>
        </div>
        <div class="form-group">
            <label class="col-sm-4">密码：</label>
            <input type="password" name="password" id="password" placeholder="请输入密码" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="realName" class="col-sm-4">真实姓名：</label>
            <input name="realName" id="realName" placeholder="请输入真实姓名" class="form-control"/>
        </div>
        <div>
            <label class="col-sm-4">性别：</label>
            <select name="sex" class="form-control">
                <option value="0">女</option>
                <option value="1">男</option>
                <option value="2">保密</option>
            </select>
        </div>
        <div>
            <button class="btn btn-primary" id="js-submit">提交</button>
            <button type="reset" class="btn btn-default">重置</button>
        </div>
    </form>
</div>
<jsp:include page="../common/common.jsp"/>
<script src="<c:url value='/js/client/system/controller/registerController.js'/>"></script>
</body>
</html>
