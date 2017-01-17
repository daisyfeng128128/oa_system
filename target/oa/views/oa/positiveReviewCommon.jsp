<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		if (window.location.href.indexOf("empType=1") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("empType=2") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(1).addClass("spanchange")
		} else{
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		}
		$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
    		data: "",
    		cache: false
    	}).done(function (data) {
    		if(data.data.length > 0){
    			$(data.data).each(function(index,value){
	    			var $self=$(this);
	    			$(".right-two .rspan ul").append("<li><a href='${pageContext.request.contextPath}/positiveReview/show.do?empType=${empType}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>");
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