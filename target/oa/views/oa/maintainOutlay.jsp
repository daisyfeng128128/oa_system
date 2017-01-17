<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>频道充值</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;}
        .yirentable tr {height: 30px;line-height: 30px;}
		.yirentable tr:hover {background-color: #ddd;}
        .yirentable .firsttr:hover {background-color: #fff;}
        .yirentable .selected {background-color: #ddd;}
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
        
            var calculateshuzu = [];
            $("#upLoad").hide();
            //新增
            $(".thistable .add").on('click',function () {
                var $chuangkou = $("#actoresForm");
                $(".yirentable tr").not(".firsttr").remove()
                $.basewindow("新建支出", $chuangkou, 500, 430);
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
                        if (data.resultStatus == 200) {
							$(data.data).each(function () {
                                var $self = $(this);
                                $("#actoresForm select").append("<option value=" + $self[0].id + ">" + $self[0].channels + "</option>")
                            });
                        }
                    }).error(function (jqXHR, textStatus, errorThrown) { });
                }
            });
            //搜索
            $("#actoresForm .next").click(function () {
                var $self = $(this);
                var data = {
                    name: $self.prev("input").val(),
                    loadTable: "false",
                    seachEmp: "true",
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
                    if(data.length>0){
                    $(data).each(function (index) {
                        var $self = $(this)[0];
                        if (index == 0) {
                            $(".yirentable").append("<tr class='selected'><td hidden>" + $self.id + "</td><td hidden>" + $self.loadTable + "</td><td>" + $self.num + "</td><td>" + $self.name + "</td><td>" + $self.aliasname + "</td></tr>")
                        }
                        else {
                            $(".yirentable").append("<tr><td hidden>" + $self.id + "</td><td hidden>" + $self.loadTable + "</td><td>" + $self.num + "</td><td>" + $self.name + "</td><td>" + $self.aliasname + "</td></tr>")
                        }
                    });
                    }else{
                    	$.threetop("未搜索到结果");
                    }
                }).error(function (jqXHR, textStatus, errorThrown) { });
            });
            //判断搜索是否被选中
            $(".yirentable").delegate("tr:not('.firsttr')", "click", function () {
                var $self = $(this);
                if ($self.hasClass("selected")) {
                }
                else {
                    $self.siblings("tr").removeClass("selected");
                    $self.addClass("selected");
                }
            });
            //新增第二步
            var number, employeeId, channelId, platformsId, platforms, name, channel;
            $("#actoresForm .next2").click(function () {
                var $self = $(this);
                number = $(".yirentable .selected td").eq(2).text();
                employeeId = $(".yirentable .selected td:first").text();
                channelId = $("#actoresForm #xxx option:selected").val();
                platforms = $(".right-onehalf .spanchange").text();
                platformsId = $(".right-onehalf .spanchange").attr("value");
                //显示
                name = $(".yirentable .selected td").eq(3).text();
                channel = $("#actoresForm #xxx option:selected").text();
                if (employeeId == "" || employeeId == undefined) {
                    $.threetop("请先选择支出人!");
                }
                else {
                    $("#actoresForm").appendTo($("body")).hide();
                    $.basewindow("新增支出", "#actoresSaveForm", 820, 350);
                    $("#actoresSaveForm #name").val(name).attr({ "disabled": "disalbed" });
                    $("#actoresSaveForm #channel").val(channel).attr({ "disabled": "disalbed" });
                }
            });
            //提交
            $("#actoresSaveForm .okok").click(function () {
                //判断xxxx
                var data = {
                	months:{id:"${mId}" == null ? "" : "${mId}"},
                    number: number,
                    employeeId: employeeId,
                    channelId: channelId,
                    platforms: platforms,
                    platformsId: platformsId,
                    //显示
                    name: name,
                    channel: channel,
                    outlay: $("#actoresSaveForm #outlay").val(),
                    topup: $("#actoresSaveForm #topup").val(),
                    restitution: $("#actoresSaveForm #restitution").val(),
                    remarks: $("#actoresSaveForm #remarks").val(),
                }
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/maintainOutlay/save.do ",
                    cache: false,
                    data: JSON.stringify(data),
                    contentType: 'application/json; charset=utf-8',
                }).done(function (data) {
                    $("#actoresSaveForm").appendTo($("body")).hide();
                    $("zhezhao-basewindow").hide();
                    window.location.reload();
                }).error(function (jqXHR, textStatus, errorThrown) { });
            });
            //input四舍五入
            $("#actoresSaveForm #topup,#actoresSaveForm #restitution").change(function () {
                var $self = $(this);
                $self.val(parseFloat($self.val()).toFixed(2));
            })
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
                })

            };
            //删除
            $(".thistable .del").on('click',function () {
                if ($(".tablelist .selected").length > 0) {
                    $.yesorno("是否删除？", 300, 160, function () {
                        var id = $(".tablelist .selected .id").text();                     
                        var delUrl ="${pageContext.request.contextPath}/maintainOutlay/delete.do"
                        //后台删除
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: delUrl,
                            data: "id=" + id ,//要发送的数据
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
                $(".thistable table .all .daxingchongzhi,.thistable table .all .pingdaofanhui,.thistable table .all .jingzhichu,.thistable table .all .shuidian").text(0);
                $(calculateshuzu).each(function () {
                    var listgerenliwu = 0, listzhuanhuifei = 0, listdaxingchongzhi = 0, listpingdaofanhui = 0, listjingzhichu = 0,listshuidian=0;

                    $(".thistable table ." + this).each(function () {
                        var $self = $(this);
                        var selfgerenliwu = parseFloat($self.find(".gerenliwu").text());
                        var selfzhuanhuifei = parseFloat($self.find(".zhuanhuifei").text());
                        var selfdaxingchongzhi = parseFloat($self.find(".daxingchongzhi").text());
                        var selfpingdaofanhui = parseFloat($self.find(".pingdaofanhui").text());
                        var selfjingzhichu = parseFloat($self.find(".jingzhichu").text());
						var selfshuidian = parseFloat($self.find(".shuidian").text());
                        listgerenliwu = listgerenliwu + selfgerenliwu;
                        listzhuanhuifei = listzhuanhuifei + selfzhuanhuifei;
                        listdaxingchongzhi = listdaxingchongzhi + selfdaxingchongzhi;
                        listpingdaofanhui = listpingdaofanhui + selfpingdaofanhui;
                        listjingzhichu = listjingzhichu + selfjingzhichu;
                        listshuidian = listshuidian+selfshuidian;
                    })

                    //list
                    $(".thistable table ." + this + "list .daxingchongzhi").text(listdaxingchongzhi);
                    $(".thistable table ." + this + "list .pingdaofanhui").text(listpingdaofanhui);
                    $(".thistable table ." + this + "list .jingzhichu").text(listjingzhichu);
                    $(".thistable table ." + this + "list .shuidian").text(listshuidian);
                    //总
                    $(".thistable table .all .daxingchongzhi").text(parseFloat($(".thistable table .all .daxingchongzhi").text()) + listdaxingchongzhi);
                    $(".thistable table .all .pingdaofanhui").text(parseFloat($(".thistable table .all .pingdaofanhui").text()) + listpingdaofanhui);
                    $(".thistable table .all .shuidian").text(parseFloat($(".thistable table .all .shuidian").text()) + listshuidian);
                    $(".thistable table .all .jingzhichu").text(parseFloat($(".thistable table .all .jingzhichu").text()) + listjingzhichu);
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
                url: "${pageContext.request.contextPath}/maintainOutlay/readAllPlatformsMaintainOutlay.do",
                cache: false,
                data: "platId="+$(".right-onehalf .spanchange").attr("value") + "&mId=${mId}&branchs=${branchs}&date=" + $(".riqi>span").text(),
            }).done(function (data) {
                $(data).each(function (index) {
                    var $self = $(this); 
                    var name = !$self[0].name ? "" : $self[0].name;
                    var pingdaohao= !$self[0].channel ? "-" : $self[0].channel;
                    if (!$(".thistable table tr").hasClass(name)) {
                        calculateshuzu.push(name);
                        $(".thistable table .all").before(
                        '<tr class="' + name + 'list nolist" >' +
                        '<td colspan="4" style="font-weight:bold;">' + name + '个人支出合计</td>' +
                        '<td class="dottwo daxingchongzhi" style="font-weight:bold;color:#e35d00;"></td>' +
                        '<td class="dottwo pingdaofanhui" style="font-weight:bold;color:#0074ac;"></td>' +
                        '<td class="dottwo shuidian" style="font-weight:bold;color:#e35d00;"></td>' +
                        '<td class="dottwo jingzhichu" style="font-weight:bold;color:#e35d00"></td>' +
                        '<td class="beizhu"></td>' +
                        '</tr>' +
                        '<tr><td colspan="9" class="nolist"></td></tr>')
                    }
                    var daxingchongzhi = !$self[0].topup ? 0 : $self[0].topup;
                    var pingdaofanhui = !$self[0].restitution ? 0 : $self[0].restitution;
                    var shuidian = !$self[0].tax ? 0 : $self[0].tax;
                    var jingzhichu = !$self[0].netOutlay ? 0 : $self[0].netOutlay;
                    var beizhu = !$self[0].remarks ? "" : $self[0].remarks;

                    $("." + name + "list").before(
                        '<tr class="' + name + ' gen">' +
                       '<td class="xuhao" ></td>' +
                       '<td class="id" hidden>' + $self[0].id + '</td>' +
                       '<td class="zhichuren">' + $self[0].name + '</td>' +
                       '<td class="zhichuneirong">' + $self[0].outlay + '</td>' +
                       '<td class="pingdaohao">' +pingdaohao + '</td>' +
                       '<td class="dottwo daxingchongzhi"style="color:#e35d00">' + daxingchongzhi + '</td>' +
                       '<td class="dottwo pingdaofanhui" style="color:#0074ac">' + pingdaofanhui + '</td>' +
                       '<td class="dottwo shuidian" style="color:#e35d00">' + shuidian + '</td>' +
                       '<td class="dottwo jingzhichu" style="color:#e35d00">' + jingzhichu + '</td>' +
                       '<td class="beizhu">' + beizhu + '</td>' +
                       '</tr>')
                })
                 if ($(data).length > 0) {
                    tojisuan();
                    xuanzhong();
                }
 				  getXuhao();
            }).error(function (jqXHR, textStatus, errorThrown) { });
			//回车操作       
         $(window).keydown(function (e) {
              if (e.which == 13&& $("#actoresForm").css("display")=="block") {
            	  $("#actoresForm .next").click();
              }
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
       <%--  <jsp:include page="platCommon.jsp"></jsp:include> --%>

    <jsp:include page="tabCommon.jsp"></jsp:include>
    <div class="thistable custom-scroll">
        <table class="table tablelist">
            <tr class="nolist"><td colspan="10" style="text-align:left;font-weight:bold;">频道充值</td></tr>
            <tr class="nolist firsttr" style="background-color:#31383d; color:#ddd;">
                <td style="width:1%">序号</td>
                <td style="width:1%">支出人</td>
                <td style="width:1%">支出内容</td>
                <td style="width:1%">所属频道</td>
                <td style="width:1%">大型充值</td>
                <td style="width:1%">频道返回总额</td>
                <td style="width:1%">税点</td>
                <td style="width:1%">净支出总额</td>
                <td>备注</td>
            </tr>
            <tr class="nolist all">
                <td colspan="4" style="font-weight:bold;">费用支出总计</td>
                <td class="dottwo daxingchongzhi" style="font-weight: bold; color: #e35d00;">0.00</td>
                <td class="dottwo pingdaofanhui" style="font-weight: bold; color: #0074ac;">0.00</td>
                <td class="dottwo shuidian" style="font-weight: bold; color: #e35d00;">0.00</td>
                <td class="dottwo jingzhichu" style="font-weight:bold;color:#e35d00;">0.00</td>
                <td></td>
            </tr>
            <tr class="nolist">
            	<td colspan="11">
					<span class="tableok add">新增</span>
					<span class="tableok del">删除</span>
            	</td>
            </tr>
        </table>
    </div>
    <div id="actoresForm" style="display:none;margin-left:20px;margin-right:20px;">
        <input type="hidden" name="id" id="id">
        <p>请输入频道号</p>
        <p><select name="xxx" id="xxx" style="width:200px;" maxlength="400"></select></p>
        <p>请输入员工号或真实姓名</p>
        <p>
            <input type="text" name="name" id="name" style="width:200px;" maxlength="400" class="input">
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
        <p>
            <input type="hidden" name="id" id="id" value="">
            <span class="next2" style="margin-right:20px;border: 1px solid #ddd;text-align: center;height: 38px;line-height: 38px;display: inline-block;color: #fff;background-color: #599eeb;width: 90px;padding: 0px 20px;border-radius: 5px;margin-right: 10px;cursor: pointer;">添加</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="cancel" type="reset" name="Input" value="关闭">
        </p>
    </div>
    <form id="actoresSaveForm" style="display:none;margin-left:20px;margin-right:20px;">
        <table class="table">
            <tr>
                <td colspan="2" style="width:50%;padding:0;border:none;"></td>
                <td colspan="2" style="width:50%;padding:0;border:none;"></td>
            </tr>
            <tr>
                <td style="text-align: right;">支出人：<label style="color: red;">*</label></td>
                <td><input type="text" name="name" id="name" style="width:200px;"></td>
                <td style="text-align: right;">所属频道：<label style="color: red;">*</label></td>
                <td><input type="text" name="channel" id="channel" style="width:200px;"></td>
            </tr>
            <tr>
                <td style="text-align: right;">支出内容：<label style="color: red;">*</label></td>
                <td colspan="3"><input type="text" name="outlay" id="outlay" style="width: 589px;"></td>
            </tr>
            <tr>
                <td style="text-align: right;">大型充值：<label style="color: red;">*</label></td>
                <td><input type="text" name="topup" id="topup" style="width:200px;"></td>
                <td style="text-align: right;">频道返还：<label style="color: red;">*</label></td>
                <td><input type="text" name="restitution" id="restitution" style="width:200px;"></td>
            </tr>
            <tr>
                <td style="text-align: right;">备注：</td>
                <td colspan="3"><input type="text" name="remarks" id="remarks" style="width: 100%style="width:100%;min-width:200px;"></td>
            </tr>
            <tr>
                <td style="text-align:center;" colspan="4">
                    <p>
                        <button class="okok" type="button">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input class="cancel" type="reset" name="Input" value="关闭">
                    </p>
                </td>
            </tr>
        </table>
    </form>
    </div>
</body>
</html>