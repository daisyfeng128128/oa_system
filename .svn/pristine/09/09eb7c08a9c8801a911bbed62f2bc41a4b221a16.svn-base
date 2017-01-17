<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
				url: "${pageContext.request.contextPath}/outlay/readBaseTabCommon.do",
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
		url: "${pageContext.request.contextPath}/outlay/readBaseTabCommon.do",
		data: "platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId=${mId}&branchs=${branchs}",
		cache: false
	}).done(function (data) {
		var date = new Date();
		if(data.exData != null && data.exData.id != null){
			$("#monthsId").val(data.exData.id);
		}
		var msg = data.resultMessage.split("|");	
		$(".auditMsg").text(msg[0]);
		if(msg[1] != null && msg[1] != "")
			$(".rejectMsg").text("驳回原因："+msg[1]);
		if(data.exData != null && data.exData.audit == "WRITE"){
			$(".thistable .pass,.thistable .reject").attr("disabled","true").addClass("tablecancel");
			$(".thistable .finalpass").attr("disabled","true").addClass("tablecancel");
		}else if(data.exData != null && data.exData.audit == "AUDIT"){
		    if(data.exData.audit == "AUDIT" && !${isEdit}){
		        $(".thistable .impr").attr("disabled","true").addClass("tablecancel");
		        $(".thistable .save").attr("disabled","true").addClass("tablecancel");
		        $(".thistable .finalpass").attr("disabled","true").addClass("tablecancel");
				if($(".thistable .pass").length == 0){
					$(".thistable .reject").attr("disabled","true").addClass("tablecancel");
				}
		        $(".thistable .add,.thistable .del").off('click').addClass("tablecancel");
		    }
		    $(".thistable .audit").attr("disabled","true").addClass("tablecancel");
		}else if(data.exData != null && data.exData.audit == "PASS"){
			$(".thistable .save,.thistable .impr,.thistable .pass,.thistable .audit").attr("disabled","true").addClass("tablecancel");
			if($(".thistable .finalpass").length == 0){
				$(".thistable .reject").attr("disabled","true").addClass("tablecancel");
			}
			$(".thistable .del,.thistable .add").off('click').addClass("tablecancel");
		}else if(data.exData != null && data.exData.audit == "FINALPASS"){
			$(".thistable .save,.thistable .impr,.thistable .pass,.thistable .audit,.thistable .reject,.thistable .finalpass").attr("disabled","true").addClass("tablecancel");
			$(".thistable .del,.thistable .add").off('click').addClass("tablecancel");
		}else if(data.exData != null && data.exData.audit == "FAILURE"){
			$(".thistable .pass,.thistable .finalpass,.thistable .reject").attr("disabled","true").addClass("tablecancel");
		}
		$(data.data[0]).each(function(){
			var $self=$(this);
			var $parent = $(".right-two .spanchange").parent("a");
			var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
			$(".right-twoRight .riqi ul").append("<li><a href='"+url+"?platId=${platId}&mId=${mId}&branchs=${branchs}&date="+$self[0].resultMessage+"'>"+ $self[0].resultMessage+"</a></li>")
		});
		if(data.data[1].length > 0){
			$(data.data[1]).each(function(){
				var $self=$(this);
				var $parent = $(".right-two .spanchange").parent("a");
				var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
				$(".right-twoRight .rspan ul").append("<li><a href='"+url+"?platId=${platId}&mId=${mId}&date=${date}&branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")			
			});
			$(".right-twoRight .regspan").show();
			$(".right-twoRight .rspan").show();
		}
		$(".right-two a").each(function(){
			var mid = "";
			if(data.exData != null){
				mid = data.exData.id;			
			}
			$(this).attr({"href":$(this).attr("href").substring(0,$(this).attr("href").indexOf("?"))+"?branchs=${branchs}&platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId="+mid});		
		});
	}).error(function (jqXHR, textStatus, errorThrown) {});
	$(".right-twoRight .riqi>span").click(function () {
		if (!$(".right-twoRight .riqi ul").is(":animated")) {
			$(".right-twoRight .riqi ul").fadeToggle(); 
       	}
    });
    $(".right-twoRight .rspan>span").click(function(){
		if (!$(".right-twoRight .rspan ul").is(":animated")) {
			$(".right-twoRight .rspan ul").fadeToggle();
       	}
    });
});
</script>
<div class="right-twohalf clearFix">
	<span>首页</span> 
	<span>></span>
	<span>直播系统</span> 
	<span>></span>
	<span class="color99">提交月报</span>
    <div class="fr">
    	<span style="float:left;">本月报表状态：</span>
    	<span class="auditMsg" style="float:left;color:#1184c3; "></span>
    </div>
    <span class="rejectMsg" style="float:left;color:#e35d00;"></span>
    
</div>
<div class="right-onehalf custom-scroll">
     <jsp:include page="platCommon.jsp"></jsp:include>
     <input type="button" value=">>" class="expand"/>
     <div class="expDet">
     </div>
 </div>
<div class="clearFix">
	<div class="right-two fl">
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
						<a href="${pageContext.request.contextPath}/<%=atts.getOpkey() %>.do?branchs=${branchs}&platId=${platId}&date=${date}&mId=${mId}"><span class="spanchange <%=url %>"><%=atts.getOpName() %></span></a>
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
							$(".right-two span").addClass("tBtn");
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
	</div>
	
	<div class="fr right-twoRight">
		<span class="riqitext">查看历史记录：</span>
		<div class="riqi"><span>${date}</span><ul></ul></div>
		
		<span class="regspan">查看分公司：</span>
		<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
	</div>
	
</div>
<script type="text/javascript">
	var $parent = $(".right-two .spanchange").parent("a");
	var url = $parent.attr("href").substring(0,$parent.attr("href").indexOf("?"));
	if(this.location.href.indexOf("outlay/show.do") >= 0 && url.indexOf("outlay/show.do") < 0){
		this.location.href = url;
	}
</script>