<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>转正申请</title>
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

.control .regspan {
	float: right;
	font-family: 'Microsoft YaHei';
	margin-right: 130px;
	font-weight: normal;
	margin-top: 3px;
	display: none;
}

.control .rspan {
	cursor: pointer;
	float: right;
	height: 27px;
	margin-right: -190px;
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
	color: black;
	text-decoration: none;
}

.control .rspan li a {
	color: black;
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
    		url: "${pageContext.request.contextPath}/positiveReview/readPagesActores.do?branchs=${branchs}&type=${empType}",
    		pageSizeSelect: true, 
    		pagingToolbarAlign: "left" ,
    		displayBlankRows: false, 
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
						    $.basewindow("延长试用期原因", $chuangkou, 500, 350);
						    $(".basewindow").addClass("custom-scroll");
	                		//$("#applies_look #apply_lo").html(record.reason);
	                		
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
                    	isExtend: {
                            build: function (edit, value) {
                                  if(value ==0){
                                    return '<span style="color:#0074ac">是<span>';
                                }else if (value==1){
                                    return '<span>否<span>';
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
    	//延长试用期
    	$(".ext").click(function(){
    		if(grid.getSelectedRow().length>0){
	    		var id = grid.getRowRecord(grid.getSelectedRow()).id;
	          	var $chuangkou = $("#applies");
			    $.basewindow("延长试用期", $chuangkou, 500, 300);
			    $("#applies #id").val(id);
			    $("#applies #type").val(2);
	    	}else{
	    		$.threesecond("请先选择", 200, 100, 1000);
	    	}
    	})
    	//转正申请
    $(".control .app").click(function(){
		if(grid.getSelectedRow().length>0){
	    		var id = grid.getRowRecord(grid.getSelectedRow()).id;
	          	var $chuangkou = $("#applies");
			    $.basewindow("提交转正申请", $chuangkou, 500, 350);
			    $("#applies #id").val(id);
			     $("#applies #type").val(1);
    	}else{
    		$.threesecond("请先选择", 200, 100, 1000);
    	}
    });
    $("#applies .okok").click(function(){
    	var id = $("#applies #id").val();
    	var apply = $("#applies #apply").val();
    	var type = $("#applies #type").val();
    	var editurl = "${pageContext.request.contextPath}/positiveReview/apply.do";
   		$.ajax({
	          type: "post",
	          dataType: "json",
	          url: editurl,
	          data: "id="+id+"&empType=${empType}&apply="+apply+"&type="+type,
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
	<jsp:include page="positiveReviewCommon.jsp"></jsp:include>
	<div class="rightPadd">
		<div class="right-twohalf">
			<span>首页</span> <span>></span> <span>平台人员</span> <span>></span> <span class="color99">转正申请</span>
		</div>

		<div class="right-two clearFix">
			<a href="${pageContext.request.contextPath}/positiveReview/show.do?empType=1"><span class="spanchange tBtn">艺人</span></a> <a
				href="${pageContext.request.contextPath}/positiveReview/show.do?empType=2"><span class="tBtn">管理</span></a>
			<div class="rspan" style=" height: 30px;display: none; float:right; ">
				<span>${branchsMsg}</span>
				<ul></ul>
			</div>
			<span class="regspan" style=" display: none; float:right; ">查看分公司：</span>
		</div>


		<div class="custom-scroll thistable" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="number" w_align="center" width="1%;">员工号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="nickName" w_align="center" width="1%;">艺名</th>
					<th w_index="joinDate" w_align="center" width="1%;">入职日期</th>
					<th w_index="platName" w_align="center" width="1%;">所属平台</th>
					<th w_edit="isExtend" w_index="isExtend" w_align="center" width="1%;">是否延长试用期</th>
					<th w_edit="reason" w_index="reason" w_align="center" width="1%;">延长试用期原因</th>
					<th w_index="exAppPer" w_align="center" width="1%;">审批人</th>
					<th w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
		</div>
		<p class="control">
			<jsp:include page="attsTabCommon.jsp"></jsp:include>
		</p>
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
		<div id="applies_look" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table" id="apply_lo">
				<input type="hidden" id="id">
				<input type="hidden" id="type">
			</table>
			<p style="text-align:center;">
				<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div>
	</div>
</body>
</html>
