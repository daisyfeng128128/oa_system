<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>预支薪资</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
body {height: 100%;min-width: 200px;overflow-x: hidden;overflow-y:scroller;position: relative;}
.yirentable tr {height: 30px;line-height: 30px;}
.yirentable tr:hover {background-color: #ddd;}
.yirentable .firsttr:hover {background-color: #fff;}
.yirentable .selected {background-color: #ddd;}
.right-twohalf{
	margin-top:0; 
}
.right-twohalf span{
	font-size:14px;
}
</style>
<script type="text/javascript">
$(function () {
    if("${advance}" =="UNKNOWN"){
        $(".add").show();
        $(".passed").show();
        $(".reject").show();
        $(".repay").hide();
    }else if("${advance}" =="PASSED"){
     	$(".repay").show();
        $(".add").hide();
        $(".passed").hide();
        $(".reject").hide();
    }else if("${advance}" =="FAILED"){
    	$(".repay").hide();
        $(".add").hide();
        $(".passed").hide();
        $(".reject").hide();
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
    
	var Main = {
		show:function(){
			$(".add").click(function () {
				$(".yirentable tr").not(".firsttr").remove();
				$.basewindow("借支申请", $("#search"), 500, 390);
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
			$("#search .next").click(function(){
				var $self = $(this);
				if($self.prev("input").val() == ""){
					$.threetop("请输入员工号或真实姓名!");					
					return false;
				}
				var data = {
					realname : $self.prev("input").val()
				}
				$.ajax({
					type: "POST",
                    url: "${pageContext.request.contextPath}/advance/search.do",
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
	                            	+"<td>" + ($self.number == null ? "" : $self.number) +"</td>"
	                            	+"<td>" + $self.realname + "</td>"
	                            	+"<td>" + $self.aliasname + "</td>"
	                            	+"<td hidden>" + $self.sex + "</td>"
	                            	+"<td hidden>" + $self.genre + "</td>"
	                            	+"<td hidden>" + $self.basicSalary + "</td>"
	                            	//+"<td hidden>" + $self.pushMoney + "</td>"
	                            	+"</tr>");
							}else{
								$(".yirentable").append(
									"<tr>"
									+"<td hidden>" + $self.id + "</td>"
	                            	+"<td>" + ($self.number == null ? "" : $self.number) +"</td>"
	                            	+"<td>" + $self.realname + "</td>"
	                            	+"<td>" + $self.aliasname + "</td>"
	                            	+"<td hidden>" + $self.sex + "</td>"
	                            	+"<td hidden>" + $self.genre + "</td>"
	                            	+"<td hidden>" + $self.basicSalary + "</td>"
	                            	//+"<td hidden>" + $self.pushMoney + "</td>"
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
				var realname = $(".yirentable .selected td:eq(2)").text();
				var aliasname = $(".yirentable .selected td:eq(3)").text();
				var sex = $(".yirentable .selected td:eq(4)").text();
				var genre = $(".yirentable .selected td:eq(5)").text();
				var basicSalary = $(".yirentable .selected td:eq(6)").text();
				if(id != null && id != ""){
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$("body").append($("#search"));
					$("#search").hide();
					$.basewindow("添加借支申请", "#adjusts", 760, 350);
					$("#adjusts .realname").text(realname);
					$("#adjusts .tableId input").val(id);
					$("#adjusts .aliasname").text(aliasname);
					$("#adjusts .sex").text(sex == "WOMAN" ? "女":"男");
					$("#adjusts .genre").text(genre == "OFFLINE"?"线下艺人":"线上艺人");
					$("#adjusts .sbasicSalary input").val(basicSalary);
					$("#adjusts .sbasicSalary input").attr({ "disabled": "disabled" });
				}else{
					$.threetop("请选择艺人进行借支申请!");
				}
			});
		},addAdjusts:function(){
			$("#adjusts .okok").click(function () {
                var $self = $(this);
                var data = {
                	id:$("#adjusts .tableId input").val(),
                	platName:$("#adjusts #platId").val(),
                	advanceSalary:$("#adjusts .advanceSalary input").val(),
                	sbasicSalary:$("#adjusts .sbasicSalary input").val(),
                }
				if(data.platName == ""){
					$.threetop("平台必须选择!");
					return false;
				}else if(data.advanceSalary == ""){
					$.threetop("借支金额不能为空!");
					return false;
				}else if(parseInt(data.advanceSalary) >parseInt(data.sbasicSalary)){
					$.threetop("借支金额不能大于底薪!");
					return false;
				}
				$.ajax({
					type: "POST",
                    url: "${pageContext.request.contextPath}/advance/save.do",
                    cache: false,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
				}).done(function(data){
					if(data != null && data.resultStatus == 200){
						$("body").append($("#adjusts"));
                        $("#adjusts").hide();
                   		$(".zhezhao-basewindow").hide();
		                $(".basewindow").hide();
		                grid.refreshPage();
					}else{
						 $.threetop("保存失败");
					}
				}).error(function (xhr, status, error) {});
            });			
		},passed:function(){
			$(".thistable .passed").click(function(){
				var ids = grid.getCheckedValues("id");
				if(ids != ""){
					$.yesorno("确认通过借支申请吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/advance/passed.do"
						}).done(function(data){
							if(data.resultStatus == 200)
								$.threesecond("通过审核成功", 200, 100, 1000);
							grid.refreshPage();
						}).error(function(qs,status,error){});				
					},function(){});
				}else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
			});			
		},reject:function(){
			$(".thistable .reject").click(function(){
				var ids = grid.getCheckedValues("id");
				if(ids != ""){
					$.yesorno("确认拒绝借支申请吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/advance/reject.do"
						}).done(function(data){
							if(data.resultStatus == 200)
								$.threesecond("拒绝审核成功", 200, 100, 1000);
							 grid.refreshPage();
						}).error(function(qs,status,error){});
					},function(){});
				}else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
			});
		},repay:function(){
			$(".thistable .repay").click(function(){
			if (grid.getSelectedRow().length > 0){
			var repay1 = grid.getRowRecord(grid.getSelectedRow()).repay;
			  if(repay1 =="已还款"){
					$.threetop("不能重复还款!");
					return false;
				}
			
			}
				var ids = grid.getCheckedValues("id");
				if(ids != ""){
					$.yesorno("确认还款吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/advance/repay.do"
						}).done(function(data){
							if(data.resultStatus == 200)
								$.threesecond("还款成功", 200, 100, 1000);
							 grid.refreshPage();
						}).error(function(qs,status,error){});
					},function(){});
				}else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
			});	
		}
	};
	
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/advance/readPages.do?ads=${advance}&branchs=${branchs}",
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
					advance:{
						build:function(edit,value){
							if(value=="待借")
								return "<span style='color:#0074ac'>"+value+"</span>"
							if(value=="拒绝")
								return "<span style='color:#e35d00'>"+value+"</span>"
						}
					},
					repay:{
						build:function(edit,value){
							if(value=="已还款")
								return "<span style='color:#0074ac'>"+value+"</span>"
							if(value=="未还款")
								return "<span style='color:#e35d00'>"+value+"</span>"
						}
					}
				}
			}        	
        }
    });
    
    Main.show();//弹窗
	Main.search();//搜索
	Main.addAdjusts();//添加借支申请人	
	Main.passed();//通过
	Main.reject();//拒绝
	Main.repay();//还款
});
  function KeyDown()
        {
          if (event.keyCode == 13)
          {
            event.returnValue=false;
            event.cancel = true;
            $(".basewindow .next").click();
          }
        }
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
			<span>日常办公</span> 
			<span>></span>
			<span class="color99">预支薪资</span>
		</div>
		<jsp:include page="advanceSalaryCommon.jsp"></jsp:include>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff;">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="realname" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="sex" w_align="center" width="1%;">性别</th>
					<th w_index="platName" w_align="center" width="1%;">所属平台</th>
					<th w_index="genre" w_align="center" width="1%;">艺人类型</th>
					<th w_index="sbasicSalary" w_align="center" width="1%;">底薪|保底</th>
					<th w_edit="advanceSalary" w_index="advanceSalary" w_align="center" width="1%;">借支金额</th>
					<th w_edit="advance" w_index="advance" w_align="center" width="1%;">审核状态</th>
					<th w_edit="repay" w_index="repay" w_align="center" width="1%;">还款状态</th>
					<th w_index="createDT" w_align="center" width="1%;">创建时间</th>
					<th w_index="passTime" w_align="center" width="1%;">通过时间</th>
					<th w_index="repayTime" w_align="center" width="1%;">还款时间</th>
				</tr>
			</table>
			<p style="text-align:center;position: fixed; left: 40%; bottom: 1%;"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
		</div>
		<!-- 搜索 -->
		<div id="search" style="display:none;margin-left:20px;margin-right:20px;">
	        <p>请输入员工号或真实姓名</p>
	        <p>	<input type="hidden" name="id" id="id">
	        	<input type="text" name="realname" id="realname"  onkeydown="KeyDown()" style="width:200px;" maxlength="400" class="input">
	            <span class="next" style="margin-left:20px;border: 1px solid #ddd;text-align: center;height: 30px;line-height: 30px;display: inline-block;color: #fff;background-color: #599eeb;width: auto;padding: 0px 20px;border-radius: 5px;margin-right: 10px;cursor: pointer;">搜索</span>
	        </p>
	        <div class="custom-scroll" style="position:relative;margin-top:20px;height:150px;overflow:auto;padding:10px 20px;;border:1px solid #ddd;">
	        	<table style="width:100%" class="yirentable" >
	            	<tr class="firsttr">
	                	<th hidden>id</th>
	               		<th width="33%">员工号</th>
	                	<th width="33%">真实姓名</th>
	                	<th width="34%">艺名</th>
	               </tr>
	           </table>
	        </div>
	        <p class="bCenter"><input type="hidden" name="id" id="id" value=""><span class="okok">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
	     </div>
	     <div id="adjusts" style="display:none;margin-left:20px;margin-right:20px;">
	            <table class="table">
	                <tr>
	                    <td colspan="2" style="width:50%;padding:0;border:none;" class="tableId"><input type="hidden"></td>
	                    <td colspan="2" style="width:50%;padding:0;border:none;"></td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">姓名：</td>
	                    <td class="realname"></td>
	                    <td style="text-align: right;">艺名：</td>
	                    <td class="aliasname"></td>
	                </tr>
	                <tr>
	                	<td style="text-align: right;">性别：</td>
	                    <td class="sex"></td>
	                    <td style="text-align: right;">平台：</td>
	                    <td>
	                        <select id="platId" name="platId"  style="width:100px;">
	                        	<c:forEach var="plat" items="${platList}">
	                        	<option value="${plat.opkey}">${plat.opName}</option>
								</c:forEach>
	                        </select>
	                    </td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">类型：</td>
	                    <td class="genre" colspan="3"></td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">底薪|保底：<label style="color: red;">*</label></td>
	                    <td class="sbasicSalary"><input type="text" style="width:100px;"></td>
	                     <td style="text-align: right;">借支金额: <label style="color: red;">*</label></td>
	                	<td class="advanceSalary"><input type="text" style="width:100px;"></td>
	                </tr>
	            </table>
	            <p style="text-align:center;"><span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
	        </div>
		</div>
  </body>
</html>
