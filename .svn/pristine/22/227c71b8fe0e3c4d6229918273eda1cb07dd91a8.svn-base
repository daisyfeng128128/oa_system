<%@page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>线上艺人</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="common.jsp"></jsp:include>
<jsp:include page="bootstarp.jsp"></jsp:include>
<style>
a{font-size:14px;color:#0099cc;text-decoration:none;}a:hover{font-size:14px;color:#cc9966;text-decoration:underline;}.style a{text-decoration:underline;}
body {
    height: 100%;
    min-width: 200px;
    overflow-x: hidden;
    position: relative; 
}
.yirentable tr{
	height:30px;
	line-height:30px;
}
.yirentable tr:hover{	
	background-color:#ddd;
}
.yirentable .firsttr:hover{
	background-color:#fff;
}
.yirentable .selected{
	background-color:#ddd;
}
.control{
	margin-left:-290px;
}
.control .regspan {
		float:right;
		font-family: 'Microsoft YaHei';
		margin-right: 130px;
		font-weight: normal;
		margin-top: 3px;
		display: none;
	}
	
	.control .rspan {
		cursor: pointer;
		float:right;
		height: 27px;
		margin-right:-190px;
		margin-top: 8px;
		width: 100px;
		border: 1px solid #ddd;
		display: none;
	}
	.control .rspan>span {
		padding: inherit;
		display: block;
		color: black;
		font-weight: normal;
		background-color: transparent;
		cursor: pointer;
		margin-top: -2px;
		width: 100px;
		height: 30px;
		line-height: 30px;
		text-align: center;
		border: 0px solid #ddd;
	}
	
	.control .rspan ul {
		margin-left: -1px;
		display: none;
		position: fixed;
	}

	.control .rspan li {
		background-color: #fff;
		cursor: pointer;
		margin-top: -1px;
		width: 100px;
		height: 30px;
		line-height: 30px;
		text-align: center;
		border: 1px solid #ddd;
		font-weight: normal;
		color:black;
		text-decoration: none;
	}
	
	.control .rspan li a{
		color:black;
		text-decoration: none;
	}
	.table>tbody>tr>td{
		border:none; 
	}
	#sex,#genre,#pushMoney,#signed,#channel{
		width:200px; height:26px;
		border: 1px solid #ddd;
	    box-shadow: 0 0 6px #fbfbfb;
	    border-radius: 2px;
	    text-indent: 1em;
	}
	.bottom{border-top:1px solid #ddd; margin-top: 30px; }
	.tablelist tr{
		border-bottom:1px solid #ddd; 
	}
</style>
<script>
$(function () {
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
       //加载数据
       $(".right-onehalf span").click(function(){
    	   var $self=$(this);
    	   if(!$self.hasClass("spanchange")){
    		   $self.siblings("span").removeClass("spanchange");
    		   $self.addClass("spanchange");   
    		   grid.search(searchParames);
    		   $("#actoresForm select option").remove();
    	   }
       })
       var grid = $.fn.bsgrid.init("searchTable", {
    		url: "${pageContext.request.contextPath}/actoresOnline/readPagesActoresOnline.do?branchs=${branchs}&genre=ONLINE&toUrl=${toUrl}",
    		pageSizeSelect: true, 
    		pagingToolbarAlign: "left" ,
    		displayBlankRows: false, 
        	pageSize:pageSize,
            displayPagingToolbarOnlyMultiPages: true,
            extend: {
                settings: {
                    supportGridEdit:true,
                    supportGridEditTriggerEvent: "",
                    gridEditConfigs: {
                    	linkUrl: {
                            build: function (edit, value) {
                                if(value != "#"){
                                    return  '<a class="dottwo pntotalSocial" class="style"   style="width:100px;" href="'+ value +'" target="view_window"  >查看视频</a>';
                                }else{
                                    return '<span style="color:#666666">查看视频<span>';
                                }
                            } 
                        }
                    } 
                }
            }
    	});
	    
 
	//搜索
	$("#actoresForm .next").click(function(){
		var $self=$(this);
		var data={
			name:$self.prev("input").val(),
			loadTable : "true",
			seachEmp : "false",
			seachAct : "false"
		}
		$.ajax({
	        type: "POST",
	        url: "${pageContext.request.contextPath}/user/search.do",
	        cache: false,
	        data: JSON.stringify(data),  
	        contentType: 'application/json; charset=utf-8',
	    }).done(function (data) {
	    	$(".yirentable tr").not(".firsttr").remove()
	    	$(data).each(function(index){
	        	var $self=$(this)[0];
		        if(index==0){
		        	$(".yirentable").append("<tr class='selected'><td hidden>"+$self.id+"</td><td hidden>"+$self.loadTable+"</td><td>"+$self.num+"</td><td>"+$self.name+"</td><td>"+$self.aliasname+"</td></tr>")   	
		        }
		        else{
		        	$(".yirentable").append("<tr><td hidden>"+$self.id+"</td><td hidden>"+$self.loadTable+"</td><td class='num'>"+$self.num+"</td><td>"+$self.name+"</td><td>"+$self.aliasname+"</td></tr>") 
		        }
	        });
	    }).error(function (jqXHR, textStatus, errorThrown){});
	});
	//手机号查找
 	isSearch=false;
	//新增
   $(".control .add").click(function(){
       $("#actoresSaveForm #realname").removeAttr("disabled");
       $("#actoresSaveForm #aliasname").removeAttr("disabled");
       $("#actoresSaveForm #sex").removeAttr("disabled");
       $("#actoresSaveForm #phone").removeAttr("disabled"); 
       $("#actoresSaveForm #settl").removeAttr("disabled");
       $("#actoresSaveForm #genre").removeAttr("disabled"); 
       $("#actoresSaveForm #minimumGuarantee").removeAttr("disabled");
       $("#actoresSaveForm #pushMoney").removeAttr("disabled");
    	var $chuangkou=$("#actoresForm");
    	$(".yirentable tr").not(".firsttr").remove();
        var $no=function(){
        	isSearch=false; 
        }
        $.basewindow("新增线上艺人","#actoresSaveForm",820,495,"",$no);
        $(".basewindow").addClass("custom-scroll");
        $("#actoresSaveForm #pushMoney").numberbox("setValue",0);
    	if($("#actoresForm select option").length==0){
        	var data={
        		id:$(".right-onehalf .spanchange").attr("value")
        	} 
    	}
    }); 
     
		$("#phone").blur(function(){
			if(isSearch == false){  
				var phone =$("#phone").val(); 
				if(phone != null){
	            var editurl = "${pageContext.request.contextPath}/actoresOnline/editActoresByphone.do";
	    		$.ajax({
		          type: "get",
		          dataType: "json",
		          url: editurl,
		          data: "phone="+phone,
		          show:"slide",
		          success: function(data){ 
						         
					var $data = data.data; 
					if($data != null && $data !=""){	
						 if($data.sex =="女"){
		                     $("#sex").val("WOMAN").selected;
		                }else if($data.sex =="男"){
		                    $("#sex").val("MAN").selected;
		                }
						 if($data.signed =="公司签约"){
		                     $("#signed").val("YES").selected;
		                }else{
		                    $("#signed").val("NO").selected;
		                } 
		           		  
						if($data.probation !="-"){ 
							$("#probation").val($data.probation).selected;
						}else{
							$("#probation").val("0").selected;
						}
		           	
						if($("#actoresSaveForm #sex").val()=="女"){
							$("#actoresSaveForm .settlCheck").prev("select").val("SINGLEPLATFORM").selected
						}else{
						    $("#actoresSaveForm .settlCheck").prev("select").val("SINGLEPLATFORM").selected
						}
						$("#actoresSaveForm #realname").val($data.realname);
						$("#actoresSaveForm #aliasname").val($data.aliasname);
						$("#actoresSaveForm #phone").val($data.phone);
						$("#actoresSaveForm #qq").val($data.qq); 
						$("#actoresSaveForm #entryTime").datebox("setValue", $data.entryTime);
						$("#actoresSaveForm #beLiveTime").datebox("setValue", $data.beLiveTime);
						$("#actoresSaveForm #minimumGuarantee").numberbox("setValue",$data.minimumGuarantee);
						$("#actoresSaveForm #basicSalary").numberbox("setValue",$data.basicSalary);
						$("#actoresSaveForm #pushMoney").numberbox("setValue",$data.pushMoney);
					

						$("#actoresSaveForm #shareBankAddress").val($data.shareBankAddress);
						$("#actoresSaveForm #shareBankCard").val($data.shareBankCard);
						$("#actoresSaveForm #bankAddress").val($data.bankAddress);
						$("#actoresSaveForm #bankCard").val($data.bankCard);
						$("#actoresSaveForm #idcard").val($data.idcard);
						$("#actoresSaveForm #address").val($data.address);
						isSearch =true;
					}else{
						isSearch =false;
					}
			   	  }, error : function(data){
			   		 
			   	  }
				}); }
			}else{  
			}
		})
		


    //修改
    $(".control .mid").click(function(){
    	var $chuangkou=$("#actoresUpdateForm");
    	var loadTable =0;
    	$("#actoresUpdateForm #loadTable").val(loadTable); 
		if(grid.getSelectedRow().length>0){
    		var id = grid.getRowRecord(grid.getSelectedRow()).id;
            var editurl = "${pageContext.request.contextPath}/actoresOnline/editActores.do";
    		$.ajax({
	          type: "get",
	          dataType: "json",
	          url: editurl,
	          data: "id="+id,
	          show:"slide",
	          success: function(data){
		        var $data = data.data;
		        $.basewindow("修改线上艺人",$chuangkou,798,522);
		        $(".basewindow").addClass("custom-scroll");
		        $("#actoresUpdateForm #id").val($data.id);  
				$("#actoresUpdateForm #linkUrl").val($data.linkUrl);
				 if($data.sex =="女"){
                     $("#actoresUpdateForm #sex").val("WOMAN").selected;
                }else if($data.sex =="男"){
                    $("#actoresUpdateForm #sex").val("MAN").selected;
                }
				 if($data.signed =="公司签约"){
                     $("#actoresUpdateForm #signed").val("YES").selected;
                }else{
                    $("#actoresUpdateForm #signed").val("NO").selected;
                } 
				if($data.probation !="-"){ 
					$("#actoresUpdateForm #probation").val($data.probation).selected;
				}else{
					$("#actoresUpdateForm #probation").val("0").selected;
				}
           		
				if($("#actoresUpdateForm #sex").val()=="女"){
					$("#actoresUpdateForm .settlCheck").prev("select").val("SINGLEPLATFORM").selected
				}else{
				    $("#actoresUpdateForm .settlCheck").prev("select").val("SINGLEPLATFORM").selected
				}
				$("#actoresUpdateForm #realname").val($data.realname);
				$("#actoresUpdateForm #aliasname").val($data.aliasname);
				$("#actoresUpdateForm #phone").val($data.phone);
				$("#actoresUpdateForm #qq").val($data.qq); 
				$("#actoresUpdateForm #entryTime").datebox("setValue", $data.entryTime);
				$("#actoresUpdateForm #beLiveTime").datebox("setValue", $data.beLiveTime);
				$("#actoresUpdateForm #minimumGuarantee").numberbox("setValue",$data.minimumGuarantee);
				$("#actoresUpdateForm #pushMoney").numberbox("setValue",$data.pushMoney);
				$("#actoresUpdateForm #shareBankAddress").val($data.shareBankAddress);
				$("#actoresUpdateForm #shareBankCard").val($data.shareBankCard);
				$("#actoresUpdateForm #bankAddress").val($data.bankAddress);
				$("#actoresUpdateForm #bankCard").val($data.bankCard);
				$("#actoresUpdateForm #idcard").val($data.idcard);
				$("#actoresUpdateForm #address").val($data.address);
				
				$("#actoresUpdateForm #minimumGuarantee").attr({"disabled":"disabled"});								
				$("#actoresUpdateForm #pushMoney").attr({"disabled":"disabled"});
		   	  }, error : function(data){
		   		  
		   	  }
			});
    	}else{
    		$.threesecond("请先选择", 200, 100, 1000);
    	}
    });

		
		
		$("#selImage").click(function(){
		    $(".zhezhao-basewindow").hide();
            $(".basewindow").hide();
            $('body').append($("#emplook"));
            $("#emplook").hide();
		    var $chuangkou = $("#idlook");
		    var id = $("#emplook #id").val();
			var editurl = "${pageContext.request.contextPath}/actoresOnline/editActores.do";
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
					var $data = data.data; 
					$("#idlook #yuanImage").attr("src",$data.idImage);
				},
				error : function(data) {
				}
			});
			$.basewindow("详细资料", $chuangkou, 700, 580);
			$chuangkou.css({
				"display" : "block"
			});
		});
		
		 /**详情*/
		 $(".views").click(function() {
					var $chuangkou = $("#emplook");
					if (grid.getSelectedRow().length > 0) {
						var id = grid.getRowRecord(grid.getSelectedRow()).id;
						 var editurl = "${pageContext.request.contextPath}/actoresOnline/editActores.do";
						$.ajax({
							type : "get",
							dataType : "json",
							url : editurl,
							data : "id=" + id,
							show : "slide",
							success : function(data) {
								  var $data = data.data; 
								  	$("#emplook #id").val($data.id);
								  	$("#emplook #image").attr("src",$data.idImage);
									$("#emplook #vsex").text($data.sex);
									$("#emplook #vrealname").text($data.realname);
									$("#emplook #valiasname").text($data.aliasname);
									$("#emplook #vphone").text($data.phone);
									$("#emplook #vqq").text($data.qq); 
									$("#emplook #ventryTime").text($data.entryTime); 
									$("#emplook #vminimumGuarantee").text($data.minimumGuarantee); 
									$("#emplook #vpushMoney").text($data.pushMoney); 
									$("#emplook #vbirth").text($data.birth);  
									$("#emplook #vgenre").text($data.genre); 
									$("#emplook #vsigned").text($data.signed); 
									$("#emplook #vlinkUrl").text($data.linkUrl); 
									$("#emplook #vbankAddress").text($data.bankAddress);
									$("#emplook #vbankCard").text($data.bankCard);
									$("#emplook #vshareBankAddress").text($data.shareBankAddress);
									$("#emplook #vshareBankCard").text($data.shareBankCard); 
									$("#emplook #vidcard").text($data.idcard);
									$("#emplook #vaddress").text($data.address);
							},
							error : function(data) {
							}
						});
						$.basewindow("详细资料", $chuangkou, 700, 470);
						$(".basewindow").addClass("custom-scroll");
						$chuangkou.css({
							"display" : "block"
						});
						} else {
							$.threesecond("请先选择", 200, 100, 1000);
						}

					});
					
					 /**详情(平台)*/
		 $(".viewsplat").click(function() {
					var $chuangkou = $("#emplook");
					if (grid.getSelectedRow().length > 0) {
						var id = grid.getRowRecord(grid.getSelectedRow()).id;
						 var editurl = "${pageContext.request.contextPath}/actoresOnline/editActores.do";
						$.ajax({
							type : "get",
							dataType : "json",
							url : editurl,
							data : "id=" + id,
							show : "slide",
							success : function(data) {
								  var $data = data.data; 
								  	$("#emplook #id").val($data.id);
								  	$("#emplook #image").attr("src",$data.idImage);
									$("#emplook #vsex").text($data.sex);
									$("#emplook #vrealname").text($data.realname);
									$("#emplook #valiasname").text($data.aliasname);
									$("#emplook #vphone").text($data.phone);
									$("#emplook #vqq").text($data.qq); 
									$("#emplook #ventryTime").text($data.entryTime); 
									$("#emplook #vminimumGuarantee").text($data.minimumGuarantee); 
									$("#emplook #vpushMoney").text($data.pushMoney); 
									$("#emplook #vbirth").text($data.birth);  
									$("#emplook #vgenre").text($data.genre); 
									$("#emplook #vsigned").text($data.signed); 
									$("#emplook #vlinkUrl").text($data.linkUrl); 
									$("#emplook #vbankAddress").text($data.bankAddress);
									$("#emplook #vbankCard").text($data.bankCard);
									$("#emplook #vshareBankAddress").text($data.shareBankAddress);
									$("#emplook #vshareBankCard").text($data.shareBankCard); 
									$("#emplook #vidcard").text($data.idcard);
									$("#emplook #vaddress").text($data.address);
							},
							error : function(data) {
							}
						});
						$.basewindow("详细资料", $chuangkou, 700, 470);
						$(".basewindow").addClass("custom-scroll");
						$chuangkou.css({
							"display" : "block"
						});
						} else {
							$.threesecond("请先选择", 200, 100, 1000);
						}

					});
    //转正
    $(".control .become").click(function () {
    	if(grid.getSelectedRow().length>0){
    	     $.yesorno("是否为其转正？", 300, 160, function () {
                    var id = grid.getRowRecord(grid.getSelectedRow()).id;
                    var delUrl = "${pageContext.request.contextPath}/actoresOnline/updateProbationer.do?t="+new Date().getTime();
                    //后台删除
                    $.ajax({
                        type: "post",
				          dataType: "json",
				          url: delUrl,
				          data: "id="+id+"&type=1",//要发送的数据
				          success: function(data){
				          	if("errors"== data.resultMessage){
				          	  $.threesecond("转正失败！", 200, 100, 1000);
				          	}else{
				          		grid.refreshPage();
				          	}
				          },
						  error : function(data){
						  }
                    });
                    
                }, function () {
                    
                });
    	}
        else{
        	$.threesecond("请先选择", 200, 100, 1000);
        }
    });
    
    
    //离职
  $(".control .leave").click(function () {
  	if(grid.getSelectedRow().length>0){ 
	    	 var $chuangkou=$("#leaveForm"); 
  	     $.basewindow("离职原因:",$chuangkou,280, 294, function () {
                var id = grid.getRowRecord(grid.getSelectedRow()).id; 
              	$("#leaveForm #id").val(id);
              	$("#leaveForm #type").val(2);
              	$("#leaveForm #t").val("ONLINE");
             }, function () {
                 $.threesecond("看来还是手下留情了!", 200, 100, 1000);
             }); 
             $(".basewindow").addClass("custom-scroll");
  	} else{
      	$.threesecond("请先选择", 200, 100, 1000);
      }
  });  
    //高级搜索
		$(".searchAll").click(function() {
			click_type = 11;
			$("#name").removeAttr("readonly");
			$("#namepy").removeAttr("readonly");
			$("#genrer").removeAttr("disabled");
			$("#basicSalary").next("span").remove();
			var $chuangkou = $("#sousuo");
			$.basewindow("高级搜索", $chuangkou, 654,272);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.find(".ok").attr({
				"disabled" : "disabled"
			}).css({
				"cursor" : "not-allowed"
			})//初始确认建不能点击所以修改不需要这段 //改动6！！！
		})
    //删除
    $(".control .del").click(function () {
    	if(grid.getSelectedRow().length>0){
    	     $.yesorno("是否删除？", 300, 160, function () {
    	      	var platId =  $(".right-onehalf .selected").attr("value");
    	       	var id = grid.getRowRecord(grid.getSelectedRow()).id;
                var delUrl = "${pageContext.request.contextPath}/actoresOnline/delete.do?t="+new Date().getTime();
                    //后台删除
                $.ajax({
                	type: "get",
				    dataType: "json",
				    url: delUrl,
				    data: "id="+id+"&platId="+platId,//要发送的数据
				    success: function(data){
				    	if("success" == data.resultMessage){
				        	$(".table .selected").remove();
				        }
				    },error : function(data){}
                });
         	}, function () {
            	$.threesecond("看来还是手下留情了!", 200, 100, 1000);
            });
    	}else{
        	$.threesecond("请先选择", 200, 100, 1000);
        }
    });
	
		//保存
	 $("#actoresSaveForm .okok").click(function () {
         var aliasname = $("#actoresSaveForm #aliasname").val();
         var phone = $("#actoresSaveForm #phone").val();
         var minimumGuarantee = $("#actoresSaveForm #minimumGuarantee").val();
         var basicSalary = $("#actoresSaveForm #basicSalary").val();  
         var pushMoney = $("#actoresSaveForm #pushMoney").val();
         var plat = $("#actoresSaveForm #plat").combogrid('getValue');
         var channel = $("#actoresSaveForm #channel").val();
         var beliveTime = $("#actoresSaveForm #beliveTime").datebox("getValue");
        
		 var telReg = !!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/); 
 
         if(telReg == false){
        	 $.threetop("请填写正确的手机号!");
         }else if(aliasname == "" || aliasname == null){
        	 $.threetop("请填写艺名!");
         }else  if(minimumGuarantee == "" || minimumGuarantee == null){
        	 $.threetop("请填写保底!");
         }else  if(pushMoney == "" || pushMoney == null){
        	 $.threetop("请填写提成比例!");
         } else if(!$("#actoresSaveForm #entryTime").combogrid("getValue")){
        	 $.threetop("请填写入职日期!"); 
         } else if (plat == ""|| plat ==null){
             $.threetop("请选择平台!"); 
         } else if (!beliveTime){
             $.threetop("请填写开播日期!"); 
         } else if (channel == "" || channel==null){
             $.threetop("请选择频道!"); 
         }else {
             $("#actoresSaveForm #realname").removeAttr("disabled");
             $("#actoresSaveForm #aliasname").removeAttr("disabled");
             $("#actoresSaveForm #sex").removeAttr("disabled");
             $("#actoresSaveForm #phone").removeAttr("disabled"); 
             $("#actoresSaveForm #settl").removeAttr("disabled");
             $("#actoresSaveForm #genre").removeAttr("disabled");  
             $("#actoresSaveForm #minimumGuarantee").removeAttr("disabled");
             $("#actoresSaveForm #pushMoney").removeAttr("disabled");
		 	 $("#actoresSaveForm").ajaxSubmit(function(data) {
                    if(data.resultStatus == 200){
                   		 $(".zhezhao-basewindow").hide();
		                 $(".basewindow").hide();
		                 $('body').append($("#actoresSaveForm"));
		                 $("#actoresSaveForm").hide();
                   		 grid.refreshPage();
                    }else if(data.resultStatus== 101){
                        $.threetop("艺人库已存在姓名与艺名相同的艺人!");
                    }
              })
         }
     });
		//关闭
	 $("#actoresSaveForm #input").click(function () {
	     document.getElementById("channel").options.length=0;
	     $(".zhezhao-basewindow").hide();
         $(".basewindow").hide();
         $('body').append($("#actoresSaveForm"));
         $("#actoresSaveForm").hide();     
	 })
		
		//修改保存
	 $("#actoresUpdateForm .okok").click(function () {
         var aliasname = $("#actoresUpdateForm #aliasname").val();
         var phone = $("#actoresUpdateForm #phone").val();
         var minimumGuarantee = $("#actoresUpdateForm #minimumGuarantee").val();
         var basicSalary = $("#actoresUpdateForm #basicSalary").val();  
         var pushMoney = $("#actoresUpdateForm #pushMoney").val();
        
		var telReg = !!phone.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/); 
 
          if(telReg == false){
        	 $.threetop("请填写正确的手机号!");
         }else if(aliasname == "" || aliasname == null){
        	 $.threetop("请填写艺名!");
         }else  if(minimumGuarantee == "" || minimumGuarantee == null){
        	 $.threetop("请填写保底!");
         }else  if(pushMoney == "" || pushMoney == null){
        	 $.threetop("请填写提成比例!");
         } else if(!$("#actoresUpdateForm #entryTime").combogrid("getValue")){
        	 $.threetop("请填写入职日期!"); 
         }else {
             $("#actoresUpdateForm #realname").removeAttr("disabled");
             $("#actoresUpdateForm #aliasname").removeAttr("disabled");
             $("#actoresUpdateForm #sex").removeAttr("disabled");
             $("#actoresUpdateForm #phone").removeAttr("disabled"); 
             $("#actoresUpdateForm #settl").removeAttr("disabled");
             $("#actoresUpdateForm #genre").removeAttr("disabled");  
             $("#actoresUpdateForm #minimumGuarantee").removeAttr("disabled");
             $("#actoresUpdateForm #pushMoney").removeAttr("disabled");
		 	 $("#actoresUpdateForm").ajaxSubmit(function(data) {
                    if(data.resultStatus == 200){
                   		 $(".zhezhao-basewindow").hide();
		                 $(".basewindow").hide();
		                 $('body').append($("#actoresUpdateForm"));
		                 $("#actoresUpdateForm").hide();
                   		grid.refreshPage();
                    }else{ 
                    }
              })
         }
     });
	//快速搜索
		$("#fast").change(function() {
		    var fastArg = $("#fast").val();
			var searchParames={
			    "fastArg": fastArg
			}
			grid.search(searchParames);
		})
		//高级搜索提交
		$("#sousuo .koko").click(function() { 
			var srealname = $("#srealname").val();
			var saliasname = $("#saliasname").val(); 
			var sphone = $("#sphone").val(); 
			
			var searchParames={
				"srealname":srealname,
				"saliasname":saliasname, 
				"sphone":sphone
			}
			grid.search(searchParames);
			$("#sousuo").hide(); 
			$(".zhezhao-basewindow").hide();
			$(".basewindow").hide();
		})
		
		
		
		
		$("#plat").combogrid({
			panelWidth : 500,
			method : "POST",
			datatype : "json",
			url : "${pageContext.request.contextPath}/plats/readPagesSkip.do",
			mode : "remote",
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			idField : "id",
			textField : "platName",
			pageSize : 30,
			pageList : [ 5, 10, 20, 30, 40, 50 ],
			columns : [ [ {
				field : "platName",
				title : "平台",
				width : 120,
				sortable : true
			} ] ],
			keyHandler : {
				up : function() { },
				down : function() { },
				enter : function() { },
				query : function(q) {
					if (q != null && q != "") {
						$("#platId").combogrid("grid").datagrid(
								"reload", {
									"filter" : q
								});
						$("#platId").combogrid("setValue", q);
					}
				}
			},onSelect: function (rowIndex, rowData){
			    if(rowData != null && rowData.id != null){
			        $.ajax({
	    				type: "POST",
	    				cache: false,
	    				url: "${pageContext.request.contextPath}/plats/readChannel.do",
	    				data:"id="+rowData.id,
	    				success:function(data){
	    				    document.getElementById("channel").options.length=0;
	    				    if(data != null&& data.length > 0){
	    				        $.each(data, function(i, value) {
	    				            $("#channel").append("<option value='"+value.id+"'>"+value.channels+"</option>");
		    				    });
	    				    }else{
	    				        $("#channel").append("<option value='-1'>无</option>");
	    				    }
	    				}
	    			});
			    }
			} 
		});
		
		$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/platManager/readBaseTabCommon.do",
    		data: "",
    		cache: false
    	}).done(function (data) {
    		if(data.data.length > 0){
    			$(data.data).each(function(index,value){
	    			var $self=$(this);
	    			$(".right-twoRight .rspan ul").append("<li><a href='${pageContext.request.contextPath}/actoresOnline/showOnline.do?branchs="+$self[0].opkey+"'>"+ $self[0].opName+"</a></li>")
	    		});
	    		$(".right-twoRight .regspan").show();
    			$(".right-twoRight .rspan").show();
    		}
    	});
    	$(".right-twoRight .rspan>span").click(function(){
			if (!$(".right-twoRight .rspan ul").is(":animated")) {
				$(".right-twoRight .rspan ul").fadeToggle();
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
			<span>人事系统/平台人员</span> 
			<span>></span>
			<span class="color99">线上艺人</span>
		</div>
		<div class="clearFix right-twoRight">
			<div class="fr">
				<span class="regspan">查看分公司：</span>
				<div class="rspan"><span>${branchsMsg}</span><ul></ul></div>
				<div class="search">
		    		<input id="fast" type="text" placeholder="姓名搜索">
			    	<button type="button" id="seach" class="tableok">搜索</button>				
				</div>
				<input class="searchAll" type="button" value="高级搜索"/>
			</div>
		</div>
		    		
		<div class="control">
			<jsp:include page="attsCommon.jsp"></jsp:include> 
		</div>
		<!-- 高级搜索 -->
			<form action="${pageContext.request.contextPath}/emp/search.do" method="post" enctype="multipart/form-data" id="sousuo" style="display:none;margin-left:20px;margin-right:20px;">
		
				<table class="table">
					<tr>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					</tr>
					<tr>
					<tr>
						<td colspan="4"><p style=" margin: 0">基本资料</p>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">姓名：</td>
						<td ><input type="text" name="realname" id="srealname" style="width:200px; height:26px;"></td>
						<td style="text-align: right;">艺名：</td>
						<td><input type="text" name="aliasname" id="saliasname" style="width:200px; height:26px;"></td>
					</tr>
					<tr>
						<td style="text-align: right;">电话：</td>
						<td colspan="" ><input type="text" name="phone" id="sphone" style="width:200px; height:26px;"></td>
					</tr>
		
				</table>
				<p class="bCenter">
					<input type="hidden" name="id" id="id"> <span class="koko">提交</span> <input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</form>
		<div class="custom-scroll thistable"  style="overflow:auto; background-color:#fff; ">
			<table id="searchTable"class="table tablelist"> 
				<tr class="firsttr"> 
			        <th w_index="id" w_align="center"width="10%;"w_hidden="true">id</th>
			        <th w_num="line" w_align="center" width="10%;">序号</th>
			       <th w_index="realname" w_align="center"width="1%;">姓名</th> 
			        <th w_index="aliasname" w_align="center"width="10%;">艺名</th>
			        <th w_index="sex" w_align="center"width="10%;">性别</th> 
			        <th w_edit="linkUrl" w_index="linkUrl" w_align="center"width="10%;">主播视频</th> 
			        <th  w_index="platNames" w_align="center"width="1%;">所在平台</th>
			        <th w_index="remark" w_align="center">备注</th> 
		   		</tr>
		</table>
		
		<form id="actoresForm" style="display:none;margin-left:20px;margin-right:20px;">
			<input type="hidden" name="id" id="id"> 
			<p>请输入员工号或真实姓名</p>
			<p>
				<input type="text" name="name" id="name" style="width:200px; height:26px; " maxlength="400" class="input">
				<span class="next" style="margin-left:20px;border: 1px solid #ddd;text-align: center;height: 30px;line-height: 30px;display: inline-block;color: #fff;background-color: #599eeb;width: auto;padding: 0px 20px;border-radius: 5px;margin-right: 10px;cursor: pointer;">搜索</span>
			</p>
			<div class="custom-scroll" style="position:relative;margin-top:20px;height:150px;overflow:auto;padding:10px 20px;;border:1px solid #ddd">
			<table style="width:100%" class="yirentable">
				<tr class="firsttr">
					<th hidden>id</th>
					<th width="33%">员工号</th>
					<th width="33%">真实姓名</th>
					<th width="34%">艺名</th>
				</tr>
			</table>
			</div>
			<p><input type="hidden" name="id" id="id" value="">
			<span class="next2" style="margin-right:20px;border: 1px solid #ddd;text-align: center;height: 38px;line-height: 38px;display: inline-block;color: #fff;background-color: #599eeb;width: 90px;padding: 0px 20px;border-radius: 5px;margin-right: 10px;cursor: pointer;">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="cancel" type="reset" name="Input" value="关闭"></p>
		</form>
		<!-- 新增 -->
		<form action="${pageContext.request.contextPath}/actoresOnline/saveActores.do" method="post" enctype="multipart/form-data" id="actoresSaveForm" style="display:none;margin-left:20px;margin-right:20px;">
					<table class="table">
						<tr>
							<td colspan="2"style="width:50%;padding:0;border:none;"></td>
							<td colspan="2"style="width:50%;padding:0;border:none;"></td>
						</tr>
						<tr><td style="text-align: right;">真实姓名：</td>
							<td><input type="text" name="realname" id="realname" style="width:200px; height:26px;"></td><td style="text-align: right;">QQ：</td>
							<td><input  type="text" name="qq" id="qq"  style="width:200px; height:26px;"></td> 
						</tr>
						<tr>
							<td style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
							<td><input type="text" name="phone" id="phone" style="width:200px; height:26px;"  onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
							
							<td style="text-align: right;">艺名：<label style="color: red;">*</label></td>
							<td><input type="text" name="aliasname" id="aliasname" style="width:200px; height:26px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;">身份证号：</td>
							<td ><input type="text" name="idcard" id="idcard" style="width:200px; height:26px;"></td> 
							<td style="text-align: right;">性别：<label style="color: red;">*</label></td>
							<td>
								<select id="sex" name="sex">
									<option value="WOMAN">女</option>
									<option value="MAN">男</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">艺人类型：<label style="color: red;">*</label></td>
							<td>
								<select id="genre" name="genre">
									<option value="ONLINE">线上艺人</option> 
								</select>
							</td>
							<td style="text-align: right;">保底 ：<label style="color: red;">*</label></td>
							<td><input type="text" name="minimumGuarantee" id="minimumGuarantee" class="easyui-numberbox input" style="width:200px; height:26px;"></td>
							
						</tr> 
						<tr>
							<td style="text-align: right;">提成比例：<label style="color: red;">*</label></td>
							<td><input id="pushMoney" name="pushMoney" class="easyui-numberbox input" value="0" style="width:190px; height:26px; ">%</td>
							<td style="text-align: right;">经济签约：<label style="color: red;">*</label></td>
							<td>
								<select id="signed" name="signed" style="width:200px; height:26px;">
									<option value="NO">否</option>
									<option value="YES">是</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">身份证扫描件：</td>
							<td><input  type="file" name="idImage" id="idImage"  style="width:200px; height:26px;"></td>  
							<td style="text-align: right;">入职时间 ：<label style="color: red;">*</label></td>
							<td><input type="text" class="easyui-datebox" editable="false" name="entryTime" id="entryTime" style="width:200px; height:26px;"></td>
						</tr>   
						<tr>
							<td style="text-align: right;">工资卡帐号：</td>
							<td><input type="text" name="bankCard" id="bankCard" style="width:200px; height:26px;"></td>
							<td style="text-align: right;">工资卡开户行：</td>
							<td><input type="text" name="bankAddress" id="bankAddress" style="width:200px; height:26px;"></td>
						</tr> 
						<tr>
							<td style="text-align: right;">分成账号(艺人)：</td>
							<td><input type="text" name="shareBankCard" id="shareBankCard" style="width:200px; height:26px;"></td>
							<td style="text-align: right;">分成卡开户行：</td>
							<td><input type="text" name="shareBankAddress" id="shareBankAddress" style="width:200px; height:26px;"></td>
						</tr>
					     
						<tr>
							<td style="text-align: right;">现居地址：</td>
							<td><input type="text" name="address" id="address" style="height:26px; width:200px;  "></td>
						</tr>
						<hr/>
						<tr>
							<td style="text-align: right;">视频链接：</td>
							<td colspan="3"><input type="text" name="linkUrl" id="linkUrl" style="width:500px; height:26px; margin-left: -80px;"></td>
						</tr>
						<tr>
							<td colspan="4"><p>开播信息</p></td>
						</tr>
						<tr>
							<td style="text-align: right;">直播平台：<label style="color: red;">*</label></td>
							<td><input name="plat" id="plat" style="width:200px; height:26px;"></td>
							<td style="text-align: right;">所属频道：<label style="color: red;">*</label></td>
							<td><select id="channel" name ="channel" style="width:200px; height:26px;"></select></td>
						</tr>
						<tr>
							<td style="text-align: right;">开播日期 ：<label style="color: red;">*</label></td>
							<td><input type="text" class="easyui-datebox" editable="false" name="beliveTime" id="beliveTime" style="width:200px; height:26px;"></td>
						</tr>   
						
						<tr>
							<td style="text-align:center;" colspan="4">
								<input type="hidden" name="id" id="id">
								<input type="hidden" name="empid" id="empid">
								<input type="hidden" name="type" id="type">
								<input type="hidden" name="genrer" id="genrer"> 
								<input type="hidden" name="loadTable" id="loadTable">
								<input type="hidden" name="platId" id="platId">
								<p class="bottom">
									<span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="cancel" type="reset" name="input" id="input" value="关闭">
								</p>
							</td>
						</tr>
					</table>
		</form>
		
		
		
		<!-- 修改 -->
		<form action="${pageContext.request.contextPath}/actoresOnline/saveActores.do" method="post" enctype="multipart/form-data" id="actoresUpdateForm" style="display:none;margin-left:20px;margin-right:20px;">
					<table class="table">
						<tr>
							<td colspan="2"style="width:50%;padding:0;border:none;"></td>
							<td colspan="2"style="width:50%;padding:0;border:none;"></td>
						</tr>
						<tr><td style="text-align: right;">真实姓名：</td>
							<td><input type="text" name="realname" id="realname" style="width:200px; height:26px;"></td><td style="text-align: right;">QQ：</td>
							<td><input  type="text" name="qq" id="qq"  style="width:200px; height:26px;"></td> 
						</tr>
						<tr>
							<td style="text-align: right;">联系电话：<label style="color: red;">*</label></td>
							<td><input type="text" name="phone" id="phone" style="width:200px; height:26px;"  onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"></td>
							
							<td style="text-align: right;">艺名：<label style="color: red;">*</label></td>
							<td><input type="text" name="aliasname" id="aliasname" style="width:200px; height:26px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;">身份证号：</td>
							<td ><input type="text" name="idcard" id="idcard" style="width:200px; height:26px;"></td> 
							<td style="text-align: right;">性别：<label style="color: red;">*</label></td>
							<td>
								<select id="sex" name="sex"  style="width:200px; height:26px; box-shadow: 0 0 6px #fbfbfb; border: 1px solid #ddd; border-radius: 2px; ">
									<option value="WOMAN">女</option>
									<option value="MAN">男</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">艺人类型：<label style="color: red;">*</label></td>
							<td>
								<select id="genre" name="genre" style="width:200px; height:26px;">
									<option value="ONLINE">线上艺人</option> 
								</select>
							</td>
							<td style="text-align: right;">保底 ：<label style="color: red;">*</label></td>
							<td><input type="text" name="minimumGuarantee" id="minimumGuarantee" class="easyui-numberbox input" style="width:200px; height:26px;"></td>
							
						</tr> 
						<tr>
							<td style="text-align: right;">提成比例：<label style="color: red;">*</label></td>
							<td><input id="pushMoney" name="pushMoney" class="easyui-numberbox input" value="0" style="width:150px; margin-left:-36px; ">%</td>
							<td style="text-align: right;">经济签约：<label style="color: red;">*</label></td>
							<td>
								<select id="signed" name="signed" style="width:200px; height:26px;">
									<option value="NO">否</option>
									<option value="YES">是</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">身份证扫描件：</td>
							<td><input  type="file" name="idImage" id="idImage"  style="width:200px; height:26px;"></td>  
							<td style="text-align: right;">入职时间 ：<label style="color: red;">*</label></td>
							<td><input type="text" class="easyui-datebox" editable="false" name="entryTime" id="entryTime" style="width:200px; height:26px;"></td>
						</tr>   
						<tr>
							<td style="text-align: right;">工资卡帐号：</td>
							<td><input type="text" name="bankCard" id="bankCard" style="width:200px; height:26px;"></td>
							<td style="text-align: right;">工资卡开户行：</td>
							<td><input type="text" name="bankAddress" id="bankAddress" style="width:200px; height:26px;"></td>
						</tr> 
						<tr>
							<td style="text-align: right;">分成账号(艺人)：</td>
							<td><input type="text" name="shareBankCard" id="shareBankCard" style="width:200px; height:26px;"></td>
							<td style="text-align: right;">分成卡开户行：</td>
							<td><input type="text" name="shareBankAddress" id="shareBankAddress" style="width:200px; height:26px;"></td>
						</tr>
					     
						<tr>
							<td style="text-align: right;">现居地址：</td>
							<td><input type="text" name="address" id="address" style="width:200px; height:26px; "></td>
						</tr>
						<tr>
							<td style="text-align: right;">视频链接：</td>
							<td><input type="text" name="linkUrl" id="linkUrl" style="width:200px;"></td>
						</tr>
						<tr>
							<td style="text-align:center;" colspan="4">
								<input type="hidden" name="id" id="id">
								<input type="hidden" name="empid" id="empid">
								<input type="hidden" name="type" id="type">
								<input type="hidden" name="genrer" id="genrer"> 
								<input type="hidden" name="loadTable" id="loadTable">
								<input type="hidden" name="platId" id="platId">
								<p>
									<span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
									<input class="cancel" type="reset" name="Input" value="关闭">
								</p>
							</td>
						</tr>
					</table>
		</form>
		
		<!--  -->
		   <form action="${pageContext.request.contextPath}/actoresOnline/updateActoresLeave.do" method="post" id="leaveForm" style="display:none;margin-left:20px;margin-right:20px;">
		        <p>离职原因：</p>
		        <input type="hidden" name="id" id="id">
		        <input type=hidden name="type" id="type" value="2">
		        <input type=hidden name="t" id="t" value="ONLINE">
		        <p><textarea name="reason" id="reason" style="width:230px; height:100px;"></textarea></p> 
		        <p class="bCenter"><input class="ok"  type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
		    </form>
		
			<!-- 查看详细 -->
		
			<div id="emplook" style="margin-left: 20px; margin-right: 20px; display:none;">
				<table class="table2">
					<tr>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
						<td colspan="2" style="width:50%;padding:0;border:none;"></td>
					</tr>
					<tr>
						<td colspan="4" class="yellowline"><p style=" margin: 0">基本资料</p>
						</td>
					</tr> 
					<input type="hidden" id="id">
					
					<tr>
						<td style="text-align: right;" class="hei">真实姓名：</td>
						<td><span id="vrealname" class="w200"></span>
						</td>
						<td style="text-align: right;" class="hei">	QQ：</td>
						<td><span id="vqq"></span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">联系电话：</td>
						<td><span id="vphone" class="w200">男 </span></td>
						<td style="text-align: right;" class="hei">艺名：</td>
						<td><span id="valiasname" class="w200"></span>
						</td>
		
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">身份证号：</td>
						<td><span id="vidcard" class="w200"></span></td>
						<td style="text-align: right;" class="hei">入职时间：</td>
						<td><span id="ventryTime" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">身份证扫描件</td>
						<td colspan="3">
							<span><img  id="image" width='220' height='80'/></span><span style="text-align: right;" ><button id="selImage">查看原图</button></span>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">联系电话：</td>
						<td><span id="vphone" class="w200"></span></td>
						<td style="text-align: right;" class="hei">性别：</td>
						<td><span id="vsex" class="w200"></span>
						</td>
					</tr>
		
					<tr>
						<td style="text-align: right;" class="hei">艺人类型：</td>
						<td><span id="vgenre" class="w200"></span></td>
						<td style="text-align: right;" class="hei">保底：</td>
						<td><span id="vminimumGuarantee" class="w200"></span></td>
					</tr>
		
					<tr>
						<td style="text-align: right;" class="hei">提成比例：</td>
						<td><span id="vpushMoney" class="w200"></span></td>
						<td style="text-align: right;" class="hei">经济签约 ：</td>
						<td><span id="vsigned" class="w200"></span></td>
					</tr>
		 
					<tr>
						<td style="text-align: right;" class="hei">工资卡帐号：</td>
						<td><span id="vbankCard" class="w200"></span></td>
						<td style="text-align: right;" class="hei">开户地址：</td>
						<td><span id="vbankAddress" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">分成账号(艺人)：</td>
						<td><span id="vshareBankCard" class="w200"></span></td>
						<td style="text-align: right;" class="hei">开户行：</td>
						<td><span id="vshareBankAddress" class="w200"></span></td>
					</tr>
					<tr>
						<td style="text-align: right;" class="hei">现居地址：</td>
						<td colspan="3"><span id="vaddress" class="w200"></span></td>
						</td>
					</tr> 
					<tr>
						<td style="text-align: right;">视频链接：</td>
						<td colspan="3"><span id="vlinkUrl" class="w200"></span></td>
					</tr> 
		
				</table>
				<p style="text-align:center">
					<input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</div>
			
			<div id="idlook" style="margin-left: 20px; margin-right: 20px; display:none;">
				<table class="table2">
					<tr>
						<td >
							<img  width='600' height='400'  id="yuanImage"/>
						</td>
					</tr>
				</table>
				<p style="text-align:center">
					<input class="cancel" type="reset" name="Input" value="关闭">
				</p>
			</div>
		</div>
	</div>

</body>
</html>
