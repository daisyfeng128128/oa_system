<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		if (window.location.href.indexOf("advance=UNKNOWN") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("advance=PASSED") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(1).addClass("spanchange")
		}else if (window.location.href.indexOf("advance=FAILED") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(2).addClass("spanchange")
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
					$(".right-twohalf .rspan ul").append("<li><a href='"+url+"?advance=${advance}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")   				
	   			});
	   			$(".right-twohalf .regspan").show();
   				$(".right-twohalf .rspan").show();
   			}
   		});
        $(".right-twohalf .rspan>span").click(function(){
			if (!$(".right-twohalf .rspan ul").is(":animated")) {
				$(".right-twohalf .rspan ul").fadeToggle();
	       	}
	    });		
	});
</script>
<div class="right-two" style="margin-right:10px;">
	<a href="${pageContext.request.contextPath}/advance/show.do?advance=UNKNOWN"><span class="spanchange tBtn">借支申请</span></a>
	<a href="${pageContext.request.contextPath}/advance/show.do?advance=PASSED"><span class="tBtn">还款</span></a>
	<a href="${pageContext.request.contextPath}/advance/show.do?advance=FAILED"><span class="tBtn">已拒绝</span></a>	

	<div class="right-twohalf fr">
		<span class="regspan">查看分公司：</span>
		<div class="rspan">
			<span>${branchsMsg}</span>
			<ul></ul>
		</div>
	</div>

</div>