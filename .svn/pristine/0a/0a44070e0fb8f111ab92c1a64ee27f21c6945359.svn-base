<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function () {
	$(".right-onehalf span").click(function(){
    	var $self=$(this);
    	if(!$self.hasClass("spanchange")){
    		$(".right-onehalf span").removeClass("spanchange");
    		$self.addClass("spanchange"); 
    		$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/totaloutlay/readBaseTabCommon.do",
				data: "platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId=${mId}&branchs=${branchs}",
				cache: false
			}).done(function (data) {
				var $data = data.exData;
				if($data != null && $data.id != null){
					$(".right-two a").each(function(){
						$(this).attr({"href":$(this).attr("href").substring(0,$(this).attr("href").indexOf("?"))+"?platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&branchs=${branchs}&mId="+$data.id});
					});
					$(".right-two .spanchange").click();
				}
			}).error(function (jqXHR, textStatus, errorThrown) {});
    	}
	});
    
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/totaloutlay/readBaseTabCommon.do",
		data: "platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId=${mId}&branchs=${branchs}",
		cache: false
	}).done(function (data) {
		var date = new Date();
		if(data.exData != null && data.exData.id != null){
			$("#monthsId").val(data.exData.id);
		}
		$(data.data[0]).each(function(){
			var $self=$(this);
			var $parent = $(".right-two .spanchange").parent("a");
			var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
			$(".right-two .riqi ul").append("<li><a href='"+url+"?platId=${platId}&mId=${mId}&branchs=${branchs}&date="+$self[0].resultMessage+"'>"+ $self[0].resultMessage+"</a></li>")
		}); 
		if(data.data[1].length > 0){
			$(data.data[1]).each(function(){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
				$(".right-two .rspan ul").append("<li><a href='"+url+"?platId=${platId}&mId=${mId}&date=${date}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")			
			});
			$(".right-two .regspan").show();
			$(".right-two .rspan").show();
		}
		$(".right-two a").each(function(){
			var mid = "";
			if(data.exData != null){
				mid = data.exData.id;			
			}
			$(this).attr({"href":$(this).attr("href").substring(0,$(this).attr("href").indexOf("?"))+"?branchs=${branchs}&platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId="+mid});		
		});
	}).error(function (jqXHR, textStatus, errorThrown) {});
	$(".right-two .riqi>span").click(function () {
		if (!$(".right-two .riqi ul").is(":animated")) {
			$(".right-two .riqi ul").fadeToggle(); 
       	}
    });
    $(".right-two .rspan>span").click(function(){
		if (!$(".right-two .rspan ul").is(":animated")) {
			$(".right-two .rspan ul").fadeToggle();
       	}
    });
});
</script>
<div class="right-two clearFix">
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
					%>
					<a href="${pageContext.request.contextPath}/<%=atts.getOpkey() %>.do?branchs=${branchs}&platId=${platId}&date=${date}&mId=${mId}"><span class="tBtn spanchange <%=url %>"><%=atts.getOpName() %></span></a>
					<script type="text/javascript">
						if (window.location.href.indexOf("<%=kes[0] %>") >= 0) {
							$(".right-two a span").removeClass("spanchange")
							$(".right-two a span").eq(0).addClass("spanchange")
						}
					</script>
					<%
				}else{
					%>
					<a href="${pageContext.request.contextPath}/<%=atts.getOpkey() %>.do?branchs=${branchs}&platId=${platId}&date=${date}&mId=${mId}"><span class="<%=url %>"><%=atts.getOpName() %></span></a>
					<script type="text/javascript">
						if (window.location.href.indexOf("<%=kes[0] %>") >= 0) {
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
<span style="float:right; margin-top:5px;" id="upLoad">&nbsp;&nbsp;&nbsp;导出Excel</span>
<div class="fr">
	<div class="riqi"><span>${date}</span><ul></ul></div>
	<span class="riqitext">查看历史记录：</span>
	<%-- <span class="regspan" style="display: none;">查看分公司：</span>
	<div class="rspan" style="display: none;"><span>${branchsMsg}</span><ul></ul></div> --%>
</div>
</div>
<script type="text/javascript">
	var $parent = $(".right-two .spanchange").parent("a");
	var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
	if(this.location.href.indexOf("totaloutlay/show.do") >= 0 && url.indexOf("totaloutlay/show.do") < 0){
		this.location.href = url;
	}
</script>