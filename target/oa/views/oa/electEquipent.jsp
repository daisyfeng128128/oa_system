<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>采购申请-电子产品</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
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
.rightbox{width:85%; float:right;overflow:auto;margin-top: -800px; position:relative; }

	.mustred{margin-left:10px;color:#ff0000}
	.ook{
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
	.control2{
		background-color: #ccc1d9;
	}
	#qq{
		margin-left:-20px; 
	}
	.right-twohalf{
	margin-top:0;
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
		
		$("#fast").change(function() {
			    var fastArg = $("#fast").val();
				var searchParames={
				    "fastArg": fastArg
				}
				grid.search(searchParames);
			})
		
    //采购申请
	$(".control .add").click(function() {
		var $chuangkou = $("#addpurchase");
		 $chuangkou.find(".toomore span").parent("p").prev("p").remove();
         $chuangkou.find(".toomore span").parent("p").remove();
         $chuangkou.find(".addpingtaihao").show();
         $chuangkou.find(".toaddpingtaihao").click();
			$.ajax({
				type : "get",
				dataType : "json",
				url : "${pageContext.request.contextPath}/purchaseApplication/editEmp.do",
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				    var $data =data.data;
					$("#addpurchase #realname").text($data.name);
					$("#addpurchase #num").text($data.num);
					$("#addpurchase #dep").text($data.depName);
				},
				error : function(data) {
				}
				});
		$.basewindow("新增电子设备采购", $chuangkou,1250, 400);
		$(".basewindow").addClass("custom-scroll");
	})

    //修改
	$(".control .mid").click(function(){
		var $chuangkou = $("#midpurchase");
		var id =  grid.getCheckedValues('id');
			if (id != "") {
			var editurl = "${pageContext.request.contextPath}/purchaseApplication/editelectEquipent.do";
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				    var $data =data.data;
					$("#midpurchase #mid").text($data.id);
					$("#midpurchase #mrealname").text($data.realname);
					$("#midpurchase #mnum").text($data.num);
					$("#midpurchase #mdep").text($data.depart);
					$("#midpurchase #mgoodsname").val($data.goodsname);
					$("#midpurchase #mmodel").val($data.model);
					$("#midpurchase #mpriceMoney").val($data.priceMoney);
					$("#midpurchase #mnumbers").val($data.numbers);
					$("#midpurchase #mtaxMoney").val($data.taxMoney);
					$("#midpurchase #mtotalMoney").val($data.totalMoney);
					$("#midpurchase #mapplyDT").datebox("setValue",$data.applyDT);
					$("#midpurchase #musername").val($data.username);
					$("#midpurchase #mplace").val($data.place);
				},
				error : function(data) {
				}
				});
			$.basewindow("修改电子设备采购",$chuangkou, 1250, 400);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.css({ "display" : "block" });
	        return false;
		} else {
			$.threesecond("请先选择", 200, 100, 1000);
		}
	})
	
	$(".okok").click(function(){
		$(".zhezhao-basewindow").show();
	    var list1 = new Array();
	    for(var i = 1; i < count;i++){
		    var id = $("#addpurchase #id").html();
		    var realname = $("#addpurchase #realname").html();
		    var num = $("#addpurchase #num").html();
		    var dep = $("#addpurchase #dep").html();
		    var applyDT = $("#applyDT").datebox("getValue");
		    var username = $("#username").val();
		    var place = $("#place").val();
		    var goodsname = $("#goodsname"+(count - i)).val();
		    var model = $("#model"+(count - i)).val();
	    	var priceMoney = $("#priceMoney"+(count - i)).val();
		    var numbers = $("#numbers"+(count - i)).val();
		    var taxMoney = $("#taxMoney"+(count - i)).val();
		    var totalMoney = $("#totalMoney"+(count - i)).val();
		     var reg = /^[1-9]\d*$/;  
			     if(!reg.test(numbers)){
			        $.threetop("数量必须为正整数");
			        return false;
			    }
			    if(!applyDT){
			        $.threetop("申请日期不能为空!");
			        return false;
			   }
			    if(!username){
			        $.threetop("使用者不能为空!");
			        return false;
			    }
			    if(!place){
			        $.threetop("放置地点不能为空!");
			        return false;
			    }
			    if(!goodsname){
			        $.threetop("物品名称不能为空!");
			        return false;
			    }
			    if(!priceMoney){
			        $.threetop("单价不能为空!");
			        return false;
			    }
			    if(!numbers){
			        $.threetop("数量不能为空!");
			        return false;
			    }
			    if(!taxMoney){
			        $.threetop("税额不能为空!");
			        return false;
			    }
	    		var list2 = {
	    			"id" : id,
		            "realname" : realname,
		            "num": num,
		            "depart": dep,
		            "applyDT" : applyDT,
		            "username" : username,
		            "goodsname" : goodsname,
		            "model" : model,
		            "priceMoney" : priceMoney,
		            "numbers" : numbers,
		            "taxMoney" : taxMoney,
		            "totalMoney" : totalMoney,
		            "place" : place
	    	}
	    	list1.push(list2);
	    }

	    $.ajax({
	        type : "POST",
			url : "${pageContext.request.contextPath}/purchaseApplication/save.do",
			cache: false,
	        data: JSON.stringify(list1),
	        contentType: 'application/json; charset=utf-8',
	        success:function(data){
	            if (data.resultStatus == 200) {
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$("#addpurchase").hide();
					$('body').append($("#addpurchase"));
					grid.refreshPage();
				}
	        }
	    })
	})
	//修改
	$(".ok").click(function(){
		    var id = $("#mid").html();
		    var realname = $("#midpurchase #mrealname").html();
		    var num = $("#mnum").html();
		    var dep = $("#mdep").html();
		    var applyDT = $("#mapplyDT").datebox("getValue");
		    var username = $("#musername").val();
		    var place = $("#mplace").val();
		    var goodsname = $("#mgoodsname").val();
		    var model = $("#mmodel").val();
	    	var priceMoney = $("#mpriceMoney").val();
		    var numbers = $("#mnumbers").val();
		    var taxMoney = $("#mtaxMoney").val();
		    var totalMoney = $("#mtotalMoney").val();
		      var reg = /^[1-9]\d*$/;  
			     if(!reg.test(numbers)){
			        $.threetop("数量必须为正整数");
			        return false;
			    }
			    if(!applyDT){
			        $.threetop("申请日期不能为空!");
			        return false;
				 }
			    if(!username){
			        $.threetop("使用者不能为空!");
			        return false;
			    }
			    if(!place){
			        $.threetop("放置地点不能为空!");
			        return false;
			    }
			    if(!goodsname){
			        $.threetop("物品名称不能为空!");
			        return false;
			    }
			    if(!priceMoney){
			        $.threetop("单价不能为空!");
			        return false;
			    }
			    if(!numbers){
			        $.threetop("数量不能为空!");
			        return false;
			    }
			    if(!taxMoney){
			        $.threetop("税额不能为空!");
			        return false;
			    }
	    		var list2 = {
	    			"id" : id,
		            "realname" : realname,
		            "num": num,
		            "depart": dep,
		            "applyDT" : applyDT,
		            "username" : username,
		            "goodsname" : goodsname,
		            "model" : model,
		            "priceMoney" : priceMoney,
		            "numbers" : numbers,
		            "taxMoney" : taxMoney,
		            "totalMoney" : totalMoney,
		            "place" : place
	    	}
	    $.ajax({
	        type : "POST",
			url : "${pageContext.request.contextPath}/purchaseApplication/mid.do",
			cache: false,
	        data: JSON.stringify(list2),
	        contentType: 'application/json; charset=utf-8',
	        success:function(data){
	            if (data.resultStatus == 200) {
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$("#midpurchase").hide();
					$('body').append($("#midpurchase"));
					grid.refreshPage();
				}
	        }
	    })
	})
	
	$(".del").click(function(){
	    var id =  grid.getCheckedValues('id');
			if (id != "") {
	        $.yesorno("是否删除？",300,160,function() {
			var editurl = "${pageContext.request.contextPath}/purchaseApplication/delete.do";
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
	
	$(".views").click(function() {
			var id =  grid.getCheckedValues('id');
			if (id != "") {
 			var $chuangkou = $("#purlook");
				 var editurl = "${pageContext.request.contextPath}/purchaseApplication/editPur.do";
				$.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) {
						  var $data = data.data; 
							$("#purlook #erealname").text(data.realname);
							$("#purlook #enum").text(data.num);
							$("#purlook #edept").text(data.depart);
							$("#purlook #eapplyDT").text(data.applyDT);
							$("#purlook #emodel").text(data.model);
							$("#purlook #egoodsname").text(data.goodsname);
							$("#purlook #epriceMoney").text(data.priceMoney);
							$("#purlook #enumbers").text(data.numbers);
							$("#purlook #etaxMoney").text(data.taxMoney);
							$("#purlook #etotalMoney").text(data.totalMoney);
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
			
		 $(".right-onehalf span").click(function () {
               var $self = $(this);
               if (!$self.hasClass("spanchange")) {
               		$(".right-onehalf span").removeClass("spanchange");
                    $self.addClass("spanchange");
                    searchParames = {"branchs":"${branchs}"};
                    grid.search(searchParames);
                    $("#actoresForm select option").remove();
                }
            })
		
		var grid = $.fn.bsgrid.init("searchTable", {
	    	url: "${pageContext.request.contextPath}/purchaseApplication/readPages.do?branchs=${branchs}",
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


});
		function seanot(id){
    			var id = id;
    			var rejectMsg = {
	                "id":id
	            };
                	$.ajax({
	                    type: "POST",
	                    url: "${pageContext.request.contextPath}/purchaseAudit/notPassEl.do",
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
	                    url: "${pageContext.request.contextPath}/purchaseAudit/checkEl.do",
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
			
			function midf(){
				var priceMoney = $("#mpriceMoney").val();
				var numbers = $("#mnumbers").val();
				var taxMoney = $("#mtaxMoney").val();
				if(priceMoney == ''){
					priceMoney = 0
				}
				if(numbers == ''){
					numbers = 0
				}
				if(taxMoney == ''){
					taxMoney = 0
				}
				var num1=parseFloat(priceMoney * numbers);   
                var num2=parseFloat(taxMoney); 
				$("#mtotalMoney").val(num1 + num2);
			}
			
			function test(num){
				var priceMoney = $("#priceMoney"+num).val();
				var numbers = $("#numbers"+num).val();
				var taxMoney = $("#taxMoney"+num).val();
				if(numbers == ''){
					numbers = 0
				}
				if(taxMoney == ''){
					taxMoney = 0
				}
				var num1=parseFloat(priceMoney * numbers);   
                var num2=parseFloat(taxMoney); 
				$("#totalMoney"+num).val(num1 + num2);
			}

		 	
		function add() {  
		  var tbl = document.all.ci;  
		  var rows = tbl.rows.length;  
		  var tr = tbl.insertRow(rows);
		  var goodsname = tr.insertCell(0);  
		  goodsname.innerHTML = "<td><input type='text' name='goodsname' id='goodsname" + count + "' style='width:180px;'></td>";  
		  var model = tr.insertCell(1);  
		  model.innerHTML = "<td><input type='text' name='model' id='model" + count + "' style='width:150px;'></td>";  
		  var priceMoney = tr.insertCell(2);   
		  priceMoney.innerHTML = "<td><input type='text' name='priceMoney' id='priceMoney" + count + "' onkeyup='test("+count+")' onkeyup='this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]'   style='width:150px;'/></td>";  
		  var numbers = tr.insertCell(3);  
		  numbers.innerHTML = "<td><input type='text' name='numbers' id='numbers" + count + "' onkeyup='test("+count+")' style='width:150px;'/></td>";  
		  var taxMoney = tr.insertCell(4);  
		  taxMoney.innerHTML = "<td><input type='text' name='taxMoney' id='taxMoney" + count + "'   onchange='test("+count+")' onchange='this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]' style='width:150px;'/></td>";  
		  var totalMoney= tr.insertCell(5);  
		  totalMoney.innerHTML = "<td><input type='text' name='totalMoney' id='totalMoney" + count + "' style='width:150px;'  onchange='this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]'></td>";  
		  var del = tr.insertCell(6);  
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
				<span class="color99">采购申请</span>
		</div>
	<jsp:include page="purchaseCommon.jsp"></jsp:include>
		<div class="custom-scroll thistable" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
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
			<p class="control">
				<jsp:include page="attsTabCommon.jsp"></jsp:include>
			</p>		
		</div>
		<div id="addpurchase" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table">
					<tr>
					    <td style="text-align: right; display:none; "></td>
	                    <td id="id" class="id" style="display:none; "></td>
					</tr>
					<tr style="text-align: center;">
	                    <td style="text-align: center;">姓名：</td>
	                    <td id="realname" style="width:200px;text-align: left"></td>
	                    <td style="text-align: center;">员工号：</td>
	                    <td id="num" style="width:200px;text-align: left"></td>
	                </tr>
					<tr style="text-align:left;"  align="center">
						<td style="text-align: center;">部门：</td>
	                    <td id="dep" style="width:200px;text-align: left"></td>
						<td style="text-align: center;">申请时间：</td>
						<td class="taL"><input class="easyui-datebox" editable="false" type="text" name="applyDT" id="applyDT" style="width:200px;text-align: left"> <span class="mustred">*</span></td>
					</tr>
					<tr style="text-align: left;" align="center">
						<td style="text-align: center;">使用人：</td>
						<td class="taL"><input type="text" name="username" id="username" style="width:200px;text-align: left"></td>
						<td style="text-align: center;">放置地点：</td>
						<td class="taL"><input type="text" name="place" id="place" style="width:200px;text-align: left"></td>
					</tr>
			       <tr><td><button onclick="add()"> 添加新行  </button></td></tr>  
			        <table style="width:100%" id="ci" name="ci"> 
					  <thead>  
					  	<tr style="text-align: center;">
			                <th style="width:180px;text-align: center;">物品名称</th>
			                <th style="width:150px;text-align: center;">规格型号</th>
			                <th style="width:150px;text-align: center;">单价/元</th>
			                <th style="width:150px;text-align: center;">数量</th>
			                <th style="width:150px;text-align: center;">税额</th>
			                <th style="width:150px;text-align: center;">总价/元</th>
			                <th style="width:150px;"></th>
		            	</tr>
					  </thead>  
					  <tbody>  
					  </tbody>  
					</table>  
			</table>
			<p style="text-align:center;">
				<span id="ok" class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		<div id="midpurchase" style="margin-left: 20px; margin-right: 20px; display:none;">
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
					<tr>
						<td style="text-align: center;">使用人：</td>
						<td class="taL"><input type="text" name="musername" id="musername" style="width:200px;"></td>
						<td style="text-align: center;">放置地点：</td>
						<td class="taL"><input type="text" name="mplace" id="mplace" style="width:200px;"></td>
					</tr>
						<table style="width:100%;"> 
						  <thead>  
						  	<tr style="text-align: center;">
				                <th style="width:180px;text-align: center;">物品名称</th>
				                <th style="width:150px;text-align: center;">规格型号</th>
				                <th style="width:150px;text-align: center;">单价/元</th>
				                <th style="width:150px;text-align: center;">数量</th>
				                <th style="width:150px;text-align: center;">税额</th>
				                <th style="width:150px;text-align: center;">总价/元</th>
			            	</tr>
						  </thead>  
					  <tbody>  
					  	<tr style="text-align: center;">
			                <td class="taL"><input type="text" name="mgoodsname" id="mgoodsname" style="width:150px;"></td>
			                <td class="taL"><input type="text" name="mmodel" id="mmodel" style="width:150px;"></td>
			                <td class="taL"><input type="text" name="mpriceMoney" id="mpriceMoney" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]"  onkeyup="midf()" style="width:150px;"/></td>
			                <td class="taL"><input type="text" name="mnumbers" id="mnumbers"  onkeyup="midf()" style="width:150px;"/></td>
			                <td class="taL"><input type="text" name="mtaxMoney" id="mtaxMoney"   onchange="midf()" onchange="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]" style="width:150px;"/></td>
			                <td class="taL"><input type="text" name="mtotalMoney" id="mtotalMoney" style="width:150px;" onchange="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]"></td>
			            </tr> 
					  </tbody>  
					</table>  
				</table>
			<p style="text-align:center;">
				<span id="ok" class="ok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
			</p>
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
						<td><span id="erealname" class="w200"></span>
						</td>
						<td style="text-align: right;" class="hei">	员工号：</td>
						<td><span id="enum"></span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">部门：</td>
						<td><span id="edept" class="w200"></span></td>
						<td style="text-align: right;" class="hei">申请时间：</td>
						<td><span id="eapplyDT" class="w200"></span></td>
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