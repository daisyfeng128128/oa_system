<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function () {
		if (window.location.href.indexOf("purType=2") >= 0) {
			$(".right-two a span").removeClass("spanchange")
			$(".right-two a span").eq(0).addClass("spanchange")
		} else if (window.location.href.indexOf("purType=1") >= 0) {
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
	   				var $self = $(this);
					var url = window.location.href;
					if(url.indexOf("?") > 0){
						url = url.substring(0,url.indexOf("?"));
					}
					$(".right-twohalf .rspan ul").append("<li><a href='"+url+"?branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")   				
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
			<a href="${pageContext.request.contextPath}/purchaseAudit/show.do?purType=2"><span class="spanchange tBtn">电子设备</span></a> <a
			   href="${pageContext.request.contextPath}/purchaseAudit/showCh.do?purType=1"><span class="tBtn">办公用品</span></a>
			<span class="control2" style="margin-left:76px">
				<input class="tabBtn1 all" type="button" value="所有数据">
				<input class="tabBtn1 dai" type="button" value="待审核">
				<input class="tabBtn1 cent" type="button" value="审核中">
				<input class="tabBtn1 pass" type="button" value="审核通过">
				<input class="tabBtn1 not" type="button" value="审核未通过">
			</span>
			
	
			<div class="right-twohalf fr">
				<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索">
			    	<button type="button" id="seach" class="tableok">搜索</button>				
				</div>
			</div>
		</div>
