
$(function () {
 
	
    (function ($) {
        $.threesecond = function (text, width, height, time) {
            $(".threesecond").remove();
            $(".zhezhao-threesecond").remove();
            $("body").append("<div class='threesecond'></div><div class='zhezhao-threesecond'></div>");
            $(".threesecond").text(text);
            if (width == undefined || height == undefined) {
                $(".threesecond").css({ width: 200, height: 100, lineHeight: "100px" });
            }
            else {
                $(".threesecond").css({ width: width, height: height, lineHeight: height + "px" });
            }

            $(".zhezhao-threesecond").show();
            $(".threesecond").show();
            if (time == undefined || time == '') {
                setTimeout(function () {
                    $(".zhezhao-threesecond").hide();
                    $(".threesecond").hide();
                }, 3000);
            }
            else {
                setTimeout(function () {
                    $(".zhezhao-threesecond").hide();
                    $(".threesecond").hide();
                }, time);
            }
        };/*
        setTimeout( function showTime(){
        	var today = new Date(); 
        	  //alert( "   time is: " + today.toString());
        	if(!$("body").hasClass("timeOut")){ 
        		 $("body").append("<div class='timeOut'></div><div class='zhezhao-timeOut'></div>");
        		 $("body").find(".timeOut").text("系统升级中。。。");    
        		 $("body").find(".timeOut").css({ width: 200, height: 100, lineHeight: "100px" });
        		 $("body").find(".timeOut").show();
        		 $("body").find(".zhezhao-timeOut").show();
        	}
        	     
        },3000);*/
       $.timeOut =function(text, width, height, time){
        	 $(".timeOut").remove();
             $(".zhezhao-timeOut").remove();
             $("body").append("<div class='timeOut'></div><div class='zhezhao-timeOut'></div>");
             $(".timeOut").append(text);
             if (width == undefined || height == undefined) {
                 $(".timeOut").css({ width: 320, height: 166, lineHeight: "166px" });
             }
             else {
                 $(".timeOut").css({ width: width, height: height, lineHeight: height + "px" });
             }

             $(".zhezhao-timeOut").show();
             $(".timeOut").show();
             
             function jump(count) {
                 window.setTimeout(function(){
                     count--;
                     if(count > 0) {
                         $('i').html(count);
                         jump(count);
                     } else {
                    	  $(".zhezhao-timeOut").hide();
                          $(".timeOut").hide();
                     }
                 }, 1000);
             }
             jump(time);

              
        }

    	//序号
    	getXuhao=function(){ 
    		$(".gen").each(function(index){
    			 var $self=$(this);
    			 $self.find(".xuhao").html(index+1);
    		})
    	} 
        
        $.threetop = function (text, width, height) {
            $(".threetop").remove();

            $("body").append("<div class='threetop'></div></div>");
            $(".threetop").text(text);
            if (width == undefined || height == undefined) {
                $(".threetop").css({ width: 200, height: 50, lineHeight: "50px" });
            }
            else {
                $(".threetop").css({ width: width, height: height, lineHeight: height + "px" });
            }

            $(".threetop").slideDown(500);
            setTimeout(function () {
                 $(".threetop").slideUp(500);
             }, 1500);

        };

//定时器
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
        	    
        },3000);*/

        $.yesorno = function (text, width, height, $yes, $no) {
            $(".yesorno").remove();
            $(".zhezhao-yesorno").remove();
            $("body").append("<div class='yesorno'><a class='yesornoout'>&times;</a><div class='clear'></div><p class='p1'></p><p class='p2'><button class='ok' type='button'>确认</button><button class='cancel' type='button'>取消</button></p></div><div class='zhezhao-yesorno'></div>");
            $(".yesorno .p1").text(text);
            if (width == undefined || height == undefined || width == '' || height == '') {
                $(".yesorno").css({ width: 300, height: 160 });
            }
            else {
                $(".yesorno").css({ width: width, height: height });
            }
            $(".yesorno .ok").on("click", function () {
                if ($yes == undefined || $yes == '') {              
                }
                else {
                    $yes();
                }
                $(".zhezhao-yesorno").hide();
                $(".yesorno").hide();
                $(".yesorno .ok,.yesorno .cancel,.yesorno .yesornoout").off("click");
            })
            $(".yesorno .cancel,.yesorno .yesornoout").on("click", function () {
                if ($no == undefined || $no == '') {
                }
                else {
                    $no();
                }
                $(".zhezhao-yesorno").hide();
                $(".yesorno").hide();
                $(".yesorno .ok,.yesorno .cancel,.yesorno .yesornoout").off("click");
            })
            $(".zhezhao-yesorno").show();
            $(".yesorno").show();
        };
        
        $.basewindow = function (title,idorclass, width, height, $yes, $no) {
            $(".basewindow").remove();
            $(".zhezhao-basewindow").remove();
            $(".inputerror").removeClass("inputchange");
            $(".inputerror").next(".combo").removeClass("inputchange");
            $("body").prepend("<div class='basewindow'><div class='bg83 clearFix'>\
					  					<a class='basewindowout'>&times;</a>\
					  					<p class='p0 fl'>"+title+"</p>\
					  				</div>\
					  			</div>\
								<div class='zhezhao-basewindow'></div>");
            
            $(idorclass).find("input,select,textarea").not(".ok,.cancel,input[type='checkbox']").val("");
            $(idorclass).find("input[type='checkbox']").each(function(){
        		$(this)[0].checked = false;
        	});
            $(idorclass).appendTo($(".basewindow")).show();
            if (width == undefined || height == undefined || width == '' || height == '') {
                $(".basewindow").css({ width: 300, height: 160 });
            }
            else {
                $(".basewindow").css({ width: width, height: height });
            }
            $(".basewindow .ok").on("click", function () {
                if ($yes == undefined || $yes == '') {              
                }
                else {
                    $yes();
                }
                $(".zhezhao-basewindow").hide();
                $(".basewindow").hide();
                $(idorclass).appendTo($("body")).hide();
                $(".basewindow .ok,.basewindow .cancel,.basewindow .basewindowout").off("click");
                
            })
     
            $(".basewindow .cancel,.basewindow .basewindowout").on("click", function () {
                if ($no == undefined || $no == '') {
                }
                else {
                    $no();
                }
                $(".zhezhao-basewindow").hide();
                $(".basewindow").hide();
                $(idorclass).appendTo($("body")).hide();
                $(".basewindow .ok,.basewindow .cancel,.basewindow .basewindowout").off("click");
            })
            
            $(".zhezhao-basewindow").show();
            $(".basewindow").show();
            $(idorclass).show();
        }
        $(".combo").css({"border":"1px solid #ddd","boxShadow":"0 0 6px #fbfbfb","borderRadius":2})
        $(".combo .combo-text").css({"height":26,"line-height":26,"text-indent":"1em","border":"none"});
        $(".combo  .combo-arrow").css({"width":16,"height":26,"marginLeft":0,"border":"1px solid #ddd","borderLeft":"none"});
        $("#snamepy").css({"marginLeft":-10});
        $.inputchangea = function () {
  			  $(".inputerror").addClass("inputchange");
  			  $(".inputerror").next(".combo").addClass("inputchange");
  	  }; 
    })(jQuery);
    
        $(".thistable").css({ "height": $("body").outerHeight(true) - $(".right-one").outerHeight(true)-$(".right-onehalf").outerHeight(true) -$(".right-two").outerHeight(true)-$(".right-twohalf").outerHeight(true)- $(".control").outerHeight(true) })
        $(window).resize(function () {
            $(".thistable").css({ "height": $("body").outerHeight(true) - $(".right-one").outerHeight(true) -$(".right-onehalf").outerHeight(true)-$(".right-two").outerHeight(true)-$(".right-twohalf").outerHeight(true)- $(".control").outerHeight(true)})
        });
        //阻止回车提交
      $(window).keydown(function (e) {
            if (e.which == 13) {
                return true;
           }
        })
      
        if($(".right-onehalf .regs").length>14){
        	$(".expand").show();
        	$(".right-onehalf").children().eq(14).nextUntil(".expand").hide();	
        }
        
        var timer=null;
        var togg=$(".right-onehalf .expand").add(".right-onehalf .expDet");
        togg.mouseover(function(){
        	clearTimeout(timer);
        	$(".right-onehalf").children().eq(14).nextUntil(".expand").appendTo(".expDet");
        	$(".expDet").children().show();
        	$(".expDet").fadeIn();
        	
        });
        
        togg.mouseout(function(){
        	timer=setTimeout(function(){
        		$(".right-onehalf .expDet").hide();
        	})
        })
});

//下列简易插件中‘不填’如果不是最后一个。请参照下面的例子
//$.aaa("修改成功", "", "", 100);

//3秒弹窗后自动消失(内容,宽度,高度[宽度高度一个不填默认200/100],消失时间)
//$.threesecond("修改成功", 300, 100, 2000);
//简单弹窗确认取消(内容,按钮颜色[geren/qiye],宽度,高度[宽度高度一个不填默认300/160],点击确认执行的事件[不填默认隐藏窗口],点击取消执行的事件[不填默认隐藏窗口])
//$.yesorno("是否删除照片?", "geren", 300, 160, $gonext, $goback);
//基本弹框(自定义div的id或者class,宽,高[宽度高度一个不填默认300/160],点击确认执行的事件[不填默认隐藏窗口],点击取消执行的事件[不填默认隐藏窗口]);
//事件一定class=ok和class=cancel
//内容是复制所以会删除所以不需要重置内容
//$.basewindow("#aa/.aa",100,100, $gonext, $goback);