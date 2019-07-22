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
        <link href="${pageContext.request.contextPath }/css/webmaster.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/boder-scoll-style.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <title>日报系统-员工管理-编辑信息</title>
        <%--
        <style>
            .W_input{
                width: 200px;
                height: 28px;
            }
        </style>
         --%>
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
        <form action="${pageContext.request.contextPath }/doEditDaily?pageNum.page=${pageNum.page}" id="empForm" method="post">
            <table cellpadding="2" cellspacing="15">
                <tr>
                    <td align="right">日报日期：</td>
                    <td><s:textfield cssClass="W_input" name="daily.dailydate" theme="simple" id="dailyDate"></s:textfield></td>
                    <td><span id="dailyDateTip" style="font-size: 12px; color: #CD0000;"></span></td>
                </tr>
                <tr>
                    <td align="right">工作量：</td>
                    <td><s:textfield cssClass="W_input" name="daily.workload" theme="simple" onkeyup="value=value.replace(/[^\d]/g,'')" id="workload"></s:textfield></td>
                    <td><span id="workloadTip" style="font-size: 12px; color: #CD0000;"></span></td>
                </tr>
                <tr>
                    <td align="right">日报内容：</td>
                    <td>
                        <s:textarea name="daily.content" theme="simple" id="content1" cols="50" rows="10" cssStyle="resize: none;"></s:textarea>
                    </td>
                    <td><span id="contentTip" style="font-size: 12px; color: #CD0000;"></span></td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" id="sub" value="提交" class="btn_bg" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>