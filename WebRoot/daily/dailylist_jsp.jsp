<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Cache-Control" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
        <title>日报系统-日报管理-日报列表</title>
        
        <script>
        $(function(){
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
                    $("#dept").find("option[value='${searchDaily.deptid}']").attr("selected",true);
                }
            });
            
            var url = '${pageContext.request.contextPath }/jsonaction_allEmployee.action?d='+Math.random();
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
                    $("#role").find("option[value='${searchDaily.empid}']").attr("selected",true);
                }
            });
        });
        function task(val){
            var url = '${pageContext.request.contextPath }/jsonaction_task.action?name=' + val;
            $.ajax( {
                type : 'post',
                url : url,
                dataType : 'json',
                success : function(data) {
                alert('提示成功');
                }
            });
        }
        </script>
        <style>
        .area_resize{
            resize:none;
            overflow-y: scroll;
        }
        </style>
    </head>
    <body>
        <h2>日报列表</h2>
        <div style="margin-top: 10px;"></div>
        <form action="${pageContext.request.contextPath }/listDaily">
        <div>
            <input id="dailyDate" placeholder="日报日期" value="${daily.dailydate }" name="daily.dailydate" class="W_input" onfocus="WdatePicker({onpicked:function(){return true;}});" readonly="readonly"/>
        </div>
        <div style="margin-left: 280px; margin-top: -28px;">
            <input type="submit" id="sub" value="查看" class="btn_weight" />
        </div>
        </form>
        <br/>
        <div>已提交日报：</div>
        <div style="margin-top: 10px;"></div>
        <hr class=dotline color=#111111 size=1 />
        <div style="margin-top: 10px;"></div>
        已审核：
        <div style="margin-top: 10px;">
            <table id="center" style="width: 580px;">
                <s:iterator value="#request.submitted" var="mitted" status="i">
                    <s:if test="#request.mitted[2]!=null">
                        <s:if test="#request.i.index%2==0">
                            <tr>
                        </s:if>
                        <s:else>
                            <tr class="alt">
                        </s:else>
                            <td width="60%" height="28" align="left">${mitted[0] }</td>
                            <td align="center" width="40%"><a href="${pageContext.request.contextPath }/reviewDaily?daily.id=${mitted[1] }"><%--审核 --%></a></td>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>
        </div>
        <div style="margin-top: 10px;"></div>
        <hr class=dotline color=#111111 size=1 />
        <div style="margin-top: 10px;"></div>
        未审核：
        <div style="margin-top: 10px;">
            <table id="center" style="width: 580px;">
                <s:iterator value="#request.submitted" var="mitted" status="i">
                    <s:if test="#request.mitted[2]==null">
                        <s:if test="#request.i.index%2==0">
                            <tr>
                        </s:if>
                        <s:else>
                            <tr class="alt">
                        </s:else>
                            <td width="60%" height="28" align="left">${mitted[0] }</td>
                            <td align="center" width="40%"><a href="${pageContext.request.contextPath }/reviewDaily?daily.id=${mitted[1] }">审核</a></td>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>
        </div>
        <br/>
        <hr />
        <br/>
        <div>
            未提交日报：
        </div>
        <div style="margin-top: 10px;">
            <table id="center" style="width: 580px;">
            <s:iterator value="#request.uncommitted" var="mitted" status="i">
                <s:if test="#request.i.index%2==0">
                    <tr>
                </s:if>
                <s:else>
                    <tr class="alt">
                </s:else>
                    <td width="60%" height="28" align="left">${mitted[0] }</td>
                    <td align="center"><a href="javascript:task('${mitted[0] }')"><%--提醒 --%></a></td>
                </tr>
            </s:iterator>
            </table>
        </div>
    </body>
</html>