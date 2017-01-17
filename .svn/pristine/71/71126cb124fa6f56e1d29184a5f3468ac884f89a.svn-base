<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		if (window.location.href.indexOf("type=1") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("type=2") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(1).addClass("spanchange")
		}else if (window.location.href.indexOf("type=3") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(2).addClass("spanchange")
		} else{
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		}
	});
</script>
<div class="right-two">
   	<a href="${pageContext.request.contextPath}/financialReports/show.do?type=1&date=${date}&branchs=${branchs}"><span class="spanchange tBtn">公司盈利总计</span></a>
	<a href="${pageContext.request.contextPath}/financialReports/show.do?type=2&date=${date}&branchs=${branchs}"><span class="tBtn">平台应收概况表</span></a>
	<a href="${pageContext.request.contextPath}/financialReports/show.do?type=3&date=${date}&branchs=${branchs}"><span class="tBtn">平台大型支出表</span></a>
   	<div class="right-twoRight fr">
    	<span class="riqitext">查看历史记录：</span>
    	<div class="riqi"><span>${date}</span> <ul></ul> </div> 
    	<span class="regspan">查看分公司：</span>
		<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
    </div>
</div>