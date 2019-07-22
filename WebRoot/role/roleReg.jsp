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
        <title>日报系统-角色管理-新增信息</title>
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
        });
        </script>
    </head>
    <body>
        <div class="border-right-class" id="Main">
            <div class="Mainbody">
                <div id="content">
                    <div class="contert_nav">
                        <ul>
                            <li><a href="${pageContext.request.contextPath }/role/roleReg.jsp">新增信息</a></li>
                            <li><a href="${pageContext.request.contextPath }/listRole.action">信息列表</a></li>
                        </ul>
                    </div>
                    <div class="contert_main">
                        <h3>员工管理>>新增信息</h3>
                        <div class="area_conterner public business_x_height">
                            <form action="${pageContext.request.contextPath }/saveRole" id="empForm" method="post">
                                <div class="webmaster_main">
                                    <p>
                                        <b class="x_color">*</b>角色名称
                                        <s:textfield cssClass="W_input" name="role.name" theme="simple" id="empname"></s:textfield>
                                        <span id="empnameTip" style="font-size: 12px; color: #CD0000;"></span>
                                    </p>
                                    <p>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述
                                        <s:textfield cssClass="W_input" name="role.descr" theme="simple" id="phone"></s:textfield>
                                        <span id="phoneTip" style="font-size: 12px; color: #CD0000;"></span>
                                    </p>
                                    <p>
                                        <input type="submit" id="sub"
                                            value="创建" class="btn_bg" />
                                        <input type="reset" value="重置"
                                            class="btn_bg" />
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>