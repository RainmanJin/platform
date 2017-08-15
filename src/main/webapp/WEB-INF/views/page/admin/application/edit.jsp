<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>应用新增</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/client.css'/>"/>
</head>
<body>
   <div>
       <form id="appForm" class="form-horizontal" action="<c:url value='/client/application/addoredit'/>"
             method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="appName" class="col-sm-2 control-label">应用名称</label>
                <div class="col-sm-10">
                    <input class="form-control" id="appName" name="appName"/>
                </div>
            </div>
           <div class="form-group">
               <label for="iconUrl" class="col-sm-2 control-label">应用图标</label>
               <div class="col-sm-10">
                   <input type="file" class="form-control" id="iconUrl" name="iconUrl"/>
               </div>
           </div>
           <div class="form-group">
               <label for="appUrl" class="col-sm-2 control-label">应用地址</label>
               <div class="col-sm-10">
                   <input class="form-control" id="appUrl" name="appUrl"/>
               </div>
           </div>
           <div class="form-group">
               <label for="appVersion" class="col-sm-2 control-label">应用版本</label>
               <div class="col-sm-10">
                   <input class="form-control" id="appVersion" name="appVersion"/>
               </div>
           </div>
           <div class="form-group">
               <label for="appType" class="col-sm-2 control-label">应用类型</label>
               <div class="col-sm-10">
                   <input class="form-control" id="appType" name="appType"/>
               </div>
           </div>
           <div class="form-group">
               <label for="browserType" class="col-sm-2 control-label">打开浏览器类型</label>
               <div class="col-sm-10">
                   <input class="form-control" id="browserType" name="browserType"/>
               </div>
           </div>
           <div class="form-group">
               <label for="ssoStatus" class="col-sm-2 control-label">单点登录状态</label>
               <div class="col-sm-10">
                   <input class="form-control" id="ssoStatus" name="ssoStatus"/>
               </div>
           </div>
           <div>
               <button type="submit" id="submit" class="btn btn-primary">提交</button>
               <button type="reset" class="btn btn-default">重置</button>
           </div>
       </form>
   </div>
   <jsp:include page="../../client/common/common.jsp"/>
</body>
</html>
