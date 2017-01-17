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
		} else if (window.location.href.indexOf("type=3") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(2).addClass("spanchange")
		} else if (window.location.href.indexOf("type=4") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(3).addClass("spanchange")
		}else if (window.location.href.indexOf("type=5") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(4).addClass("spanchange")
		}else{
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		}
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/finSalaries/readDateList.do",
			data: "",
			cache: false
		}).done(function (data) {
			$(data.data[0]).each(function(index,value){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("&"));
				$(".right-twoRight .riqi ul").append("<li><a href='"+url+"&date="+value+"'>"+ value+"</a></li>")
			});	
		});

		$(".right-twoRight .riqi>span").click(function() {
			if (!$(".right-twoRight .riqi ul").is(":animated")) {
				$(".right-twoRight .riqi ul").fadeToggle();
			}
		})
		/** 导出excel */
		$("#upLoad").click(function(){    
            window.location.href="${pageContext.request.contextPath}/finSalaries/exportExcel.do?date=${date}";    
        })
	});
</script>

<div class="right-two">
	<a href="${pageContext.request.contextPath}/finSalaries/actshow.do?type=1&date=${date}"><span class="spanchange tBtn">频道管理(线下)</span></a>
	<a href="${pageContext.request.contextPath}/finSalaries/actshow.do?type=2&date=${date}"><span class="tBtn">艺人主播(线下)</span></a>
	<a href="${pageContext.request.contextPath}/finSalariesOnline/show.do?type=3&date=${date}"><span class="tBtn">频道管理(线上)</span></a>
	<a href="${pageContext.request.contextPath}/finSalariesOnline/show.do?type=4&date=${date}"><span class="tBtn">艺人主播(线上)</span></a> 
	<a href="${pageContext.request.contextPath}/finSalariesTalent/show.do?type=5&date=${date}"><span class="tBtn">星探工资</span></a> 
   	<span id="searchAll" class ="searchAll">高级搜索</span>
   	<div class="right-twoRight fr">
  		<span class="riqitext">查看历史记录：</span>
   		<div class="riqi">
   			<span>${date}</span>
   			<ul></ul>
		</div>
   		<div class="search">
    		<input id="fast" type="text" placeholder="姓名搜索"/>
    		<button type="button" id="seach" class="tableok" >搜索</button>
	    </div>
   	</div>
    
</div>