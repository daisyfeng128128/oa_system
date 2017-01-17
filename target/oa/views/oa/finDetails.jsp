<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>财务报表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {height: 100%; min-width: 200px;overflow-x: hidden;position: relative;}
        .yirentable tr {height: 30px;line-height: 30px;}
		.yirentable tr:hover {background-color: #ddd;}
        .yirentable .firsttr:hover { background-color: #fff;}
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
        .thistable{
        	overflow:auto; background-color:#fff;
        }
        .timeOut{
        	overflow:visible;
        }
        /*弹出框上的圈*/
        #gbcounter{
			width: 400px;
			position:absolute;
			left:-300px;
			top:0;
			clip:rect(-4px,400px,103px,300px);
		}
		
		.time_circles {
		    position: absolute;
		    left: -14px;
		    top: -14px;
		}
		.circleBg{
			width: 72px;
		    height: 72px;
		    background-color: #fff;
		    position: absolute;
		    left: 124px;
		    top: -36px;
		    -moz-border-radius: 50%;
		    -webkit-border-radius: 50%;
		    border-radius: 50%;
		}
		
</style>
<script>
    $(function(){
        if("${isEdit}" == "true"){
    		$(".okok").show();
    	}else{
    		$(".okok").hide();
    	}
        var flag = ${isEdit};
        if(!flag){
            $(".check").hide();
        }
        
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
    		url: "${pageContext.request.contextPath}/finSalaries/readDateList.do",
    		data: "",
    		cache: false
    	}).done(function (data) {
    		$(data.data[0]).each(function(index,value){
    			var $self=$(this);
    			var $parent = $(".right-two .spanchange").parent("a");
    			var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
    			$(".right-twoRight .riqi ul").append("<li><a href='"+url+"?date="+value+"&branchs=${branchs}&type=${type}'>"+ value+"</a></li>")
    		});	
    		if(data.data[1].length > 0){
    			$(data.data[1]).each(function(index,value){
	    			var $self=$(this);
	    			$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/financialReports/show.do?date=${date}&type=${type}&branchs="+$self[0].opkey+"'>"+ $self[0].opName +"</a></li>")
	    		});
	    		$(".right-twoRight .regspan").show();
    			$(".right-twoRight .rspan").show();
    		}
    	});
				
        $(".right-twoRight .riqi>span").click(function () {
    		if (!$(".right-twoRight .riqi ul").is(":animated")) {
    			$(".right-twoRight .riqi ul").fadeToggle();
           	}
        });
        $(".right-twoRight .rspan>span").click(function () {
    		if (!$(".right-twoRight .rspan ul").is(":animated")) {
    			$(".right-twoRight .rspan ul").fadeToggle();
           	}
        });
        
        $.ajax({
            type: "get",
            dataType: "json",
    		url: "${pageContext.request.contextPath}/financialReports/readPage.do",
    		data: "date=${date}&branchs=${branchs}",
    	    success:function(data){
    	       if(data != null){
    	          $("#id").val(data.id); 
    	          $("#currentIncome").text(data.currentIncome);
    	          $("#currentExpenditure").text(data.currentExpenditure);
    	          $("#currentProfit").text(data.currentProfit);
    	          $("#lastProfit").text(data.lastProfit);
    	          $("#cp_growthRate").text(data.cp_growthRate+"%");
    			  
    	          $("#onlineActs").text(data.onlineActs);
    	          $("#onlineMans").text(data.onlineMans);
    	          $("#onlineTalent").text(data.onlineTalent);
    	          $("#offlineEmps").text(data.offlineEmps);
    	          $("#allTotal").text(data.allTotal);
    	          $("#financing").text(data.financing);
    	          $("#financingTotal").text(data.financingTotal);
    	          $("#totalOutlays").text(data.totalOutlays);
    	       }
    	    }
    	});
        //平台流水
        var grid = $.fn.bsgrid.init("searchTable", {
            url: "${pageContext.request.contextPath}/months/readAllPlatformsMonths.do?date=${date}&branchs=${branchs}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit: true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                      
                    }
                }
            }
        });
        //大型支出
        var	gd = $.fn.bsgrid.init("gdTable", {
            url: "${pageContext.request.contextPath}/months/readAllMonthsOutlay.do?date=${date}&branchs=${branchs}",
            pageSizeSelect: true,
            pageSize:pageSize,
            pagingToolbarAlign: "left",
            displayBlankRows: false,
            displayPagingToolbarOnlyMultiPages: true
        }); 
        //input四舍五入
        $(".thistable").delegate("input", "change", function () {
            var $self = $(this);
            if ($self.hasClass("dottwo")) {
                $self.val((parseFloat($self.val())).toFixed(2));
            }
        });
		        
        //计算
        $(".check").click(function () {
        	$.yesorno("是否开始计算？", 298, 156, function () {
	        	//ajax
	        	$.ajax({
	                type: "POST",
	                dataType:"json",
	                url: "${pageContext.request.contextPath}/financialReports/check.do",
	                data:"date=${date}&branchs=${branchs}",
	                success:function(){
			        	$.timeOut("计算需耗时约<i>30</i>秒，请稍等后刷新", 320, 166, 30);
			        	/* $(".timeOut").prepend("<div class='circleBg'><div id='gbcounter' data-timer='31'></div>");
			        	$("#gbcounter").TimeCircles({
							circle_bg_color: '#dedede',
							count_past_zero: false,
							bg_width: 0.3,
							fg_width: 0.1,
							time: {
								Seconds: { color: "#0098ed" , text: ""}
							}
						});  */
						
			        	
			        	
	                }
	            })
            });
        });
        
        $("#upLoad").click(function(){    
            window.location.href="${pageContext.request.contextPath}/financialReports/export.do?date=${date}";    
        });
        
        $(".total_click").click(function(){
            document.getElementById('showTable').style.display='';
            document.getElementById('pcontro').style.display='';
			$.basewindow("支出明细", $("#showdetails"), 500, 480);
        });
        
        
        tabSwich(".finanHd",".tBtn","spanchange","#finanTab",".finanbd");
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
	        <span>财务系统</span>
	        <span>></span>
	        <span class="color99">财务报表</span>
	        <div class="fr">
	        	<span id="upLoad">导出Excel</span>
	        </div>	
	    </div>
	    <jsp:include page="finDetailTab.jsp"></jsp:include>
	    <p style="position: fixed; left: 50%; margin-left:-48px; bottom: 1%;" class="control">
	    	<jsp:include page="attsTabCommon.jsp"></jsp:include>
		</p> 
	    <div class="thistable custom-scroll"  id="finanTab">
		     <div class="profit finanbd" style="display:block; ">
		     	<table class="table" style="margin-bottom:10px; ">
			       <tr>
		                <th style="width:1%">本月收款总额</th>
		                <th style="width:1%">本月支出总计</th>
		                <th style="width:1%">本月净利润</th>
		                <th style="width:1%">上月净利润</th>
		                <th style="width:1%">环比增长</th>
		            </tr>
		            <tr>
		                <td id="currentIncome" style="width:1%"><input id="id" type="hidden" /></td>
		                <td id="currentExpenditure" class="total_click" style="width:1%"></td>
		                <td id="currentProfit" style="width:1%"></td>
		                <td id="lastProfit" style="width:1%"></td>
		                <td id="cp_growthRate" style="width:1%"></td>
		            </tr>
		     	</table>
		     </div>
				     
		      <div class="income finanbd" style="overflow:auto; ">
		        <table id="searchTable" class="table tablelist">
		            <tr class="firsttr">
		                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
		                <th w_num="line" w_align="center" width="1%;">序号</th>
		                <th w_index="platforms" w_align="center" width="1%;">平台</th>
		                <th w_index="bgIncome" w_align="center" width="1%;">后台收入</th>
		                <th w_index="genre" w_align="center" width="1%;">收入类型</th>
		                <th w_index="taxSubsidy" w_align="center" width="1%;">税点补贴</th>
		                <th w_index="receivable" w_align="center" width="1%;">平台应收款</th>
		                <th w_index="onlineTotal" w_align="center" width="1%;">线上主播合计</th>
		                <th w_index="offlineTotal" w_align="center" width="1%;">线下主播合计</th>
		                <th w_index="otherIncome" w_align="center" width="1%;">其他收入</th>
		                <th w_index="incomeTotal" w_align="center" width="1%;">应收合计</th>
		                <th w_index="outlayTotal" w_align="center" width="1%;">支出合计</th>
		                <th w_index="netProfit" w_align="center" width="1%;">平台毛利润</th>
		                <th w_index="remarks" w_align="center">备注</th>
		            </tr>
		        </table>
		    </div>
		    
		    <div class="expend finanbd">
		    	<table id="gdTable" class="table tablelist">
		            <tr class="firsttr">
		                <th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
		                <th w_num="line" w_align="center" width="1%;">序号</th>
		                <th w_index="platforms" w_align="center" width="1%;">平台</th>
		                <th w_index="chIncome" w_align="center" width="1%;">频道应收</th>
		                <th w_index="chOutlay" w_align="center" width="1%;">频道支出</th>
		                <th w_index="remarks" w_align="center">备注</th>
		            </tr>
		    	</table>
		    </div>
	    </div>
	    
	    <div id="showdetails">
		    	 <table id="showTable" class="table" style="margin-left: 20px;width: 450px; display: none" >
		            <tr><td colspan="2">工资支出</td></tr>
		            <tr><td style="width:1%">线上艺人</td><td id="onlineActs">0.00</td></tr>
		            <tr><td style="width:1%">线上管理</td><td id="onlineMans">0.00</td></tr>
		            <tr><td style="width:1%">线上星探</td><td id="onlineTalent">0.00</td></tr>
		            <tr><td style="width:1%">所有员工</td><td id="offlineEmps">0.00</td></tr>
		            <tr><td style="width:1%">工资合计</td><td id="allTotal">0.00</td></tr>
		            <tr><td colspan="2">&nbsp;</td></tr>
		            <tr><td style="width:1%">财务支出</td><td id="financing">0.00</td></tr>
		            <tr><td style="width:1%">财务支出合计</td><td id="financingTotal">0.00</td></tr>
		            <tr><td colspan="2">&nbsp;</td></tr>
		            <tr><td style="width:1%;color: red;">公司总计支出</td><td id="totalOutlays">0.00</td></tr>
		        </table>
		         <p  id="pcontro" style="text-align:center; display: none;" ><input class="cancel" type="reset" name="Input" value="关闭"></p>
		    </div>
    </div>
</body>
</html>
