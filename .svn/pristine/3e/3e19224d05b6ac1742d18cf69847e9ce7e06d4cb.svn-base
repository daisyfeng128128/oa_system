<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>艺人薪资(线上主播)</title>
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
			border:1px solid #ddd;
			margin: 10px 20px;
			border-spacing: 0px;
			color:#333;
} 
.editTable p{margin:0}
.editTable tr td{border-bottom:1px solid #ddd; font-size: 14px; width:10%;height:30px}
.editTable tr td:first-child,tr th:first-child,.pdr{text-align:right;padding-right:40px;}
.salarylook{width:100%;height:80%}

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
	$(function () {
	    $(".right-twohalf .riqi>span").click(function () {
    		if (!$(".right-twohalf .riqi ul").is(":animated")) {
    			$(".right-twohalf .riqi ul").fadeToggle();
           	}
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
	    
	    if(${type} != null && ${type} ==3){
            $(".searchAll").hide();
            $("#partUpLoad").hide();
        }else if(${type}!= null && ${type} == 4){
            $(".searchAll").show();
            $("#partUpLoad").show();
        }
        var flag = ${isEdit};
        if(!flag){
            $(".payoff").hide();
            $(".edit").hide();
            $(".save").hide();
            $(".check").hide();
        }
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/finSalariesOnline/readPages.do?date=${date}&type=${type}&all=${all}",
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
    	                        if(value=="UNKNOWN"){
    	                            return '<span style="color:#e35d00">未通过</span>';
    	                        }else if(value=="UNBILLED"){
    	                            return '<span style="color:#e35d00">未发</span>';
    	                        }else if(value=="PUBLISHED") {
    	                            return '<span style="color:#0074ac">已发</span>';
    	                        }
    	                    }
    	                }
                    } 
                }
            }
        });
        //提交
        $(".save").click(function () {
            var otherSubsidy =$("#otherSubsidy").val();
       		var otherDeduct =$("#otherDeduct").val();
       		var id =$("#id").val();
       		var remarks =$("#remark").val();
			  $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/finSalariesOnline/save.do",
                data:"otherSubsidy="+otherSubsidy+"&otherDeduct="+otherDeduct+"&id="+id+"&remarks="+remarks,
                cache: false,
                success:function(){
                    $.threesecond("保存成功", 200, 100, 1000);
					
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$('body').append($(".salaryLook"));
					$(".salaryLook").hide(); 
					grid.refreshPage();
					$(".cancel").show();
					$(".edit").show(); 
					$(".save").hide();
                }
            })
        });
        
        //计算
        $(".check").click(function () {
                $.yesorno("是否开始计算？", 300, 160, function () {
        			 //ajax
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
      //详细
		$(".views").click(function() {
			var $chuangkou = $(".salarylook");
			var isActores = ${type};
			$(".item").text("0.00");
			if (grid.getSelectedRow().length > 0) {
				var id = grid.getRowRecord(grid.getSelectedRow()).id; 
				var editurl = "${pageContext.request.contextPath}/finSalariesOnline/edit.do?t="+ new Date().toTimeString();
				  $.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) { 　　
					    $("#editTable tr td span").remove();  
		       			$("#editTable th td span").remove();　 
		       			$("#id").val(data.id);
						$("#name").text(data.name);
						$("#remarks").text(data.remarks);
						$("#aliasname").text(data.aliasname);
						$("#basicSalary").text(data.basicSalary);
						$("#realitySalary").text(data.realitySalary);
						$("#pushMoney").text(data.pushMoney);
						$("#pushMoneySalary").text(data.pushMoneySalary);
						$("#pushMoney").text(data.pushMoney);
						$("#attendance").text(data.attendance);
						$("#platDeduct").text(data.platDeduct);
						$("#otherDeduct").val(data.otherDeduct);
						$("#individualIncomeTax").text(data.individualIncomeTax);
						$("#actoresIncomeTax").text(data.actoresIncomeTax);
						$("#deductTax").text(data.deductTax);
						$("#platSubsidy").text(data.platSubsidy);
						$("#otherSubsidy").val(data.otherSubsidy);
						$("#remark").val(data.remark);
						$("#totalIncome").text(data.totalIncome);
						 $("#editTable tr td input").each(function(){ 
					       	 	val=$(this).val(); 
					       	 	$(this).after("<span>"+val+"</span>"); 
					       	 	  
					       	 })
						if(isActores == 4){
							if(data.details != null){
							    $(".tbd").show();
							    $.each(data.details, function(name, value) { 
									$("#"+name).text(value);
								});
							}
						}else{
							$(".tbd").hide();
						}
						 $("#editTable tr td input").hide(); 
				       	 $("#editTable th td input").hide();  
					},
					error : function(data) {
					}
				}); 
					var height = 740;
					if("${type}" == "3"){
						height = 440;
						$(".tbd").css({"display" : "none"});
					}
					$.basewindow("详细资料", $chuangkou, 880, height);
					$chuangkou.css({"display" : "block"}); 
					 
			       	var table = document.getElementById("editTable");
			       	// 获取 table 内的全部 input
			       	var textinputs = table.getElementsByTagName('input');
			       	var textSpan = table.getElementsByTagName('span');
			       	// 循环
			       	for(var i = 0; i < textinputs.length; i++) { 
			       		textinputs[i].parentNode.innerHTML +='<span>'+ textinputs[i].value+'</span>';
			       	}
			       	
					$("#editTable tr td input").hide(); 
			       	$("#editTable th td input").hide();
 	
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}

			});
      		//显示全部
			 $(".showAll").click(function(){
		            window.location.href="${pageContext.request.contextPath}/finSalariesOnline/show.do?date=${date}&type=${type}&all=1";
		     });
        
		     //快速搜索
			$("#fast").change(function() {
			    var fastArg = $("#fast").val();
				var searchParames={
				    "fastArg": fastArg,
				    "type" : ${type},
				    "all":${all}
				}
				grid.search(searchParames);
			});
		    $(".payoff").click(function(){
		        var ids =  grid.getCheckedValues('id');
		        if(ids != ''){
		   	         $.ajax({
		   	           type : "POST",
		   	           cache : false,
		   	           url:  "${pageContext.request.contextPath}/finSalariesOnline/grantCheck.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	               if(data.resultStatus == 200){
				   	  	   	      $.ajax({
				   	  	   	           type : "POST",
				   	  	   	           cache : false,
				   	  	   	           url:  "${pageContext.request.contextPath}/finSalariesOnline/payoff.do",
				   	  	   	           data: "id="+ids,
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
		    });
		    
		    // 发工资确定
	        $("#okokok").click(function(){
	            var ids =  grid.getCheckedValues('id');
	            if(ids != ''){
	  	   	      	$.ajax({
	  	  	   	           type : "POST",
	  	  	   	           cache : false,
	  	  	   	           url:  "${pageContext.request.contextPath}/finSalariesOnline/payoff.do",
	  	  	   	           data: "id="+ids,
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
			
			  //搜索
			$(".searchAll").click(function() {
				var $chuangkou = $("#sousuo");
				$.basewindow("高级搜索", $chuangkou, 400, 200);
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
					$.basewindow("高级搜索", $chuangkou, 400, 200);
					$chuangkou.css({"display" : "block"}); 
			 })
			 
				 //提交搜索
			$("#ok").click(function() { 
				var sex = $("#sexy").val();
				var plat = $("#platId").combogrid("getValue");
				window.location.href="${pageContext.request.contextPath}/finSalariesOnline/partExcel.do?date=${date}&sex="+sex+"&plat="+plat;    
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
				<span id="upLoad">导出Excel</span>
			</div>
		</div>
		<jsp:include page="finSalariesCommon.jsp"></jsp:include>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_check="true" w_index="billed" width="3%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="basicSalary" w_align="center" width="1%;">保底</th>
					<th w_index="realitySalary" w_align="center" width="1%;">实发工资</th>
					<th w_index="realExpenditure" w_align="center" width="1%;">公司实际支出</th>
					<th  w_edit="billed" w_index="billed" w_align="center" width="1%;" rowspan="2">发放状态</th>
					<th w_index="bankCard" w_align="left" width="13%;">工资卡账号</th>
					<th w_index="bankAddress" w_align="left" width="15%;">开户行地址</th>
					<th  w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
			<p style="text-align:center;position: fixed; left: 50%; bottom: 1%; margin-left:-232px;"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
		</div>
		 <div class="salarylook" style="display:none">
				<table class="editTable" id="editTable"> 
					<tr style="background-color: #599eeb;">
						<th> 基本信息</th><th></th><th></th><th></th> 
					</tr>
					<tr>
						<td>姓名</td>
					    <td id ="name"></td>
					    <td class="pdr">艺名</td>
					    <td id="aliasname"></td>
					</tr>
					<tr>
						<td>保底工资</td>
					    <td id="basicSalary"></td>
					    <td class="pdr">实发工资</td>
					    <td id="realitySalary"></td>
					</tr>
					<tr>
						<td>出勤天数</td>
						<td id="attendance" colspan="3"></td>
					</tr>
					<tr style="background-color: #599eeb;">
						<th>本月工资扣减项</th>
						<th></th>
						<th class="pdr"></th>
						<th></th> 
					</tr>
					<tr>
						<td>平台扣款</td>
					    <td id="platDeduct"></td>
					    <td class="pdr">其他扣款</td>
					    <td ><input id="otherDeduct" type ="number"></td>
					</tr>
					<tr>
					    <td >分成扣税</td>
					    <td id="deductTax"></td>
					    <td class="pdr">劳务服务费</td>
					    <td><p id="actoresIncomeTax"></p></td>
					</tr>
					<tr style="background-color: #599eeb;">
						<th>本月工资加项合计</th><th></th><th class="pdr"></th> 
					  	<th></th> 
					</tr>
					<input type="hidden" id="id"> 
					<tr>
						<td>平台补贴</td>
					    <td id="platSubsidy"></td>
					    <td class="pdr" >其他补贴</td>
					    <td ><input id="otherSubsidy" type ="number"></td>
					</tr>
					<tr>
						<td>备注</td>
						<td colspan="3" ><input id="remark" type="text"></td>
					</tr>
					<tbody  class="tbd" style="display:none">
						<tr style="background-color: #599eeb;">
							<th>直播收入</th><th></th><th class="pdr"></th> 
						  	<th></th> 
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
						    <td id="totalIncome"></td>
						    <td class="pdr">提成比例</td>
						    <td id="pushMoney"></td>
						</tr>
						<tr>
							<td>提成工资</td>
						    <td id="pushMoneySalary"></td>
						    <td class="pdr"></td>
						    <td></td>
						</tr> 
					</tbody>
				</table>
				<p style="text-align:center;"><button class="tableok edit">编辑</button> <button class="tableok save"  style="display:none" >保存</button>
				<button class="tableok cancel" >关闭</button></p>
	 	</div>
	 	
	 	<!-- 高级搜索 -->
		<form action="${pageContext.request.contextPath}/emp/search.do" method="post" enctype="multipart/form-data" id="sousuo" style="display:none;margin:0 20px;">
	
			<table class="table">
				<tr>
					<td style="width:50%;padding:0;border:none;"></td>
					<td style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">直播平台：</td>
					<td><input name="plat" id="plat" style="width:200px;"> </td>
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
				<input class="cancel" type="reset" name="Input" value="关闭">
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
