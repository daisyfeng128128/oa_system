<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>frames.jsp</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10,chrome=1"/>
<script src="http://cdn.bootcss.com/sockjs-client/1.0.3/sockjs.min.js"></script>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/views/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/views/js/bootstrap-modal.js"></script>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/views/js/bootstrap-transition.js"></script>
<style type="text/css">
*{margin:0; padding: 0; width:100%; height:99%;}

</style>
</head>
	<script type="text/javascript">	
	
       /*  setTimeout( function showTime(){
        	var today = new Date(); 
        	  //alert( "   time is: " + today.toString());
        	if(!$("body").hasClass("timeOut")){ 
        		 $("body").append("<div class='timeOut'></div><div class='zhezhao-timeOut'></div>");
        		 $("body").find(".timeOut").text("系统升级中。。。");    
        		 $("body").find(".timeOut").css({ width: 200, height: 100, lineHeight: "100px" });
        		 $("body").find(".timeOut").show();
        		 $("body").find(".zhezhao-timeOut").show();
        	}
        	    
        },3000); */
        </script>
         <script type="text/javascript">
	function autoSize(iframes){
		iframes.height = $(document).height() - 0;
	} 
	
</script> 
<body>
 <!--  <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">消息通知</h3>
		</div>
		<div class="modal-body">
			<p><label id="labelService"></label></p>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" name="butSubmit" id="butSubmit">关闭</button>
		</div>
	</div> -->
	<iframe src="${pageContext.request.contextPath}/views/page/mainindex.jsp" id="frames" name="frames" onload="javascript:autoSize(this);"  frameborder="0" marginwidth="0" marginheight="0" width="100%" height="100%">
</body>
</html>
