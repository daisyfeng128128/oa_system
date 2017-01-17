<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>职工转岗</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;
	overflow-y: scroller;
	position: relative;
}

.yirentable tr {
	height: 30px;
	line-height: 30px;
}

.mustred {margin-left: 10px;color: #ff0000;}

.yirentable tr:hover {
	background-color: #ddd;
}

.yirentable .firsttr:hover {
	background-color: #fff;
}

.yirentable .selected {
	background-color: #ddd;
}
.control{
	margin-left:-116px;
}
.search{
	width: 212px;
    height: 34px;
    border: 2px solid #0072ac;
    border-radius: 2px;
    position: relative;
}
</style>
<script type="text/javascript">
$(function () {
	if("${empTransfer}" =="UNKNOWN"){
        $(".add").show();
        $(".pass").show();
    }else{
        $(".add").hide();
        $(".pass").hide();
    }
    
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
					$(".right-twohalf .rspan ul").append("<li><a href='"+url+"?empTransfer=${empTransfer}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")   				
	   			});
	   			$(".right-twohalf .regspan").show();
   				$(".right-twohalf .rspan").show();
   			}
   		});
   		$(".right-twoRight .rspan>span").click(function(){
				if (!$(".right-twoRight .rspan ul").is(":animated")) {
					$(".right-twoRight .rspan ul").fadeToggle();
		       	}
		    });
		    
	var Main = {
		show:function(){
			$(".add").click(function () {
				$(".yirentable tr").not(".firsttr").remove();
				$.basewindow("转岗申请", $("#search"), 500, 390);
				$(".basewindow").addClass("custom-scroll");
			});
		},search:function(){
			$(".yirentable").delegate("tr:not('.firsttr')", "click", function () {
                var $self = $(this);
                if ($self.hasClass("selected")) {}else {
                    $self.siblings("tr").removeClass("selected");
                    $self.addClass("selected");
                }
            });
			$("#search #seachBtn").click(function(){
				var $self = $(this);
				if($self.prev("input").val() == ""){
					$.threetop("请输入员工号或真实姓名!");					
					return false;
				}
				var data = {
                    name: $self.prev("input").val(),
                    loadTable: "false",
                    seachEmp: "true"
                }
				$.ajax({
					type: "POST",
                    url: "${pageContext.request.contextPath}/user/search.do",
                    cache: false,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
				}).done(function (data) {
					$(".yirentable tr").not(".firsttr").remove();
					if(data.length > 0){
						$(data).each(function(index){
							$self = $(this)[0];
							if(index == 0){
								 $(".yirentable").append(
								 	"<tr class='selected'>"
	                            	+"<td hidden>" + $self.id + "</td>"
	                            	+"<td>" + ($self.num == null ? "" : $self.num) +"</td>"
	                            	+"<td>" + $self.name + "</td>"
	                            	+"<td>" + $self.aliasname + "</td>"
	                            	+"<td hidden>" + $self.depName + "</td>"
	                            	+"<td hidden>" + $self.posName + "</td>"
	                            	+"<td hidden>" + $self.employeType + "</td>"
	                            	+"</tr>");
							}else{
								$(".yirentable").append(
									"<tr>"
									+"<td hidden>" + $self.id + "</td>"
	                            	+"<td>" + ($self.num == null ? "" : $self.num) +"</td>"
	                            	+"<td>" + $self.name + "</td>"
	                            	+"<td>" + $self.aliasname + "</td>"
	                            	+"<td hidden>" + $self.depName + "</td>"
	                            	+"<td hidden>" + $self.posName + "</td>"
	                            	+"<td hidden>" + $self.employeType + "</td>"
	                            	+"</tr>");
							}
						});					
					}else{
						$.threetop("未搜索到结果");
					}
				}).error(function (xhr, status, error) { });				
			});
			$("#search .okok").click(function () {
						var id = $(".yirentable .selected td:eq(0)").text();
						var number = $(".yirentable .selected td:eq(1)").text();
				 		var name = $(".yirentable .selected td:eq(2)").text();
				 		var depName = $(".yirentable .selected td:eq(4)").text();
				 		var posName = $(".yirentable .selected td:eq(5)").text();
						if(id != null && id != ""){
							$(".zhezhao-basewindow").hide();
							$(".basewindow").hide();
							$("body").append($("#search"));
							$("#search").hide();
							$.basewindow("添加转岗申请", "#empTra", 760, 310);
							$("#empTra #id").text(id);
							$("#empTra #name").text(name);
							$("#empTra #num").text(number);
							$("#empTra #oldDepart").text(depName);
							$("#empTra #oldPosition").text(posName);
						}else{
							$.threetop("请选择员工进行转岗!");
						}
			});
		},addempTransfer:function(){
			$("#empTra .okok").click(function () {
                var $self = $(this);
                var id = $("#id").html();
                var employeType1 = $(".yirentable .selected td:eq(6)").text();
                if ($("#posid").combogrid("grid").datagrid("getSelected") != null) {
                	var posid = $("#posid").combogrid("grid").datagrid("getSelected").id;
				}else{
					$.threetop("职位不能为空!");
					return false;
				}
				 if ($("#depid").combogrid("grid").datagrid("getSelected") != null) {
					var depid = $("#depid").combogrid("grid").datagrid("getSelected").id;
				}else{
					$.threetop("部门不能为空!");
					return false;
				}
				if($("#empid").combogrid("grid").datagrid("getSelected") != null){
					var empid = $("#empid").combogrid("grid").datagrid("getSelected").id;
				}
				if($("#employeType").val() == "1" & employeType1 == "EMPLOYEE"){
					$.threetop("员工类型相同不可转!");
					return false;
				}else if($("#employeType").val() == "2" & employeType1 == "ACTORES"){
					$.threetop("员工类型相同不可转!");
					return false;
				}else if($("#employeType").val() == "3" & employeType1 == "TALENT"){
					$.threetop("员工类型相同不可转!");
					return false;
				}else if($("#employeType").val() == "4" & employeType1 == "MANAGER"){
					$.threetop("员工类型相同不可转!");
					return false;
				}else{
					var employeType = $("#employeType").val();
				}
				
            	var transferParames={
					"id":id,
					"posId":posid, 
					"depId":depid, 
					"empId":empid,
					"employeType":employeType 
				}
				$.ajax({
					type: "POST",
                    url: "${pageContext.request.contextPath}/empTransfer/transfer.do",
                    cache: false,
                    data: JSON.stringify(transferParames),
                    contentType: "application/json; charset=utf-8",
				}).done(function(data){
					if(data != null && data.resultStatus == 200){
						$.threesecond("申请转岗成功", 200, 100, 1000);
						$("body").append($("#empTra"));
                        $("#empTra").hide();
                   		$(".zhezhao-basewindow").hide();
		                $(".basewindow").hide();
		                grid.refreshPage();
					}else{
						 $.threetop("申请转岗失败");
					}
				}).error(function (xhr, status, error) {});
            });			
		},passed:function(){
			$(".control .pass").click(function(){
				var ids = grid.getCheckedValues("number");
				if(ids != ""){
					$.yesorno("确认通过转岗申请吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/empTransfer/passed.do"
						}).done(function(data){
							if(data.resultStatus == 200)
								$.threesecond("通过转岗申请", 200, 100, 1000);
							grid.refreshPage();
						}).error(function(qs,status,error){});				
					},function(){});
				}else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
			});			
		}
	};
	//
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/empTransfer/readPages.do?ads=${empTransfer}&branchs=${branchs}",
        pageSizeSelect: true,
        pageSize: pageSize,
        pagingToolbarAlign: "left",
        displayBlankRows: false,
        displayPagingToolbarOnlyMultiPages: true,
        extend: {
			settings:{
				supportGridEdit:true,
				supportGridEditTriggerEvent:"",
				gridEditConfigs:{
					empTransfer:{
						build:function(edit,value){
							if(value=="处理中")
								return "<span style='color:#0074ac'>"+value+"</span>"
							if(value=="完成转岗")
								return "<span style='color:#e35d00'>"+value+"</span>"
						}
					}
				}
			}        	
        }
    });
    Main.show();//弹窗
	Main.search();//搜索
	Main.addempTransfer();//添加转职申请人
	Main.passed();//立即通过	
});
</script>
<script>
$(function() {
	$("#depid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesParent.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "部门",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() { },
			down : function() { },
			enter : function() { },
			query : function(q) {
				if (q != null && q != "") {
					$("#depid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#depid").combogrid("setValue", q);
				}
			}
		}
		
	});

	$("#empid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "上级主管",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#empid").combogrid("grid").datagrid("reload", {"filter":q});
					$("#empid").combogrid("setValue", q);
				}
			}
		}
	});

	$("#posid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/emp/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "职位",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#posid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#posid").combogrid("setValue", q);
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
		<div class="right-twohalf">
			<span>首页</span> <span>></span> <span>人事系统</span> <span>></span> <span class="color99">员工转岗</span>
		</div>
		<div class="control">
			<jsp:include page="attsCommon.jsp"></jsp:include>
		</div>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="number" w_align="center" width="1%;">员工号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="newDepartName" w_align="center" width="1%;">新部门</th>
					<th w_index="newPositionName" w_align="center" width="1%;">新职位</th>
					<th w_index="supervisorName" w_align="center" width="1%;">直属上级</th>
					<th w_index="oldDepartName" w_align="center" width="1%;">原部门</th>
					<th w_index="oldPositionName" w_align="center" width="1%;">原职位</th>
					<th w_index="createName" w_align="center" width="1%;">创建人</th>
					<th w_edit="empTransfer" w_index="empTransfer" w_align="center" width="1%;">转岗状态</th>
					<th w_index="transferDate" w_align="center" width="1%;">转岗时间</th>
				</tr>
			</table>
		</div>
		<!-- 搜索 -->
		<div id="search" style="display:none;margin-left:20px;margin-right:20px;">
			<p>请输入员工号或真实姓名</p>
			<div class="search">
	    		<input id="nameOrNumber" type="text" />
	    		<button type="button" id="seachBtn" class="tableok" >查询</button>				
		    </div>
			<div class="custom-scroll" style="position:relative;margin-top:20px;height:150px;overflow:auto;padding:10px 20px;;border:1px solid #ddd;">
				<table style="width:100%" class="yirentable">
					<tr class="firsttr">
						<th hidden>id</th>
						<th width="33%">员工号</th>
						<th width="33%">真实姓名</th>
						<th width="34%">艺名</th>
					</tr>
				</table>
			</div>
			<p class="bCenter">
				<input type="hidden" name="id" id="id" value=""><span class="okok">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input"
					value="关闭">
			</p>
		</div>
		<div id="empTra" style="display:none;margin-left:20px;margin-right:20px;">
			<table class="table">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;" class="tableId"><input type="hidden"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">姓名：</td>
					<td class="name" id="name" style="text-align:left;"></td>
					<td style="text-align: right;">员工号：</td>
					<td class="num" id="num" style="text-align:left;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">原部门：</td>
					<td class="oldDepart" id="oldDepart" style="text-align:left;"></td>
					<td style="text-align: right;">原职位：</td>
					<td class="oldPosition" id="oldPosition" style="text-align:left;"></td>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">调整后部门：</label></td>
					<td><input id="depid" name="depid" style="width:200px;" class="depid"><span class="mustred">*</span></td>
					<td style="text-align: right;">调整后职位：</label></td>
					<td><input id="posid" name="posid" style="width:200px;" class="posid"><span class="mustred">*</span></td>
				</tr>
				<tr>
					<td style="text-align: right;">上级主管：</label></td>
					<td><input id="empid" name="empid" style="width:200px;" class="empid"></td>
					<td style="text-align: right;">员工属性：</td>
					<td><select id="employeType" name="employeType" style="width:200px;">
							<option value="1">普通员工</option>
							<option value="2">艺人</option>
							<option value="3">星探</option>
							<option value="4">管理</option>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right; display:none; ">id</td>
	                <td id="id" class="id" style="display:none; "></td>
				</tr>
			</table>
			<p style="text-align:center;">
				<span class="okok" type="button">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div>
	</div>
</body>
</html>
