<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>大型充值</title>
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
        .table{border:none; }
        .table td{border:none; }
        .table td input{border-radius:2px; -moz-border-radius:2px; -o-border-radius:2px; 
        	height:22px;
        	border:1px solid #ddd; 
        	text-indent:1em;
        	margin-left: -6px;
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
		.mustred{color: red;}
		.right-onehalf {
			overflow:inherit;
			width:auto;
		}
		
		.control{
			margin-left:-166px;
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
		
	     	var grid = $.fn.bsgrid.init("searchTable", {
                url: "${pageContext.request.contextPath}/bigtop/readPages.do",
                pageSizeSelect: true,
        		pageSize: pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true,
                extend: {
	                settings: {
	                    supportGridEdit:true, 
	                    supportGridEditTriggerEvent: "",
	                    gridEditConfigs: {
	                        createDTs: {
	                            build: function (edit, value) {
	                                return value.split(" ")[0];
	                            } 
	                        }
	                    } 
	                }
	            }
            });
            var searchParames = { "platId": $(".right-onehalf .spanchange").attr("value"),date:"${date}","branchs":"${branchs}" };
            grid.search(searchParames);
            loader();
        	
        	$(".right-onehalf span").click(function () {
                var $self = $(this);
                if (!$self.hasClass("spanchange")) {
                    $(".right-onehalf span").removeClass("spanchange");
                    $self.addClass("spanchange");
                    searchParames = {"platId": $self.attr("value"),date:"${date}"};
                    loader();
                    grid.search(searchParames);
                }
            });
            
            $.ajax({
	    		type: "POST",
	    		cache: false,
	    		url: "${pageContext.request.contextPath}/bigtop/readDateList.do"
	    	}).done(function (data) {
	    		var $p = $(".right-onehalf .spanchange").attr("value");
	    		$(data.data[0]).each(function(index,value){
	    			var $self=$(this);
	    			$(".right-twoRight .riqi ul").append("<li><a href='${pageContext.request.contextPath}/bigtop/show.do?branchs=${branchs}&platId="+$p+"&date="+value+"'>"+ value +"</a></li>")
	    		});	
	    		if(data.data[1].length > 0){
	    			$(data.data[1]).each(function(index,value){
		    			var $self=$(this);
		    			$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/bigtop/show.do?date=${date}&platId="+$p+"&branchs="+$self[0].opkey+"'>"+ $self[0].opName +"</a></li>")
		    		});
	    		}else{
	    			$(".right-twoRight .regspan").hide();
    				$(".right-twoRight .rspan").hide();
	    		}
	    	});
	        $(".right-twoRight .riqi>span").click(function () {
	    		if (!$(".right-twoRight .riqi ul").is(":animated")) {
	    			$(".right-twoRight .riqi ul").fadeToggle();
	           	}
	           	loader();
	        });
	        $(".right-twoRight .rspan>span").click(function () {
	    		if (!$(".right-twoRight .rspan ul").is(":animated")) {
	    			$(".right-twoRight .rspan ul").fadeToggle();
	           	}
	           	loader();
	        });
		        
			function loader(){
				 $.ajax({
		    		type: "GET",
		    		cache: false,
		    		data: "date="+searchParames.date+"&platId="+searchParames.platId+"&branchs=${branchs}",
		    		url: "${pageContext.request.contextPath}/bigtop/readLoader.do"
		    	}).done(function (data) {
		    		var data = data.data;
		    		if(!data){
		    			data = {
		    				askfees:"0.00",
		    				payfees:"0.00",
		    				cashfees:"0.00"
		    			}
		    		}
					$("#taskfees").text(data.askfees);
					$("#tpayfees").text(data.payfees);
					$("#tcashfees").text(data.cashfees);
		    	});
			}
			
            $(".control .add").click(function(){
             	var $chuangkou = $("#bigtopForm");
            	$.basewindow("新增充值", $chuangkou, 420, 260);
            });
			
            $(".control .edit").click(function(){
             	var $chuangkou = $("#bigtopForm");
             	if (grid.getSelectedRow().length > 0) {
             		var id = grid.getRowRecord(grid.getSelectedRow()).id;
             		 $.ajax({
			    		type: "GET",
			    		cache: false,
			    		data: "id="+id,
			    		url: "${pageContext.request.contextPath}/bigtop/readBigtopDetails.do"
			    	}).done(function (data) {
						if(data.resultStatus == 200){
							$.basewindow("修改充值", $chuangkou, 420, 260);
							var data = data.data;
							$("#id").val(data.id);
							$("#askfees").val(data.askfees);
							$("#createDT").datebox("setValue",data.createDT.split(" ")[0]);		
						}
			    	});
             	}else{
             		$.threesecond("请先选择", 200, 100, 1000);
             	}
            });
						            
            $("#bigtopForm .okok").click(function(){
            	alert()
            	var id = $("#id").val();
            	var askfees = $("#askfees").val();
            	var createDT = $("#createDT").datebox("getValue");
            	if(!askfees){
            		$("#askfees").focus();
					$.threetop("带 * 为必填项!");
					return false;
            	}
            	if(!createDT){
            		$("#createDT").focus();
					$.threetop("带 * 为必填项!");
					return false;
            	}
				$.ajax({
					type: "POST",
					cache: false,
	                url: "${pageContext.request.contextPath}/bigtop/save.do",
	                data: "id="+id+"&askfees=" + askfees + "&createDT=" + createDT + "&date=${date}&platId="+$(".right-onehalf .spanchange").attr("value"),
	                success:function(data){
						$(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$('body').append($("#bigtopForm"));
						$("#bigtopForm").hide();
						$.threesecond("保存成功", 200, 100, 1000);
						grid.refreshPage();
						loader();
	                }
				});
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
	        <span>财务系统</span>
	        <span>></span>
	        <span class="color99">大型充值</span>
	    </div>
	    
	    <div class="clearFix">
	    	<div class="right-onehalf fl">
		        <jsp:include page="platCommon.jsp"></jsp:include>
		        <input type="button" value=">>" class="expand"/>
		        <div class="expDet">
		        </div>
		        
			</div> 
	    	<div class="right-twoRight fr">
	    		<span class="riqitext" style="">查看历史记录：</span>
		        <div class="riqi" style=""><span>${date}</span> <ul></ul> </div> 
		        <span class="regspan" style="margin-top: 6px;">查看分公司：</span>
				<div class="rspan" style="margin-top: 0px;"><span>${branchsMsg}</span><ul></ul></div>
	    	</div>
		   
		  </div>

	    
		<div class="control">
    		<jsp:include page="attsTabCommon.jsp"></jsp:include>
    	</div>
	    	
	    <div class="custom-scroll thistable" style="overflow:auto; background-color:#fff; ">
		    <p class="fsize18 fweightbold" style="padding-left:10px;">充值明细</p>
	        <table id="searchTable" class="table tablelist">
	            <tr class="firsttr">
	                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
	                <th w_num="line" w_align="center" width="1%;">序号</th>
	                <th w_index="askfees" w_align="center" width="1%;">充值金额</th>
	                <th w_index="payfees" w_align="center" width="1%;">支出金额</th>
	                <th w_edit="createDTs" w_index="createDT" w_align="center" width="1%;">操作时间</th>
	                <th w_align="center">备注</th>
	            </tr>
	        </table>
	        <p class="fsize18 fweightbold" style="padding-left:10px;">年度统计</p>
		     <table class="table" style="margin-bottom:10px;">
			       <tr>
			       		<th style="width:1%">年度充值合计</th>
		                <th style="width:1%">年度支出合计</th>
		                <th style="width:1%">年度结余合计</th>
		            </tr>
		            <tr>
		                <td id="taskfees" style="width:1%"></td>
		                <td id="tpayfees" style="width:1%"></td>
		                <td id="tcashfees" style="width:1%"></td>
		            </tr>
		    </table></br></br>
	        <form action="${pageContext.request.contextPath}/bigtop/save.do" method="post" id="bigtopForm" style="display:none;margin-left:20px;margin-right:20px;">
	            <table class="table">
	            	 <tr>
			        	<td style="text-align: right;">充值金额：<input type="hidden" name="id" id="id"/></td>
			            <td><input name="askfees" id="askfees" style="width:200px;" /><span class="mustred">*</span></td>
			       	</tr>
			    	<tr>
			        	<td style="text-align: right;">充值时间：</td>
			        	<td><input class="easyui-datebox" editable="false" type="text" name="createDT" id="createDT" style="width:200px;"> <span class="mustred">*</span>
			        </tr>
			        <tr>
				        <td colspan="2">
				        	<p style="text-align:center;">
				            	<span class="save okok" style="margin">保存</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="取消">
				            </p>
				        </td>
			        </tr>
	            </table>
	        </form>
	        
	    </div>
    </div>
</body>
</html>