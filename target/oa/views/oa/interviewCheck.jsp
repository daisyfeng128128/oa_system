<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>面试管理-平台审核</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">

 .kkkk{font-size:14px;color:#0099cc;text-decoration:underline;}
.kkkk:hover{font-size:14px;color:#cc9966;text-decoration:underline;}
.kkkk{text-decoration:underline;}
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;overflow-y:scroller;
	position: relative;
}
.interLook{width:100%;height:80%}
.salarylook{width:100%;height:80%}
.mustred{margin-left:10px;color:#ff0000}
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
.right-twohalf{
	margin-top:0;
}
.right-twohalf span{
	font-size:14px;
}
.tableok{
	position:static;
}
.control{
	margin-left:-168px;
}

</style>
<script type="text/javascript">
	$(function () {
    	$("#excel").hide();
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/interview/readPages.do?date=${date}&type=1&branchs=${branchs}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            event: {
	            customCellEvents: {
	                click: function (record, rowIndex, colIndex, tdObj, trObj, options) {
	                    if(colIndex ==4 && record.photo != null && record.photo != ""  ){
	                		var $chuangkou = $("#idlook");
	                		$("#idlook #yuanImage").attr("src",record.photo);
	                		$.basewindow("原图", $chuangkou, 700, 580);
	                		$chuangkou.css({
	                			"display" : "block"
	                		});
	                    }else if(colIndex==5  && record.videoPhoto != null && record.videoPhoto != ""){
	                        var $chuangkou = $("#idlook");
                			$("#idlook #yuanImage").attr("src",record.videoPhoto);
                			$.basewindow("原图", $chuangkou, 700, 580);
                			$chuangkou.css({
                				"display" : "block"
                			});
	                    }
	                }
	            }
            },
            extend: {
                settings: {
                    supportGridEdit:true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                        photo: {
    	                    build: function (edit, value) {
    	                        if(value!= "" && value != null)
    	                       		return '<input type="image" src='+ value +' width="80" height="80" />';
    	                    }
	    	            },
	    	            videoPhoto: {
    	                    build: function (edit, value) {
    	                       if(value!= "" && value != null)
    	                      	  return '<input type="image" src='+ value +' width="80" height="80" />';
    	                    }
	    	            },
	    	            linkUrl: {
                            build: function (edit, value) {
                                if(value != "http://" && value != "" && value != null){
                                    return  '<a  class="kkkk"   style="width:100px;" href="'+ value +'" target="view_window"  >查看视频</a>';
                                }else{
                                    return '<span style="color:#666666">查看视频<span>';
                                }
                            } 
                        }
                    } 
                }
            }
        });
	
        //拒绝
		$(".control .rej").click(function() {
			if (grid.getSelectedRow().length > 0) {
			    var $chuangkou = $("#reje");
			    $.basewindow("拒收审核评语", $chuangkou, 400, 250);
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
				$("#reje #id").val(id);
				$chuangkou.css({ "display" : "block" });
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
			}
			
		}) 
		
		
        //录用
		$(".control .empl").click(function() {
			if (grid.getSelectedRow().length > 0) {
				var id = grid.getRowRecord(grid.getSelectedRow()).id;
				var $chuangkou ; 
				var editurl = "${pageContext.request.contextPath}/interview/edit.do?t="+ new Date().toTimeString();
				$.ajax({
					type : "get",
					dataType : "json",
					url : editurl,
					data : "id=" + id,
					show : "slide",
					success : function(data) {
					    var $data = data.data;
					    if( $data!= null){
					        if($data.genre=="OFFLINE"){
					            $chuangkou = $("#emplDiv");
							    $.basewindow("录用", $chuangkou, 400, 250);
							    $("#emplDiv #id").val(id);
							    $chuangkou.css({ "display" : "block" });
					        }else if($data.genre=="ONLINE"){
					            $chuangkou=$("#actoresSaveForm");
					        	$.basewindow("线上入职","#actoresSaveForm",820,495,"",$no);
								$chuangkou.css({ "display" : "block" });
					            if ($data.sex == "女") {
									$("#actoresSaveForm #sex").val("WOMAN").selected;
								} else if ($data.sex == "男") {
									$("#actoresSaveForm #sex").val("MAN").selected;
								}
								$("#actoresSaveForm #interId").val($data.id);
								$("#actoresSaveForm #realname").val($data.name);
								$("#actoresSaveForm #aliasname").val($data.aliasname);
								$("#actoresSaveForm #phone").val($data.phone);
								$("#actoresSaveForm #qq").val($data.qq);
								var $no=function(){
						        	isSearch=false; 
						        }
						        
						        $("#actoresSaveForm #pushMoney").numberbox("setValue",0);
						    	if($("#actoresForm select option").length==0){
						        	var data={
						        		id:$(".right-onehalf .spanchange").attr("value")
						        	} 
						    	}
					        }
					    }
					},
					error : function(data) {
					}
					});
				
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
			}
			
		}) 
        $("#ok").click(function(){
            var track = $("#track").val();
            var id = $("#reje #id").val();
       		if(track =="限20字" || track==""){
       		 	$("#track").focus();
				$.threetop("拒收理由");
       		    return  false;
       		}
       	   var editurl = "${pageContext.request.contextPath}/interview/addReje.do?t="+ new Date().toTimeString();
		    $.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id+"&track="+track,
				show : "slide",
				success : function(data) {
				    if ("success" == data.resultMessage) {
					    $(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$('body').append($("#reje"));
						$("#reje").hide();
						grid.refreshPage();
					}
				},
				error : function(data) {
				}
			});
        })
        
        
         $("#sub").click(function(){
            var launchTime =$("#launchTime").datebox("getValue");
            var id = $("#emplDiv #id").val();
       		if(launchTime =="限20字" || launchTime==""){
       		 	$("#launchTime").focus();
				$.threetop("请选择开播时间");
       		    return  false;
       		}
       	   var editurl = "${pageContext.request.contextPath}/interview/addEmpl.do?t="+ new Date().toTimeString();
		    $.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id+"&launchTime="+launchTime,
				show : "slide",
				success : function(data) {
				    if ("success" == data.resultMessage) {
					    $(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$('body').append($("#emplDiv"));
						$("#emplDiv").hide();
						grid.refreshPage();
					}
				},
				error : function(data) {
				}
			});
        })
		//保存
	 $("#actoresSaveForm .okok").click(function () {
         var aliasname = $("#actoresSaveForm #aliasname").val();
         var phone = $("#actoresSaveForm #phone").val();
         var minimumGuarantee = $("#actoresSaveForm #minimumGuarantee").val();
         var basicSalary = $("#actoresSaveForm #basicSalary").val();  
         var pushMoney = $("#actoresSaveForm #pushMoney").val();
         var plat = $("#actoresSaveForm #plat").combogrid('getValue');
         var channel = $("#actoresSaveForm #channel").val();
         var beliveTime = $("#actoresSaveForm #beliveTime").datebox("getValue");
        
		 var telReg = !!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/); 
 
         if(telReg == false){
        	 $.threetop("请填写正确的手机号!");
         }else if(aliasname == "" || aliasname == null){
        	 $.threetop("请填写艺名!");
         }else  if(minimumGuarantee == "" || minimumGuarantee == null){
        	 $.threetop("请填写保底!");
         }else  if(pushMoney == "" || pushMoney == null){
        	 $.threetop("请填写提成比例!");
         } else if(!$("#actoresSaveForm #entryTime").combogrid("getValue")){
        	 $.threetop("请填写入职日期!"); 
         } else if (plat == ""|| plat ==null){
             $.threetop("请选择平台!"); 
         } else if (!beliveTime){
             $.threetop("请填写开播日期!"); 
         } else if (channel == "" || channel==null){
             $.threetop("请选择频道!"); 
         }else {
             $("#actoresSaveForm #realname").removeAttr("disabled");
             $("#actoresSaveForm #aliasname").removeAttr("disabled");
             $("#actoresSaveForm #sex").removeAttr("disabled");
             $("#actoresSaveForm #phone").removeAttr("disabled"); 
             $("#actoresSaveForm #settl").removeAttr("disabled");
             $("#actoresSaveForm #genre").removeAttr("disabled");  
             $("#actoresSaveForm #minimumGuarantee").removeAttr("disabled");
             $("#actoresSaveForm #pushMoney").removeAttr("disabled");
		 	 $("#actoresSaveForm").ajaxSubmit(function(data) {
                    if(data.resultStatus == 200){
                   		 $(".zhezhao-basewindow").hide();
		                 $(".basewindow").hide();
		                 $('body').append($("#actoresSaveForm"));
		                 $("#actoresSaveForm").hide();
		                 window.location.href="${pageContext.request.contextPath}/actoresOnline/showOnline.do";
                    }else{ 
                    }
              })
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
		
		$("#plat").combogrid({
			panelWidth : 500,
			method : "POST",
			datatype : "json",
			url : "${pageContext.request.contextPath}/plats/readPagesSkip.do",
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
			},onSelect: function (rowIndex, rowData){
			    if(rowData != null && rowData.id != null){
			        $.ajax({
	    				type: "POST",
	    				cache: false,
	    				url: "${pageContext.request.contextPath}/plats/readChannel.do",
	    				data:"id="+rowData.id,
	    				success:function(data){
	    				    document.getElementById("channel").options.length=0;
	    				    if(data != null&& data.length > 0){
	    				        $.each(data, function(i, value) {
	    				            $("#channel").append("<option value='"+value.id+"'>"+value.channels+"</option>");
		    				    });
	    				    }else{
	    				        $("#channel").append("<option value='-1'>无</option>");
	    				    }
	    				}
	    			});
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
		<div class="right-twoTit">
			<span>首页</span> 
			<span>></span>
			<span>平台人员</span> 
			<span>></span>
			<span class="color99">面试管理</span>
	   	</div>
	    		
		<jsp:include page="interviewCommon.jsp"></jsp:include>
		<div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="aliasname" w_align="center" width="1%;">艺名</th>
					<th w_edit="photo" w_index="photo" w_align="center" width="1%;">艺术照</th>
					<th w_edit="videoPhoto" w_index="videoPhoto" w_align="center" width="1%;">视频照</th>
					<th w_index="sex" w_align="center" width="1%;">性别</th>
					<th w_index="phone" w_align="center" width="1%;">手机号</th>
					<th w_index="qq" w_align="center" width="1%;">QQ</th>
					<th w_index="genre" w_align="center" width="1%;">艺人类型</th>
					<th w_index="plat" w_align="center" width="1%;">试播平台</th>
					<th w_index="introducer" w_align="center" width="1%;">介绍人</th>
					<th w_index="chTransition" w_align="center" width="1%;">平台交接人</th>
					<th w_edit="linkUrl" w_index="linkUrl" w_align="center" width="1%;">视频链接</th>
					<th w_index="createDT" w_align="left" width="1%;">创建时间</th>
					<th w_index="remark" w_align="center">备注</th>
				</tr>
			</table>
			<div class="control">
				<input class="tabBtn rej" type="button" value="拒收">
				<input class="tabBtn empl" type="button" value="录用">
			</div>
		</div>
		 <div id="reje" style="margin-left: 20px; margin-right: 20px; display:none;">
		  	<table class="table">
		  		<input type="hidden" id="id">
		  		<tr>
		  			<td><textarea id="track"  onfocus="if(value=='限20字'){value=''}"  onblur="if (value ==''){value='限20字'}" rows="4" cols="40" value="限20字">
		  			</textarea></td>
		  		</tr>
		  	</table>
		  	<p style="text-align:center;">
		  		<span id="ok" class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
		  	</p>
		  	
		  </div>
		  
		  <div id="emplDiv" style="margin-left: 20px; margin-right: 20px; display:none;">
		  	<table class="table">
		  		<input type="hidden" id="id">
		  		<tr>
		  			<td style="text-align: right;">
		  				开播时间
		  			</td>
		  			<td>
		  				<input class="easyui-datebox" editable="false" name="launchTime" id="launchTime" style="width:200px;"> <span style="margin-left:10px;color:#e35d00">*</span>
		  			</td>
		  		</tr>
		  	</table>
		  	<p style="text-align:center;">
		  		<span id="sub" class="okok">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
		  	</p>
		  </div>
		  
		  
		 <div id="idlook" style="margin-left: 20px; margin-right: 20px; display:none;">
			<table class="table2">
				<tr>
					<td >
						<img  width='600'  id="yuanImage"/>
					</td>
				</tr>
			</table>
			<p style="text-align:center">
				<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div> 
		<form action="${pageContext.request.contextPath}/interview/saveActores.do" method="post" enctype="multipart/form-data" id="actoresSaveForm" style="display:none;margin-left:20px;margin-right:20px;">
				<table class="table">
					<tr>
						<td colspan="2"style="width:50%;padding:0;border:none;"></td>
						<td colspan="2"style="width:50%;padding:0;border:none;"></td>
					</tr>
					<tr><td style="text-align: right;">真实姓名：</td>
						<td><input type="text" name="realname" id="realname" style="width:200px;"></td><td style="text-align: right;">QQ：</td>
						<td><input  type="text" name="qq" id="qq"  style="width:200px;"></td> 
					</tr>
					<tr>
						<td style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
						<td><input type="text" name="phone" id="phone" style="width:200px;"  onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
						
						<td style="text-align: right;">艺名：<label style="color: red;">*</label></td>
						<td><input type="text" name="aliasname" id="aliasname" style="width:200px;"></td>
					</tr>
					<tr>
						<td style="text-align: right;">身份证号：</td>
						<td ><input type="text" name="idcard" id="idcard" style="width:200px;"></td> 
						<td style="text-align: right;">性别：<label style="color: red;">*</label></td>
						<td>
							<select id="sex" name="sex"  style="width:200px;">
								<option value="WOMAN">女</option>
								<option value="MAN">男</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">艺人类型：<label style="color: red;">*</label></td>
						<td>
							<select id="genre" name="genre" style="width:200px;">
								<option value="ONLINE">线上艺人</option> 
							</select>
						</td>
						<td style="text-align: right;">保底 ：<label style="color: red;">*</label></td>
						<td><input type="text" name="minimumGuarantee" id="minimumGuarantee" class="easyui-numberbox input" style="width:200px;"></td>
						
					</tr> 
					<tr>
						<td style="text-align: right;">提成比例：<label style="color: red;">*</label></td>
						<td><input id="pushMoney" name="pushMoney" class="easyui-numberbox input" value="0" style="width:150px;">%</td>
						<td style="text-align: right;">经济签约：<label style="color: red;">*</label></td>
						<td>
							<select id="signed" name="signed" style="width:200px;">
								<option value="NO">否</option>
								<option value="YES">是</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">身份证扫描件：</td>
						<td><input  type="file" name="idImage" id="idImage"  style="width:200px;"></td>  
						<td style="text-align: right;">入职时间 ：<label style="color: red;">*</label></td>
						<td><input type="text" class="easyui-datebox" editable="false" name="entryTime" id="entryTime" style="width:200px;"></td>
					</tr>   
					<tr>
						<td style="text-align: right;">工资卡帐号：</td>
						<td><input type="text" name="bankCard" id="bankCard" style="width:200px;"></td>
						<td style="text-align: right;">开户地址：</td>
						<td><input type="text" name="bankAddress" id="bankAddress" style="width:200px;"></td>
					</tr> 
					<tr>
						<td style="text-align: right;">分成账号(艺人)：</td>
						<td><input type="text" name="shareBankCard" id="shareBankCard" style="width:200px;"></td>
						<td style="text-align: right;">开户行：</td>
						<td><input type="text" name="shareBankAddress" id="shareBankAddress" style="width:200px;"></td>
					</tr>
				     
					<tr>
						<td style="text-align: right;">现居地址：</td>
						<td colspan="3"><input type="text" name="address" id="address" style="width:500px;"></td>
					</tr>
					<tr>
						<td style="text-align: right;">视频链接：</td>
						<td colspan="3"><input type="text" name="linkUrl" id="linkUrl" style="width:500px;"></td>
					</tr>
					<tr>
						<td colspan="4"><p>开播信息</p></td>
					</tr>
					<tr>
						<td style="text-align: right;">直播平台：<label style="color: red;">*</label></td>
						<td><input name="plat" id="plat" style="width:200px;"></td>
						<td style="text-align: right;">所属频道：<label style="color: red;">*</label></td>
						<td><select id="channel" name ="channel" style="width:200px;"></select></td>
					</tr>
					<tr>
						<td style="text-align: right;">开播日期 ：<label style="color: red;">*</label></td>
						<td colspan="3"><input type="text" class="easyui-datebox" editable="false" name="beliveTime" id="beliveTime" style="width:200px;"></td>
					</tr> 
					
					<tr>
						<td style="text-align:center;" colspan="4">
							<input type="hidden" name="id" id="id">
							<input type="hidden" name="interId" id="interId">
							<input type="hidden" name="empid" id="empid">
							<input type="hidden" name="type" id="type">
							<input type="hidden" name="genrer" id="genrer"> 
							<input type="hidden" name="loadTable" id="loadTable">
							<input type="hidden" name="platId" id="platId">
							<p>
								<span id="okok" class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<input class="cancel" type="reset" name="Input" value="关闭">
							</p>
						</td>
					</tr>
			</table>
		</form>
	</div>
  </body>
</html>
