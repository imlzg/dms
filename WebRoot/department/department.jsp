<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>日报系统-部门管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
    $(function(){
        $("#deptname").blur(function(){
            /*
            if($(this).val()==''){
                $("#deptnameTip").html('部门名称不能为空');
                return;
            }
            */
            $("#deptnameTip").html('');
        });

        $("#sub").click(function(){
            if($("#deptname").val()==''){
                //$("#deptnameTip").html('部门名称不能为空');
                return false;
            }
            $("#deptnameTip").html('');
        });
    });
    </script>

  </head>
  
  <body>
    <h2>部门列表</h2>
    <div style="margin-top: 10px;"></div>
    <table id="center">
        <tr>
            <th width="25%" height="28">
                <font style="font-weight: bold">部门</font>
            </th>
            <th width="25%" height="28">
                <font style="font-weight: bold">负责人</font>
            </th>
            <th width="40%" height="28">
                <font style="font-weight: bold">简介</font>
            </th>
            <sec:authorize ifAnyGranted="ROLE_ADMIN">
            <th width="10%" height="28">
                <font style="font-weight: bold">操作</font>
            </th>
            </sec:authorize>
        </tr>
        <s:iterator value="list" status="i">
            <s:if test="#request.i.index%2==0">
                <tr>
            </s:if>
            <s:else>
                <tr class="alt">
            </s:else>
                <td align="left">${name }</td>
                <td align="left">${manager.name }</td>
                <td align="left">${descr }</td>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <td align="left">
                    <a href="${pageContext.request.contextPath }/updateDepartment?department.id=${id}" title="编辑"><img src="${pageContext.request.contextPath }/images/edit-1.png" width="16" height="16" /></a>
                    <a href="${pageContext.request.contextPath }/deleteDepartment?department.id=${id}" title="删除"><img src="${pageContext.request.contextPath }/images/del.png" width="16" height="16" /></a>
                </td>
                </sec:authorize>
            </tr>
        </s:iterator>
    </table>
    <div style="margin-top: 15px;"></div>
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
    <hr />
    <div style="margin-top: 10px;"></div>
    <h2>新增部门</h2>
    <div style="margin-top: 5px;"></div>
	<form action="${pageContext.request.contextPath }/saveDepartment.action" method="post">
        <div class="div_top">
          <b class="x_color">*</b>部门名称：
            <input name="department.name" type="text" style="margin-left: 20px;" class="W_input input_left" id="deptname" placeholder="部门名称，必填项"/>
            <span id="deptnameTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
            <b class="x_color">&nbsp;</b>描述信息：
        <div style="margin-left: 96px; margin-top: -10px;">
            <textarea name="department.descr" rows="8" cols="45" style="resize: none;"></textarea>
            <span id="phoneTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
		<input type="submit" value="增加" style="margin-left: 220px; margin-top: 30px;" class="btn_weight" id="sub" />
	</form>
    </sec:authorize>
  </body>
</html>
