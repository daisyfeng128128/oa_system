<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>艺人管理</title>
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

.koko {
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
.control{
	margin-left:-174px;
}
.control .regspan {
	float: right;
	font-family: 'Microsoft YaHei';
	margin-right: 130px;
	font-weight: normal;
	margin-top: 3px;
	display: none;
}

.control .rspan {
	cursor: pointer;
	float: right;
	height: 27px;
	margin-right: -190px;
	margin-top: 8px;
	width: 100px;
	border: 1px solid #ddd;
	display: none;
}

.control .rspan>span {
	padding: inherit;
	display: block;
	color: black;
	font-weight: normal;
	background-color: transparent;
	cursor: pointer;
	margin-top: -2px;
	width: 100px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	border: 0px solid #ddd;
}

.control .rspan ul {
	margin-left: -1px;
	display: none;
	position: fixed;
}

.control .rspan li {
	background-color: #fff;
	cursor: pointer;
	margin-top: -1px;
	width: 100px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	border: 1px solid #ddd;
	font-weight: normal;
}
.right-onehalf{
	width:auto;
}
</style>
<script>
        $(function () {
            //加载数据
           $(".right-onehalf span").click(function () {
               var $self = $(this);
               if (!$self.hasClass("spanchange")) {
               		$(".right-onehalf span").removeClass("spanchange");
                    $self.addClass("spanchange");
                    searchParames = {"platId": $self.attr("value"),"branchs":"${branchs}"};
                    grid.search(searchParames);
                    $("#actoresForm select option").remove();
                }
            })
            var grid = $.fn.bsgrid.init("searchTable", {
                url: "${pageContext.request.contextPath}/actores/readPagesPlatformActores.do",
                pageSizeSelect: true,
        		pageSize: pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true
            });
            searchParames = { "platId": $(".right-onehalf .spanchange").attr("value"),"branchs":"${branchs}" };
            grid.search(searchParames);
            //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#actoresForm");
                $(".yirentable tr").not(".firsttr").remove()
                if ($("#actoresForm select option").length == 0) {
                    var data = {
                        id: $(".right-onehalf .spanchange").attr("value")
                    }
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/plats/getChannels.do",
                        cache: false, 
                        async: false,
                        data: JSON.stringify(data),
                        contentType: 'application/json; charset=utf-8',
                    }).done(function (data) {
                        if (data.resultStatus == 100) {
                            $("#actoresForm select").parent("p").hide();
                            $("#actoresForm select").parent("p").prev("p").hide();
                        } else {
                            $("#actoresForm select").parent("p").show();
                            $("#actoresForm select").parent("p").prev("p").show();
                            $(data.data).each(function () {
                                var $self = $(this);
                                $("#actoresForm select").append("<option value=" + $self[0].id + ">" + $self[0].channels + "</option>")
                            });
                        }
                    }).error(function (jqXHR, textStatus, errorThrown) {});
                }
                if($("#actoresForm select").parent("p").css("display") == "block"){
                	$.basewindow("新增艺人", $chuangkou, 500, 458);
                	$(".basewindow").addClass("custom-scroll");
                }else{
                	$.basewindow("新增艺人", $chuangkou, 500, 370);
                	$(".basewindow").addClass("custom-scroll");
                }
            });
            
            $("#actoresForm .okok").click(function () {
                var $self = $(this);
                $("#realname").removeAttr("disabled");
                $("#aliasname").removeAttr("disabled");
                $("#phone").removeAttr("disabled");
                $("#qq").removeAttr("disabled");
                $("#sex").removeAttr("disabled");
                $("#genre").removeAttr("disabled");
                $("#beLiveTime").datebox({disabled: false});
                $("#costArtists").removeAttr("disabled");
				
                var id = $(".yirentable .selected td:first").text();
                var loadTable = $(".yirentable .selected td").eq(1).text()
                if(id != null && id != ""){
                	var platId = $(".right-onehalf .spanchange").attr("value");
                	var channelsId = $("#actoresForm select option:checked").val();
                
                	//验证是否已添加
                	$.ajax({
	                    type: "GET",
	                    show: "slide",
	                    dataType: "json",
	                    url: "${pageContext.request.contextPath}/actores/editEmp.do",
	                    data: "id=" + id + "&loadTable=" + loadTable + "&platId=" + platId + "&channelsId=" + channelsId,
	                }).done(function (data) {
	                	var $data = data.data;
	                	if(data.resultStatus == 200){
	                		$("#actoresForm").appendTo($("body")).hide();
                            $.basewindow("新增艺人到平台", "#actoresSaveForm", 780, 356);
							$(".basewindow").addClass("custom-scroll");
                            $("#actoresSaveForm #empid").val($data.id);
                            $("#actoresSaveForm #loadTable").val(loadTable);
                            $("#actoresSaveForm #platId").val(platId);
                            $("#actoresSaveForm #channelsId").val(channelsId);
                            
                            if($data.genre == "ONLINE"){
                            	$("#actoresSaveForm #costArtists").numberbox("setValue",$data.minimumGuarantee);
                            } else{
                            	if($data.genrer=="试用"){
                           			$("#actoresSaveForm #costArtists").numberbox("setValue",$data.probationSalary);
                            	}
                            	if($data.genrer=="正式"){
                           			$("#actoresSaveForm #costArtists").numberbox("setValue",$data.basicSalary);
                            	}
                            }
                            	
							$("#actoresSaveForm #realname").val($data.realname);
							$("#actoresSaveForm #beLiveTime").datebox("setValue",new Date().format("yyyy-MM-dd").toString());
	                        $("#actoresSaveForm #pushMoney").numberbox("setValue", $data.pushMoney);
                            $("#actoresSaveForm #aliasname").val($data.aliasname);
                            $("#actoresSaveForm #phone").val($data.phone);
                            $("#actoresSaveForm #qq").val($data.qq);
                            $("#actoresSaveForm #sex").val($data.sex);
                            $("#actoresSaveForm #genre").val($data.genre);
                            $("#actoresSaveForm #formGenre").val($data.formGenre);
                            $("#actoresSaveForm #genrer").val($data.genrer);
                            $("#actoresSaveForm #probationSalary").val($data.probationSalary);
                            $("#actoresSaveForm #basicSalary").val($data.basicSalary);
                            $("#actoresSaveForm #min").val($data.minimumGuarantee);
                            if($data.formGenre!="ZHIBOJIAN")
                            	$("#actoresSaveForm .num").hide();
                            else
                            	$("#actoresSaveForm .num").show();
                            $("#actoresSaveForm #pushMoney").attr({ "disabled": "disabled" });
							$("#actoresSaveForm #realname").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #aliasname").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #phone").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #qq").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #sex").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #genre").attr({ "disabled": "disabled" });
                             $("#actoresSaveForm #costArtists").attr({ "disabled": "disabled" });
	                	}else if(data.resultStatus == 101){
	                		$.threetop("当前用户已存在!");
	                	}
	                });
                }else{
                	$.threetop("请选择艺人!");
                }
            });
			
			            
             //修改
            $(".control .mid").click(function () {
                var $chuangkou = $("#actoresSaveForm");
                var loadTable = 0;
                $("#actoresSaveForm #loadTable").val(loadTable);
                if (grid.getSelectedRow().length > 0) {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/actores/editActores.do",
                        data: "id=" + id,
                        show: "slide",
                        success: function (data) {
                            var $data = data.data;
                            $.basewindow("修改平台艺人", $chuangkou, 780, 356);
                            $(".basewindow").addClass("custom-scroll");
                            $("#actoresSaveForm #id").val($data.id);
                            $("#actoresSaveForm #channelsId").val($data.channelId);
                            $("#actoresSaveForm #platId").val($(".right-onehalf .spanchange").attr("value"));
                             $("#actoresSaveForm #formGenre").val($data.formGenre);
                            if($data.formGenre!="ZHIBOJIAN")
                            	$("#actoresSaveForm .num").hide();
                            else
                            	$("#actoresSaveForm .num").show();
                            	
                            $("#actoresSaveForm #number").val($data.number);
                            $("#actoresSaveForm #realname").val($data.realname);
                           	 if($data.genre == "ONLINE"){
                            	$("#actoresSaveForm #costArtists").numberbox("setValue",$data.minimumGuarantee);
                            } else{
                            	if($data.genrer=="试用"){
                           			$("#actoresSaveForm #costArtists").numberbox("setValue",$data.probationSalary);
                            	}
                            	if($data.genrer=="正式"){
                           			$("#actoresSaveForm #costArtists").numberbox("setValue",$data.basicSalary);
                            	}
                            }	
                            $("#actoresSaveForm #aliasname").val($data.aliasname);
                            $("#actoresSaveForm #phone").val($data.phone);
                            $("#actoresSaveForm #qq").val($data.qq);
                            $("#actoresSaveForm #sex").val($data.sex);
                            $("#actoresSaveForm #genre").val($data.genre);
                            $("#actoresSaveForm #mainPlatform").val($data.mainPlatform);
                            if($data.beLiveTime != null && $data.beLiveTime != "")
                            	$("#actoresSaveForm #beLiveTime").datebox("setValue", $data.beLiveTime);
                            $("#actoresSaveForm #pushMoney").numberbox("setValue", $data.pushMoney);
                            $("#actoresSaveForm #genrer").val($data.genrer);
                            $("#actoresSaveForm #probationSalary").val($data.probationSalary);
                            $("#actoresSaveForm #basicSalary").val($data.basicSalary);
                             $("#actoresSaveForm #min").val($data.minimumGuarantee);
                            
                            $("#actoresSaveForm #pushMoney").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #realname").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #aliasname").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #phone").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #qq").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #sex").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #genre").attr({ "disabled": "disabled" });
                            $("#actoresSaveForm #costArtists").attr({"disabled" : "disabled" });
                        }, error: function (data) { }
                    });
                } else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            }); 
            
            //删除
            $(".control .del").click(function () {
                if (grid.getSelectedRow().length > 0) {
                    $.yesorno("是否删除？", 300, 160, function () {
                        var platId = $(".right-onehalf .selected").attr("value");
                        var id = grid.getRowRecord(grid.getSelectedRow()).id;
                        var delUrl = "${pageContext.request.contextPath}/actores/delete.do?t=" + new Date().getTime();
                        //后台删除
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: delUrl,
                            data: "id=" + id + "&platId=" + platId,//要发送的数据
                            success: function (data) {
                                if ("success" == data.resultMessage) {
                                    $(".table .selected").remove();
                                }
                            }, error: function (data) { }
                        });
                    }, function () {
                        $.threesecond("看来还是手下留情了!", 200, 100, 1000);
                    });
                } else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
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
            $("#actoresForm .next").click(function () {
                var $self = $(this);
                var data = {
                    name: $self.prev("input").val(),
                    loadTable: "true",
                    seachEmp: "false"
                }
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/user/search.do",
                    cache: false,
                    data: JSON.stringify(data),
                    contentType: 'application/json; charset=utf-8',
                }).done(function (data) {
                    $(".yirentable tr").not(".firsttr").remove();
                    if(data.length>0){
	                    $(data).each(function (index) {             
	                        var $self = $(this)[0];
	                        if (index == 0) {
	                            $(".yirentable").append("<tr class='selected'>"
	                            	+"<td hidden>" + $self.id + "</td>"
	                            	+"<td hidden>" + $self.loadTable + "</td>"
	                            	+"<td>" + $self.num + "</td>"
	                            	+"<td>" + $self.name + "</td>"
	                            	+"<td>" + $self.aliasname + "</td>"
	                            	+"</tr>")
	                        }
	                        else {
	                            $(".yirentable").append("<tr><td hidden>" + $self.id + "</td>"
	                            	+"<td hidden>" + $self.loadTable + "</td>"
	                            	+"<td>" + $self.num + "</td>"
	                            	+"<td>" + $self.name + "</td>"
	                            	+"<td>" + $self.aliasname + "</td>"
	                            	+"</tr>")
	                        }
	                    });
                    }else{
                    	$.threetop("未搜索到结果");
                    }
                }).error(function (jqXHR, textStatus, errorThrown) { });
            });
			           
            //提交+判断
            $("#actoresSaveForm .okok").click(function () {
                var beLiveTime = $("#beLiveTime").datebox("getValue");
                var pushMoney = $("#pushMoney").val();
                var costArtists = $("#costArtists").val();
                var mainPlatform = $("#mainPlatform").val();
                var number = $("#number").val();
                var formGenre=$("#formGenre").val();
                if (beLiveTime == null || beLiveTime == "") {
                    $.threetop("请填开播时间!");
                    return false;
                }
                if (pushMoney == null || pushMoney == "") {
                    $.threetop("请填提成比例!");
                    return false;
                }
                if (costArtists == null || costArtists == "") {
                    $.threetop("请填艺人成本!");
                    return false;
                }
                if(mainPlatform==null || mainPlatform==""){
                 $.threetop("请选择主平台!");
                    return false;
                }
                if(formGenre=="ZHIBOJIAN"){
                	if(number==null || number==""){
                	$.threetop("请填房间号!");
                    return false;
                	}
                }
                $("#sex").removeAttr("disabled");
                $("#settl").removeAttr("disabled");
                $("#genre").removeAttr("disabled");
                $("#signed").removeAttr("disabled");     
                
                $("#actoresSaveForm #pushMoney").removeAttr("disabled");
                $("#actoresSaveForm #realname").removeAttr("disabled");
                $("#actoresSaveForm #aliasname").removeAttr("disabled");
                $("#actoresSaveForm #phone").removeAttr("disabled");
                $("#actoresSaveForm #qq").removeAttr("disabled");
                $("#actoresSaveForm #sex").removeAttr("disabled");
                $("#actoresSaveForm #genre").removeAttr("disabled");
                $("#actoresSaveForm #mainPlatform").removeAttr("disabled");
                $("#actoresSaveForm #number").removeAttr("disabled");
                $("#actoresSaveForm #costArtists").removeAttr("disabled");
                $("#actoresSaveForm").ajaxSubmit(function(data) {
                    if(data !=null && data.resultStatus==200){
                        $('body').append($("#actoresSaveForm"));
                        $("#actoresSaveForm").hide();
                   		$(".zhezhao-basewindow").hide();
		                $(".basewindow").hide();
                   		grid.refreshPage();
                    }else{
                        $.threetop("保存失败");
                    }
              })
            });
            
            //快速搜索
    		$("#fast").change(function() {
    		    var fastArg = $("#fast").val();
    			var searchParames={
    				"branchs":"${branchs}",
    			    "fastArg": fastArg,
    			    "platId": $(".right-onehalf .spanchange").attr("value") 
    			}
    			grid.search(searchParames);
    		})
    		
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
            
            //回车操作
            $(window).keydown(function (e) {
                if (e.which == 13 && $("#actoresForm").css("display") == "block") {
                    $("#actoresForm .next").click();
                }
            });
            $.ajax({
    			type: "POST",
    			url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
    			data: "",
    			cache: false
    		}).done(function (data) {
    			if(data.data.length > 0){
    				$(data.data).each(function(index,value){
	    				var $self=$(this);
	    				$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/actores/show.do?platId=${platId}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
	    			});
	    			$(".right-twoRight .regspan").show();
    				$(".right-twoRight .rspan").show();
    			}
    		});
    		$(".right-twoRight .rspan>span").click(function(){
				if (!$(".right-twoRight .rspan ul").is(":animated")) {
					$(".right-twoRight .rspan ul").fadeToggle();
		       	}
		    });
        });
        function gradeChange(){
        	var mainPlatform = $("#mainPlatform").val(); 
        	var genrer = $("#genrer").val(); 
        	var probationSalary = $("#probationSalary").val(); 
        	var basicSalary = $("#basicSalary").val(); 
        	var genre = $("#genre").val(); 
        	var min = $("#min").val(); 
        	if(mainPlatform=="1"){
        		if(genre == "ONLINE"){
        			 $("#actoresSaveForm #costArtists").numberbox("setValue",min);
        		}else{
        			if(genrer=="试用")
                    	$("#actoresSaveForm #costArtists").numberbox("setValue",probationSalary);
                	if(genrer=="正式")
                     	$("#actoresSaveForm #costArtists").numberbox("setValue",basicSalary);
        		}
        	}else{
                $("#actoresSaveForm #costArtists").numberbox("setValue","0");
        	}
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
			<span>直播系统</span> 
			<span>></span>
			<span class="color99">艺人管理</span>
	   	</div>
	    
	    <div class="clearFix">
	    	<div class="right-onehalf fl">
		        <jsp:include page="platCommon.jsp"></jsp:include>
		        <input type="button" value=">>" class="expand"/>
		        <div class="expDet">
		        </div>
		    </div>
		    <div class="right-twoRight fr">
	        	<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
				<div class="search">
					<input type="text" id="fast"/>
					<input type="button" id="search" class="tableok" value="查询"/>
				</div>
	        </div>
	    </div>	
	    
	    <div class="control">
	        <jsp:include page="attsCommon.jsp"></jsp:include>
	    </div>
	
				
	   
			 <!-- 高级搜索 -->
		<form action="${pageContext.request.contextPath}/emp/search.do" method="post" enctype="multipart/form-data" id="sousuo" style="display:none;margin-left:20px;margin-right:20px;">
			<table class="table">
				<tr>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					<td colspan="2" style="width:50%;padding:0;border:none;"></td>
				</tr>
				<tr>
				<tr>
					<td colspan="4"><p style=" margin: 0">基本资料</p>
					</td>
				</tr>
				<td style="text-align: right;">员工号：</td>
				<td><input type="text" name="snumber" id="snumber" style="width:200px;">
				</td>
				<td style="text-align: right;">姓名：</td>
				<td><input type="text" name="snamepy" id="snamepy" style="width:200px;">
				</td>
				</tr>
				<tr>
					<td style="text-align: right;">部门：</td>
					<td><input name="sdepid" id="sdepid" style="width:200px;">
					<td style="text-align: right;">职位：</td>
					<td><input name="sposid" id="sposid" style="width:200px;"> <span style="margin-left:10px;color:red"></span>
					</td> 
				</tr>
			</table>
			<p>	<input type="hidden" name="branchs" value="${branchs}">
				<input type="hidden" name="id" id="id"> <span class="koko">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</form>
	    <div class="custom-scroll thistable">
	        <table id="searchTable" class="table tablelist">
	            <tr class="firsttr">
	                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
	                <th w_num="line" w_align="center" width="1%;">序号</th>
	                <th w_index="number" w_align="center" width="1%;">员工号</th>
	                <th w_index="realname" w_align="center" width="1%;">真实姓名</th>
	                <th w_index="aliasname" w_align="center" width="1%;">艺名</th>
	                <th w_index="sex" w_align="center" width="1%;">性别</th>
	                <th w_index="phone" w_align="center" width="1%;">联系电话</th>
	                <th w_index="qq" w_align="center" width="1%;">QQ</th>
	                <th w_index="platName" w_align="center" width="1%;">平台</th>
	                <th w_index="channels" w_align="center" width="1%;">频道号</th>
	                <th w_index="genre" w_align="center" width="1%;">艺人类型</th>
	                <th w_index="costArtists" w_align="center" width="1%;">艺人成本</th>
	                <th w_index="beLiveTime" w_align="center" width="1%;">开播时间</th>
	                <th w_index="minimumGuarantee" w_align="center" width="1%;">保底</th>
	                <th w_index="basicSalary" w_align="center" width="1%;">底薪</th>
	                <th w_index="pushMoney" w_align="center" width="1%;">提成比例</th>
	                <th w_index="createDT" w_align="center" width="1%;">创建时间</th>
	                <th w_align="center">备注</th>
	            </tr>
	        </table>
	        <div id="actoresForm" style="display:none;margin-left:20px;margin-right:20px;">
	            <input type="hidden" name="id" id="id">
	            <p>请输入频道号</p>
	            <p><select name="xxx" id="xxx" style="width:200px;" maxlength="400"></select></p>
	            <p>请输入手机号码或艺名</p>
	            <p>
	                <input type="text" name="name" id="name" style="width:200px;" maxlength="400" class="input">
	                <span class="next">搜索</span>
	            </p>
	            <div class="custom-scroll" style="position:relative;margin-top:20px;height:150px;overflow:auto;padding:10px 20px;;border:1px solid #ddd">
	                <table style="width:100%" class="yirentable">
	                    <tr class="firsttr">
	                        <th hidden>id</th>
	                        <th width="33%">员工号</th>
	                        <th width="33%">真实姓名</th>
	                        <th width="34%">艺名</th>
	                    </tr>
	                </table>
	            </div>
	            <p class="bCenter">
	                <input type="hidden" name="id" id="id" value="">
	                <span class="okok">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;
	                <input class="cancel" type="reset" name="Input" value="关闭">
	            </p>
	        </div>
	        <form action="${pageContext.request.contextPath}/actores/saveActores.do" method="post" id="actoresSaveForm" style="display:none;margin-left:20px;margin-right:20px;">
	            <table class="table">
	                <tr>
	                    <td colspan="2" style="width:50%;padding:0;border:none;"></td>
	                    <td colspan="2" style="width:50%;padding:0;border:none;"></td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">真实姓名：</td>
	                    <td><input type="text" name="realname" id="realname" style="width:200px;"></td>
	                    <td style="text-align: right;">艺名：</td>
	                    <td><input type="text" name="aliasname" id="aliasname" style="width:200px;"></td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">联系电话：</td>
	                    <td><input type="text" name="phone" id="phone" style="width:200px;"></td>
	                    <td style="text-align: right;">QQ：</td>
	                    <td><input type="text" name="qq" id="qq" style="width:200px;"></td>
	                </tr>
	                <tr>
	                	<td style="text-align: right;">性别：</td>
	                    <td>
	                        <select id="sex" name="sex" style="width:200px;">
	                            <option value="WOMAN">女</option>
	                            <option value="MAN">男</option>
	                        </select>
	                    </td>
	                    <td style="text-align: right;">艺人类型：</td>
	                    <td>
	                        <select id="genre" name="genre" style="width:200px;">
	                            <option value="ONLINE">线上艺人</option>
	                            <option value="OFFLINE">线下艺人</option>
	                        </select>
	                    </td>
	                </tr>
	                <tr>
	                    <td style="text-align: right;">开播日期：</td>
	                    <td><input type="text" class="easyui-datebox" editable="false" name="beLiveTime" id="beLiveTime" style="width:200px;"><label style="color: red;">*</label></td>
	                    <td style="text-align: right;">提成比例：<label style="color: red;">*</label></td>
	                	<td><input id="pushMoney" name="pushMoney" class="easyui-numberbox input" style="width:188px;" value="0">%</td>
	                </tr>
	                 <tr>
	                    <td style="text-align: right;">艺人成本：</td>
	                    <td><input type="text" class="easyui-numberbox input" name="costArtists" id="costArtists" style="width:200px;"></td>
	                    <td style="text-align: right;">主平台：</td>
	                    <td>
	                    <select id="mainPlatform" name="mainPlatform" style="width:200px;" onchange="gradeChange()">
	                            <option value="1">是</option>
	                            <option value="0">否</option>
	                        </select>
	                        <label style="color: red;">*</label>
	                    </td>
	                </tr>
	                 <tr class="num">
	                    <td style="text-align: right;">房间号：</td>
	                    <td><input type="text" class="easyui-numberbox input"  name="number" id="number" style="width:200px;"><label style="color: red;">*</label></td>
	                </tr>
	                <tr>
	                    <td style="text-align:center;" colspan="4">
	                        <input type="hidden" name="id" id="id">
	                        <input type="hidden" name="empid" id="empid">
	                        <input type="hidden" name="type" id="type">
	                        <input type="hidden" name="genrer" id="genrer">
	                        <input type="hidden" name="channelsId" id="channelsId">
	                        <input type="hidden" name="loadTable" id="loadTable">
	                        <input type="hidden" name="platId" id="platId">
	                        <input type="hidden" name="formGenre" id="formGenre">
	                        <input type="hidden" name="genrer" id="genrer">
	                        <input type="hidden" name="probationSalary" id="probationSalary">
	                        <input type="hidden" name="basicSalary" id="basicSalary">
	                        <input type="hidden" name="min" id="min">
	                        <p>
	                            <span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
	                            <input class="cancel" type="reset" name="Input" value="关闭">
	                        </p>
	                    </td>
	                </tr>
	            </table>
	        </form>
	    </div>
    </div>
</body>
</html>