<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><spring:message code="title" /></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/all.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/bootstrap.min.css" />
    <script src="${pageContext.request.contextPath}/views/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/views/js/sea.js"></script> 
	<script>
		seajs.use("${pageContext.request.contextPath}/views/js/bootstrap.min");
		seajs.use("${pageContext.request.contextPath}/views/js/all");
	</script>
    <script>
        $(function(){
		    $.ajax({
				type : "POST",
				cache : false,
				url : "${pageContext.request.contextPath}/branchOff/readAllBranchs.do",
				success : function(data) {
					if (data != null && data.length > 0) {
						$.each(data, function(i, value) {
							$(".son_ul").append("<li data='"+value.id+"' onclick=choose('"+value.id+"','"+value.name+"') >" + value.name + "</li>");
						});
					} 
				}
		    });
					    
            $('.son_ul').hide(); //初始ul隐藏
            $('.select_box span').click(function(){ //鼠标移动函数
                $(this).parent().find('ul.son_ul').slideDown();  //找到ul.son_ul显示
                $(this).parent().find('li').hover(function(){$(this).addClass('hover')},function(){$(this).removeClass('hover')}); //li的hover效果
                $(this).parent().hover(function(){},
                       	function(){
                            $(this).parent().find("ul.son_ul").slideUp();
                        }
                );
            })
            
            
	        $("#sub").click(function(){ 
	            var loginName = $("#loginName").val();
				var loginPwd = $("#loginPwd").val();
				var branch =  $(".select_box span").attr("data");
				if ($.trim(branch) == "") {
					$.threesecond("请选择分公司！", "", "", 2000) 
					return false;
				}
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
	            $.ajax({
	    			type : "POST",
	    			cache : false,
	    			data: "loginName=" + $("#loginName").val() + "&loginPwd="+$("#loginPwd").val()+"&branchs=" + $(".select_box span").attr("data"),
	    			url : "${pageContext.request.contextPath}/jump.do",
	    			success : function(data) {
	    				if (data.resultStatus==200) {
	    				    window.location.href="${pageContext.request.contextPath}/user/login.do";
	    				}  else{
	    				    $.threetop("登陆失败!用户名或密码错误!");
	    				}
	    			}
	    		 });  
	        })
                     
          });
        
        window.onload=function(){ 
            var isChrome = ${chrome};
            if(!isChrome){
                var $chuangkou = $("#prompt");
                $.basewindow("警告", $chuangkou, 625, 100);
    			$(".basewindowout").hide();
        	} 
   		}

        function choose(id,name){
            $('.select_box').find('span').html(name);
            $('.select_box').find('span').attr("data",id);
            $('.select_box').find('ul').slideUp();
        } 
        
        
    </script>
    <style>
        * {
            margin: 0;
            padding: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            text-decoration: none;
            list-style-type: none;
            font-size: 14px;
            font-family: 'Microsoft YaHei';
        }
        a {
            text-decoration: none;
            color: #000;
        }
        a:hover,a:focus {
            text-decoration: none;
            color: #000;
        }
        .clear {
            clear: both;
        }
        .header input.symbolsInput:-ms-input-placeholder {color: #b6b6b6; }
		.header input.symbolsInput::-webkit-input-placeholder { color: #b6b6b6;}
		.header input.symbolsInput::-moz-placeholder { color: #b6b6b6;}
        
        .logbtn button:hover{background: url("/oa/views/images/login-bt-hover.png")no-repeat;}
        .wrap{background: url("${pageContext.request.contextPath}/views/images/background.jpg") no-repeat 50% 0;} 
        .sec{margin: 0 auto;  width: 1191px; background-repeat: no-repeat; }
        .mg{width: 546px;height: 70px;margin: 0 auto; position: absolute;bottom: 70px;left: 0;right: 0;}
        .title{height:354px;position: relative;background-image:  url("${pageContext.request.contextPath}/views/images/center-top.jpg");}
        .content{height:298px;position: relative;background-image:  url("${pageContext.request.contextPath}/views/images/center-cent.jpg");}
        .mainlogin{width:500px;height: 300px;margin: 0 auto; overflow: hidden;}
        .employee{margin: 28px 0 0 36px;}
        #loginName{background:  url("${pageContext.request.contextPath}/views/images/zhanghu.png") no-repeat;}
        #loginPwd{background:  url("${pageContext.request.contextPath}/views/images/mima.png") no-repeat;}
        .employee span{color: #DEE1E1;font-size: 20px;line-height:22px;padding-left:30px;background: url("${pageContext.request.contextPath}/views/images/yuangong.png")no-repeat center left;}
        .login-input{margin:10px 0 0 36px; width:420px;height: 50px;background:  url("${pageContext.request.contextPath}/views/images/bt.png") no-repeat;border: none; 
        font-size: 16px;color: #DEE1E1;outline: none;padding-left: 50px;}
        #loginName:focus{
            background:  url("${pageContext.request.contextPath}/views/images/zhanghu-hover.png")no-repeat;;
        }
        #loginPwd:focus{
            background:  url("${pageContext.request.contextPath}/views/images/mima-hover.png") no-repeat;;
        }
        .sel,.logbtn{float: left;margin:25px 38px;}
        .select_box span{width:168px;height: 52px;color: #D3D1D2;display: inline-block;background:  url("${pageContext.request.contextPath}/views/images/xiala-.png") no-repeat;cursor: pointer;line-height: 50px;padding-left:10px;}
        .son_ul{width:168px;overflow: hidden;border: 1px solid #454D54;position: absolute;z-index: 9999}
        .son_ul li{height: 50px;color: #D3D1D2; line-height: 50px;padding-left: 10px;cursor:pointer;}
        .logbtn button{background:  url("${pageContext.request.contextPath}/views/images/login-bt.png")no-repeat;width: 168px;height: 50px;border: none;color: #FEFEFE;font-size: 18px;font-weight: 700;cursor:pointer}
        .bottom{height:336px;position:relative;background-image:  url("${pageContext.request.contextPath}/views/images/center-foot.jpg");}
        .hover {background:#2384AB;}
    </style>
</head>
<body>
    <article class="wrap">
        <section class="sec title">
            <div class="mg"><img src="${pageContext.request.contextPath}/views/images/mt.png" /></div>
        </section>

        <section class="sec content">
            <div class="mainlogin">
                <div class="employee"><span>员工登录</span></div>
                <div class="login">
                    <div><input type="text" name="loginName" id="loginName" class="login-input" style="border:none;  box-shadow:inset 0 0 0;" placeholder="请输入用户名" autocomplete="off" tabindex="1" /></div>
                    <div><input type="password" name="loginPwd" id="loginPwd" class="login-input" placeholder="请输入密码" autocomplete="off" tabindex="2" /></div>
                </div>
                <div class="branch">
                    <div class="sel">
                        <div class="select_box" tabindex="3">
                            <span data="">所属公司</span>
                            <ul class="son_ul" style="display: none;"></ul>
                        </div>
                    </div>
                    <div class="logbtn">
                        <button id="sub" tabindex="4">登　　录</button>
                    </div>
                </div>
            </div>
        </section>
        <section class="sec bottom">
            <div></div>
        </section>

    </article>
    <div id="prompt" style="margin-left: 20px; margin-right: 20px; display:none;text-align:center;" >
		<p style="color:#e35d00; font-size:23px;">系统暂不支持当前浏览器，请使用谷歌或者火狐浏览器。</br>
			<a href="http://w.x.baidu.com/alading/anquan_soft_down_ub/14744" >点击此处下载Google浏览器</a>
		</p>		
	</div>
</body>
</html>
