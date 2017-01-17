<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code="title"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/all.css" />
<script src="${pageContext.request.contextPath}/views/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/views/js/sea.js"></script>
<script>
    seajs.use("${pageContext.request.contextPath}/views/js/bootstrap.min");
    seajs.use("${pageContext.request.contextPath}/views/js/all");
</script>
<script type="text/javascript">
	$(function(){
		$(".loginout").click(function(data){
			$.ajax({
				type : "POST",
				cache : false,
				async : false,
				url : "${pageContext.request.contextPath}/user/logout.do",
				success : function(data) {
					if(data.resultStatus == 200){
						window.location.href="${pageContext.request.contextPath}/login.do";
					}
				}
		    });			
		});
	})
</script>
<style>
body {
    height: 100%;
    min-width: 200px;
    overflow: hidden;
    position: relative;
    background-color:#f1f1f1; 
}

 </style>
</head>
<body class="custom-scroll">  
	<div class="right">
	   <div class="right-one fsize14 fweightbold clearFix">
	   		<div class="fl">
	   			<span class="refresh fl"></span>
	   		</div>
	   		<div class="fr">
	   			<span class="rName fl"></span>
	   			<span class="username fl">${user.user.accounts}</span>
	   			<span class="fl">, 欢迎来到<spring:message code="title"/></span>
	   			<a href="#" id="logout" target="_parent" class="loginout fl">| 退出系统</a>
	   		</div>
	   </div>
	   <div class="right-two"></div>
	</div>
</body>
</html>