<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图片上传</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>"/>
</head>
<body>
    <div>
        <form id="js-form" class="form-horizontal"
              action="<c:url value='/admin/image/addoredit'/>" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">标题</label>
                <div class="col-sm-10">
                    <input class="form-control" id="title" name="title"/>
                </div>
            </div>
            <div class="form-group">
                <label for="type" class="col-sm-2 control-label">类型</label>
                <div class="col-sm-10">
                    <input class="form-control" id="type" name="type"/>
                </div>
            </div>
            <div class="form-group">
                <label for="introduce" class="col-sm-2 control-label">描述</label>
                <div class="col-sm-10">
                    <input class="form-control" id="introduce" name="introduce"/>
                </div>
            </div>
            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">图片</label>
                <div class="col-sm-10">
                    <input id="imageUrl" name="imageUrl" type="file"/>
                </div>
            </div>
        </form>
        <div class="form-group">
            <button id="js-submit" class="btn btn-primary">提交</button>
            <button id="js-reset" class="btn btn-default">重置</button>
        </div>
    </div>
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/admin/image/controller/editController.js'/>"></script>
</body>
</html>
