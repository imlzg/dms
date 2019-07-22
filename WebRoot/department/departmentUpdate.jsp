<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>

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
                    $("#deptManager").append($option);
                });
                $("#deptManager").find("option[value='${department.manager.id}']").attr("selected",true);
            }
        });
        $("#deptname").blur(function(){
            $("#deptnameTip").html('');
        });

        $("#sub").click(function(){
            if($("#deptname").val()==''){
                return false;
            }
            if($("#deptManager").val()==''){
            	$("#deptManagerTip").html('请选择部门负责人');
                return false;
            }
            $("#deptnameTip").html('');
        });
    });
    </script>

  </head>
  
  <body>
    <div style="margin-top: 10px;"></div>
    <h2>编辑部门</h2>
    <div style="margin-top: 5px;"></div>
    <form action="${pageContext.request.contextPath }/doUpdateDepartment.action" method="post">
        <s:hidden name="department.id"></s:hidden>
        <div class="div_top">
            <b class="x_color">*</b>部门名称：
            <s:textfield name="department.name" cssStyle="margin-left: 20px;" cssClass="W_input input_left" id="deptname" placeholder="部门名称，必填项"/>
            <span id="deptnameTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div class="div_top">
            <b style="margin-left: 11px;" class="x_color">*</b>负责人：
            
            <select class="W_input input_left" style="margin-left: 20px;" name="department.manager.id" id="deptManager">
                <option value="">请选择</option>
            </select>
            
            <span id="deptManagerTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br />
        <b class="x_color">&nbsp;</b>描述信息：
        <div style="margin-left: 96px; margin-top: -10px;">
            <textarea name="department.descr" rows="8" cols="45" style="resize: none;">${department.descr }</textarea>
            <span id="phoneTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <input type="submit" value="更新" style="margin-left: 220px; margin-top: 30px;" class="btn_weight" id="sub" />
    </form>
  </body>
</html>
