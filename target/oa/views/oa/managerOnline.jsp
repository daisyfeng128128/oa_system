<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>线上管理</title>
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
    	margin-left:-232px; 
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
	.table td{
		border:none; 
	}
	.table .ji input,.table .ji select{
		height:26px; 
	}
	.bottom{
		text-align:center;
		border-top:1px solid #ddd; 
		margin-top:42px; 
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
                url: "${pageContext.request.contextPath}/manager/readPagesOffline.do?toUrl=${toUrl}&genre=ONLINE&branchs=${branchs}",
                pageSizeSelect: true,
                pageSize:pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true
            });

            //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#managerForm");
                $(".yirentable tr").not(".firsttr").remove()
                $.basewindow("新增助理", $chuangkou, 796,386);
                $(".basewindow").addClass("custom-scroll");
            });
           
            //修改
            $(".control .mid").click(function () {
                var $chuangkou = $("#managerUpdate");
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
                            $("#positions").val(data.positions);
                            $("#basicSalary").numberbox("setValue",data.basicSalary);
                            $("#foodSubsidy").val(data.foodSubsidy);
                            $("#bankCard").val(data.bankCard);
                            $("#bankCardAccount").val(data.bankCardAccount);
                            $("#sex").val(data.sex);
                            if (data.entryTime != "") {
                                $("#entryTime").datebox("setValue", data.entryTime);
                            }
                        }, error: function (data) {
                        }
                    });

                    $.basewindow("修改助理", $chuangkou, 450,550);
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
                    $.basewindow("助理离职", $chuangkou, 470, 400);
                    $(".basewindow").addClass("custom-scroll");
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
            $("#leaveForm .okok").click(function () {
                var leaveReasons = $("#leaveReasons").val();
                if (leaveReasons == "" || leaveReasons == null) {
                    $.threetop("请填写离职原因!");
                }else {
                    $("#leaveForm").ajaxSubmit(function(data) {
                        if(data !=null && data.resultStatus==200){
                            $('body').append($("#leaveForm"));
                       		$(".zhezhao-basewindow").hide();
    		                $(".basewindow").hide(); 
    		                $("#leaveForm").hide(); 
                       		grid.refreshPage();
                        }else{
                            $.threetop("保存失败");
                        }
                  })
                }
            });
			$("#entryPlatform").combogrid({
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
            //回车操作
            $(window).keydown(function (e) {
                if (e.which == 13 && $("#empForm").css("display") == "block") {
                    $("#empForm .next").click();
                }
            })
            
            $("#phone").change(function(){
              	var phone= $("#phone").val();
              	var id = $("#id").val();
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/manager/checkPhone.do",
				    cache: false,
				    data: "id="+id+"&phone="+phone,
				    success:function(data){
				        if(data.resultStatus ==100){
				            $.threetop("手机号不正确或已重复!");
				            $("#phone").val("");
				        }
				    },
				    error:function(data){
				        
				    }
                })
            })
            
            //提交+判断
            $("#managerForm .okok").click(function () {
                var basicSalary = $("#basicSalary").val();
                var realname = $("#realname").val();
                var sphone= $("#phone").val();
                var entryPlatform = $("#entryPlatform").combogrid('getValue');
                var channel = $("#channel").val();
                var sqq = $("#qq").val();
                var sex = $("#sex ").val();
                var telReg = !!sphone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
                //如果手机号码不能通过验证
                if(telReg == false){
                    $.threetop("请填写正确手机号!");
                    return;
                }
                if(sqq != ""){
                    if(!/^\d+$/.test(sqq) || sqq.length <6 ){
                        $.threetop("请填写正确QQ号!");
                        return;
                    }
                }
                
                if (realname == "" || realname == null  ) {
                    $.threetop("请填写姓名!");
                }else if( basicSalary == "" ||basicSalary== null){
                    $.threetop("请填写底薪!");
                } else if(sphone ==""|| sphone==null ){
                    $.threetop("请填写手机号!");
                } else if(sex == "" || sex == null){
                	$.threetop("请选择性别!");
                }  else if(entryPlatform == "" || entryPlatform == null){
                	$.threetop("请选择入职平台!");
                } else if(channel == "" || channel == null){
                	$.threetop("请选择所属频道!");
                }else {
                   var data = {
                       id:$("#id").val(),
                       aliasname :$("#aliasname").val(),
                       realname :$("#realname").val(),
                       phone :$("#phone").val(),
                       qq :$("#qq").val(),
                       sex :$("#sex ").val(),
                       positions:$("#positions").val(),
                       bankCardAccount:$("#bankCardAccount").val(),
                       bankCard:$("#bankCard").val(),
                       entryTime:$("#entryTime").datebox("getValue"),
                       basicSalary:$("#basicSalary").numberbox("getValue"),
                       foodSubsidy :$("#foodSubsidy").val(),
                       idcard:$("#idcard").val(),
                       address:$("#address").val(),
                       empid:$("#empid").val(),
                       genre:$("#genre").val(),
                       channel:$("#channel").val(),
                       entryPlatform:$("#entryPlatform").combogrid('getValue'),
           			};
					$.ajax({
					    type: "POST",
					       url: "${pageContext.request.contextPath}/manager/saveOnline.do",
					       cache: false,
					       data: JSON.stringify(data),
					       contentType: 'application/json; charset=utf-8',
					       success: function(data){
					           if(data != null && data.resultStatus==200){
					               $('body').append($("#managerForm"));
					               $(".basewindow").hide(); 
					               $(".zhezhao-basewindow").hide();
					               $("#managerForm").hide(); 
					               grid.refreshPage();
					           }else if(data != null && data.resultStatus==101){
					               $.threesecond("保存失败!姓名,艺名在管理库已存在!", 300, 100, 1000);
					           }else{
					               $.threesecond("保存失败", 200, 100, 1000);
					           }
					       },error:function(data){}
					});
                }
            });
            
             /**详情()*/
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
            
            //提交+判断
            $("#managerUpdate .okok").click(function () {
                var basicSalary = $("#basicSalary").val();
                var realname = $("#realname").val();
                var sphone= $("#phone").val();
                var sqq = $("#qq").val();
                var sex = $("#sex ").val();
                var telReg = !!sphone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
                //如果手机号码不能通过验证
                if(telReg == false){
                    $.threetop("请填写正确手机号!");
                    return;
                }
                if(sqq != ""){
                    if(!/^\d+$/.test(sqq) || sqq.length <6 ){
                        $.threetop("请填写正确QQ号!");
                        return;
                    }
                }
                
                if (realname == "" || realname == null  ) {
                    $.threetop("请填写姓名!");
                }else if( basicSalary == "" ||basicSalary== null){
                    $.threetop("请填写底薪!");
                } else if(sphone ==""|| sphone==null ){
                    $.threetop("请填写手机号!");
                } else if(sex == "" || sex == null){
                	$.threetop("请选择性别!");
                }else {
                   var data = {
                       id:$("#id").val(),
                       aliasname :$("#aliasname").val(),
                       realname :$("#realname").val(),
                       phone :$("#phone").val(),
                       qq :$("#qq").val(),
                       sex :$("#sex ").val(),
                       positions:$("#positions").val(),
                       bankCardAccount:$("#bankCardAccount").val(),
                       bankCard:$("#bankCard").val(),
                       entryTime:$("#entryTime").datebox("getValue"),
                       basicSalary:$("#basicSalary").numberbox("getValue"),
                       foodSubsidy :$("#foodSubsidy").val(),
                       idcard:$("#idcard").val(),
                       address:$("#address").val(),
                       empid:$("#empid").val(),
                       genre:$("#genre").val(),
           			};
					$.ajax({
					    type: "POST",
					       url: "${pageContext.request.contextPath}/manager/saveOnline.do",
					       cache: false,
					       data: JSON.stringify(data),
					       contentType: 'application/json; charset=utf-8',
					       success: function(data){
					           if(data != null && data.resultStatus==200){
					               $('body').append($("#managerUpdate"));
					               $(".basewindow").hide(); 
					               $(".zhezhao-basewindow").hide();
					               $("#managerForm").hide(); 
					               grid.refreshPage();
					           }else if(data != null && data.resultStatus==101){
					               $.threesecond("保存失败!姓名,艺名在管理库已存在!", 300, 100, 1000);
					           }else{
					               $.threesecond("保存失败", 200, 100, 1000);
					           }
					       },error:function(data){}
					});
                }
            });
           
				/**详情(平台)*/
			 $(".viewsplat").click(function() {
	 			var $chuangkou = $("#emplook");
				if (grid.getSelectedRow().length > 0) {
					var id = grid.getRowRecord(grid.getSelectedRow()).id;
					 var editurl = "${pageContext.request.contextPath}/manager/editmanagerOnline.do";
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
            
          //快速搜索
    		$("#fast").change(function() {
    		    var fastArg = $("#fast").val();
    			var searchParames={
    			    "fastArg": fastArg
    			}
    			grid.search(searchParames);
    		})
			
			$.ajax({
	    		type: "POST",
	    		url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
	    		data: "",
	    		cache: false
	    	}).done(function (data) {
	    		if(data.data.length > 0){
	    			$(data.data).each(function(index,value){
		    			var $self=$(this);
		    			$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/manager/onlineShow.do?branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
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
			<span class="color99">线上管理</span>
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
	                <th w_index="basicSalary" w_align="center" width="1%;">底薪</th>
	                <th w_index="foodSubsidy" w_align="center" width="1%;">餐补</th>
	                <th w_index="bankCardAccount" w_align="left" width="1%;">开户行地址</th>
	                <th w_index="bankCard" w_align="left" width="1%;">工资卡账号</th>
	                <th w_align="center">备注</th>
	            </tr>
	        </table>
	    </div>
	    
	    <form action="${pageContext.request.contextPath}/manager/saveOnline.do" method="post" id="managerForm" enctype="multipart/form-data" style="display:none;margin-left:20px;margin-right:20px">
	        <table width="100%" border="0" cellpadding="4" cellspacing="1" class="table">
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">真实姓名：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="realname" id="realname" style="width:200px;" maxlength="400"></td>
	                <td class="rt" style="text-align: right;">艺名 ：</td>
	                <td class="ji"><input type="text" name="aliasname" id="aliasname" style="width:200px;" maxlength="400" class="input"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="phone" id="phone" style="width:200px;" maxlength="400"></td>
	                <td class="rt" style="text-align: right;">QQ：</td>
	                <td class="ji"><input type="text" name="qq" id="qq" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">性别：</td>
	                <td class="ji">
	                    <select id="sex" name="sex" style="width:200px;">
	                        <option value="WOMAN">女</option>
	                        <option value="MAN" selected="selected">男</option>
	                    </select>
	                </td>
	                <td class="rt" style="text-align: right;">入职时间：</td>
	                <td><input type="text" class="easyui-datebox" editable="false" name="entryTime" id="entryTime" style="width:200px; "></td>
	            </tr>
	            <tr class="ji">
	                <td class=" rt" style="text-align: right;"> 岗位：</td>
	                <td><input id="positions" name="positions" type="text" style="width:200px;" maxlength="400"></td>
	                <td class="rt" style="text-align: right;">开户行：</td>
	                <td class="ji"><input type="text" name="bankCardAccount" id="bankCardAccount" style="width:200px;" maxlength="400"></td>
	                
	            </tr>

	             <tr class="ji">
	                <td class="rt" style="text-align: right;">工资卡卡号：</td>
	                <td class="ji"><input type="text" name="bankCard" id="bankCard" style="width:200px;" maxlength="400"></td>
	                <td class="rt" style="text-align: right;">底薪：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text"  class="easyui-numberbox input" name="basicSalary" id="basicSalary" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr>
					<td style="text-align: right;">入职平台：<label style="color: red;">*</label></td>
					<td><input name="entryPlatform" id="entryPlatform" style="width:200px;"></td>
					<td style="text-align: right;">所属频道：<label style="color: red;">*</label></td>
					<td><select id="channel" name ="channel" style="width:200px;"></select></td>
				</tr>
				<tr class="ji">
					<td class="rt" style="text-align: right;">餐补：</td>
					<td class="ji"><input type="text" name="foodSubsidy"
						id="foodSubsidy" style="width:200px;" maxlength="400"></td>
				</tr>
				
			</table>
	        <input type="hidden" name="id" id="id">
	        <input type="hidden" name="empid" id="empid">
	        <input type="hidden" name="platId" id="platId">
	        <input type= "hidden" name="genre" id = "genre" value="1">
	        <p class="bottom">
	        	<span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
	        	<input class="cancel" type="reset" name="Input" id="butrs" value="关闭">
	        </p>
	    </form>
	    
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
					</tr>  
		
				</table>
				<p style="text-align:center">
					<input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</div>
	    
	    <!-- 修改 -->
	    <form action="${pageContext.request.contextPath}/manager/saveOnline.do" method="post" id="managerUpdate" enctype="multipart/form-data" style="display:none;margin-left:20px;margin-right:20px">
	        <table width="100%" border="0" cellpadding="4" cellspacing="1" class="table">
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">艺名 ：</td>
	                <td class="ji"><input type="text" name="aliasname" id="aliasname" style="width:200px;" maxlength="400" class="input"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">真实姓名：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="realname" id="realname" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="phone" id="phone" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">QQ：</td>
	                <td class="ji"><input type="text" name="qq" id="qq" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">性别：</td>
	                <td class="ji">
	                    <select id="sex" name="sex" style="width:200px;">
	                        <option value="WOMAN">女</option>
	                        <option value="MAN" selected="selected">男</option>
	                    </select>
	                </td>
	            </tr>
	            <tr class="ji">
	                <td class=" rt" style="text-align: right;"> 岗位：</td>
	                <td><input id="positions" name="positions" type="text" style="width:200px;" maxlength="400"></td>
	            </tr>
	             <tr class="ji">
	                <td class="rt" style="text-align: right;">开户行：</td>
	                <td class="ji"><input type="text" name="bankCardAccount" id="bankCardAccount" style="width:200px;" maxlength="400"></td>
	            </tr>
	             <tr class="ji">
	                <td class="rt" style="text-align: right;">工资卡卡号：</td>
	                <td class="ji"><input type="text" name="bankCard" id="bankCard" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">入职时间：</td>
	                <td><input type="text" class="easyui-datebox" editable="false" name="entryTime" id="entryTime" style="width:200px;"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">底薪：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text"  class="easyui-numberbox input" name="basicSalary" id="basicSalary" style="width:200px;" maxlength="400"></td>
	            </tr>
				<tr class="ji">
					<td class="rt" style="text-align: right;">餐补：</td>
					<td class="ji"><input type="text" name="foodSubsidy"
						id="foodSubsidy" style="width:200px;" maxlength="400"></td>
				</tr>
			</table>
	        <input type="hidden" name="id" id="id">
	        <input type="hidden" name="empid" id="empid">
	        <input type="hidden" name="platId" id="platId">
	        <input type= "hidden" name="genre" id = "genre" value="1">
	        <p class="bCenter"><span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" id="butrs" value="关闭"></p>
	    </form>
	     <form action="${pageContext.request.contextPath}/manager/saveLeave.do" method="post" id="leaveForm" enctype="multipart/form-data" style="display:none;margin-left:20px;margin-right:20px">
	     	 <table width="100%" border="0" cellpadding="4" cellspacing="1" class="table">
	     	 	<tr class="ji">
	                <td class="rt" style="text-align: right;">艺名 ：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" disabled="disabled" name="aliasname" id="aliasname" style="width:200px;" maxlength="400" class="input"></td>
	            </tr>
	             <tr class="ji">
	                <td class="rt" style="text-align: right;">真实姓名：<label style="color: red;">*</label></td>
	                <td class="ji"><input disabled="disabled" type="text" name="realname" id="realname" style="width:200px;" maxlength="400"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
	                <td class="ji"><input type="text" name="phone" id="phone" style="width:200px;" maxlength="400" disabled="disabled"></td>
	            </tr>
	            <tr class="ji">
	                <td class="rt" style="text-align: right;">离职原因：<label style="color: red;">*</label></td>
	                <td class="ji"><textarea name="leaveReasons" id="leaveReasons" style="width:300px;height:120px" maxlength="400"></textarea></td>
	            </tr>
	     	 </table>
	     	 <input type="hidden" name="id" id="id">
	           <p class="bCenter"> <span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" id="butrs" value="关闭"></p>
	     </form>
    </div>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  