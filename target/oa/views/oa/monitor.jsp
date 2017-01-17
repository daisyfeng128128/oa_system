<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>操作记录 </title>
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

</style>
<script>
$(function () {
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
        
	//加载数据
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/operation/readPages.do",
    	pageSizeSelect: true, 
    	pageSize:pageSize,
    	pagingToolbarAlign: "left",
    	displayBlankRows: false,
        displayPagingToolbarOnlyMultiPages: true
	});
  
     
});
</script>
</head>
<body>
<div class="right-one fsize18 fweightbold">系统设置
<span style="margin-left:20px;"><a href="#">首页</a></span>
<span>></span>
<span><a href="/oa/actoresOnline/showOnline.do">操作记录</a></span>
</div> 
<div class="control">
	<jsp:include page="attsCommon.jsp"></jsp:include>
</div>
<div class="custom-scroll thistable"  style="overflow:auto;">
	<table id="searchTable"class="table tablelist">
		<tr class="firsttr"> 
	        <th w_num="line" w_align="center" width="1%;">序号</th> 
	        <th w_index="id" w_align="center"width="1%;">log</th>
	        <th w_align="center">备注</th> 
   		</tr>
</table> 

</div>
</body>
</html>
