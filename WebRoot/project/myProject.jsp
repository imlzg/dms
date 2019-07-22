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
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/publicJs.js"></script>
        <title>日报系统-日报管理-我参与的项目</title>
        
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
        </script>
        
    </head>
    <body>
        <h2>我参与的项目</h2>
        <br/>
        <div>
            <table id="center" style="width: 580px;">
            <s:iterator value="#request.list" var="pro" status="i">
                <s:if test="#request.i.index%2==0">
                    <tr>
                </s:if>
                <s:else>
                    <tr class="alt">
                </s:else>
                    <td width="60%" height="28" align="left">${pro[1] }</td>
                    <td align="center"><a href="${pageContext.request.contextPath }/editProject?pro.id=${pro[0] }">查看</a></td>
                </tr>
            </s:iterator>
            </table>
        </div>
    </body>
</html>