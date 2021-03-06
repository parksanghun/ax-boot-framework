<%@ page import="com.chequer.axboot.core.utils.ContextUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" uri="http://axisj.com/axu4j" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>AXBOOT :: ${title}</title>

    <link rel="shortcut icon" href="<c:url value='/static/ui/axisj-black.ico'/>" type="image/x-icon"/>
    <link rel="icon" href="<c:url value='/static/ui/axisj-black.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/plugins/axicon/axicon.min.css' />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/ui/axisj/axisj-theme-axboot/axisj.min.css?layout=empty'/>" id="axu-theme-axisj"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/app.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/custom.css' /> "/>

    <ax:write divname="css"/>
    <script type="text/javascript">
        var CONTEXT_PATH = "<%=ContextUtil.getContext()%>";
    </script>
    <script type="text/javascript" src="<c:url value='/static/plugins/jquery/jquery.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/plugins/axisj/dist/AXJ.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/plugins/chartjs/Chart.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/plugins/mustache/mustache.min.js' />"></script>

    <script type="text/javascript" src="<c:url value='/static/plugins/AXBinder/dist/AXBinder.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/plugins/axboot.js/dist/axboot.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/js/common/ax5-polyfill.js' />"></script>

    <script type="text/javascript" src="<c:url value='/static/js/common/base.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/js/data/data.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/js/common/app.js' />"></script>

    <ax:write divname="js"/>
</head>
<body>
<div id="AXPage" class="${axpage_class}">

    <ax:write divname="ax-sample-menu"></ax:write>

    <div class="ax-body">
        <div class="ax-wrap">
            <div class="ax-layer ax-title">
                <div class="ax-col-12">
                    <ax:write divname="header"/>
                </div>
                <div class="ax-clear"></div>
            </div>

            <div class="ax-layer">
                <div class="ax-col-12">
                    <div class="ax-unit">
                        <ax:write divname="contents"/>
                    </div>
                </div>
                <div class="ax-clear"></div>
            </div>

        </div>
    </div>

    <div class="ax-footer">
        <div class="ax-wrap">
            <div class="ax-col-6">
                <div class="ax-unit">
                    <ul class="ax-version">
                        <li>COPYRIGHT(C) 2015 AXISJ/AX-boot</li>
                        <li>Optimized for IE9+, Chrome, FireFox, Safari</li>
                    </ul>
                </div>
            </div>
            <div class="ax-col-6">
                <div class="ax-unit">

                </div>
            </div>
            <div class="ax-clear"></div>
        </div>
    </div>
</div>
<ax:write divname="scripts"/>
</body>
</html>
