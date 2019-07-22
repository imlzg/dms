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
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/boder-scoll-style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/emp.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/emp.js"></script>
        <title>日报系统-员工管理-编辑信息</title>
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
        });
        </script>
    </head>
    <body>
        <h2>编辑员工</h2>
        <div class="div_main">
            <form action="${pageContext.request.contextPath }/doEditEmployee?pageNum.page=${pageNum.page}&searchEmp.department.id=${searchEmp.department.id}&searchEmp.role.id=${searchEmp.role.id}&searchEmp.name=${searchEmp.name}" id="empForm" method="post">
                    <div class="div_top">
                        <b class="x_color">*</b>姓名：
                        <s:hidden name="emp.id"></s:hidden>
                        <s:hidden name="emp.password"></s:hidden>
                        <s:textfield cssClass="W_input input_left" name="emp.name" theme="simple" id="empname" placeholder="员工姓名，必填项"></s:textfield>
                        <span id="empnameTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top">
                        <b class="x_color">*</b>性别：
                        <input class="input_left" type="radio" name="emp.gender" value="男" id="empsex1" ${emp.gender == '男'?'checked':'' }/>男
                        <input class="input_left" type="radio" name="emp.gender" value="女" id="empsex2" ${emp.gender == '女'?'checked':'' }/>女
                        <span id="sexTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top">
                    <b class="x_color">*</b>住址：
                        <s:textfield cssClass="W_input input_left" name="emp.address" theme="simple" id="address" placeholder="住址，必填项"></s:textfield>
                        <span id="addressTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top">
                    <b class="x_color">*</b>电话：
                        <s:textfield cssClass="W_input input_left" name="emp.phone" theme="simple" id="phone" placeholder="电话为手机号码，必填项"></s:textfield>
                        <span id="phoneTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top">
                    <b class="x_color">*</b>邮箱：
                        <s:textfield cssClass="W_input input_left" name="emp.email" theme="simple" id="email" placeholder="邮箱，必填项"></s:textfield>
                        <span id="emailTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top">
                    <b class="x_color">*</b>部门：
                    <select class="W_input input_left" name="emp.department.id" id="dept">
                        <option value="">请选择</option>
                    </select>
                    <span id="deptTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top">
                    <b class="x_color">*</b>角色：
                    <select class="W_input input_left" name="emp.role.id" id="role">
                        <option value="">请选择</option>
                    </select>
                    <span id="roleTip" style="font-size: 12px; color: #CD0000;"></span>
                    </div>
                    <div class="div_top but_right">
                        <input type="submit" id="sub"
                            value="更新" class="btn_weight" />
                    </div>
            </form>
        </div>
    </body>
</html>