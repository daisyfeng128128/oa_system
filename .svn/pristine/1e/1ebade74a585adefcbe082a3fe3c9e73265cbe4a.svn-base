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
    		url: "${pageContext.request.contextPath}/finSalaries/readDateList.do",
    		data: "",
    		cache: false
    	}).done(function (data) {
    		$(data.data).each(function(index,value){
    			var $self=$(this);
    			$(".right-twohalf .riqi ul").append("<li><a href='${pageContext.request.contextPath}/financialReports/showOPE.do?date="+value+"'>"+ value +"</a></li>")
    		});	
    	});
        $(".right-twohalf .riqi>span").click(function () {
    		if (!$(".right-twohalf .riqi ul").is(":animated")) {
    			$(".right-twohalf .riqi ul").fadeToggle();
           	}
        });
        
        $.ajax({
            type: "get",
            dataType: "json",
    		url: "${pageContext.request.contextPath}/financialReports/readPage.do",
    		data: "date=${date}",
    	    success:function(data){
    	       if(data != null){
    	          $("#id").val(data.id); 
    	          $("#richang").numberbox("setValue",data.richang);
    	          $("#office").numberbox("setValue",data.office);
    	          $("#travel").numberbox("setValue",data.travel);
    	          $("#social").numberbox("setValue",data.social);
    	          $("#national").numberbox("setValue",data.national);
    	          $("#technology").numberbox("setValue",data.technology);
    	          $("#rent").numberbox("setValue",data.rent);
    	          $("#water").numberbox("setValue",data.water);
    	          $("#docorate").numberbox("setValue",data.docorate);
    	          $("#make").numberbox("setValue",data.make);
    	          $("#other").numberbox("setValue",data.other);
    	          
    	          $("#richangRemark").val(data.richangRemark);
    	          $("#officeRemark").val(data.officeRemark);
    	          $("#travelRemark").val(data.travelRemark);
    	          $("#socialRemark").val(data.socialRemark);
    	          $("#nationalRemark").val(data.nationalRemark);
    	          $("#technologyRemark").val(data.technologyRemark);
    	          $("#rentRemark").val(data.rentRemark);
    	          $("#docorateRemark").val(data.docorateRemark);
    	          $("#makeRemark").val(data.makeRemark);
    	          $("#waterRemark").val(data.waterRemark);
    	          $("#otherRemark").val(data.otherRemark);
    	          $("#total").text(data.total);
    	       }
    	    }
    	});
        //提交
        $(".save").click(function () {
              var richang =$("#richang").val();
              var office =$("#office").val();
              var travel =$("#travel").val();
              var social =$("#social").val();
              var national =$("#national").val();
              var technology =$("#technology").val();
              var rent =$("#rent").val();
              var water =$("#water").val();
              var docorate =$("#docorate").val();
              var make =$("#make").val();
              var other =$("#other").val();
              var richangRemark =$("#richangRemark").val();
              var officeRemark =$("#officeRemark").val();
              var socialRemark =$("#socialRemark").val();
              var nationalRemark =$("#nationalRemark").val();
              var technologyRemark =$("#technologyRemark").val();
              var rentRemark =$("#rentRemark").val();
              var docorateRemark =$("#docorateRemark").val();
              var makeRemark =$("#makeRemark").val();
              var travelRemark =$("#travelRemark").val();
              var waterRemark =$("#waterRemark").val();
              var otherRemark =$("#otherRemark").val();
              var id = $("#id").val();
			 //ajax
			 
			 var data = {
				id:id,
				details:[{name:"richang",expenditure:richang,remarks:richangRemark},
				         {name:"office",expenditure:office,remarks:officeRemark},
				         {name:"travel",expenditure:travel,remarks:travelRemark},
				         {name:"social",expenditure:social,remarks:socialRemark},
				         {name:"national",expenditure:national,remarks:nationalRemark},
				         {name:"technology",expenditure:technology,remarks:technologyRemark},
				         {name:"rent",expenditure:rent,remarks:rentRemark},
				         {name:"docorate",expenditure:docorate,remarks:docorateRemark},
				         {name:"make",expenditure:make,remarks:makeRemark},
				         {name:"water",expenditure:water,remarks:waterRemark},
				         {name:"other",expenditure:other,remarks:otherRemark}]
			 }
			 
			 if(id != null){
			     $.ajax({
		                type: "POST",
		                url: "${pageContext.request.contextPath}/financialReports/save.do",
		                data:JSON.stringify(data),
		                cache: false,
		                contentType: 'application/json; charset=utf-8',
		                success:function(){
		                    $.threesecond("保存成功", 200, 100, 1000);
		                   	window.location.reload();
		                }
		            })
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
	    	<span style="margin-left:20px;">首页</span>
	        <span>></span>
	        <span>财务系统</span>
	        <span>></span>
	        <span class="color99">财务报表</span>
	        <div class="fr">
	        	<span id="upLoad">导出Excel</span>
	        </div>
	    </div>
	    <div class="right-twoRight fr">
	    	<span class="riqitext">查看历史记录：</span>
	    	<div class="riqi">
	            <span>${date}</span>
	            <ul></ul>
	        </div> 
	    	<span class="fsize18 fweightbold" style="float:left;margin-right:10px">${vdate}</span><span style="float:left;" class="fsize18 fweightbold" style="margin-right:10px">运营支出 </span>
	        
	    </div>
	    <div class="thistable custom-scroll" style="overflow:auto;">
	         <p class="fsize18 fweightbold"  style="padding-left:10px;">公司基本运营支出</p>
	       <table class="table">
	            <input type= "hidden" id= "id" >
	            <tr class="nolist firsttr">
	                <th style="width:1%">编号</th>
	                <th style="width:1%">项目内容</th>
	                <th style="width:1%">支出金额</th>
	                <th>备注</th>
	            </tr>
	            <tr>
	                <td style="width:1%">1</td>
	                <td style="width:1%">公司日常</td>
	                <td style="width:1%"><input id="richang" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="richangRemark" type="text" style="width:95%"/></td>
	            </tr>
	            <tr>
	                <td style="width:1%">2</td>
	                <td style="width:1%">办公用品</td>
	                <td style="width:1%"><input id="office" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id = "officeRemark" type="text" style="width:95%" /></td>
	            </tr>
	            <tr>
	                <td style="width:1%">3</td>
	                <td style="width:1%">差旅招待费用</td>
	                <td style="width:1%"><input id= "travel" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id= "travelRemark" type="text"  style="width:95%" /></td>
	            </tr>
	            <tr>
	                <td style="width:1%">4</td>
	                <td style="width:1%">社保公积金</td>
	                <td style="width:1%"><input id="social" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id= "socialRemark" type="text"  style="width:95%"/></td>
	            </tr>
	            <tr>
	                <td style="width:1%">5</td>
	                <td style="width:1%">国税</td>
	                <td style="width:1%"><input id="national" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="nationalRemark" type="text"  style="width:95%"/></td>
	            </tr>
	            <tr>
	                <td style="width:1%">6</td>
	                <td style="width:1%">技术工资</td>
	                <td style="width:1%"><input id="technology" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="technologyRemark" type="text"  style="width:95%"/></td>
	            </tr>
	              <tr>
	                <td style="width:1%">7</td>
	                <td style="width:1%">房租</td>
	                <td style="width:1%"><input id="rent" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="rentRemark" type="text"  style="width:95%"/></td>
	            </tr>
	              <tr>
	                <td style="width:1%">8</td>
	                <td style="width:1%">水电</td>
	                <td style="width:1%"><input id="water" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="waterRemark" type="text"  style="width:95%"/></td>
	            </tr>
	              <tr>
	                <td style="width:1%">9</td>
	                <td style="width:1%">直播间装修</td>
	                <td style="width:1%"><input id="docorate" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="docorateRemark" type="text"  style="width:95%"/></td>
	            </tr>
	              <tr>
	                <td style="width:1%">10</td>
	                <td style="width:1%">节目制作</td>
	                <td style="width:1%"><input id="make" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="makeRemark" type="text"  style="width:95%"/></td>
	            </tr>
	            <tr>
	                <td style="width:1%">11</td>
	                <td style="width:1%">其他</td>
	                <td style="width:1%"><input id="other" class="easyui-numberbox input" precision="2" type="text" /></td>
	                <td><input id="otherRemark" type="text"  style="width:95%"/></td>
	            </tr>
	            
	            <tr class="nolist all">
	                <td colspan="2" style="width:1%">合计</td>
	                <td id="total" style="width:1%"></td>
	                <td></td>
	            </tr>
	            <tr class="nolist">
	                <td colspan="4">
	                    <jsp:include page="attsTabCommon.jsp"></jsp:include>
	                </td>
	            </tr>
	        </table>
	    </div>
	    
	   </div>
    </div>
    
   
</body>
