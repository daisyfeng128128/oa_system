<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>星探</title>
    
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
		color:#333
		} 
		.editTable p{margin:0}
		.editTable tr td{border-bottom:1px solid #ddd; font-size: 14px; width:10%;height:30px}
		.editTable tr td:first-child,tr th:first-child,.pdr{text-align:right;padding-right:40px;}
		.salarylook{width:100%;height:40%}
		
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
		.thistable{
			overflow:auto; background-color:#fff;
		}
		
	</style>
	<script type="text/javascript">
	$(function () {
        if("${isEdit}" == "true"){
    		$(".okok").show();
    	}else{
    		$(".okok").hide();
    	}
        $(".searchAll").hide();
        $("#partUpLoad").hide();
        $(".showAll").hide();
        $(".save").hide();
        var flag = ${isEdit};
        if(!flag){
            $(".check").hide();
            $(".payoff").hide();
            $(".views").hide();
            $(".salaryLook .edit").hide();
        }
        $(".right-twohalf .riqi>span").click(function () {
    		if (!$(".right-twohalf .riqi ul").is(":animated")) {
    			$(".right-twohalf .riqi ul").fadeToggle();
           	}
        });
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/finSalariesTalent/readPages.do?date=${date}&all=${all}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit: ${isEdit},
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                    	bill: {
    	                    build: function (edit, value) {
    	                        if(value=="已发"){
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
        
        /* //提交
        $(".save").click(function () {
            var shuzu=[];
            if(grid.getChangedRowsIndexs()!=''){
                $(grid.getChangedRowsIndexs()).each(function(){
                    var self=this;
                    var data={
                        id:$(".thistable tbody tr").eq(self).find("td").eq(0).text(),
                        taSalary:$(".thistable tbody tr").eq(self).find(".taSalary").val()
                    }
                    shuzu.push(data);
                })
			  $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/finSalariesTalent/save.do",
                data:JSON.stringify(shuzu),
                cache: false,
                contentType: 'application/json; charset=utf-8',
                success:function(){
                    $.threesecond("保存成功", 200, 100, 1000);
                    grid.refreshPage();
                }
            })
			 }
        }); */
        
        //计算
        $(".check").click(function () {
                $.yesorno("是否开始计算？", 300, 160, function () {
        			 //ajax
        			$.ajax({
                        type: "POST",
                        dataType:"json",
                        url: "${pageContext.request.contextPath}/finSalaries/check.do",
                        data:"date=${date}",
                        success:function(){
		                     $.timeOut("计算需耗时约<i>30</i>秒，请稍等后刷新", 200, 100, 30);
		                     grid.refreshPage();
                        }
                    })
                });
        });
        
		     //快速搜索
		$("#fast").change(function() {
			    var fastArg = $("#fast").val();
				var searchParames={
				    "fastArg": fastArg,
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
	   	           url:  "${pageContext.request.contextPath}/finSalariesTalent/grant.do",
	   	           data: "id="+ids,
	   	           success : function(data){
	   	               if(data.resultStatus == 200){
	   						$.threesecond("发放成功", 200, 100, 1000);
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
			$(".salaryLook .cancel").show();
			$(".salaryLook .save").show(); 
			$(".salaryLook .edit").hide();
			
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
				var editurl = "${pageContext.request.contextPath}/finSalariesTalent/edit.do?t="+ new Date().toTimeString();
				  $.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) { 　
						$("#editTable tr td span").remove();  
		       			$("#editTable th td span").remove();　 
		       			$(".salaryLook .edit").show();
		       			$(".salaryLook .save").hide();
		       			 
		       			$(".editTable #id").val(data.id);
						$(".name").text(data.name);
						$(".aliasname").text(data.aliasname);
						$(".sex").text(data.sex);
						$(".bankCard").text(data.bankCard);
						$(".bankAddress").text(data.bankAddress);
						$(".taSalary").val(data.taSalary);
						$(".otherSubsidy").val(data.otherSubsidy);
						$(".otherDeduct").val(data.otherDeduct);
						$(".remarks").val(data.remarks);
						$(".realitySalary").text(data.realitySalary);
						$(".realExpenditure").text(data.realExpenditure);
				       	 $("#editTable tr td input").each(function(){ 
				       	 	val=$(this).val(); 
				       	 	$(this).after("<span>"+val+"</span>"); 
				       	 })
				       	 $("#editTable tr td input").hide(); 
				       	 $("#editTable th td input").hide();  
					
					},error : function(data) {
					}
				});  
					　
					$.basewindow("详细资料", $chuangkou,715, 380);
					$(".basewindow").addClass("custom-scroll");
					$chuangkou.css({"display" : "block"}); 
	
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}

			});
		$(".salaryLook .save").click(function () {
              var taSalary = $(".editTable .taSalary").val();
              var id =$(".editTable #id").val();
              var remarks =$(".editTable .remarks").val();
              var otherSubsidy =$(".editTable .otherSubsidy").val();
              var otherDeduct =$(".editTable .otherDeduct").val();
			  $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/finSalariesTalent/saveFin.do",
                data:"id="+id+"&taSalary="+taSalary+"&remarks="+remarks+"&otherSubsidy="+otherSubsidy+"&otherDeduct="+otherDeduct,
                cache: false,
                success:function(){
                	$.threesecond("保存成功", 200, 100, 1000);
					
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$('body').append($(".salaryLook"));
					$(".salaryLook").hide(); 
					grid.refreshPage();
                }
            })
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
		<div class="thistable custom-scroll">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="sex" w_align="center" width="1%;">性别</th>
					<th w_index="realitySalary" w_align="center" width="1%;">实发工资</th>
					<th w_index="realExpenditure" w_align="center" width="1%;">公司实际支出</th>
					<th w_edit="bill" w_index="bill" w_align="center" width="1%;" rowspan="2">发放状态</th>
					<th w_index="bankCard" w_align="center" width="1%;">工资卡账号</th>
					<th w_index="bankAddress" w_align="center" width="1%;">开户行地址</th>
					<th w_index="remarks" w_align="center">备注</th>
				</tr>
			</table>
			
			<p style="text-align:center;position: fixed; left: 50%; bottom: 1%; margin-left:-174px; "><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
			
		</div>
		
		<div class="salaryLook" style="display:none"> 
			<table class="editTable" id="editTable"> 
				<tr style="background-color: #f3f3f3; height:30px; ">
				<th>基本信息</th><th></th><th></th><th></th> 
				</tr>
				<input type="hidden" id= "id">
				<tr>
					<td>姓名</td><td class="name"></td> 
					<td class="pdr">艺名</td><td class="aliasname"></td>
				</tr>
				<tr>
					<td>性别</td><td class="sex"></td>
					<td class="pdr">星探工资</td><td><input type="number"  class="taSalary" /></td>
				</tr>
				<tr>
					<td>其他补贴</td><td><input type="number"  class="otherSubsidy" /></td>
					<td class="pdr">其他扣款</td><td><input type="number"  class="otherDeduct" /></td>
				</tr>
				<tr>
					<td>实发工资</td>
					<td class="realitySalary"></td> 
					<td class="pdr">公司实际支出</td>
					<td class="realExpenditure"></td>
				</tr>
				<tr> 
					<td >工资卡账号</td>
					<td class="bankCard"></td> 
					<td class="pdr">开户行地址</td>
				    <td class="bankAddress"></td>
				</tr>
				<tr> 
					<td >备注</td>
					<td colspan="3"><input type="text"  class="remarks" /></td>
				</tr>
			</table> 
			<div class="clear"></div>
			<p style="text-align:center;">
		 			 <button class="tableok edit">编辑</button> <button class="tableok save"  style="display:none" >保存</button>
		 			<button class="tableok cancel" id="bClose">关闭</button>
		 			
			</p>
		</div>
	</div>
	

	
  </body>
</html>
