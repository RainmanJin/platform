<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图片集展示列表</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>"/>
</head>
<body>
    <div>
        <a type="button" class="btn btn-primary" href="<c:url value='/admin/image/toaddoredit'/>">新增</a>
    </div>
    <div>
        <table class="table table-bordered">
            <%--<th>--%>
                <%--序号--%>
            <%--</th>--%>
            <th>
                标题
            </th>
            <th>
                类型
            </th>
            <th>
                描述
            </th>
            <th>
                操作
            </th>
            <tbody>

            <c:forEach var="item" items="${list}">
                <tr>
                    <%--<td>1</td>--%>
                    <td>${item.title}</td>
                    <td>${item.type}</td>
                    <td>${item.introduce}</td>
                    <td>
                        <a href="javascript:void(0);">下载</a>
                        <a href="${item.url}" target="_blank">查看</a>
                        <a class="js-delete" data-id="${item.id}" href="javascript:void(0);">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/admin/image/controller/listController.js'/> "></script>
</body>
</html>
