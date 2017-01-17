<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>直播平台</title>
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

            //数据获取
            var dateViewModel = function () {
                var self = this;
                self.Apps = ko.observableArray();
                var AppViewModel = function (root, data, index) {
                    var self = this;
                    self.data = ko.observable(data);
                    self.Index = ko.observable(index + 1);
                    self.Id = ko.observable(data.id);
                    self.Name = ko.observable(data.name);
                    self.Charfirst = ko.observable(data.details[0]);
                    self.Charindex = ko.observable(data.details.length);
                    self.Indexs = ko.observable(data.indexs);
                    self.Dev = ko.observable(data.dev);
                    self.Url = ko.observable(data.url);
                    self.Divid = ko.observable(data.divid);
                    self.ParentName = ko.observable(data.parentName);
                    self.Parentid = ko.observable(data.parentid);
                    //数组
                    self.Chars = ko.observableArray();
                    var CharViewModel = function (father, data, index) {
                        var self = this;
                        self.Char = ko.observable(data);
                    };
                    $(data.details.splice(1)).each(function (index, item) {
                        self.Chars.push(new CharViewModel(self, this, index));
                    })
                    //click事件
                    self.selectcolor = ko.observable("");
                    self.click = function () {
                        $(root.Apps()).each(function (index, item) {
                            item.selectcolor("");
                        });
                        if (root.SelectApp().Id() == self.Id()) {
                            self.selectcolor("");
                            var data = {
                                id: ""
                            };
                            root.SelectApp(new SelectAppViewModel(root, data));

                        }
                        else {
                            self.selectcolor("selected");
                            root.SelectApp(new SelectAppViewModel(self, self.data()));
                        }

                    };
                };
                var SelectAppViewModel = function (root, data) {
                    var self = this;
                    if (data != null) {
                        self.data = ko.observable(data);
                        self.Id = ko.observable(data.id);
                    };

                }
                //得到初始数据
                self.getapps = function () {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/menus/readPages.do?pageSize=50&curPage=1&sortName=id&sortOrder=asc",
                        cache: false,
                        contentType: "application/json; charset=utf-8",
                        displayBlankRows: false,
                        displayPagingToolbarOnlyMultiPages: true
                    }).done(function (data) {
                        $(data.data).each(function (index, item) {
                            self.Apps.push(new AppViewModel(self, item, index));
                        });
                    }).error(function (jqXHR, textStatus, errorThrown) {
                    });
                };
                self.firstselected = function () {
                    var data = {
                        id: ""
                    };
                    self.SelectApp = ko.observable(new SelectAppViewModel(self, data));
                };
                //初始化
                self.init = function () {
                    self.getapps();
                    self.firstselected();
                };
                self.init();
            }
            ko.applyBindings(new dateViewModel());



            //新增
            $(".control .add").click(function () {
                var $chuangkou = $("#addNewMenu");
                $chuangkou.find(".toomore span").parent("p").prev("p").remove();
                $chuangkou.find(".toomore span").parent("p").remove();
                $chuangkou.find(".toaddpingtaihao").click();
                $.basewindow("添加菜单", $chuangkou, 276, 534);

            })
            //修改
             $(".control .mid").click(function(){
                 var $chuangkou=$("#addNewMenu");
                 $chuangkou.find(".toomore span").parent("p").prev("p").remove();
                 $chuangkou.find(".toomore span").parent("p").remove();

                if($(".selected").length>0){
                    var id =$(".selected td:first").text();
                    var editurl = "${pageContext.request.contextPath}/menus/edit.do";
                    $.basewindow("修改菜单", $chuangkou, 700, 400);
                    $.ajax({
                          type: "get",
                          dataType: "json",
                          url: editurl,
                          data: "id="+id,
                          show:"slide",
                          success: function(data){
                              $chuangkou.find("#id").val(data.id);
                              $chuangkou.find("#name").val(data.name);
                              $chuangkou.find("#url").val(data.url)
                              $chuangkou.find("#divid").val(data.divid)
                              $chuangkou.find("#indexs").val(data.indexs)
                              $chuangkou.find("#itemId").combogrid("setValue", data.parentid);
                              if(data.dev =="YES"){
                                  $("#dev").val("YES").selected;
                              }else{
                                  $("#dev").val("NO").selected;
                              }
                              $(data.details).each(function(index){
                                  $(".toaddpingtaihao").click();
                                  $("[name='details']").eq(index).val(this);
                                  $("[name='details']").eq(index).addClass("midchannel");
                                  $("[name='details']").eq(index).attr({ "disabled": "disabled" });
                              });
                          },
                          error : function(data){

                          }
                    });
                }
                else{
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            }) 
            //删除
            $(".control .del").click(function () {
                if ($(".selected").length > 0) {
                    var $yes = function () {
                        var id = $(".selected td:first").text();
                        var delUrl = "${pageContext.request.contextPath}/menus/delete.do";
                        $.ajax({
                            type: "get",
                            dataType: "json",
                            url: delUrl,
                            data: "id=" + id,//要发送的数据
                            success: function (data) {
                                if (data.resultStatus == 200) {
                                    $(".table .selected").remove();
                                }
                            }, error: function (data) { }
                        });
                    }
                    var $no = function () { };
                    $.yesorno("是否删除？", 300, 160, $yes, $no);
                }
                else {
                    $.threesecond("请先选择", 200, 100, 1000);
                }
            });
            //添加功能
            $(".toaddpingtaihao").click(function () {
                var $self = $(this);        
                    $self.parent(".addpingtaihao").append('<p style="">功能：</p><p class="toomore" ><input type="text" id="details" name="details" style="width:200px;" maxlength="400" class="input"><span style="cursor:pointer;margin-left:20px;">-删除功能</span></p>')
                    $(".basewindow").css({ height: "+=64px" })
            })
            //删除功能
            $(".addpingtaihao").delegate(".toomore span", "click", function () {
                var $self = $(this);
                if ($self.prev("input").hasClass("midchannel")) {
   
                                $self.parent("p").prev("p").remove();
                                $self.parent("p").remove();
                                $(".basewindow").css({ height: "-=64px" });
                    
                } else {
                    $self.parent("p").prev("p").remove();
                    $self.parent("p").remove();
                    $(".basewindow").css({ height: "-=64px" });
                }
            });
            //提交+判断(新增)
            $("#addNewMenu .okok").click(function () {
                var name = $("#addNewMenu #name").val();
                if (name == "" || name == null) {
                    $.threetop("请填写菜单名称!");
                }
                else {
                	$("[name='details']").removeAttr("disabled");
                    $("#addNewMenu").submit();
                }
            });   
        });
    </script>

    <script>
        $(function () {
            $("#itemId").combogrid({
                panelWidth: 500,
                method: "POST",
                datatype: "json",
                url: "${pageContext.request.contextPath}/menus/readPagesSkip.do",
                mode: "remote",
                fitColumns: true,
                rownumbers: true,
                pagination: true,
                idField: "id",
                textField: "name",
                pageSize: 30,
                pageList: [5, 10, 20, 30, 40, 50],
                columns: [[
                    { field: "name", title: "上级菜单", width: 120, sortable: true }
                ]],
                keyHandler: {
                    up: function () { },
                    down: function () { },
                    enter: function () { },
                    query: function (q) {
                        if (q != null && q != "") {
                            $("#itemId").combogrid("grid").datagrid("reload", { "filter": q });
                            $("#itemId").combogrid("setValue", q);
                        }
                    }
                }
            });
        })
    </script>
