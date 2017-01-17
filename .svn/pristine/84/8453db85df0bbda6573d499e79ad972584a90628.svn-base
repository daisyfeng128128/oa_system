<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>操作日志</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<style>
body {
    height: 100%;
    min-width: 200px;
    overflow-x: hidden;
    position: relative; 
}
.yirentable tr{
	height:30px;
	line-height:30px;
}
.yirentable tr:hover{	
	background-color:#ddd;
}
.yirentable .firsttr:hover{
	background-color:#fff;
}
.yirentable .selected{
	background-color:#ddd;
}
.thistable{
	overflow:auto;
	background-color:#fff;
}

</style>
<script>
$(function () {
    if (window.location.href.indexOf("type=1") >= 0) {
		$(".right-two a span").removeClass("spanchange")
		$(".right-two a span").eq(0).addClass("spanchange")
	} else if (window.location.href.indexOf("type=2") >= 0) {
		$(".right-two a span").removeClass("spanchange")
		$(".right-two a span").eq(1).addClass("spanchange")
	} else if (window.location.href.indexOf("type=3") >= 0) {
		$(".right-two a span").removeClass("spanchange")
		$(".right-two a span").eq(2).addClass("spanchange")
	} 
        
	//加载数据
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/mlog/readPages.do?type=${type}",
    	longLengthAotoSubAndTip:false,
    	pageSizeSelect: true, 
    	pagingToolbarAlign: "left",
    	displayBlankRows: false,
        displayPagingToolbarOnlyMultiPages: true
	});
	
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
       
});
</script>
</head>
<body class="custom-scroll">
    <div class="right-one fsize18 fweightbold clearFix">
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
	<div class="rightPadd">
		<div class="right-twohalf">
			<span>首页</span> 
			<span>></span>
			<span>系统设置</span> 
			<span>></span>
			<span class="color99">系统日志</span>
    	</div>
		<div class="right-two">
			<a href="${pageContext.request.contextPath}/mlog/show.do?type=1"><span class="spanchange tBtn">登陆</span></a> 
			<a href="${pageContext.request.contextPath}/mlog/show.do?type=2"><span class="tBtn">新增/删除</span></a>
			<a href="${pageContext.request.contextPath}/mlog/show.do?type=3"><span class="tBtn">修改</span></a>
		</div>
		<div class="custom-scroll thistable"  style="overflow:auto;">
			<table id="searchTable"class="table tablelist">
				<tr class="firsttr"> 
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
			        <th w_num="line" w_align="center" width="1%;">序号</th> 
			         <th w_index="accounts" w_align="center" width="1%;">操作人</th>
			        <th w_index="log" w_align="center" width="1%;">操作记录</th>
			        <th w_index="ip" w_align="center" width="1%;">IP地址</th>
			        <th w_index="createDT" w_align="center" width="1%;">时间</th>
			        <th w_align="center">备注</th> 
		   		</tr>
			</table> 
		</div>
	</div>
</body>
</html>
