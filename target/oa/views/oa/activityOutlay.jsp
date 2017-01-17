<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>运营支出</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {
            height: 100%;
            min-width: 200px;
            overflow-x: hidden;
            position: relative;
        }
        .riqitext{
        	float: left;
		    margin-right: 20px;
		    margin-top: 6px;
		    display: block;
        }
        .riqi{
        	float: left;
		    width: 100px;
		    height: 30px;
		    line-height: 28px;
		    text-align: center;
		    border: 1px solid #ddd;
		    margin-top: 2px;
        }
        .riqi >span{
        	display: block;
		    cursor: pointer;
		    background-color: #fff;
		    width: 100%;
        }
        .riqi ul{
        	margin-left: -1px;
		    display: none;
		    position: fixed;
        }
        .riqi ul li{
        	background-color: #fff;
		    cursor: pointer;
		    margin-top: -1px;
		    width: 100px;
		    height: 30px;
		    line-height: 30px;
		    text-align: center;
		    border: 1px solid #ddd;
        }
        .regspan{
        	float: left;
		    display: block;
		    margin-top: 6px;
		    margin-left: 20px;
        }
        .rspan{
        	cursor: pointer;
		    line-height: 27px;
		    width: 100px;
		    text-align: center;
		    float: left;
		    display: block;
		    border: 1px solid #ddd;
		    margin-top: 2px;
        }
        .rspan span{
        	display: block;
		    cursor: pointer;
		    background-color: #fff;
		    width: 100%;
        }
        .rspan ul{
        	margin-left: -1px;
		    display: none;
		    position: fixed;
		}
        .rspan li{
        	background-color: #fff;
		    cursor: pointer;
		    margin-top: -1px;
		    width: 100px;
		    height: 30px;
		    line-height: 30px;
		    text-align: center;
		    border: 1px solid #ddd;
        }
        .right-onehalf{
        	border-bottom:1px solid #ddd;
        	margin-bottom:24px;
        }
        
        .right-onehalf .regs{
        	border:1px solid #e35d00;
        	background-color:#fff;
        }
        .right-onehalf .spanchange{
        	background-color: #e35d00;
        }
        
    </style>
    <script>
        $(function () {
            $("#upLoad").hide();
            var calculateshuzu = [];
            
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
            //新增
            $(".thistable .add").on('click',function () {
                var $chuangkou = $("#actoresForm");
                $.basewindow("新建运营支出", $chuangkou, 840, 300);
                $("#isTax")[0].checked = true;
                if ($("#actoresForm select option").length == 0) {
                    var data = {
                        id: $(".right-onehalf .spanchange").attr("value")
            		}
		         	$.ajax({
		            type: "POST",
		            url: "${pageContext.request.contextPath}/plats/getChannels.do",
		            cache: false,
		            data: JSON.stringify(data),
		            contentType: 'application/json; charset=utf-8',
		            }).done(function (data) {
		                if (data.resultStatus == 100) {
		                } else {
		                    $(data.data).each(function () {
		                        var $self = $(this);
		                        $("#actoresForm select").append("<option value=" + $self[0].id + ">" + $self[0].channels + "</option>")
		                    });
		                }
		            }).error(function (jqXHR, textStatus, errorThrown) { });
		        }
        	});
        	
        //提交
        $("#actoresForm .okok").click(function () {
            //判断xxxx
        var channel = $("#actoresForm #xxx option:selected").text();
        var channelId = $("#actoresForm #xxx option:selected").val();
        var platforms = $(".right-onehalf .spanchange").text();
        var platformsId = $(".right-onehalf .spanchange").attr("value");
        var checkbox = document.getElementById('isTax');
        var $tax;
        if(checkbox.checked){
            $tax=1;
        }else{
            $tax=0;
        }
        var data = {
	        months:{id:"${mId}" == null ? "" : "${mId}"},
	        channel: channel,
	        channelId: channelId,
	        platforms: platforms,
	        platformsId: platformsId,
	        //显示
	        activity: $("#actoresForm #activity").val(),
	        num: $("#actoresForm #num").val(),
	        totalMoney: $("#actoresForm #totalMoney").val(),
	        project: $("#actoresForm #project").val(),
	        awardsPersonnel: $("#actoresForm #awardsPersonnel").val(),
	        tax :$tax,
	        remarks: $("#actoresForm #remarks").val()
        }
        if( $("#actoresForm #activity").val()!=""){
		    $.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath}/activityOutlay/save.do",
		        cache: false,
		        data: JSON.stringify(data),
		        contentType: 'application/json; charset=utf-8',
		    }).done(function (data) {
		        $("#actoresForm").appendTo($("body")).hide();
		        $("zhezhao-basewindow").hide();
		        window.location.reload();
    		}).error(function (jqXHR, textStatus, errorThrown) { });
        }else{
        	$.inputchangea();
        	$.threetop("请输入支出内容")	;
        }
        });
        //input四舍五入
        $("#actoresForm #totalMoney,#actoresForm #num").change(function () {
            var $self = $(this);
            $self.val(parseFloat($self.val()).toFixed(2));
        });
        //选中
        var xuanzhong = function () {
            $(".thistable tr").not(".nolist").click(function () {
                var $self = $(this);
                if ($self.hasClass("selected")) {
                    $self.removeClass("selected");
                }
                else {
                    $self.siblings("tr").removeClass("selected");
                    $self.addClass("selected");
                }
            });
        };
        //删除
        $(".thistable .del").on('click',function () {
            if ($(".tablelist .selected").length > 0) {
                $.yesorno("是否删除？", 300, 160, function () {
                    var id = $(".tablelist .selected .id").text();
                    var delUrl = "${pageContext.request.contextPath}/activityOutlay/delete.do";
                    //后台删除
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: delUrl,
                        data: "id=" + id,//要发送的数据
                        success: function (data) {
                            if ("success" == data.resultMessage) {
                                $(".tablelist .selected").remove();
                                window.location.reload();
                            }
                        }, error: function (data) { }
                    });
                }, function () {
                    $.threesecond("看来还是手下留情了!", 200, 100, 1000);
                });
            } else {
                $.threesecond("请先选择", 200, 100, 1000);
            }
        });
        //计算
        var tojisuan = function () {
            $(".thistable table .all .zhoubian,.thistable table .all .dianka,.thistable table .all .heji").text(0);
            $(calculateshuzu).each(function () {
                var  listheji = 0,listshuidian = 0;

                $(".thistable table ." + this).each(function () {
                    var $self = $(this);
                    var selfheji = parseFloat($self.find(".heji").text());
                    var selfshuidian = parseFloat($self.find(".shuidian").text());
                    listshuidian = listshuidian + selfshuidian;
                    listheji = listheji + selfheji;

                })

                //list
                $(".thistable table ." + this + "list .heji").text(listheji);
              //list
                $(".thistable table ." + this + "list .shuidian").text(listshuidian);

                //总
                $(".thistable table .all .heji").text(parseFloat($(".thistable table .all .heji").text()) + listheji);
                $(".thistable table .all .shuidian").text(parseFloat($(".thistable table .all .shuidian").text()) + listshuidian);
            })
    			$(".dottwo").each(function () {
                    var $self = $(this);
                    if ($self.find("input").length > 0) {
                        $self.find("input").val((Math.round($self.find("input").val()*100)/100).toFixed(2));
                    }
                    else {
                        $self.text((Math.round($self.text()*100)/100).toFixed(2));
                    }
                })
        }
        //获取
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/activityOutlay/readAllPlatformsActivityOutlay.do",
            cache: false,
            data: "platId=" + $(".right-onehalf .spanchange").attr("value") + "&branchs=${branchs}&mId=${mId}&date=" + $(".riqi>span").text(),
        }).done(function (data) {
            $(data).each(function (index) {
                var $self = $(this); 
                var pingdaohao = !$self[0].channel ? "-" : $self[0].channel;
                if (!$(".thistable table tr").hasClass(pingdaohao)) {
                    calculateshuzu.push(pingdaohao);
                    $(".thistable table .all").before(
                    '<tr class="' + pingdaohao + 'list nolist" >' +
                    '<td colspan="3" style="font-weight:bold;">' + pingdaohao + '频道支出合计</td>' +
                    '<td class="dottwo heji"style="font-weight:bold;color:#e35d00;"></td>' +
                    '<td class="dottwo shuidian" style="font-weight:bold;color:#e35d00;"></td>' +
                    '<td class="huojiangrenyuan" style="font-weight:bold;">-</td>' +
                    '<td class="beizhu"></td>' +
                    '</tr>' +
                    '<tr><td colspan="8" class="nolist"></td></tr>')
                }
                var dianka = !$self[0].cards ? 0 : $self[0].cards;
                var heji = !$self[0].totalMoney ? 0 : $self[0].totalMoney;
                var shuliang= !$self[0].num ? 0 : $self[0].num;
                var jingzhichu = !  $self[0].project? "":  $self[0].project;
                var jingzhichu = !$self[0].netOutlay ? 0 : $self[0].netOutlay;
                var shuidian = !$self[0].tax ? 0 : $self[0].tax;
                var beizhu = !$self[0].remarks ? "" : $self[0].remarks;
                var awardsPersonnel = !$self[0].awardsPersonnel ? "-" : $self[0].awardsPersonnel;

                $("." + pingdaohao + "list").before(
                   '<tr class="' + pingdaohao + ' gen">' +
                   '<td class="xuhao"></td>' +
                   '<td class="id" hidden>' + $self[0].id + '</td>' +
                   '<td class="pingdaohao">' + pingdaohao + '</td>' +
                   '<td class="huodongneirong">' + $self[0].activity + '</td>' +
                   '<td class="dottwo heji" style="color:#e35d00">' + heji + '</td>' +
                   '<td class="dottwo shuidian" style="color:#e35d00">' + shuidian + '</td>' +
                   '<td class="huojiangrenyuan">' + awardsPersonnel + '</td>' +
                   '<td class="beizhu">' + beizhu + '</td>' +
                   '</tr>')
            })
               if ($(data).length > 0) {
                    tojisuan();
                    xuanzhong();
                }
            getXuhao();
        }).error(function (jqXHR, textStatus, errorThrown) { });
			
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
    	<%-- <div class="right-onehalf">
        <jsp:include page="platCommon.jsp"></jsp:include>
    </div> --%>
    <jsp:include page="tabCommon.jsp"></jsp:include>
    <div class="thistable custom-scroll" style="overflow:auto; background-color:#fff;">
        <table class="table tablelist">
            <tr class="nolist"><td colspan="7" style="text-align:left;font-weight:bold;">活动支出</td></tr>
            <tr class="firsttr" style="background-color:#31383d; color:#ddd;">
                <td style="width:1%">序号</td>
                <td style="width:1%">活动频道</td>
                <td style="width:1%">支出内容</td>
                <td style="width:1%">金额</td>
                <td style="width:1%">税点</td>
                <td style="width:1%">获奖人员</td>
                <td>备注</td>
            </tr>
            <tr class="nolist all">
                <td style="font-weight:bold;"><span></span>运营总支出</td>
                <td class="xiangmu" style="font-weight:bold;">-</td>
                <td class="shuliang" style="font-weight: bold;">-</td>
                <td class="dottwo heji" style="font-weight: bold; color: red">0</td>
                <td class="dottwo shuidian" style="font-weight: bold; color: red">0</td>
                <td style="font-weight:bold;">-</td>
                <td></td>
            </tr>
            <tr>
            <tr class="nolist">
                <td colspan="7">&nbsp;
