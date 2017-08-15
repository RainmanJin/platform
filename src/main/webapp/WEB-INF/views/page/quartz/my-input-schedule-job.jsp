<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp"/>

<form action="save-schedule-job" method="post" class="form-horizontal">
    <input type="hidden" name="scheduleJobId" value="${myScheduleJobDto.scheduleJobId}">
    <%--<input type="hidden" name="keywords" value="${myScheduleJobDto.keywords}">--%>

    <div class="form-group">
        <label for="jobName" class="col-sm-2 control-label">任务名称</label>
        <div class="col-sm-8">
            <input type="text" id="jobName" name="jobName" value="${myScheduleJobDto.jobName}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="jobGroup" class="col-sm-2 control-label">任务分组</label>
        <div class="col-sm-8">
            <input type="text" id="jobGroup" name="jobGroup" value="${myScheduleJobDto.jobGroup}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="aliasName" class="col-sm-2 control-label">任务别名</label>
        <div class="col-sm-8">
            <input type="text" id="aliasName" name="aliasName" value="${myScheduleJobDto.aliasName}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="cronExpression" class="col-sm-2 control-label">时间表达式</label>
        <div class="col-sm-8">
            <input type="text" id="cronExpression" name="cronExpression" value="${myScheduleJobDto.cronExpression}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">是否异步</label>
        <div class="col-sm-8">
            <input type="radio" name="isSync" value="false" <c:if test="${myScheduleJobDto.isSync == 'false'}">checked</c:if> />同步
            <input type="radio" name="isSync" value="true" <c:if test="${myScheduleJobDto.isSync == 'true'}">checked</c:if> />异步
        </div>
    </div>

    <div class="form-group">
        <label for="url" class="col-sm-2 control-label">任务执行url</label>
        <div class="col-sm-8">
            <input type="text" id="url" name="url" value="${myScheduleJobDto.url}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="url" class="col-sm-2 control-label">服务地址</label>
        <div class="col-sm-8">
            <input type="text" id="classFullPath" name="classFullPath" value="${myScheduleJobDto.classFullPath}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="url" class="col-sm-2 control-label">方法名称</label>
        <div class="col-sm-8">
            <input type="text" id="methodName" name="methodName" value="${myScheduleJobDto.methodName}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="url" class="col-sm-2 control-label">参数</label>
        <div class="col-sm-8">
            <input type="text" id="params" name="params" value="${myScheduleJobDto.params}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="description" class="col-sm-2 control-label">任务描述</label>
        <div class="col-sm-8">
            <input type="text" id="description" name="description" value="${myScheduleJobDto.description}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">保存</button>
            <a href="list-schedule-job" class="btn btn-default" role="button">返回</a>
        </div>
    </div>

</form>
<jsp:include page="foot.jsp"/>
</html>
