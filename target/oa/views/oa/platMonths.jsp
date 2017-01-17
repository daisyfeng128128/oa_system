<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>平台应收</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {height: 100%; min-width: 200px;overflow-x: hidden;position: relative;}
        .yirentable tr {height: 30px;line-height: 30px;}
        .yirentable tr:hover {background-color: #ddd;}
        .yirentable .firsttr:hover {background-color: #fff;}
        .yirentable .selected {background-color: #ddd;}
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
        
            var isInput = ${flag};
            var isEdit ="";
            if(!isInput){
                $("#tab").hide();
            }
            var loadUrl = "${pageContext.request.contextPath}/months/readAllPlatformsMonths.do?date=${date}&branchs=${branchs}";
            var grid = $.fn.bsgrid.init("searchTable", {
                url: loadUrl,
                pageSizeSelect: true,
                pageSize:pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true
            });
            
            var	gd = $.fn.bsgrid.init("gdTable", {
            	url: loadUrl,
                pageSizeSelect: true,
                pageSize:pageSize,
                pagingToolbarAlign: "left",
                displayBlankRows: false,
                displayPagingToolbarOnlyMultiPages: true,
                extend: {
                	settings: {
                    	supportGridEdit: isInput,
                    	supportGridEditTriggerEvent: "",
                    	gridEditConfigs: {
                               chIncome: {
                                   build: function (edit, value) {
                                       return value + '<input class="dottwo chIncome"  style="width:100px;" value="' + value + '"  />';
                                   }
                               },chOutlay: {
                                   build: function (edit, value) {
                                       return value + '<input class="dottwo chOutlay"  style="width:100px;" value="' + value + '"  />';
                                   }
                               },remarks:{
                                   build : function (edit,value){
                                       return value + '<input class="remarks"  style="width:100%;" value="' + value + '"  />';
                                   }
                               }
                               
                           }
                       }
                   }
            });
             
          	var flag = ${isSee};
			if(!flag){
			    document.getElementById("gdTable").style.display="none";
			}               
            //input四舍五入
            $(".thistable").delegate("input", "change", function () {
                var $self = $(this);
                if ($self.hasClass("dottwo")) {
                    $self.val((parseFloat($self.val())).toFixed(2));
                }
            })
            //提交
            $(".save").click(function () {
                var json=[];
                if(flag){
                    if(gd.getChangedRowsIndexs()!=''){
                        $(gd.getChangedRowsIndexs()).each(function(){
                            var self=this;
                            var data={
                                id:$(".thistable tbody tr").eq(self).find("td").eq(0).text(),
                                chIncome:$("#gdTable tbody tr").eq(self).find(".chIncome").val(),
                                chOutlay: $("#gdTable tbody tr").eq(self).find(".chOutlay").val(),
                                remarks :$("#gdTable tbody tr").eq(self).find(".remarks").val()
                            }
                            json.push(data);
                        })
    					  $.ajax({
    	                    type: "POST",
    	                    url: "${pageContext.request.contextPath}/months/saveTop.do",
    	                    data:JSON.stringify(json),
    	                    cache: false,
    	                    contentType: 'application/json; charset=utf-8',
    	                    success:function(){
    	                        $.threesecond("保存成功", 200, 100, 1000);
    	                        window.location.reload();
    	                   	 }
                    	})
    				}
                }
            });
            $(".right-twohalf .riqi>span").click(function () {
				if (!$(".right-twohalf .riqi ul").is(":animated")) {
					$(".right-twohalf .riqi ul").fadeToggle(); 
		       	}
		    });
		    $(".right-twohalf .rspan>span").click(function(){
				if (!$(".right-twohalf .rspan ul").is(":animated")) {
					$(".right-twohalf .rspan ul").fadeToggle();
		       	}
		    });
            $.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/outlay/readBaseTabCommon.do",
				data: "platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}",
				cache: false
			}).done(function (data) {
				$(data.data[0]).each(function(){
					var $self=$(this);
					$(".right-twohalf .riqi ul").append("<li><a href='${pageContext.request.contextPath}/months/show.do?platId=${platId}&branchs=${branchs}&date="+$self[0].resultMessage+"'>"+ $self[0].resultMessage+"</a></li>")
				});
				if(data.data[1].length > 0){
					$(data.data[1]).each(function(){
						var $self=$(this);
						$(".right-twohalf .rspan ul").append("<li><a href='${pageContext.request.contextPath}/months/show.do?platId=${platId}&date=${date}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
					});
					$(".right-twohalf .regspan").show();
					$(".right-twohalf .rspan").show();				
				}
				
			}).error(function (jqXHR, textStatus, errorThrown) {});     
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
	    <div class="right-twohalf" style="margin-bottom:10px">
	    	<span>首页</span> 
			<span>></span>
			<span>直播系统</span> 
			<span>></span>
			<span class="color99">平台应收</span>
	        <div class="clearFix">
	        	<div class="right-twoRight fr">
		        	<span class="riqitext">查看历史记录：</span>
			        <div class="riqi"><span>${date}</span><ul></ul></div>
			        <span class="regspan">查看分公司：</span>
					<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
		        </div>
	        </div>
	    </div>
	    <div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
	        <table id="searchTable" class="table tablelist">
	            <tr class="firsttr">
	                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
	                <th w_num="line" w_align="center" width="1%;">序号</th>
	                <th w_index="platforms" w_align="center" width="1%;">平台</th>
	                <th w_index="genre" w_align="center" width="1%;">收入类型</th>
	                <th w_index="receivable" w_align="center" width="1%;">平台应收款</th>
	                <th w_index="onlineTotal" w_align="center" width="1%;">线上主播合计</th>
	                <th w_index="offlineTotal" w_align="center" width="1%;">线下主播合计</th>
	                <th w_index="otherIncome" w_align="center" width="1%;">其他收入</th>
	                <th w_index="incomeTotal" w_align="center" width="1%;">应收合计</th>
	                <th w_index="outlayTotal" w_align="center" width="1%;">支出合计</th>
	                <th w_index="netProfit" w_align="center" width="1%;">平台毛利润</th>
	                <th w_edit="chIncome" w_index="chIncome" w_align="center" width="1%;">频道应收</th>
	                <th w_edit="chOutlay" w_index="chOutlay" w_align="center" width="1%;">频道支出</th>
	                <th w_edit="remarks" w_index="remarks" w_align="center">备注</th>
	            </tr>
	            <tfoot>
			        <tr>
			            <td class="aggLabel">合计:</td>
			            <td class="aggLabel"></td>
			            <td class="aggLabel"></td>
			            <td class="aggLabel" w_agg="sum,receivable"></td>
			            <td class="aggLabel" w_agg="sum,onlineTotal"></td>
			            <td class="aggLabel" w_agg="sum,offlineTotal"></td>
			            <td class="aggLabel" w_agg="sum,otherIncome"></td>
			            <td class="aggLabel" w_agg="sum,incomeTotal"></td>
			            <td class="aggLabel" w_agg="sum,outlayTotal"></td>
			            <td class="aggLabel"w_agg="sum,netProfit"></td>
			        </tr>
			    </tfoot>
	        </table>
	        </br>
	        </br>
	        <!-- <table id="gdTable" class="table tablelist">
	            <tr class="firsttr">
	                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
	                <th w_num="line" w_align="center" width="1%;">序号</th>
	                <th w_index="platforms" w_align="center" width="1%;">平台</th>
	                <th w_edit="chIncome" w_index="chIncome" w_align="center" width="1%;">频道应收</th>
	                <th w_edit="chOutlay" w_index="chOutlay" w_align="center" width="1%;">频道支出</th>
	                <th w_edit="remarks" w_index="remarks" w_align="center">备注</th>
	            </tr>
	            <tfoot>
			        <tr>
			            <td class="aggLabel">合计:</td>
			            <td class="aggValue"></td>
			            <td class="aggLabel" w_agg="sum,chIncome"></td>
			            <td class="aggLabel" w_agg="sum,chOutlay"></td>
			            <td></td>
			        </tr>
		    	</tfoot>
	        </table>  -->
	        <p id="tab" class="control"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
	    </div>
    </div>
</body>
</html>