<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code="title"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/all.css" />
<script src="${pageContext.request.contextPath}/views/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/views/js/sea.js"></script>
<script>
    seajs.use("${pageContext.request.contextPath}/views/js/bootstrap.min");
    seajs.use("${pageContext.request.contextPath}/views/js/all");
</script>
<style>
body{
	background-image:url("${pageContext.request.contextPath}/views/images/Login_BG.jpg");
	background-position:center;
	position:relative;
	min-width:1250px;
	overflow-y:hidden;
}
.login-title{
	width:580px;
	height:66px;
	position:absolute;
	left:0;
	right:0;
	top:150px;
	margin:auto;
}
.login-middle{
	height:500px;
	position:absolute;
	left:0;
	right:0;
	margin:auto;
	top:250px;
	width:1250px;

}
.login-text{
width:514px;
height:375px;
background-image:url("${pageContext.request.contextPath}/views/images/Login.png");
display:inline-block;
position:relative;
vertical-align:middle
}
.login-text #loginName{
margin-top:112px;
height:45px;
width: 444px;
margin-left: 37px;
padding-left: 35px;
padding-right: 35px;
background-color: transparent;
border:none;
color:#999;
}
.login-text #loginPwd{
margin-top:35px;
height:45px;
color:#999;
width: 444px;
margin-left: 37px;
padding-left: 35px;
padding-right: 35px;
background-color: transparent;
border:none;

}
.login-text .tosubmit{
margin-top:50px;
background-image:url("${pageContext.request.contextPath}/views/images/LOGIN_BTN.png");
width:172px;
height:53px;
display:block;
margin-top: 35px;
margin-left: 300px;
background-color: transparent;
border: none;
}
.login-text .tosubmitchange{
background-image:url("${pageContext.request.contextPath}/views/images/LOGIN_BTN(C).png");
}
.login-text #thecheckbox{
position:absolute;
left: 54px;
bottom: 69px;
width: 12px;
opacity: 0.7;
}
@media (max-width: 1366px) { 
.login-title{
     top: 50px;
   }
   .login-middle{
     top: 150px;
   }
}


</style>
<script>
$(function () {
	$(".login-text #thecheckbox").css({backgroundColor:"red"})
    $("#loginForm").submit(function () {
        var loginName = $("#loginName").val();
        var loginPwd = $("#loginPwd").val();
        if ($.trim(loginName) == "") {
            $.threesecond("用户名不能为空！", "", "", 2000)
            $("#loginName").focus();
            return false;
        }
        if ($.trim(loginPwd) == "") {
            $.threesecond("密码不能为空！", "", "", 2000);
            $("#loginPwd").focus();
            return false;
        }
        $(".tosubmit").addClass("tosubmitchange");
    });
})
</script>
</head>
<body>
<div class="login-title">
<img src="${pageContext.request.contextPath}/views/images/LOGIN_title.png"/>
</div>
<div class="login-middle">
	<img style="margin-right:120px;" src="${pageContext.request.contextPath}/views/images/Login_logo.png"/>
	<img style="margin-right:120px;"src="${pageContext.request.contextPath}/views/images/line.png"/>
	<div class="login-text">
		<form action="${pageContext.request.contextPath}/user/adLogin.do" method="post" name="show" id="loginForm">
		         <input type="text" name="loginName" id="loginName" class="form-control" placeholder="请输入用户名"  autocomplete="off"/>
		         <input type="password" name="loginPwd" id="loginPwd"  class="form-control" placeholder="请输入密码" autocomplete="off" />
		         <button type="submit"  name="submit" class="tosubmit"></button>
				<input type="checkbox" id="thecheckbox" />
	    </form>
	</div>
</div>
 
</body>
</html>
