<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>线下管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;}
        .yirentable tr {height: 30px;line-height: 30px;}
        .yirentable tr:hover {background-color: #ddd;}
        .yirentable .firsttr:hover {background-color: #fff;}
        .yirentable .selected {background-color: #ddd;}
        .control{
        	margin-left:-120px; 
        }
    .control .regspan {
		float:right;
		font-family: 'Microsoft YaHei';
		margin-right: 130px;
		font-weight: normal;
		margin-top: 3px;
		display: none;
	}
	.control .rspan {
		cursor: pointer;
		float:right;
		height: 27px;
		margin-right:-190px;
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
		color:black;
		text-decoration: none;
	}
	.seaC{
		position:relative;
		width:204px; 
	}
	.next{
		position: absolute;
	    right: 0;
	    top: -1px;
	    border: 1px solid #ddd;
	    text-align: center;
	    height: 30px;
	    line-height: 30px;
	    color: #fff;
	    background-color: #0074ac;
	    width: auto;
	    padding: 0px 14px;
	    border-radius: 2px;
	    cursor: pointer; 
	}
	.bottom{
		text-align:center;
	}
	.okok{
		background-color: #e35d00;
	}
	.firsttr{
		border-bottom:1px solid #ddd; 
	}
	.table td{
		border:none; 
	}
	.tablelist tr{
		border-bottom:1px solid #ddd;
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
                    $("#empForm select option").remove();
                }
            });
            var grid = $.fn.bsgrid.init("searchTable", {
                url: "${pageContext.request.contextPath}/manager/readPagesOffline.do?toUrl=${toUrl}&genre=OFFLINE&branchs=${branchs}",
                pageSizeSelect: true,
                pageSize:pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true
            });

            //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#empForm");
                $(".yirentable tr").not(".firsttr").remove()
                $.basewindow("新增助理", $chuangkou, 548,384);
            });
            //搜索
            $("#empForm .next").click(function () {
                var $self = $(this);
                var data = {
                    name: $self.prev("input").val(),
                    loadTable: "false",
                    seachEmp : "true",
                    seachAct : "false"
                }
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/user/search.do",
                    cache: false,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                }).done(function (data) {
                    $(".yirentable tr").not(".firsttr").remove()
                    if(data.length>0){
                    $(data).each(function (index) {
                        var $self = $(this)[0];
                        if (index == 0) {
                            $(".yirentable").append("<tr class='selected'><td hidden>" + $self.id + "</td><td hidden>" + $self.loadTable + "</td><td>" + $self.num + "</td><td>" + $self.name + "</td><td>" + $self.aliasname + "</td></tr>")
                        }
                        else {
                            $(".yirentable").append("<tr><td hidden>" + $self.id + "</td><td hidden>" + $self.loadTable + "</td><td>" + $self.num + "</td><td>" + $self.name + "</td><td>" + $self.aliasname + "</td></tr>")
                        }
                    });
                    }else{
                    	$.threetop("未搜索到结果!");
                    }
                }).error(function (jqXHR, textStatus, errorThrown) { });
            });
            //下一步
            $("#empForm .okok").click(function () {
                var $self = $(this);
                if ($self.hasClass("okok")) {
                    var id = $(".yirentable .selected td:first").text();
                    if (id != null && id > 0) {
                        var editurl = "${pageContext.request.contextPath}/manager/empEdit.do";
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: editurl,
                            data: "id=" + id,
                            show: "slide",
                            success: function (data) {
                                if (data.resultStatus == 200) {
                                    var $data = data.data;
                                    $("#empForm").appendTo($("body")).hide();
                                    $.basewindow("新增助理", "#managerForm", 600,500);
                                    $("#empid").val($data.id);
                                    $("#aliasname").val($data.aliasname);
                                    $("#realname").val($data.realname);
                                    $("#phone").val($data.phone);
                                    $("#qq").val($data.qq);
                                    $("#address").val($data.address);
                                    $("#idcard").val($data.idcard);
                                    $("#positions").val($data.positions); 
                                    $("#sex").val($data.sex);
                                    
                                    if ($data.entryTime != "") {
                                        $("#entryTime").datebox("setValue", $data.entryTime);
                                    }
                                    if($("#empForm .next").prev("input").val()==null){
                                        $("#managerForm #aliasname").val($("#empForm .next").prev("input").val());
                                    }
                                } else if (data.resultStatus == 101) {
                                    $.threetop("当前用户已存在!")
                                }
                            }, error: function (data) {
                            }
                        });
                    }else{
                        $.threetop("请选择用户!");
                    }
                }
            });

            //修改
            $(".control .mid").click(function () {
                var $chuangkou = $("#managerForm");
                if (grid.getSelectedRow().length > 0) {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    var editurl = "${pageContext.request.contextPath}/manager/edit.do";
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: editurl,
                        data: "id=" + id,
                        show: "slide",
                        success: function (data) {
                            $("#id").val(data.id);
                            $("#aliasname").val(data.aliasname);
                            $("#realname").val(data.realname);
                            $("#address").val(data.address);
                            $("#phone").val(data.phone);
                            $("#qq").val(data.qq);
                            $("#idcard").val(data.idcard);
                            $("#empid").val(data.empid);
                            $("#positions").val(data.positions);
                            $("#foodSubsidy").val(data.foodSubsidy); 
                            
                            $("#sex").val(data.sex);
                            if (data.entryTime != "") {
                                $("#entryTime").datebox("setValue", data.entryTime);
                            }
                          
                        }, error: function (data) {
                        }
                    });

                    $.basewindow("修改助理", $chuangkou, 450, 400);
                    $(".basewindow").addClass("custom-scroll");
                    $chuangkou.css({ "display": "block" });
                } else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            })


            //删除
            $(".control .del").click(function () {
                if (grid.getSelectedRow().length > 0) {
                    $.yesorno("是否删除？", 300, 160, function () {
                        var id = grid.getRowRecord(grid.getSelectedRow()).id;
                        var delUrl = "${pageContext.request.contextPath}/manager/delete.do?t=" + new Date().getTime();
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


                }
                else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            });
            
            //离职
            $(".control .leave").click(function () {
                var $chuangkou = $("#leaveForm");
                if (grid.getSelectedRow().length > 0) {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    var editurl = "${pageContext.request.contextPath}/manager/edit.do";
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: editurl,
                        data: "id=" + id,
                        show: "slide",
                        success: function (data) {
                            $("#id").val(data.id);
                            $("#phone").val(data.phone);
                            $("#aliasname").val(data.aliasname);
                            $("#realname").val(data.realname);
                            $("#div_dialog").dialog({ title: "助理离职" });
                        }, error: function (data) {
                        }
                    });

                    $.basewindow("助理离职", $chuangkou, 600, 400);
                    $chuangkou.css({ "display": "block" });
                } else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            })


            $(".yirentable").delegate("tr:not('.firsttr')", "click", function () {
                var $self = $(this);
                if ($self.hasClass("selected")) {
                }
                else {
                    $self.siblings("tr").removeClass("selected");
                    $self.addClass("selected");
                }
            });
            //提交+判断
            $("#managerForm .okok").click(function () {
                var realname = $("#realname").val();
                if (realname == "" || realname == null) {
                    $.threetop("请填写姓名!");
                }
                else {
                    $("#managerForm").ajaxSubmit(function(data) {
                        if(data !=null && data.resultStatus==200){
                            $('body').append($("#managerForm"));
                       		$(".zhezhao-basewindow").hide();
    		                $(".basewindow").hide(); 
    		                $("#managerForm").hide();
                       		grid.refreshPage();
                        }else{
                            $.threetop("保存失败");
                        }
                  })
                }
            });
            
            /**详情(全部)*/
			 $(".views").click(function() {
	 			var $chuangkou = $("#emplook");
				if (grid.getSelectedRow().length > 0) {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;
					 var editurl = "${pageContext.request.contextPath}/manager/editmanager.do";
					$.ajax({
						type : "get",
						dataType : "json",
						url : editurl,
						data : "id=" + id,
						show : "slide",
						success : function(data) {
							  var $data = data.data; 
								$("#emplook #vsex").text($data.sex);
								$("#emplook #vrealname").text($data.realname);
								$("#emplook #valiasname").text($data.aliasname);
								$("#emplook #vphone").text($data.phone);
								$("#emplook #vqq").text($data.qq); 
								$("#emplook #ventryTime").text($data.entryTime); 
								if($data.genrer=="试用")
									$("#emplook #vbasicSalary").text($data.probationSalary); 
								if($data.genrer=="正式")
									$("#emplook #vbasicSalary").text($data.basicSalary); 	
								$("#emplook #vbirth").text($data.birth);  
								$("#emplook #vgenre").text($data.genre); 
								 
								$("#emplook #vidcard").text($data.idcard);
								$("#emplook #vaddress").text($data.address);
						},
						error : function(data) {
						}
					});
					$.basewindow("详细资料", $chuangkou,600,370);
					$chuangkou.css({
						"display" : "block"
					});
					} else {
						$.threesecond("请先选择", 200, 100, 1000);
					}
				});
				/**详情(平台)*/
			 $(".viewsplat").click(function() {
	 			var $chuangkou = $("#emplook");
				if (grid.getSelectedRow().length > 0) {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;
					 var editurl = "${pageContext.request.contextPath}/manager/editmanager.do";
					$.ajax({
						type : "get",
						dataType : "json",
						url : editurl,
						data : "id=" + id,
						show : "slide",
						success : function(data) {
							  var $data = data.data; 
								$("#emplook #vsex").text($data.sex);
								$("#emplook #vrealname").text($data.realname);
								$("#emplook #valiasname").text($data.aliasname);
								$("#emplook #vphone").text($data.phone);
								$("#emplook #vqq").text($data.qq); 
								$("#emplook #ventryTime").text($data.entryTime); 
								if($data.genrer=="试用")
									$("#emplook #vbasicSalary").text($data.probationSalary); 
								if($data.genrer=="正式")
									$("#emplook #vbasicSalary").text($data.basicSalary); 	
								$("#emplook #vbirth").text($data.birth);  
								$("#emplook #vgenre").text($data.genre); 
								 
								$("#emplook #vidcard").text($data.idcard);
								$("#emplook #vaddress").text($data.address);
						},
						error : function(data) {
						}
					});
					$.basewindow("详细资料", $chuangkou,600,370);
					$chuangkou.css({
						"display" : "block"
					});
					} else {
						$.threesecond("请先选择", 200, 100, 1000);
					}
				});
				
            //回车操作
            $(window).keydown(function (e) {
                if (e.which == 13 && $("#empForm").css("display") == "block") {
                    $("#empForm .next").click();
                }
            })
            
              //快速搜索
    		$("#fast").change(function() {
    		    var fastArg = $("#fast").val();
    			var searchParames={
    			    "fastArg": fastArg
    			}
    			grid.search(searchParames);
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
		    			$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/manager/show.do?branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
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
			<span>人事系统/平台人员</span> 
			<span>></span>
			<span class="color99">线下管理</span>
		
		 </div>
		 <div class="clearFix right-twoRight">
			<div class="fr">
				<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索">
			    	<button type="button" id="seach" class="tableok">搜索</button>				
				</div>
			</div>
		</div>
	    <div class="control">
	        <jsp:include page="attsCommon.jsp"></jsp:include>
	    </div>
	    <div class="custom-scroll thistable" style="overflow:auto; background-color:#fff; ">
	        <table id="searchTable" class="table tablelist">
	            <tr class="firsttr">
	                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
	                <th w_num="line" w_align="center" width="1%;">序号</th>
	                <th w_index="number" w_align="center" width="1%;">员工号</th>
	                <th w_index="realname" w_align="center" width="1%;">真实姓名</th>
	                <th w_index="aliasname" w_align="center" width="1%;">艺名</th>
	                <th w_index="sex" w_align="center" width="1%;">性别</th>
	                <th w_index="phone" w_align="center" width="1%;">电话</th>
	                <th w_index="qq" w_align="center" width="1%;">QQ</th>
	                <th w_index="positions" w_align="center" width="1%;">岗位</th>
	                <th w_index="genre" w_align="center" width="1%;">管理类型</th>
	                <th w_index="entryTime" w_align="center" width="1%;">入职日期</th>
	                <th w_index="genrer" w_align="center" width="1%;">转正状态</th> 
	                <th w_align="center">备注</th>
	            </tr>
	        </table>
	
	    </div>
	    <div id="empForm" style="display:none;margin-left:20px;margin-right:20px;">
	        <input type="hidden" name="id" id="id">
	        <p class="seaC">
	            <input type="text" name="name" id="name" style="width:200px; height:28px; " maxlength="400" class="input">
	            <span class="next">搜索</span>
	        </p>
	        <div class="custom-scroll" style="position:relative;margin-top:20px;height:150px;overflow:auto; ">
	            <table style="width:100%" class="yirentable">
	                <tr class="firsttr">
	                    <th hidden>id</th>
	                    <th width="33%">员工号</th>
	                    <th width="33%">真实姓名</th>
	                    <th width="34%">艺名</th>
	                </tr>
	            </table>
	        </div>
	        <p class="bottom">
	            <input type="hidden" name="id" id="id" value="">
	            <span class="okok">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input class="cancel" type="reset" name="Input" value="关闭">
	        </p>
	    </div>
	    <!-- 查看详细 -->
		
			<div id="emplook" style="margin-left: 20px; margin-right: 20px; display:none;">
				<table class="table2">
					<tr>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					</tr>
					<tr>
						<td colspan="4" class="yellowline"><p style=" margin: 0">基本资料</p>
						</td>
					</tr> 
					<tr>
						<td style="text-align: right;" class="hei">真实姓名：</td>
						<td><span id="vrealname" class="w200"></span>
						</td>
						<td style="text-align: right;" class="hei">	QQ：</td>
						<td><span id="vqq"></span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">联系电话：</td>
						<td><span id="vphone" class="w200">男 </span></td>
						<td style="text-align: right;" class="hei">艺名：</td>
						<td><span id="valiasname" class="w200"></span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">身份证号：</td>
						<td><span id="vidcard" class="w200"></span></td>
						<td style="text-align: right;" class="hei">入职时间：</td>
						<td><span id="ventryTime" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">联系电话：</td>
						<td><span id="vphone" class="w200"></span></td>
						<td style="text-align: right;" class="hei">性别：</td>
						<td><span id="vsex" class="w200"></span>
						</td>
					</tr>
		
					<tr>
						<td style="text-align: right;" class="hei">管理类型：</td>
						<td><span id="vgenre" class="w200"></span></td>
						<td style="text-align: right;" class="hei">底薪：</td>
						<td><span id="vbasicSalary" class="w200"></span></td>
					</tr>
		
					<tr>
						<td style="text-align: right;" class="hei">现居地址：</td>
						<td><span id="vaddress" class="w200"></span></td>
						<td></td>
						<td></td>
					</tr> 
					<tr>
						
						</td>
					</tr>  
		
				</table>
				<p style="text-align:center">
					<input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</div>
	    
	    <form action="${pageContext.request.contextPath}/manager/save.do" method="post" id="managerForm" enctype="multipart/form-data" style="display:none;margin-left:20px;margin-right:20px">
	        <table width="100%" border="0" cellpadding="4" cellspacing="1" class="table">
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">艺名 ：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" readonly="readonly" name="aliasname" id="aliasname" style="width:200px;" maxlength="400" class="input"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">真实姓名：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" readonly="readonly" name="realname" id="realname" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="phone" id="phone" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">QQ：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="qq" id="qq" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">性别：<label style="color: red;">*</label></td>
	                <td class="ji">
	                    <select id="sex" name="sex" style="width:200px;">
	                        <option value="WOMAN">女</option>
	                        <option value="MAN" selected="selected">男</option>
	                    </select>
	                </td>
	            </tr>
	            <tr class="ji">
	                <td class=" rt" style="text-align: right;">岗位：
	                    <label style="color: red;">*</label>
	                </td>
	                <td><input id="positions" name="positions" type="text" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">入职时间：<label style="color: red;">*</label></td>
	                <td><input type="text" class="easyui-datebox" editable="false" name="entryTime" id="entryTime" style="width:200px;"></td>
	            </tr>
	        </table>
	        <input type="hidden" name="id" id="id">
	         <input type="hidden" name="genre" id="genre" value = "0">
	        <input type="hidden" name="empid" id="empid">
	        <p style="text-align: center; margin-top:46px; border-top:1px solid #ddd; ">
	        	<span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input class="cancel" type="reset" name="Input" id="butrs" value="关闭">
	        </p>
	    </form>
    </div>
     
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  