<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>采购审核</title>
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
.mustred {margin-left: 10px;color: #ff0000;}
.mains{width:100%;min-width:1000px;overflow: hidden; background-color:#fff; }
.lefttree{width:15%;margin-right:15px;height:800px;float:left; overflow:scroll; }
.rightbox{width:85%; float:right;overflow:auto;margin-top: -800px;  }

	.mustred{margin-left:10px;color:#ff0000}
	.ok{
	    border: 1px solid #ddd;
        text-align: center;
        height: 38px;
        line-height: 38px;
        display: inline-block;
        color: #fff;
        background-color:#599eeb;
        width: 90px;
        border-radius: 5px;
        margin-right: 20px;
        cursor: pointer;
   		margin-top:20px; 
	}  
	.control{
		margin-left:-174px;
	}
	#qq{
		margin-left:-20px; 
	}
	.okok{
	background-color:#ccc1d9;
	}
	.right-twohalf{
	margin-top:0;
	}
	
	.tabBtn1{
		width: 96px;
		height: 32px;
		text-align: center;
		line-height: 32px;
		background-color: #9370DB;
		color: #fff;
		border: none;
		display: inline-block;
		margin-right: 20px;
		border-radius: 2px;
	}

</style>
<script>
$(function () {
	 $(".equipass").hide();
	
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
	 
	 $(".control .deptpass").click(function() {
	 		var ids =  grid.getCheckedValues('id');
           	if(ids != ''){
				var $chuangkou = $("#deptpass");
				$.basewindow("是否确认部门审核通过?", $chuangkou, 280, 200);
				$(".basewindow").addClass("custom-scroll");
			}else{
				$.threesecond("请勾选中数据", 200, 100, 1000);
			}
		});
	
		$("#deptpass #ok").click(function(){
				var ids =  grid.getCheckedValues('id');
           		if(ids != ''){
           			var proc =  grid.getCheckedValues('process');
           			var p = new Array();
					for(var i=0;i<proc.length;i++){
						var process=proc[i];
						if(process == "1"){
							p.push(process);
						}
					}
					if(proc.length == p.length){
		   	         $.ajax({
		   	           type : "POST",
		   	           cache : false,
		   	           url:  "${pageContext.request.contextPath}/purchaseAudit/deptpassOff.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	              			if(data.resultStatus == 200){
		   	              			$.threesecond("部门审核通过!", 200, 100, 1000);
	  	  	   						grid.refreshPage();
	  	  	   					}
	  	  	   	           }
	  	  	   	      })
	  	  	   	     }else{
				   	    $.threesecond("审核状态不相同,请选择正确的状态",200, 100, 1000);
				   	} 
	            }else{
		   	         $.threesecond("未选中数据", 200, 100, 1000);
		   	     }
		    });
 		
 		$("#personpass #ok").click(function(){
 			var ids =  grid.getCheckedValues('id');
           		if(ids != ''){
           			var proc =  grid.getCheckedValues('process');
           			var p = new Array();
					for(var i=0;i<proc.length;i++){
						var process=proc[i];
						if(process == "2"){
							p.push(process);
						}
					}
					if(proc.length == p.length){
		   	         $.ajax({
		   	           type : "POST",
		   	           cache : false,
		   	           url:  "${pageContext.request.contextPath}/purchaseAudit/personpassOff.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	              			if(data.resultStatus == 200){
		   	              			$.threesecond("负责人审核通过成功!", 200, 100, 1000);
	  	  	   						grid.refreshPage();
	  	  	   					}
	  	  	   	           }
	  	  	   	      })
	  	  	   	    }else{
				   	    $.threesecond("审核状态不相同,请选择正确的状态",200, 100, 1000);
				   	} 
	            }else{
		   	         $.threesecond("未选中数据", 200, 100, 1000);
		   	     }
		    });
           
		$("#headpass #ok").click(function(){
			var ids =  grid.getCheckedValues('id');
           		if(ids != ''){
           			var proc =  grid.getCheckedValues('process');
           			var p = new Array();
					for(var i=0;i<proc.length;i++){
						var process=proc[i];
						if(process == "3"){
							p.push(process);
						}
					}
					if(proc.length == p.length){
		   	         $.ajax({
		   	           type : "POST",
		   	           cache : false,
		   	           url:  "${pageContext.request.contextPath}/purchaseAudit/headpassOff.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	              			if(data.resultStatus == 200){
	                   				$.threesecond("总公司审核通过成功!", 200, 100, 1000);
	  	  	   						grid.refreshPage();
	  	  	   					}
	  	  	   	           }
	  	  	   	      })
	  	  	   	   }else{
				   	    $.threesecond("审核状态不相同,请选择正确的状态",200, 100, 1000);
				   	} 
	            }else{
		   	         $.threesecond("未选中数据", 200, 100, 1000);
		   	     }
		    });
 		
			$(".control .personpass").click(function () {
				var ids =  grid.getCheckedValues('id');
           		if(ids != ''){
		          var $chuangkou = $("#personpass");
				  $.basewindow("是否确认负责人审核通过?", $chuangkou, 280, 200);
				  $(".basewindow").addClass("custom-scroll");
				 }else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				 }
		       });
		        
			$(".control .headpass").click(function () {
				var ids =  grid.getCheckedValues('id');
           		if(ids != ''){
		            var $chuangkou = $("#headpass");
					$.basewindow("是否确认总公司审核通过?", $chuangkou, 280, 200);
					$(".basewindow").addClass("custom-scroll");
				}else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
		      });
           
           $("#deptpass #jujue").click(function(){
		         $('body').append($("#deptpass"));
				 $("#deptpass").hide();
		         $(".basewindow").hide();
		         $(".zhezhao-basewindow").hide(); 
		    	 var $chuangkou=$("#rejectE"); 
	    	     $.basewindow("是否确认审核拒绝?", $chuangkou, 320, 300); 
	             $(".basewindow").addClass("custom-scroll");
	    	});  
           $("#personpass #jujue").click(function(){
		         $('body').append($("#personpass"));
				 $("#personpass").hide();
		         $(".basewindow").hide();
		         $(".zhezhao-basewindow").hide(); 
		    	 var $chuangkou=$("#rejectE"); 
	    	     $.basewindow("是否确认审核拒绝?", $chuangkou, 320, 300); 
	             $(".basewindow").addClass("custom-scroll");
	    	});  
           $("#headpass #jujue").click(function(){
		         $('body').append($("#headpass"));
				 $("#headpass").hide();
		         $(".basewindow").hide();
		         $(".zhezhao-basewindow").hide(); 
		    	 var $chuangkou=$("#rejectE"); 
	    	     $.basewindow("是否确认审核拒绝?", $chuangkou, 320, 300); 
	             $(".basewindow").addClass("custom-scroll");
	    	});  
		   
		   $("#rejectE #okok").click(function(){
	           var ids =  grid.getCheckedValues('id');
	           if(ids != ""){
	           		var id;
					for(var i=0;i<ids.length;i++){
					     id=ids[i];
					}
		            var rejectMsg = $("#rejectE #rejectMsg").val();
		            var reject = {
	                "id":id,
	                "rejectMsg":rejectMsg
	            	};
		            $.ajax({
		                type: "POST",
		                url: "${pageContext.request.contextPath}/purchaseAudit/rejectOff.do",
		                data: JSON.stringify(reject),
		                contentType: "application/json; charset=utf-8",
		                cache: false
		            }).done(function (data) {
		                if (data.resultStatus == 200) {
		                    $.threesecond("审核已拒绝!", 200, 100, 1000);
		                    grid.refreshPage();
		                }
		            }).error(function (jqXHR, textStatus, errorThrown) { });
	              }else{
				$.threesecond("请勾选中数据", 200, 100, 1000);
			  }
           });
                

		$(".control .start").click(function () {
			var ids =  grid.getCheckedValues('id');
				if(ids != ""){
					var proc =  grid.getCheckedValues('process');
           			var p = new Array();
					for(var i=0;i<proc.length;i++){
						var process=proc[i];
						if(process == "4"){
							p.push(process);
						}
					}
					if(proc.length == p.length){
					$.yesorno("确认开始处理吗?",300,160,function(){
						 $.ajax({
		   	           type : "POST",
		   	           cache : false,
		   	           url:  "${pageContext.request.contextPath}/purchaseAudit/startOff.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	              			if(data.resultStatus == 200){
		   	              			$.threesecond("开始处理成功!", 200, 100, 1000);
	  	  	   						grid.refreshPage();
	  	  	   					}
	  	  	   	           }
	  	  	   	      }) 
					})
					}else{
				   	    $.threesecond("所处状态不相同,请选择正确的状态",200, 100, 1000);
				   	}
				}else{
				$.threesecond("请勾选中数据", 200, 100, 1000);
			}
        });
	        
	    $(".control .end").click(function () {
	   		 var ids =  grid.getCheckedValues('id');
             if(ids != ''){
	            var $chuangkou = $("#remark");
	            $.basewindow("是否确认完成处理?", $chuangkou, 300,320);
				$(".basewindow").addClass("custom-scroll");
			}else{
				$.threesecond("请勾选中数据", 200, 100, 1000);
			}
        });
        
        $("#remark #ok").click(function(){
           		var ids =  grid.getCheckedValues('id');
	           	if(ids != ""){
	           		var proc =  grid.getCheckedValues('process');
           			var p = new Array();
					for(var i=0;i<proc.length;i++){
						var process=proc[i];
						if(process == "5"){
							p.push(process);
						}
					}
					if(proc.length == p.length){
	           		var id;
					for(var i=0;i<ids.length;i++){
					     id=ids[i];
					}
	           		var remarks = $("#remark #remarks").val();
		            var rejectMsg = {
		                "id":id,
		                "remarks":remarks
		            };
		            $.ajax({
		                type: "POST",
		                url: "${pageContext.request.contextPath}/purchaseAudit/endOff.do",
		                data: JSON.stringify(rejectMsg),
		                contentType: "application/json; charset=utf-8",
		                cache: false
		            }).done(function (data) {
		                if (data.resultStatus == 200) {
		                    $.threesecond("完成处理!", 200, 100, 1000);
		                    grid.refreshPage();
		                }
		            }).error(function (jqXHR, textStatus, errorThrown) { });
		            }else{
				   	    $.threesecond("所处状态不相同,请选择正确的状态",200, 100, 1000);
				   	}
		           }else{
					$.threesecond("请勾选中数据", 200, 100, 1000);
				}
           });
	        
	 
		$(".views").click(function() {
	 		var $chuangkou = $("#purlook");
			var id =  grid.getCheckedValues('id');
			if (id != "") {
					 var editurl = "${pageContext.request.contextPath}/purchaseApplication/editOfficeSupplies.do";
					$.ajax({
						type : "get",
						dataType : "json",
						url : editurl,
						data : "id=" + id,
						show : "slide",
						success : function(data) {
							  var $data = data.data; 
								$("#purlook #realname").text(data.realname);
								$("#purlook #num").text(data.num);
								$("#purlook #dept").text(data.depart);
								$("#purlook #applyDT").text(data.applyDT);
								$("#purlook #emodel").text(data.model);
								$("#purlook #egoodsname").text(data.goodsname);
								$("#purlook #epriceMoney").text(data.priceMoney);
								$("#purlook #enumbers").text(data.numbers);
								$("#purlook #etaxMoney").text(data.taxMoney);
								$("#purlook #etotalMoney").text(data.totalMoney);
						},
						error : function(data) {
						}
					});
					$.basewindow("详细资料", $chuangkou,540,320);
					$chuangkou.css({
						"display" : "block"
					});
					} else {
						$.threesecond("请先选择", 200, 100, 1000);
					}
				});
	
		var grid = $.fn.bsgrid.init("searchTable", {
	    	url: "${pageContext.request.contextPath}/purchaseAudit/readPagesR.do?&branchs=${branchs}",
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
						auditp:{
						build:function(edit,value,record, rowIndex, colIndex, options){
							if (value =="未通过")
								return "<span style='color:red'>"+value+"</span><br/><a style='color:blue;' href='#' onclick='seanot("+record.id+")' >查看原因</a>"
							if (value =="待审核")
								return "<span style='color:#0074ac'>"+value+"</span>"
							if (value =="通过")
								return "<span style='color:green'>"+value+"</span>"
							if (value =="审核中")
			      			  	return  "<span style='color:red'>"+value+"</span><br/><a style='color:blue;' href='#' onclick='sea("+record.id+")' >审核进度</a>"
						}
					},
						purchase:{
							build:function(edit,value){
								if(value=="待采购")
								return "<span style='color:#0074ac'>"+value+"</span>"
								if(value=="采购中")
									return "<span style='color:#e35d00'>"+value+"</span>"
								if(value=="采购完成")
									return "<span style='color:#ccc1d9'>"+value+"</span>"
							}
						}
					}
				}        	
	        } 
	    });
	    
	     var ids ="";
	    this.tree = [];
			$.getJSON("${pageContext.request.contextPath}/empAttendance/readTree.do?t="+new Date().toString(),function(data){
				 $("#tree").treeview({
				  data: data.data,
				  levels: 5,
				  expanded:false,
				  selectedBackColor:"#f3f3f3",
				  selectedColor:"#333",
				  icon: "glyphicon glyphicon-stop",
	  			  //selectedIcon: "glyphicon glyphicon-stop",
				  onNodeSelected: function(event, data) {
				    ids=data.id;
				    var click_type;
				 		//加载数据
					var searchParames={
							    "id": ids
							}
					grid.search(searchParames);
				  }
				});	 
			});
			
			
			//快速搜索
			$("#fast").change(function() {
			    var fastArg = $("#fast").val();
				var searchParames={
				    "fastArg": fastArg,
				    "id":ids
				}
				grid.search(searchParames);
			})
			
			$(".control2 .all").click(function() {
			    var fastArg = "";
				var searchParames={
				    "classQuery": fastArg
				}
				grid.search(searchParames);
			})
			$(".control2 .dai").click(function() {
			    var fastArg = "PENDING";
				var searchParames={
				    "classQuery": fastArg
				}
				grid.search(searchParames);
			})
			$(".control2 .cent").click(function() {
			    var fastArg = "UNKNOWN";
				var searchParames={
				    "classQuery": fastArg
				}
				grid.search(searchParames);
			})
			$(".control2 .pass").click(function() {
			    var fastArg = "PASSED";
				var searchParames={
				    "classQuery": fastArg
				}
				grid.search(searchParames);
			})
			$(".control2 .not").click(function() {
			    var fastArg = "NOTPASS";
				var searchParames={
				    "classQuery": fastArg
				}
				grid.search(searchParames);
			})
			
			$("#searchTable").click(function() {
				if (grid.getSelectedRow().length > 0) {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;
					var branchs = grid.getRowRecord(grid.getSelectedRow()).branchs;
					var purType = grid.getRowRecord(grid.getSelectedRow()).purType;
					var passType = grid.getRowRecord(grid.getSelectedRow()).passType;
					var auditp = grid.getRowRecord(grid.getSelectedRow()).auditp;
					var purchase = grid.getRowRecord(grid.getSelectedRow()).purchase;
					$(".thistable .deptpass, .thistable .personpass, .thistable .headpass,.thistable .start,.thistable .end").attr("disabled","true").addClass("tablecancel");
						if(auditp == "待审核"){
						        $(".thistable .deptpass").removeAttr("disabled").removeClass("tablecancel");
						}else if(auditp == "通过"){
						        if(purchase == "待采购"){
									$(".thistable .start").removeAttr("disabled").removeClass("tablecancel");
						        }else if(purchase == "采购中"){
						        	 $(".thistable .end").removeAttr("disabled").removeClass("tablecancel");
						        } else if(purchase == "采购完成"){
						        	$(".thistable .deptpass, .thistable .personpass, .thistable .headpass,.thistable .start,.thistable .end").attr("disabled","true").addClass("tablecancel");
						        } 
						}else if(auditp == "未通过"){
								$(".thistable .deptpass, .thistable .personpass, .thistable .headpass,.thistable .start,.thistable .end").attr("disabled","true").addClass("tablecancel");
						}else if(auditp == "审核中"){
								if(passType == "1"){
									$(".thistable .personpass").removeAttr("disabled").removeClass("tablecancel");
								}else if(passType == "2"){
									$(".thistable .headpass").removeAttr("disabled").removeClass("tablecancel");
								}else if(passType == "3"){
									$(".thistable .start").removeAttr("disabled").removeClass("tablecancel");
								}
						    }
						 }
					});
					
		});

		function seanot(id){
    			var id = id;
    			var rejectMsg = {
	                "id":id
	            };
                	$.ajax({
	                    type: "POST",
	                    url: "${pageContext.request.contextPath}/purchaseAudit/notPassOff.do",
	                    data: JSON.stringify(rejectMsg),
						contentType: "application/json; charset=utf-8",
						cache: false
	                  }).done(function (data) {
	                	if(data.resultStatus == 200){
                            $.basewindow("未通过原因", "#notpass", 400, 200);
                             $("#notpass #rejectMsg").html(data.exData);
	                	}else{
	                	    $.threesecond("获取失败!", 200, 100, 1000);
	                	    return false;
	                	}
	                })
				return true;
			};
			function sea(id){
    			var id = id;
    			var rejectMsg = {
	                "id":id
	            };
                	$.ajax({
	                    type: "POST",
	                    url: "${pageContext.request.contextPath}/purchaseAudit/checkOff.do",
	                   	data: JSON.stringify(rejectMsg),
						contentType: "application/json; charset=utf-8",
						cache: false
	                  }).done(function (data) {
	                	if(data.resultStatus == 200){
                            $.basewindow("审核进度", "#checkEl", 350, 250);
                            if(data.exData != null ){
                            var $apply=data.exData.split("</br>");
	                		
							for(var i=0;i<$apply.length;i++){
								$("#checkEl #reason").append("<tr></tr>");
							}
							$("#reason tr").each(function(i, item) {
						        $("#reason tr").eq(i).html($apply[i]);
						    });
                          }
	                	}else{
	                	    $.threesecond("获取失败!", 200, 100, 1000);
	                	    return false;
	                	}
	                })
				return true;
			};
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
				<span>行政系统</span> 
				<span>></span>
				<span class="color99">采购审核</span>
			</div>
			
	<jsp:include page="purchaseAuditCommon.jsp"></jsp:include>
	<div class="custom-scroll mains">
		<div class="lefttree custom-scroll" >
				<table class="table" style="min-width: 20%;">
					<tr><th align="center">部门导航</th></tr>
					<tr><td id="tree" style="text-align:left;"></td></tr>
				</table>
			</div>
		<div  class="rightbox thistable custom-scroll">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_index="branchs" w_align="center" width="1%;" w_hidden="true">branchs</th>
					<th w_index="purType" w_align="center" width="1%;" w_hidden="true">purType</th>
					<th w_index="passType" w_align="center" width="1%;" w_hidden="true">passType</th>
					<th w_index="process" w_align="center" width="1%;" w_hidden="true">process</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="num" w_align="center" width="1%;">员工号</th>
					<th w_index="realname" w_align="center" width="1%;">姓名</th>
					<th w_index="depart" w_align="center" width="1%;">部门</th>
					<th w_index="goodsname" w_align="center" width="1%;">申请物品名称</th>
					<th w_index="model" w_align="center" width="1%;">型号</th>
					<th w_index="priceMoney" w_align="center" width="1%;">单价</th>
					<th w_index="numbers" w_align="center" width="1%;">数量</th>
					<th w_index="totalMoney" w_align="center" width="1%;">总价</th>
					<th w_index="taxMoney" w_align="center" width="1%;">税额</th>
					<th w_index="applyDT" w_align="center"width="1%;">申请日期</th>
					<th w_index="auditp" w_edit="auditp" w_align="center"width="1%;">审核状态</th>
					<th w_index="purchase" w_edit="purchase" w_align="center"width="1%;">采购状态</th>
					<th w_index="completeDT" w_align="center"width="1%;">采购日期</th>
					<th w_index="remarks" w_align="center">备注</th>
				</tr>
			</table>
			<div class="control" style="text-align:center;">
				<jsp:include page="attsCommon.jsp"></jsp:include>
			</div>
		</div>
	</div>
		
		<!-- 查看详细 -->
			<div id="purlook" style="margin-left: 20px; margin-right: 20px; display:none;">
				<table class="table2">
					<tr>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">申请人姓名：</td>
						<td><span id="realname" class="w200"></span>
						</td>
						<td style="text-align: right;" class="hei">	员工号：</td>
						<td><span id="num"></span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">部门：</td>
						<td><span id="dept" class="w200"></span></td>
						<td style="text-align: right;" class="hei">申请时间：</td>
						<td><span id="applyDT" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">物品名称：</td>
						<td><span id="egoodsname" class="w200"></span></td>
						<td style="text-align: right;" class="hei">规格型号：</td>
						<td><span id="emodel" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">单价：</td>
						<td><span id="epriceMoney" class="w200"></span></td>
						<td style="text-align: right;" class="hei">数量：</td>
						<td><span id="enumbers" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">税额：</td>
						<td><span id="etaxMoney" class="w200"></span></td>
						<td style="text-align: right;" class="hei">总价：</td>
						<td><span id="etotalMoney" class="w200"></span></td>
					</tr>
					<tr>
					    <td style="text-align: right; display:none; ">id</td>
	                    <td id="id" class="id" style="display:none; "></td>
					</tr>
				</table>
				<p style="text-align:center">
					<input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</div>
			
			<form id="deptpass"style="display:none;margin-left:20px;margin-right:20px;">
			<p>
				<input class="ok" id="ok" type="reset" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="jujue" class="okok">拒绝</span>
			</p>
		</form>
	
		<form id="personpass"style="display:none;margin-left:20px;margin-right:20px;">
			<p>
				<input class="ok" id="ok" type="reset" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="jujue" class="okok">拒绝</span>
			</p>
		</form>
		
		<form id="headpass"style="display:none;margin-left:20px;margin-right:20px;">
			<p>
				<input class="ok" id="ok" type="reset" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="jujue" class="okok">拒绝</span>
			</p>
		</form>
		
		<form  id="rejectE" style="display:none;margin-left:20px;margin-right:20px;">
			<p>拒绝申请原因</p>
			<input type="hidden" name="id" id="id">
			<p><textarea name="rejectMsg" id="rejectMsg" type="text" placeholder="20字以内"  style="width:250px; height:100px;"></textarea></p>
			<p class="bCenter">
				<input class="ok" id="okok" type="button" value="确定拒绝">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</form>
		
		<form  id="remark" style="display:none;margin-left:20px;margin-right:20px;">
			<p>备注</p>
			<input type="hidden" name="id" id="id">
			<p><textarea name="remarks" id="remarks" type="text" placeholder="20字以内"  style="width:250px; height:100px;"></textarea></p>
			<p class="bCenter">
				<input class="ok" id="ok" type="button" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</form>
		
		<div  id="notpass" style="display:none;margin-left:20px;margin-right:20px;">
		<table class="table2">
			<tr>
				<td style="text-align: right;" class="hei">未通过原因：</td>
				<td><span id="rejectMsg" class="w200"></span></td>
			</tr>
			</table>
			<p class="bCenter">
				<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</div>
		<div id="checkEl" style="display:none;margin-left:20px;margin-right:20px;">
			<table class="table2">
				<tr>
					<td><span id="reason" class="w200"></span></td>
				</tr>
				<tr>
					<td colspan="1"  style="text-align:left;"></td>
				</tr>
				<tr>
					<td colspan="1"  style="text-align:left;"></td>
				</tr>
			</table>
			<p class="bCenter">
				<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</div>
		
		</div>
</body>
</html>