<!--                     <span class="tableok add">新增</span> -->
<!--                     <span class="tableok del">删除</span> -->
                </td>
            </tr>
        </table>
    </div>
    <form id="actoresForm" style="display:none;margin-left:20px;margin-right:20px;">
        <table class="table">
            <tr>
                <td style="text-align: right;">频道号：</td>
                <td><select name="xxx" id="xxx" style="width:200px;" maxlength="400"></select></td>
                <td style="text-align: right;">支出内容：</td>
                <td><input name="activity" id="activity" style="width:200px;" class="inputerror" /></td>
            </tr>
            <tr>
                <td style="text-align: right;">项目：</td>
                <td><input name="project" id="project" style="width:200px;" /></td>
                <td style="text-align: right;">数量：</td>
                <td><input name="num" id="num" style="width:200px;" /></td>
            </tr>
            <tr>
                <td style="text-align: right;">金额：</td>
                <td>
                	<table style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;">
                		<tr>
                			<td>
	                			<table style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;">
		               				<input name="totalMoney" id="totalMoney" style="width:80px;" />
		               			</table>
                			</td>
                			<td>
                				<table style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;">
									<tr>
										<td style="border-right:0px solid #ddd;border-bottom:0px solid #ddd;text-align: right;">&nbsp;&nbsp;&nbsp;&nbsp;交税：</td>
										<td style="border:0;border-right:0px solid #ddd;border-bottom:0px solid #ddd;"><input type="checkbox" id="isTax" name="isTax" value="1">
										</td>
									</tr>
								</table>
                			</td>
                		</tr>
					</table>
                </td>
                <td style="text-align: right;">获奖人员：</td>
                <td><input name="awardsPersonnel" id="awardsPersonnel" style="width:200px;" /></td>
            </tr>
            <tr>
                <td style="text-align: right;">备注：</td>
                <td colspan="3"><input name="remarks" id="remarks" style="width:100%;;min-width:200px;" /></td>
            </tr>
            <tr>
                <td colspan="4">
                    <p style="text-align:center;">
                        <input type="hidden" name="id" id="id">
                        <span class="okok" style="margin">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input class="cancel" type="reset" name="Input" value="关闭">
                    </p>
                </td>

            </tr>
        </table>

    </form>
    </div>
</body>
</html>