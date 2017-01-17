<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>薪资调整</title>
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
</style>
<script type="text/javascript">
$(function () {
    if("${adjust}" =="UNKNOWN"){
        $(".add").show();
        $(".del").show();
        $(".passed").show();
        $(".reject").show();
    }else{
        $(".add").hide();
        $(".del").hide();
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
				$.basewindow("调薪申请", $("#search"), 500, 390);
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
                    url: "${pageContext.request.contextPath}/adjusts/search.do",
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
	                            	+"<td hidden>" + $self.pushMoney + "</td>"
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
	                            	+"<td hidden>" + $self.pushMoney + "</td>"
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
					$.ajax({
					type: "POST",
                    url: "${pageContext.request.contextPath}/adjusts/check.do",
                    cache: false,
                    data: "id="+id
				}).done(function (data) {
				 	if(data != null && (data.resultStatus == 101|| data.resultStatus==100 )){
				 		$.threetop("报表审核中,不可调薪");
				 		return;
				 	}else{
				 		var realname = $(".yirentable .selected td:eq(2)").text();
						var aliasname = $(".yirentable .selected td:eq(3)").text();
						var sex = $(".yirentable .selected td:eq(4)").text();
						var genre = $(".yirentable .selected td:eq(5)").text();
						var basicSalary = $(".yirentable .selected td:eq(6)").text();
						var pushMoney = $(".yirentable .selected td:eq(7)").text();
						if(id != null && id != ""){
							$(".zhezhao-basewindow").hide();
							$(".basewindow").hide();
							$("body").append($("#search"));
							$("#search").hide();
							$.basewindow("添加调薪申请", "#adjusts", 760, 350);
							$("#adjusts .realname").text(realname);
							$("#adjusts .tableId input").val(id);
							$("#adjusts .aliasname").text(aliasname);
							$("#adjusts .sex").text(sex == "WOMAN" ? "女":"男");
							$("#adjusts .genre").text(genre == "OFFLINE"?"线下艺人":"线上艺人");
							$("#adjusts .sbasicSalary input").val(basicSalary);
							$("#adjusts .spushMoney input").val(pushMoney);
							$("#adjusts .sbasicSalary input").attr({ "disabled": "disabled" });
							$("#adjusts .spushMoney input").attr({ "disabled": "disabled" });
						}else{
							$.threetop("请选择艺人进行调薪申请!");
						}
				 	}
				}).error(function (xhr, status, error) {
					$.threetop("报表审核中,不可调薪");
					return;
				 });	
			});
		},addAdjusts:function(){
			$("#adjusts .okok").click(function () {
                var $self = $(this);
                var data = {
                	id:$("#adjusts .tableId input").val(),
                	platName:$("#adjusts #platId").val(),
                	nbasicSalary:$("#adjusts .nbasicSalary input").val(),
                	npushMoney:$("#adjusts .npushMoney input").val(),
                	adjustMsg:$("#adjusts .adjustMsg input").val(),
                }
				if(data.platName == "" || data.platName ==null){
					$.threetop("平台必须选择!");
					return false;
				}else if(data.nbasicSalary == ""){
					$.threetop("调整底薪|保底不能为空!");
					return false;
				}else if(data.npushMoney == ""){
					$.threetop("调整提成比例不能为空!");
					return false;
				}else if(data.adjustMsg == ""){
					$.threetop("调薪申请原因不能为空!");
					return false;
				}
				$.ajax({
					type: "POST",
                    url: "${pageContext.request.contextPath}/adjusts/save.do",
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
					$.yesorno("确认通过调薪申请吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/adjusts/passed.do"
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
					$.yesorno("确认拒绝调薪申请吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/adjusts/reject.do"
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
		},deletes:function(){
			$(".thistable .del").click(function(){
				var ids = grid.getCheckedValues("id");
				if(ids != ""){
					$.yesorno("确认删除调薪申请吗?",300,160,function(){
						$.ajax({
							type: "POST",
	                    	cache: false,
	                    	data:"ids="+ids,
	                    	url: "${pageContext.request.contextPath}/adjusts/del.do"
						}).done(function(data){
							if(data.resultStatus == 200)
								$.threesecond("删除数据成功", 200, 100, 1000);
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
    	url: "${pageContext.request.contextPath}/adjusts/readPages.do?ads=${adjust}&branchs=${branchs}",
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
					adjust:{
						build:function(edit,value){
							if(value=="待审")
								return "<span style='color:#0074ac'>"+value+"</span>"
							if(value=="拒绝")
								return "<span style='color:#e35d00'>"+value+"</span>"
						}
					},nbasicSalary:{
						build:function(edit, value, record, rowIndex, colIndex, tds, rows, options){
							var sbasicSalary = record.sbasicSalary;
							var $value = value;						
							if(parseInt(sbasicSalary) > parseInt($value)){
								return "<span style='color:#0074ac'>"+value+"</span>";
							}else{
								return "<span style='color:#e35d00'>"+value+"</span>";
							}
						}
					},npushMoney:{
						build:function(edit, value, record, rowIndex, colIndex, tds, rows, options){
							var spushMoney = record.spushMoney.substring(0,record.spushMoney.length - 4);
							var $value = value.substring(0,value.length - 1);
							if(parseInt(spushMoney) > parseInt(value)){
								return "<span style='color:#0074ac'>"+value+"</span>";
							}else{
								return "<span style='color:#e35d00'>"+value+"</span>";
							}
						}
					}
				}
			}        	
        }
    });
    Main.show();//弹窗
	Main.search();//搜索
	Main.addAdjusts();//添加调薪申请人	
	Main.passed();//通过
	Main.reject();//拒绝
	Main.deletes();//删除
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
	</div>
	<div class="rightPadd">
		<jsp:include page="finSalariesAdjustsCommon.jsp"></jsp:include>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
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
					<th w_index="sbasicSalary" w_align="center" width="1%;">原底薪|保底</th>
					<th w_edit="nbasicSalary" w_index="nbasicSalary" w_align="center" width="1%;">申请底薪|保底</th>
					<th w_index="spushMoney" w_align="center" width="1%;">原提成比例</th>
					<th w_edit="npushMoney" w_index="npushMoney" w_align="center" width="1%;">申请提成比例</th>
					<th w_index="adjustMsg" w_align="left" width="1%;">调整原因</th>
					<th w_index="employee" w_align="center" width="1%;">申请人</th>
					<th w_edit="adjust" w_index="adjust" w_align="center" width="1%;">审核状态</th>
					<th w_index="createDT" w_align="center" width="1%;">创建时间</th>
					<th w_index="passTime" w_align="center" width="1%;">通过时间</th>
				</tr>
			</table>
			<p style="text-align:center;position: fixed; left: 40%; bottom: 1%;"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
		</div>
		<!-- 搜索 -->
		<div id="search" style="display:none;margin-left:20px;margin-right:20px;">
	        <p>请输入员工号或真实姓名</p>
	        <p>	<input type="hidden" name="id" id="id">
	        	<input type="text" name="realname" id="realname" style="width:200px;" maxlength="400" class="input">
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
	                    <td style="text-align: right;">平台：<label style="color: red;">*</label></td>
	                    <td>
	                        <select id="platId" name="platId" style="width:100px;">
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
	                    <td style="text-align: right;">原底薪|保底：<label style="color: red;">*</label></td>
	                    <td class="sbasicSalary"><input type="text" style="width:100px;"></td>
	                    <td style="text-align: right;">原提成比例：<label style="color: red;">*</label></td>
	                	<td class="spushMoney"><input type="text" style="width:100px;">%</td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">申请底薪|保底：<label style="color: red;">*</label></td>
	                    <td class="nbasicSalary"><input type="text" style="width:100px;"></td>
	                    <td style="text-align: right;">申请提成比例：<label style="color: red;">*</label></td>
	                	<td class="npushMoney"><input type="text" style="width:100px;">%</td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">调整原因：<label style="color: red;">*</label></td>
	                    <td colspan="3" class="adjustMsg"><input type="text" style="width:450px;"></td>
	                </tr>
	            </table>
	            <p style="text-align:center;"><span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
	        </div>
	</div>
  </body>
</html>
