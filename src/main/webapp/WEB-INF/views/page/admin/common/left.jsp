<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台管理中心</title>
    <link rel="stylesheet" href="<c:url value='/js/lib/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/admin.css'/>"/>

</head>
<body>
    <div class="menu" id="menu">
        <h2>
            商家管理</h2>
        <ul>
            <li><a href="#">添加商家</a></li>
            <li><a href="#">商家信息管理</a></li>
        </ul>
        <h2>
            商家管理</h2>
        <ul>
            <li><a href="#">添加商家</a></li>
            <li><a href="#">商家信息管理</a></li>
        </ul>
        <h2>
            商家管理</h2>
        <ul>
            <li><a href="#">添加商家</a></li>
            <li><a href="#">商家信息管理</a></li>
        </ul>
        <h2>
            商家管理</h2>
        <ul>
            <li><a href="#">添加商家</a></li>
            <li><a href="#">商家信息管理</a></li>
        </ul>
        <h2>
            商家管理</h2>
        <ul>
            <li><a href="#">添加商家</a></li>
            <li><a href="#">商家信息管理</a></li>
        </ul>
    </div>
<jsp:include page="../../client/common/common.jsp"/>
<script src="<c:url value='/js/lib/jquery-1.12.3.min.js'/>"></script>
    <script type="text/javascript">
        $(".menu h2").click(function () {
            $(this).next("ul").slideToggle(300).siblings("ul").slideUp(400);
            $(this).addClass("hover").siblings().removeClass("hover");
        });
        var i;
        var oa = document.getElementById("menu").getElementsByTagName("a");
        for (i = 0; i < oa.length; i++) {
            oa[i].onclick = function () {
                var tit = self.parent.frames["title"].document.getElementsByTagName('div')[0].getElementsByTagName('p')[0];
                tit.innerHTML = this.innerHTML;
                for (i = 0; i < oa.length; i++) {
                    oa[i].className = '';
                }
                this.className = "cur";
            }
            oa[i].onfocus = function () {
                this.blur();
            }
        }
    </script>
</body>
</html>
