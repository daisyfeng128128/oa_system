<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/js/themes/default/easyui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/js/themes/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/all.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/TimeCircles.css" />
<script src="${pageContext.request.contextPath}/views/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/views/js/jquery-1.10.1.min.js"></script>
<script src="${pageContext.request.contextPath}/views/js/knockout-3.1.0.js"></script>
<script src="${pageContext.request.contextPath}/views/js/sea.js"></script>
<script src="${pageContext.request.contextPath}/views/js/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/views/js/easyui-lang-zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/views/js/datagrid-detailview.js"></script>
<script src="${pageContext.request.contextPath}/views/js/tabs.js"></script>
<script src="${pageContext.request.contextPath}/views/js/TimeCircles.js"></script>

<script>
	var pageSize = Math.round(($("#right", window.parent.document).height() - 130) / 34);
	seajs.use("${pageContext.request.contextPath}/views/js/bootstrap.min");
	seajs.use("${pageContext.request.contextPath}/views/js/json2");
	seajs.use("${pageContext.request.contextPath}/views/js/common");
	seajs.use("${pageContext.request.contextPath}/views/js/all");
	seajs.use("${pageContext.request.contextPath}/views/js/jquery.form");
</script>