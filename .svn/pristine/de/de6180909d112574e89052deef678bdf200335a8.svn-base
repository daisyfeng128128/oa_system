<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>线上艺人收支</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;}
        .auditMsg {color: #0074ac;}
        .genselected{background-color:#599eeb;}

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
            //需要计算的保底和提成
            var baodijishu = [];
            var tichengjishu = [];
            var isold = false;
            var isEdit =${isEdit};
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/outlay/readBaseTabCommon.do",
                data: "platId=" + $(".right-onehalf .spanchange").attr("value") + "&date=${date}&mId=${mId}&branchs=${branchs}",
                cache: false,
                async: false,
            }).done(function (data) {
                if ((data.resultMessage == "等待业务负责人审批" && !isEdit) || data.resultMessage == "业务负责人审批通过,等待财务负责人审批" || data.resultMessage == "财务负责人审批通过") {
                    isold = true;
                }
            }).error(function (jqXHR, textStatus, errorThrown) { });
            if(!isold){
			    $("#upLoad").hide();
			}
        	//序号
        	getXuhao=function(){ 
        		$(".gen").each(function(index){
        			 var $self=$(this);
        			 $self.find(".xuhao").html(index+1);
        		})
        	}
        	
        	$("#cssTable").delegate(".gen","click",function(){
                var $self=$(this);
                $self.siblings(".gen").removeClass("genselected");
                $self.addClass("genselected");
            });
            
            $("#cssTable").delegate(".gen","dblclick",function(){
			
            });
            
            var tojisuan = function () {
                $(".thistable table .all .shouru,.thistable table .all .shuidian,.thistable table .all .jingshouru,.thistable table .all .ticheng,.thistable table .all .yingkui,.thistable table .all .pingtaikouchu,.thistable table .all .gongsiliwu,.thistable table .all .pingtaibutie,.thistable table .all .laowufuwufei").text(0);
                $(calculateshuzu).each(function () {
                    var listshouru = 0, listshuidian = 0, listgongsiliwu = 0,listpingtaibutie = 0,listpingtaikouchu = 0 , listjingshouru = 0, listticheng = 0, listyingkui = 0,listlaowufuwufei = 0;

                    $(".thistable table ." + this).each(function () {
                        var $self = $(this);
                        if (!isold) {
                            //input	
                            var selfshouru = parseFloat($self.find(".shouru input").val());
                            var selfshuidian = parseFloat($self.find(".shuidian input").val());
                            var selfgongsiliwu = parseFloat($self.find(".gongsiliwu input").val()); 
                            var selfpingtaibutie  =parseFloat($self.find(".pingtaibutie input").val());
                            var selfpingtaikouchu = parseFloat($self.find(".pingtaikouchu input").val());
                            var selfjingshouru = parseFloat($self.find(".jingshouru").text());
                            var selftianshu = parseFloat($self.find(".tianshu").text());
                            var selfbaodi = parseFloat($self.find(".baodi").text());
                            var selfticheng = parseFloat($self.find(".ticheng").text());
                            var selfyingkui = parseFloat($self.find(".yingkui").text());
                            var selflaowufuwufei = parseFloat($self.find(".laowufuwufei").text()); 
                        }else {
                            var selfshouru = parseFloat($self.find(".shouru").text());
                            var selfshuidian = parseFloat($self.find(".shuidian").text());
                            var selfjingshouru = parseFloat($self.find(".jingshouru").text());
                            var selftianshu = parseFloat($self.find(".tianshu").text());
                            var selfbaodi = parseFloat($self.find(".baodi").text());
                            var selfticheng = parseFloat($self.find(".ticheng").text());
                            var selfyingkui = parseFloat($self.find(".yingkui").text());
                            var selfgongsiliwu = parseFloat($self.find(".gongsiliwu").text()); 
                            var selflaowufuwufei = parseFloat($self.find(".laowufuwufei").text()); 
                            var selfpingtaibutie  =parseFloat($self.find(".pingtaibutie").text());
                            var selfpingtaikouchu = parseFloat($self.find(".pingtaikouchu").text());
                            
                        }
                        //input
                        listshouru = listshouru + selfshouru;
                        listshuidian = listshuidian + selfshuidian;
                        listticheng = listticheng + selfticheng;
                        listgongsiliwu = listgongsiliwu + selfgongsiliwu;
                        listpingtaibutie = listpingtaibutie + selfpingtaibutie;
                        listpingtaikouchu = listpingtaikouchu + selfpingtaikouchu;
						listlaowufuwufei = listlaowufuwufei + selflaowufuwufei;
                        //单挑数据总和
                        listjingshouru = listjingshouru + selfjingshouru;
                        listyingkui = listyingkui + selfyingkui;
						
                    })

                    //list
                    $(".thistable table ." + this + "list .shouru").text(listshouru);
                    $(".thistable table ." + this + "list .shuidian").text(listshuidian);
                    $(".thistable table ." + this + "list .ticheng").text(listticheng);
                    $(".thistable table ." + this + "list .jingshouru").text(listjingshouru);
                    $(".thistable table ." + this + "list .laowufuwufei").text(listlaowufuwufei);
                    $(".thistable table ." + this + "list .yingkui").text(listyingkui);

                    $(".thistable table ." + this + "list .gongsiliwu").text(listgongsiliwu);
                    $(".thistable table ." + this + "list .pingtaibutie").text(listpingtaibutie);
                    $(".thistable table ." + this + "list .pingtaikouchu").text(listpingtaikouchu);
                    //总
                    $(".thistable table .all .shouru").text(parseFloat($(".thistable table .all .shouru").text()) + listshouru);
                    $(".thistable table .all .shuidian").text(parseFloat($(".thistable table .all .shuidian").text()) + listshuidian);
                    $(".thistable table .all .ticheng").text(parseFloat($(".thistable table .all .ticheng").text()) + listticheng);
                    $(".thistable table .all .gongsiliwu").text(parseFloat($(".thistable table .all .gongsiliwu").text()) + listgongsiliwu);
                    $(".thistable table .all .pingtaibutie").text(parseFloat($(".thistable table .all .pingtaibutie").text()) + listpingtaibutie);
                    $(".thistable table .all .pingtaikouchu").text(parseFloat($(".thistable table .all .pingtaikouchu").text()) + listpingtaikouchu);
                    $(".thistable table .all .laowufuwufei").text(parseFloat($(".thistable table .all .laowufuwufei").text()) + listlaowufuwufei);
                    $(".thistable table .all .jingshouru").text(parseFloat($(".thistable table .all .jingshouru").text()) + listjingshouru);
                    $(".thistable table .all .yingkui").text(parseFloat($(".thistable table .all .yingkui").text()) + listyingkui);
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
                url: "${pageContext.request.contextPath}/onlineIncome/readAllPlatformsOnlineIncome.do",
                cache: false,
                data: "platId=" + $(".right-onehalf .spanchange").attr("value") + "&branchs=${branchs}&date=${date}&mId=${mId}",
            }).done(function (data) {
                $(data).each(function (index) {
                    var $self = $(this); 
                    var pingdaohao = !$self[0].channel ? "-" : $self[0].channel;
                    if (!$(".thistable table tr").hasClass(pingdaohao + "list")) {
                        calculateshuzu.push(pingdaohao);
                        $(".thistable table .all").before(
                        '<tr class="' + pingdaohao + 'list">' 
                       /*  +'<td colspan="6" style="font-weight:bold;">' + pingdaohao + '线上收入合计</td>' +
                        '<td class="dottwo shouru" style="font-weight:bold;color:#0074ac;"></td>' +
                        '<td class="dottwo shuidian"style="font-weight:bold;color:#e35d00;"></td>' +
                        '<td class="dottwo gongsiliwu"style="font-weight:bold;color:#0074ac;"></td>' +
                        '<td class="dottwo pingtaibutie"style="font-weight:bold;color:#e35d00;"></td>' +
                        '<td class="dottwo pingtaikouchu"style="font-weight:bold;color:#0074ac;"></td>' +
                        '<td class="dottwo ticheng"style="font-weight:bold;color:#e35d00;"></td>' +
                        '<td class="dottwo laowufuwufei"  style="font-weight:bold;" ></td>' +
                        '<td class="dottwo jingshouru" style="font-weight:bold;color:#e35d00"></td>' +
                        '<td class="dottwo yingkui" style="font-weight:bold;color:#0074ac"></td>' +
                        '<td class="beizhu"></td>' +
                        '</tr>' +
                        '<tr><td colspan="16"></td></tr>' */)
                    }
                    //input
                    var tianshu = !$self[0].attendance ? 0 : $self[0].attendance >${maxmum} ?${maxmum} :$self[0].attendance  ;
                    var shouru = !$self[0].bgIncome ? 0 : $self[0].bgIncome;
                    var shuidian = !$self[0].deductTax ? 0 : $self[0].deductTax;
                    var gongsiliwu = !$self[0].restitutionGift ? 0 : $self[0].restitutionGift;
                    var pingtaibutie = !$self[0].platSubsidy ? 0 : $self[0].platSubsidy;
                    var pingtaikouchu = !$self[0].platDeduct ? 0 : $self[0].platDeduct;
                    var laowufuwufei = !$self[0].laborService ? 0 : $self[0].laborService;
                    var beizhu = !$self[0].remarks ? "" : $self[0].remarks;
                    //text
                    var jingshouru = !$self[0].netIncome ? 0 : $self[0].netIncome;
                    var yingkui = !$self[0].netProfit ? 0 : $self[0].netProfit;
                    //需要计算的保底和提成
                    var baodi = !$self[0].realMinimumGuarantee ? 0 : $self[0].realMinimumGuarantee;
                    baodijishu.push($self[0].id);
                    baodijishu.push(!$self[0].minimumGuarantee ? 0 : $self[0].minimumGuarantee);
                    var ticheng = !$self[0].realPushMoney ? 0 : $self[0].realPushMoney;
                    tichengjishu.push($self[0].id);
                    tichengjishu.push(!$self[0].pushMoney ? 0 : $self[0].pushMoney);
                    if (!isold) {
                        $("." + pingdaohao + "list").before(
                        '<tr class="' + pingdaohao + ' gen">' +
                        '<td class="xuhao" ></td>' +
                        '<td class="id" hidden>' + $self[0].id + '</td>' +
                        '<td class="tax" hidden>' + $self[0].tax + '</td>' +
                        '<td class="yiming">' + $self[0].aliasname + '</td>' +
                        '<td class="xingming">' + $self[0].name + '</td>' +
                        '<td class="pingdaohao">' + pingdaohao + '</td>' +
                        '<td class="dottwo tianshu"><input type="number" style="width:100px;" value="' + tianshu + '" /></td>' +
                        '<td class="dottwo baodi"style="color:#e35d00">' +  $self[0].basicSalaires + '</td>' +
                        '<td class="dottwo shouru"><input type="number" style="width:100px;" value="' + $self[0].bgIncome + '" /></td>' +
                        '<td class="dottwo shuidian"><input type="number" style="width:100px;" value="' +  $self[0].deductTax + '" /></td>' +
                        '<td class="dottwo gongsiliwu"><input type="number" style="width:100px;" value="' +  $self[0].restitutionGift + '" /></td>' + 
                        '<td class="dottwo pingtaibutie"><input type="number" style="width:100px;" value="' + $self[0].platSubsidy + '" /></td>' + 
                        '<td class="dottwo pingtaikouchu"><input type="number" style="width:100px;" value="' + $self[0].platDeduct + '" /></td>' + 
                       '<td class="dottwo ticheng" style="color:#e35d00">' + $self[0].pushMoneySalaries + '</td>' +
                       '<td class="dottwo laowufuwufei" >'+ $self[0].laborService +'</td>' +
                       '<td class="dottwo jingshouru"style="color:#e35d00">' + $self[0].actoresSalary  + '</td>' +
                        '<td class="dottwo yingkui"style="color:#0074ac">' + $self[0].netProfit + '</td>' +
                        '<td class="beizhu"><input type="text" style="width:100%;min-width:200px;" value="' + beizhu + '" /></td>' +
                        '</tr>')
                    }
                    else {
                        $("." + pingdaohao + "list").before(
                        '<tr class="' + pingdaohao + ' gen">' +
                        '<td class="xuhao" ></td>' +
                        '<td class="id" hidden>' + $self[0].id + '</td>' +
                        '<td class="tax" hidden>' + $self[0].tax + '</td>' +
                        '<td class="yiming">' + $self[0].aliasname + '</td>' +
                        '<td class="xingming">' + $self[0].name + '</td>' +
                        '<td class="pingdaohao">' + pingdaohao + '</td>' +
                        '<td class="dottwo tianshu">' + tianshu + '</td>' +
                        '<td class="dottwo baodi"style="color:#e35d00">' +  $self[0].basicSalaires + '</td>' +
                        '<td class="dottwo shouru" style="color:#0074ac">' +  $self[0].bgIncome + '</td>' +
                        '<td class="dottwo shuidian" style="color:#e35d00">' +  $self[0].deductTax + '</td>' +
                        '<td class="dottwo gongsiliwu" style="color:#0074ac">' + $self[0].restitutionGift + '</td>' +
                        '<td class="dottwo pingtaibutie" style="color:#e35d00">' + $self[0].platSubsidy + '</td>' +
                        '<td class="dottwo pingtaikouchu" style="color:#0074ac">' + $self[0].platDeduct + '</td>' +
                        '<td class="dottwo ticheng" style="color:#e35d00">' + $self[0].pushMoneySalaries + '</td>' +
                        '<td class="dottwo laowufuwufei">'+ $self[0].laborService +'</td>' +
                        '<td class="dottwo jingshouru"style="color:#e35d00">' + $self[0].actoresSalary + '</td>' +
                        '<td class="dottwo yingkui"style="color:#0074ac">' + $self[0].netProfit + '</td>' +
                        '<td class="beizhu">' + beizhu + '</td>' +
                        '</tr>')
                    }
                })
                if ($(data).length > 0) {
                    tojisuan();
                }
                getXuhao();
            }).error(function (jqXHR, textStatus, errorThrown) { });

            $(".thistable table").delegate("tr td input", "blur", function () {
                var $self = $(this).parent().parent('tr');
                var alldata = {
                        id: $self.find(".id").text(),
                        bgIncome: $self.find(".shouru input").val(),
                        deductTax: $self.find(".shuidian input").val(),
                        restitutionGift: $self.find(".gongsiliwu input").val(),
                        platSubsidy : $self.find(".pingtaibutie input").val(),
                        attendance:$self.find(".tianshu input").val(),
                        platDeduct : $self.find(".pingtaikouchu input").val()
                };
                
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/onlineIncome/edit.do",
                    data: JSON.stringify(alldata),
                    cache: false,
                    contentType: 'application/json; charset=utf-8'
                }).done(function (data) {
                    
                    $self.find(".baodi").text(data.basicSalaires);
                    $self.find(".ticheng").text(data.pushMoneySalaries);
                    $self.find(".laowufuwufei").text(data.laborService);
                    $self.find(".jingshouru").text(data.actoresSalary);
                    $self.find(".yingkui").text(data.netProfit);
                    tojisuan();
                }).error(function (jqXHR, textStatus, errorThrown) {
                });
            });
            
            //提交
            $(".thistable .save").click(function () {
                var alldata = [];
                $(calculateshuzu).each(function () {
                    $(".thistable table ." + this).each(function () {
                        var $self = $(this);
                        var data = {
                            id: $self.find(".id").text(),
                            attendance: $self.find(".tianshu input").val(),
                            bgIncome: $self.find(".shouru input").val(),
                            deductTax: $self.find(".shuidian input").val(),
                            restitutionGift: $self.find(".gongsiliwu input").val(),
                            platSubsidy : $self.find(".pingtaibutie input").val(),
                            platDeduct : $self.find(".pingtaikouchu input").val(),
                            remarks: $self.find(".beizhu input").val(),
                        };
                        alldata.push(data);
                    })
                });		



                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/onlineIncome/save.do",
                    data: JSON.stringify(alldata),
                    cache: false,
                    contentType: 'application/json; charset=utf-8',
                }).done(function (data) {
                    $.threesecond("保存成功", 200, 100, 2000);
                    setTimeout("location.reload();", 1000)
                }).error(function (jqXHR, textStatus, errorThrown) {
                });
            })
            
             /** 导出excel */
			$("#upLoad").click(function(){    
	            window.location.href="${pageContext.request.contextPath}/onlineIncome/exportExcel.do?date=${date}&mId=${mId}&platId="+$(".right-onehalf .spanchange").attr("value");    
	        })
	        
	         /** 导入excel */
	       	$(".thistable .impr").click(function(){
			    var $chuangkou = $("#uploadForm");
			    $.basewindow("导入Excel", $chuangkou,770, 220);
				$chuangkou.css({"display" : "block"});
            }) 
            
            $("#uploadForm .ok").click(function(){
			  $("#uploadForm").ajaxSubmit(function(data) {
					if (data.resultStatus == 200) {
						$(".zhezhao-basewindow").hide();
						$(".basewindow").hide();
						$('body').append($("#uploadForm"));
						$("#uploadForm").hide();
						setTimeout("location.reload();", 1000);
					}else if(data.resultStatus == 101){
						$('body').append($("#uploadForm"));
                        $("#uploadForm").hide();
                   		$(".zhezhao-basewindow").hide();
		                $(".basewindow").hide();
						var $chuangkou = $("#showNchoice");
						$("#showNchoice .table1 tr").empty();
		                $.basewindow("未导入的房间号", $chuangkou,450, 220);
		                var index = 0;
		                var totalNum = data.data.length;
		                var PERROW = 9;
		                var result =　"";
		                var preTr = "<tr>";
		                var endTr = "</tr>";
		                var tdStr = "<td>{0}</td>";
		                for(index = 1 ; index <= totalNum; index ++){
			            	if(1 == index){
			           			result += preTr;
			           		}
			            	
			            	result += tdStr.replace("{0}", data.data[index-1]);
			            	
			            	if(0 == index % PERROW){
			           			result += endTr;
			           			if(index != totalNum){
			           				result += preTr;
			           			}
			            	}
			            	
			            	if(index == totalNum){
			            		result += endTr;
			            	}
			            }
		                $(result).appendTo("#showNchoice .table1");
					}
			  })
			});
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
    <div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
        <table class="table" id="cssTable">
            <tr><td colspan="16" style="font-weight:bold;text-align:left">线上艺人收支</td></tr>
            <tr class="firsttr" style="background-color:#31383d; color:#ddd;">
                <td style="width:1%">序号</td>
                <td style="width:1%">主播艺名</td>
                <td style="width:1%">真实姓名</td>
                <td style="width:1%">频道</td>
                <td style="width:1%">出勤天数</td>
                <td style="width:1%">保底</td>
                <td style="width:1%">后台收入</td>
                <td style="width:1%">分成扣税</td>
                <td style="width:1%">礼物返还</td>
                <td style="width:1%">平台补贴</td>
                <td style="width:1%">平台扣除</td> 
                <td style="width:1%">主播提成</td>
                <td style="width:1%">劳务服务费</td>
                <td style="width:1%">主播实际收入</td>
                <td style="width:1%">实际盈亏</td>
                <td>备注</td>
            </tr>
            <tr class="all">
                <td colspan="6" style="font-weight:bold;">全频道合计</td>
                <td class="dottwo shouru" style="font-weight:bold;color:green;">0.00</td>
                <td class="dottwo shuidian" style="font-weight:bold;color: red">0.00</td>
                <td class="dottwo gongsiliwu" style="font-weight:bold;color: red">0.00</td>
                <td class="dottwo pingtaibutie" style="font-weight:bold;color: red">0.00</td>
                <td class="dottwo pingtaikouchu" style="font-weight:bold;color: red">0.00</td>
                <td class="dottwo ticheng" style="font-weight:bold;color: red">0.00</td>
                <td class="dottwo laowufuwufei" style="font-weight:bold;">0.00</td>
                <td class="dottwo jingshouru" style="font-weight: bold; color: red">0.00</td>
                <td class="dottwo yingkui" style="font-weight: bold; color: green">0.00</td>
                <td class="beizhu"></td>
            </tr>
        </table>
        <p class="control">
        	<jsp:include page="attsTabCommon.jsp"></jsp:include>
        </p>
    </div>
    <div id="showOnlines"></div>
    <div class="salaryLook" style="display:none">
			<form action="${pageContext.request.contextPath}/onlineIncome/upload.do" method="post" enctype="multipart/form-data" id="uploadForm" style="display:none;margin-left:20px;margin-right:20px;">
				<table class="table" > 
					<tr >
						<td>Excel</td><td><input type="file"  id= "upload" name = "upload" ><span class="mustred">*(格式:房间号、姓名、出勤天数、当月收入、分成扣税、公司礼物返还、平台补贴、平台扣税)</span></td> 
					</tr>
					<tr>
						<td style="text-align:center;" colspan="2">
							<p>
								<input class="ok" type="button" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
								<input class="cancel" type="reset" name="Input" value="关闭">
							</p>
						</td>
					</tr>
				</table> 
			</form>
		</div>
	<div id="showNchoice" >
				<table class="table1" > 
				</table> 
	</div>
    </div>
</body>
</html>