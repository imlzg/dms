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

            $("#dailyDate").blur(function(){
                var $dailyDate = $("#dailyDate").val();
                /*
                if($dailyDate == ''){
                    $("#dailyDateTip").html("日报日期不能为空");
                    return false;
                }
                */
                
                $("#dailyDateTip").html("");
            });

            $("#content1").blur(function(){
                var $content1 = $("#content1").val();
                if($content1 == ''){
                    //$("#contentTip").html("日报内容不能为空");
                    return false;
                }
                $("#contentTip").html("");
            });

            $("#workload").blur(function(){
                var $workload = $("#workload").val();
                if($workload == ''){
                    //$("#workloadTip").html("工作量不能为空");
                    return false;
                }
                $("#workloadTip").html("");
            });

            $("#sub").click(function(){
                var $dailyDate = $("#dailyDate").val();
                if($dailyDate == ''){
                    //$("#dailyDateTip").html("日报日期不能为空");
                    return false;
                }
                var date1 = "<s:date name='@com.bank.dms.utils.DayUtil@getDay()' format='yyyy-MM-dd'/>";
                date1 = date1.replace("-","/").replace("-","/");
                var date2 = $dailyDate.replace("-","/").replace("-","/");
                var days = new Date(date2).getTime()-new Date(date1).getTime();
                days = days/(1000*24*60*60);
                if(!(days>=-1 && days<=1)){
                    if(days>0){
                        alert('对不起，您不能提前'+days+'天写日报！');
                    }else{
                    	alert('对不起，您不能修改'+(-days)+'天前的日报！');
                    }
                    //$("#dailyDateTip").html("只能填写昨天、今天或者明天的日报");
                    return false;
                }
                $("#dailyDateTip").html("");
                
                var $workload = $("#workload").val();
                if($workload == ''){
                    //$("#workloadTip").html("工作量不能为空");
                    return false;
                }
                $("#workloadTip").html("");

                var $content1 = $("#content1").val();
                if($content1 == ''){
                    //$("#contentTip").html("日报内容不能为空");
                    return false;
                }
                $("#contentTip").html("");

                var $workload = $("#workload").val();
                if($workload == ''){
                    //$("#workloadTip").html("工作量不能为空");
                    return false;
                }
                $("#workloadTip").html("");
            });
        });
        </script>
    </head>
    <body>
    <div>
        <form action="${pageContext.request.contextPath }/saveDaily" id="empForm" method="post">
        <h2>写日报</h2>
        <div style="margin-top: 15px;"></div>
        <div>
            <b class="x_color">*</b>日报日期：
            <s:textfield cssClass="W_input" name="daily.dailydate" theme="simple" id="dailyDate" onfocus="WdatePicker({onpicked:function(){return true;}});" readonly="true" placeholder="日报日期，必填项"></s:textfield>
            <span id="dailyDateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div style="padding-left: 12px;">
        <b class="x_color">*</b>工作量：
            <s:textfield cssClass="W_input" name="daily.workload" theme="simple" onkeyup="value=value.replace(/[^\d]/g,'')" id="workload" placeholder="工作量，必填项"></s:textfield>
            <span id="workloadTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
            <b class="x_color">*</b>日报内容：
        <div style="margin-left: 80px; margin-top: -20px;">
            <s:textarea name="daily.content" theme="simple" id="content1" cols="50" rows="10" cssStyle="resize: none;" placeholder="日报内容，必填项"></s:textarea>
            <span id="contentTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <div style="width: 340px;"><button class="btn_weight btn_dailyreg" id="sub">提交</button></div>
        </form>
        </div>
    </body>
</html>