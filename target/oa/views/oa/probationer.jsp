<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>试用员工</title>
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
.basewindow .table td{
	border:none; 
}
.bottom{
	text-align:center;
}
.control{
	margin-left:-170px;
}
.bg83{
	height: 46px;
    line-height: 46px;
    margin-bottom: 20px;
}
#name,#phone,#joinDate,#regularDate{
	text-align:left;
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
          url: "${pageContext.request.contextPath}/emp/readPagesProbationer.do?type=pro",
          pageSizeSelect: true, 
          pagingToolbarAlign: "left",
          displayBlankRows: false,
    	  pageSize:pageSize,
          displayPagingToolbarOnlyMultiPages: true
      });
	
    //试用期调整
    $(".control .probation").click(function(){
    	var $chuangkou=$("#probationerForm");
    	if(grid.getSelectedRow().length>0){
    		 var id = grid.getRowRecord(grid.getSelectedRow()).id;
             var editurl = "${pageContext.request.contextPath}/emp/editProbationer.do";
    		$.ajax({
	          type: "get",
	          dataType: "json",
	          url: editurl,
	          data: "id="+id,
	          show:"slide",
	          success: function(data){ 
	              $("#id").val(data.id);
	              document.getElementById("name").innerHTML=data.name;
	              document.getElementById("phone").innerHTML=data.phone;
	              if(data.joinDate != null)
	              	document.getElementById("joinDate").innerHTML= new Date(data.joinDate).format("yyyy-MM-dd").toString();
			      if(data.regularDate != null)
	              	$("#regularDate").datebox("setValue", new Date(data.regularDate).format("yyyy-MM-dd").toString());
		       }, 
		      error : function(data){
			 }
			});
    		   	
            $.basewindow("修改转正日期",$chuangkou,392,268);
            $(".basewindow").addClass("custom-scroll");
           $chuangkou.css({"display":"block"});	
    	}
    	else{
            $.threesecond("请先选择", 200, 100, 1000);
    	}
    })
    //转正
    $(".control .become").click(function () {
    	if(grid.getSelectedRow().length>0){
    	     $.yesorno("是否为其转正？", 300, 160, function () {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    var delUrl = "${pageContext.request.contextPath}/emp/updateProbationer.do?t="+new Date().getTime();
                    //后台删除
                    $.ajax({
                        type: "post",
				          dataType: "json",
				          url: delUrl,
				          data: "id="+id+"&type=1",//要发送的数据
				          success: function(data){  
				          	//alert("yes");
				          },error : function(data){
						       location.reload();
						  }
                    });
                   
                }, function () {
                   
                });
    	 
    	}
        else{
        	$.threesecond("请先选择", 200, 100, 1000);
        }
    });
    
  
    //离职
    $(".control .leave").click(function () {
    	if(grid.getSelectedRow().length>0){ 
	    	 var $chuangkou=$("#leaveForm"); 
    	     $.basewindow("离职原因:",$chuangkou,270, 270, function () {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                	$("#leaveForm #id").val(id);
                	$("#leaveForm #type").val(2);
                	$("#leaveForm #tt").val("probationer");
               }, function () {
                   $.threesecond("看来还是手下留情了!", 200, 100, 1000);
               }); 
               $(".basewindow").addClass("custom-scroll");
    	}
        else{
        	$.threesecond("请先选择", 200, 100, 1000);
        }
    });  
       
    $("#fast").change(function () {
        var fastArg = $("#fast").val();
		var searchParames={
		    "fastArg": fastArg
		}
		grid.search(searchParames);
    })
	

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
		<span class="color99">试用员工</span>
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
	<div class="custom-scroll thistable"  style="overflow:auto; background-color:#fff; ">
		<table id="searchTable"class="table tablelist">
			 
	            <tr class="firsttr">
	        <th w_index="id" w_align="center"width="1%;"w_hidden="true">id</th>
	        <th w_num="line" w_align="center" width="1%;">序号</th>
	        
	       <th w_index="num" w_align="center"width="1%;">员工号</th>
	        <th w_index="name" w_align="center"width="1%;">姓名</th>
	        <th w_index="aliasname" w_align="center"width="1%;">艺名</th>
	        <th w_index="posName" w_align="center"width="1%;">职位</th>
	        <th w_index="depName" w_align="center"width="1%;">部门</th>
	        <th w_index="introducer" w_align="center"width="1%;">入职介绍人</th>
	        <th w_index="phone" w_align="center"width="1%;">联系电话</th>
	        <th w_index="incomeday" w_align="center"width="1%;">入职日期</th>
	        <th w_index="outday" w_align="center"width="1%;">转正日期</th><!-- 
	        <th w_index="namepy" w_align="center"width="1%;">姓名拼音</th>
	        <th w_index="empName" w_align="center"width="1%;">上级主管</th>
	        <th w_index="genrer" w_align="center"width="1%;">类型</th>
	        <th w_index="birthday" w_align="center"width="1%;">生日</th>
	        <th w_index="emergencyContact" w_align="center"width="1%;">紧急联系人</th>
	        <th w_index="emergencyPhone" w_align="center"width="1%;">紧急联系人电话</th>
	        <th w_index="liveAdress" w_align="center"width="1%;">现居地址</th> 
	        <th w_index="hostRegister" w_align="center"width="1%;">户籍所在地</th> -->
	        
	        <th  w_align="center">备注</th> 
	   		</tr>
	</table>
	<form id="probationerForm"action="${pageContext.request.contextPath}/emp/addEmp.do" method="post" enctype="multipart/form-data" id="empForm" style="display:none;margin-left:20px;margin-right:20px;">
	
				<table class="table">
					<tr>
						<td colspan="2"style="width:50%;padding:0;border:none;"></td>
					</tr>
				<tr>
						<td>员工姓名：</td>
						<td name="name" id="name" ></td>
					</tr>
					<tr>
						<td>联系电话：</td>
						<td name="phone" id="phone" ></td>
					</tr>
					<tr>
						<td>入职日期：</td>
						<td name="joinDate" id="joinDate"></td>
					</tr>
					<tr>
						<td>转正日期：</td>
						<td><input type="text" class="easyui-datebox" editable="false" name="regularDate" id="regularDate" style="width:200px;"></td>
					</tr>
					
				</table>
				<p class="bottom"><input type="hidden" name="id" id="id"><input class="ok" type="submit" value="提交"><input class="cancel" type="reset" name="Input" value="关闭"></p>
	</form>
	
	
	   <form action="${pageContext.request.contextPath}/emp/updateProbationer.do" method="post" id="leaveForm" style="display:none;margin-left:20px;margin-right:20px;">
	        <p>离职原因：</p>
	        <input type="hidden" name="id" id="id">
	        <input type=hidden name="type" id="type" value="2"> 
	        <input type=hidden name="tt" id="tt" value="probationer">
	        
	        <p><textarea name="reason" id="reason" style="width:230px; height:100px;"></textarea></p> 
	        <p class="bCenter"><input class="ok"  type="submit" value="提交"><input class="cancel" type="reset" name="Input" value="关闭"></p>
	    </form>
	
	</div>
</div>
</body>
</html>