</head>
<body>
    <div class="right-one fsize18 fweightbold">菜单管理</div>
    <div class="control">
        <jsp:include page="attsCommon.jsp"></jsp:include>
    </div>
    <div class="thistable custom-scroll" style="overflow:auto;">
        <table id="searchTable" class="table yitablelist">
            <tr class="firsttr">
                <th hidden width="1%;">id</th>
                <th width="1%;">序号</th>
                <th width="1%;">菜单名称 </th>
                <th width="1%;">访问地址 </th>
                <th width="1%;">功能</th>
                <th width="1%;">父类菜单 </th>
                <th width="1%;">显示 </th>
                <th width="1%;">排序显示 </th>
                <th width="1%;">页面divId </th>
                <th>备注</th>
            </tr>
            <thead></thead>
            <!-- ko foreach: Apps -->
            <tbody data-bind="click:click, css:selectcolor">
                <tr>
                    <td hidden data-bind="text:Id,attr: { rowspan: Charindex }">></td>
                    <td data-bind="text:Index,attr: { rowspan: Charindex }"></td>
                    <td data-bind="text:Name,attr: { rowspan: Charindex }"></td>
                    <td data-bind="text:Url,attr: { rowspan: Charindex }"></td>
                    <td data-bind="text:Charfirst"></td>
                    <td data-bind="text:ParentName,attr: { rowspan: Charindex }"></td>
                    <td data-bind="text:Dev,attr: { rowspan: Charindex }"></td>
                    <td data-bind="text:Indexs,attr: { rowspan: Charindex }"></td>
                    <td data-bind="text:Divid,attr: { rowspan: Charindex }"></td>
                    <td data-bind="attr: { rowspan: Charindex }"></td>
                </tr>
                <!-- ko foreach: Chars -->
                <tr>
                    <td data-bind="text:Char"></td>
                </tr>
                <!-- /ko -->
            </tbody>
            <!-- /ko -->
        </table>

    </div>
    <form id="addNewMenu" action="${pageContext.request.contextPath}/menus/save.do" method="post" style="display:none;margin-left:20px;margin-right:20px;">
        <div style="display:inline-block;margin-right:100px;">
            <p>菜单名称：</p>
            <p><input type="text" id="name" name="name" style="width:200px;" maxlength="400" class="input"></p>
        </div>
        <div style="display:inline-block">
            <p>访问地址：</p>
            <p><input type="text" id="url" name="url" style="width:200px;" maxlength="400" class="input"></p>
        </div>
        <div class="clear"></div>
        <div style="display:inline-block;margin-right:100px;width:200px;">
            <p style="">上级菜单：</p>
            <p><input id="itemId" name="itemId" style="width:200px;" maxlength="400" class="input"></p>
        </div>
        <div style="display:inline-block">
            <p>显示：</p>
            <p>
                <select style="width:200px;" id="dev" name="dev">
                    <option value="YES">否</option>
                    <option value="NO">是</option>
                </select>
            </p>
        </div>
        <div class="clear"></div>
        <div style="display:inline-block;margin-right:100px;">
            <p>页面divId：</p>
            <p><input type="text" id="divid" name="divid" style="width:200px;" maxlength="400" class="input"></p>
        </div>
        <div style="display:inline-block">
            <p>排序(降序排列)：</p>
            <p><input type="text" name="indexs" id="indexs" class="easyui-numberbox input" style="width:200px"/></p>
        </div>

        <div class="addpingtaihao" style="position:relative;margin-top:20px;padding:20px;border:1px solid #ddd">
            <p style="position:absolute;top:-10px;left:10px;background-color:#fff;padding:0px 10px">子功能</p>
            <p class="toaddpingtaihao" style="position:absolute;top:-22px;right:-1px;background-color:#fff;padding:0px 5px;border:1px solid #ddd;border-bottom:0px;cursor:pointer;">+添加功能</p>
        </div>
        <p>
            <input type="hidden" name="id" id="id" value="">
            <span class="okok">提交</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="cancel" type="reset" name="Input" value="关闭">
        </p>
    </form>
</body>
</html>