<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		if (window.location.href.indexOf("show") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("devFinAOnline") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(1).addClass("spanchange")
		} else if (window.location.href.indexOf("devFinAman") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(2).addClass("spanchange")
		} else if (window.location.href.indexOf("devFinATM") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(3).addClass("spanchange")
		} else if (window.location.href.indexOf("devOther") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(3).addClass("spanchange")
		}
	});
</script>

<div class="right-two">
	<a href="${pageContext.request.contextPath}/branchLogin/show.do"><span class="spanchange tBtn">分公司登陆表</span></a> 
</div>