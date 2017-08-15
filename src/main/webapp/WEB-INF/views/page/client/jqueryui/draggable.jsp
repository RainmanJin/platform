<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>拖动实现</title>
    <script src="<c:url value='/js/lib/jquery-1.12.3.min.js'/>"></script>
    <script src="<c:url value='/js/lib/jquery-ui.js'/>"></script>
    <script>
        $(function () {
            $('#draggable').draggable();//{scroll: false, containment:"parent"}
            $('#droppable').droppable({
                drop:function (event, ui) {
                    $( this )
                            .addClass( "ui-state-highlight" )
                            .find( "p" )
                            .html( "Dropped!" );
                }
            });
        });
    </script>
</head>
<body>
<%--<div style="border: 2px solid #ccc; width: 95%; height: 250px;">--%>
    <div id="draggable" style="cursor: pointer; border: 2px solid #ccc; width: 10%">
        <p>请拖动我！</p>
    </div>
<%--</div>--%>

<div id="droppable" class="ui-widget-header" style="border: 2px solid #ccc; width: 250px; height: 250px;">
    <p>请放置在这里！</p>
</div>
</body>
</html>
