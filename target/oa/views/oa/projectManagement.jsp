<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>项目管理</title>
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
        .table>tbody+tbody{
        	border:none;
        }
        .table tr td{
         	border:1px solid #ddd;
        }
        .bg83{
        	height:44px;
        	line-height:44px;
        	margin-bottom: 20px;
        }
        .section{
        	width:100%;
        	margin-bottom:12px; 
        }
        .section lable{
        	width:112px;
        	text-align:right;
        	display:inline-block; 
        }
       
        .control{
        	margin-left:-174px; 
        }
    </style>
    <script>
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
            
            //加载数据
            $(".right-onehalf span").click(function () {
                var $self = $(this);
                if (!$self.hasClass("spanchange")) {
                    $self.siblings("span").removeClass("spanchange");
                    $self.addClass("spanchange");
                    $("#addProject select option").remove();
                }
            });
            var grid = $.fn.bsgrid.init("searchTable", {
                url: "${pageContext.request.contextPath}/projectManagement/readPages.do?branchs=${branchs}",
                pageSizeSelect: true,
                pageSize:pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true
            });
           
            //修改
            $(".control .mid").click(function () {
                var $chuangkou = $("#updateProject");
                if ($(".selected").length > 0) {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    var editurl = "${pageContext.request.contextPath}/projectManagement/edit.do?t=" + new Date().getTime();
                    $.basewindow("修改项目管理", $chuangkou, 700,300);
                    $(".basewindow").addClass("custom-scroll");
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: editurl,
                        data: "id=" + id,
                        show: "slide",
                        success: function (data) {
                            $chuangkou.find("#id").val(data.id);
                            $chuangkou.find("#empids").combogrid("setValue", data.projectManager);
                            $chuangkou.find("#platName").combobox("setValues", data.platform);
                            $chuangkou.find("#projectName").val(data.projectName);
                            $chuangkou.find("#remarks").val(data.remarks);
                        },
                        error: function (data) {}
                    });
                }else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            })
            //删除
            $(".control .del").click(function () {
                if ($(".selected").length > 0) {
                    var $yes = function () {
                        var id = grid.getRowRecord(grid.getSelectedRow()).id;
                        var delUrl = "${pageContext.request.contextPath}/projectManagement/delete.do?t=" + new Date().getTime();
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: delUrl,
                            data: "id=" + id,//要发送的数据
                            success: function (data) {
                                if (data.resultStatus == 200) {
                                    $(".table .selected").remove();
                                } else {
                                    $.threetop("平台使用中不能删除!");
                                }
                            }, error: function (data) { }
                        });
                    }
                    var $no = function () { };
                    $.yesorno("是否删除？", 300, 160, $yes, $no);
                }
                else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            });
           
              //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#addProject");
                $.basewindow("添加项目管理", $chuangkou, 700, 350);
                $(".basewindow").addClass("custom-scroll");

            })
            
            //提交+判断(新增)
            $("#addProject .okok").click(function () {
                var platIds = $("#addProject #platId").combobox("getValues");
                var projectName = $("#addProject #projectName").val();
                var remarks = $("#addProject #remarks").val();
                var empidP = $("#addProject #empidP").combobox("getValue");
                if (platIds == "" || platIds == null) {
                    $.threetop("请填写平台名称!");
                    return false;
                }
                if (projectName == "" || projectName == null) {
                    $.threetop("请填写项目名称!");
                    return false;
                }
                if (empidP == "" || empidP == null) {
                    $.threetop("请填写项目管理人!");
                    return false;
                }
               	var project = {
                    platIds : platIds,
                    projectName : projectName,
                    remarks : remarks,
		            empidP:empidP
			    }
			    $.ajax({	
	    				type: "POST",
	    				cache: false,
	    				url: "${pageContext.request.contextPath}/projectManagement/save.do",
	    				data:JSON.stringify(project),
	    				contentType:"application/json; charset=utf-8",
	    				success:function(data){
	    					if(data.resultStatus == 200){
	    						$(".zhezhao-basewindow").hide();
	    						$(".basewindow").hide();
	    						$('body').append($("#addProject"));
	    						$("#addProject").hide(); 
	    						grid.refreshPage();
	    					}
	    				}
	    		});               
            });
            //提交+判断(修改)
            $("#updateProject .okok").click(function () {
                var id= $("#id").val();
                var platIds = $("#updateProject #platIdU").combobox("getValues");
                var projectName = $("#updateProject #projectName").val();
                var remarks = $("#updateProject #remarks").val();
                var empids = $("#updateProject #empids").combobox("getValue");
                if (platIds == "" || platIds == null) {
                    $.threetop("请填写平台名称!");
                    return false;
                }
                if (projectName == "" || projectName == null) {
                    $.threetop("请填写项目名称!");
                    return false;
                }
                if (empids == "" || empids == null) {
                    $.threetop("请填写项目管理人!");
                    return false;
                }
                var projectP = {
                	id:id,
                    platIds : platIds,
                    projectName : projectName,
                    remarks : remarks,
		            empids:empids
			    }
			    $.ajax({ 
			        type : "POST",
					url : "${pageContext.request.contextPath}/projectManagement/save.do",
					cache: false,
			        data: JSON.stringify(projectP),
			        contentType: 'application/json; charset=utf-8',
			        success:function(data){
			            if (data.resultStatus == 200) {
							$(".zhezhao-basewindow").hide();
							$(".basewindow").hide();
							$("#updateProject").hide();
							$('body').append($("#updateProject"));
							grid.refreshPage();
						}else if(data.resultStatus == 101){
						    $.threetop("项目修改失败");
						}
		       		 }
		   		 })
            });
        });
    </script>
    <script>
        $(function () {
            $("#empids").combogrid({
                panelWidth: 500,
                method: "POST",
                datatype: "json",
                url: "${pageContext.request.contextPath}/department/readPagesSkip.do",
                mode: "remote",
                fitColumns: true,
                rownumbers: true,
                pagination: true,
                idField: "id",
                textField: "name",
                pageSize: 30,
                pageList: [5, 10, 20, 30, 40, 50],
                columns: [[
                    { field: "name", title: "姓名", width: 120, sortable: true }
                ]],
                keyHandler: {
                    up: function () { },
                    down: function () { },
                    enter: function () { },
                    query: function (q) {
                        if (q != null && q != "") {
                            $("#empid").combogrid("grid").datagrid("reload", { "filter": q });
                            $("#empid").combogrid("setValue", q);
                        }
                    }
                }
            });
            $("#empidP").combogrid({
                panelWidth: 500,
                method: "POST",
                datatype: "json",
                url: "${pageContext.request.contextPath}/department/readPagesSkip.do",
                mode: "remote",
                fitColumns: true,
                rownumbers: true,
                pagination: true,
                idField: "id",
                textField: "name",
                pageSize: 30,
                pageList: [5, 10, 20, 30, 40, 50],
                columns: [[
                    { field: "name", title: "姓名", width: 120, sortable: true }
                ]],
                keyHandler: {
                    up: function () { },
                    down: function () { },
                    enter: function () { },
                    query: function (q) {
                        if (q != null && q != "") {
                            $("#empid").combogrid("grid").datagrid("reload", { "filter": q });
                            $("#empid").combogrid("setValue", q);
                        }
                    }
                }
            });
            $("#platId").combogrid({
                panelWidth: 500,
                method: "POST",
                datatype: "json",
                url: "${pageContext.request.contextPath}/plats/readPagesAllPlat.do",
                mode: "remote",
                fitColumns: true,
                rownumbers: true,
                pagination: true,
                idField: "id",
                textField: "platName",
                pageSize: 30,
                pageList: [5, 10, 20, 30, 40, 50],
                columns: [[
                    { field: "platName", title: "平台名称", width: 120, sortable: true }
                ]],
                keyHandler: {
                    up: function () { },
                    down: function () { },
                    enter: function () { },
                    query: function (q) {
                        if (q != null && q != "") {
                            $("#platId").combogrid("grid").datagrid("reload", { "filter": q });
                            $("#platId").combogrid("setValue", q);
                        }
                    }
                }
            });
            
            $("#platIdU").combogrid({
                panelWidth: 500,
                method: "POST",
                datatype: "json",
                url: "${pageContext.request.contextPath}/plats/readPagesAllPlat.do",
                mode: "remote",
                fitColumns: true,
                rownumbers: true,
                pagination: true,
                idField: "id",
                textField: "platName",
                pageSize: 30,
                pageList: [5, 10, 20, 30, 40, 50],
                columns: [[
                    { field: "platName", title: "平台名称", width: 120, sortable: true }
                ]],
                keyHandler: {
                    up: function () { },
                    down: function () { },
                    enter: function () { },
                    query: function (q) {
                        if (q != null && q != "") {
                            $("#platId").combogrid("grid").datagrid("reload", { "filter": q });
                            $("#platId").combogrid("setValue", q);
                        }
                    }
                }
            });
            
        })
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
			<span>直播系统</span> 
			<span>></span>
			<span class="color99">直播平台</span>
    	</div>
	    <div class="control">
	        <jsp:include page="attsCommon.jsp"></jsp:include>
	    </div>
	    <div class="thistable custom-scroll">
	        <table id="searchTable" class="table yitablelist">
	            <tr class="firsttr">
	                <th w_index="ID" w_align="center"  w_hidden="true" width="1%;">id</th>
	                <th w_num="line" w_align="center" width="1%;">序号</th>
	                <th w_index="projectName" w_align="center" width="1%;">项目名称 </th>
	                <th w_index="projectManager" w_align="center" width="1%;">项目管理人 </th>
	                <th w_index="projectPlat" w_align="center" width="1%;">项目中的平台 </th>
	                <th w_index="remarks" w_align="center">备注</th>
	            </tr>
	        </table>
	    </div>
	    
	    <form id="addProject"  enctype="multipart/form-data" style="display:none;margin-left:44px;margin-right:44px;">
	            <div class="clearFix">
	            	<div class="fl">
	            		<lable>项目名称：</lable>
	            		<input type="text" id="projectName" name="projectName" maxlength="400" class="input w200">
	            	</div>
	            	
	         	</div>
	            <div class="clearFix">
	            	<div class="fl">
	            		<lable>平台：</lable>
						<input id="platId" name="platId" style="width:200px;" data-options="multiple:true" >
					</div>
	            	<div class="fr" >
			            <lable>项目主管：</lable>
			            <input id="empidP" name="empidP" maxlength="400" class="input w200">
	        		</div>
	        	</div>  
	        	<div style="position:relative;margin-top:20px;padding:20px;border:1px solid #ddd">
	            <p style="position:absolute;top:-10px;left:10px;background-color:#fff;padding:0px 10px">备注</p>
	            <p style="margin-bottom:0;"><textarea id="remarks" name="remarks" rows=5 style="width:100%;" maxlength="400" class="input"></textarea></p>
	        </div>      
	        <p class="bCenter">
	            <input type="hidden" name="id" id="id" value="">
	            <span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input class="cancel" type="reset" name="Input" value="关闭">
	        </p>
	    </form>
	    
	     <form id="updateProject" enctype="multipart/form-data" style="display:none;margin-left:44px;margin-right:44px;">
	            <div class="clearFix">
	            	<div class="fl">
	            		<lable>项目名称：</lable>
	            		<input type="text" id="projectName" name="projectName" maxlength="400" class="input w200">
	            	</div>
	         	</div>
	            <div class="clearFix">
	            	<div class="fl">
	            		<lable>平台：</lable>
						<input id="platIdU" name="platIdU" style="width:200px;" data-options="multiple:true" >
					</div>
	            	<div class="fr" >
			            <lable>项目主管：</lable>
			            <input id="empids" name="empids" maxlength="400" class="input w200">
	        		</div>
	        	</div>
				<div style="position:relative;margin-top:20px;padding:20px;border:1px solid #ddd">
	            <p style="position:absolute;top:-10px;left:10px;background-color:#fff;padding:0px 10px">备注</p>
	            <p style="margin-bottom:0;"><textarea id="remarks" name="remarks" rows=5 style="width:100%;" maxlength="400" class="input"></textarea></p>
	        </div>       
	        <p class="bCenter">
	            <input type="hidden" name="id" id="id" value="">
	            <span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input class="cancel" type="reset" name="Input" value="关闭">
	        </p>
	    </form>
	    
    </div>
</body>
</html>