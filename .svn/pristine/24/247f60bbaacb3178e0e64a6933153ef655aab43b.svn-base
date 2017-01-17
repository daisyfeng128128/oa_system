<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		if (window.location.href.indexOf("devFinAOff") >= 0) {
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
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/finemp/readDateList.do",
			data: "",
			cache: false
		}).done(function (data) {
			$(data.data).each(function(index,value){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
				$(".right-twohalf .riqi ul").append("<li><a href='"+url+"?date="+value+"'>"+ value+"</a></li>")
			});	
		});

		$(".right-twohalf .riqi>span").click(function() {
			if (!$(".right-twohalf .riqi ul").is(":animated")) {
				$(".right-twohalf .riqi ul").fadeToggle();
			}
		})
	});
</script>
    <div class="right-twohalf">
    <span class="fsize18 fweightbold" style="float:left;margin-right:10px">${vdate}</span>
    <span style="float:left;" class="fsize18 fweightbold" style="margin-right:10px">员工工资（艺人部）  </span>
    <div class="riqi"><span>${date}</span>
    <ul></ul>
	</div>
    <span class="riqitext">查看历史记录：</span>
    </div>
<div class="right-two">
	<a href="${pageContext.request.contextPath}/salaryOffline/devFinAOff.do?date=${date}"><span class="spanchange">线下艺人工资</span></a> 
	<a href="${pageContext.request.contextPath}/salaryOnline/devFinAOnline.do?date=${date}"><span>线上艺人工资</span></a>
	<a href="${pageContext.request.contextPath}/salaryActManager/devFinAman.do?date=${date}"><span>管理工资</span></a>
    <span style="float:right; margin-top:5px;"><input id="fast" type="text" style="width:200px;height:39px;margin-left:-10px;">&nbsp;&nbsp;快速搜索</span>
</div>