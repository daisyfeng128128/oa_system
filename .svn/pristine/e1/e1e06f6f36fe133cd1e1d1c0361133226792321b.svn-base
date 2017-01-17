<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>分公司登陆</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<style type="text/css">
body {
	height: 100%;
	min-width: 200px;
	overflow-x: hidden;
	overflow-y: scroller;
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

.editTable p {
	margin: 0
}

.editTable tr td {
	border-bottom: 1px solid #ddd;
	font-size: 14px;
	width: 10%;
	height: 30px
}

.editTable tr td:first-child, tr th:first-child, .pdr {
	text-align: right;
	padding-right: 40px;
}

.salarylook {
	width: 100%;
	height: 40%
}

.yirentable tr {
	height: 30px;
	line-height: 30px;
	font-weight: normal;
	color: #999;
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
	height: 32px;
	line-height: 32px;
	display: inline-block;
	color: #fff;
	background-color: #e35d00;
	box-shadow: inset 0 0 6px #e99255;
	width: 82px;
	border-radius: 2px;
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

.thistable {
	overflow: auto;
	background-color: #fff;
}

#branchsForm .addSea {
	position: relative;
	width: 234px;
}

#branchsForm .addSea .next {
	border: 1px solid #ddd;
	text-align: center;
	height: 30px;
	line-height: 30px;
	color: #fff;
	background-color: #0074ac;
	width: auto;
	padding: 0px 14px;
	border-radius: 2px;
	-moz-border-radius: 2px;
	-o-border-radius: 2px;
	cursor: pointer;
	position: absolute;
	top: -1px;
	right: 0;
}

.bottom {
	text-align: center;
}

