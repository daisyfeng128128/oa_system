<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>提交月报-线下/上管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
    	.genselected{background-color:#599eeb;}
        body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;}
        .auditMsg  {color:green}
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
            //需要计算的餐补和底薪
            var dixingjishu = [];
            var canbujishu = [];           
			var isold=false;	
			var isEdit =${isEdit};
			$("#upLoad").hide();
			
        	$.ajax({
        		type: "POST",
        		url: "${pageContext.request.contextPath}/outlay/readBaseTabCommon.do",
        		data: "platId="+$(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId=${mId}&branchs=${branchs}",
        		cache: false,
        	      async:false, 
        	}).done(function (data) {
        		if ((data.resultMessage == "等待业务负责人审批" && !isEdit) || data.resultMessage == "业务负责人审批通过,等待财务负责人审批" || data.resultMessage == "财务负责人审批通过") {
    				isold=true;
    			}	
        	}).error(function (jqXHR, textStatus, errorThrown) {});        
			
			//计算
			var dangyuetianshu=0;
            var tojisuan = function () {
                $(".thistable table .allup .heji").text(0);
                $(".thistable table .alldown .heji").text(0);
                $(".thistable table .allall .heji").text(0);
                $(calculateshuzu).each(function () {
                    var updixing = 0, upticheng = 0, upcanbu = 0, upqitabutie = 0, upkouxiang = 0;
                    var downdixing = 0, downticheng = 0, downcanbu = 0, downqitabutie = 0, downkouxiang = 0;
                    var upheji = 0, downheji = 0;
                    $(".thistable table ." + this).each(function () {
                        var $self = $(this);

                        if (!isold) {  
						//自己计算
                        var selfticheng = parseFloat($self.find(".ticheng input").val());
                        var selfqitabutie = parseFloat($self.find(".qitabutie input").val());
                        var selfkouxiang = parseFloat($self.find(".kouxiang input").val());
                        //特殊计算位置位置要正确
                        //需要计算的餐补和底薪  
                        var selftianshu = parseFloat($self.find(".tianshu input").val());
                        var selfcanbu = canbujishu[canbujishu.indexOf(parseFloat($self.find(".id").text())) + 1] * (selftianshu >= "${maxmum}" ? "${maxmum}" : selftianshu);
                       //selfcanbu=parseFloat(selfcanbu.toFixed(2));
                       if ($self.find(".guanlileixing").text() == "线上") {
                           var selfdixing = dixingjishu[dixingjishu.indexOf(parseFloat($self.find(".id").text())) + 1] / "30" * (selftianshu >= 30 ? "30"  : selftianshu)  ;
                       }else{
                           var selfdixing = dixingjishu[dixingjishu.indexOf(parseFloat($self.find(".id").text())) + 1] / "${maxmum}" * (selftianshu >= "${maxmum}" ? "${maxmum}"  : selftianshu)  ;
                       }
	                   	//selfdixing=parseFloat(selfdixing.toFixed(2));
						//
                        	var selfheji = (selfdixing*100  + selfticheng*100  +selfcanbu*100  + selfqitabutie*100  - selfkouxiang*100 )/100;
                        }else{
	                       //获取值
	                       var selfticheng = parseFloat($self.find(".ticheng").text());
	                       var selfqitabutie = parseFloat($self.find(".qitabutie").text());
	                       var selfkouxiang = parseFloat($self.find(".kouxiang").text());  
	                       var selftianshu = parseFloat($self.find(".tianshu").text());  
	                       var selfcanbu    = parseFloat($self.find(".canbu").text());  
	                       var selfdixing = parseFloat($self.find(".dixing").text());  
	                       var selfheji=parseFloat($self.find(".heji").text());  
                        }
						
                        if ($self.find(".guanlileixing").text() == "线上") {
                            //text
                            updixing = updixing + selfdixing;
                            upcanbu = upcanbu + selfcanbu;
                            //input
                            upticheng = upticheng + selfticheng;
                            upqitabutie = upqitabutie + selfqitabutie;
                            upkouxiang = upkouxiang + selfkouxiang;
                            //单挑数据总和
                            upheji = upheji + selfheji;
                            $self.find(".heji").text(selfheji);
                            //需要计算的餐补和底薪
                            $self.find(".canbu").text(selfcanbu);
                            $self.find(".dixing").text(selfdixing);
                        }
                        else if ($self.find(".guanlileixing").text() == "线下") {
                            //text
                            downdixing = downdixing + selfdixing;
                            downcanbu = downcanbu + selfcanbu;
                            //input
                            downticheng = downticheng + selfticheng;
                            downqitabutie = downqitabutie + selfqitabutie;
                            downkouxiang = downkouxiang + selfkouxiang;
                            //单挑数据总和
                            downheji = downheji + selfheji;
                            $self.find(".heji").text(selfheji);
                            //需要计算的餐补和底薪
                            $self.find(".canbu").text(selfcanbu);
                            $self.find(".dixing").text(selfdixing);
                        }

                    })
                    //线上and线下
                    $(".thistable table ." + this + "all .dixing").text(updixing + downdixing);
                    $(".thistable table ." + this + "all .ticheng").text(upticheng + downticheng);
                    $(".thistable table ." + this + "all .canbu").text(upcanbu + downcanbu);
                    $(".thistable table ." + this + "all .qitabutie").text(upqitabutie + downqitabutie);
                    $(".thistable table ." + this + "all .kouxiang").text(upkouxiang + downkouxiang);
                    $(".thistable table ." + this + "all .heji").text(upheji + downheji);
                    //总
                    $(".thistable table .allup .heji").text(parseFloat($(".thistable table .allup .heji").text()) + upheji);
                    $(".thistable table .alldown .heji").text(parseFloat($(".thistable table .alldown .heji").text()) + downheji);
                    $(".thistable table .allall .heji").text(parseFloat($(".thistable table .allall .heji").text()) + upheji + downheji);
                })
     			$(".dottwo").each(function () {
                    var $self = $(this);
                    if ($self.find("input").length > 0) {
                        $self.find("input").val((Math.round($self.find("input").val()*100)/100).toFixed(2));
                    }else {
                        $self.text((Math.round($self.text()*100)/100).toFixed(2));
                    }
                })
                }
            $("#cssTable").delegate(".gen","click",function(){
                var $self=$(this);
                $self.siblings(".gen").removeClass("genselected");
                $self.addClass("genselected");
            });
            //获取
            $.ajax({
                type: "POST", 
                url: "${pageContext.request.contextPath}/managerOutlay/readAllPlatformsManagerOutlay.do",
                cache: false,
                data: "platId=" + $(".right-onehalf .spanchange").attr("value")+"&date=${date}&mId=${mId}&online=${online}&branchs=${branchs}",
            }).done(function (data) {
            	$(data.data).each(function (index) {
                    var $self = $(this);
                    var pingdaohao = !$self[0].channel ? "-" : $self[0].channel;
                    if (!$(".thistable table tr").hasClass(pingdaohao + "all")) {
                        calculateshuzu.push(pingdaohao);
                        $(".thistable table .firsttr").after(
                        '<tr class="' + pingdaohao + 'all">' +
                        '<td colspan="8" style="font-weight:bold;">' + pingdaohao + '支出总计</td>' +
                        '<td class="dottwo dixing" style="font-weight:bold;color:#e35d00"></td>' +
                        '<td class="dottwo ticheng"style="font-weight:bold;color:#e35d00"></td>' +
                        '<td class="dottwo canbu"style="font-weight:bold;color:#e35d00"></td>' +
                        '<td class="dottwo qitabutie"style="font-weight:bold;color:#e35d00"></td>' +
                        '<td class="dottwo kouxiang" style="font-weight:bold;color:#0074ac"></td>' +
                        '<td class="dottwo heji" style="font-weight:bold;color:#e35d00;"></td>' +
                        '<td class="beizhu"></td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td colspan="15"></td>' +
                        '</tr>')
                    }

                    //input 
                    var yuangonghao = !$self[0].number ? "-" : $self[0].number;
                    var tianshu = !$self[0].attendance ? 0 : $self[0].attendance;
                    var ticheng = !$self[0].pushMoney ? 0 : $self[0].pushMoney;
                    var qitabutie = !$self[0].otherAllowance ? 0 : $self[0].otherAllowance;
                    var kouxiang = !$self[0].deduct ? 0 : $self[0].deduct;
                    var beizhu = !$self[0].remarks ? "" : $self[0].remarks;
                    var heji= !$self[0].total ? 0 : $self[0].total;
                    //需要计算的餐补和底薪
                    var dixing = !$self[0].realBasicSalary ? 0 : $self[0].realBasicSalary;
                    dixingjishu.push($self[0].id);
                    dixingjishu.push(!$self[0].basicSalary ? 0 : $self[0].basicSalary);
                    var canbu = !$self[0].realMealAllowance ? 0 : $self[0].realMealAllowance;
                    canbujishu.push($self[0].id);
                    canbujishu.push(!$self[0].mealAllowance ? 0 : $self[0].mealAllowance);
                    if (!isold) {
                    if ($self[0].genre == "OFFLINE") {
                        $("." + pingdaohao + "all").before(
                        '<tr class="' + pingdaohao + ' gen">' +
                        '<td class="xuhao"></td>' +
                        '<td class="id" hidden>' + $self[0].id + '</td>' +
                        '<td class="yuangonghao">' + yuangonghao + '</td>' +
                        '<td class="xingming">' + $self[0].name + '</td>' +
                        '<td class="yiming">' + $self[0].aliasname + '</td>' +
                        '<td class="guanlileixing">线下</td>' +
                        '<td class="guanligangwei">' + $self[0].positions + '</td>' +
                        '<td class="pingdaohao">' + pingdaohao + '</td>' +
                        '<td class="dottwo tianshu"><input type="number" style="width:100px;" value="' + tianshu + '" /></td>' +
                        '<td class="dottwo dixing" style="color:#e35d00">' + dixing + '</td>' +
                        '<td class="dottwo ticheng"><input type="number" style="width:100px;" value="' + ticheng + '" /></td>' +
                        '<td class="dottwo canbu" style="color:#e35d00">' + canbu + '</td>' +
                        '<td class="dottwo qitabutie"><input type="number" style="width:100px;" value="' + qitabutie + '" /></td>' +
                        '<td class="dottwo kouxiang"><input type="number" style="width:100px;" value="' + kouxiang + '" /></td>' +
                        '<td class="dottwo heji" style="color:#e35d00;">"'+heji+'"</td>' +
                        '<td class="beizhu"><input type="text" style="width:100%;" value="' + beizhu + '" /></td>' +
                        '</tr>')
                    }

                    if ($self[0].genre == "ONLINE") {
                        $("." + pingdaohao + "all").before(
                        '<tr class="' + pingdaohao + ' gen">' +
                        '<td class="id" hidden>' + $self[0].id + '</td>' +
                        '<td class="xuhao"></td>' +
                        '<td class="yuangonghao">' + yuangonghao + '</td>' +
                        '<td class="xingming">' + $self[0].name + '</td>' +
                        '<td class="yiming">' + $self[0].aliasname + '</td>' +
                        '<td class="guanlileixing">线上</td>' +
                        '<td class="guanligangwei">' + $self[0].positions + '</td>' +
                        '<td classs="pingdaohao">' + pingdaohao + '</td>' +
                        '<td class="dottwo tianshu"><input type="number" style="width:100px;" value="' + tianshu + '" /></td>' +
                        '<td class="dottwo dixing" style="color:#e35d00">' + dixing + '</td>' +
                        '<td class="dottwo ticheng"><input type="number" style="width:100px;" value="' + ticheng + '" /></td>' +
                        '<td class="dottwo canbu" style="color:#e35d00">' + canbu + '</td>' +
                        '<td class="dottwo qitabutie"><input type="number" style="width:100px;" value="' + qitabutie + '" /></td>' +
                        '<td class="dottwo kouxiang"><input type="number" style="width:100px;" value="' + kouxiang + '" /></td>' +
                        '<td class="dottwo heji" style="color:#e35d00;">"'+heji+'"</td>' +
                        '<td class="beizhu"><input type="text" style="width:200px;" value="' + beizhu + '" /></td>' +
                        '</tr>')
                    }
                    }
                    else{
                        if ($self[0].genre == "OFFLINE") {
                            $("." + pingdaohao + "all").before(
                            '<tr class="' + pingdaohao + ' gen">' +
                            '<td class="id" hidden>' + $self[0].id + '</td>' +
                       		'<td class="xuhao"></td>' +
                            '<td class="yuangonghao">' + yuangonghao + '</td>' +
                            '<td class="xingming">' + $self[0].name + '</td>' +
                            '<td class="yiming">' + $self[0].aliasname + '</td>' +
                            '<td class="guanlileixing">线下</td>' +
                            '<td class="guanligangwei">' + $self[0].positions + '</td>' +
                            '<td class="pingdaohao">' + pingdaohao + '</td>' +
                            '<td class="dottwo tianshu">' + tianshu + ' </td>' +
                            '<td class="dottwo dixing" style="color:#e35d00">' + dixing + '</td>' +
                            '<td class="dottwo ticheng"style="color:#e35d00">' + ticheng + '</td>' +
                            '<td class="dottwo canbu"style="color:#e35d00">' + canbu + '</td>' +
                            '<td class="dottwo qitabutie"style="color:#e35d00">' + qitabutie + '</td>' +
                            '<td class="dottwo kouxiang"style="color:#e35d00">' + kouxiang + '</td>' +
                            '<td class="dottwo heji" style="color:#e35d00;">'+heji+'</td>' +
                            '<td class="beizhu">' + beizhu + '</td>' +
                            '</tr>')
                        }

                        if ($self[0].genre == "ONLINE") {
                            $("." + pingdaohao + "all").before(
                            '<tr class="' + pingdaohao + ' gen">' +
                            '<td class="id" hidden>' + $self[0].id + '</td>' +
                        	'<td class="xuhao"></td>' +
                            '<td class="yuangonghao">' + yuangonghao + '</td>' +
                            '<td class="xingming">' + $self[0].name + '</td>' +
                            '<td class="yiming">' + $self[0].aliasname + '</td>' +
                            '<td class="guanlileixing">线上</td>' +
                            '<td class="guanligangwei">' + $self[0].positions + '</td>' +
                            '<td classs="pingdaohao">' + pingdaohao + '</td>' +
                            '<td class="dottwo tianshu">' + tianshu + '</td>' +
                            '<td class="dottwo dixing" style="color:#e35d00">' + dixing + '</td>' +
                            '<td class="dottwo ticheng" style="color:#e35d00">' + ticheng + '</td>' +
                            '<td class="dottwo canbu" style="color:#e35d00">' + canbu + '</td>' +
                            '<td class="dottwo qitabutie" style="color:#e35d00">' + qitabutie + '</td>' +
                            '<td class="dottwo kouxiang" style="color:#0074ac">' + kouxiang + '</td>' +
                            '<td class="dottwo heji" style="color:#e35d00;">'+heji+'</td>' +
                            '<td class="beizhu">' + beizhu + '</td>' +
                            '</tr>')
                        }	
                    }
                })
                if ($(data.data).length > 0) {
                    tojisuan();
                }
                getXuhao();
            }).error(function (jqXHR, textStatus, errorThrown) { });

            $(".thistable table").delegate("tr td input", "blur", function () {
                tojisuan();
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
                            pushMoney: $self.find(".ticheng input").val(),
                            otherAllowance: $self.find(".qitabutie input").val(),
                            deduct: $self.find(".kouxiang input").val(),
                            remarks: $self.find(".beizhu input").val()
                        };
                        alldata.push(data);
                    })
                });
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/managerOutlay/save.do",
                    data: JSON.stringify(alldata),
                    cache: false,
                    contentType: 'application/json; charset=utf-8',
                }).done(function (data) {
                    $.threesecond("保存成功", 200, 100, 2000);
                    setTimeout("location.reload();", 1000)
                }).error(function (jqXHR, textStatus, errorThrown) {
                });
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
    	<jsp:include page="tabCommon.jsp"></jsp:include>
	    
	    <div class="thistable custom-scroll">
	        <table class="table" id="cssTable">
	            <tr><td colspan="15" style="text-align:left;font-weight:bold;">管理支出</td></tr>
	            <tr class="firsttr" style="background-color:#31383d; color:#ddd;"> 
	                <td style="width:1%">序号</td>
	                <td style="width:1%">员工号</td>
	                <td style="width:1%">姓名</td>
	                <td style="width:1%">艺名</td>
	                <td style="width:1%">管理类型</td>
	                <td style="width:1%">管理岗位</td>
	                <td style="width:1%">频道号</td>
	                <td style="width:1%">出勤天数</td>
	                <td style="width:1%">底薪</td>
	                <td style="width:1%">提成</td>
	                <td style="width:1%">餐补</td>
	                <td style="width:1%">其他补贴</td>
	                <td style="width:1%">扣项</td>
	                <td style="width:1%">合计</td>
	                <td>备注</td>
	            </tr>
	             <tr class="allall">
	                <td colspan="8" style="font-weight:bold;">管理总支出</td> 
	                <td colspan="6" class="dottwo heji" style="font-weight:bold;color:#e35d00;">0.00</td>
	                <td></td>
	            </tr>
	        </table>
	        <p class="control">
	        	<jsp:include page="attsTabCommon.jsp"></jsp:include>
	        </p>
	    </div>
    </div>
</body>
