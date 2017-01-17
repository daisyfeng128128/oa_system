<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script>
	$(function() {
		if (window.location.href.indexOf("type=off") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("type=on") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(1).addClass("spanchange")
		} 
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/empAttendance/readDateList.do",
			data: "",
			cache: false
		}).done(function (data) {
			$(data.data[0]).each(function(index,value){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("&"));
				$(".right-twoRight .riqi ul").append("<li><a href='"+url+"&date="+value+"&branchs=${branchs}'>"+ value+"</a></li>")
			});	
			if(data.data[1].length > 0){
				$(data.data[1]).each(function(index,value){
					var $self=$(this);
					var $parent = $(".right-two .spanchange").parent("a");
					var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("&"));
					$(".right-twoRight .rspan ul").append("<li><a href='"+url+"&date=${date}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
				});
				$(".right-twoRight .regspan").show();
				$(".right-twoRight .rspan").show();
			}
		});
		$(".right-twoRight .riqi>span").click(function() {
			if (!$(".right-twoRight .riqi ul").is(":animated")) {
				$(".right-twoRight .riqi ul").fadeToggle();
			}
		});
		 $(".right-twoRight .rspan>span").click(function(){
			if (!$(".right-twoRight .rspan ul").is(":animated")) {
				$(".right-twoRight .rspan ul").fadeToggle();
			}
		});
		
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
		/** 导出excel */
		/* $("#upLoad").click(function(){    
            window.location.href="${pageContext.request.contextPath}/finSalaries/exportExcel.do?date=${date}";    
        }) */
	});
</script>
    <div class="right-twohalf">
    	<span>首页</span> 
		<span>></span>
		<span>直播系统</span> 
		<span>></span>
		<span class="color99">收益明细</span>
    </div>
<div class="right-two">
	<a href="${pageContext.request.contextPath}/finEarningsIncome/show.do?type=off&date=${date}&branchs=${branchs}"><span class="spanchange tBtn">线下艺人</span></a>
	<a href="${pageContext.request.contextPath}/finEarningsIncome/show.do?type=on&date=${date}&branchs=${branchs}"><span class="tBtn">线上艺人</span></a>
	
	<div class="right-twoRight fr">
  		<span class="riqitext">查看历史记录：</span>
    	<div class="riqi"><span>${date}</span><ul></ul></div>
    	<span class="regspan">查看分公司：</span>
		<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
   	</div>
   <!--  <span style="float:right; margin-top:5px;"><input id="fast" type="text" style="width:200px;height:39px;margin-left:-10px;">&nbsp;&nbsp;快速搜索</span>
    <span style="float:right; margin-top:5px;"  id="upLoad">&nbsp;&nbsp;&nbsp;导出Excel</span> -->
</div>