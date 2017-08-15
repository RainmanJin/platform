<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>高校信息列表</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>"/>
    <script type="text/javascript" src="<c:url value='/js/lib/jquery-1.12.3.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/lib/zTree/skin/zTreeStyle.css'/>"/>
    <style>
        div {
            float: left;
        }
    </style>
</head>
<body>
<div class="zTreeDemoBackground left">
    <ul id="user_tree" class="ztree"
        style="border: 1px solid #617775;overflow-y: scroll;height: auto;width: 200px;"></ul>
</div>
<div>
    <div>
        <button id="add" class="btn btn-primary">新增</button>
    </div>
    <table id="college" class="table table-bordered">
        <%--<th>序号</th>--%>
        <th>校名</th>
        <th>英文名</th>
        <th>简称</th>
        <th>创办日期</th>
        <th>校长</th>
        <th>地址</th>
        <th>属性</th>
        <th>占地面积</th>
    </table>
</div>
<script id="college-templete" type="text/x-handlebars-templete">

    {{#each list}}
     <tr>
        <%--<td></td>--%>
        <td>{{name}}</td>
        <td>{{englishName}}</td>
        <td>{{abbreviation}}</td>
        <td>{{setUpDate}}</td>
        <td>{{principal}}</td>
        <td>{{address}}</td>
        <td>{{property}}</td>
        <td>{{area}}</td>
    </tr>
    {{/each}}
</script>
<jsp:include page="../../client/common/common.jsp"/>
<script type="text/javascript" src="<c:url value='/js/admin/business/controller/collegeListController.js'/>"></script>
</body>
</html>
