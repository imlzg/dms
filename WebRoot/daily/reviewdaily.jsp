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
        <title>日报系统-日报管理-日报审核</title>
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
                if($dailyDate == ''){
                    $("#dailyDateTip").html("日报日不能为空");
                    return false;
                }
                $("#dailyDateTip").html("");
            });

            $("#content1").blur(function(){
                var $content1 = $("#content1").val();
                if($content1 == ''){
                    $("#contentTip").html("日报内容不能为空");
                    return false;
                }
                $("#contentTip").html("");
            });

            $("#workload").blur(function(){
                var $workload = $("#workload").val();
                if($workload == ''){
                    $("#workloadTip").html("工作量不能为空");
                    return false;
                }
                $("#workloadTip").html("");
            });

            $("#sub").click(function(){
                var $dailyDate = $("#dailyDate").val();
                if($dailyDate == ''){
                    $("#dailyDateTip").html("日报日不能为空");
                    return false;
                }
                $("#dailyDateTip").html("");

                var $content1 = $("#content1").val();
                if($content1 == ''){
                    $("#contentTip").html("日报内容不能为空");
                    return false;
                }
                $("#contentTip").html("");

                var $workload = $("#workload").val();
                if($workload == ''){
                    $("#workloadTip").html("工作量不能为空");
                    return false;
                }
                $("#workloadTip").html("");
            });
        });
        </script>
    </head>
    <body>
    <div style="margin-top: 0px;">
        <form name="reviewForm" method="post">
        <h2>日报审核</h2>
        <div style="margin-top: 10px;"></div>
        <div>
        <s:hidden name="daily.id"></s:hidden>
        <%--
        <s:hidden name="daily.status" value="1"></s:hidden>
         --%>
        <s:hidden name="daily.dailydate"></s:hidden>
        <s:hidden name="daily.employee.id"></s:hidden>
            日期：${daily.dailydate }
            <s:date name="daily.dailydate" format="yyyy年MM月dd日"/>
            <span id="dailyDateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div>
            姓名：${daily.employee.name }
            <s:date name="daily.dailydate" format="yyyy年MM月dd日"/>
            <span id="dailyDateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
            日报内容：<span id="contentTip" style="font-size: 12px; color: #CD0000;"></span>
        <div style="margin-left: 0px; margin-top: 5px;">
            ${daily.content }
            <s:hidden name="daily.content"></s:hidden>
            <%--
            <s:textarea name="daily.content" theme="simple" id="content1" cols="40" rows="6" cssStyle="resize: none;" readonly="true"></s:textarea>
             --%>
        </div>
        <br/>
            批复内容：
        <div style="margin-left: 0px; margin-top: 5px;">
            <s:textarea name="daily.reviewcontent" theme="simple" id="" cols="40" rows="4" cssStyle="resize: none;"></s:textarea>
            <span id="contentTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <div style="width: 400px;">
            <button class="btn_weight btn_reviewdaily" type="button" onclick="mysubmit(1)">通过</button>
            <button class="btn_weight btn_reviewdaily btn_reviewdailycx" onclick="mysubmit(-1)" type="button">不通过</button>
        </div>
        </form>
        </div>
        
        <script>
        function mysubmit(val){
        	document.reviewForm.action="${pageContext.request.contextPath }/doReviewDaily?daily.status=" + val;
        	document.reviewForm.submit();
        }
        </script>
    </body>
</html>