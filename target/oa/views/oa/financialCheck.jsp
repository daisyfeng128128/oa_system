<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<title>财务支出</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<style>
body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;width: 100%;}
.table{border:none; }
.table th td {text-align: center;position: relative; }
.table tr td{border:none; color:#333; }
.table input {
	width: 50%;
	min-width:118px;
	height:28px; 
	text-indent:1em; 
	border-color:#ddd; 
	border:1px solid #ddd; 
	border-radius:2px;
	-moz-border-radius:2px;
	-o-border-radius:2px; 

}
.main td{height:46px;}
.mustred{color: red;}
#oneTable{
	float:left; width: 99%; display:block;
}

#oneTable tr{
	border-bottom:1px solid #ddd; 
}
.control{
	margin-left:-116px;
}
.right-twohalf .fr span{
	width:98px; 
}
</style>
<script>
<%
	List<RoleDetailsAtts> platList = (List<RoleDetailsAtts>)request.getAttribute("platList"); 
	Collection<Object> tionList = (Collection<Object>)request.getAttribute("master");
%>
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

        	var onetable = document.getElementById('oneTable');
        	var oneinput = onetable.getElementsByTagName('input');
        	var oneSpan = onetable.getElementsByTagName('span');
        	for(var i = 0; i < oneinput.length; i++) {
        		oneinput[i].parentNode.innerHTML +='<span>'+ oneinput[i].value+'</span>';
        	}
        	
        	var twoTable = document.getElementById('twoTable');
        	var oneinput = twoTable.getElementsByTagName('input');
        	var oneSpan = twoTable.getElementsByTagName('span');
        	for(var i = 0; i < oneinput.length; i++) {
        		oneinput[i].parentNode.innerHTML +='<span>'+ oneinput[i].value+'</span>';
        	}
        	if (window.location.href.indexOf("type=1") >= 0) {
				$(".right-two a span").removeClass("spanchange")
				$(".right-two a span").eq(0).addClass("spanchange")
			} else if (window.location.href.indexOf("type=2") >= 0) {
				$(".right-two a span").removeClass("spanchange")
				$(".right-two a span").eq(1).addClass("spanchange")
			} else{
				$(".right-two a span").removeClass("spanchange")
				$(".right-two a span").eq(0).addClass("spanchange")
			}
		
            $.ajax({
        		type: "POST",
        		url: "${pageContext.request.contextPath}/finSalaries/readDateList.do",
        		data: "",
        		async:false,
        		cache: false
        	}).done(function (data) {
        		$(data.data[0]).each(function(index,value){
        			var $self=$(this);
        			var $parent = $(".right-two .spanchange").parent("a");
    				var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
        			$(".right-twoRight .riqi ul").append("<li><a href='${pageContext.request.contextPath}/finCheck/showOPE.do?branchs=${branchs}&type=${type}&date="+value+"'>"+ value +"</a></li>");
        		});
        		if(data.data[1].length > 0){
	    			$(data.data[1]).each(function(index,value){
	        			var $self=$(this);
	        			$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/finCheck/showOPE.do?date=${date}&type=${type}&branchs="+$self[0].opkey+"'>"+ $self[0].opName +"</a></li>")
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
			            
            $("#oneTable tr td input").hide();
            $("#twoTable tr td input").hide();
            $(".add").css("display","none");
            $(".save").css("display","none");
            
            $(".edit").click(function(){
            	$(".add").css("display","");
            	$(".save").css("display","");
            	$(".edit").css("display","none");
            	$("#oneTable tr td span").remove();
                $("#oneTable tr td input").show();
                $("#twoTable tr td span").remove();
                $("#twoTable tr td input").show();
            });
            
            $(".master input[type='text']").change(function(){
            	if(this.value == "")
            		this.value = "0.00";
            });
			            
          	$("#upLoad").click(function(){    
                window.location.href="${pageContext.request.contextPath}/finCheck/export.do?date=${date}";    
            })
          								
			$("#twoTable tr").click(function(){
				$("#twoTable tr").css("background-color","transparent");
				$(this).css("background-color","#F3F3F3");
			});
			$("#oneTable tr").click(function(){
				$("#oneTable tr").css("background-color","transparent");
				$(this).css("background-color","#F3F3F3");
			});
									
		    //添加
            $(".add").on('click',function () {
                var $chuangkou = $("#finacialForm");
                $.basewindow("新增支出项目", $chuangkou, 390, 238);
                $(".basewindow").addClass("custom-scroll");
        	});
			
			//添加支出保存数据
			$("#finacialForm .okok").on('click',function(){
				var $name = $("#name").val();
				var $subjects = $("#subjects").val();
				if($name == ""){
					$.threetop("支出项目名称不能为空!");
					return false;
				}
				$.ajax({
	                type: "POST",
	                url: "${pageContext.request.contextPath}/finCheck/add.do",
	                data:"name="+$name+"&subjects="+$subjects+"&date=${date}",
	                cache: false,
	                async:false,
	                success:function(data){
	                	if(data.resultStatus == 200){
	                	}else{
	                		$.threetop("添加项目重复");
	                	}
	                }
	            });	
			});
			
			$(".save").click(function(){
				 var data = [];
				 $(".master").each(function (){
				 	var $self = $(this);
				 	var ds = {
				 		id: $self.find("td:eq(0)").attr("class"),
				 		number: JSON.stringify({
				 			<%  
				 			int findex = 1;
							for(RoleDetailsAtts $atts : platList){
								String id = $atts.getOpkey();
								String kid = "v"+id;
								%>
								<%=kid %> : $self.find("td:eq(<%=findex++ %>) input").val(),
								<%								
							}
							%>
							guanli : $self.find("td:eq(<%=findex %>) input").val()
							<%%>
					 	}),
				 		remark : $self.find(".remark").val(),
				 		date : "${date}"
				 	}
				 	data.push(ds);
				});
				$.ajax({
	                type: "POST",
	                url: "${pageContext.request.contextPath}/finCheck/save.do",
	                data:JSON.stringify(data),
	                cache: false,
	                contentType: 'application/json; charset=utf-8',
	                success:function(){
	                    $.threesecond("保存成功", 200, 100, 1000);
	                   	window.location.href="${pageContext.request.contextPath}/finCheck/showOPE.do?date=${date}";
	                }
	            });	
	            
	            data = [];
	            $(".other").each(function (){
				 	var $self = $(this);
				 	var ds = {
				 		id: $self.find("td:eq(0)").attr("class"),
				 		number: JSON.stringify({
					 		fees : $self.find("td:eq(1) input").val()
					 	}),
				 		remark : $self.find(".remark").val(),
				 		date : "${date}"
				 	}
				 	data.push(ds);
				});
	            $.ajax({
	                type: "POST",
	                url: "${pageContext.request.contextPath}/finCheck/save.do",
	                data:JSON.stringify(data),
	                cache: false,
	                contentType: 'application/json; charset=utf-8',
	                success:function(){
	                    $.threesecond("保存成功", 200, 100, 1000);
	                   	window.location.href="${pageContext.request.contextPath}/finCheck/showOPE.do?date=${date}";
	                }
	            });	
	            						  
			});
			tabSwich(".expendHd",".tBtn","spanchange",".expendBd",".expendBdItem")
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
			<span class="color99">财务支出</span>
	    </div>
	    <div class="right-two expendHd">
	    	<a href="${pageContext.request.contextPath}/finCheck/showOPE.do?type=1&date=${date}&branchs=${branchs}"><span class="spanchange tBtn">主营业务支出</span></a>
			<a href="${pageContext.request.contextPath}/finCheck/showOPE.do?type=2&date=${date}&branchs=${branchs}"><span class="tBtn">其他财务支出</span></a>
		   	<div class="right-twoRight fr">
		   		<span class="riqitext">查看历史记录：</span>
	        	<div class="riqi"><span>${date}</span> <ul></ul> </div> 
		    	<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
		    </div>
		    
		</div>
	    
	    <p class="control"><jsp:include page="attsTabCommon.jsp"></jsp:include></p>
		
		<div class="thistable custom-scroll expendBd" style="overflow:auto; background-color:#fff; ">
			<table class="table main1 expendBdItem" id="oneTable">
				<tr>
					<th style="width:1%;">项目/平台</th>
					<c:forEach var="plat" items="${platList}">
						<th style="width:1%;">${plat.opName}</th>
					</c:forEach>
					<th style="width:1%;">管理费用</th>
					<th style="width:1%;">合计</th>
					<th>备注</th>
				</tr>
					<%
					int i = 0;		
					for(Object o : tionList){
						%><tr style="height:46px;" class="master"><%
						JSONObject v = new JSONObject();
						String name = String.valueOf("");
						String remark = String.valueOf("");
						String twoSubject = String.valueOf("");
						HashMap<String,String> hash = (HashMap<String,String>)o;
						for(Iterator<String> it = hash.keySet().iterator();it.hasNext();){
							String key = it.next();
							Object value = hash.get(key);
							if("name".equals(key) && value != null)
								name = value.toString();
							if("remark".equals(key) && value != null)
								remark = value.toString();
							if("twoSubject".equals(key) && value != null)
								twoSubject = value.toString();
							if("v".equals(key) && value != null)
								v = (JSONObject)value;
						}
						%>
						<td class="<%=name %>"><%=twoSubject %></td>
						<%
						for(RoleDetailsAtts atts : platList){
							String values = String.valueOf("");
							if(v.get("v"+atts.getOpkey()) != null)
								values = (String)v.get("v"+atts.getOpkey());
							%><td><input type="text" class="num c<%=atts.getOpkey() %>" value="<%=values %>"></td><%			
						}
						String manager = String.valueOf("");
						if(v.get("guanli") != null)
							manager = (String)v.get("guanli");
						String total = String.valueOf("");
						if(v.get("total") != null)
							total = (String)v.get("total");		
						%>
						<td><input type="text" class="num guanli" value="<%=manager %>"></td>
						<td><input type="text" class="num total" value="<%=total %>"></td>
						<td>&nbsp;</td>
						<%
						%></tr><%
					}
					%>
				<tr style="height:46px;" class="two">
					<td width="181px" style="height:46px;font-weight:bold;" class="tohej">总合计：</td>
					<td width="141px" style="height:46px;font-weight:bold;" colspan="<%=platList.size() + 1 %>">&nbsp;</td>
					<td width="1268px" style="text-align: left; height:46px;font-weight:bold;">${masterTotal}</td>
					<td width="141px" style="height:46px;font-weight:bold;">&nbsp;</td>
				</tr>
				<tr style="height:46px;" class="two">
					<td style="height:46px;font-weight:bold;" class="tohej">总合计(不含工资)：</td>
					<td style="height:46px;font-weight:bold;" colspan="<%=platList.size() + 1%>">&nbsp;</td>
					<td style="text-align: left; height:46px;font-weight:bold;">${masterTotal_salaries}</td>
					<td style="height:46px;font-weight:bold;">&nbsp;</td>
				</tr>
			</table>
			<table class="table main2 expendBdItem" id="twoTable" style="float:left; width: 99%;">
				<tr>
					<th style="width:1%;">项目/平台</th>
					<th style="width:1%;">金额</th>
					<th>备注</th> 
				</tr>
				<c:forEach var="one" items="${other}">
					<tr style="height:46px;" class="other">
						<td class="${one.name}">${one.twoSubject}</td>
						<td><input type="text" class="num fees" value="${one.v.fees}"></td>
						<td style="text-align: left; height:46px;"><input class="remark ${one.name}Remark" value="${one.remark}"></td>
					</tr>
				</c:forEach>
				<tr style="height:46px;" class="two">
					<td width="181px" style="height:46px;font-weight:bold;" class="tohej">总合计：</td>
					<td width="141px" style="height:46px;font-weight:bold;">${otherTotal}</td>
					<td width="1268px"  style="text-align: left; height:46px;">&nbsp;</td>
				</tr>
			</table>
			
			<form id="finacialForm" style="display:none;margin-left:20px;margin-right:20px;">
		    <table class="table">
		    	 <tr>
		        	<td style="text-align: right;">项目名称：</td>
		            <td><input name="name" id="name" style="width:200px;" /><span class="mustred">*</span></td>
		       	</tr>
		    	<tr>
		        	<td style="text-align: right;">科目：</td>
		            <td>
		            	<select name="subjects" id="subjects" style="width:200px; margin-left: -6px; " maxlength="400">
							<option value="master">主营业务支出</option>            	
							<option value="other">其他财务支出</option>            	
		            	</select>
		            </td>
		        </tr>
		        <tr>
			        <td colspan="4">
			        	<p class="bCenter"><input type="hidden" name="id" id="id">
			            	<span class="save okok">保存</span>
			            	<span class="cancel">取消</span>
			            </p>
			        </td>
		        </tr>
		    </table>
		    </form>
			
	    </div>
	</div>
</body>
</html>