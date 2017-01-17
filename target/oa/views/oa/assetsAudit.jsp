<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>固定资产月报</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<jsp:include page="treeview.jsp"></jsp:include>
<style>
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;
	position: relative;
}
.mustred {margin-left: 10px;color: #ff0000;}
.mains{width:100%;min-width:1000px;overflow: hidden; background-color:#fff; }
.lefttree{width:15%;margin-right:15px;height:800px;float:left; overflow:scroll; }
.rightbox{width:85%; float:right;overflow:auto;margin-top: -800px;  }

	.mustred{margin-left:10px;color:#ff0000}
	.ok{
	    border: 1px solid #ddd;
        text-align: center;
        height: 38px;
        line-height: 38px;
        display: inline-block;
        color: #fff;
        background-color:#599eeb;
        width: 90px;
        border-radius: 5px;
        margin-right: 20px;
        cursor: pointer;
   		margin-top:20px; 
	}  
	.control{
	margin-left:-290px;
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
	#qq{
		margin-left:-20px; 
	}
	.okok{
	background-color:#ccc1d9;
	}
	.right-twohalf{
	margin-top:0;
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
	
		var grid = $.fn.bsgrid.init("searchTable", {
	    	url: "${pageContext.request.contextPath}/assetsAudit/readPagesAduit.do?branchs=${branchs}",
	        pageSizeSelect: true,
	        pageSize: pageSize,
	        pagingToolbarAlign: "left",
	        displayBlankRows: false,
		    displayPagingToolbarOnlyMultiPages: true ,
	        extend: {
				settings:{
					supportGridEdit:true,
					supportGridEditTriggerEvent:"",
					gridEditConfigs:{
						file:{
							build:function(edit,value,record, rowIndex, colIndex, options){
								if (value =="Excel文档")
									return "<span>"+value+"</span><br/><a style='color:blue;' href='#' onclick='seanot("+record.id+")' >下载文档</a>"
							}
						},
						headPass:{
						build:function(edit,value){
							if(value=="待处理")
								return "<span style='color:#0074ac'>"+value+"</span>"
							if(value=="通过")
								return "<span style='color:#e35d00'>"+value+"</span>"
						  }
					   }
					}
				}        	
	        } 
	    });
	    
	    $(".control .headpass").click(function () {
	    	var ids =  grid.getCheckedValues('id');
	    	if(ids != ""){
	           	var $chuangkou = $("#remark");
	            $.basewindow("是否确认通过?", $chuangkou, 300,320);
				$(".basewindow").addClass("custom-scroll");
			}else{
				$.threesecond("请勾选中数据", 200, 100, 1000);
			}
        });
        
        $("#remark #ok").click(function(){
           		var ids =  grid.getCheckedValues('id');
           		if(ids != ""){
	           		var remarks = $("#remark #remarks").val();
	           		var id;
					for(var i=0;i<ids.length;i++){
					     id=ids[i];
					}
		            var rejectMsg = {
		                "id":id,
		                "opinion":remarks
		            };
		            $.ajax({
		                type: "POST",
		                url: "${pageContext.request.contextPath}/assetsAudit/headpass.do",
		                data: JSON.stringify(rejectMsg),
		                contentType: "application/json; charset=utf-8",
		                cache: false
		            }).done(function (data) {
		                if (data.resultStatus == 200) {
		                    $.threesecond("总公司审阅通过!", 200, 100, 1000);
		                    grid.refreshPage();
		                }
		            }).error(function (jqXHR, textStatus, errorThrown) { });
		            }else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
           });
           
           $(".control .add").click(function(){
		    var $chuangkou = $("#uploadForm");
		    $.basewindow("导入Excel", $chuangkou,510, 250);
			$chuangkou.css({"display" : "block"}); 
		});
		
		$("#searchTable").click(function() {
			if (grid.getSelectedRow().length > 0) {
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
				var headPass = grid.getRowRecord(grid.getSelectedRow()).headPass;
				$(".thistable .headpass").attr("disabled","true").addClass("tablecancel");
				if(headPass == "通过"){
					$(".thistable .headpass").attr("disabled","true").addClass("tablecancel");
				}else if(headPass == "待处理"){
					$(".thistable .headpass").removeAttr("disabled").removeClass("tablecancel");
				}
			}
		});
           
	    $.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
    		data: "",
   			cache: false
   		}).done(function (data) {
   			if(data.data.length > 0){
   				$(data.data).each(function(index,value){
	   				var $self = $(this);
					var url = window.location.href;
					if(url.indexOf("?") > 0){
						url = url.substring(0,url.indexOf("?"));
					}
					$(".right-twohalf .rspan ul").append("<li><a href='"+url+"?branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")   				
	   			});
	   			$(".right-twohalf .regspan").show();
   				$(".right-twohalf .rspan").show();
   			}
   		});
        $(".right-twohalf .rspan>span").click(function(){
			if (!$(".right-twohalf .rspan ul").is(":animated")) {
				$(".right-twohalf .rspan ul").fadeToggle();
	       	}
	    });	
	    
	});
			function seanot(id){
    				var editurl = "${pageContext.request.contextPath}/assetsAudit/exportExcel.do?id="+id;
    				window.location.href=editurl;  
			};
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
			<span>行政系统</span> 
			<span>></span>
			<span class="color99">固定资产月报</span>
		</div>
		<div class="right-two">
			<div class="right-twohalf fr">
				<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
			</div>
		</div>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_index="headPass" w_align="center" width="1%;" w_hidden="true">headPass</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="empName" w_align="center" width="1%;">提交人</th>
					<th w_edit="file" w_index="file" w_align="center" width="1%;">提交文件</th>
					<th w_index="branchsMsg" w_align="center" width="1%;">所属分公司</th>
					<th w_index="thisMonth" w_align="center" width="1%;">固定资产所属月份</th>
					<th w_index="completeDT" w_align="center" width="1%;">提交时间</th>
					<th w_index="remarks" w_align="center" width="1%;">备注</th>
					<th w_index="opinion" w_align="center" width="1%;">审阅意见</th>
				</tr>
			</table>
			<p class="control">
				<jsp:include page="attsTabCommon.jsp"></jsp:include>
			</p>
		</div>
		
		<%-- <form action="${pageContext.request.contextPath}/assetsAudit/save.do" method="post" enctype="multipart/form-data" id="uploadForm" style="display:none;margin-left:20px;margin-right:20px;">
					<table class="table" > 
						<tr >
							<td>Excel</td><td><input type="file"  id= "upload" name = "upload" ><span class="mustred">*(格式:时间(2012-09),固定资产,xlsx)</span></td> 
						</tr>
						<tr>
							<td style="text-align:center;" colspan="2">
								<p>
									<input class="ok" type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="cancel" type="reset" name="Input" value="关闭">
								</p>
							</td>
						</tr>
					</table> 
				</form> --%>
		
		<form  id="remark" style="display:none;margin-left:20px;margin-right:20px;">
			<p>请输入审批意见</p>
			<input type="hidden" name="id" id="id">
			<p><textarea name="remarks" id="remarks" type="text" placeholder="20字以内"  style="width:250px; height:100px;"></textarea></p>
			<p class="bCenter">
				<input class="ok" id="ok" type="button" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</form>
		<form action="${pageContext.request.contextPath}/assetsAudit/save.do" method="post" enctype="multipart/form-data" id="uploadForm" style="display:none;margin-left:20px;margin-right:20px;">
					<table class="table" > 
						<tr >
							<td>Excel</td><td><input type="file"  id= "upload" name = "upload" ><span class="mustred">*(格式:时间(2012-09),固定资产,xlsx)</span></td> 
						</tr>
						<tr>
							<td>备注：</td>
							<td class="taL"><input type="text" name="remarks" id="remarks" style="width:200px;text-align: left" ></td>
						</tr>
						<tr>
							<td style="text-align:center;" colspan="2">
								<p>
									<input class="ok" type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="cancel" type="reset" name="Input" value="关闭">
								</p>
							</td>
						</tr>
					</table> 
				</form>
		</div>
	</body>
</html>
