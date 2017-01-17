<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>面试管理-未通过</title>
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
            url: "${pageContext.request.contextPath}/interview/readPages.do?date=${date}&type=3&branchs=${branchs}",
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
	    	            statusTracking:{
	    	                build: function (edit, value) {
    	                       return value;
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
        
   	 	//跟踪结果
		$(".control .edit").click(function() {
			if (grid.getSelectedRow().length > 0) {
			    var $chuangkou = $("#adjulook");
			    $.basewindow("编辑面试人员", $chuangkou, 490, 170);
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
			 	$("#adjulook  #id").val(id);
				$chuangkou.css({ "display" : "block" });
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
			}
		}) 
        
		$("#ok").click(function(){
		    var id = $("#adjulook #id").val();
		    var trackingResults = $("#adjulook #trackingResults").val();
		    if (!trackingResults) {
				$("#trackingResults").focus();
				$.threetop("请填写回访结果!");
				return false;
			}
		    var editurl = "${pageContext.request.contextPath}/interview/addTrackingResults.do?t="+ new Date().toTimeString();
		    $.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id+"&trackingResults="+trackingResults,
				show : "slide",
				success : function(data) {
				    if ("success" == data.resultMessage) {
					    $(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$('body').append($("#adjulook"));
						$("#adjulook").hide();
						grid.refreshPage();
					}
				},
				error : function(data) {
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
					<th w_index="introducer" w_align="center" width="1%;">介绍人</th>
					<th w_index="chTransition" w_align="center" width="1%;">平台交接人</th>
					<th w_edit="linkUrl" w_index="linkUrl" w_align="center" width="1%;">视频链接</th>
					<th w_edit="statusTracking"  w_index="statusTracking" w_align="left" width="4%;">状态跟踪</th>
					<th w_index="createDT" w_align="left" width="1%;">创建时间</th>
					<th w_index="trackingResults" w_align="left" width="1%;">回访结果</th>
					<th w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
			<div class="control">
				<input class="tabBtn edit" type="button" value="编辑">
			</div>
		</div>
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
		 <div id="adjulook" style="margin-left: 20px; margin-right: 20px; display:none;">
		  	<table class="table">
		  		<input type="hidden" id="id">
		  		<tr>
		  			<td style="text-align: right;padding-right: 40px;">回访结果</td>
		  			<td><input name="trackingResults" id="trackingResults" style="width:200px;"><span class="mustred">*</span></td>
		  		</tr>
		  	</table>
		  	<p style="text-align:center;">
		  		<span id="ok" class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
		  	</p>
		  </div>
	</div>
  </body>
</html>
