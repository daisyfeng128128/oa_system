<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<jsp:include page="common.jsp"></jsp:include>
	<jsp:include page="bootstarp.jsp"></jsp:include>
<script>
	$(function() {
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
			url: "${pageContext.request.contextPath}/interview/readBaseTabCommon.do",
			data: "",
			cache: false
		}).done(function (data) {
			$(data.data[0]).each(function(index,value){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href");
				url = url.substring(0,$parent.attr("href").indexOf("?"));
				$(".right-twohalf .riqi ul").append("<li><a href='"+url+"?date="+value+"&branchs=${branchs}'>"+ value+"</a></li>")
			});
			$(data.data[1]).each(function(index,value){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href");
				url = url.substring(0,$parent.attr("href").indexOf("?"));
				$(".right-twohalf .rspan ul").append("<li><a href='"+url+"?date=${date}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
			});
		});
		$(".right-twohalf .riqi>span").click(function() {
			if (!$(".right-twohalf .riqi ul").is(":animated")) {
				$(".right-twohalf .riqi ul").fadeToggle();
			}
		});
		$(".right-twohalf .rspan>span").click(function(){
			if (!$(".right-twohalf .rspan ul").is(":animated")) {
				$(".right-twohalf .rspan ul").fadeToggle();
			}
		});
	});
</script>
<div class="right-two">
	<%
	List<RoleDetailsAtts> attsList = (List<RoleDetailsAtts>)request.getAttribute("attsList");
	if(attsList != null && attsList.size() > 0){
		int ftnIndex = 0;
		for(RoleDetailsAtts atts : attsList){
			try{
				Integer.parseInt(atts.getOpkey(),0);
			}catch(Exception e){
				if(atts.getViews() == Enabled.NO){
					String url = atts.getOpkey().replace("/", "_");
					String[] kes = atts.getOpkey().split("/");
					if(ftnIndex == 0){
						%><a href="${pageContext.request.contextPath}/<%=atts.getOpkey() %>.do?branchs=${branchs}&date=${date}"><span  style="visibility:hidden" class="spanchange <%=url %>"><%=atts.getOpName() %></span></a>
						<script type="text/javascript">
							if (window.location.href.indexOf("<%=kes[1] %>") >= 0) {
								$(".right-two a span").removeClass("spanchange")
								$(".right-two a span").eq(0).addClass("spanchange")
							}	
						</script>
						<%
					}else{
						%><a href="${pageContext.request.contextPath}/<%=atts.getOpkey() %>.do?branchs=${branchs}&date=${date}"><span  style="visibility:hidden" class="<%=url %>"><%=atts.getOpName() %></span></a>
						<script type="text/javascript">
							if (window.location.href.indexOf("<%=kes[1] %>") >= 0) {
								$(".right-two a span").removeClass("spanchange")
								$(".right-two .<%=url %>").eq(0).addClass("spanchange")
							}
						</script>
						<%
					}
					ftnIndex++;
				}
			}
		}
	}
	%>
  <!--   <span style="float:right; margin-top:5px;"><input id="fast" type="text" style="width:200px;height:39px;margin-left:-10px;">&nbsp;&nbsp;快速搜索</span> -->
</div>
<script type="text/javascript">
	var $parent = $(".right-two .spanchange").parent("a");
	var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
	this.location.href = url;
</script>

