<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>艺人薪资</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
	<style type="text/css">
	body {
		height: 100%;
		min-width: 200px;
		overflow-x: hidden;overflow-y:scroller;
		position: relative;
	}
	.editTable{
		width:95%;
		height:100%;
		border:1px solid #ddd;
		margin: 10px 20px;
		border-spacing: 0px;
		color:#333;
	} 
	.editTable p{margin:0; }
	.editTable tr td{border-bottom:1px solid #ddd; font-size: 14px; width:10%;height:30px}
	.editTable tr td:first-child,tr th:first-child,.pdr{text-align:right;padding-right:40px;}
	.salarylook{width:100%;height:80%; }
	
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
	
	.basewindow .okok {
		width:82px;
		height:34px; 
		text-align:center;
		line-height:34px; 
		background-color:#0074ac;
		box-shadow: inset 0 0 6px #599eeb;
		position:static;
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
	/* 弹出框 */
	#plat{
		width:200px; 
	}
	.combo .combo-text,#sex{
		text-indent:1em;
	}
	.basewindow .table{
		border:none;
	}
	.table tbody tr td{
		border:none; 
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
		
        $(".save").hide();
        if(${type} != null && ${type} ==1){
            $(".searchAll").hide();
            $("#partUpLoad").hide();
        }else if(${type}!= null && ${type} == 2 ){
            $(".searchAll").show();
            $("#partUpLoad").show();
        }
        
        var flag = ${isEdit};
        if(!flag){
            $(".check").hide();
            $(".payoff").hide();
            $(".edit").hide();
        }
        $(".right-twoRight .riqi>span").click(function () {
    		if (!$(".right-twoRight .riqi ul").is(":animated")) {
    			$(".right-twoRight .riqi ul").fadeToggle();
           	}
        });
        
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/finSalaries/readPages.do?date=${date}&type=${type}&all=${all}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit: true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                        billed: {
    	                    build: function (edit, value) {
    	                        if(value == "UNBILLED"){
    	                            return '<span style="color:#e35d00">未发</span>';
    	                        }else if(value=="PUBLISHED"){
    	                            return '<span style="color:#0072ac">已发</span>';
    	                        }else{
    	                            return '<span style="color:#e35d00">未通过</span>';
    	                        }
    	                    }
	    	            }
                    } 
                }
            }
        });
   	 //提交
        $(".save").click(function () { 
    			var $data = {
    				id : $(".editTable #id").text(),  
    		       	pn_pension:$(".editTable .pn_pension").val(),
    				pn_medicare:$(".editTable .pn_medicare").val(),
    				pn_unemployment:$(".editTable .pn_unemployment").val(),
    				pn_provident:$(".editTable .pn_provident").val(), 
    				cp_pension:$(".editTable .cp_pension").val(),
    				cp_unemployment:$(".editTable .cp_unemployment").val(),
    				cp_provident:$(".editTable .cp_provident").val(), 
    				cp_fertility :$(".editTable .cp_fertility").val(),
    				applyFee:$(".editTable .applyFee").val(),
    				otherSubsidy:$(".editTable .otherSubsidy").val(),
    				bonus:$(".editTable .bonus").val(),
    				beLate:$(".editTable .beLate").val(),
    				sickLeave:$(".editTable .sickLeave").val(), 
    				cp_medicare:$(".editTable .cp_medicare").val(), 
    				cp_occupationalInjury:$(".editTable .cp_occupationalInjury").val(), 
    				 
    				otherDeduct:$(".editTable .otherDeduct").val(), 	  
    				remarks:$(".editTable .remarks").val(),
    			}
    			
    			$.ajax({
    				type: "POST",
    				cache: false,
    				url: "${pageContext.request.contextPath}/finSalaries/save.do",
    				data:JSON.stringify($data),
    				contentType:"application/json; charset=utf-8",
    				success:function(data){
    					if(data.resultStatus == 200){
    						$.threesecond("保存成功", 200, 100, 1000);
    						$(".zhezhao-basewindow").hide();
    						$(".basewindow").hide();
    						$('body').append($(".salaryLook"));
    						$(".salaryLook").hide(); 
    						grid.refreshPage();
    					}
    				}
    			});
    		 
        }); 
        
        //计算
        $(".check").click(function () {
                $.yesorno("是否开始计算？", 300, 160, function () {
        			$.ajax({
                        type: "POST",
                        dataType:"json",
                        url: "${pageContext.request.contextPath}/finSalaries/check.do",
                        data:"date=${date}&type=${type}",
                        success:function(){
		                     $.timeOut("计算需耗时约<i>30</i>秒，请稍等后刷新", 200, 100, 30);
		                     grid.refreshPage();
                        }
                    })
                });
        });
        $(".edit").click(function(){
			 //alrt("编辑!");
			$(".cancel").show();
			$(".save").show(); 
			$(".edit").hide();
			
			$("#editTable tr td span").remove();  
	       	$("#editTable th td span").remove(); 
			$("#editTable tr td input").show(); 
	       	$("#editTable th td input").show();
		 
		})
      //详细
		$(".views").click(function(){
			$(".item").text("0.00");
			var $chuangkou = $(".salaryLook");
			if (grid.getSelectedRow().length > 0) { 
				var id = grid.getRowRecord(grid.getSelectedRow()).id; 
				var editurl = "${pageContext.request.contextPath}/finSalaries/edit.do?t="+ new Date().toTimeString();
				  $.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) { 　
					    $("#editTable tr td span").remove();  
		       			$("#editTable th td span").remove();　
		       			if(!flag){
		       	            $(".edit").hide();
		       	        }else{
		       	         	$(".edit").show();
		       	        }
		       			$(".save").hide();
		       			 
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
						$(".pushMoneySalary").text(data.pushMoneySalary);
						$(".platSubsidy").text(data.platSubsidy);
						$(".otherSubsidy").val(data.otherSubsidy);
						$(".bonus").val(data.bonus);
						$(".foodSubsidy").text(data.foodSubsidy);
						$(".postAllowance").text(data.postAllowance);
						$(".companyOtheSub").text(data.companyOtheSub);
						
						$(".beLate").val(data.beLate);
						$(".casualLeave").text(data.casualLeave);
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
				       	 $("#editTable th td input").hide();  
					
					},error : function(data) {
					}
				});  
					　
					$.basewindow("详细资料", $chuangkou,880, 868);
					$chuangkou.css({"display" : "block"}); 
 	
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}

			});
      		//显示全部
			 $(".showAll").click(function(){
		            window.location.href="${pageContext.request.contextPath}/finSalaries/actshow.do?date=${date}&type=${type}&all=1";
		     })
        
		     //快速搜索
			$("#fast").change(function() {
			    var fastArg = $("#fast").val();
				var searchParames={
				    "fastArg": fastArg,
				    "type" : ${type},
				    "all":${all}
			}
			grid.search(searchParames);
		})
		//发放工资
        $(".payoff").click(function(){
	         var ids =  grid.getCheckedValues('id');
	   	     if(ids != ''){
	   	         $.ajax({
	   	           type : "POST",
	   	           cache : false,
	   	           url:  "${pageContext.request.contextPath}/finSalaries/grantCheck.do",
	   	           data: "id="+ids+"&date=${date}",
	   	           success : function(data){
	   	               if(data.resultStatus == 200){
			   	  	   	      $.ajax({
			   	  	   	           type : "POST",
			   	  	   	           cache : false,
			   	  	   	           url:  "${pageContext.request.contextPath}/finSalaries/grant.do",
			   	  	   	           data: "id="+ids+"&date=${date}",
			   	  	   	           success : function($data){
			   	  	   	               if($data.resultStatus == 200){
			   	  	   						$.threesecond("发放成功", 200, 100, 1000);
			   	  	   						grid.refreshPage();
			   	  	   					}
			   	  	   	           }
			   	  	   	      }) 
	   					}else if(data.resultStatus==101){
	   						var $chuangkou = $("#check");
		   					$.basewindow("提示", $chuangkou, 400, 200);
		   					$("#prom").text(" "+data.resultMessage)
		   					$chuangkou.css({"display" : "block"}); 
	   					}
	   	             }
	   	         })
	   	     }else{
	   	         $.threesecond("未选中数据", 200, 100, 1000);
	   	     }
        })
        
        // 发工资确定
        $("#okokok").click(function(){
            var ids =  grid.getCheckedValues('id');
            if(ids != ''){
  	   	      	$.ajax({
  	  	   	           type : "POST",
  	  	   	           cache : false,
  	  	   	           url:  "${pageContext.request.contextPath}/finSalaries/grant.do",
  	  	   	           data: "id="+ids+"&date=${date}",
  	  	   	           success : function($data){
  	  	   	               if($data.resultStatus == 200){
  	  	   						$.threesecond("发放成功", 200, 100, 1000);
		  	  	   				$("#check").hide(); 
		  	  	   				$(".zhezhao-basewindow").hide(); 
		  	  	   				$(".basewindow").hide();
		  	  	   				$('body').append($("#check"));
  	  	   						grid.refreshPage();
  	  	   					}
  	  	   	           }
  	  	   	      }) 
            }else{
	   	         $.threesecond("未选中数据", 200, 100, 1000);
	   	     }
            
        })
        
      //搜索
		$(".searchAll").click(function() {
			var $chuangkou = $("#sousuo");
			$.basewindow("高级搜索", $chuangkou, 392, 232);
			$chuangkou.css({"display" : "block"}); 
		})
		//提交搜索
		$("#okok").click(function() { 
			var sex = $("#sex").val();
			var plat = $("#plat").combogrid("getValue");
			
			var searchParames={
				"sex":sex,
				"plat":plat 
			}
			grid.search(searchParames);
			$("#sousuo").hide(); 
			$(".zhezhao-basewindow").hide(); 
			$(".basewindow").hide();
			$('body').append($("#sousuo"));
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
						$("#platId").combogrid("grid").datagrid(
								"reload", {
									"filter" : q
								});
						$("#platId").combogrid("setValue", q);
					}
				}
			}
		});
      		
      		
			 $("#platId").combogrid({
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
								$("#platId").combogrid("grid").datagrid(
										"reload", {
											"filter" : q
										});
								$("#platId").combogrid("setValue", q);
							}
						}
					}
				});
			 $("#partUpLoad").click(function(){
			     var $chuangkou = $("#daochu");
					$.basewindow("部分导出", $chuangkou, 400, 200);
					$chuangkou.css({"display" : "block"}); 
			 })
			 
				 //提交搜索
			$("#ok").click(function() { 
				var sex = $("#sexy").val();
				var plat = $("#platId").combogrid("getValue");
				window.location.href="${pageContext.request.contextPath}/finSalaries/partExcel.do?date=${date}&sex="+sex+"&plat="+plat;    
				$("#daochu").hide(); 
				$(".zhezhao-basewindow").hide(); 
				$(".basewindow").hide();
				$('body').append($("#daochu"));
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
			<span>财务系统</span> 
			<span>></span>
			<span class="color99">艺人薪资</span>
			<div class="fr">
				<span id="partUpLoad">部分导出</span>
				<span id="upLoad">导出Excel</span>
			</div>
    	</div>
			
		<jsp:include page="finSalariesCommon.jsp"></jsp:include>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff;">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="number" w_align="center" width="1%;">员工号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="deptName" w_align="center" width="1%;">部门</th>
					<th w_index="positionsName" w_align="center" width="1%;">职位</th>
					<th w_index="icbcBank" w_align="center" width="1%;">工行</th>
					<th w_index="bocomBank" w_align="center" width="1%;">交行</th>
					<th w_index="realitySalary" w_align="center" width="1%;">实发工资</th>
					<th w_index="realExpenditure" w_align="center" width="1%;">公司实际支出工资</th>
					<th w_edit="billed" w_index="billed" w_align="center" width="1%;" rowspan="2">发放状态</th>
					<th w_index="bankCard" w_align="left" width="1%;">工资卡账号</th>
					<th w_index="bankAddress" w_align="left" width="1%;">开户行地址</th>
					<th w_edit="remark" w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
			
			<p style="position: fixed; left: 50%; margin-left:-232px; bottom: 1%;"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
			
		</div>
		<div class="salaryLook" style="display:none"> 
			<table class="editTable" id="editTable"> 
			<tr style="background-color: #599eeb;">
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
			  
			<tr style="background-color: #599eeb;">
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
			<tr style="background-color: #599eeb;">
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
			
			<tr style="background-color: #599eeb;">
			<th>本月工资加项</th><th></th><th class="pdr">合计:</th> 
		  	<th class="add_totalSocial"></th> 
			</tr>
			<tr>
				<td>费用报销</td>
			    <td><input type="number" class="applyFee" /></td>
			      <td class="pdr">餐补</td>
			    <td><p class="foodSubsidy"></p></td>
			</tr>
			<tr>
				<td>岗位津贴</td>
			    <td><p class="postAllowance"></p></td>
			    <td class="pdr">其他补贴(公司)</td>
			    <td><p class="companyOtheSub"></p></td>
			</tr>
			<tr>
				<td>项目补贴</td>
		    	<td><p  class="platSubsidy"></p></td>
		    	<td class="pdr">其他项目补贴</td>
		    	<td><input type="number" class="otherSubsidy" /></td>
			</tr>
			<tr>
				<td>提成</td> 
			    <td><p  class="pushMoneySalary"></p></td>
			    <td class="pdr">奖金</td>
			    <td><input type="number" class="bonus" /></td>
			</tr>
			
			<tr style="background-color: #599eeb;">
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
		    	<td><p  class="casualLeave"></p></td>
		    	<td class="pdr">其他扣款</td>
		    	<td><input type="number" class="otherDeduct" /></td>
			</tr>
			<tr>
				<td>项目扣款</td>
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
			<tr style="background-color: #599eeb;" >
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
					<button class="tableok edit">编辑</button> <button class="tableok save"  style="display:none" >保存</button>
		 			<button class="tableok cancel" >关闭</button>
			</p>
		</div>
		
		<!-- 高级搜索 -->
		<form action="" method="post" enctype="multipart/form-data" id="sousuo" style="display:none;margin:0 20px;">
	
			<table class="table">
				<tr>
					<td style="width:50%;padding:0;border:none;"></td>
					<td style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
					<td style="text-align: right; font-size:14px; ">直播平台：</td>
					<td><input name="plat" id="plat"/> </td>
				</tr>
				<tr>
					<td style="text-align: right;">性别：</td>
					<td><select id="sex" style="width:200px;">
						<option value="">请选择</option>
						<option value="WOMAN">女</option>
						<option value="MAN">男</option>
					</select></td>
				</tr>
			</table>
			<p align="center">
				<input type="hidden" name="id" id="id"> 
				<button type="button" id="okok" class="tableok okok" >提交</button> 
				<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</form>
		
		<!-- 部分导出 -->
		<form action="" method="post" enctype="multipart/form-data" id="daochu" style="display:none;margin:0 20px;">
	
			<table class="table">
				<tr>
					<td style="width:50%;padding:0;border:none;"></td>
					<td style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">直播平台：</td>
					<td><input name="plat" id="platId" style="width:200px;"> </td>
				</tr>
				<tr>
					<td style="text-align: right;">性别：</td>
					<td><select id="sexy" style="width:200px;">
						<option value="">请选择</option>
						<option value="WOMAN">女</option>
						<option value="MAN">男</option>
						</select>
					</td>
				</tr>
			</table>
			<p align="center">
				<input type="hidden" name="id" id="id"> 
				<button type="button" id="ok" class="tableok okok" >按条件导出</button> 
				<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</form>
		<div id="check" style="display:none">
			<div style="padding:5px 25px;width:100%"><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>员工：</span><span id="prom" style="color:#e35d00"></span><span>报表未通过，不可发放工资，点击确定将略过部分员工</span></div>
			<p align="center">
				<button type="button" id="okokok" class="tableok okok" >确定</button> <input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div>
	</div>
	
  </body>
</html>
