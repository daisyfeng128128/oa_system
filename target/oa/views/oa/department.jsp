<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>部门管理</title>
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
        .red{color:red; margin-right:8px; }
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
            var grid = $.fn.bsgrid.init("searchTable", {
                url: "${pageContext.request.contextPath}/department/readPages.do",
                pageSizeSelect: true,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                pageSize:pageSize,
                displayPagingToolbarOnlyMultiPages: true

            });

            //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#departmentForm");
                $.basewindow("新建部门", $chuangkou, 392, 260);
                $(".basewindow").addClass("custom-scroll");
                $("#parentid").combogrid({
                    panelWidth: 500,
                    method: "POST",
                    datatype: "json",
                    url: "${pageContext.request.contextPath}/department/readPagesParent.do",
                    mode: "remote",
                    fitColumns: true,
                    rownumbers: true,
                    pagination: true,
                    idField: "id",
                    textField: "name",
                    pageSize: 30,
                    pageList: [5, 10, 20, 30, 40, 50],
                    columns: [[
                        { field: "name", title: "部门负责人", width: 120, sortable: true }
                    ]],
                    keyHandler: {
                        up: function () { },
                        down: function () { },
                        enter: function () { },
                        query: function (q) {
                            if (q != null && q != "") {
                                $("#parentid").combogrid("grid").datagrid("reload", { "filter": q });
                                $("#parentid").combogrid("setValue", q);
                            }
                        }
                    }
                });

            });

            //修改
            $(".control .mid").click(function () {
                var $chuangkou = $("#departmentForm");
                if (grid.getSelectedRow().length > 0) {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    var editurl = "${pageContext.request.contextPath}/department/edit.do";
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: editurl,
                        data: "id=" + id,
                        show: "slide",
                        success: function (data) {
                            $("#id").val(data.id);
                            $("#name").val(data.name);
                            $("#empid").combogrid('setValue', data.manager);
                            var did = data.id;
                            $("#parentid").combogrid({
                                panelWidth: 500,
                                method: "POST",
                                datatype: "json",
                                url: "${pageContext.request.contextPath}/department/readPagesParent.do",
                                mode: "remote",
                                fitColumns: true,
                                rownumbers: true,
                                pagination: true,
                                idField: "id",
                                textField: "name",
                                pageSize: 30,
                                queryParams: { "did": did },
                                pageList: [5, 10, 20, 30, 40, 50],
                                columns: [[
                                    { field: "name", title: "上级部门", width: 120, sortable: true }
                                ]],
                                keyHandler: {
                                    up: function () { },
                                    down: function () { },
                                    enter: function () { },
                                    query: function (q) {
                                        if (q != null && q != "") {
                                            $("#parentid").combogrid("grid").datagrid("reload", { "filter": q });
                                            $("#parentid").combogrid("setValue", q);
                                        }
                                    }
                                }
                            });
                            $("#parentid").combogrid('setValue', data.parent);
                        },
                        error: function (data) {
                        }
                    });
                    $.basewindow("修改部门", $chuangkou, 260, 320);
                }
                else {
                    $.threesecond("请选择一行数据", 200, 100, 1000);
                }
            });

            //删除
            $(".control .del").click(function () {
                if (grid.getSelectedRow().length > 0) {
                    $.yesorno("是否删除？", 300, 160, function () {
                        var id = grid.getRowRecord(grid.getSelectedRow()).id;
                        var delUrl = "${pageContext.request.contextPath}/department/delete.do?t=" + new Date().getTime();
                        //后台删除
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: delUrl,
                            data: "id=" + id,//要发送的数据
                            success: function (data) {
                                if ("success" == data.resultMessage) {
                                    $(".table .selected").remove();
                                }
                                if ("errors" == data.resultMessage) {
                                    var str = data.data;
                                    $.threesecond(str, 200, 100, 1000);
                                }
                            },
                            error: function (data) {
                            }
                        });

                    }, function () {
                        $.threesecond("看来还是手下留情了!", 200, 100, 1000);
                    });
                } else {
                    $.threesecond("请选择一行数据", 200, 100, 1000);
                }
            });
            //提交+判断
            $("#departmentForm .okok").click(function () {
                var name = $("#name").val();
                if (name == "" || name == null) {
                    $.threetop("请填写部门名称!");
                }
                else {
                    $("#departmentForm").submit();
                }
            });
        });

    </script>

    <script>

        $(function () {
            $("#empid").combogrid({
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
                    { field: "name", title: "部门负责人", width: 120, sortable: true }
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
			<span>人事系统</span> 
			<span>></span>
			<span class="color99">部门管理</span>		
	   	</div>
	   	
	   	<div class="clearFix right-twoRight">
			<div class="fr">
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索">
			    	<button type="button" id="seach" class="tableok">搜索</button>				
				</div>
			</div>
		</div>
			
	    <div class="control">
	        <jsp:include page="attsCommon.jsp"></jsp:include>        
	    </div>
	    <div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
	        <table id="searchTable" class="table tablelist">
	            <tr class="firsttr">
	                <th w_index="id" w_hidden="true" width="1%">ID</th>
	                <th w_num="line" w_align="center" width="1%">序号</th>
	                <th w_index="name" w_align="center" width="1%">部门名称</th>
	                <th w_index="manager" w_align="center" width="1%">部门负责人</th>
	                 <th w_index="parent" w_align="center" width="1%">上级部门</th> 
	                <th w_index="createDT" w_align="center" width="1%">创建时间</th>
	                <th w_align="center">备注</th>
	            </tr>
	        </table>
	    </div>
	    <form action="${pageContext.request.contextPath}/department/save.do" method="post" id="departmentForm" style="display:none;margin-left:20px;margin-right:20px;">
	        <input type="hidden" name="id" id="id">
	        <p><span class="red">*</span>部门名称：<input type="text" name="name" id="name" style="width:200px;" maxlength="400" class="input"></p>
	        <p>部门负责人：<input type="text" name="empid" id="empid" value="" style="width:200px;" maxlength="400" class="input"></p>
	        <p><span class="red">*</span>上级部门：<input type="text" name="parentid" id="parentid" value="" style="width:200px;" maxlength="400" class="input"></p>
	        <p class="bCenter"><span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
	    </form>
	    <!-- <div id="search_dialog" title="高级搜索">
	        <table width="99.5%" border="0" cellpadding="4" cellspacing="1" class="table">
	            <tr class="table_title">
	                <td class="rt">部门名称：</td>
	                <td class="ji"><input type="text" id="sname" name="sname"></td>
	            </tr>
	            <tr class="tr ct">
	                <td colspan="6"><input type="button" value="搜索" onclick="sinorSearch();">&nbsp;&nbsp;&nbsp;&nbsp;<input
	                    type="button" id="sclose" value="关闭"></td>
	            </tr>
	        </table>
	    </div> -->
    </div>
</body>
</html>