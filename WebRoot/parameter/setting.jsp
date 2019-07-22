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
        <title>日报系统-日报管理-配置</title>
        
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
        });
        </script>
        
    </head>
    <body>
        <h2>参数配置</h2>
        <div style="margin-top: 15px;"></div>
        <div>
            <table id="center" style="width: 690px;" cellpadding="0" cellspacing="0" border="0px;">
                <tr>
                    <th width="35" height="28">
                        <font style="font-weight: bold">序号</font>
                    </th>
                    <th width="100" height="28">
                        <font style="font-weight: bold">名称</font>
                    </th>
                    <th width="100" height="28">
                        <font style="font-weight: bold">代号</font>
                    </th>
                    <th width="100" height="28">
                        <font style="font-weight: bold">值</font>
                    </th>
                    <th width="175" height="28">
                        <font style="font-weight: bold">表达式</font>
                    </th>
                    <th width="70" height="28">
                        <font style="font-weight: bold">排序</font>
                    </th>
                    <th width="110" height="28">
                        <font style="font-weight: bold">操作</font>
                    </th>
                </tr>
            </table>
        <div style="margin-top: 3px;"></div>
            <table style="width: 690px;" cellpadding="0" cellspacing="0">
            <s:iterator value="list" status="i">
            <form action="${pageContext.request.contextPath }/saveParameter.action" method="post">
                <tr>
                <%--
                    <td align="center" width="35" height="28"><s:hidden name="parameter.id" value="%{id}"></s:hidden><s:property value="id" />&nbsp;</td>
                --%>
                    <td align="center" width="35" height="28"><s:hidden name="parameter.id" value="%{id}"></s:hidden>${i.count }&nbsp;</td>
                    <td align="center" width="100"><input name="parameter.name" value="<s:property value="name" />" size="11"/></td>
                    <td align="center" width="100"><input name="parameter.code" value="<s:property value="code" />" size="11"/></td>
                    <td align="center" width="100"><input name="parameter.value" value="<s:property value="value" />" size="11"/></td>
                    <td align="center" width="165"><input name="parameter.expression" value="<s:property value="expression" />" size="20"/></td>
                    <td align="center" width="70"><input name="parameter.orderBy" value="<s:property value="orderBy" />" size="6"/></td>
                    <td align="center" width="110">
                        <input class="btn_weight_min" type="submit" value="修改"/>
                        <input class="btn_weight_min" type="button" onclick="javascript:if(confirm('确定删除吗？')){window.location.href='${pageContext.request.contextPath }/deleteParameter?parameter.id=<s:property value="id" />';}" value="删除"/>
                    </td>
                </tr>
            </form>
            </s:iterator>
            </table>
        </div>
        <div style="margin-top: 10px;"></div>
        <hr />
        <div style="margin-top: 15px;"></div>
        <h2>新建参数</h2>
        <div style="margin-top: 5px;"></div>
        <div>
        <form action="${pageContext.request.contextPath }/saveParameter.action" method="post">
            <table style="width: 630px;" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center" width="35" height="28">&nbsp;</td>
                    <td align="center" width="80"><input name="parameter.name" size="11" placeholder="名称"/></td>
                    <td align="center" width="70"><input name="parameter.code" size="11" placeholder="代号"/></td>
                    <td align="center" width="70"><input name="parameter.value" size="11" placeholder="值"/></td>
                    <td align="center" width="145"><input name="parameter.expression" size="20" placeholder="表达式"/></td>
                    <td align="center" width="70"><input name="parameter.orderBy" size="6" placeholder="排序"/></td>
                    <td align="center" width="30"><input class="btn_weight_min" type="submit" value="新建"/></td>
                </tr>
            </table>
        </form>
        </div>
        <div style="margin-top: 10px;"></div>
        <hr />
        <div style="margin-top: 15px;"></div>
        <h2>角色</h2>
        <div style="margin-top: 10px;"></div>
        <table id="center" style="width: 600px;" cellpadding="0" cellspacing="0" border="0px;">
                <tr>
                    <th align="center" width="30" height="28"><font style="font-weight: bold">序号</font></th>
                    <th align="center" width="90"><font style="font-weight: bold">名称</font></th>
                    <th align="center" width="85"><font style="font-weight: bold">描述</font></th>
                    <th align="center" width="90"><font style="font-weight: bold">code</font></th>
                    <th align="center" width="55"><font style="font-weight: bold">操作</font></th>
                </tr>
        </table>
        <div style="margin-top: 3px;"></div>
        <table style="width: 630px;" cellpadding="0" cellspacing="5">
                <s:iterator value="#request.roleList" status="i">
                <form action="${pageContext.request.contextPath }/doEditRole">
                        <tr>
                            <td align="center" width="30">
                                &nbsp;${i.count }<%--<s:property value="#request.id"/>--%>&nbsp;
                                <s:hidden name="role.id" value="%{#request.id}"></s:hidden>
                            </td>
                            <td align="center" width="100" style="padding-left: 25px;">
                                <s:textfield name="role.name" value="%{#request.name}" theme="simple" size="16"></s:textfield>
                            </td>
                            <td align="center" width="100" style="padding-left: 15px;">
                                <s:textfield  name="role.descr" value="%{#request.descr}" theme="simple" size="16"></s:textfield>
                            </td>
                            <td align="center" width="100" style="padding-left: 15px;">
                                <s:textfield  name="role.code" value="%{#request.code}" theme="simple" size="16"></s:textfield>
                            </td>
                            <td height="20" align="left" style="padding-left: 10px;">
                                <button class="btn_weight_min" type="submit">修改</button>
                                <button class="btn_weight_min" type="button" onclick="javascript:if(confirm('确定删除吗？')){window.location.href='${pageContext.request.contextPath }/deleteRole?role.id=<s:property value='#request.id'/>';}">删除</button>
                            </td>
                        </tr>
                    </form>
                </s:iterator>
        </table>
        <div style="margin-top: 15px;"></div>
        <hr />
        <div style="margin-top: 10px;"></div>
        <h2>新建角色</h2>
        <div style="margin-top: 5px;"></div>
        <div style="margin-left: 65px;">
        <form action="${pageContext.request.contextPath }/saveRole">
            <s:textfield name="role.name" theme="simple" id="empname" size="16" placeholder="名称"></s:textfield>
            <div style="margin-top: -18px; margin-left: 150px;">
                <s:textfield name="role.descr" theme="simple" id="phone" size="16" placeholder="描述"></s:textfield>
            </div>
            <div style="margin-top: -18px; margin-left: 280px;">
                <s:textfield name="role.code" theme="simple" id="phone" size="16" placeholder="code"></s:textfield>
            </div>
            <div style="margin-top: -24px; margin-left: 440px;">
                <input class="btn_weight_min" type="submit" value="新建"/>
            </div>
        </form>
        </div>
    </body>
</html>