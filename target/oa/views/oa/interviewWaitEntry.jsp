<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>面试管理-待入职</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
.kkkk{font-size:14px;color:#0099cc;text-decoration:underline;}
.kkkk:hover{font-size:14px;color:#cc9966;text-decoration:underline;}
.kkkk{text-decoration:underline;}
body {height: 100%;min-width: 200px;overflow-x: hidden;overflow-y:scroller;position: relative;}
.interLook{width:100%;height:80%}
.salarylook{width:100%;height:80%}
.mustred{margin-left:10px;color:#ff0000}
.yirentable tr {height: 30px;line-height: 30px;}
.yirentable tr:hover {background-color: #ddd;}
.yirentable .firsttr:hover {background-color: #fff;}
.yirentable .selected {background-color: #ddd;}
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
.right-twohalf{
	margin-top:0;
}
.right-twohalf span{
	font-size:14px;
}

.tableok{
	position:static;
}
.control{
	margin-left:-178px;
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
		
        $(".right-twohalf .riqi>span").click(function () {
    		if (!$(".right-twohalf .riqi ul").is(":animated")) {
    			$(".right-twohalf .riqi ul").fadeToggle();
           	}
        });
    	$("#excel").hide();
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/interview/readPages.do?date=${date}&type=2&branchs=${branchs}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            event: {
	            customCellEvents: {
	                click: function (record, rowIndex, colIndex, tdObj, trObj, options) {
	                    if(colIndex ==4 && record.photo != "" && record.photo != null ){
	                		var $chuangkou = $("#idlook");
	                		$("#idlook #yuanImage").attr("src",record.photo);
	                		$.basewindow("原图", $chuangkou, 700, 580);
	                		$chuangkou.css({
	                			"display" : "block"
	                		});
	                    }else if(colIndex==5 && record.videoPhoto != "" && record.videoPhoto != null){
	                        var $chuangkou = $("#idlook");
                			$("#idlook #yuanImage").attr("src",record.videoPhoto);
                			$.basewindow("原图", $chuangkou, 700, 580);
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
                        photo: {
    	                    build: function (edit, value) {
    	                        if(value!= "" && value != null)
    	                       		return '<input type="image" src='+ value +' width="80" height="80" />';
    	                    }
	    	            },
	    	            videoPhoto: {
    	                    build: function (edit, value) {
    	                       if(value!= "" && value != null)
    	                      	  return '<input type="image" src='+ value +' width="80" height="80" />';
    	                    }
	    	            },
	    	            linkUrl: {
                            build: function (edit, value) {
                                if(value != "http://" && value != "" && value != null){
                                    return  '<a  class="kkkk"   style="width:100px;" href="'+ value +'" target="view_window"  >查看视频</a>';
                                }else{
                                    return '<span style="color:#666666">查看视频<span>';
                                }
                            } 
                        }
                    } 
                }
            }
        });
		
		//删除
		$(".del").click(function() {
				if (grid.getSelectedRow().length > 0) {
						$.yesorno("是否删除？",300,160,function() {
							var id = grid.getRowRecord(grid.getSelectedRow()).id;
							var delUrl = "${pageContext.request.contextPath}/interview/delete.do?t="+ new Date().getTime();
							//后台删除
							$.ajax({
								type : "get",
								dataType : "json",
								url : delUrl,
								data : "id="+ id,//要发送的数据
								success : function(data) {
									if ("success" == data.resultMessage) {
									    $.threesecond("删除成功", 200, 100, 1000);
									    grid.refreshPage();
									}
								},
								error : function(data) {
								    
								}
							});
		
						});} else {
							$.threesecond("请先选择", 200, 100, 1000);
					}
				});
		
		//未入职
		$(".noEntry").click(function() {
				if (grid.getSelectedRow().length > 0) {
						$.yesorno("移入未通过列表后无法恢复，确认将艺人移入未通过列表？",300,160,function() {
							var id = grid.getRowRecord(grid.getSelectedRow()).id;
							var delUrl = "${pageContext.request.contextPath}/interview/noEntry.do?t="+ new Date().getTime();
							//后台设置未入职
							$.ajax({
								type : "get",
								dataType : "json",
								url : delUrl,
								data : "id="+ id,//要发送的数据
								success : function(data) {
									if ("success" == data.resultMessage) {
									    $.threesecond("设置成功", 200, 100, 1000);
									    grid.refreshPage();
									}
								},
								error : function(data) {
								    
								}
							});
		
						});} else {
							$.threesecond("请先选择", 200, 100, 1000);
					}
				});

	
		//线下艺人入职
		$(".entry").click(function() {
		    var $chuangkou = $("#empForm");
		    if (grid.getSelectedRow().length > 0) {
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
				var editurl = "${pageContext.request.contextPath}/interview/edit.do";
				$.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) {
					   
					    var $data =data.data;
						if ($data.sex == "WOMAN") {
							$("#empForm #sex").val("1").selected;
						} else if ($data.sex == "MAN") {
							$("#empForm #sex").val("2").selected;
						}
						
						
						
						$("#empForm #salary").numberbox("setValue", 3000);
						$("#empForm #probationSalary").numberbox("setValue", 0);
						$("#empForm #otherSubsidy").numberbox("setValue", 0);
						$("#empForm #introducer").val($data.introducer);
						$("#empForm #plat").val($data.plat);
						$("#empForm #interId").val($data.id);
						$("#empForm #name").val($data.name);
						$("#empForm #aliasname").val($data.aliasname);
						$("#empForm #phone").numberbox("setValue", $data.phone);
						$("#empForm #qq").numberbox("setValue",$data.qq);
					},
					error : function(data) {
					    
					}
					});
				 $.basewindow("线下入职", $chuangkou, 900, 850);
				 $("#actores")[0].checked = true;
				 $chuangkou.find(".ok").attr({"disabled" : "disabled"}).css({"cursor" : "not-allowed"})//初始确认建不能点击所以修改不需要这段 //改动6！！！
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
			}
		})
		
		//线下入职保存
		$("#empForm #ok").click(function() {
			$("#genrer").removeAttr("disabled");
			var vname = $("#name").val();
			var vnamepy = $("#namepy").val();
			var vposid = $("#posid").combogrid("getValue");
			var vbirth = $("#birth").combogrid("getValue");
			var vjoinDate = $("#joinDate").combogrid("getValue");
			var vregularDate = $("#regularDate").combogrid("getValue");
			var vidcard = $("#idcard").val();
			var vphone = $("#phone").val();
			var vsalary = $("#salary").val();
			var votherSubsidy = $("#otherSubsidy").val();
			var probationSalary = $("#probationSalary").val();
			var vliveAdress = $("#liveAdress").val();
			if (!vname) {
				$("#name").focus();
				$.threetop("姓名不能为空!");
				return false;
			}
			if (!vnamepy) {
				$("#namepy").focus();
				$.threetop("姓名拼音不能为空!");
				return false;
			}
			if (!vidcard) {
				$("#idcard").focus();
				$.threetop("身份证号不能为空!");
				return false;
			}
			if (!vphone) {
				$("#phone").focus();
				$.threetop("手机号不能为空!");
				return false;
			}
			if (!vsalary) {
				$("#salary").focus();
				$.threetop("薪资不能为空!");
				return false;
			}
			if (!vliveAdress) {
				$("#liveAdress").focus();
				$.threetop("住址不能为空!");
				return false;
			}
			if (!vbirth) {
				$.threetop("生日不能为空!");
				return false;
			}
			if (!vregularDate) {
				$("#regularDate").focus();
				$.threetop("转正日期不能为空!");
				return false;
			}
			if (!vposid) {
				$("#posid").focus();
				$.threetop("职位不能为空!");
				return false;
			}
			if (!vjoinDate) {
				$("#joinDate").focus();
				$.threetop("入职日期不能为空!");
				return false;
			}
			if (!votherSubsidy) {
				$("#otherSubsidy").focus();
				$.threetop("转正补贴不能为空!");
				return false;
			}
			if (!probationSalary) {
				$("#probationSalary").focus();
				$.threetop("试用补贴不能为空!");
				return false;
			}
			$("#empForm").ajaxSubmit(function(data) {
				if (data.resultStatus == 200) {
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$('body').append($("#empForm"));
					$("#empForm").hide();
					window.location.href="${pageContext.request.contextPath}/emp/show.do";
				} else {
					alert("wrong!");
				}
			})

		})
			
		$("#empForm #actores").change(function(){
		    var id = $("#empForm #id").val();
		    if(id==null || id==""){
		        if($("#empForm #actores")[0].checked ==true){
			        $("#sp").show();
			    }else{
			        $("#sp").hide();
			    }
		    }
		})
		
      });
	/** 验证纯数字 */
	function checkNum(data){
	    var reg = /^\d+(\.\d+)?$/; 	 
	    if(reg.test(data.value)==true){
	        $("#"+data.id).val( data.value);
	    }else{
	        $("#"+data.id).val(0);
	    }
	}
	
	</script>
	<script>
	$(function() {
		$("#depid")
				.combogrid(
						{
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
							columns : [ [ {
								field : "name",
								title : "部门",
								width : 120,
								sortable : true
							} ] ],
							keyHandler : {
								up : function() { },
								down : function() { },
								enter : function() { },
								query : function(q) {
									debugger;
									if (q != null && q != "") {
										$("#depid").combogrid("grid").datagrid(
												"reload", {
													"filter" : q
												});
										$("#depid").combogrid("setValue", q);
									}
								}
							}
						});
		$("#sdepid").combogrid({panelWidth : 500,
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
								up : function() {},
								down : function() {},
								enter : function() {},
								query : function(q) {
									debugger;
									if (q != null && q != "") {
										$("#depid").combogrid("grid").datagrid(
												"reload", {
													"filter" : q
												});
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
							columns : [ [ {
								field : "name",
								title : "上级主管",
								width : 120,
								sortable : true
							} ] ],
							keyHandler : {up : function() {},down : function() {
								},enter : function() {},
								query : function(q) {
									if (q != null && q != "") {
										$("#empid").combogrid("grid").datagrid(
												"reload", {
													"filter" : q
												});
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
			columns : [ [ {
				field : "name",
				title : "职位",
				width : 120,
				sortable : true
			} ] ],
			keyHandler : {
				up : function() {
				},
				down : function() {
				},
				enter : function() {
				},
				query : function(q) {
					if (q != null && q != "") {
						$("#posid").combogrid("grid").datagrid("reload", {
							"filter" : q
						});
						$("#posid").combogrid("setValue", q);
					}
				}
			}
		});
		$("#sposid").combogrid({
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
			columns : [ [ {
				field : "name",
				title : "职位",
				width : 120,
				sortable : true
			} ] ],
			keyHandler : {
				up : function() {},
				down : function() {},
				enter : function() {},
				query : function(q) {
					if (q != null && q != "") {
						$("#posid").combogrid("grid").datagrid("reload", {
							"filter" : q
						});
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
		<div class="right-twoTit">
		<span>首页</span> 
		<span>></span>
		<span>平台人员</span> 
		<span>></span>
		<span class="color99">面试管理</span>
   	</div>
    		
	<jsp:include page="interviewCommon.jsp"></jsp:include>
	<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
		<table id="searchTable" class="table tablelist">
			<tr class="firsttr">
				<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
				<th w_num="line" w_align="center" width="1%;">序号</th>
				<th w_index="name" w_align="center" width="1%;">姓名</th>
				<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
				<th w_edit="photo" w_index="photo" w_align="center" width="1%;">艺术照</th>
				<th w_edit="videoPhoto" w_index="videoPhoto" w_align="center" width="1%;">视频照</th>
				<th w_index="sex" w_align="center" width="1%;">性别</th>
				<th w_index="phone" w_align="center" width="1%;">手机号</th>
				<th w_index="qq" w_align="center" width="1%;">QQ</th>
				<th w_index="genre" w_align="center" width="1%;">艺人类型</th>
				<th w_index="plat" w_align="center" width="1%;">试播平台</th>
				<th w_index="launchTime" w_align="center" width="1%;">开播时间</th>
				<th w_index="introducer" w_align="center" width="1%;">介绍人</th>
				<th w_index="chTransition" w_align="center" width="1%;">平台交接人</th>
				<th w_edit="linkUrl" w_index="linkUrl" w_align="center" width="1%;">视频链接</th>
				<th w_index="createDT" w_align="left" width="1%;">创建时间</th>
				<th w_index="remark" w_align="center">备注</th>
			</tr>
		</table>
		
		<div class="control">
			<input class="tabBtn del" type="button" value="删除">
			<input class="tabBtn entry" type="button" value="入职">
			<input class="tabBtn noEntry" type="button" value="未入职">
		</div>
	</div>
	<form action="${pageContext.request.contextPath}/interview/save.do" method="post" enctype="multipart/form-data" class="salaryLook" id="salaryLook" style="display:none;margin-left:0px;margin-right:20px;">
		<table class="table" id="table"> 
		<input type="hidden" id="id" name="id">
			<tr>
				<td style="text-align: right;padding-right: 40px;">姓名</td><td><input type="text" name="name" id="name" style="width:200px;"> <span class="mustred">*</span></td>
				<td style="text-align: right; padding-right: 40px;">艺名</td><td><input type="text" name="aliasname" id="aliasname" style="width:200px;"></td>
			</tr>
			<tr>
				<td style="text-align: right;padding-right: 40px;">性别</td>
				<td>
				 	<select id="sex"  name="sex" style="width:200px"> 
				 		<option value="MAN">女</option>
				 		<option value="WOMAN">男</option>
				 	</select><span class="mustred">*</span>
				</td>
				<td style="text-align: right;padding-right: 40px;">试播平台</td><td><input name="plat" id="plat" style="width:200px;"><span class="mustred">*</span></td>
			</tr>
			<tr>
				<td style="text-align: right;padding-right: 40px;">手机号</td><td><input type="text" name="phone" id="phone" style="width:200px;"></td>
				<td style="text-align: right; padding-right: 40px;">QQ</td><td><input type="text" name="qq" id="qq" style="width:200px;"></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 40px;">介绍人</td><td><input type="text" name="introducer" id="introducer" style="width:200px;"></td>
				<td style="text-align: right; padding-right: 40px;">频道交接人</td><td><input type="text" name="chTransition" id="chTransition" style="width:200px;"> <span class="mustred">*</span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 40px;">艺人类别</td>
				<td>
					<select id="genre" name="genre" style="width:200px;">
						<option value="">请选择</option>
						<option value="ONLINE" >线上</option>
						<option value="OFFLINE">线下</option>
					</select> 
					<span class="mustred">*</span>
				</td>
				<td style="text-align: right; padding-right: 40px;">视频连接</td><td><input type="text" name="linkUrl" id="linkUrl" style="width:200px;"></td>
			</tr>
			<tr>
				<td style="text-align: right; height: 50px ;padding-right: 40px;"  >艺人照片</td><td><input type="file" name="photo" id="photo" style="width:200px;"></td>
				<td style="text-align: right; height: 50px;padding-right: 40px;" >艺人视频照</td><td><input type="file" name="videoPhoto" id="videoPhoto" style="width:200px;"  ></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 40px;" >备注</td><td colspan="3"><input type="text" name="remark" id="remark" style="width:600px;"></td>
			</tr>
		</table> 
		<div class="clear"></div>
		<p style="text-align:center;">
				<button type="button" id="addInter" class="tableok">保存</button>
	 			<button type="button" class="tableok cancel">关闭</button>
		</p>
	</form>
	
	<form action="${pageContext.request.contextPath}/interview/save.do" method="post" enctype="multipart/form-data" class="interLook" id="interLook" style="display:none;margin-left:0px;margin-right:20px;">
		<table class="table" id="table"> 
		<input type="hidden" id="id" name="id">
			<tr>
				<td style="text-align: right; padding-right: 40px;">姓名</td><td name="name" id="name"></td>
				<td style="text-align: right; padding-right: 40px; ">艺名</td><td><input type="text" name="aliasname" id="aliasname" style="width:200px;"></td>
			</tr>
			<tr>
				<td style="text-align: right;padding-right: 40px; ">性别</td>
				<td>
				 	<select id="sex"  name="sex" style="width:200px"> 
				 		<option value="MAN">女</option>
				 		<option value="WOMAN">男</option>
				 	</select><span class="mustred">*</span>
				</td>
				<td style="text-align: right; padding-right: 40px; ">试播平台</td><td  name="plat" id="plat" ></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 40px;">手机号</td><td><input type="text" name="phone" id="phone" style="width:200px;"></td>
				<td style="text-align: right; padding-right: 40px; ">QQ</td><td><input type="text" name="qq" id="qq" style="width:200px;"></td>
			</tr>
			<tr>
				<td style="text-align: right;padding-right: 40px;">介绍人</td><td><input type="text" name="introducer" id="introducer" style="width:200px;"></td>
				<td style="text-align: right; padding-right: 40px; ">频道交接人</td><td><input type="text" name="chTransition" id="chTransition" style="width:200px;"> <span class="mustred">*</span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 40px;">艺人类别</td>
				<td>
					<select id="genre" name="genre" style="width:200px;">
						<option value="">请选择</option>
						<option value="ONLINE" >线上</option>
						<option value="OFFLINE">线下</option>
					</select> 
					<span class="mustred">*</span>
				</td>
				<td style="text-align: right; padding-right: 40px; ">视频连接</td><td><input type="text" name="linkUrl" id="linkUrl" style="width:200px;"></td>
			</tr>
			<tr>
				<td style="text-align: right; height: 50px;padding-right: 40px;"  >艺人照片</td><td><input type="file" name="photo" id="photo" style="width:200px;"></td>
				<td style="text-align: right; height: 50px;padding-right: 40px; " >艺人视频照</td><td><input type="file" name="videoPhoto" id="videoPhoto" style="width:200px;"  ></td>
			</tr>
			<tr>
				<td style="text-align: right;padding-right: 40px;" >备注</td><td colspan="3"><input type="text" name="remark" id="remark" style="width:600px;"></td>
			</tr>
		</table> 
		<div class="clear"></div>
		<p style="text-align:center;">
				<button type="button" id="okok" class="tableok">保存</button>
	 			<button type="button" class="tableok cancel">关闭</button>
		</p>
	</form>
	 <div id="idlook" style="margin-left: 20px; margin-right: 20px; display:none;">
		<table class="table2">
			<tr>
				<td >
					<img  width='600'  id="yuanImage"/>
				</td>
			</tr>
		</table>
		<p style="text-align:center">
			<input class="cancel" type="reset" name="Input" value="关闭">
		</p>
	</div> 
	  <form action="${pageContext.request.contextPath}/interview/empsave.do" method="post" enctype="multipart/form-data" id="empForm" style="display:none;margin-left:20px;margin-right:20px;">

			<table class="table">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
				<tr>
					<td colspan="4"><p style=" margin: 0">基本资料</p>
					</td>
				</tr>
				<td style="text-align: right;">姓名：</td>
				<td><input type="text" name="name" id="name" style="width:200px;"> <span class="mustred">*</span>
				</td>
				<td style="text-align: right;">姓名拼音：</td>
				<td><input type="text" name="namepy" id="namepy" style="width:200px;"> <span class="mustred">*</span>
				</td>

				</tr>
				<tr>
					<td style="text-align: right;">性别：</td>
					<td><select id="sex" name="sex" style="width:200px;">
							<option value="1">女</option>
							<option value="2">男</option>
					</select> <span style="color:#e35d00;margin-left:10px">*</span></td>

					<td style="text-align: right;">艺名：</td>
					<td><input type="text" name="aliasname" id="aliasname" style="width:200px;">
					</td>

				</tr>
				<tr>
					<td style="text-align: right;">身份证号：</td>
					<td><input type="text" name="idcard" id="idcard" style="width:200px;"> <span class="mustred">*</span>
					</td>
					<td style="text-align: right;">生日：</td>
					<td><input class="easyui-datebox" editable="false" name="birth" id="birth" style="width:200px;"> <span style="margin-left:10px;color:#e35d00">*</span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">联系电话：</td>
					<td><input type="text" name="phone" id="phone" style="width:200px;" class="easyui-numberbox input"> <span class="mustred">*</span>
					</td>
					<td style="text-align: right;">QQ：</td>
					<td><input type="text" style="width:200px;" name="qq" id="qq" class="easyui-numberbox input">
					</td>
				</tr>

				<tr>
					<td style="text-align: right;">入职介绍人：</td>
					<td><input type="text" name="introducer" id="introducer" style="width:200px">
					</td>
					<td style="text-align: right;">户籍所在地：</td>
					<td><input type="text" name="hostRegister" id="hostRegister" style="width:200px;">
					</td>
				</tr>

				<tr>
					<td style="text-align: right;">紧急联系人：</td>
					<td><input type="text" name="emergencyContact" id="emergencyContact" style="width:200px;">
					</td>
					<td style="text-align: right;">紧急联系人电话 ：</td>
					<td><input type="text" name="emergencyPhone" id="emergencyPhone" style="width:200px;">
					</td>
				</tr>

				<tr>
					<td style="text-align: right;">户口类型：</td>
					<td><select id="account" name="account" style="width:200px;">
							<option value="CHENGZHEN">城镇</option>
							<option value="NONGCUN">农村</option>
					</select></td>
					<td style="text-align: right;">电子邮件：</td>
					<td><input type="text" name="email" id="email" style="width:200px;">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">现居地址：</td>
					<td colspan="3"><input type="text" name="liveAdress" id="liveAdress" style="width:600px;"> <span class="mustred">*</span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">户籍地址 ：</td>
					<td colspan="3"><input type="text" name="hostAddress" id="hostAddress" style="width:600px;">
					</td>
				</tr>
				<tr>
					<td colspan="4"><p style=" margin: 0">职位资料</p>
					</td>
				</tr>


				<tr>
					<td style="text-align: right;">部门：</label>
					</td>
					<td><input id="depid" name="depid" style="width:200px;"> <span class="mustred">*</span>
					</td>
					<td style="text-align: right;">职位：</label>
					</td>
					<td><input id="posid" name="posid" style="width:200px;"> <span class="mustred">*</span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">入职日期：</td>
					<td><input class="easyui-datebox" editable="false" type="text" name="joinDate" id="joinDate" style="width:200px;"> <span class="mustred">*</span>
					</td>
					<td style="text-align: right;">上级主管：</label>
					</td>
					<td><input id="empid" name="empid" style="width:200px;">
					</td>

				</tr>
				<tr>
					<td style="text-align: right;">状态：</td>
					<td><select id="genrer" name="genrer" style="width:200px;">
							<option value="1">试用</option>
							<option value="2">正式</option>
							<option value="3">待离职</option>
					</select><span class="mustred">*</span></td>
					<td style="text-align: right;">员工属性：</td>
					<td colspan="2">
						<table style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;">
							<tr>
								<td style="border-right:0px solid #ddd;border-bottom:0px solid #ddd;text-align: right;">艺人：</td>
								<td style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;"><input type="checkbox" id="actores" name="actores" value="1">
							</td>
								<td style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd; text-align: right;padding-left:24px;">交金：</td>
								<td style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;"><input type="checkbox" id="jiaojin" name="jiaojin" syle="color: #333" value="1"></td>
								<td style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd; text-align: right;padding-left:24px;"><span id="sp"><input type="text" id="pushMoney" style="width:30px" name="pushMoney" onblur="checkNum(this)">%</span></td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td style="text-align: right;">转正日期：</td>
					<td><input type="text" class="easyui-datebox" editable="false" name="regularDate" id="regularDate" style="width:200px;"> <span class="mustred">*</span>
					</td>
					<td style="text-align: right;">基本工资：</td>
					<td><input type="text" style="width:200px;" class="easyui-numberbox input" name="salary" id="salary"><span class="mustred">*</span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">试用补贴：</td>
					<td><input type="text" style="width:200px;" class="easyui-numberbox input" name="probationSalary" id="probationSalary"><span class="mustred">*</span>
					</td>
					<td style="text-align: right;">转正补贴：</td>
					<td><input type="text" style="width:200px;" class="easyui-numberbox input" name="otherSubsidy" id="otherSubsidy"><span class="mustred">*</span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">社保基数：</td>
					<td><input type="text" style="width:200px;" class="easyui-numberbox input" name="SScardinalNumber" id="SScardinalNumber"> 
					</td>
					<td style="text-align: right;">公积金基数：</td>
					<td><input type="text" style="width:200px;" class="easyui-numberbox input" name="PFcardinalNumber" id="PFcardinalNumber"> 
					</td>
				</tr>
				
				<tr>
					<!-- <td style="text-align: right;">底薪(艺人)：</td>
					<td><input type="text"  style="width:200px;" name="basicSalary" id="basicSalary" ></td>   -->
					<td style="text-align: right;">劳动合同期限：</td>
					<td><input class="easyui-datebox" editable="false" name="contractDT" id="contractDT" type="text" style="width:200px;">
					</td>
					<td style="text-align: right;">公积金帐号 ：</td>
					<td><input type="text" style="width:200px;" name="cpfAccounts" id="cpfAccounts">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">工资卡帐号：</td>
					<td><input type="text" style="width:200px;" name="bankCard" id="bankCard">
					</td>
					<td style="text-align: right;">工资卡银行开户地址：</td>
					<td><input type="text" style="width:200px;" name="bankAddress" id="bankAddress">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">分成账号(艺人)：</td>
					<td><input type="text" style="width:200px;" name="shareBankCard" id="shareBankCard">
					</td>
					<td style="text-align: right;">分成账户开户行：</td>
					<td><input type="text" style="width:200px;" name="shareBankAddress" id="shareBankAddress">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">技能：</td>
					<td colspan="3"><input type="text" style="width:600px;" name="speciality" id="speciality">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">备注：</td>
					<td colspan="3"><input type="text" name="remarks" id="remarks" style="width:600px;">
					</td>
				</tr>

			</table>
			<p>
				<input type= "hidden" name="plat" id="plat"><input type="hidden" name="interId" id="interId"><input type="hidden" name="id" id="id"> <span id="ok" class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</form>
	</div>
	  
	  
  </body>
</html>
