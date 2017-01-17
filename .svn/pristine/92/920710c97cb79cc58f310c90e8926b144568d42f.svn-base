<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>工资统计</title>
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
        .table>tbody>tr>td{
        	border-right:1px solid #ddd;
        }
      
    </style>
    <script>
    $(function(){
        if("${isEdit}" == "true"){
    		$(".okok").show();
    	}else{
    		$(".okok").hide();
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
    		url: "${pageContext.request.contextPath}/finemp/readDateList.do",
    		data: "",
    		cache: false
    	}).done(function (data) {
    		$(data.data).each(function(index,value){
    			var $self=$(this);
    			$(".right-twoRight .riqi ul").append("<li><a href='${pageContext.request.contextPath}/salariesStatistics/show.do?date="+value+"'>"+ value +"</a></li>")
    		});	
    	});
        $(".right-twoRight .riqi>span").click(function () {
    		if (!$(".right-twoRight .riqi ul").is(":animated")) {
    			$(".right-twoRight .riqi ul").fadeToggle();
           	}
        });
        
        $.ajax({
            type: "get",
            dataType: "json",
    		url: "${pageContext.request.contextPath}/salariesStatistics/readPages.do",
    		data: "date=${date}",
    	    success:function(data){
    	       $(".count").text("0");
    	       if(data != null && data.data!= null){
    	          var $data= data.data;
    	          $("#acOff").text($data.acOff); 
    	          $("#acOffTotal").text($data.acOffTotal);
    	          $("#activity").text($data.activity);
    	          $("#activityTotal").text($data.activityTotal);
    	          $("#acOn").text($data.acOn);
    	          $("#acOnTotal").text($data.acOnTotal);
    	          $("#talent").text($data.talent);
    	          $("#talentTotal").text($data.talentTotal);
    	          $("#manOff").text($data.manOff);
    	          $("#manOffTotal").text($data.manOffTotal);
    	          $("#manOn").text($data.manOn);
    	          $("#manOnTotal").text($data.manOnTotal);
    	          $("#development").text($data.development);
    	          $("#developmentTotal").text($data.developmentTotal);
    	          $("#finance").text($data.finance);
    	          $("#financeTotal").text($data.financeTotal);
    	          $("#person").text($data.person);
    	          $("#personTotal").text($data.personTotal);
    	          $("#operate").text($data.operate);
    	          $("#operateTotal").text($data.operateTotal);
    	          $("#total").text($data.total);
    	          $("#totalCount").text($data.totalCount);
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
	        <span>财务系统</span>
	        <span>></span>
	        <span class="color99">工资统计</span>
	    </div>
	   
	   	<div class="right-twoRight clearFix">
	   		<span class="fsize14 tBtn spanchange fl">公司工资统计
    		</span>
	   		<div class="fr">
	   			<span class="riqitext">查看历史记录：</span>
		   		<div class="riqi">
			         <span>${date}</span>
			         <ul></ul>
			     </div> 
	   		</div>
		     
	   	</div>
		
	     <div class="thistable custom-scroll" style="overflow:auto; margin-bottom:10px; width:100%; background-color:#fff; ">
	     	<table class="table">
		       <tr>
	                <th>部门</th>
	                <th>二级部门</th>
	                <th>人数</th>
	                <th>工资支出合计</th>
	            </tr>
	            <tr>
	                <td rowspan="6" >运营部</td>
	                <td>活动部</td>
	                <td id="activity" class="count"></td>
	                <td id="activityTotal" class="count"></td>
	            </tr>
	             <tr>
	                <td>星探部</td>
	                <td id="talent" class="count"></td>
	                <td id="talentTotal" class="count"></td>
	            </tr>
	             <tr>
	                <td>线下艺人</td>
	                <td id="acOff" class="count"></td>
	                <td id="acOffTotal" class="count"></td>
	            </tr>
	            <tr>
	                <td>线上艺人</td>
	                <td id="acOn" class="count" ></td>
	                <td id="acOnTotal" class="count"></td>
	            </tr>
	            <tr>
	                <td >线下管理</td>
	                <td id="manOff" class="count"></td>
	                <td id="manOffTotal" class="count" ></td>
	            </tr>
	            <tr>
	                <td >线上管理</td>
	                <td id="manOn" class="count" ></td>
	                <td id="manOnTotal" class="count" ></td>
	            </tr>
	            <tr>
	                <td colspan="2" style="text-align: center;" >部门合计</td>
	                <td id="operate" class="count"></td>
	                <td id="operateTotal" class="count" ></td>
	            </tr>
	            <tr>
	                <td>研发部</td>
	                <td>-</td>
	                <td id="development" class="count"></td>
	                <td id="developmentTotal" class="count"></td>
	            </tr>
	            <tr>
	                <td>财务部</td>
	                <td>-</td>
	                <td id="currentProfit" class="count"></td>
	                <td id="lastProfit" class="count" ></td>
	            </tr>
	            <tr>
	                <td>人事部</td>
	                <td>-</td>
	                <td id="person" class="count" ></td>
	                <td id="financeTotal" class="count" ></td>
	            </tr>
	            <tr>
	                <td colspan="2" style="text-align: center;" >合计</td>
	                <td id="total" class="count"></td>
	                <td id="totalCount" class="count" ></td>
	            </tr>
	     	</table>
	     </div>

    </div>
</body>
</html>
