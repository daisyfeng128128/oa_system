<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<title>员工考勤</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<jsp:include page="treeview.jsp"></jsp:include>
<style>
body {
	height: 100%;
	min-width: 200px;
	overflow-x:hidden; 
	position: relative;
	width: 100%;
}
.mustred {
	margin-left: 10px;
	color: #ff0000
}
.mains{width:100%;min-width:1000px;overflow: hidden; background-color:#fff; } 
.lefttree{width:15%;margin-right:15px;height:800px;float:left; overflow:scroll; }
.rightbox{width:85%; float:right;overflow:auto; margin-top:-800px; }

</style>
<script>
	$(function() {
	    //$(".rightbox").css({"width": $("body").outerWidth(true) - 240}); 
        /* $(window).resize(function () { 
			if($("body").outerWidth(true)>1000){ 
				$(".rightbox").css({"width": $("body").outerWidth(true) - 240});
			} 
		}); */
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
	    
	    if("${isEdit}" == "true"){
			$(".okok").show();
		}else{
			$(".okok").hide();
		}
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/empAttendance/readDateList.do",
			data: "",
			cache: false
		}).done(function (data) {
			$(data.data[0]).each(function(index,value){
				var $self=$(this);
				$(".right-twoRight .riqi ul").append("<li><a href='${pageContext.request.contextPath}/empAttendance/show.do?date="+value+"'>"+ value +"</a></li>")
			});	
		});
			
		$(".right-twoRight .riqi>span").click(function () {
			if (!$(".right-twoRight .riqi ul").is(":animated")) {
				$(".right-twoRight .riqi ul").fadeToggle();
	       	}
	    });
		var ids ="";
	    var grid = $.fn.bsgrid.init("searchTable", {
 			url : "${pageContext.request.contextPath}/empAttendance/readPages.do?date=${date}",
            pageSize:pageSize,
 			pagingToolbarAlign : "left",
 			displayBlankRows : false,
 			displayPagingToolbarOnlyMultiPages : true,
 			extend: {
                settings: {
                    supportGridEdit:${isEdit}, 
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                        attendance: {
                            build: function (edit, value) {
                                return value + '<input class="dottwo attendance" class="easyui-numberbox input"  style="width:100px;" value="' + value + '"  />';
                            } 
                        },
                        remark:{
                            build:function (edit, value) {
                                return value + '<input class="dottwo remark"   style="width:100%" min-width:200px; value="' + value + '"  />';
                            }
                        }
                    } 
                }
            }
 		});
	    
		this.tree = [];
		$.getJSON("${pageContext.request.contextPath}/empAttendance/readTree.do?t="+new Date().toString(),function(data){
			 $("#tree").treeview({
			  data: data.data,
			  levels: 5,
			  expanded:false,
			  selectedBackColor:"#f3f3f3",
			  selectedColor:"#333",
			  icon: "glyphicon glyphicon-stop",
  			  //selectedIcon: "glyphicon glyphicon-stop",
			  onNodeSelected: function(event, data) {
			    ids=data.id;
			    var click_type;
			 		//加载数据
				var searchParames={
						    "id": ids
						}
				grid.search(searchParames);
			  }
			});	 
		});
		$(".save").click(function(){
		    var flag = false;
            var shuzu=[];
            if(grid.getChangedRowsIndexs()!=''){
                $(grid.getChangedRowsIndexs()).each(function(){
                    var self=this;
                    var attendances=$("#searchTable tbody tr").eq(self).find(".attendance").val();
    		        if(isNaN(attendances)){
    		            flag= true;
    		            $("#searchTable tbody tr").eq(self).find(".attendance").val("");
    	            }
                    var data={
                        id:$("#searchTable tbody tr").eq(self).find("td").eq(0).text(),
                        attendance: $("#searchTable tbody tr").eq(self).find(".attendance").val(),
                        remark:$("#searchTable tbody tr").eq(self).find(".remark").val()
                    }
                    shuzu.push(data);
                })
			  $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/empAttendance/save.do",
                data:JSON.stringify(shuzu),
                cache: false,
                contentType: 'application/json; charset=utf-8',
                success:function(){
                    $.threesecond("保存成功", 200, 100, 1000);
                    grid.refreshPage();
                }
            })
			 }
		})
		//快速搜索
		$("#fast").change(function() {
		    var fastArg = $("#fast").val();
			var searchParames={
			    "fastArg": fastArg,
			    "id":ids
			}
			grid.search(searchParames);
		})
		
		$("#upLoad").click(function(){
		    var $chuangkou = $("#uploadForm");
		    $.basewindow("导入Excel", $chuangkou,510, 220);
			$chuangkou.css({"display" : "block"}); 
		})
		
 
 
		
		
	})
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
			<span>人事系统</span> 
			<span>></span>
			<span class="color99">员工考勤</span>
			<div class="fr">
				<span id="upLoad">导入Excel</span>
			</div>
	   	</div>

		<div class="clearFix right-twoRight">
			<div class="fr">
				<span class="riqitext">查看历史记录：</span>
				<div class="riqi"><span>${date}</span><ul></ul></div>
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索">
			    	<button type="button" id="seach" class="tableok">搜索</button>				
				</div>
			</div>
		</div>

		<div class="mains custom-scroll">
			<div class="lefttree custom-scroll" >
				<table class="table" style=" min-width: 20%;">
					<tr><th align="center">部门导航</th></tr>
					<tr><td id="tree" style="text-align:left;"></td></tr>
				</table>
			</div>
			<div  class="rightbox thistable custom-scroll">
				<table id="searchTable" class="table tablelist">
					<tr class="firsttr" style="overflow: auto;">
						<th w_index="id" w_align="center" width="1%;" w_hidden="true">id</th>
						<th w_num="line" w_align="center" width="1%;">序号</th>
						<th w_index="number" w_align="center" width="1%;">员工号</th>
						<th w_index="name" w_align="center" width="1%;">员工姓名</th> 
						<th w_index="aliasname" w_align="center" width="1%;">员工艺名</th>
						<th w_index="departName" w_align="center" width="1%;">部门</th>
						<th w_index="posiName" w_align="center" width="1%;">职位</th> 
						<th w_edit="attendance" w_index="attendance" w_align="center" width="1%;">出勤天数</th>
						<th w_edit="remark" w_index="remark" w_align="center">备注</th>
					</tr>
				</table>
			</div> 
		</div>
		<div class="baocun control">
			<jsp:include page="attsCommon.jsp"></jsp:include>
		</div>
		<div class="salaryLook" style="display:none"> 
			<form action="${pageContext.request.contextPath}/empAttendance/upload.do" method="post" enctype="multipart/form-data" id="uploadForm" style="display:none;margin-left:20px;margin-right:20px;">
				<table class="table" > 
					<tr >
						<td>Excel</td><td><input type="file"  id= "upload" name = "upload" ><span class="mustred">*(格式:员工号、姓名、出勤天数,xlsx)</span></td> 
					</tr>
					<tr>
						<td style="text-align:center;" colspan="2">
							<p>
								<input class="ok" type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
								<input class="cancel" type="reset" name="Input" value="关闭">
							</p>
						</td>
					</tr>
				</table> 
			</form>
		</div>
	</div>
	
	
</body>
</html>