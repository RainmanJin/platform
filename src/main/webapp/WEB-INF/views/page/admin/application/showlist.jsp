<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>应用</title>
    <%--<jsp:include page="../common/meta-ie6.jsp"/>--%>
</head>
<body>
<div class="nano scss-application">
    <div class="nano-content">
        <div class="application">
            <div class="advert">
                <a href="http://linksgood.com/page/client/topic/wechatfocus" target="_blank">
                    <img src="<c:url value='/img/client/application/advert/lianlian_wechat_focus.jpg'/>" alt="">
                </a>
            </div>
            <ul class="clear list">
                <c:forEach var="app" items="${list}" step="1" varStatus="vs">
                    <li>
                        <a href='${app.url}?v=${app.isSso==0?0:1}' target="_blank">
                            <div>
                                <img src="${app.iconUrl}" alt="">
                                <span></span>
                            </div>
                            <p>${app.title}</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<script src="<c:url value='/js/lib/jquery-1.12.3.min.js'/>"></script>
<%--<script src="<c:url value='/js/lib/jquery.nanoscroller.min.js'/>"></script>--%>
<%--<script>--%>
    <%--!(/msie 6./i.test(window.navigator.appVersion)) ? $(".nano").nanoScroller() : $('.scss-application').removeClass('nano');--%>
<%--</script>--%>
</body>
</html>