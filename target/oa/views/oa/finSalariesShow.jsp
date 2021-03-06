<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>薪资查询</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
body {height: 100%;min-width: 200px;overflow-x: hidden;overflow-y:scroll;position: relative;}
.editTable{width:95%;height:100%;border:1px solid #ddd;margin: 10px 20px;border-spacing: 0px;color:#333} 
.editTable p{margin:0}
.editTable tr td{border-bottom:1px solid #ddd; font-size: 14px; width:10%;height:30px}
.editTable tr td:first-child,tr th:first-child,.pdr{text-align:right;padding-right:40px;}
.salarylook{width:100%;height:80%}
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
</style>
	<script type="text/javascript">
	var grid;
	$(function () {
	    $.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/finSalaries/readDateList.do",
			data: "",
			cache: false
		}).done(function (data) {
			$(data.data[0]).each(function(index,value){
				var $self=$(this);
				$(".right-twoRight .riqi ul").append("<li><a href='${pageContext.request.contextPath}/finSalaries/showSalaries.do?date="+value+"'>"+ value+"</a></li>")
			});	
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
		
		$(".right-twoRight .riqi>span").click(function() {
			if (!$(".right-twoRight .riqi ul").is(":animated")) {
				$(".right-twoRight .riqi ul").fadeToggle();
			}
		})
       
         grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/finSalaries/readPagesShowSalaries.do?date=${date}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit: true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: { } 
                }
            }
        });
		
		//快速搜索
		$("#seach").click(function() {
		    var name = $("#nameOrNumber").val();
			var searchParames={"name": name}
			grid.search(searchParames);
		})
     });
	
	 	function operate(record, rowIndex, colIndex, options) {
	        return '<a href="#" onclick="edit('+record.id+')">查看详情</a>';
	    }
	 	
	 	function edit(value){
	 	   $(".item").text("0.00");
			var $chuangkou = $(".salaryLook");
	 	   	 var editurl = "${pageContext.request.contextPath}/finSalaries/edit.do?t="+ new Date().toTimeString();
			  $.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + value,
				show : "slide",
				success : function(data) { 　
				    $("#editTable tr td span").remove();  
	       			$("#editTable th td span").remove();　
	       	        $(".edit").hide();
	       			$("#id").text(data.id); 
					$(".number").text(data.number);
					$(".name").text(data.name);
					$(".aliasname").text(data.aliasname);
					$(".deptName").text(data.deptName);
					$(".positionsName").text(data.positionsName); 
					$(".realitySalary").text(data.realitySalary);  
					$(".subsidySalary").text(data.basicSalary + data.subsidySalary);  
					
					$(".taxableSalary").text(data.taxableSalary);  
					$(".pn_totalSocial").text(data.pn_totalSocial); 
					$(".cp_totalSocial").text(data.cp_totalSocial); 
					$(".add_totalSocial").text(data.add_totalSocial); 
					$(".dec_totalSocial").text(data.dec_totalSocial); 
					
					
					$(".basicSalary").text(data.basicSalary); 
					$(".attendance").text(data.attendance);
					$(".realitySalary").val(data.realitySalary);
					$(".totalIncome").text(data.totalIncome);
					$(".pushMoneyActores").text(data.pushMoneyActores);
					$(".pushMoney").text(data.pushMoney+"%");
					 
					$(".pn_pension").val(data.pn_pension);
					$(".pn_medicare").val(data.pn_medicare);
					$(".pn_unemployment").val(data.pn_unemployment);
					$(".pn_provident").val(data.pn_provident); 
					$(".individualIncomeTax").text(data.individualIncomeTax);
					$(".actoresIncomeTax").text(data.actoresIncomeTax);
					$(".bocomBank").text(data.bocomBank);
					$(".icbcBank").text(data.icbcBank);
					$(".cp_pension").val(data.cp_pension);
					$(".cp_medicare").val(data.cp_medicare);
					$(".cp_unemployment").val(data.cp_unemployment);
					$(".cp_provident").val(data.cp_provident); 
					$(".cp_occupationalInjury").val(data.cp_occupationalInjury);
					$(".cp_fertility").val(data.cp_fertility);
					
					$(".applyFee").val(data.applyFee);
					$(".pushMoneySalary").val(data.pushMoneySalary);
					$(".platSubsidy").text(data.platSubsidy);
					$(".otherSubsidy").val(data.otherSubsidy);
					$(".bonus").val(data.bonus);
					$(".foodSubsidy").val(data.foodSubsidy);
					 
					$(".beLate").val(data.beLate);
					$(".casualLeave").val(data.casualLeave);
					$(".sickLeave").val(data.sickLeave);
					$(".platLeave").text(data.platLeave);
					$(".otherDeduct").val(data.otherDeduct);
					  
					$(".remarks").val(data.remarks);
					
					if(data.isActore ==11){
						$(".tbd").show();
						if(data.details != null){
						    $.each(data.details, function(name, value) { 
								$("#"+name).text(value);
							}); 
						}
					}else{
						$(".tbd").hide();
					}
				 	  
			       	 $("#editTable tr td input").each(function(){ 
			       	 	val=$(this).val(); 
			       	 	$(this).after("<span>"+val+"</span>"); 
			       	 	  
			       	 })
			       	 $("#editTable tr td input").hide(); 
				},error : function(data) {
				}
			});  
				$.basewindow("详细资料", $chuangkou,880, 868);
				$chuangkou.css({"display" : "block"}); 
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
		<div class="right-twohalf">
			<span>首页 </span> 
			<span>></span>
			<span>财务系统 </span> 
			<span>></span>
			<span class="color99">薪资查询</span>
    	</div>
    	
    	<div class="right-twoRight clearFix">
    		<div class="fr">
    			<span class="riqitext">查看历史记录：</span>
	    		<div class="riqi">
	    			<span>${date}</span>
	    			<ul></ul>
				</div>
	    		<div class="search">
		    		<input id="nameOrNumber" type="text" placeholder="姓名搜索"/>
		    		<button type="button" id="seach" class="tableok" >查询</button>				
			    </div>
    		</div>
		</div>
	    
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="number" w_align="center" width="1%;">员工号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="deptName" w_align="center" width="1%;">部门</th>
					<th w_index="positionsName" w_align="center" width="1%;">职位</th>
					<th w_render="operate" w_align="center" width="1%;">操作</th>
					<th w_edit="remark" w_index="remark" w_align="center" class="text-left">备注</th>
				</tr>
			</table>
		</div>
		<div class="salaryLook" style="display:none"> 
			<table class="editTable" id="editTable"> 
			<tr style="background-color: #0074ac;">
			<th>基本信息</th><th></th><th></th><th></th> 
			</tr>
			<tr>
				<td>员工号</td><td><p class="number"></p> <p style="display:none;" id="id"></p></td> 
				<td class="pdr">员工姓名</td><td><p class="name"></p></td>
			</tr>
			<tr>
				<td>部门</td><td> <p class="deptName"></p></td>
				<td class="pdr">职位</td><td><p class="positionsName"></p></td>
			</tr>
			<tr> 
				<td >基本工资</td>
				<td class="subsidySalary"></td> 
				<td class="pdr">计税工资</td>
			    <td class="taxableSalary"></td>
			</tr>
			 
			 <tr> 
				<td  class="pdr">工商银行</td>
			    <td class="icbcBank"></td>
			    
				<td  class="pdr">交通银行</td>
			    <td class="bocomBank"></td>
			    
			</tr>  
			<tr>
				<td>本月出勤</td><td><p  class="attendance"></p></td>
				<td class="pdr">实发工资</td><td class="realitySalary"></td> 
			</tr>
			  
			<tr style="background-color: #0074ac;">
			<th>个人社保缴纳</th>
			<th></th>
			<th class="pdr">合计:</th>
			<th class="pn_totalSocial"></th> 
			</tr>
			<tr>
				<td>养老保险个人缴纳</td>
			    <td><input type="number"  class="pn_pension" /></td>
			     <td class="pdr">医疗保险个人缴纳</td>
		    	<td><input type="number"  class="pn_medicare" ></td>
			</tr>
			<tr>
				<td>失业保险个人缴纳</td>
			    <td><input type="number" class="pn_unemployment" ></td>
			     <td class="pdr">基本公积金个人缴纳</td>
			    <td><input type="number"  class="pn_provident" ></td>
			</tr>  
			<tr style="background-color: #0074ac;">
			<th >公司社保缴纳</th><th></th><th class="pdr">合计:</th><th class="cp_totalSocial"> </th>
			</tr>
			<tr>
				<td>养老保险公司缴纳</td>
			    <td><input type="number" class="cp_pension" ></td>
			    <td class="pdr">医疗保险公司缴纳</td>
			    <td><input type="number"  class="cp_medicare" ></td>
			</tr>
			<tr>
				<td>失业保险公司缴纳</td>
			    <td><input type="number"  class="cp_unemployment" ></td>
			    <td class="pdr">工伤保险公司缴纳</td>
			    <td><input type="number"  class="cp_occupationalInjury" ></td>
			</tr>
			<tr>
				<td>生育保险公司缴纳</td>
			    <td><input type="number"  class="cp_fertility" /></td>
			    <td class="pdr">基本公积金公司缴纳</td>
			    <td><input type="number"  class="cp_provident" /></td>
			</tr> 
			
			<tr style="background-color:#0074ac;">
			<th>本月工资加项</th><th></th><th class="pdr">合计:</th> 
		  	<th class="add_totalSocial"></th> 
			</tr>
			<tr>
				<td>费用报销</td>
			    <td><input type="number" class="applyFee" /></td>
			    <td class="pdr">餐补</td>
			    <td><input type="number" class="foodSubsidy" /></td>
			</tr>
			<tr>
				<td>平台补贴</td>
		    	<td><p  class="platSubsidy"></p></td>
		    	<td class="pdr">其他补贴</td>
		    	<td><input type="number" class="otherSubsidy" /></td>
			</tr>
			<tr>
				<td>提成</td> 
			    <td><input type="number" class="pushMoneySalary" /></td>
			    <td class="pdr">奖金</td>
			    <td><input type="number" class="bonus" /></td>
			</tr>
			
			<tr style="background-color: #0074ac;">
			<th>本月工资扣项</th><th></th><th class="pdr">合计:</th> 
		  	<th class="dec_totalSocial"></th> 
			</tr>
			<tr>
				<td>迟到扣款</td>
			    <td><input type="number" class="beLate" /></td>
			    <td class="pdr">病假扣款</td>
			    <td><input type="number" class="sickLeave" /></td>
			</tr>
			<tr>
				<td>事假扣款</td>
		    	<td><input type="number" class="casualLeave" /></td>
		    	<td class="pdr">其他扣款</td>
		    	<td><input type="number" class="otherDeduct" /></td>
			</tr>
			<tr>
				<td>平台扣款</td>
			    <td><p class="platLeave"></p></td>
			     <td  class="pdr">个人所得税</td>
			    <td><p class="individualIncomeTax"></p></td>
			</tr>
			<tr>
				<td>劳务服务费</td>
			    <td><p class="actoresIncomeTax"></p></td>
			    <td class="pdr"></td>
			    <td></td>
			</tr>
			<tr>
			     <td class="pdr">备注</td>
			    <td colspan="3"><input type="text" class="remarks" style="width:88%"></td>
		    </tr>
			 <tbody  class="tbd" style="display:none">
			<tr style="background-color: #0074ac;" >
				<th>直播收入</th><th></th><th class="pdr">合计:</th> 
			  	<th class="pushMoneyActores"></th> 
			</tr>
			 
			<tr> 
				<c:forEach var="item" items="${list}"  varStatus="var">
						<tr>		
							<td>${item.aname}</td>
							<td id="${item.aid}" class="item"></td>
							<td class="pdr">${item.bname}</td>
							<c:choose>
							<c:when test="${item.bname != null}">
							 <td id="${item.bid}"  class="item"></td>
							</c:when>
							<c:otherwise>
							 <td id="${item.bid}"></td>
							</c:otherwise>
							</c:choose>
							
						</tr>						
				</c:forEach> 
			<tr>
				<td>税后总业绩</td>
			    <td><p class="totalIncome"></p></td>
			    <td class="pdr">提成比例</td>
			    <td><p class="pushMoney"></p></td>
			</tr>  
			<tr>
				<td>提成薪资</td>
			  	<td class="pushMoneyActores"></td>   
			    <td class="pdr"></td>
			    <td></td>
			</tr>
			 </tbody>
			</table> 
			<div class="clear"></div>
			<p style="text-align:center;">
		 			<button class="tableok cancel" >关闭</button>
			</p>
		</div>
	</div>
	
	 
	
  </body>
</html>
