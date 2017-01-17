<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>线下艺人成本校验</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;overflow-y:scroller;
	position: relative;
}
.yirentable tr {
	height: 30px;
	line-height: 30px;
}

.yirentable tr:hover {
	background-color: #ddd;
}

.yirentable .firsttr:hover {
	background-color: #fff;
}

.yirentable .selected {
	background-color: #ddd;
}

.basewindow .okok {
	border: 1px solid #ddd;
	text-align: center;
	height: 38px;
	line-height: 38px;
	display: inline-block;
	color: #fff;
	background-color: #599eeb;
	width: 90px;
	border-radius: 5px;
	margin-right: 20px;
	cursor: pointer;
	margin-top: 20px;
}
.ksss{
cursor: pointer;
border: 1px solid #ddd;
height: 30px;
line-height: 30px;
display: inline-block;
color: #fff;
background-color: #599eeb;
width: auto;
padding: 0px 20px;
border-radius: 5px;
margin-right: 10px;
cursor: pointer;
}
	</style>
	<script type="text/javascript">
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
	
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/costCheck/readPages.do",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit:true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                        costArtists:{
	    	                build: function (edit, value) {
    	                       return value;
	    	                }
	    	            }
                    } 
                }
            }
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
		<div class="right-twoTit">
			<span>首页 </span> 
			<span>></span>
			<span>平台人员 </span> 
			<span>></span>
			<span class="color99">成本校验</span>
		</div>
		
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="number" w_align="center" width="1%;">员工号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_edit="costArtists"  w_index="costArtists" w_align="center" width="1%;">艺人成本</th>
					<th w_index="basicSalary" w_align="center" width="1%;">底薪/保底</th>
					<th w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
		</div>
	</div>
  </body>
</html>
