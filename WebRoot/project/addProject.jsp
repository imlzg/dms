<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type"
            content="text/html; charset=utf-8" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Cache-Control" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
        <title>日报系统-日报管理-新增信息</title>
        <script>
        $().ready(function(){
        	var url = '${pageContext.request.contextPath }/jsonaction_allDept.action?d='+Math.random();
            $.ajax( {
                type : 'post',
                url : url,
                dataType : 'json',
                success : function(data) {
                    $.each(data, function(i, list) {
                        var $option = $("<option></option>");
                        $option.attr("value", list[0]);
                        $option.text(list[1]);
                        $("#dept").append($option);
                    });
                    $("#dept").find("option[value='${emp.department.id}']").attr("selected",true);
                }
            });
            var url = '${pageContext.request.contextPath }/jsonaction_allRoel.action?d='+Math.random();
            $.ajax( {
                type : 'post',
                url : url,
                dataType : 'json',
                success : function(data) {
                    $.each(data, function(i, list) {
                        var $option = $("<option></option>");
                        $option.attr("value", list[0]);
                        $option.text(list[1]);
                        $("#role").append($option);
                    });
                    $("#role").find("option[value='${emp.role.id}']").attr("selected",true);
                }
            });

            $("#proname").blur(function(){
                var $proname = $("#proname").val();
                if($proname == ''){
                    //$("#dailyDateTip").html("日报日不能为空");
                    return false;
                }
                $("#pronameTip").html("");
            });

            $("#sub").click(function(){
                var $proname = $("#proname").val();
                if($proname == ''){
                    //$("#dailyDateTip").html("日报日不能为空");
                    return false;
                }
                $("#pronameTip").html("");

                var $startdate = $("#startdate").val();
                if($startdate == ''){
                    //$("#startdateTip").html("日报内容不能为空");
                    return false;
                }
                $("#startdateTip").html("");

                var $enddate = $("#enddate").val();
                if($enddate == ''){
                    //$("#enddateTip").html("工作量不能为空");
                    return false;
                }
                if($enddate<$startdate){
                	$("#enddateTip").html("项目结束时间必须大于开始时间");
                    return false;
                }
                $("#enddateTip").html("");
            });
        });
        </script>
    </head>
    <body>
    <div>
        <form action="${pageContext.request.contextPath }/saveProject" id="proForm" method="post">
        <h2>新建项目</h2>
        <div style="margin-top: 15px;"></div>
        <div>
            <b class="x_color">*</b>项目名称：
            <s:textfield cssClass="W_input" name="pro.name" theme="simple" id="proname" placeholder="项目名称，必填项"></s:textfield>
            <span id="pronameTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div>
        <b class="x_color">*</b>开始时间：
            <s:textfield cssClass="W_input" name="pro.startdate" theme="simple" id="startdate" readonly="true" onfocus="WdatePicker({onpicked:function(){return true;}});" placeholder="开始时间，必填项"></s:textfield>
            <span id="startdateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div>
        <b class="x_color">*</b>结束时间：
            <s:textfield cssClass="W_input" name="pro.enddate" theme="simple" id="enddate" readonly="true" onfocus="WdatePicker({onpicked:function(){return true;}});" placeholder="结束时间，必填项"></s:textfield>
            <span id="enddateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
            <b class="x_color">&nbsp;</b>描述信息：<span id="descrTip" style="font-size: 12px; color: #CD0000;"></span>
        <div style="margin-top: -10px; margin-left: 80px;">
            <s:textarea name="pro.descr" theme="simple" id="descr1" cols="50" rows="10" cssStyle="resize: none;"></s:textarea>
        </div>
        <div style="width: 340px;"><button class="btn_weight but_right" id="sub">提交</button></div>
        </form>
        </div>
    </body>
</html>