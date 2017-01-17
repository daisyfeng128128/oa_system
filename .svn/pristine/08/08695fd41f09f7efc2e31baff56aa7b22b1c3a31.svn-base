<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>面试管理-初试</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
.kkkk{font-size:14px;color:#0099cc;text-decoration:underline;}
.kkkk:hover{font-size:14px;color:#cc9966;text-decoration:underline;}
.kkkk{text-decoration:underline;}
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;overflow-y:scroller;
	position: relative;
}

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
	font-size:14px
}
.tableok{
	position:static;
	margin-right:6px; 
	display:inline-block;
}
.control{
	margin-left:-168px;
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
            url: "${pageContext.request.contextPath}/interview/readPages.do?date=${date}&type=5&branchs=${branchs}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            event: {
	            customCellEvents: {
	                click: function (record, rowIndex, colIndex, tdObj, trObj, options) {
	                    if(colIndex ==4 && record.photo != "" && record.photo != null){
	                		var $chuangkou = $("#idlook");
	                		$("#idlook #yuanImage").attr("src",record.photo);
	                		$.basewindow("原图", $chuangkou, 700, 580);
	                		$chuangkou.css({
	                			"display" : "block"
	                		});
	                    }else if(colIndex==5  && record.videoPhoto != null && record.videoPhoto != ""){
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
	    	            } ,
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
        $("#addInter").click(function() {
			var name = $("#name").val();
			var genre = $("#genre").val();
			if(genre == ""){
			    $("#genre").focus();
			    $.threetop("请选择艺人类别!");
			    return false;
			}
			if (!name) {
				$("#name").focus();
				$.threetop("请填写姓名!");
				return false;
			}
			 
			$("#salaryLook").ajaxSubmit(function(data) {
				if (data.resultStatus == 200) {
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$('body').append($("#salaryLook"));
					$("#salaryLook").hide();
					grid.refreshPage();
				} 
			})
		})
    	//未通过
		$(".del").click(function() {
				if (grid.getSelectedRow().length > 0) {
						$.yesorno("移入未通过列表后无法恢复，确认将艺人移入未通过列表？",300,160,function() {
							var id = grid.getRowRecord(grid.getSelectedRow()).id;
							var delUrl = "${pageContext.request.contextPath}/interview/noPassPre.do?t="+ new Date().getTime();
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
		//新增
		$(".control .addN").click(function() {
			var $chuangkou = $("#salaryLook");
			$.basewindow("新增面试人员", $chuangkou, 800, 400);
			$(".basewindow").addClass("custom-scroll");
			$("#linkUrl").val("http://");
		})
    	
    	//初试通过
    	$(".add").click(function(){
    		if (grid.getSelectedRow().length > 0) {
    		    var id = grid.getRowRecord(grid.getSelectedRow()).id;
    		    var $chuangkou = $("#passTable");
    			$.basewindow("初试通过", $chuangkou, 400, 215);
    			$("#passTable #id").val(id);
    		} else {
				$.threesecond("请先选择", 200, 100, 1000);
    		}
    	})
    	
    	
    	$("#passTable #pass").click(function(){
    	    var chTransition = $("#passTable #chTransition").val();
    	    var plat = $("#passTable #plat").combogrid("getValue");
    	    var id = $("#passTable #id").val();
    	    if(plat == "" || plat==null){
			    $("#passTable #plat").focus();
			    $.threetop("请选择交接平台!");
			    return false;
			}
    	    if(chTransition == "" || chTransition== null){
			    $("#passTable #chTransition").focus();
			    $.threetop("请填写平台接受人!");
			    return false;
			} 
    	    var  addUrl="${pageContext.request.contextPath}/interview/addPrePass.do?t="+ new Date().getTime();
    	    $.ajax({
				type : "post",
				dataType : "json",
				url : addUrl,
				data : "id="+ id+"&chTransition="+chTransition+"&plat="+plat,//要发送的数据
				success : function(data) {
					if ("success" == data.resultMessage) {
					    $.threesecond("设置成功", 200, 100, 1000);
					    $(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$('body').append($("#passTable"));
						$("#passTable").hide();
					    grid.refreshPage();
					}
				},
				error : function(data) {
				    
				}
			});
    	})
		
		$("#plat").combogrid({
			panelWidth : 500,
			method : "POST",
			datatype : "json",
			url : "${pageContext.request.contextPath}/interview/readPagesSkip.do",
			mode : "remote",
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			idField : "id",
			textField : "platName",
			pageSize : 30,
			pageList : [ 5, 10, 20, 30, 40, 50 ],
			columns : [ [ {
				field : "platName",
				title : "平台",
				width : 120,
				sortable : true
			} ] ],
			keyHandler : {
				up : function() { },
				down : function() { },
				enter : function() { },
				query : function(q) {
					if (q != null && q != "") {
						$("#plat").combogrid("grid").datagrid("reload", {"filter" : q});
						$("#plat").combogrid("setValue", q);
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
					<th w_index="introducer" w_align="center" width="1%;">介绍人</th>
					<th w_edit="linkUrl" w_index="linkUrl" w_align="center" width="1%;">视频链接</th>
					<th w_index="createDT" w_align="left" width="1%;">创建时间</th>
					<th w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
			<div>
				</br>
				</br>
				<div class="control">
					<input class="tabBtn addN" type="button" value="新增">
					<input class="tabBtn add" type="button" value="初试通过">
					<input class="tabBtn del" type="button" value="未通过">
				</div>
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
					 		<option value="MAN">男</option>
					 		<option value="WOMAN">女</option>
					 	</select><span class="mustred">*</span>
					</td>
					<td style="text-align: right; padding-right: 40px;">介绍人</td><td><input type="text" name="introducer" id="introducer" style="width:200px;"></td>
				</tr>
				<tr>
					<td style="text-align: right;padding-right: 40px;">手机号</td><td><input type="text" name="phone" id="phone" class="w200"></td>
					<td style="text-align: right; padding-right: 40px;">QQ</td><td><input type="text" name="qq" id="qq" class="w200"></td>
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
					<td style="text-align: right; height: 50px ;padding-right: 40px;"  >艺人照片</td><td><input type="file" name="photo" id="photo" class="w200"></td>
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
		
		 <div id="idlook" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table2">
				<tr>
					<td><img  width='600'  id="yuanImage"/></td>
				</tr>
			</table>
			<p style="text-align:center"><input class="cancel" type="reset" name="Input" value="关闭"></p>
		</div> 
		<div id="passTable" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table">
				<tr>
					<td>面试平台</td><td><input id="plat" name="plat" style="width:200px"/> <span class="mustred">*</span></td>
				</tr>
				<tr>
					<td>平台接收人</td><td><input type="text" style="width:200px" id="chTransition" name="chTransition"/>  <span class="mustred">*</span></td>
				</tr>
			</table>
			<input type="hidden" id="id">
			<p style="text-align:center"><button type="button" id="pass" class="tableok">通过</button>&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
		</div> 
	</div>
	
  </body>
</html>
