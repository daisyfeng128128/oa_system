<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>星探管理</title>
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
	.mustred{margin-left:10px;color:#ff0000}
	.ook{
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
		margin-left:-174px;
	}
	#qq{
		margin-left:-20px; 
	}
</style>
<script>
$(function () {
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/talent/readPages.do",
    	pageSizeSelect: true, 
    	pagingToolbarAlign: "left",
    	displayBlankRows: false,
    	pageSize:pageSize,
        displayPagingToolbarOnlyMultiPages: true,
        extend: {
			settings:{
				supportGridEdit:true,
				supportGridEditTriggerEvent:"",
				gridEditConfigs:{
					genrer:{
						build:function(edit,value){
							if(value=="ONLINE")
								return "<span style='color:#e35d00;' >线上</span>"		
							if(value=="OFFLINE")	
								return "<span style='color:#0074ac;' >线下</span>"
							if(value=="null" || value=="")
								return "<span></span>"	
						}
					}
				}
			}        	
        }
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
	
	//新增
	$(".add").click(function() {
		var $chuangkou = $("#addTalent");
		$.basewindow("新建星探", $chuangkou, 830, 308);
		$(".basewindow").addClass("custom-scroll");
	})
	
	//修改
	$(".mid").click(function(){
		var $chuangkou = $("#addTalent");
		if (grid.getSelectedRow().length > 0) {
			var id = grid.getRowRecord(grid.getSelectedRow()).id;
			var genrer=grid.getRowRecord(grid.getSelectedRow()).genrer;
			if(genrer!="OFFLINE"){
			var editurl = "${pageContext.request.contextPath}/talent/edit.do";
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				    var $data =data.data;
					if ($data.sex == "女") {
						$("#sex").val("1").selected;
					} else if ($data.sex == "男") {
						$("#sex").val("2").selected;
					}
					$("#id").val($data.id);
					$("#name").val($data.name);
					$("#aliasname").val($data.aliasname);
					$("#phone").numberbox("setValue", $data.phone);
					$("#qq").numberbox("setValue",$data.qq);
					$("#bankAddress").val($data.bankAddress);
					$("#bankCard").val($data.bankCard);
					$("#number").val($data.number);
				},
				error : function(data) {
				}
				});
			$.basewindow("修改星探",$chuangkou, 830, 308);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.css({ "display" : "block" });
			}else{
			$.threetop("不能修改线下星探!");
			}
	        return false;
		} else {
			$.threesecond("请先选择", 200, 100, 1000);
		}
	})
	
	$(".okok").click(function(){
	    var sname = $("#name").val();
	    var saliasname= $("#aliasname").val();
	    var sid = $("#id").val();
	    var ssex = $("#sex").val();
	    var sphone = $("#phone").numberbox("getValue");
	    var sbankCard = $("#bankCard").val();
	    var sbankAddress = $("#bankAddress").val();
	    var sqq = $("#qq").numberbox("getValue");
	    var snumber = $("#number").val();
	    var telReg = !!sphone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);

	    if(!sname){
	        $.threetop("姓名不能为空!");
	        return false;
	    }
	    if(!saliasname){
	        $.threetop("艺名不能为空!");
	        return false;
	    }
	    if(!ssex){
	        $.threetop("性别不能为空!");
	        return false;
	    }
	    if(!sphone){
	        $.threetop("手机号不能为空!");
	        return false;
	    }
	     if(telReg == false){
             $.threetop("请填写正确手机号!");
                return;
         }
	    if(!sbankCard){
	        $.threetop("工资卡帐号不能为空!");
	        return false;
	    }
	    if(!sbankAddress){
	        $.threetop("工资卡开户地址不能为空!");
	        return false;
	    }
	    var json = {
	            id : sid,
	            name:sname,
	            aliasname:saliasname,
	            sex :ssex,
	            phone :sphone,
	            bankCard : sbankCard,
	            bankAddress:sbankAddress,
	            qq :sqq,
	            number:snumber
	    }
	    var editurl = "${pageContext.request.contextPath}/talent/save.do";
	    $.ajax({
	        type : "post",
			url : editurl,
			cache: false,
	        data: JSON.stringify(json),
	        contentType: 'application/json; charset=utf-8',
	        success:function(data){
	            if (data.resultStatus == 200) {
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$("#addTalent").hide();
					$('body').append($("#addTalent"));
					grid.refreshPage();
				}
	        }
	    })
	})
	
	
	$(".del").click(function(){
	    if (grid.getSelectedRow().length > 0) {
	    var genrer=grid.getRowRecord(grid.getSelectedRow()).genrer;
		if(genrer!="OFFLINE"){
	        $.yesorno("是否删除？",300,160,function() {
			var id = grid.getRowRecord(grid.getSelectedRow()).id;
			var editurl = "${pageContext.request.contextPath}/talent/delete.do";
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				    if (data.resultStatus == 200) {
				        $.threesecond("删除成功", 200, 100, 1000);
						grid.refreshPage();
					} 
				   
				},
				error : function(data) {
				}
			});
		
		}, function() {
			$.threesecond("看来还是手下留情了!",
					200, 100, 1000);
		});
		}else{
			$.threetop("不能删除线下星探!");
	        return false;	
		}	
		} else {
			$.threesecond("请先选择", 200, 100, 1000);
		}
	})
	
	  //快速搜索
    		$("#fast").change(function() {
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
			<span class="color99">星探管理</span>
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
			<table id="searchTable"class="table tablelist"  >
				<tr class="firsttr">
		        <th w_index="id" w_align="center"width="1%;"w_hidden="true">id</th>
		        <th w_num="line" w_align="center" width="1%;">序号</th>
		        <th w_index="number" w_align="center"width="1%;">员工号</th>
		        <th w_index="name" w_align="center"width="1%;">姓名</th>
		        <th w_index="aliasname" w_align="center"width="1%;">艺名</th>
		        <th w_index="sex" w_align="center"width="1%;">性别</th>
		        <th w_index="phone" w_align="center"width="1%;">联系电话</th> 
		        <th w_index="qq" w_align="center"width="1%;">QQ</th>
		        <th w_index="bankCard" w_align="left"width="1%;">工资卡帐号</th>
		        <th w_index="bankAddress" w_align="left"width="1%;">工资卡开户地址</th> 
		        <th w_edit="genrer" w_index="genrer" w_align="left"width="1%;">线上/线下</th> 
		        <th w_index="remarks"  w_align="center">备注</th> 
		   		</tr>
			</table>
		</div>
		<div id="addTalent" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table">
				<tr>
					<td style="text-align: right;">姓名：</td>
					<td><input type="text" name="name" id="name" style="width:200px;"> <span class="mustred">*</span></td>
					<td style="text-align: right;">艺名：</td>
					<td><input type="text" name="aliasname" id="aliasname" style="width:200px;"><span class="mustred">*</span> </td>
				</tr>
				<tr>
					<td style="text-align: right;">性别：</td>
					<td>
						<select  id="sex" name="sex" style="width:200px;">
							<option value="1">女</option>
							<option value="2">男</option>
						</select>
						<span class="mustred">*</span>
					</td>
					<td style="text-align: right;">联系电话：</td>
					<td><input type="text" name="phone" id="phone" style="width:200px;"  class="easyui-numberbox input"> <span class="mustred">*</span></td>
				</tr>
				<tr>
					<td style="text-align: right;">工资卡帐号：</td>
					<td><input type="text" name="bankCard" id="bankCard" style="width:200px;"> <span class="mustred">*</span></td>
					<td style="text-align: right;">工资卡开户地址：</td>
					<td><input type="text" name="bankAddress" id="bankAddress" style="width:200px;"><span class="mustred">*</span> </td>
				</tr>
				<tr>
					<td style="text-align: right;">QQ：</td>
					<td colspan=""><input type="text" name="qq" id="qq"  class="easyui-numberbox input w200"></td>
				</tr>
			</table>
	
			<p style="text-align:center">
				<input type="hidden" name="id" id="id">
				<input type="hidden" name="number" id="number">
				<span class="okok">提交</span>&nbsp;&nbsp;
				<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div>
	</div>

</body>
</html>
