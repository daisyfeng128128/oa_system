<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>月报总表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <jsp:include page="common.jsp"></jsp:include>
    <jsp:include page="bootstarp.jsp"></jsp:include>
    <style>
        body {height: 100%;min-width: 200px;overflow-x: hidden;position: relative;}
        .lastMonths,.netIncome, .currentMonths, .growthRate,.onlineIncome,.offlineIncome,.lastOnlineIncome,.lastOfflineIncome,.onlineRate,.society,.anchors,.offlineRate {color: green;}
        .onlineOutlay, .offlineOutlay,.activity, .divident, .outlay,.actOnlineOutlay,.actOfflineOutlay,.maintain {color: red;}
        .son_ul{width:68px;overflow:hidden;border: 1px solid #fff;position: absolute;z-index: 9999}
        .son_ul li{line-height: 50px;padding-left: 10px;background-color: #ffffff;}
        .save{
        	box-shadow: inset 0 0 6px #f3c19f;
        }
        .riqitext{
        	float: left;
		    margin-right: 20px;
		    margin-top: 6px;
		    display: block;
        }
        .riqi{
        	float: right;
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
        .table td{
        	border-right: 1px solid #ddd;
        }
    </style>
</head>
<script>
    $(function () {
        $("#upLoad").hide();
        
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
        //获取数据
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/totaloutlay/readAllPlatformsMonthsOutlay.do?t=" + new Date(),
            data: "platId=" + $(".right-onehalf .spanchange").attr("value") + "&date=${date}&mId=${mId}",
            cache: false
        }).done(function (data) {
            var $data = data.data;
            $("#monthsId").val($data.id);
            $(".lastMonths").text($data.lastMonths);
            $(".currentMonths input").val($data.currentMonths);
            $(".growthRate").text($data.growthRate);
            $(".onlineIncome").text($data.onlineIncome);
            $(".offlineIncome").text($data.offlineIncome);
            $(".lastOnlineIncome").text($data.lastOnlineIncome);
            $(".lastOfflineIncome").text($data.lastOfflineIncome);
            $(".onlineRate").text($data.onlineRate);
            $(".offlineRate").text($data.offlineRate);
            
            $(".onlineOutlay").text($data.onlineOutlay);
            $(".offlineOutlay").text($data.offlineOutlay);
            $(".actOnlineOutlay").text($data.actOnlineOutlay);
            $(".actOfflineOutlay").text($data.actOfflineOutlay);
            $(".maintain").text($data.maintain);
            $(".laborService").text($data.laborService);
            $(".activity").text($data.activity);
            $(".divident input").val($data.divident);
            $(".outlay").text($data.outlay);
            $(".netIncome").text($data.netIncome);
            $(".transferIncome input").val($data.transferIncome);
            $(".selftop input").val($data.selftop);
            $(".society input").val($data.society);
            $(".anchors input").val($data.anchors);
			if($data.remarks != null){
				$($data.remarks.split("|")).each(function(){
					var self=this;
					 $("#"+self.split("_")[0]).val( self.split("_")[1]);
				});
			}
                $(".currentMonths").text($data.currentMonths);
                $(".transferIncome").text($data.transferIncome);
                $(".divident").text($data.divident);
                $(".selftop").text($data.selftop);
                $(".society").text($data.society);
                $(".anchors").text($data.anchors);
				
                $("#rbgincome").parent("td").text($("#rbgincome").val());
                $("#ronlineOutlay").parent("td").text($("#ronlineOutlay").val());
                $("#rofflineOutlay").parent("td").text($("#rofflineOutlay").val());
                $("#ractOnlineOutlay").parent("td").text($("#ractOnlineOutlay").val());
                $("#ractOfflineOutlay").parent("td").text($("#ractOfflineOutlay").val());
                
                $("#rthireOutlay").parent("td").text($("#rthireOutlay").val());
                $("#ronlineRate").parent("td").text($("#ronlineRate").val());
                $("#rofflineRate").parent("td").text($("#rofflineRate").val());
                $("#rtransferIncome").parent("td").text($("#rtransferIncome").val());
                $("#rmaintain").parent("td").text($("#rmaintain").val());
                $("#rlaborService").parent("td").text($("#rlaborService").val());
                $("#ractivity").parent("td").text($("#ractivity").val());
                $("#rdivident").parent("td").text($("#rdivident").val());
                $("#routlay").parent("td").text($("#routlay").val());
                $("#rnetIncome").parent("td").text($("#rnetIncome").val());
                $("#rselftop").parent("td").text($("#rselftop").val());
        }).error(function (jqXHR, textStatus, errorThrown) { });

        //保存数据
       $(".thistable .save").click(function () {
	       var remarks="rbgincome_"+$("#rbgincome").val()+"|ronlineOutlay_"+$("#ronlineOutlay").val()+"|rofflineOutlay_"+
	       		$("#rofflineOutlay").val()+"|ractOnlineOutlay_"+$("#ractOnlineOutlay").val()+"|ractOfflineOutlay_"+
	       		$("#ractOfflineOutlay").val()+"|rthireOutlay_"+ $("#rthireOutlay").val()+"|ronlineRate_"+
	       		$("#ronlineRate").val()+"|rofflineRate_"+ $("#rofflineRate").val()+"|rtransferIncome_"+
	       		$("#rtransferIncome").val()+"|rmaintain_"+$("#rmaintain").val()+"|ractivity_"+$("#ractivity").val()+"|rdivident_"+
	       		$("#rdivident").val()+"|routlay_"+$("#routlay").val()+"|rnetIncome_"+$("#rnetIncome").val()+"|rlaborService_"+
	       		$("#rlaborService").val()+"|rselftop_"+$("#rselftop").val();   
	       var data = {
	       id: $("#monthsId").val(),
                platId: $(".right-onehalf .spanchange").attr("value"),
                currentMonths: $(".currentMonths input").val(),
                remarks: remarks,
                divident: $(".divident input").val(),
                transferIncome: $(".transferIncome input").val(),
                selftop : $(".selftop input").val(),
                society : $(".society input").val(),
                anchors : $(".anchors input").val(),
                date: "${date}"
            };
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/totaloutlay/save.do",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                cache: false
            }).done(function (data) {
                if (data.resultStatus == 200) {
                    $.threesecond("保存成功", 200, 100, 1000);
                    setTimeout("location.reload(true);", 1000);
                }
            }).error(function (jqXHR, textStatus, errorThrown) { });
        });
    });
