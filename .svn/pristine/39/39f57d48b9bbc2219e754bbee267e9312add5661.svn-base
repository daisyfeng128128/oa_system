<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>职位管理</title>
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
.remarks{
	width:70px;
	text-align:right;
	display: inline-block;
}
.bottom{
	text-align:center;
	margin-top: -14px;
}
.control{
	margin-left:-174px; 
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
	
	//数据获取
	 var grid = $.fn.bsgrid.init("searchTable", {
    	url:"${pageContext.request.contextPath}/positions/readPages.do",
        pageSizeSelect: true, 
        pageSize:pageSize,
        pagingToolbarAlign: "left",
        displayBlankRows: false,
        displayPagingToolbarOnlyMultiPages: true

   	 });
	
	//新增
    $(".control .add").click(function(){
    	var $chuangkou=$("#positionsForm");
        $.basewindow("新建职位",$chuangkou,392,260);       
    })    
    //修改
    $(".control .mid").click(function(){
    	var $chuangkou=$("#positionsForm");
    	if(grid.getSelectedRow().length>0){
    	    var id = grid.getRowRecord(grid.getSelectedRow()).id;
    	    var editurl = "${pageContext.request.contextPath}/positions/edit.do?t="+new Date().getTime();
			$.ajax({
		          type: "get",
		          dataType: "json",
		          url: editurl,
		          data: "id="+id,
		          show:"slide",
		          success: function(data){
		            $.basewindow("修改员工",$chuangkou,392,260);
		          	$("#id").val(data.id);
		          	$("#name").val(data.name);
		          	$("#described").val(data.described);
		          },
				  error : function(data){
				  }
			});  	
            
           $chuangkou.css({"display":"block"});	
    	}
    	else{
            $.threesecond("请先选择", 200, 100, 1000);
    	}
    })   
    //删除
    $(".control .del").click(function () {
    	if(grid.getSelectedRow().length>0){
    	   
            var $yes = function () {
                var id = grid.getRowRecord(grid.getSelectedRow()).id;
                var delUrl = "${pageContext.request.contextPath}/positions/delete.do?t="+new Date().getTime();
                $.ajax({
			          type: "get",
			          dataType: "json",
			          url: delUrl,
			          data: "id="+id,//要发送的数据
			          success: function(data){
			            if("201"==data.resultStatus){
			                $.threesecond(data.data, 200, 100, 1000);
			            }
			          	if("success" == data.resultMessage){
			          	  $("table .selected").remove();
			          	}
			          },
					  error : function(data){
					  }
				});
               
                $(".control .onechoose").show();
                $(".control .morechoose").show();
            };
            var $no = function () {
				
            };
            $.yesorno("是否删除？", 256, 154, $yes, $no);
    	}
        else{
        	$.threesecond("请先选择", 200, 100, 1000);
        }
    });
    //提交+判断
    $("#positionsForm .okok").click(function () {
        var name = $("#name").val();
        if (name == "" || name == null) {
            $.threetop("请填写职位名称!");
        }
        else {
            $("#positionsForm").submit();
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
	<div class="right-twohalf">
		<span>首页</span> 
		<span>></span>
		<span>人事系统</span> 
		<span>></span>
		<span class="color99">职位管理</span>
	</div>
	<div class="clearFix right-twoRight">
		<div class="fr">
			<div class="search">
	    		<input id="fast" type="text" placeholder="姓名搜索">
		    	<button type="button" id="seach" class="tableok">搜索</button>				
			</div>
		</div>
	</div>
		
	
	<div class="control">
	  	<jsp:include page="attsCommon.jsp"></jsp:include>  	
	</div>
	<div class="thistable custom-scroll"  style="overflow:auto; background-color:#fff; ">
		<table id="searchTable"class="table tablelist">
			<tr class="firsttr">
			<th w_index="ID" w_align="center" w_hidden="true"  width="1%;">ID</th>
			<th w_num="line" w_align="center" width="1%;">序号</th>
			<th w_index="name" w_align="center" width="1%;">职位名称</th>
	        <th w_index="described" w_align="center" width="1%;">职位描述</th>
	        <th  w_align="center">备注</th>    
	   		</tr>
	</table>
		<form action="${pageContext.request.contextPath}/positions/save.do" method="post" id="positionsForm"style="display:none;margin-left:20px;margin-right:20px;">
		<input type="hidden" name="id" id="id" value="">
			<p><lable>职位名称：</lable><input type="text" name="name" id="name" style="width:200px;" maxlength="400" class="input"></p>
			<p><lable>职位描述：<input type="text" name="described" id="described" value="" style="width:200px;" maxlength="400" class="input"></p>
			<p><lable class="remarks">备注：</lable><input type="text" name="" style="width:200px;" maxlength="400" class="input"></p>
			<p class="bottom"><span class="okok">提交</span><input class="cancel" type="reset" name="Input" value="关闭"></p>
	</form>
	</div>
</div>
</body>
</html>
