<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Cache-Control" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/boder-scoll-style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/emp.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <title>日报系统-员工管理-员工列表</title>
        
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
                    $("#dept").find("option[value='${searchEmp.department.id}']").attr("selected",true);
                }
            });

            $("#dept").change(function(){
                location.href="${pageContext.request.contextPath }/listEmployee.action?searchEmp.department.id=" + $(this).val();
            });
        });
        </script>
    </head>
    <body>
        <h2>员工列表</h2>
        <div style="margin-top: 20px;">
        <form action="${pageContext.request.contextPath }/listEmployee.action" id="emplistForm" method="post" class="searchtypeheight">
        <span>
            <label>部门：</label>
            <select id="dept" name="searchEmp.department.id" class="W_input">
                <option value="">请选择</option>
            </select>
        </span>
        </form>
        </div>
        
        <div style="margin-top: 10px;"></div>
        <table id="center">
            <tr>
                <th width="10%" height="28">
                    <font style="font-weight: bold">姓名</font>
                </th>
                <th width="8%" height="28">
                    <font style="font-weight: bold">性别</font>
                </th>
                <th width="11%" height="28">
                    <font style="font-weight: bold">电话</font>
                </th>
                <th width="12%" height="28">
                    <font style="font-weight: bold">邮箱</font>
                </th>
                <th width="9%" height="28">
                    <font style="font-weight: bold">状态</font>
                </th>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <th width="6%" height="28" valign="middle">
                    <font style="font-weight: bold">操作</font>
                </th>
                </sec:authorize>
            </tr>
            <s:iterator value="#request.list" status="i">
            <s:if test="#request.i.index%2==0">
                <tr>
            </s:if>
            <s:else>
                <tr class="alt">
            </s:else>
                <td height="20" align="left">
                    <s:property value="#request.name"/>
                    &nbsp;
                </td>
                <td height="20" align="left">
                    <s:property value="#request.gender"/>&nbsp;
                </td>
                <td height="20" align="left">
                    <s:property value="#request.phone"/>&nbsp;
                </td>
                <td height="20" align="left">
                    <s:property value="#request.email"/>&nbsp;
                </td>
                <td height="20" align="left">
                    <%--
                    <s:property value="#request.status"/>&nbsp;
                     --%>
                     在职
                </td>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <td height="20" align="center">
                    <span><a href="editEmployee?emp.id=<s:property value='#request.id'/>&pageNum.page=${pageNum.page}&searchEmp.department.id=${searchEmp.department.id}&searchEmp.role.id=${searchEmp.role.id}&searchEmp.name=${searchEmp.name}" title="编辑"><img src="${pageContext.request.contextPath }/images/edit-1.png" width="16" height="16" /></a></span>
                    <span><a href="javascript:if(confirm('确定删除吗？')){window.location.href='${pageContext.request.contextPath }/deleteEmployee?emp.id=<s:property value='#request.id'/>&pageNum.page=${pageNum.page}&searchEmp.department.id=${searchEmp.department.id}&searchEmp.role.id=${searchEmp.role.id}&searchEmp.name=${searchEmp.name}';}" title="删除"><img src="${pageContext.request.contextPath }/images/del.png" width="16" height="16" /></a></span>
                </td>
                </sec:authorize>
            </tr>
        </s:iterator>
        </table>
    </body>
</html>