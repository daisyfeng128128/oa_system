<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title>离职员工</title>
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
	.mustred{margin-left:10px;color:#ff0000}
	.ook{
	        border: 1px solid #ddd;
        text-align: center;
        height: 38px;
        line-height: 38px;
        display: inline-block;
        color: #fff;
        background-color:#599eeb;
        width: 90px;
        border-radius: 5px;
        margin-right: 20px;
        cursor: pointer;
   		margin-top:20px; 
	}  
	
</style>
<script>
$(function () {
	var grid = $.fn.bsgrid.init("searchTable", {
    	url: "${pageContext.request.contextPath}/emp/readPagesProbationer.do?type=leave",
    	pageSizeSelect: true, 
    	pagingToolbarAlign: "left",
    	displayBlankRows: false,
    	pageSize:pageSize,
        displayPagingToolbarOnlyMultiPages: true
	});
	$("#fast").change(function () {
        var fastArg = $("#fast").val();
		var searchParames={
		    "fastArg": fastArg
		}
		grid.search(searchParames);
    })
	
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
    
	//详细
	$(".views").click(
		function() {
		var $chuangkou = $("#emplook");
		if (grid.getSelectedRow().length > 0) {
			var id = grid.getRowRecord(grid.getSelectedRow()).id;
			var editurl = "${pageContext.request.contextPath}/emp/editLeave.do?t="+ new Date().toTimeString();
			$.ajax({
				type : "get",
				dataType : "json",
				url : editurl,
				data : "id=" + id,
				show : "slide",
				success : function(data) {
				if (data.sex == "WOMAN") {
					$("#vsex").text("女");
				} else if (data.sex == "MAN") {
					$("#vsex").text("男");
				}
				if(data.jiaojin==0){
				    $("#vjiaojin").text("否");
				}else if(data.jiaojin==1){
				    $("#vjiaojin").text("是");
				}
				if (data.account == "CHENGZHEN") {
					$("#vaccount").text("城镇");
				} else if (data.account == "NONGCUN") {
					$("#vaccount").text("农村");
				}
				if (data.actores == "NO") {
					$("#vactores").text("否");
				} else if (data.actores == "YES") {
					$("#vactores").text("是")
				}
				
				if (data.genrer == "SHIYONG") {
					$("#vgenrer").text("试用");
				} else if (data.genrer == "ZHENGSHI") {
					$("#vgenrer").text("正式");
				} else {
					$("#vgenrer").text("-");
				}

				$("#vremarks").text(data.remarks);
				$("#vspeciality").text(data.speciality);
				$("#vshareBankAddress").text(data.shareBankAddress);
				$("#vshareBankCard").text(data.shareBankCard);
				$("#vsalary").text(data.salary);
				if (data.otherSubsidy != null) {
					$("#votherSubsidy").text(data.otherSubsidy);
				}
				if (data.pfcardinalNumber != null) {
					$("#PFcardinalNumber").text(data.pfcardinalNumber);
				}
				if (data.sscardinalNumber != null) {
					$("#SScardinalNumber").text(data.sscardinalNumber);
				}
				$("#vprobationSalary").text(data.probationSalary);
				$("#vbankAddress").text(data.bankAddress);
				$("#vbankCard").text(data.bankCard);
				$("#vcpfAccounts").text(data.cpfAccounts);

				$("#vname").text(data.name);
				$("#vnamepy").text(data.namepy);
				$("#valiasname").text(data.aliasname);
				$("#vidcard").text(data.idcard);
				$("#vphone").text(data.phone);
				$("#vqq").text(data.qq);
				$("#vhostRegister").text(
						data.hostRegister);
				$("#vemail").text(data.email);

				$("#vliveAdress").text(data.liveAdress);
				$("#vhostAddress").text(data.hostAddress);

				$("#emergencyContact").text(data.emergencyContact);
				$("#vemergencyPhone").text(data.emergencyPhone);
				$("#vintroducer").text(data.introducer);
				if (data.posName != null) {
					$("#vposid").text(data.posName);
				}
				if (data.empName != null) {
					$("#vempid").text(data.empName);
				}
				if (data.depName != null) {
					$("#vdepid").text(data.depName);
				}

				if (data.contractDT != "" && data.contractDT != null) {
					$("#vcontractDT").text(data.contractDT);
				}
				if (data.birth != "" && data.birth != null) {
					$("#vbirth").text(data.birth);
				}
				if (data.joinDate != "" && data.joinDate != null) {
					$("#vjoinDate").text(data.joinDate);
				}
				if (data.regularDate != "" && data.regularDate != null) {
					$("#vregularDate").text(data.regularDate);
				}
				},error : function(data) {}
			});
			$.basewindow("详细资料", $chuangkou, 800, 630);
			$(".basewindow").addClass("custom-scroll");
			$chuangkou.css({
				"display" : "block"
			});
			} else {
				$.threesecond("请先选择", 200, 100, 1000);
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
		<span>人事系统</span> 
		<span>></span>
		<span class="color99">离职员工</span>
	</div>
	
	<div class="clearFix right-twoRight">
		<div class="fr">
			<div class="search">
	    		<input id="fast" type="text" placeholder="姓名搜索">
		    	<button type="button" id="seach" class="tableok">搜索</button>				
			</div>
		</div>
	</div>
		
		
	<div class="control">
		<jsp:include page="attsCommon.jsp"></jsp:include>
	</div>
	<div class="custom-scroll thistable"  style="overflow:auto; background-color:#fff; ">
		<table id="searchTable"class="table tablelist"  >
			<tr class="firsttr">
	        <th w_index="id" w_align="center"width="1%;"w_hidden="true">id</th>
	        <th w_num="line" w_align="center" width="1%;">序号</th>
	        <th w_index="num" w_align="center"width="1%;">员工号</th>
	        <th w_index="name" w_align="center"width="1%;">姓名</th>
	        <th w_index="aliasname" w_align="center"width="1%;">艺名</th>
	        <th w_index="sex" w_align="center"width="1%;">性别</th>
	        <th w_index="phone" w_align="center"width="1%;">联系电话</th> 
	        <th w_index="genrer" w_align="center"width="1%;">类型</th>
	        <th w_index="posName" w_align="center"width="1%;">职位</th>
	        <th w_index="depName" w_align="center"width="1%;">部门</th>
	        <th w_index="incomeday" w_align="center"width="1%;">入职日期</th>
	        <th w_index="leaveTime" w_align="center"width="1%;">离职日期</th> 
	        <th w_index="leaveMsg" w_align="center"width="1%;">离职原因</th>
	        <th w_index="remarks"  w_align="center">备注</th> 
	   		</tr>
		</table>
	   <form action="${pageContext.request.contextPath}/department/save.do" method="post" id="leaveForm" style="display:none;margin-left:20px;margin-right:20px;">
	        <input type="hidden" name="id" id="id">
	        <p>离职原因：</p>
	        <p><textarea name="reason" id="reason" style="width:230px; height:100px;"></textarea></p>
	       
	        <p><span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;<input class="cancel" type="reset" name="Input" value="关闭"></p>
	    </form>
	</div>
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
				<tr>
					<td style="text-align: right;" class="hei">姓名：</td>
					<td><span id="vname" class="w200"></span>
					</td>
					<td style="text-align: right;" class="hei">	姓名拼音：</td>
					<td><span id="vnamepy"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">性别：</td>
					<td><span id="vsex" class="w200"> </span></td>
					<td style="text-align: right;" class="hei">艺名：</td>
					<td><span id="valiasname" class="w200"> </span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">身份证号：</td>
					<td><span id="vidcard" class="w200">123132</span></td>
					<td style="text-align: right;" class="hei">生日：</td>
					<td><span id="vbirth" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">联系电话：</td>
					<td><span id="vphone" class="w200"></span></td>
					<td style="text-align: right;" class="hei">QQ：</td>
					<td><span id="vqq" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">入职介绍人：</td>
					<td><span id="vintroducer" class="w200"></span></td>
					<td style="text-align: right;" class="hei">户籍所在地：</td>
					<td><span id="vhostRegister" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">紧急联系人：</td>
					<td><span id="vemergencyContact" class="w200"></span></td>
					<td style="text-align: right;" class="hei">紧急联系人电话 ：</td>
					<td><span id="vemergencyPhone" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">户口类型：</td>
					<td><span id="vaccount" class="w200"></span></td>
					<td style="text-align: right;" class="hei">电子邮件：</td>
					<td><span id="vemail" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">现居地址：</td>
					<td colspan="3"><span id="vliveAdress" class="w200"></span></td>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">户籍地址 ：</td>
					<td colspan="3"><span id="vhostAddress" class="w200"></span></td>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="yellowline"><p style=" margin: 0">职位资料</p>
					</td>
				</tr>
	
				<tr>
					<td style="text-align: right;" class="hei">部门：</label>
					</td>
					<td><span id="vdepid" class="w200"></span></td>
					<td style="text-align: right;" class="hei">职位：</label>
					</td>
					<td><span id="vposid" class="w200"></span></td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">入职日期：</td>
					<td><span id="vjoinDate" class="w200"></span></td>
					<td style="text-align: right;" class="hei">上级主管：</label>
					</td>
					<td><span id="vempid" class="w200"></span></td>
	
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">状态：</td>
					<td><span id="vgenrer" class="w200"></span></td>
					<td style="text-align: right;" class="hei">是否艺人：</br>
					是否交金：
					</td>
					<td><span id="vactores" class="w200"></span></br>
						<span id="vjiaojin" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">转正日期：</td>
					<td><span id="vregularDate" class="w200"></span></td>
					<td style="text-align: right;" class="hei">基本工资：</td>
					<td><span id="vsalary" class="w200"></span>
					</td>
				</tr>
	
				<tr>
					<td style="text-align: right;" class="hei">试用补贴：</td>
					<td><span id="vprobationSalary" class="w200"></span>
					</td>
					<td style="text-align: right;" class="hei">转正补贴：</td>
					<td><span id="votherSubsidy" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei"> 社保基数：</td>
					<td><span id="SScardinalNumber" class="w200"></span>
					</td>
					<td style="text-align: right;" class="hei"> 公积金基数：</td>
					<td><span id="PFcardinalNumber" class="w200"></span>
					</td>
				</tr>
				
				<tr> 
					<td style="text-align: right;" class="hei">劳动合同期限：</td>
					<td><span id="vcontractDT" class="w200"></span>
					</td>
					<td style="text-align: right;" class="hei">公积金帐号 ：</td>
					<td><span id="vcpfAccounts" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">工资卡帐号：</td>
					<td><span id="vbankCard" class="w200"></span>
					</td>
					<td style="text-align: right;" class="hei">工资银行卡开户行：</td>
					<td><span id="vbankAddress" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">分成账号(艺人)：</td>
					<td><span id="vshareBankCard" class="w200"></span>
					</td>
					<td style="text-align: right;" class="hei">银行卡：</td>
					<td><span id="vshareBankAddress" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">技能：</td>
					<td colspan="3"><span id="vspeciality" class="w200"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" class="hei">备注：</td>
					<td colspan="3"><span id="vremarks" class="w200"></span>
					</td>
				</tr>
			</table>
			<p style="text-align:center;">
				<input class="cancel" type="reset" name="Input" value="关闭">
			</p>
		</div>
</div>
</body>
</html>
