<%@page import="com.baofeng.utils.Constants"%>
<%@page import="com.baofeng.commons.entity.MenuItemDetails"%>
<%@page import="com.baofeng.commons.entity.MenuItem"%>
<%@page import="org.apache.commons.collections.comparators.ComparatorChain"%>
<%@page import="org.apache.commons.beanutils.BeanComparator"%>
<%@page import="org.apache.commons.collections.comparators.ComparableComparator"%>
<%@page import="org.apache.commons.collections.ComparatorUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>角色权限</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<style>
body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;}
.bg83{
	height:44px; 
	line-height:44px; 
	margin-bottom:14px; 
}
p{margin: 0 0 4px;}
label{font-weight:normal; color:#333; }
.bCenter{
	margin-bottom:20px;
}
.control{
	margin-left:-174px; 
}
</style>
<script>
$(function () {
	//加载数据
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/roles/readPages.do",
    	pageSize: pageSize,
    	pageSizeSelect: true,
    	displayBlankRows: false,
    	pagingToolbarAlign: "left",
        displayPagingToolbarOnlyMultiPages: true
    });
	
	 //新增
    $(".control .add").click(function(){
        $(".firstchecked").next("span").find("input").attr({"disabled":"disabled"});
    	var $chuangkou=$("#rolesForm");
        $chuangkou.find(".p0").text("新建角色")
        $.basewindow("新建角色",$chuangkou,600,750);
        $(".basewindow").addClass("custom-scroll");
    });
	
    //修改
    $(".control .mid").click(function(){
    	 $(".firstchecked").next("span").find("input").attr({"disabled":"disabled"});
    	var $chuangkou=$("#rolesForm");
    	if(grid.getSelectedRow().length>0){
    		var id = grid.getRowRecord(grid.getSelectedRow()).id;
			$.ajax({type:"get",dataType:"json",url:"${pageContext.request.contextPath}/roles/edit.do?t="+new Date().getTime(),
					data:"id="+id,
					success:function(data){
			            $.basewindow("修改角色",$chuangkou,600,780);
			            $(".basewindow").addClass("custom-scroll");
						$("#id").val(data.id);
						$("#name").val(data.name);
						$("#describes").val(data.described);
						var n=0;
						$.each(data.details,function(index,item){
							$("input[name='roles'][value='"+item.itemId+"']")[0].checked = true;
							$("input[name='roles'][value='"+item.itemId+"']").parent("label").next("span").find("input").removeAttr("disabled");
							 n=n+1;
							$.each(item.details,function(index2,item2){
								$("input[name='roleDetails"+ item.itemId +"'][value='"+item2.itemMId+"']")[0].checked = true;
								n=n+1;
							}); 
						}); 
						if(n==$(".firstchecked input").length+$(".firstchecked").next("span").find("input").length){
				    	 	  $(".allchecked input")[0].checked = true;
				    	} 
					},error:function(data){
						$.threesecond("修改数据失败!", 200, 100, 1000);
					}
			});
    	}
    	else{
            $.threesecond("请选择一行数据", 200, 100, 1000);
    	}
    });
	
	 //删除
    $(".control .del").click(function(){
    	if(grid.getSelectedRow().length>0){
    		$.yesorno("是否删除？", 300, 160,function(){
    		 	var id = grid.getRowRecord(grid.getSelectedRow()).id;
				$.ajax({type:"get",dataType:"json",url:"${pageContext.request.contextPath}/roles/delete.do?t="+new Date().getTime(),
					data:"id="+id,
					success:function(data){
						if(data.resultStatus == 100)
							$.threesecond("删除失败!", 200, 100, 1000);  	
						else
							$.threesecond("删除成功!", 200, 100, 1000);						
					}
				});
                $(".table .selected").remove();
		    },function(){
				$.threesecond("看来还是手下留情了!", 200, 100, 1000);    	 
		    });
    	}else{
    		$.threesecond("请选择一行数据", 200, 100, 1000);
    	}
    });
	
     //分配权限
    $(".control .span4").click(function(){
    	if(grid.getSelectedRow().length>0){
			
    	}else{
    		$.threesecond("请选择一行数据", 200, 100, 1000);
    	}
    });
    
    //全选
    $(".allchecked input").click(function () {
        var $self = $(this);
        if ($self.is(":checked")) {
            $(".firstchecked input").each(function () {
                $(this)[0].checked = true;
            })
            $(".firstchecked").next("span").find("input").each(function () {
                $(this)[0].checked = true;
            })
            $(".firstchecked").next("span").find("input").removeAttr("disabled");
        }
        else {
            $(".firstchecked input").each(function () {
                $(this)[0].checked = false;
            })
            $(".firstchecked").next("span").find("input").each(function () {
                $(this)[0].checked = false;
            })
            $(".firstchecked").next("span").find("input").attr({"disabled":"disabled"});
        }
    });
    
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
	
    var onoffall=function(){
   		if( $(".firstchecked input:checked").length+$(".firstchecked").next("span").find("input:checked").length==$(".firstchecked input").length+$(".firstchecked").next("span").find("input").length){
    	 	  $(".allchecked input")[0].checked = true;
    	}
    	else{
    	  	  $(".allchecked input")[0].checked = false;
    	  		
    	}		
    		
    }
    $(".firstchecked").next("span").find("input").click(function(){
        	onoffall();  	
    }); 
    $(".firstchecked input").click(function(){
    	onoffall();
        var $self=$(this);
        if ($self.is(":checked")) {
            $self.parent("label").next("span").find("input").removeAttr("disabled");
        }else {
            $self.parent("label").next("span").find("input").each(function () {
                $(this)[0].checked = false;
            })
            $self.parent("label").next("span").find("input").attr({"disabled":"disabled"});
        }
    });
});
</script>
</head>
<body class="custom-scroll">
    <div class="right-one fsize18 fweightbold clearFix">
	   		<div class="fl">
	   			<span class="refresh fl"></span>
	   		</div>
	   		<div class="fr">
	   			<span class="rName fl"></span>
	   			<span class="username fl">${user.user.accounts}</span>
	   			<span class="fl">, 欢迎来到<spring:message code="title"/></span>
	   			<a href="#" id="logout" target="_parent" class="loginout fl">| 退出系统</a>
	   		</div>
	   </div>
	<div class="rightPadd">
		<div class="right-twohalf">
			<span>首页</span> 
			<span>></span>
			<span>系统设置</span> 
			<span>></span>
			<span class="color99">角色权限</span>
    	</div>
		<div class="control">
		    <jsp:include page="attsCommon.jsp"></jsp:include>
		</div>
		<div class="custom-scroll thistable"  style="overflow:auto; background-color:#fff; ">
			<table id="searchTable"class="table tablelist">
				<tr class="firsttr">
					<th w_index="id" w_align="center" w_hidden="true" width="1%;">ID</th>
					<th w_num="line" w_align="center" width="1%;">序号</th>   	    	 
		   	    	<th w_index="name" w_align="center" width="1%;">角色名称</th>
		   	     	<th w_index="described" w_align="center" width="1%;">角色描述</th>
		         	<th w_index="createDT" w_align="center" width="1%;">创建时间</th>
		         	<th w_align="center">备注</th>
		   		</tr>
			</table>
		
			<form action="${pageContext.request.contextPath}/roles/save.do" method="post" id="rolesForm" style="margin-left:20px;margin-right:20px;display: none">
				<input type="hidden" name="id" id="id">
				<p>角色名称：</p>
				<p><input type="text" name="name" id="name" style="width:200px;" maxlength="400" class="input"></p>
				<p>角色描述：</p>
				<p><input type="text" name="describes" id="describes" value="" style="width:300px;" maxlength="400" class="input"></p>
				<p>角色属性：<label class="allchecked" style="margin-left:20px;"><input type="checkbox" style="vertical-align: text-top;"/>全选</label></p>
				<p>
				<%
					String branchs = (String)request.getSession().getAttribute(Constants.CURRENT_BRANCHS);
					List<MenuItem> list = (List<MenuItem>)request.getAttribute("menuList");
					if(list != null && list.size() > 0){
						Comparator cmp = ComparatorUtils.nullLowComparator(ComparableComparator.getInstance());
						ArrayList<Object> sortFields = new ArrayList<Object>();
						sortFields.add(new BeanComparator("id",cmp));
						ComparatorChain multiSort = new ComparatorChain(sortFields);
						Collections.sort(list, multiSort);
						for(ListIterator<MenuItem> lit = list.listIterator();lit.hasNext();){
							Integer index = lit.nextIndex();
							MenuItem menus = lit.next();
							%>
							<label class="firstchecked" style="margin-right:20px;"><input type="checkbox" style="vertical-align: text-top;" id="roles" name="roles" value="<%=menus.getId() %>" /><%=menus.getName() %></label>
							<span>
							<%
								if(menus.getDetails() != null && menus.getDetails().size() > 0){
									List<MenuItemDetails> detailsList = menus.getDetails();
									for(ListIterator<MenuItemDetails> mit = detailsList.listIterator();mit.hasNext();){
										MenuItemDetails menuDetails = mit.next();
										%>
										<label style="margin-right:10px;"><input type="checkbox" style="vertical-align: text-top;" id="roleDetails<%=menus.getId() %>" name="roleDetails<%=menus.getId() %>" value="<%=menuDetails.getId() %>" disabled/><%=menuDetails.getOpName() %></label>
										<%
									}
								}					
							 %>
							</span>
							<br/>
							<%
						}
					}
				 %>
				</p>
				<p class="bCenter"><input class="ok" type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
			</form>
		</div>
	</div>
</body>
</html>