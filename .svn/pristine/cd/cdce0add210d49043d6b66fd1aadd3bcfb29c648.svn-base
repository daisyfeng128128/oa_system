<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		if (window.location.href.indexOf("adjust=UNKNOWN") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("adjust=FAILED") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(1).addClass("spanchange")
		}
		$(".right-twohalf .riqi>span").click(function() {
			if (!$(".right-twohalf .riqi ul").is(":animated")) {
				$(".right-twohalf .riqi ul").fadeToggle();
			}
		});
		$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
    		data: "",
   			cache: false
   		}).done(function (data) {
   			if(data.data.length > 0){
   				$(data.data).each(function(index,value){
	   				var $self = $(this);
					var url = window.location.href;
					if(url.indexOf("?") > 0){
						url = url.substring(0,url.indexOf("?"));
					}
					$(".right-two .rspan ul").append("<li><a href='"+url+"?adjust=${adjust}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>");   				
	   			});
	   			$(".right-two .regspan").show();
   				$(".right-two .rspan").show();
   			}
   		});
        $(".right-two .rspan>span").click(function(){
			if (!$(".right-two .rspan ul").is(":animated")) {
				$(".right-two .rspan ul").fadeToggle();
	       	}
	    });		
	});
</script>
<div class="right-twohalf">
	<span>首页</span> 
	<span>></span>
	<span>平台人员</span> 
	<span>></span>
	<span class="color99">薪资调整(艺人)</span>
	
</div>
<div class="right-two" style="margin-right:10px;">
	<a href="${pageContext.request.contextPath}/adjusts/show.do?adjust=UNKNOWN"><span class="spanchange tBtn">调薪申请</span></a>
	<a href="${pageContext.request.contextPath}/adjusts/show.do?adjust=FAILED"><span class="tBtn">已完成</span></a>
	<div class="rspan" style=" height: 30px;display: none; float:right; "><span>${branchsMsg}</span><ul></ul></div>
	<span class="regspan" style=" display: none; float:right; ">查看分公司：</span>
</div>