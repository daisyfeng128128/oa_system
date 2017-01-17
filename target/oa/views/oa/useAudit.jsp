<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>设备领用</title>
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
	.tabBtn{
		width: 96px;
		height: 32px;
		text-align: center;
		line-height: 32px;
		background-color: #e35d00;
		color: #fff;
		border: none;
		display: inline-block;
		margin-right: 20px;
		border-radius: 2px;
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
	.control {
	margin-left: -300px;
	}

</style>
<script>
var count = 0; 
$(function () {
	count = 1;
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
			
			//采购申请
	$(".control .add").click(function() {
		var $chuangkou = $("#addequipment");
		var editurl = "${pageContext.request.contextPath}/useAudit/editEmp.do";
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				    var $data =data.data;
					$("#addequipment #realname").text($data.name);
					$("#addequipment #num").text($data.num);
					$("#addequipment #dep").text($data.depName);
				},
				error : function(data) {
				}
				});
		$.basewindow("新增设备领用申请", $chuangkou,900, 400);
		$(".basewindow").addClass("custom-scroll");
	})
    //修改
	$(".control .mid").click(function(){
		var id =  grid.getCheckedValues('id');
		if (id != "") {
		var $chuangkou = $("#midequipment");
		var editurl = "${pageContext.request.contextPath}/useAudit/editCollar.do";
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				    var $data =data.data;
					$("#midequipment #mid").text($data.id);
					$("#midequipment #mrealname").text($data.realname);
					$("#midequipment #mnum").text($data.num);
					$("#midequipment #mdep").text($data.depart);
					$("#midequipment #mgoodsname").val($data.goodsname);
					$("#midequipment #mmodel").val($data.model);
					$("#midequipment #mnumbers").val($data.numbers);
					$("#midequipment #mapplyDT").datebox("setValue",$data.applyDT);
					$("#midequipment #musername").val($data.username);
					$("#midequipment #mplace").val($data.place);
					$("#midequipment #mremarks").val($data.remarks);
				},
				error : function(data) {
				}
				});
			$.basewindow("修改设备领用申请",$chuangkou, 900, 300);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.css({ "display" : "block" });
	        return false;
		} else {
			$.threesecond("请先选择", 200, 100, 1000);
		}
	})
	
	$("#addequipment .okok").click(function(){
	    var list1 = new Array();
	    for(var i = 1; i < count;i++){
		    var id = $("#addequipment #id").html();
		    var realname = $("#addequipment #realname").html();
		    var num = $("#addequipment #num").html();
		    var dep = $("#addequipment #dep").html();
		    var applyDT = $("#applyDT").datebox("getValue");
		    var place = $("#place").val();
		    var username = $("#username").val();
		    var goodsname = $("#goodsname"+(count - i)).val();
		    var model = $("#model"+(count - i)).val();
		    var numbers = $("#numbers"+(count - i)).val();
		    var remarks = $("#remarks"+(count - i)).val();
		  
			    if(!applyDT){
			        $.threetop("申请日期不能为空!");
			        return false;
			   }
			     var reg = /^[1-9]\d*$/;  
			     if(!reg.test(numbers)){
			        $.threetop("数量必须为正整数");
			        return false;
			    }
			    if(!goodsname){
			        $.threetop("物品名称不能为空!");
			        return false;
			    }
			    if(!numbers){
			        $.threetop("数量不能为空!");
			        return false;
			    }
			    if(!place){
			        $.threetop("放置地点不能为空!");
			        return false;
			    }
			    if(!model){
			        $.threetop("规格型号不能为空!");
			        return false;
			    }
			    if(!goodsname){
			        $.threetop("领用物品名称不能为空!");
			        return false;
			    }
			    if(!username){
			        $.threetop("使用人不能为空!");
			        return false;
			    }

	    		var list2 = {
	    			"id" : id,
		            "realname" : realname,
		            "num": num,
		            "depart": dep,
		            "applyDT" : applyDT,
		            "model" : model,
		            "numbers" : numbers,
		            "place" : place,
		            "username" : username,
		            "remarks" : remarks,
		            "goodsname" : goodsname
	    	}
	    	list1.push(list2);
	    }

	    $.ajax({
	        type : "POST",
			url : "${pageContext.request.contextPath}/useAudit/save.do",
			cache: false,
	        data: JSON.stringify(list1),
	        contentType: 'application/json; charset=utf-8',
	        success:function(data){
	            if (data.resultStatus == 200) {
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$("#addequipment").hide();
					$('body').append($("#addequipment"));
					grid.refreshPage();
				}
	        }
	    })
	})
	
	$("#midequipment .ok").click(function(){
	    var id = $("#mid").html();
	    var realname = $("#mrealname").html();
	    var num = $("#mnum").html();
	    var dep = $("#mdep").html();
	    var applyDT = $("#mapplyDT").combogrid("getValue");
	    var place = $("#mplace").val();
	    var username = $("#musername").val();
	    var model = $("#mmodel").val();
	    var numbers = $("#mnumbers").val();
	    var goodsname = $("#mgoodsname").val();
	    var remarks = $("#mremarks").val();
		
			if(!applyDT){
			        $.threetop("申请日期不能为空!");
			        return false;
			   }
			     var reg = /^[1-9]\d*$/;  
			     if(!reg.test(numbers)){
			        $.threetop("数量必须为正整数");
			        return false;
			    }
			    if(!goodsname){
			        $.threetop("物品名称不能为空!");
			        return false;
			    }
			    if(!numbers){
			        $.threetop("数量不能为空!");
			        return false;
			    }
			    if(!place){
			        $.threetop("放置地点不能为空!");
			        return false;
			    }
			    if(!model){
			        $.threetop("规格型号不能为空!");
			        return false;
			    }
			    if(!goodsname){
			        $.threetop("领用物品名称不能为空!");
			        return false;
			    }
			    if(!username){
			        $.threetop("使用人不能为空!");
			        return false;
			    }
			    
		    var purchase2 = {
		            "id" : id,
		            "realname" : realname,
			        "num": num,
			        "depart": dep,
			        "applyDT" : applyDT,
			        "model" : model,
			        "numbers" : numbers,
			        "place" : place,
			        "username" : username,
			        "remarks" : remarks,
			        "goodsname" : goodsname
		    }
		    $.ajax({
		        type : "POST",
				url : "${pageContext.request.contextPath}/useAudit/mid.do",
				cache: false,
		        data: JSON.stringify(purchase2),
		        contentType: 'application/json; charset=utf-8',
		        success:function(data){
		            if (data.resultStatus == 200) {
						$(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$("#midequipment").hide();
						$('body').append($("#midequipment"));
						grid.refreshPage();
					}
		        }
		    })
		})
		
		$(".del").click(function(){
		var ids =  grid.getCheckedValues('id');
		    if (ids != "") {
		        $.yesorno("是否删除？",300,160,function() {
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
				var editurl = "${pageContext.request.contextPath}/useAudit/delete.do";
				$.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) {
					    if (data.resultStatus == 200) {
					        $.threesecond("删除成功", 200, 100, 1000);
							grid.refreshPage();
						} 
					},
					error : function(data) {
					}
				});
			
			}, function() {
				$.threesecond("看来还是手下留情了!",
						200, 100, 1000);
			});
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
			}
		})
			
	 
		 $(".control .deptpass").click(function() {
		 	var ids =  grid.getCheckedValues('id');
           	if(ids != ''){
				var $chuangkou = $("#deptpass");
				$.basewindow("是否确认部门审核通过?", $chuangkou, 280, 200);
				$(".basewindow").addClass("custom-scroll");
			}else{
		   	     $.threesecond("未选中数据", 200, 100, 1000);
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
		   	           url:  "${pageContext.request.contextPath}/useAudit/deptpass.do",
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
		    
		    $(".control .equipass").click(function () {
		    	var ids =  grid.getCheckedValues('id');
           		if(ids != ''){
		          var $chuangkou = $("#equipass");
				  $.basewindow("是否确认设备人员审核通过?", $chuangkou, 280, 200);
				  $(".basewindow").addClass("custom-scroll");
				 }else{
		   	         $.threesecond("未选中数据", 200, 100, 1000);
		   	     }
		    });
	            
 			$("#equipass #ok").click(function(){
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
		   	           url:  "${pageContext.request.contextPath}/useAudit/equipass.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	              			if(data.resultStatus == 200){
		   	              			$.threesecond("设备人员审核通过!", 200, 100, 1000);
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
		        
           $("#deptpass #jujue").click(function(){
		         $('body').append($("#deptpass"));
				 $("#deptpass").hide();
		         $(".basewindow").hide();
		         $(".zhezhao-basewindow").hide(); 
		    	 var $chuangkou=$("#rejectE"); 
	    	     $.basewindow("是否确认审核拒绝?", $chuangkou, 320, 300); 
	             $(".basewindow").addClass("custom-scroll");
	    	});  
	    	
           $("#equipass #jujue").click(function(){
		         $('body').append($("#equipass"));
				 $("#equipass").hide();
		         $(".basewindow").hide();
		         $(".zhezhao-basewindow").hide(); 
		    	 var $chuangkou=$("#rejectE"); 
	    	     $.basewindow("是否确认审核拒绝?", $chuangkou, 320, 300); 
	             $(".basewindow").addClass("custom-scroll");
	    	});  
		   
		   $("#rejectE #okok").click(function(){
	            var ids =  grid.getCheckedValues('id');
	          	if(ids != ""){
	            	var rejectMsg = $("#rejectE #rejectMsg").val();
		            var id;
					for(var i=0;i<ids.length;i++){
					     id=ids[i];
					}
		            var reject = {
	                "id":id,
	                "rejectMsg":rejectMsg
	            	};
		            $.ajax({
		                type: "POST",
		                url: "${pageContext.request.contextPath}/useAudit/reject.do",
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
	        
	    $(".control .end").click(function () {
	    	 var ids =  grid.getCheckedValues('id');
	          	if(ids != ""){
		    	var $chuangkou = $("#remark");
	            $.basewindow("是否确认完成处理?", $chuangkou, 320,200);
				$(".basewindow").addClass("custom-scroll");
			 }else{
		   	    $.threesecond("未选中数据", 200, 100, 1000);
		   	 }
        });
        
        $("#remark #ok").click(function(){
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
		   	           url:  "${pageContext.request.contextPath}/useAudit/end.do",
		   	           data: "id="+ids,
		   	           success : function(data){
		   	              			if(data.resultStatus == 200){
		   	              			$.threesecond("发放完成!", 200, 100, 1000);
	  	  	   						grid.refreshPage();
	  	  	   					}
	  	  	   	           }
	  	  	   	      })
	  	  	   	    }else{
				   	    $.threesecond("所处状态不相同,请选择正确的状态",200, 100, 1000);
				   	}  
	            }else{
		   	         $.threesecond("未选中数据", 200, 100, 1000);
		   	     }
		    });
	        
	 
		$(".views").click(function() {
 			var $chuangkou = $("#purlook");
			var id =  grid.getCheckedValues('id');
           		if(id != ''){
				 var editurl = "${pageContext.request.contextPath}/useAudit/editEq.do";
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
							$("#purlook #eusername").text(data.username);
							$("#purlook #eplace").text(data.place);
							$("#purlook #emodel").text(data.model);
							$("#purlook #egoodsname").text(data.goodsname);
							$("#purlook #enumbers").text(data.numbers);
							$("#purlook #eremarks").text(data.remarks);
							$("#purlook #eusername").text(data.username);
					},
					error : function(data) {
					}
				});
				$.basewindow("详细资料", $chuangkou,540,350);
				$chuangkou.css({
					"display" : "block"
				});
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}
			});
	
		var grid = $.fn.bsgrid.init("searchTable", {
	    	url: "${pageContext.request.contextPath}/useAudit/readPages.do",
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
						grantEquip:{
							build:function(edit,value,record, rowIndex, colIndex, options){
								if(value=="待发放")
									return "<span style='color:#e35d00'>"+value+"</span>"
								if(value=="发放完成")
									return "<span style='color:#ccc1d9'>"+value+"</span><br/><a style='color:blue;' href='#' onclick='seah("+record.id+")' >发放人</a>"
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
					var auditp = grid.getRowRecord(grid.getSelectedRow()).auditp;
					var grantEquip = grid.getRowRecord(grid.getSelectedRow()).grantEquip;
					$(".thistable .deptpass, .thistable .equipass,.thistable .end").attr("disabled","true").addClass("tablecancel");
						if(auditp == "待审核"){
						    $(".thistable .deptpass").removeAttr("disabled").removeClass("tablecancel");
						}else if(auditp == "通过"){
						     if(grantEquip == "待发放"){
								$(".thistable .end").removeAttr("disabled").removeClass("tablecancel");
						     } else if(grantEquip == "采购完成"){
						        $(".thistable .deptpass, .thistable .equipass, .thistable .end").attr("disabled","true").addClass("tablecancel");
						     } 
						}else if(auditp == "未通过"){
							$(".thistable .deptpass, .thistable .equipass, .thistable .end").attr("disabled","true").addClass("tablecancel");
						}else if(auditp == "审核中"){
							$(".thistable .equipass").removeAttr("disabled").removeClass("tablecancel");
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
	                    url: "${pageContext.request.contextPath}/useAudit/notPass.do",
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
	                    url: "${pageContext.request.contextPath}/useAudit/check.do",
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
			
			function seah(id){
    			var id = id;
    			var rejectMsg = {
	                "id":id
	            };
                	$.ajax({
	                    type: "POST",
	                    url: "${pageContext.request.contextPath}/useAudit/checkIssuer.do",
	                   	data: JSON.stringify(rejectMsg),
						contentType: "application/json; charset=utf-8",
						cache: false
	                  }).done(function (data) {
	                	if(data.resultStatus == 200){
                            $.basewindow("发放人", "#Issuer", 350, 250);
                            if(data.exData != null ){
                            var $apply=data.exData.split("</br>");
	                		
							for(var i=0;i<$apply.length;i++){
								$("#Issuer #people").append("<tr></tr>");
							}
							$("#people tr").each(function(i, item) {
						        $("#people tr").eq(i).html($apply[i]);
						    });
                          }
	                	}else{
	                	    $.threesecond("获取失败!", 200, 100, 1000);
	                	    return false;
	                	}
	                })
				return true;
			};
			
			function add() {  
			  var tbl = document.all.ci;  
			  var rows = tbl.rows.length;  
			  var tr = tbl.insertRow(rows);
			  var goodsname = tr.insertCell(0);  
			  goodsname.innerHTML = "<td><input type='text' name='goodsname' id='goodsname" + count + "' style='width:180px;'></td>";  
			  var model = tr.insertCell(1);  
			  model.innerHTML = "<td><input type='text' name='model' id='model" + count + "' style='width:150px;'></td>";  
			  var numbers = tr.insertCell(2);  
			  numbers.innerHTML = "<td><input type='text' name='numbers' id='numbers" + count + "'  style='width:100px;'/></td>";  
			  var numbers = tr.insertCell(3);  
			  numbers.innerHTML = "<td><input type='text' name='remarks' id='remarks" + count + "'  style='width:260px;'/></td>";  
			  var del = tr.insertCell(4); 
			  del.innerHTML = '<td><input type="button" onclick="del(this)" value="删除此行"></td>'; 
			  count++; 
			}  
			  
			function del(btn) {  
			  var tr = btn.parentElement.parentElement;  
			  var tbl = tr.parentElement;  
			  tbl.deleteRow(tr.rowIndex);
			  count--;  
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
				<span>首页</span> 
				<span>></span>
				<span>行政系统</span> 
				<span>></span>
				<span class="color99">设备领用</span>
			</div>
	
	<div class="clearFix right-twoRight">
		<div class="control2 fl" style="margin-left:238px">
			<input class="tabBtn1 all" type="button" value="所有数据">
			<input class="tabBtn1 dai" type="button" value="待审核">
			<input class="tabBtn1 cent" type="button" value="审核中">
			<input class="tabBtn1 pass" type="button" value="审核通过">
			<input class="tabBtn1 not" type="button" value="审核未通过">
		</div>
		<div class="search" style="float: right;">
    		<input id="fast" type="text" placeholder="姓名搜索">
	    	<button type="button" id="seach" class="tableok">搜索</button>				
		</div>
	</div>		
			
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
					<th w_index="passType" w_align="center" width="1%;" w_hidden="true">passType</th>
					<th w_index="process" w_align="center" width="1%;" w_hidden="true">process</th>
					<th w_check="true" w_index="billed" width="1%;"></th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="num" w_align="center" width="1%;">员工号</th>
					<th w_index="realname" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_index="depart" w_align="center" width="1%;">部门</th>
					<th w_index="goodsname" w_align="center" width="1%;">申请物品名称</th>
					<th w_index="place" w_align="center" width="1%;">放置地点</th>
					<th w_index="model" w_align="center" width="1%;">型号</th>
					<th w_index="numbers" w_align="center" width="1%;">数量</th>
					<th w_index="applyDT" w_align="center" width="1%;">申请日期</th>
					<th w_index="auditp" w_edit="auditp" w_align="center" width="1%;">审核状态</th>
					<th w_index="grantEquip" w_edit="grantEquip" w_align="center" width="1%;">发放状态</th>
					<th w_index="completeDT" w_align="center" width="1%;">发放日期</th>
					<th w_index="remarks" w_align="center">备注</th>
				</tr>
				</table>
				<div class="control">
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
						<td><span id="realname" class="w200"></span></td>
						<td style="text-align: right;" class="hei">员工号：</td>
						<td><span id="num"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">部门：</td>
						<td><span id="dept" class="w200"></span></td>
						<td style="text-align: right;" class="hei">申请时间：</td>
						<td><span id="applyDT" class="w200"></span></td>
					</tr>
					<tr>
							<td style="text-align: right;" class="hei">使用人：</td>
							<td><span id="eusername" class="w200"></span></td>
							<td style="text-align: right;" class="hei">放置地点：</td>
							<td><span id="eplace" class="w200"></span></td>
					</tr>
					<tr>
							<td style="text-align: right;" class="hei">领用物品名称：</td>
							<td><span id="egoodsname" class="w200"></span></td>
							<td style="text-align: right;" class="hei">规格型号：</td>
							<td><span id="emodel" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">数量：</td>
						<td><span id="enumbers" class="w200"></span></td>
						<td style="text-align: right;" class="hei">备注：</td>
						<td><span id="eremarks" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">使用人：</td>
						<td><span id="eusername" class="w200"></span></td>
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
	
		<form id="equipass"style="display:none;margin-left:20px;margin-right:20px;">
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
		
		<form  id="remark" style="display:none;margin-left:20px;margin-right:20px;">
			<p class="bCenter">
				<input class="ok" id="ok" type="button" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="取消">
			</p>
		</form>
		
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
		<div id="Issuer" style="display:none;margin-left:20px;margin-right:20px;">
			<table class="table2">
				<tr>
					<td><span id="people" class="w200"></span></td>
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
		
		<div id="addequipment" style="margin-left: 20px; margin-right: 20px; display:none;">
				<table class="table">
					<tr>
						<td style="text-align: center;">姓名：</td>
						<td id="realname" style="width:200px;"></td>
						<td style="text-align: center;">员工号：</td>
						<td id="num" style="width:200px;"></td>
					</tr>
					<tr>
						<td style="text-align: center;">部门：</td>
						<td id="dep" style="width:200px;"></td>
						<td style="text-align: center;">申请时间：</td>
						<td class="taL"><input class="easyui-datebox" editable="false"
							type="text" name="applyDT" id="applyDT" style="width:200px;">
							<span class="mustred">*</span></td>
					</tr>
					<tr style="text-align: left;" align="center">
						<td style="text-align: center;">使用人：</td>
						<td class="taL"><input type="text" name="username" id="username" style="width:200px;text-align: left"></td>
						<td style="text-align: center;">放置地点：</td>
						<td class="taL"><input type="text" name="place" id="place" style="width:200px;text-align: left"></td>
					</tr>
					<tr>
					<td style="text-align: right; display:none; ">id</td>
					<td id="id" class="id" style="display:none; "></td>
					</tr>
					  <tr><td><button onclick="add()"> 添加新行  </button></td></tr>  
				        <table style="width:100%" id="ci" name="ci"> 
						  <thead>  
						  	<tr>
				                <th style="width:180px;text-align: center;">领用物品名称</th>
				                <th style="width:150px;text-align: center;">规格型号</th>
				                <th style="width:150px;text-align: center;">数量</th>
				                <th style="width:150px;text-align: center;">备注</th>
				                <th style="width:150px;"></th>
			            	</tr>
						  </thead>  
						  <tbody>  
						  </tbody>  
						</table>  
					</table>
				<p style="text-align:center;">
					<span id="ok" class="okok">提交</span> <input class="cancel"
						type="reset" name="Input" value="关闭">
				</p>
			</div>
			<div id="midequipment" style="margin-left: 20px; margin-right: 20px; display:none;">
				<table class="table">
						<tr>
						    <td style="text-align: right; display:none; "></td>
		                    <td id="mid" class="id" style="display:none; "></td>
						</tr>
						<tr>
		                    <td style="text-align: center;">姓名：</td>
		                    <td id="mrealname" style="width:200px;"></td>
		                    <td style="text-align: center;">员工号：</td>
		                    <td id="mnum" style="width:200px;"></td>
		                </tr>
						<tr>
							<td style="text-align: center;">部门：</td>
		                    <td id="mdep" style="width:200px;"></td>
							<td style="text-align: center;">申请时间：</td>
							<td class="taL"><input class="easyui-datebox" editable="false" type="text" name="mapplyDT" id="mapplyDT" style="width:200px;"> <span class="mustred">*</span></td>
						</tr>
						<tr style="text-align: left;" align="center">
						<td style="text-align: center;">使用人：</td>
						<td class="taL"><input type="text" name="musername" id="musername" style="width:200px;text-align: left"></td>
						<td style="text-align: center;">放置地点：</td>
						<td class="taL"><input type="text" name="mplace" id="mplace" style="width:200px;text-align: left"></td>
					</tr>
						<table style="width:100%"> 
							  <thead>  
							  	<tr>
					               	<th style="width:180px;text-align: center;">领用物品名称</th>
					                <th style="width:150px;text-align: center;">规格型号</th>
					                <th style="width:150px;text-align: center;">数量</th>
					                <th style="width:150px;text-align: center;">备注</th>
				            	</tr>
							  </thead>  
						  <tbody>  
						  	<tr>
				                <td class="taL"><input type="text" name="mgoodsname" id="mgoodsname" style="width:180px;text-align: center;"></td>
				                <td class="taL"><input type="text" name="mmodel" id="mmodel" style="width:180px;text-align: center;"></td>
				                <td class="taL"><input type="text" name="mnumbers" id="mnumbers" style="width:160px;text-align: center;"/></td>
				                <td class="taL"><input type="text" name="mremarks" id="mremarks" style="width:200px;text-align: center;"/></td>
				            </tr> 
						  </tbody>  
						</table>  
					</table>
				<p style="text-align:center;">
					<span id="ok" class="ok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</div>
		
		
		</div>
	</body>
</html>
