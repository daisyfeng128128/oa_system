<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<title>员工薪资</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<jsp:include page="treeview.jsp"></jsp:include>
<style>
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;
	position: relative;
}

.editTable {
	width: 95%;
	height: 100%;
	border: 1px solid #ddd;
	margin: 10px 20px;
	border-spacing: 0px;
	color: #333
}

.editTable tr td {
	border-bottom: 1px solid #ddd;
	font-size: 14px;
	width: 10%;
	height: 30px
}

.editTable tr td:first-child, tr th:first-child, .pdr {
	text-align: right;
	height: 30px;
	padding-right: 40px;
}

.salaryLook {
	
}

.editTable p {
	margin: 0
}

.yirentable tr {
	height: 30px;
	line-height: 30px;
}

.yirentable tr:hover {
	background-color: #ddd;
}

.fix {
	position: fixed;
	width: 300px;
	bottom: 66px;
	text-align: center;
	right: 39%;
}

.fix span {
	cursor: pointer;
	border: 1px solid #ddd;
	text-align: center;
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

.yirentable .firsttr:hover {
	background-color: #fff;
}

.yirentable .selected {
	background-color: #ddd;
}

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

.ksss {
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

.mains {
	width: 100%;
	min-width: 1000px;
	background-color:#fff;
	overflow: auto;
}

.lefttree {
	width: 15%;
    margin-right: 15px;
    height: 800px;
    float: left;
    overflow: scroll;
}

.lefttree .secNav{
	background-color:#31383d;
	color:#ddd;
}

.rightbox {
	width: 85%;
    float: right;
    overflow: auto;
    margin-top: -800px;
}
.control{
	left:50%;
	margin-left:-60px;
}
.right-twoRight{
	margin-bottom:6px;
}
.tablelist .selected{
	background-color:#f3f3f3; 
}
</style>
</head>
<script type="text/javascript">
$(function () {
 		/* $(".rightbox").css({"width": $("body").outerWidth(true) - 240}); 
       		$(window).resize(function () { 
			if($("body").outerWidth(true)>1000){ 
				$(".rightbox").css({"width": $("body").outerWidth(true) - 240});
			}else{
				$(".rightbox").css({"width": "770px"});
			} 
		}); */
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
		
	if("${isEdit}" == "true"){
		$(".okok").show();
	}else{
		$(".okok").hide();
	}
	if(!${isEdit}){
	    $(".payoff").hide();
	}
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/finSalaries/readDateList.do",
		data: "",
		cache: false
	}).done(function (data) {
		$(data.data[0]).each(function(index,value){
			var $self=$(this);
			$(".right-twoRight .riqi ul").append("<li><a href='${pageContext.request.contextPath}/finSalaries/show.do?date="+value+"'>"+ value +"</a></li>")
		});	
	});
		
	$(".right-twoRight .riqi>span").click(function () {
		if (!$(".right-twoRight .riqi ul").is(":animated")) {
			$(".right-twoRight .riqi ul").fadeToggle();
       	}
    });
	
	var grid = $.fn.bsgrid.init("searchTable", {
	    url: "${pageContext.request.contextPath}/finSalaries/readAllSalary.do?date=${date}&type=${type}",
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
    	                         if(value=="UNBILLED"){
    	                            return '<span style="color:#e35d00">未发</span>';
    	                        }else if(value=="PUBLISHED"){
    	                            return '<span style="color:#0074ac">已发</span>';
    	                        }else{
    	                            return '<span style="color:#e35d00">未通过</span>';
    	                        }
    	                   }
	                 }
	            }
	        }
	    }
	});
	
	var  ids="";
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
				remarks:$(".editTable .remarks").val()
						 
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
		this.tree = [];
		$.getJSON("${pageContext.request.contextPath}/empAttendance/readTree.do?t="+new Date().toString(),function(data){
			 $("#tree").treeview({
			  data: data.data,
			  levels: 5,
			  expanded:false,
			  selectedBackColor:"#f3f3f3",
			  selectedColor:"#333",
			  icon: "glyphicon glyphicon-stop",
			   onNodeSelected: function(event, data) {
			    ids=data.id;
			    var click_type;
				var searchParames={"id": ids,pageSize:pageSize}
				grid.search(searchParames);
			  }
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
		       			$(".edit").show();
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
					$(".basewindow").addClass("custom-scroll");
					$chuangkou.css({"display" : "block"}); 
 	
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}

			});
 
		$("#upLoad").click(function(){    
            window.location.href="${pageContext.request.contextPath}/finSalaries/export.do?date=${date}";    
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
        
	 //快速搜索
		$("#fast").change(function() {
		    var fastArg = $("#fast").val();
			var searchParames={
			    "fastArg": fastArg,
			    "id" : ids
			}
			grid.search(searchParames);
		})
	 //显示全部
	     $(".showAll").click(function(){
	            window.location.href="${pageContext.request.contextPath}/finemp/show.do?date=${date}&type=1";
	     })
	     /** 导出excel */
		$("#upLoad").click(function(){    
            window.location.href="${pageContext.request.contextPath}/finSalaries/exportFinSalariesExcel.do?date=${date}";    
        })
});
</script>
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
			<span class="color99">员工薪资</span>
			<div class="fr">
				<span id="upLoad">导出Excel</span>
			</div>
		</div>
		
		<div class="right-twoRight fr">
			<span class="riqitext">查看历史记录：</span>
			<div class="riqi">
				<span>${date}</span>
				<ul></ul>
			</div>
			<span class="search">
				<input type="text" id="fast" type="text" placeholder="姓名搜索"/> 
				<button type="button" id="seach" class="tableok">搜索</button>
			</span> 
		</div>
		<div >
		
		<div class="mains custom-scroll">
			<div class="lefttree custom-scroll">
				<table class="table" style=" min-width: 20%;">
					<tr>
						<th class="secNav" style="text-align:center">部门导航</th>
					</tr>
					<tr>
						<td id="tree" style="text-align:left;"></td>
					</tr>
				</table>
			</div>
			<div class="rightbox custom-scroll thistable">
				<table id="searchTable" class="table tablelist">
					<thead>
						<tr class="nolist firsttr" style="overflow:auto;">
							<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
							<th w_check="true" w_index="billed" width="1%;"></th>
							<th w_num="line" w_align="center" width="1%;">序号</th>
							<th w_index="number" w_align="center" width="1%;">员工号</th>
							<th w_index="name" w_align="center" width="1%;">姓名</th>
							<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
							<th w_index="icbcBank" w_align="center" width="1%;">工商银行</th>
							<th w_index="bocomBank" w_align="center" width="1%;">交通银行</th>
							<th w_index="realitySalary" w_align="center" width="1%;">实发工资</th>
							<th w_index="realExpenditure" w_align="center" width="1%;">公司实际支出</th>
							<th w_edit="billed" w_index="billed" w_align="center" width="1%;" rowspan="2">发放状态</th>
							<th w_index="bankCard" w_align="left" width="1%;">工资卡帐号</th>
							<th w_index="bankAddress" w_align="left" width="1%;">开户地址</th>
							<th w_edit="remarks" w_index="remarks" w_align="center">备注</th>
						</tr>
					</thead>
				</table>
				<p class="control">
					<jsp:include page="attsCommon.jsp"></jsp:include>
				</p>
			</div>
		</div>
	
		<div class="salaryLook" style="display:none">
			<table class="editTable clearFix" id="editTable">
				<tr style="background-color: #f3f3f3;">
					<th>基本信息</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td>员工号</td>
					<td><p class="number"></p>
						<p style="display:none;" id="id"></p></td>
					<td class="pdr">员工姓名</td>
					<td><p class="name"></p></td>
				</tr>
				<tr>
					<td>部门</td>
					<td>
						<p class="deptName"></p>
					</td>
					<td class="pdr">职位</td>
					<td><p class="positionsName"></p></td>
				</tr>
				<tr>
					<td>基本工资</td>
					<td class="subsidySalary"></td>
					<td class="pdr">计税工资</td>
					<td class="taxableSalary"></td>
				</tr>
	
				<tr>
					<td class="pdr">工商银行</td>
					<td class="icbcBank"></td>
	
					<td class="pdr">交通银行</td>
					<td class="bocomBank"></td>
	
				</tr>
				<tr>
					<td>本月出勤</td>
					<td><p class="attendance"></p></td>
					<td class="pdr">实发工资</td>
					<td class="realitySalary"></td>
				</tr>
	
				<tr style="background-color: #f3f3f3;">
					<th>个人社保缴纳</th>
					<th></th>
					<th class="pdr">合计:</th>
					<th class="pn_totalSocial"></th>
				</tr>
				<tr>
					<td>养老保险个人缴纳</td>
					<td><input type="number" class="pn_pension" /></td>
					<td class="pdr">医疗保险个人缴纳</td>
					<td><input type="number" class="pn_medicare"></td>
				</tr>
				<tr>
					<td>失业保险个人缴纳</td>
					<td><input type="number" class="pn_unemployment"></td>
					<td class="pdr">基本公积金个人缴纳</td>
					<td><input type="number" class="pn_provident"></td>
				</tr>
				<tr style="background-color: #f3f3f3;">
					<th>公司社保缴纳</th>
					<th></th>
					<th class="pdr">合计:</th>
					<th class="cp_totalSocial"></th>
				</tr>
				<tr>
					<td>养老保险公司缴纳</td>
					<td><input type="number" class="cp_pension"></td>
					<td class="pdr">医疗保险公司缴纳</td>
					<td><input type="number" class="cp_medicare"></td>
				</tr>
				<tr>
					<td>失业保险公司缴纳</td>
					<td><input type="number" class="cp_unemployment"></td>
					<td class="pdr">工伤保险公司缴纳</td>
					<td><input type="number" class="cp_occupationalInjury"></td>
				</tr>
				<tr>
					<td>生育保险公司缴纳</td>
					<td><input type="number" class="cp_fertility" /></td>
					<td class="pdr">基本公积金公司缴纳</td>
					<td><input type="number" class="cp_provident" /></td>
				</tr>
	
				<tr style="background-color: #f3f3f3;">
					<th>本月工资加项</th>
					<th></th>
					<th class="pdr">合计:</th>
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
					<td><p class="platSubsidy"></p></td>
					<td class="pdr">其他项目补贴</td>
					<td><input type="number" class="otherSubsidy" /></td>
				</tr>
				<tr>
					<td>提成</td>
					<td><p class="pushMoneySalary"></p></td>
					<td class="pdr">奖金</td>
					<td><input type="number" class="bonus" /></td>
				</tr>
	
				<tr style="background-color: #f3f3f3;">
					<th>本月工资扣项</th>
					<th></th>
					<th class="pdr">合计:</th>
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
					<td><p class="casualLeave"></p></td>
					<td class="pdr">其他扣款</td>
					<td><input type="number" class="otherDeduct" /></td>
				</tr>
				<tr>
					<td>项目扣款</td>
					<td><p class="platLeave"></p></td>
					<td class="pdr">个人所得税</td>
					<td><p class="individualIncomeTax"></p></td>
				</tr>
				<tr>
					<td class="pdr">备注</td>
					<td colspan="3"><input type="text" class="remarks" style="width:88%"></td>
				</tr>
				<tbody class="tbd" style="display:none">
					<tr style="background-color: #f3f3f3;">
						<th>直播收入</th>
						<th></th>
						<th class="pdr">合计:</th>
						<th class="pushMoneyActores"></th>
					</tr>
	
					<tr>
						<c:forEach var="item" items="${list}" varStatus="var">
							<tr>
								<td>${item.aname}</td>
								<td id="${item.aid}" class="item"></td>
								<td class="pdr">${item.bname}</td>
								<c:choose>
									<c:when test="${item.bname != null}">
										<td id="${item.bid}" class="item"></td>
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

			<p class="bCenter">
				<button class="tableok edit">编辑</button>
				<button class="tableok save" style="display:none">保存</button>
				<button class="tableok cancel">关闭</button>
	
			</p>
		</div>
		
		<div id="check" style="display:none">
			<div style="padding:5px 25px;width:100%">
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>员工：</span><span id="prom" style="color:#e35d00"></span><span>报表未通过，不可发放工资，点击确定将略过部分员工</span>
			</div>
			<p align="center">
				<button type="button" id="okokok" class="tableok okok">确定</button>
				<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div>
	</div>

</body>
</html>