</script>
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
	        <span>直播系统</span>
	        <span>></span>
	        <span class="color99">月报总表</span>
	    </div>
	    <div class="right-onehalf">
	        <jsp:include page="platCommon.jsp"></jsp:include>
	        <input type="button" value=">>" class="expand"/>
		    <div class="expDet">
		    </div>
		 </div>
	    <jsp:include page="tabTotalCommon.jsp"></jsp:include>
	    
	    <div class="thistable custom-scroll" style="overflow:auto; background-color:#fff; ">
			<table id="tableone-1" class="table">
				<tr>
					<td colspan="7" style="text-align:left;font-weight:bold;">平台总营收<input type="hidden" id="monthsId" style="width:800px" value="6"></td>
				</tr>
				<tr class="firsttr">
					<td style="width:1%" rowspan="6">平台营收</td>
					<td style="width:1%" colspan="2">&nbsp;</td>
					<td style="width:1%">本月收款</td>
					<td style="width:1%">上月收款</td>
					<td style="width:1%">环比</td>
					<td>备注</td>
				</tr>
				<tr>
					<td colspan="2" rowspan="2">频道收款</td>
					<td class="currentMonths" rowspan="2"><input type="text" style="width:100px"></td>
					<td class="lastMonths" rowspan="2">0</td>
					<td class="growthRate" rowspan="2">0</td>
					<td class="remarks" rowspan="2"><input id="rbgincome" name="rbgincome" type="text" style="width:100%; min-width:400px;"></td>
				</tr>
				<tr></tr>
				<tr>
					<td rowspan="2">艺人收款</td>
					<td>线上</td>
					<td class="onlineIncome">0</td>
					<td class="lastOnlineIncome">0</td>
					<td class="onlineRate">0</td>
					<td><input id="ronlineRate" name="ronlineRate" type="text" style="width:100%; min-width:400px;"></td>
				</tr>
				<tr>
					<td>线下</td>
					<td class="offlineIncome">0</td>
					<td class="lastOfflineIncome">0</td>
					<td class="offlineRate">0</td>
					<td><input id="rofflineRate" name="rofflineRate" type="text" style="width:100%; min-width:400px;"></td>
				</tr>
				<tr>
					<td colspan="2">其他收入</td>
					<td class="transferIncome"><input type="text" style="width:100px"></td>
					<td>-</td>
					<td>-</td>
					<td><input id="rtransferIncome" name="rtransferIncome" type="text" style="width:100%; min-width:400px;"></td>
				</tr>
				<tr>
					<td colspan="7" style="text-align:left;font-weight:bold;">平台支出概况</td>
				</tr>
				<tr class="firsttr">
					<td colspan="4">支出内容</td>
					<td colspan="2">支出总计</td>
					<td>备注</td>
				</tr>
				<tr>
					<td rowspan="4" class="firsttr">工资类支出</td>
					<td colspan="2" rowspan="2">管理工资</td>
					<td>线上</td>
					<td class="onlineOutlay" colspan="2">0</td>
					<td><input id = "ronlineOutlay" name="ronlineOutlay" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td>线下</td>
					<td class="offlineOutlay" colspan="2">0</td>
					<td><input id="rofflineOutlay" name="rofflineOutlay" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td colspan="2" rowspan="2">艺人工资</td>
					<td>线上</td>
					<td class="actOnlineOutlay" colspan="2">0</td>
					<td><input id="ractOnlineOutlay" name="ractOnlineOutlay" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td>线下</td>
					<td class="actOfflineOutlay" colspan="2">0</td>
					<td><input id="ractOfflineOutlay" name="ractOfflineOutlay" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td rowspan="2" class="firsttr">频道支出</td>
					<td colspan="3">维护费用</td>
					<td class="maintain" colspan="2">0</td>
					<td><input id="rmaintain" name="rmaintain" type="text" style="width:100%; min-width:400px;"></td>
				</tr>
				<tr>
					<td colspan="3">运营支出</td>
					<td class="activity" colspan="2">0</td>
					<td><input id="ractivity" name="ractivity" type="text" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td rowspan="2" class="firsttr">频道其他支出</td>
					<td colspan="3">其他</td>
					<td class="divident" colspan="2"><input type="text" style="width:100px;"></td>
					<td><input id="rdivident" name="rdivident" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td colspan="3">劳务服务费</td>
					<td class="laborService" colspan="2"></td>
					<td><input id="rlaborService" name="rlaborService" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td colspan="4">平台支出合计概况</td>
					<td class="outlay" colspan="2">0</td>
					<td><input id="routlay" name="routlay" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td colspan="4">平台净利润</td>
					<td class="netIncome" colspan="2">0</td>
					<td><input id="rnetIncome" name="rnetIncome" type="text" style="width:100%; min-width:400px;" /></td>
				</tr>
				<tr>
					<td colspan="7" style="text-align:left;font-weight:bold;">自充返还概况</td>
				</tr>
				<tr class="firsttr">
					<td style="width:1%" rowspan="2">自充返还</td>
					<td style="width:1%" colspan="2">&nbsp;</td>
					<td style="width:1%">充值金额</td>
					<td style="width:1%">公会返还</td>
					<td style="width:1%">主播返还</td>
					<td>备注</td>
				</tr>
				<tr>
					<td colspan="2" rowspan="2">&nbsp;</td>
					<td class="selftop" rowspan="2"><input type="text" style="width:100px"></td>
					<td class="society" rowspan="2"><input type="text" style="width:100px"></td>
					<td class="anchors" rowspan="2"><input type="text" style="width:100px"></td>
					<td class="remarks" rowspan="2"><input id="rselftop" name="rselftop" type="text" style="width:100%; min-width:400px;"></td>
				</tr>
				<tr></tr>
				<tr>
	                <td colspan="7">
	                    <jsp:include page="attsTabCommon.jsp"></jsp:include>
	                </td>
	            </tr>
			</table>
		    <form id="finExamine" style="display:none;margin-left:20px;margin-right:20px;">
		        <p>登陆密码：<input type="password" name="fins" id="fins" style="width: 150px;"><label style="color: red;">*</label></p> 
		        <p><input class="ok"  type="button" value="确定">&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="取消"></p>
		    </form>
	    </div>
    </div>
</body>
</html>