.table>tbody>tr>td {
	border: none;
	text-align: left;
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
		
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/branchLogin/readPages.do",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit: true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {} 
                }
            }
        });
        
        //新增
        $(".add").click(function () {
            var $chuangkou = $("#branchsForm");
            $(".yirentable tr").not(".firsttr").remove()
            $.basewindow("新增分公司登陆", $chuangkou, 318, 366);
        });
        $(".yirentable").delegate("tr:not('.firsttr')", "click", function () {
            var $self = $(this);
            if ($self.hasClass("selected")) {
            }
            else {
                $self.siblings("tr").removeClass("selected");
                $self.addClass("selected");
            }
        });
        
        //搜索
        $("#branchsForm .next").click(function () {
            var $self = $(this);
            var data = {
                name: $self.prev("input").val()
            }
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/searchEmp.do",
                cache: false,
                data: JSON.stringify(data),
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                $(".yirentable tr").not(".firsttr").remove();
                if(data.length>0){
                    $(data).each(function (index) {             
                        var $self = $(this)[0];
                        if (index == 0) {
                            $(".yirentable").append("<tr class='selected'>"
                            	+"<td hidden>" + $self.id + "</td>"
                            	+"<td>" + $self.num + "</td>"
                            	+"<td>" + $self.accounts + "</td>"
                            	+"</tr>")
                        } else {
                            $(".yirentable").append("<tr><td hidden>" + $self.id + "</td>"
                            	+"<td>" + $self.num + "</td>"
                            	+"<td>" + $self.accounts + "</td>"
                            	+"</tr>")
                        }
                    });
                }else{
                	$.threetop("未搜索到结果");
                }
            }).error(function (jqXHR, textStatus, errorThrown) { });
        });
        
        $("#branchsForm .okok").click(function () {
            var $self = $(this);
            var id = $(".yirentable .selected td:first").text();
            if(id != null && id != ""){
            	//验证是否已添加
            	$.ajax({
                    type: "GET",
                    show: "slide",
                    dataType: "json",
                    url: "${pageContext.request.contextPath}/branchLogin/editEmp.do",
                    data: "id=" + id,
                }).done(function (data) {
                	var $data = data.data;
                	if(data.resultStatus == 200){
                		$("#branchsForm").appendTo($("body")).hide();
                        $.basewindow("新增员工到分公司", "#branchsSaveForm", 780, 330);
                        
                        $("#branchsSaveForm #tarBranchs_id").combogrid(
                				{
                					panelWidth : 500,
                					method : "POST",
                					datatype : "json",
                					queryParams : { empid : $data.id },
                					url : "${pageContext.request.contextPath}/branchLogin/readPagesAllBranchs.do",
                					mode : "remote",
                					fitColumns : true,
                					rownumbers : true,
                					pagination : true,
                					idField : "id",
                					textField : "name",
                					pageSize : 50,
                					pageList : [ 5, 10, 20, 30, 40, 50 ],
                					columns : [ [ {
                						field : "ipgp",
                						title : "分公司",
                						width : 120,
                						sortable : true
                						
                					} ] ],
                					keyHandler : {
                						up : function() { },
                						down : function() { },
                						enter : function() { },
                						query : function(q) {
                							if (q != null && q != "") {
                								$("#branchsSaveForm #tarBranchs_id").combogrid("grid").datagrid(
                										"reload", {
                											"filter" : q
                										});
                								$("#branchsSaveForm #tarBranchs_id").combogrid("setValue", q);
                							}
                						}
                					}
                				});
                        $("#branchsSaveForm #empid").val($data.id);
						$("#branchsSaveForm #name").text($data.name);
						$("#branchsSaveForm #number").text($data.num);
						$("#branchsSaveForm #phone").text($data.phone);
						$("#branchsSaveForm #ownbranchs").text($data.branchs);
						$("#branchsSaveForm #depart").text($data.depName);
						$("#branchsSaveForm #position").text($data.posName);
                	}
                });
            }else{
            	$.threetop("请选择艺人!");
            }
        });
        
        //提交+判断
        $("#branchsSaveForm .okok").click(function () {
            var endTime = $("#branchsSaveForm #endTime").datebox("getValue");
            var tarBranchs_id = $("#branchsSaveForm #tarBranchs_id").combogrid("getValue");
            var email = $("#branchsSaveForm #email").val();
            var name = $("#branchsSaveForm #name").text();
            var number = $("#branchsSaveForm #number").text();
            var phone = $("#branchsSaveForm #phone").text();
            var ownbranchs = $("#branchsSaveForm #ownbranchs").text();
            var depart = $("#branchsSaveForm #depart").text();
            var position = $("#branchsSaveForm #position").text();
            var empid= $("#branchsSaveForm #empid").val();
            if (endTime == null || endTime == "") {
                $.threetop("请填写结束时间!");
                return false;
            }
            if (tarBranchs_id == null || tarBranchs_id == "") {
                $.threetop("请选择目标分公司!");
                return false;
            }
            if(email == null || email == ""){
                $.threetop("请填写邮箱地址")
                return false;
            }
            var json = {
                    endTime : endTime,
		            name: name,
		            tarBranchs_id : tarBranchs_id,
		            email : email,
		            number : number,
		            phone : phone ,
		            depart : depart,
		            positions : position,
		            empid : empid,
		            ownBranchs:ownbranchs
		    }
		    var editurl = "${pageContext.request.contextPath}/branchLogin/save.do";
		    $.ajax({
		        type : "post",
				url : editurl,
				cache: false,
		        data: JSON.stringify(json),
		        contentType: 'application/json; charset=utf-8',
		        success:function(data){
		            if (data.resultStatus == 200) {
						$(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$("#branchsSaveForm").hide();
						$('body').append($("#branchsSaveForm"));
						grid.refreshPage();
					}else if(data.resultStatus == 101){
					    $.threetop("同一分公司不可重复添加员工");
					}
		        }
		    })
            
           
        });
        $(".mid").click(function(){
    			if (grid.getSelectedRow().length > 0) {
    			    var $chuangkou = $("#branchsUpdateForm");
    				var id = grid.getRowRecord(grid.getSelectedRow()).id;
    				var editurl = "${pageContext.request.contextPath}/branchLogin/edit.do?t="+ new Date().toTimeString();
    				$.ajax({
    					type : "get",
    					dataType : "json",
    					url : editurl,
    					data : "id=" + id,
    					show : "slide",
    					success : function(data) {
    					    var $data = data.data;
    					    if($data.role_id1 != null){
    					    	 var roleid = $data.role_id1;
    					    }else if($data.role_id != null){
    					    	 var roleid =$data.role_id;
    					    }
    					    var branchs = $data.branchs;
    					    var tarBranchs_id = $data.tarBranchs_id;
    					    var ownBranchs_id = $data.ownBranchs_id;
    					    if( $data!= null && data.resultStatus == 200){
    					    	$("#branchsUpdateForm #id").val($data.id);
    					        $("#branchsUpdateForm #name").text($data.name);
    							$("#branchsUpdateForm #number").text($data.number);
    							$("#branchsUpdateForm #phone").text($data.phone);
    							$("#branchsUpdateForm #ownbranchs").text($data.ownBranchs);
    							$("#branchsUpdateForm #depart").text($data.depart);
    							$("#branchsUpdateForm #position").text($data.positions);
    							$("#branchsUpdateForm #email").val($data.email);
    							$("#branchsUpdateForm #tarBranchs").text($data.tarBranchs);
    							$("#branchsUpdateForm #endTime").datebox("setValue",$data.endTime);
    							 if($data.role_id1 != null){
    					    	     $("#branchsUpdateForm #role_id").combogrid("setValues", roleid);
	    					    }else if($data.role_id != null){
	    					    	 $("#branchsUpdateForm #role_id").combogrid('grid').datagrid('selectRecord',roleid);
	    					    }
    							if(branchs == ownBranchs_id){
					            	$("#branchsUpdateForm #role_id").combogrid("disable");
    							}
    					    }
    					},
    					error : function(data) {}
    				});
    				$.basewindow("编辑分公司账号", $chuangkou, 800, 360);
    				$chuangkou.css({ "display" : "block" });
    			} else {
    				$.threesecond("请先选择", 200, 100, 1000);
    			}
        })
        
         //提交+判断
        $("#branchsUpdateForm .okok").click(function () {
            var endTime = $("#branchsUpdateForm #endTime").datebox("getValue");
            var role_id = $("#branchsUpdateForm .role_id").combobox("getValues").join();
            var email = $("#branchsUpdateForm #email").val();
            var id= $("#branchsUpdateForm #id").val();
            if (endTime == null || endTime == "") {
                $.threetop("请填写结束时间!");
                return false;
            }
            if (role_id == null || role_id == "") {
                $.threetop("请选择目标分公司!");
                return false;
            }
            if(email == null || email == ""){
                $.threetop("请填写邮箱地址")
                return false;
            }
            var json = {
                    id : id,
                    endTime : endTime,
                    role_id : role_id,
		            email : email
		    }
		    var editurl = "${pageContext.request.contextPath}/branchLogin/save.do";
		    $.ajax({
		        type : "post",
				url : editurl,
				cache: false,
		        data: JSON.stringify(json),
		        contentType: 'application/json; charset=utf-8',
		        success:function(data){
		            if (data.resultStatus == 200) {
						$(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$("#branchsUpdateForm").hide();
						$('body').append($("#branchsUpdateForm"));
						grid.refreshPage();
					}
		        }
		    })
        });
        
    	//删除
		$(".del").click(function() {
				if (grid.getSelectedRow().length > 0) {
						$.yesorno("是否删除？",300,160,function() {
							var id = grid.getRowRecord(grid.getSelectedRow()).id;
							var delUrl = "${pageContext.request.contextPath}/branchLogin/delete.do?t="+ new Date().getTime();
							//后台删除
							$.ajax({
								type : "get",
								dataType : "json",
								url : delUrl,
								data : "id="+ id,//要发送的数据
								success : function(data) {
									if ("success" == data.resultMessage) {
									    $.threesecond("删除成功", 200, 100, 1000);
									    grid.refreshPage();
									}
								},
								error : function(data) {
								    
								}
							});
		
						});} else {
							$.threesecond("请先选择", 200, 100, 1000);
					}
				});
        $("#branchsUpdateForm #role_id").combogrid(
				{
					panelWidth : 500,
					method : "POST",
					datatype : "json",
					url : "${pageContext.request.contextPath}/branchLogin/readPagesAllRole.do",
					mode : "remote",
					fitColumns : true,
					rownumbers : true,
					pagination : true,
					idField : "id",
					textField : "name",
					pageSize : 50,
					pageList : [ 5, 10, 20, 30, 40, 50 ],
					columns : [ [ {
						field : "name",
						title : "角色名称",
						width : 120,
						sortable : true
					} ] ],
					keyHandler : {
						up : function() { },
						down : function() { },
						enter : function() { },
						query : function(q) {
							if (q != null && q != "") {
								$("#branchsUpdateForm #role_id").combogrid("grid").datagrid(
										"reload", {
											"filter" : q
										});
								$("#branchsUpdateForm #role_id").combogrid("setValue", q);
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
			<span>首页</span> <span>></span> <span>系统设置</span> <span>></span> <span class="color99">分公司登陆</span>
		</div>
		<jsp:include page="branchLoginCommon.jsp"></jsp:include>
		<div class="thistable custom-scroll">
			<table id="searchTable" class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
					<th w_num="line" w_align="center" width="1%;">序号</th>
					<th w_index="number" w_align="center" width="1%;">员工号</th>
					<th w_index="name" w_align="center" width="1%;">姓名</th>
					<th w_index="phone" w_align="center" width="1%;">联系电话</th>
					<th w_index="email" w_align="center" width="1%;">邮件地址</th>
					<th w_index="ownBranchs" w_align="center" width="1%;">所属子公司</th>
					<th w_index="tarBranchs" w_align="center" width="1%;">目标子公司</th>
					<th w_index="depart" w_align="center" width="1%;">部门</th>
					<th w_index="positions" w_align="center" width="1%;">职位</th>
					<th w_index="role" w_align="center" width="1%;">角色权限</th>
					<th w_index="overdues" w_align="center" width="1%;">账号状态</th>
					<th w_index="createDT" w_align="center" width="1%;">创建时间</th>
					<th w_index="endTime" w_align="center" width="1%;">结束时间</th>
					<th w_index="remarks" w_align="center">备注</th>
				</tr>
			</table>
			<div>
				</br> </br>
				<p style="text-align:center;position: fixed; left: 50%; margin-left:-174px; bottom: 1%;"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
			</div>
		</div>
		<div id="branchsForm" style="display:none;margin-left:40px;margin-right:40px;">
			<p class="addSea">
				<input type="text" name="realname" id="realname" style="width:234px; height:28px; " maxlength="400" class="input"> <span class="next">搜索</span>
			</p>
			<div class="custom-scroll" style="position:relative;margin-top:20px;height:150px;overflow:auto; ">
				<table style="width:100%" class="yirentable">
					<tr class="firsttr">
						<th hidden>id</th>
						<th width="50%">员工号</th>
						<th width="50%">真实姓名</th>
					</tr>
				</table>
			</div>
			<p class="bottom">
				<input type="hidden" name="managerId" id="managerId" value=""> <span class="okok">添加</span>&nbsp;&nbsp;&nbsp;&nbsp; <input class="cancel"
					type="reset" name="Input" value="关闭">
			</p>
		</div>
		<div id="branchsSaveForm" style="display:none;margin-left:20px;margin-right:20px;">
			<table class="table">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">员工号：</td>
					<td id="number" name="number"></td>
					<td style="text-align: right;">姓名：</td>
					<td id="name" name="name"></td>
				</tr>
				<tr>
					<td style="text-align: right;">联系电话：</td>
					<td id="phone" name="phone"></td>
					<td style="text-align: right;">所属分公司：</td>
					<td id="ownbranchs" name="ownbranchs"></td>
				</tr>
				<tr>
					<td style="text-align: right;">部门：</td>
					<td id="depart" name="depart"></td>
					<td style="text-align: right;">职位：</td>
					<td id="position" name="position"></td>
				</tr>
				<tr>
					<td style="text-align: right;">角色权限：</td>
					<td>普通员工</td>
					<td style="text-align: right;">目标分公司：</td>
					<td><input name="tarBranchs_id" id="tarBranchs_id" style="width:200px;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">邮箱地址：<label style="color: red;">*</label></td>
					<td><input id="email" name="email" style="width:150px;" value="0"></td>
					<td style="text-align: right;">结束时间：<label style="color: red;">*</label></td>
					<td><input type="text" class="easyui-datebox" editable="false" name="endTime" id="endTime" style="width:200px;"></td>
				</tr>
				<tr>
					<td style="text-align:center;" colspan="4"><input type="hidden" name="id" id="id"> <input type="hidden" name="empid" id="empid">
						<p>
							<span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp; <input class="cancel" type="reset" name="Input" value="关闭">
						</p></td>
				</tr>
			</table>
		</div>
		<div id="branchsUpdateForm" style="display:none;margin-left:20px;margin-right:20px;">
			<table class="table">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
					<td style="text-align: right;">员工号：</td>
					<td id="number" name="number"></td>
					<td style="text-align: right;">姓名：</td>
					<td id="name" name="name"></td>
				</tr>
				<tr>
					<td style="text-align: right;">联系电话：</td>
					<td id="phone" name="phone"></td>
					<td style="text-align: right;">所属分公司：</td>
					<td id="ownbranchs" name="ownbranchs"></td>
				</tr>
				<tr>
					<td style="text-align: right;">部门：</td>
					<td id="depart" name="depart"></td>
					<td style="text-align: right;">职位：</td>
					<td id="position" name="position"></td>
				</tr>
				<tr>
					<td style="text-align: right;">角色权限：</td>
					<td><input name="roles_id" id="role_id" class="role_id" style="width:200px;" data-options="multiple:true" ></td>
					<td style="text-align: right;">目标分公司：</td>
					<td id="tarBranchs" name="tarBranchs"></td>
				</tr>
				<tr>
					<td style="text-align: right;">邮箱地址：<label style="color: red;">*</label></td>
					<td><input id="email" name="email" style="width:150px;" value="0"></td>
					<td style="text-align: right;">结束时间：<label style="color: red;">*</label></td>
					<td><input type="text" class="easyui-datebox" editable="false" name="endTime" id="endTime" style="width:200px;"></td>
				</tr>
				<tr>
					<td style="text-align:center;" colspan="4"><input type="hidden" name="id" id="id"> <input type="hidden" name="empid" id="empid">
						<p>
							<span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp; <input class="cancel" type="reset" name="Input" value="关闭">
						</p></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
