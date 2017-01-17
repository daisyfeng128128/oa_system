<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>频道助理</title>
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
   		.right-twoRight .regspan{
   			margin-top:6px;
   		}
   		.control{
   			margin-left:-116px;
   		}
   		.right-onehalf{
   			width:auto;
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
        
            $.ajax({
    			type: "POST",
    			url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
    			data: "",
    			cache: false
    		}).done(function (data) {
    			if(data.data.length > 0){
    				$(data.data).each(function(index,value){
	    				var $self=$(this);
	    				$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/platManager/show.do?platId=${platId}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
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
						            
            //加载数据
            $(".right-onehalf >span").click(function () {
                var $self = $(this);
                if (!$self.hasClass("spanchange")) {
                	$(".right-onehalf span").removeClass("spanchange");
                    $self.addClass("spanchange");
                    searchParames = { 'platId': $self.attr("value"),'branchs':${branchs} };
                    grid.search(searchParames);
                    $("#managerForm select option").remove();
                }
            });
            var grid = $.fn.bsgrid.init("searchTable", {
                url: "${pageContext.request.contextPath}/platManager/readPages.do",
                pageSizeSelect: true,
    			pageSize:pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true
            });
            searchParames = { 'platId': $(".right-onehalf .spanchange").attr("value") ,'branchs':${branchs} };
            grid.search(searchParames);

            //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#managerForm");
                $(".yirentable tr").not(".firsttr").remove()
                $.basewindow("新增频道助理", $chuangkou, 500, 430);
                $(".basewindow").addClass("custom-scroll");
                if ($("#managerForm select option").length == 0) {
                    var data = {
                        id: $(".right-onehalf .spanchange").attr("value")
                    }
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/plats/getChannels.do",
                        cache: false,
                        data: JSON.stringify(data),
                        contentType: "application/json; charset=utf-8",
                    }).done(function (data) {
                        if (data.resultStatus == 100) {
                            $("#managerForm select").parent("p").hide();
                            $("#managerForm select").parent("p").prev("p").hide();
                        } else {
                            $("#managerForm select").parent("p").show();
                            $("#managerForm select").parent("p").prev("p").show();
                            $(data.data).each(function () {
                                var $self = $(this);
                                $("#managerForm select").append("<option value=" + $self[0].id + ">" + $self[0].channels + "</option>")
                            });
                        }
                    }).error(function (jqXHR, textStatus, errorThrown) { });
                }
            });
            //搜索
            $("#managerForm .next").click(function () {
                var $self = $(this);
                var data = {
                    realname: $self.prev("input").val()
                }
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/platManager/search.do",
                    cache: false,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                }).done(function (data) {
                    $(".yirentable tr").not(".firsttr").remove()
                    if(data.length>0){
                    $(data).each(function (index) {
                        var $self = $(this)[0];
                        if (index == 0) {
                            $(".yirentable").append("<tr class='selected'><td hidden>" + $self.id + "</td><td>" + $self.number + "</td><td>" + $self.realname + "</td><td>" + $self.aliasname + "</td></tr>")
                        }
                        else {
                            $(".yirentable").append("<tr><td hidden>" + $self.id + "</td><td>" + $self.number + "</td><td>" + $self.realname + "</td><td>" + $self.aliasname + "</td></tr>")
                        }
                    });
                    }
                    else{
                    	$.threetop("未搜索到结果!");
                    	
                    }
                }).error(function (jqXHR, textStatus, errorThrown) { });
            });
            //下一步
            $("#managerForm .okok").click(function () {
                var $self = $(this);
                if ($self.hasClass("okok")) {
                    var id = $(".yirentable .selected td:first").text();
                    var platId = $(".right-onehalf .spanchange").attr("value");
                    var channelId = $("#managerForm select option:checked").val();
                    if (platId == "" || platId == "undefined")
                        platId = "0";
                    if (channelId == undefined || channelId == "")
                        channelId = "0";
                    $("#managerId").val(id);
                    $("#platId").val(platId);
                    $("#channelId").val(channelId);
                    if (id == "" || id == null) {
                        $.threetop("请选择助理！");
                    }else {
                        var id = $(".yirentable .selected td:first").text();
                        var platId = $(".right-onehalf .spanchange").attr("value");
                        var channelId = $("#managerForm select option:checked").val();
                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            url:"${pageContext.request.contextPath}/platManager/verify.do",
                            data:"id="+id+"&platId="+platId+"&channelId="+channelId,
                            success:function(data){
                                if(data.resultStatus == 200){
                                    $.threetop(data.resultMessage);
                                }else{
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
                                };
                            },
                            error:function(data){
                            }
                        })
                    }
                }
            });


            //删除
            $(".control .del").click(function () {
                if (grid.getSelectedRow().length > 0) {
                    $.yesorno("是否删除？", 300, 160, function () {
                        var id = grid.getRowRecord(grid.getSelectedRow()).id;
                        var delUrl = "${pageContext.request.contextPath}/platManager/delete.do?t=" + new Date().getTime();
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

            $(".yirentable").delegate("tr:not('.firsttr')", "click", function () {
                var $self = $(this);
                if ($self.hasClass("selected")) {
                }
                else {
                    $self.siblings("tr").removeClass("selected");
                    $self.addClass("selected");
                }
            });
           
          //快速搜索
    		$("#fast").change(function() {
    		    var fastArg = $("#fast").val();
    			var searchParames={
    			    "fastArg": fastArg,
    			    "platId": $(".right-onehalf .spanchange").attr("value") 
    			}
    			grid.search(searchParames);
    		})
	

            //回车操作
            $(window).keydown(function (e) {
                if (e.which == 13 && $("#managerForm").css("display") == "block") {
                    $("#managerForm .next").click();
                    return false;
                }

            })

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
			<span>直播系统</span> 
			<span>></span>
			<span class="color99">频道助理</span> 
	   	</div>
	    <div class="clearFix">
	    	<div class="right-onehalf fl">
		        <jsp:include page="platCommon.jsp"></jsp:include>
		        <input type="button" value=">>" class="expand"/>
		        <div class="expDet">
		        </div>
		    </div>
		    <div class="fr right-twoRight">
	        	<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索"/>
		    		<button type="button" id="seach" class="tableok" >搜索</button>
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
	                <th w_index="qq" w_align="center" width="1%;">QQ</th>
	                <th w_index="phone" w_align="center" width="1%;">手机</th>
	                <th w_index="plat" w_align="center" width="1%;">平台</th>
	                <th w_index="channel" w_align="center" width="1%;">频道号</th>
	                <th w_index="positions" w_align="center" width="1%;">岗位</th>
	                <th w_index="entryTime" w_align="center" width="1%;">入职日期</th>
	                <th w_index="probation" w_align="center" width="1%;">试用期</th>
	                <th w_index="regular" w_align="center" width="1%;">转正状态</th>
	                <th w_align="center">备注</th>
	            </tr>
	        </table>
	    </div>
	    <form  id="managerForm" action="${pageContext.request.contextPath}/platManager/save.do"  enctype="multipart/form-data" method="post" name="managerForm" style="display:none;margin-left:20px;margin-right:20px;" >
	        <p>请输入频道号</p>
	        <p><select  style="width:200px;" maxlength="400"></select></p>
	        <p>请输入员工号或真实姓名</p>
	        <p>
	            <input type="text" name="realname" id="realname" style="width:200px;" maxlength="400" class="input">
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
	            <input type="hidden" name="managerId" id="managerId" value="">
	            <input type="hidden" name="platId" id="platId" value="">
	            <input type="hidden" name="channelId" id="channelId" value="">
	            <span class="okok">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;
	            <input class="cancel" type="reset" name="Input" value="关闭">
	        </p>
	    </form>
    	
    </div>
   
</body>
</html>
