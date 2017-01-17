<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>转正审核</title>
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

.koko {
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
.control{
	margin-left:-160px;
}
.control .regspan {
		float:right;
		font-family: 'Microsoft YaHei';
		margin-right: 130px;
		font-weight: normal;
		margin-top: 3px;
		display: none;
	}
	
	.control .rspan {
		cursor: pointer;
		float:right;
		height: 27px;
		margin-right:-190px;
		margin-top: 8px;
		width: 100px;
		border: 1px solid #ddd;
		display: none;
	}
	.control .rspan>span {
		padding: inherit;
		display: block;
		color: black;
		font-weight: normal;
		background-color: transparent;
		cursor: pointer;
		margin-top: -2px;
		width: 100px;
		height: 30px;
		line-height: 30px;
		text-align: center;
		border: 0px solid #ddd;
	}
	
	.control .rspan ul {
		margin-left: -1px;
		display: none;
		position: fixed;
	}

	.control .rspan li {
		background-color: #fff;
		cursor: pointer;
		margin-top: -1px;
		width: 100px;
		height: 30px;
		line-height: 30px;
		text-align: center;
		border: 1px solid #ddd;
		font-weight: normal;
		color:black;
		text-decoration: none;
	}
	
	.control .rspan li a{
		color:black;
		text-decoration: none;
	}
	#apply_lo{
		border:1px solid #ddd; 
	}
	#applies_look .table{
		white-space: inherit;
	}
	#apply_lo tr{
		border-bottom:1px solid #ddd; 
		height:44px; 
		line-height:40px; 
		text-indent: 20px;
	}
