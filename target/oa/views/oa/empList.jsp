<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>员工档案</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<jsp:include page="treeview.jsp"></jsp:include>
<style>
body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;} 
.mustred {margin-left: 10px;color: #ff0000;}
.mains{width:100%;min-width:1000px;overflow: hidden; background-color:#fff; }
.lefttree{width:15%;margin-right:15px;height:800px;float:left; overflow:scroll; }
.rightbox{width:85%; float:right;overflow:auto;margin-top: -800px; position:relative; }
.koko {
	border: 1px solid #ddd;
	text-align: center;
	height: 32px;
	line-height: 32px;
	display: inline-block;
	color: #fff;
	background-color: #e35d00;
	width: 82px;
	border-radius: 5px;
	margin-right: 20px;
	cursor: pointer;
	margin-top: 20px;
}

.sws {
	position: fixed;
	top: 0px;
	_position: absolute;
	_bottom: auto;
	_top: expression(eval(document.documentElement.scrollTop) );
	z-index: 401;
	width: 100%;
	margin: 0 auto;
	left: 0px;
}

.classp {
	margin-top: 7px;
}
.bg83{
	margin-bottom:20px; 
	height:46px;
	line-height:46px; 
}
.bottom{
	text-align:center; 
}
.tit{
	margin:0;
	margin-top:10px; 
}
.m20{
	width:200px; 
}
.control{
	margin-left:-234px;
}
.bsgridPagingOutTab>tbody>tr>td{
	padding:0; 
}
.mustff{
	color:#fff;
	margin-left:10px;
}

</style>
<script>
	$(function() {
	    $("#sp").hide();
	    var ids ="";
		var click_type;
		//加载数据
		var grid = $.fn.bsgrid.init("searchTable", {
			url : "${pageContext.request.contextPath}/emp/readPages.do",
			pageSizeSelect : true,
			pagingToolbarAlign : "left",
			displayBlankRows : false,
			pageSize : pageSize,
			displayPagingToolbarOnlyMultiPages : true
		});
		//设置下拉框触发事件
		 $("#empForm #employeType").on('change', function() {
		      if($("#empForm #employeType").val()==2){
		      	  $("#showTwo").css("display","table-row");
		      	  $("#showOne").css("display","table-row");
		      	  $("#showThree").css("display","table-row");
		      	  $("#showFour").css("display","table-row");
		      }else{
		          $("#showTwo").css("display","none");
		      	  $("#showOne").css("display","none");
		      	  $("#showThree").css("display","none");
		          $("#showFour").css("display","none");
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

		//新增
		$(".control .add").click(function() {
			click_type = 11;
			
			$("#name").removeAttr("readonly");
			$("#namepy").removeAttr("readonly");
			$("#genrer").removeAttr("disabled");
			$("#basicSalary").next("span").remove();
			$("#namepy").removeAttr("readOnly");
			$("#sex").removeAttr("disabled");
			var $chuangkou = $("#empForm");
			$.basewindow("新建员工", $chuangkou, 900, 620);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.find(".ok").attr({"disabled" : "disabled"}).css({"cursor" : "not-allowed"})//初始确认建不能点击所以修改不需要这段 //改动6！！！
		})
		//搜索
		$(".searchAll").click(function() {
			click_type = 11;
			$("#name").removeAttr("readonly");
			$("#namepy").removeAttr("readonly");
			$("#genrer").removeAttr("disabled");
			$("#basicSalary").next("span").remove();
			var $chuangkou = $("#sousuo");
			$.basewindow("高级搜索", $chuangkou, 800, 234);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.find(".ok").attr({
				"disabled" : "disabled"
			}).css({
				"cursor" : "not-allowed"
			})//初始确认建不能点击所以修改不需要这段 //改动6！！！
		})
		
		//详细
		$(".views").click(
			function() {
			var $chuangkou = $("#emplook");
			if (grid.getSelectedRow().length > 0) {
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
				var editurl = "${pageContext.request.contextPath}/emp/edit.do?t="+ new Date().toTimeString();
				$.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) {
					if (data.sex == "WOMAN") {
						$("#vsex").text("女");
					} else if (data.sex == "MAN") {
						$("#vsex").text("男");
					}
					if(data.jiaojin==1){
					    $("#vjiaojin").text("是");
					}else{
					    $("#vjiaojin").text("否");
					}
					if(data.socialSecurity==1){
					    $("#vsocialSecurity").text("是");
					}else{
					    $("#vsocialSecurity").text("否");
					}
					
					if (data.account == "CHENGZHEN") {
						$("#vaccount").text("城镇");
					} else if (data.account == "NONGCUN") {
						$("#vaccount").text("农村");
					}
					
					if (data.genrer == "SHIYONG") {
						$("#vgenrer").text("试用");
					} else if (data.genrer == "ZHENGSHI") {
						$("#vgenrer").text("正式");
					}
					
					$("#vremarks").text(data.remarks);
					if (data.employeType == "EMPLOYEE") {
						$("#vemployeType").text("普通员工");
						$("#artistdata").hide();
						$("#artistdata").nextAll().hide();
					} else if (data.employeType == "ACTORES") {
						$("#vemployeType").text("艺人");
						$("#artistdata").show();
						$("#artistdata").nextAll().show();
						$("#vpushMoney").text(data.pushMoney);
						$("#vactoresBSalary").text(data.actoresBSalary);
						$("#vspeciality").text(data.speciality);
						$("#vshareBankAddress").text(data.shareBankAddress);
						$("#vshareBankCard").text(data.shareBankCard);
					} else if (data.employeType == "TALENT") {
						$("#vemployeType").text("星探");
						$("#artistdata").hide();
						$("#artistdata").nextAll().hide();
					} else {
						$("#vemployeType").text("管理");
						$("#artistdata").hide();
						$("#artistdata").nextAll().hide();
					}
					
					$("#vsalary").text(data.salary);
					if (data.otherSubsidy != null) {
						$("#votherSubsidy").text(data.otherSubsidy);
					}
					if (data.pfcardinalNumber != null) {
						$("#PFcardinalNumber").text(data.pfcardinalNumber);
					}
					if (data.sscardinalNumber != null) {
						$("#SScardinalNumber").text(data.sscardinalNumber);
					}
					if (data.foodSubsidyE != null) {
						$("#FoodSubsidyE").text(data.foodSubsidyE);
					}
					if (data.otherSubsidyE != null) {
						$("#OtherSubsidyE").text(data.otherSubsidyE);
					}
					if (data.jobSubsidyE != null) {
						$("#JobSubsidyE").text(data.jobSubsidyE);
					}
					
					$("#vprobationSalary").text(data.probationSalary);
					$("#vbankAddress").text(data.bankAddress);
					$("#vbankCard").text(data.bankCard);
					$("#vcpfAccounts").text(data.cpfAccounts);

					// $("#vid").text(data.id);
					$("#vname").text(data.name);
					$("#vnamepy").text(data.namepy);
					$("#valiasname").text(data.aliasname);
					$("#vidcard").text(data.idcard);
					$("#vphone").text(data.phone);
					$("#vqq").text(data.qq);
					$("#vhostRegister").text(
							data.hostRegister);
					$("#vemail").text(data.email);

					$("#vliveAdress").text(data.liveAdress);
					$("#vhostAddress").text(data.hostAddress);
						
					$("#emergencyContact").text(data.emergencyContact);
					$("#vemergencyPhone").text(data.emergencyPhone);
					$("#vintroducer").text(data.introducer);
					if (data.posName != null) {
						$("#vposid").text(data.posName);
					}
					if (data.empName != null) {
						$("#vempid").text(data.empName);
					}
					if (data.depName != null) {
						$("#vdepid").text(data.depName);
					}

					if (data.contractDT != "" && data.contractDT != null) {
						$("#vcontractDT").text(data.contractDT);
					}
					if (data.birth != "" && data.birth != null) {
						$("#vbirth").text(data.birth);
					}
					if (data.joinDate != "" && data.joinDate != null) {
						$("#vjoinDate").text(data.joinDate);
					}
					if (data.regularDate != "" && data.regularDate != null) {
						$("#vregularDate").text(data.regularDate);
					}
					},
					error : function(data) {
					}
				});
				$.basewindow("详细资料", $chuangkou, 800, 630);
				$(".basewindow").addClass("custom-scroll");
				$chuangkou.css({
					"display" : "block"
				});
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}

			});
		//修改
		$(".control .mid").click(function() {
				click_type = 22;
				var $chuangkou = $("#empForm");
				 $("#sp").hide();
				$("#probationSalary").next("span").remove();
				if (grid.getSelectedRow().length > 0) {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;
					var editurl = "${pageContext.request.contextPath}/emp/edit.do?t="+ new Date().toTimeString();
					$.ajax({
						type : "get",
						dataType : "json",
						url : editurl,
						data : "id=" + id,
						show : "slide",
						success : function(data) {
							if (data.sex == "WOMAN") {
								$("#sex").val("1").selected;
							} else if (data.sex == "MAN") {
								$("#sex").val("2").selected;
							}
							if (data.account == "CHENGZHEN") {
								$("#account").val("CHENGZHEN").selected;
							} else if (data.account == "NONGCUN") {
								$("#account").val("NONGCUN").selected;
							}

							$("#jiaojin").val(data.jiaojin);
							$("#socialSecurity").val(data.socialSecurity);
							if (data.genrer == "SHIYONG") {
								$("#genrer").val("1").selected;
								ISSHIYONG = "yes";
							} else if (data.genrer == "ZHENGSHI") {
								$("#genrer").val("2").selected;
							} 

							$("#id").val(data.id);
							$("#name").val(data.name);
							$("#namepy").val(data.namepy);
							$("#aliasname").val(data.aliasname);
							$("#idcard").val(data.idcard);
							$("#phone").numberbox("setValue", data.phone);
							$("#qq").numberbox("setValue",data.qq);
							$("#hostRegister").val(data.hostRegister);
							$("#email").val(data.email);

							$("#remarks").val(data.remarks);
							
							if (data.employeType == "ACTORES") {
								$("#employeType").val("2").selected;
								$("#actoresBSalary").numberbox("setValue",data.actoresBSalary);
								$("#speciality").val(data.speciality);
								$("#pushMoney").val(data.pushMoney);
								$("#shareBankAddress").val(data.shareBankAddress);
								$("#shareBankCard").val(data.shareBankCard);
						      	$("#showOne").css("display","table-row");
								$("#showTwo").css("display","table-row");
						      	$("#showThree").css("display","table-row");
						      	$("#showFour").css("display","table-row");
							}else if (data.employeType == "EMPLOYEE"){
							    $("#employeType").val("1").selected;
							    $("#showTwo").css("display","none");
						        $("#showOne").css("display","none");
						      	$("#showThree").css("display","none");
						        $("#showFour").css("display","none");
							}else if (data.employeType == "TALENT"){
							    $("#employeType").val("3").selected;
							    $("#showTwo").css("display","none");
					      	    $("#showOne").css("display","none");
					      	    $("#showThree").css("display","none");
					            $("#showFour").css("display","none");
							}else if (data.employeType == "MANAGER"){
							    $("#employeType").val("4").selected;
							    $("#showTwo").css("display","none");
					      	    $("#showOne").css("display","none");
					      	    $("#showThree").css("display","none");
					            $("#showFour").css("display","none");
							}   
							$("#salary").numberbox("setValue",data.salary);
							$("#otherSubsidy").numberbox("setValue",data.otherSubsidy);
							$("#probationSalary").numberbox("setValue",data.probationSalary);
							$("#foodSubsidyE").numberbox("setValue",data.foodSubsidyE);
							$("#otherSubsidyE").numberbox("setValue",data.otherSubsidyE);
							$("#jobSubsidyE").numberbox("setValue",data.jobSubsidyE);
							
							$("#PFcardinalNumber").numberbox("setValue",data.pfcardinalNumber);
							$("#SScardinalNumber").numberbox("setValue",data.sscardinalNumber); 
							$("#bankAddress").val(data.bankAddress);
							$("#bankCard").val(data.bankCard);
							$("#cpfAccounts").val(data.cpfAccounts);

							$("#liveAdress").val(data.liveAdress);
							$("#hostAddress").val(data.hostAddress);

							$("#emergencyContact").val(data.emergencyContact);
							$("#emergencyPhone").val(data.emergencyPhone);
							$("#introducer").val(data.introducer);

						
							if (data.contractDT != "") {
							$("#contractDT").datebox("setValue",data.contractDT);
							}
							if (data.birth != "") {
								$("#birth").datebox("setValue",data.birth);
							}
							if (data.joinDate != "") {
								$("#joinDate").datebox("setValue",data.joinDate);
							}
							if (data.regularDate != "") {
								$("#regularDate").datebox("setValue",data.regularDate);
							}
							
							$("#posid").combogrid("setValue", data.posId);
							$("#depid").combogrid("setValue", data.depId);
							$("#empid").combogrid("setValue", data.empId);
							$("#employeType").attr({"disabled" : "disabled" });
							$("#genrer").attr({"disabled" : "disabled" });
							$("#name").attr({"readOnly":"readOnly"});
							$("#namepy").attr({"readOnly":"readOnly"});
							$("#sex").attr({"disabled":"disabled"});
						},
						error : function(data) {
						}
						});
					$.basewindow("新建员工", $chuangkou, 900, 850);
					$(".basewindow").addClass("custom-scroll");
					$chuangkou.css({ "display" : "block" });
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}
			});
		//删除
		$(".control .del").click(function() {
				if (grid.getSelectedRow().length > 0) {
						$.yesorno("是否删除？",300,160,function() {
							var id = grid.getRowRecord(grid.getSelectedRow()).id;
							var delUrl = "${pageContext.request.contextPath}/emp/delete.do?t="+ new Date().getTime();
							//后台删除
							$.ajax({
								type : "get",
								dataType : "json",
								url : delUrl,
								data : "id="+ id,//要发送的数据
								success : function(data) {
									if ("success" == data.resultMessage) {
										$(".table .selected").remove();
									}
									if ("errors" == data.resultMessage) {
										var str = data.data;
										$.threesecond(str,200,100,1000);
									}
								},
								error : function(data) {}
							});
		
						}, function() {
							$.threesecond("看来还是手下留情了!",
									200, 100, 1000);
						});} else {
							$.threesecond("请先选择", 200, 100, 1000);
						}
		});
		//判断和提交
		$("#basicSalary").val();
		ISSHIYONG = "no";
		$("#genrer").change(function() {
			if ($("#genrer").val() == "1") {
				ISSHIYONG = "yes";
				$("#probationSalary").after('<span class="mustred">*</span>');
			} else {
				ISSHIYONG = "no";
				$("#probationSalary").next("span").remove();
			}
		})
	 
		$("#sousuo .koko").click(function() { 
			var snumber = $("#snumber").val();
			var snamepy = $("#snamepy").val(); 
			var sposid = $("#sposid").combogrid("getValue");
			var sdepid = $("#sdepid").combogrid("getValue");
			
			var searchParames={
				"snumber":snumber,
				"snamepy":snamepy, 
				"sposid":sposid,
				"sdepid":sdepid 
			}
			grid.search(searchParames);
			$("#sousuo").hide(); 
			$(".zhezhao-basewindow").hide(); 
			$(".basewindow").hide();
			$('body').append($("#sousuo"));
		})
		
		$("#empForm #actores").change(function(){
		    var id = $("#empForm #id").val();
		    if(id==null || id==""){
		        if($("#empForm #actores")[0].checked ==true){
			        $("#sp").show();
			    }else{
			        $("#sp").hide();
			    }
		    }
		})
		
		$("#empForm .okok").click(function() {
			$(".zhezhao-basewindow").show();
		    $("#name").removeAttr("readOnly");
			$("#namepy").removeAttr("readOnly");
			$("#sex").removeAttr("disabled");
			$("#genrer").removeAttr("disabled");
			$("#employeType").removeAttr("disabled");
		
			var vid = $("#id").val();
			var vname = $("#name").val();
			var vnamepy = $("#namepy").val();
			var vposid = $("#posid").combogrid("getValue");
			var vbirth = $("#birth").combogrid("getValue");
			var vjoinDate = $("#joinDate").combogrid("getValue");
			var vregularDate = $("#regularDate").combogrid("getValue");
			var vidcard = $("#idcard").val();
			var vphone = $("#phone").val();
			var vsalary = $("#salary").val();
			var votherSubsidy = $("#otherSubsidy").val();
			var probationSalary = $("#probationSalary").val();
			var foodSubsidyE = $("#foodSubsidyE").val();
			var otherSubsidyE = $("#otherSubsidyE").val();
			var jobSubsidyE = $("#jobSubsidyE").val();
			var vliveAdress = $("#liveAdress").val();
			var emergencyContact =$("#emergencyContact").val();
			var emergencyPhone =$("#emergencyPhone").val();
			if(($("#empForm #employeType").val() == "2")){
			    var push =$("#pushMoney").val();
			    var shareBankCard = $("#shareBankCard").val();
			    var shareBankAddress = $("#shareBankAddress").val();
			    if(push == ""){
			        $("#pushMoney").focus();
			        $.threetop("艺人必须填写提成比例!");
			        return false;
			    }
			}
			if ($("#basicSalary").val() == "") {
				$("#basicSalary").focus();
				$.threetop("带 * 为必填项!");
				return false;
			}
			if (ISSHIYONG == "yes" && $("#probationSalary").val() == "") {
				$("#probationSalary").focus();
				$.threetop("带 * 为必填项!");
				return false;
			}
			if (!vname) {
				$("#name").focus();
				$.threetop("姓名不能为空!");
				return false;
			}
			if (!vnamepy) {
				$("#namepy").focus();
				$.threetop("姓名拼音不能为空!");
				return false;
			}
			if (!vidcard) {
				$("#idcard").focus();
				$.threetop("身份证号不能为空!");
				return false;
			}
			if (!vphone) {
				$("#phone").focus();
				$.threetop("手机号不能为空!");
				return false;
			}
			if (!vsalary) {
				$("#salary").focus();
				$.threetop("薪资不能为空!");
				return false;
			}
			if (!vliveAdress) {
				$("#liveAdress").focus();
				$.threetop("住址不能为空!");
				return false;
			}
			if (!vbirth) {
				$.threetop("生日不能为空!");
				return false;
			}
			if (!vregularDate) {
				$("#regularDate").focus();
				$.threetop("转正日期不能为空!");
				return false;
			}
			if (!vposid) {
				$("#posid").focus();
				$.threetop("职位不能为空!");
				return false;
			}
			if (!vjoinDate) {
				$("#joinDate").focus();
				$.threetop("入职日期不能为空!");
				return false;
			}
			if (!votherSubsidy) {
				$("#otherSubsidy").focus();
				$.threetop("转正补贴不能为空!");
				return false;
			}
			if (!probationSalary) {
				$("#probationSalary").focus();
				$.threetop("试用补贴不能为空!");
				return false;
			}
			
			$("#empForm").ajaxSubmit(function(data) {
				if (data.resultStatus == 200) {
				    $("#sp").hide();
					$(".zhezhao-basewindow").hide();
					$(".basewindow").hide();
					$('body').append($("#empForm"));
					$("#empForm").hide();
					grid.refreshPage();
				} else {
					alert("wrong!");
				}
			})

		})
		//离职
		$(".control .leave").click(function() {
			if (grid.getSelectedRow().length > 0) {
				var $chuangkou = $("#leaveForm");
				$.basewindow("离职原因:", $chuangkou, 270, 270, function() {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;

					$("#leaveForm #id").val(id);
					$("#leaveForm #type").val(2);
					$("#leaveForm #tt").val("show");
				}, function() {
					$.threesecond("看来还是手下留情了!", 200, 100, 1000);
				});
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
			}
		});
		//快速搜索
		$("#fast").change(function() {
		    var fastArg = $("#fast").val();
			var searchParames={
			    "fastArg": fastArg
			}
			grid.search(searchParames);
		})
		
		this.tree = [];
		$.getJSON("${pageContext.request.contextPath}/empAttendance/readTree.do?t="+new Date().toString(),function(data){
			 $("#tree").treeview({
			  data: data.data,
			  levels: 5,
			  expanded:false,
			  selectedBackColor:"#f3f3f3",
			  icon: "glyphicon glyphicon-stop",
  			  //selectedIcon: "glyphicon glyphicon-stop",
			  onNodeSelected: function(event, data) {
			    ids=data.id;
			    var click_type;
			 		//加载数据
				var searchParames={
						    "sdepid": ids
						}
				grid.search(searchParames);
			  }
			});	 
		});
		
		/** 转岗*/
		$(".control .transfer").click(
			function() {
			var $chuangkou = $("#empTransfer");
			 $("#sp").hide();
				$("#probationSalary").next("span").remove();
				if (grid.getSelectedRow().length > 0) {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;
					var editurl = "${pageContext.request.contextPath}/emp/edit.do?t="+ new Date().toTimeString();
					$.ajax({
						type : "get",
						dataType : "json",
						url : editurl,
						data : "id=" + id,
						show : "slide",
						success : function(data) {
/* 						document.getElementById("tid").innerHTML=data.id;
 */						$("#empTransfer #id").text(data.id);
						$("#empTransfer #num").text(data.num);
						$("#empTransfer #name").text(data.name);
						$("#empTransfer #oldDepart").text( data.depName);
						$("#empTransfer #oldPosition").text(data.posName);
						$("#empTransfer #oldemployeType").text(data.employeType);
						$("#tposid").combogrid("setValue", data.posId);
						$("#tdepid").combogrid("setValue", data.depId);
						$("#tempid").combogrid("setValue", data.empId);
						$("#empTransfer #employeType").val();
						},
					error : function(data) {
					}
				});
				$.basewindow("员工转岗", $chuangkou, 800, 280);
				$(".basewindow").addClass("custom-scroll");
				$chuangkou.css({
					"display" : "block"
				});
				} else {
					$.threesecond("请先选择", 200, 100, 1000);
				}

			});
			
			$("#empTransfer .ok").click(function() {
					var id = document.getElementById("id").innerHTML;
					var oldemployeType = document.getElementById("oldemployeType").innerHTML;
					if($("#employeType").val() == "1" & oldemployeType == "EMPLOYEE"){
							$.threetop("员工类型相同不可转!");
							return false;
					}else if($("#employeType").val() == "2" & oldemployeType == "ACTORES"){
						$.threetop("员工类型相同不可转!");
							return false;
					}else if($("#employeType").val() == "3" & oldemployeType == "TALENT"){
						$.threetop("员工类型相同不可转!");
						return false;
					}else if($("#employeType").val() == "4" & oldemployeType == "MANAGER"){
						$.threetop("员工类型相同不可转!");
						return false;
					}else{
						var employeType = $("#employeType").val();
					}
				var posid = $("#tposid").combogrid("getValue");
				var depid = $("#tdepid").combogrid("getValue");
				if($("#empTransfer #tempid").val() != ""){
				var empid = $("#tempid").combogrid("getValue");
				}
				
				var transferParames={
					"id":id,
					"posId":posid, 
					"depId":depid, 
					"empId":empid,
					"employeType":employeType 
				}
				$.ajax({	
	    				type: "POST",
	    				cache: false,
	    				url: "${pageContext.request.contextPath}/emp/transfer.do",
	    				data:JSON.stringify(transferParames),
	    				contentType:"application/json; charset=utf-8",
	    				success:function(data){
	    					if(data.resultStatus == 200){
	    						$(".zhezhao-basewindow").hide();
	    						$(".basewindow").hide();
	    						$('body').append($("#empTransfer"));
	    						$("#empTransfer").hide(); 
	    						grid.refreshPage();
	    					}
	    				}
	    		});
		})
			
	});
	
	
	/** 验证纯数字 */
	function checkNum(data){
	    var reg = /^\d+(\.\d+)?$/; 	 
	    if(reg.test(data.value)==true){
	        $("#"+data.id).val( data.value);
	    }else{
	        $("#"+data.id).val(0);
	    }
	}
</script>
<script>
$(function() {
	$("#depid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesParent.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "部门",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() { },
			down : function() { },
			enter : function() { },
			query : function(q) {
				if (q != null && q != "") {
					$("#depid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#depid").combogrid("setValue", q);
				}
			}
		}
		
	});

	$("#sdepid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesParent.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "部门",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#depid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#depid").combogrid("setValue", q);
				}
			}
		}
	});

	$("#empid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "上级主管",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#empid").combogrid("grid").datagrid("reload", {"filter":q});
					$("#empid").combogrid("setValue", q);
				}
			}
		}
	});

	$("#posid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/emp/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "职位",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#posid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#posid").combogrid("setValue", q);
				}
			}
		}
	});
	$("#sposid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/emp/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [ [ {
			field : "name",
			title : "职位",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#posid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#posid").combogrid("setValue", q);
				}
			}
		}
	});
	
	$("#tdepid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesParent.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "部门",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() { },
			down : function() { },
			enter : function() { },
			query : function(q) {
				if (q != null && q != "") {
					$("#depid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#depid").combogrid("setValue", q);
				}
			}
		}
		
	});
	
	$("#tempid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/department/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "上级主管",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#empid").combogrid("grid").datagrid("reload", {"filter":q});
					$("#empid").combogrid("setValue", q);
				}
			}
		}
	});

	$("#tposid").combogrid({
		panelWidth : 500,
		method : "POST",
		datatype : "json",
		url : "${pageContext.request.contextPath}/emp/readPagesSkip.do",
		mode : "remote",
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		idField : "id",
		textField : "name",
		pageSize : 30,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		columns : [[{
			field : "name",
			title : "职位",
			width : 120,
			sortable : true
		}]],
		keyHandler : {
			up : function() {},
			down : function() {},
			enter : function() {},
			query : function(q) {
				if (q != null && q != "") {
					$("#posid").combogrid("grid").datagrid("reload", {"filter" : q});
					$("#posid").combogrid("setValue", q);
				}
			}
		}
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
			<span>人事系统</span> 
			<span>></span>
			<span class="color99">员工档案</span>
	    </div>
	    <div class="clearFix right-twoRight">
			<div class="fr">
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索">
			    	<button type="button" id="seach" class="tableok">搜索</button>				
				</div>
				<input class="searchAll" type="button" value="高级搜索"/>
			</div>
		</div>
		
		<!-- 转岗-->
			<form action="${pageContext.request.contextPath}/emp/transfer.do" id="empTransfer" class="transfer" method="post" enctype="multipart/form-data" style="display:none;margin-left:20px;margin-right:20px;">
	            <table class="table">
	                <tr>
	                    <td colspan="2" style="width:50%;padding:0;border:none;" class="tableId"><input type="hidden"></td>
	                    <td colspan="2" style="width:50%;padding:0;border:none;"></td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">员工号：</td>
	                    <td id="num" class="num taL"></td>
	                    <td style="text-align: right;">姓名：</td>
	                    <td id="name" class="name taL"></td>
	                </tr>
	                <tr>
	                	<td style="text-align: right;">原部门：</td>
	                    <td id="oldDepart" class="oldDepart taL"></td>
	                	<td style="text-align: right;">原职位：</td>
	                    <td id="oldPosition" class="oldPosition taL"></td>
	                    </td>
	                </tr>
	                <tr>
						<td style="text-align: right;">调整后部门：</label></td>
						<td class="taL"><input id="tdepid" name="tdepid" class="w20"><span class="mustred">*</span></td>
						<td style="text-align: right;">调整后职位：</label></td>
						<td class="taL"><input id="tposid" name="tposid" class="w20"><span class="mustred">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">上级主管：</label></td>
						<td class="taL"><input id="tempid" name="tempid" class="w20" type="text"><span class="mustff">*</span></td>
						
						<td style="text-align: right;">员工属性：</td>
						<td class="taL"><select type="text" id="employeType" name="employeType" style="width:200px;">
							<option value="1">普通员工</option>
							<option value="2">艺人</option>
							<option value="3">星探</option>
							<option value="4">管理</option>
						</select></td>
					</tr>
						<td style="text-align: right; display:none ">原员工类型：</td>
						<td class="oldemployeType" id="oldemployeType" style="display:none; "></td>
					    <td style="text-align: right; display:none; ">id</td>
	                    <td id="id" class="id" style="display:none; "></td>
					</tr>
	            </table>
				<p class="bCenter"><input class="ok" type="button" value="提交"><input class="cancel" type="reset" name="Input" value="关闭"></p>	       
			 </form>
	
		<!-- 高级搜索 -->
		<form action="${pageContext.request.contextPath}/emp/search.do" method="post" enctype="multipart/form-data" id="sousuo" style="display:none;margin:0 20px;">
			<table class="table">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr><td colspan="4"><p style=" margin: 0">基本资料</p></td></tr>
				<tr>
					<td style="text-align: right;">员工号：</td>
					<td><input type="text" name="snumber" id="snumber" style="width:200px;"></td>
					<td style="text-align: right;">姓名/艺名：</td>
					<td><input type="text" name="snamepy" id="snamepy" style="width:200px;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">部门：</td>
					<td><input name="sdepid" id="sdepid" style="width:200px;">
					<td style="text-align: right;">职位：</td>
					<td><input name="sposid" id="sposid" style="width:200px;"><span style="margin-left:10px;color:#e35d00"></span></td> 
				</tr>
			</table>
			<p class="bCenter"><input type="hidden" name="id" id="id"> <span class="koko">提交</span> <input class="cancel" type="reset" name="Input" value="关闭"></p>
		</form>
		
		<!-- 查看详细 -->
		<div id="emplook" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table2">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr><td colspan="4" class="yellowline"><p style=" margin: 0">基本资料</p></td></tr>
				<tr>
					<td style="text-align: right;" class="hei">姓名：</td>
					<td><span id="vname" class="w200"></span></td>
					<td style="text-align: right;" class="hei">	姓名拼音：</td>
					<td><span id="vnamepy"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">性别：</td>
					<td><span id="vsex" class="w200"> </span></td>
					<td style="text-align: right;" class="hei">艺名：</td>
					<td><span id="valiasname" class="w200"> </span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">身份证号：</td>
					<td><span id="vidcard" class="w200">123132</span></td>
					<td style="text-align: right;" class="hei">生日：</td>
					<td><span id="vbirth" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">联系电话：</td>
					<td><span id="vphone" class="w200"></span></td>
					<td style="text-align: right;" class="hei">QQ：</td>
					<td><span id="vqq" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">入职介绍人：</td>
					<td><span id="vintroducer" class="w200"></span></td>
					<td style="text-align: right;" class="hei">户籍所在地：</td>
					<td class="taL"><span id="vhostRegister" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">紧急联系人：</td>
					<td><span id="vemergencyContact" class="w200"></span></td>
					<td style="text-align: right;" class="hei">紧急联系人电话 ：</td>
					<td><span id="vemergencyPhone" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">户口类型：</td>
					<td><span id="vaccount" class="w200"></span></td>
					<td style="text-align: right;" class="hei">电子邮件：</td>
					<td><span id="vemail" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">现居地址：</td>
					<td colspan="3"><span id="vliveAdress" class="w200"></span></td></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">户籍地址 ：</td>
					<td colspan="3"><span id="vhostAddress" class="w200"></span></td></td>
				</tr>
				<tr><td colspan="4" class="yellowline"><p style=" margin: 0">职位资料</p></td></tr>
				<tr>
					<td style="text-align: right;" class="hei">部门：</label></td>
					<td><span id="vdepid" class="w200"></span></td>
					<td style="text-align: right;" class="hei">职位：</label></td>
					<td><span id="vposid" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">入职日期：</td>
					<td><span id="vjoinDate" class="w200"></span></td>
					<td style="text-align: right;" class="hei">上级主管：</label></td>
					<td><span id="vempid" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">状态：</td>
					<td><span id="vgenrer" class="w200"></span></td>
					<td style="text-align: right;" class="hei">员工类型：</td>
					<td><span id="vemployeType" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">转正日期：</td>
					<td><span id="vregularDate" class="w200"></span></td>
					<td style="text-align: right;" class="hei">基本工资：</td>
					<td><span id="vsalary" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">试用补贴：</td>
					<td><span id="vprobationSalary" class="w200"></span></td>
					<td style="text-align: right;" class="hei">转正补贴：</td>
					<td><span id="votherSubsidy" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">公积金缴纳：</td>
					<td><span id="vjiaojin" class="w200"></span></td>
					<td style="text-align: right;" class="hei">社保缴纳：</td>
					<td><span id="vsocialSecurity" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei"> 社保基数：</td>
					<td><span id="SScardinalNumber" class="w200"></span></td>
					<td style="text-align: right;" class="hei"> 公积金基数：</td>
					<td><span id="PFcardinalNumber" class="w200"></span></td>
				</tr>
				<tr> 
					<td style="text-align: right;" class="hei">劳动合同期限：</td>
					<td><span id="vcontractDT" class="w200"></span></td>
					<td style="text-align: right;" class="hei">公积金帐号 ：</td>
					<td><span id="vcpfAccounts" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">工资卡帐号：</td>
					<td><span id="vbankCard" class="w200"></span></td>
					<td style="text-align: right;" class="hei">工资银行卡开户行：</td>
					<td><span id="vbankAddress" class="w200"></span></td>
				</tr>
				<tr><td colspan="4" class="yellowline"><p style=" margin: 0">岗位补充资料</p></td></tr>
				<tr>
					<td style="text-align: right;" class="hei">饭贴：</td>
					<td><span id="FoodSubsidyE" class="w200"></span></td>
					<td style="text-align: right;" class="hei">其他补贴(公司级)：</td>
					<td><span id="OtherSubsidyE" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">岗位津贴：</td>
					<td><span id="JobSubsidyE" class="w200"></span></td>
					<td colspan="2"></td>
				</tr>
				<tr id="artistdata"><td colspan="4" class="yellowline"><p style=" margin: 0">艺人资料</p></td></tr>
				<tr>
					<td style="text-align: right;" class="hei">艺人保底：</td>
					<td><span id="vactoresBSalary" class="w200"></span></td>
					<td style="text-align: right;" class="hei">提成比例：</td>
					<td><span id="vpushMoney" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">分成账号(艺人)：</td>
					<td><span id="vshareBankCard" class="w200"></span></td>
					<td style="text-align: right;" class="hei">银行卡：</td>
					<td><span id="vshareBankAddress" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">技能：</td>
					<td colspan="3"><span id="vspeciality" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">备注：</td>
					<td colspan="3"><span id="vremarks" class="w200"></span></td>
				</tr>
			</table>
			<p style="text-align:center;"><input class="cancel" type="reset" name="Input" value="关闭"></p>
		</div>
		
		<!--表头 -->
		<div class="custom-scroll mains">
			<div class="lefttree custom-scroll" >
				<table class="table" style="min-width: 20%;">
					<tr><th align="center">部门导航</th></tr>
					<tr><td id="tree" style="text-align:left;"></td></tr>
				</table>
			</div>
			<div class="rightbox custom-scroll thistable">
				<table id="searchTable" class="table tablelist">
					<tr class="firsttr" style="overflow: auto ;" >
						<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
						<th w_num="line" w_align="center" width="1%;">序号</th>
						<th w_index="num" w_align="center" width="1%;">员工号</th>
						<th w_index="name" w_align="center" width="1%;">姓名</th> 
						<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
						<th w_index="sex" w_align="center" width="1%;">性别</th>
						<th w_index="depName" w_align="center" width="1%;">部门</th>
						<th w_index="posName" w_align="center" width="1%;">职位</th>  
						<th w_index="genrer" w_align="center" width="1%;">类型</th>
						<th w_index="phone" w_align="center" width="1%;">联系电话</th>
						<th w_index="introducer" w_align="center" width="1%;">入职介绍人</th>
						<th w_index="joinDate" w_align="center" width="1%;">入职日期 </th>
						<th w_index="remarks" w_align="center" width="1%;">备注</th>
					</tr>
				</table>
				<div class="control">
					<jsp:include page="attsCommon.jsp"></jsp:include>
				</div>
			</div>
			<!-- 新增 -->
			<form action="${pageContext.request.contextPath}/emp/empsave.do" method="post" enctype="multipart/form-data" id="empForm" style="display:none;margin-left:20px;margin-right:20px;">
	
				<table class="table">
					<tr>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					</tr>
					<tr><td colspan="4"><p class="tit">基本资料</p><hr/></td></tr>
					<tr>
						<td style="text-align: right;">姓名：</td>
						<td class="taL"><input type="text" name="name" id="name" style="width:200px;"> <span class="mustred">*</span></td>
						<td style="text-align: right;">姓名拼音：</td>
						<td class="taL"><input type="text" name="namepy" id="namepy" style="width:200px;"> <span class="mustred">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">性别：</td>
						<td class="taL">
							<select id="sex" name="sex" style="width:200px;">
								<option value="1">女</option>
								<option value="2">男</option>
							</select><span style="color:#e35d00;margin-left:10px">*</span>
						</td>
						<td style="text-align: right;">艺名：</td>
						<td class="taL"><input type="text" name="aliasname" id="aliasname" class="m20"></td>
					</tr>
					<tr>
						<td style="text-align: right;">身份证号：</td>
						<td class="taL"><input type="text" name="idcard" id="idcard" style="width:200px;"> <span class="mustred">*</span></td>
						<td style="text-align: right;">生日：</td>
						<td class="taL"><input class="easyui-datebox" editable="false" name="birth" id="birth" style="width:200px;"> <span style="margin-left:10px;color:#e35d00">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">联系电话：</td>
						<td class="taL"><input type="text" name="phone" id="phone" style="width:200px;" class="easyui-numberbox input"> <span class="mustred">*</span></td>
						<td style="text-align: right;">QQ：</td>
						<td class="taL"><input type="text" name="qq" id="qq" class="easyui-numberbox input m20"></td>
					</tr>
					<tr>
						<td style="text-align: right;">入职介绍人：</td>
						<td class="ui-widget taL"><input type="text" class="ui-autocomplete-input m20" autocomplete="off" name="introducer" id="introducer"></td>
						<td style="text-align: right;">户籍所在地：</td>
						<td class="taL"><input type="text" name="hostRegister" class="m20" id="hostRegister"></td>
					</tr>
					<tr>
						<td style="text-align: right;">紧急联系人：</td>
						<td class="taL"><input type="text" name="emergencyContact" id="emergencyContact" class="m20"></td>
						<td style="text-align: right;">紧急联系人电话 ：</td>
						<td class="taL"><input type="text" name="emergencyPhone" id="emergencyPhone" class="m20"></td>
					</tr>
					<tr>
						<td style="text-align: right;">户口类型：</td>
						<td class="taL">
							<select id="account" class="m20" name="account">
								<option value="CHENGZHEN">城镇</option>
								<option value="NONGCUN">农村</option>
							</select>
						</td>
						<td style="text-align: right;">电子邮件：</td>
						<td class="taL"><input type="text" name="email" id="email" class="m20"></td>
					</tr>
					<tr>
						<td style="text-align: right;">现居地址：</td>
						<td  class="taL" colspan="3"><input type="text" name="liveAdress" id="liveAdress" style="width:620px;"> <span class="mustred">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">户籍地址 ：</td>
						<td  class="taL" colspan="3"><input type="text" class="m20" name="hostAddress" id="hostAddress" style="width:620px;"></td>
					</tr>
					<tr><td colspan="4"><p class="tit">职位资料</p><hr/></td></tr>
					<tr>
						<td style="text-align: right;">部门：</label></td>
						<td class="taL"><input id="depid" name="depid" style="width:200px;"> <span class="mustred">*</span></td>
						<td style="text-align: right;">职位：</label></td>
						<td class="taL"><input id="posid" name="posid" style="width:200px;"> <span class="mustred">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">入职日期：</td>
						<td class="taL"><input class="easyui-datebox" editable="false" type="text" name="joinDate" id="joinDate" style="width:200px;"> <span class="mustred">*</span></td>
						<td style="text-align: right;">上级主管：</label></td>
						<td class="taL"><input id="empid" name="empid" class="m20"></td>
					</tr>
					<tr>
						<td style="text-align: right;">状态：</td>
						<td class="taL">
							<select id="genrer" name="genrer" style="width:200px;">
								<option value="1">试用</option>
								<option value="2">正式</option>
							</select> <span class="mustred">*</span>
						</td>
						<td style="text-align: right;">员工属性：</td>
							<td class="taL">
							<select id="employeType" name="employeType" style="width:200px;">
								<option value="1">普通员工</option>
								<option value="2">艺人</option>
								<option value="3">星探</option>
								<option value="4">管理</option>
							</select><span style="color:#e35d00;margin-left:10px">*</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">转正日期：</td>
						<td class="taL"><input type="text" class="easyui-datebox" editable="false" name="regularDate" id="regularDate" style="width:200px;"> <span class="mustred">*</span></td>
						<td style="text-align: right;">基本工资：</td>
						<td class="taL"><input type="text" style="width:200px;" class="easyui-numberbox input" name="salary" id="salary"><span class="mustred">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">试用补贴：</td>
						<td class="taL"><input type="text" class="easyui-numberbox input m20" name="probationSalary" id="probationSalary"></td>
						<td style="text-align: right;">转正补贴：</td>
						<td class="taL"><input type="text" style="width:200px;" class="easyui-numberbox input" name="otherSubsidy" id="otherSubsidy"><span class="mustred">*</span></td>
					</tr>
					<tr>
						<td style="text-align: right;">社保基数：</td>
						<td class="taL"><input type="text" class="easyui-numberbox input m20" name="SScardinalNumber" id="SScardinalNumber"></td>
						<td style="text-align: right;">公积金基数：</td>
						<td class="taL"><input type="text" class="easyui-numberbox input m20" name="PFcardinalNumber" id="PFcardinalNumber"></td>
					</tr>
					<tr>
						<td style="text-align: right;">公积金缴纳：</td>
						<td class="taL">
							<select id="jiaojin" name="jiaojin" style="width:200px;">
								<option value="1">是</option>
								<option value="0">否</option>
							</select><span class="mustred">*</span>
						</td>
						<td style="text-align: right;">社保缴纳：</td>
						<td class="taL">
							<select id="socialSecurity" name="socialSecurity" style="width:200px;">
								<option value="1">是</option>
								<option value="0">否</option>
							</select><span class="mustred">*</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">劳动合同期限：</td>
						<td class="taL"><input class="easyui-datebox m20" editable="false" name="contractDT" id="contractDT" type="text"><span class="mustff">*</span></td>
						<td style="text-align: right;">公积金帐号 ：</td>
						<td class="taL"><input type="text" class="m20" name="cpfAccounts" id="cpfAccounts"></td>
					</tr>
					<tr>
						<td style="text-align: right;">工资卡帐号：</td>
						<td class="taL"><input type="text" class="m20" name="bankCard" id="bankCard"></td>
						<td style="text-align: right;">工资银行卡开户行：</td>
						<td class="taL"><input type="text" class="m20" name="bankAddress" id="bankAddress"></td>
					</tr>
					<tr>
						<td style="text-align: right;">备注：</td>
						<td class="taL" colspan="3"><input type="text" name="remarks" id="remarks" style="width:631px;"></td>
					</tr>
					<tr><td colspan="4"><p class="tit">岗位补充信息</p><hr/></td></tr>
					<tr>
						<td style="text-align: right;">饭贴：</td>
						<td class="taL"><input type="text" style="width:200px;" class="easyui-numberbox input" name="foodSubsidyE" id="foodSubsidyE"></td>
						<td style="text-align: right;">岗位津贴：</td>
						<td class="taL"><input type="text" style="width:200px;" name="jobSubsidyE"  class="easyui-numberbox input"id="jobSubsidyE"></td>
					</tr>
					<tr>
						<td style="text-align: right;">其他补贴：</td>
						<td class="taL"><input type="text" style="width:200px;" class="easyui-numberbox input" name="otherSubsidyE" id="otherSubsidyE"></td>
					</tr>
					<tr style="display:none" id="showFour"><td colspan="4"><p style=" margin: 0">艺人信息</p></td></tr>
					<tr style="display:none" id="showOne">
						<td style="text-align: right;">艺人保底：</td>
						<td><input type="text" style="width:200px;" class="easyui-numberbox input" name="actoresBSalary" id="actoresBSalary"></td>
						<td style="text-align: right;">提成比例：</td>
						<td><input type="text" id="pushMoney" style="width:186px" name="pushMoney" onblur="checkNum(this)">%</span><span class="mustred">*</span></td>
					</tr>
					<tr style="display:none" id="showTwo">
						<td style="text-align: right;">分成账号(艺人)：</td>
						<td><input type="text" style="width:200px;" name="shareBankCard" id="shareBankCard"></td>
						<td style="text-align: right;">分成账户开户行：</td>
						<td><input type="text" style="width:200px;" name="shareBankAddress" id="shareBankAddress"></td>
					</tr>
					<tr style="display:none" id="showThree">
						<td style="text-align: right;">艺人特长：</td>
						<td colspan="3"><input type="text" style="width:631px;" name="speciality" id="speciality"></td>
					</tr>
				</table>
				<p style="text-align:center;"><input type="hidden" name="id" id="id"> <span class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭"></p>
			</form>
			
			<!-- 离职 -->
			<form action="${pageContext.request.contextPath}/emp/updateProbationer.do" method="post" id="leaveForm" style="display:none;margin-left:20px;margin-right:20px;">
				<p>离职原因：</p>
				<input type="hidden" name="id" id="id"> <input type=hidden name="type" id="type" value="2"> <input type=hidden name="tt" id="tt" value="show">
				<p><textarea name="reason" id="reason" style="width:228px; height:100px;"></textarea></p>
				<p class="bCenter"><input class="ok" type="submit" value="提交"><input class="cancel" type="reset" name="Input" value="关闭"></p>
			</form>
			
		</div>
	</div>
	
	
	
</body>
</html>