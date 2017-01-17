<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>薪酬福利</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/views/js/jquery.form.js"></script>
<style type="text/css">
	table{
		width:100%;
		height:90%;
		border:1px solid #ddd;
		border-spacing: 0px;
		background-color:#fff;
	} 
	.div{width:100%;height:80%}
	tr{height:52px; }
	tr td{ font-size: 14px; width:10%;}
	tr td:first-child,tr th:first-child,.pdr{text-align:left;padding-left:34px; }
	.tabBtn{
		width:140px;
	}
	lable {
	    width: 112px;
	    display: inline-block;
	}
	.bottom{margin:0; text-align:center; }
	.bg83{margin-bottom:36px; }
	.basewindow .ok{margin:0; }
	#newCard{
		border:1px solid #ddd; 
		border-radius:2px;
		-webkit-border-radius:2px;
		-o-border-radius:2px;
		box-shadow: 0 0 6px #fbfbfb;
	}
</style>
<script>
$(function(){
	if("${isEdit}" == "true"){
		$(".okok").show();
	}else{
		$(".okok").hide();
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
	
	/* $.ajax({
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
					$(".right-twohalf .rspan ul").append("<li><a href='"+url+"?advance=${advance}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")   				
	   			});
	   			$(".right-twohalf .regspan").show();
   				$(".right-twohalf .rspan").show();
   			}
   		}); */
	
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/finemp/readDateList.do",
		data: "",
		cache: false
	}).done(function (data) {
		if(data.data.length>0){
			$(data.data).each(function(index,value){
				var $self=$(this);
				var url = window.location.href;
				if(url.indexOf("?") > 0){
					url = url.substring(0,url.indexOf("?"));
				}
				$(".right-twoRight .riqi sul").append("<li><a href='${pageContext.request.contextPath}/finemp/show.do?date="+value+"'>"+ value +"</a></li>")
			});	
		}
	});
		
	$(".right-twoRight .riqi>span").click(function () {
		if (!$(".right-twoRight .riqi ul").is(":animated")) {
			$(".right-twoRight .riqi ul").fadeToggle();
       	}
    });
	
	//修改
		$(".update").click(function() {
			var $chuangkou = $("#leaveForm");
			$.basewindow("修改卡号:", $chuangkou, 392, 244, function() { 
				 
			}, function() {
				
			});
			 
		});
		$("#leaveForm .ok").click(function() {
			$("#leaveForm").ajaxSubmit(function(data) { 
				if (data.resultStatus == 200) {
					window.location.reload();
				}
			})
		})
})
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
		<div class="right-twohalf" style="margin-bottom:10px">
			<span>首页</span> 
			<span>></span>
			<span>个人中心</span> 
			<span>></span>
			<span class="color99">薪酬福利</span>
	    	<div class="clearFix">
	    		<div class="fr right-twoRight">
		    		<span class="riqitext">查看历史记录：</span>
		    		<div class="riqi"><span>${date}</span><ul></ul></div>
		    	</div>
	    	</div>
	     </div>
	
		<div class="div">
			<table class="custom-scroll">
				<tr style="background-color: #32373d; color:#f9fafe; height:40px; ">
				<th> 基本工资基数</th><th></th><th></th><th></th>
				 
				</tr>
				<tr  style="background-color:#ebebeb; ">
				<td>本月基本工资</td>
			    <td>${salaryemp.basicSalary}</td>
			    <td class="pdr">本月实发工资</td>
			    <td>${salaryemp.realitySalary}</td>
				</tr>
				<tr>
				<td>出勤天数</td>
			    <td>${salaryemp.realitySalary}</td>
			    <td class="pdr"></td>
			    <td></td>
				</tr>
				<tr style="background-color:#ebebeb;">
				<th>本月工资扣减项合计</th>
				<th></th>
				<th class="pdr">合计:</th>
				<th>${pn_heji}</th>
				 
				</tr>
				<tr>
				<td>养老保险个人缴纳</td>
			    <td>${salaryemp.pn_pension}</td>
			     <td class="pdr">其他扣款</td>
			    <td>${salaryemp.otherDeduct}</td>
				</tr>
				<tr>
				<td>失业保险个人缴纳</td>
			    <td>${salaryemp.pn_unemployment}</td>
			    <td class="pdr">医疗保险个人缴纳</td>
			    <td>${salaryemp.pn_medicare}</td>
				</tr>
				<tr>
				<td>补充公积金个人缴纳</td>
			    <td>${salaryemp.pn_replenishProvident}</td>
			    <td class="pdr">基本公积金个人缴纳</td>
			    <td>${salaryemp.pn_provident}</td>
				</tr>
				<tr>
				<td>个人所得税</td>
			    <td>${salaryemp.individualIncomeTax}</td>
			    <td class="pdr">迟到扣款</td>
			    <td>${salaryemp.beLate}</td>
				</tr>
				<tr>
				<td>事假扣款</td>
			    <td>${salaryemp.casualLeave}</td>
			    <td class="pdr">病假扣款</td>
			    <td>${salaryemp.sickLeave}</td>
				</tr>
				<tr>
				<td>平台扣款</td>
			    <td>0.00</td>
			   <td></td>
			   <td></td>
				</tr>
				<tr style="background-color:#ebebeb;">
				<th >公司社保缴纳</th>
			    <th></th><th class="pdr">合计:</th><th>${cp_heji}</th>
				</tr>
				<tr>
				<td>养老保险公司缴纳</td>
			    <td>${salaryemp.cp_pension}</td>
			    <td class="pdr">医疗保险公司缴纳</td>
			    <td>${salaryemp.cp_medicare}</td>
				</tr>
				<tr>
				<td>失业保险公司缴纳</td>
			    <td>${salaryemp.cp_unemployment}</td>
			    <td class="pdr">工伤保险公司缴纳</td>
			    <td>${salaryemp.cp_occupationalInjury}</td>
				</tr>
				<tr>
				<td>生育保险公司缴纳</td>
			    <td>${salaryemp.cp_fertility}</td>
			    <td class="pdr">基本公积金公司缴纳</td>
			    <td>${salaryemp.cp_provident}</td>
				</tr>
				<tr>
				<td>补充公积金公司缴纳</td>
			    <td>${salaryemp.cp_replenishProvident}</td>
			  <td></td>
			  <td></td>
			 
				</tr>
				<tr style="background-color:#ebebeb; ">
				<th>本月工资加项合计</th><th></th><th class="pdr">合计:</th>
				
			   <th>${else_heji}</th>
			  
				</tr>
				<tr>
				<td>费用报销</td>
			    <td>${salaryemp.applyFee}</td>
			    <td class="pdr">餐补</td>
			    <td>0.00</td>
				</tr>
				<tr>
				<td>平台补贴</td>
			    <td>0.00</td>
			    <td class="pdr">其他补贴</td>
			    <td>${salaryemp.otherSubsidy}</td>
				</tr>
				<tr>
				<td>提成</td>
			    <td>${salaryemp.cp_replenishProvident}</td>
			    <td class="pdr">奖金</td>
			    <td>${salaryemp.bonus}</td>
				</tr>
				<tr style="background-color: #ebebeb;">
				<th >工资卡信息 </th>
			    <th></th><th></th><th></th>
				</tr>
				<tr>
				<td>工商银行${employee.bankAddress}-银行卡号：${employee.bankCard}</td>
			    <td></td>
			    <td class="pdr">工资卡信息错误？</td>
			    <td></td>
			    
				</tr>
		 
			</table>
			<p style="text-align:center;position: fixed; left: 40%; bottom: 1%; ">
				<input type="button" value="修改工资卡信息" class="tabBtn update">
			</p>
			</div>
	 
		<!-- 修改卡号 -->
		<form action="${pageContext.request.contextPath}/salaries/updateCard.do" method="post" id="leaveForm" style="display:none;margin-left:20px;margin-right:20px;">
			<p> 
				<lable>银行卡：</lable>
				<input type="number" name="newCard" id="newCard" style="width:200px;height: 30px;" value=""/>
			</p>
			<p> 
				<lable>银行卡开户支行：</lable>
				<input type="text" name="newCardAddress" id="newCardAddress" style="width:200px;height: 30px;" value=""/>
			</p>
			<p class="bottom">
				<input class="ok" type="button" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</form>
	</div>
</body>
</html>