</style>
<script>
$(function () {
      /*  //加载数据
       $(".right-onehalf span").click(function(){
    	   var $self=$(this);
    	   if(!$self.hasClass("spanchange")){
    		   $self.siblings("span").removeClass("spanchange");
    		   $self.addClass("spanchange");   
    		   grid.search(searchParames);
    	   }
       }) */
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
    		url: "${pageContext.request.contextPath}/positiveReview/readPages.do?branchs=${branchs}&type=${empType}",
    		pageSizeSelect: true, 
    		pagingToolbarAlign: "left" ,
    		displayBlankRows: false, 
        	pageSize:pageSize,
            displayPagingToolbarOnlyMultiPages: true,  event: {
	            customCellEvents: {
	                click: function (record, rowIndex, colIndex, tdObj, trObj, options) {
	                    if(colIndex ==8 && record.reason != null && record.reason != ""  ){
	                		var $chuangkou = $("#applies_look");
	                		var $apply=record.reason.split("</br>");
	                		
							for(var i=0;i<$apply.length;i++){
								$("#applies_look #apply_lo").append("<tr></tr>");
							}
							$("#apply_lo tr").each(function(i, item) {
						        $("#apply_lo tr").eq(i).html($apply[i]);
						    });
	                		$.basewindow("转正评语", $chuangkou, 500, 300);
	                		$(".basewindow").addClass("custom-scroll");
	                		$chuangkou.css({
	                			"display" : "block"
	                		});
	                    } 
	                }
	            }
            },
            extend: {
                settings: {
                    supportGridEdit:true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                    	type: {
                            build: function (edit, value) {
                                  if(value ==0){
                                    return '<span style="color:red">待转正<span>';
                                }else if (value==1){
                                    return '<span style="color:#cc9933">审核通过<span>';
                                }else if(value == 3){
                               		return '<span style="color:green">转正完成<span>';
                                }
                            } 
                        },reason: {
                            build: function (edit, value) {
                            	  if(value.length > 15){
                                	var str=value.substring(0,15);
                                	str= str+"..."
                                    return str;
                                }else{
                                    return value;
                                }
                            } 
                        }
                    } 
                }
            }
    	});
    	$(".pass").click(function(){
    		if(grid.getSelectedRow().length>0){
    				var type = grid.getRowRecord(grid.getSelectedRow()).type;
    				if(type == 0){
    						var id = grid.getRowRecord(grid.getSelectedRow()).id;
		    				var editurl = "${pageContext.request.contextPath}/positiveReview/edit.do";
					   		$.ajax({
						          type: "get",
						          dataType: "json",
						          url: editurl,
						          data: "id="+id,
						          show:"slide",
						          success: function(data){
						          	if(data.resultStatus == 200){
						          		 var $data = data.data;
							     		 if($data.empType == 1){
							     		 	var $chuangkou = $("#eidtAct");
					   						$.basewindow("艺人转正", $chuangkou, 670, 300);
							     		 	$("#eidtAct #id").val(id);
							     		 	$("#eidtAct #number").html($data.number);
							     		 	$("#eidtAct #name").html($data.name);
							     		 	$("#eidtAct #salary").html($data.salary);
							     		 	$("#eidtAct #money").html($data.pushMoney);
							     		 	$("#eidtAct #newPushMon").val($data.intPush);
							     		 	$("#eidtAct #newSal").val($data.salary);
							     		 }else{
							     		 	var $chuangkou = $("#eidtMan");
					   						$.basewindow("管理转正", $chuangkou, 670, 300);
					   						$("#eidtMan #id").val(id);
							     		 	$("#eidtMan #number").html($data.number);
							     		 	$("#eidtMan #name").html($data.name);
							     		 	$("#eidtMan #salary").html($data.salary);
							     		 	$("#eidtMan #newSal").val($data.salary);
							     		 }
						          	}
							   	  }
							}); 
    				}else{
    					$.threetop("不可重复通过");
    					return ;
    				}
    		}else{
	    		$.threesecond("请先选择", 200, 100, 1000);
	    	}
    	})
    	$("#eidtAct .okok").click(function(){
    			var id = $("#eidtAct #id").val();
    			var newPushMoney = $("#eidtAct #newPushMon").val();
    			var newSalary= $("#eidtAct #newSal").val();
    			var editApply = $("#eidtAct #editApply").val();
    			var allData = {
                        id: id,
                        newPushMoney : newPushMoney,
                        newSalary:  newSalary,
                        editApply:  editApply
                };
  				var editurl = "${pageContext.request.contextPath}/positiveReview/pass.do";
		   		$.ajax({
			          type: "post",
			          dataType: "json",
			          url: editurl,
			          show:"slide",
			          data: JSON.stringify(allData),
                      cache: false,
                      contentType: 'application/json; charset=utf-8'	,
			          success: function(data){
			          	if(data.resultStatus == 200){
			          		$(".zhezhao-basewindow").hide();
							$(".basewindow").hide();
							$('body').append($("#eidtAct"));
							$("#eidtAct").hide();
			          	 	grid.refreshPage();
			          	}
				   	  }
				 });
    	})
    	
    	$("#eidtMan .okok").click(function(){
    			var id = $("#eidtMan #id").val();
    			var newPushMoney = 0;
    			var newSalary= $("#eidtMan #newSal").val();
    			var editApply = $("#eidtMan #editApply").val();
    			var allData = {
                        id: id,
                        newPushMoney : newPushMoney,
                        newSalary:  newSalary,
                        editApply:  editApply
                };
  				var editurl = "${pageContext.request.contextPath}/positiveReview/pass.do";
		   		$.ajax({
			          type: "post",
			          dataType: "json",
			          url: editurl,
			          show:"slide",
			          data: JSON.stringify(allData),
                      cache: false,
                      contentType: 'application/json; charset=utf-8'	,
			          success: function(data){
			          	if(data.resultStatus == 200){
			          		$(".zhezhao-basewindow").hide();
							$(".basewindow").hide();
							$('body').append($("#eidtAct"));
							$("#eidtAct").hide();
			          	 	grid.refreshPage();
			          	}
				   	  }
				 });
    	})
    	
    	$(".reg").click(function(){
    		if(grid.getSelectedRow().length>0){
    			var type = grid.getRowRecord(grid.getSelectedRow()).type;
    			if(type == "0"){
    				$.threetop("未通过审核,无法转正");
    				return;
    			}else if(type == "3"){
    				$.threetop("不可重复转正");
    				return ;
    			}
    			$.yesorno("转正操作不可逆，确认完成转正？",300,160,function() {
    				var id = grid.getRowRecord(grid.getSelectedRow()).id;
    				var editurl = "${pageContext.request.contextPath}/positiveReview/reg.do";
    				$.ajax({
			          type: "post",
			          dataType: "json",
			          url: editurl,
			          show:"slide",
			          data:"id="+id,
			          success: function(data){
			          	if(data.resultStatus == 200){
			          	 	grid.refreshPage();
			          	}else if(data.resultStatus == 100){
			          	 	$.threetop("未知错误");
			          	}
				   	  }
				 });
    			}, function() {
				});
    		}else{
	    		$.threesecond("请先选择", 200, 100, 1000);
	    	}
    	})
    	//延长试用期
    	$(".ext").click(function(){
    		if(grid.getSelectedRow().length>0){
	    		var id = grid.getRowRecord(grid.getSelectedRow()).id;
	    		var type = grid.getRowRecord(grid.getSelectedRow()).type;
	    		if(type == 0){
	    		 	var $chuangkou = $("#applies");
				    $.basewindow("延长试用期", $chuangkou, 500, 324);
				    $(".basewindow").addClass("custom-scroll");
				    $("#applies #id").val(id);
				    $("#applies #type").val(2);
	    		}else{
	    			$.threetop("不可延长试用期！");
	    			return;
	    		}
	    	}else{
	    		$.threesecond("请先选择", 200, 100, 1000);
	    	}
    	})
    $("#applies .okok").click(function(){
    	var id = $("#applies #id").val();
    	var apply = $("#applies #apply").val();
    	var type = $("#applies #type").val();
    	var editurl = "${pageContext.request.contextPath}/positiveReview/applyCheck.do";
   		$.ajax({
	          type: "post",
	          dataType: "json",
	          url: editurl,
	          data: "id="+id+"&apply="+apply+"&type="+type,
	          show:"slide",
	          success: function(data){
	            	$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$('body').append($("#applies"));
					$("#applies").hide();
		     		grid.refreshPage();
		   	  }
		}); 
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
    <span>平台人员</span>
    <span>></span>
    <span class="color99">转正审核</span>
</div>
<jsp:include page="positiveCheckCommon.jsp"></jsp:include>

<div class="custom-scroll thistable"  style="overflow:auto;">
	<table id="searchTable"class="table tablelist"> 
		<tr class="firsttr"> 
	        <th w_index="id"  w_align="center"width="1%;"w_hidden="true">id</th>
	        <th w_num="line" w_align="center" width="1%;">序号</th>
	        <th w_index="number" w_align="center"width="1%;">员工号</th> 
	        <th w_index="name" w_align="center"width="1%;">姓名</th> 
	        <th w_index="nickName" w_align="center"width="1%;">艺名</th>
	        <th w_index="joinDate" w_align="center" width="1%;">入职日期</th>
	        <th w_index="platName" w_align="center" width="1%;">所属平台</th>
	        <th w_edit="applicant" w_index="applicant" w_align="center" width="1%;">申请人</th>
	        <th w_edit="reason" w_index="reason" w_align="center"width="1%;">转正评语</th>
	        <th w_edit="type" w_index="type" w_align="center"width="1%;">状态</th>
	        <th w_index="confirmationDate" w_align="center"width="1%;">转正日期</th>
	        <th w_index="remark" w_align="center">备注</th> 
   		</tr>
	</table>
</div>
	 <div id="applies" style="margin-left: 20px; margin-right: 20px; display:none;">
	  	<table class="table">
	  		<input type="hidden" id="id">
	  		<input type="hidden" id="type">
	  		<tr>
	  			<td><textarea name="apply" id="apply" style="width:410px;height:160px"></textarea></td>
	  		</tr>
	  	</table>
	  	<p style="text-align:center;">
	  		<span id="ok" class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
	  	</p>
	  </div>
	  
	   <div id="applies_look" style="padding-left: 20px; padding-right: 20px; display:none;">
		  	<table class="table" id="apply_lo">
		  		<input type="hidden" id="id">
		  		<input type="hidden" id="type">
		  	</table>
		  	<p style="text-align:center;">
		  		<input class="cancel" type="reset" name="Input" value="关闭">
		  	</p>
	  </div>
	   <div id="eidtAct" style="padding-left: 20px; padding-right: 20px; display:none;">
		  	<table class="table">
		  		<input type="hidden" id="id">
		  		<tr>
						<td style="text-align: right;">员工号：</td>
						<td id="number"></td>
						<td style="text-align: right;">姓名：</td>
						<td id="name" ></td>
				</tr>
				<tr>
						<td style="text-align: right;">底薪：</td>
						<td id="salary"></td>
						<td style="text-align: right;">提成比例：</td>
						<td id="money"></td>
				</tr>
				<tr>
						<td style="text-align: right;">调整底薪：</td>
						<td style="text-align:left;"><input type="text" name="newSal" id="newSal"  style="width:150px;" class="easyui-numberbox input"></td>
						<td style="text-align: right;">调整提成：</td>
						<td><input type="text" name="newPushMon" id="newPushMon" style="width:150px;" class="easyui-numberbox input m20">%</td>
				</tr>
				<tr>
					<td style="text-align: right;" >评语：</td>
					<td colspan="3"  style="text-align:left;"><input type="text" name="editApply" id="editApply"  style="width:466px;" /></td>
				</tr>
	  		</table>
		  	<p style="text-align:center;">
		  		<span  class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
		  	</p>
	  </div>
	  
	  <div id="eidtMan" style="padding-left: 20px; padding-right: 20px; display:none;">
		  	<table class="table">
		  		<input type="hidden" id="id">
		  		<tr>
						<td style="text-align: right;">员工号：</td>
						<td id="number"></td>
						<td style="text-align: right;">姓名：</td>
						<td id="name" ></td>
				</tr>
				<tr>
						<td style="text-align: right;">底薪：</td>
						<td id="salary"></td>
						<td style="text-align: right;">调整底薪：</td>
						<td style="text-align:left;"><input type="text" name="newSal" id="newSal"  style="width:150px;" class="easyui-numberbox input"></td>
				</tr>
				<tr>
					<td style="text-align: right;" >评语：</td>
					<td colspan="3"  style="text-align:left;"><input type="text" name="editApply" id="editApply"  style="width:466px;" /></td>
				</tr>
	  		</table>
		  	<p style="text-align:center;">
		  		<span  class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
		  	</p>
	  </div>
</div>
</body>
</html>
