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
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/webmaster.css" type="text/css"></link>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/boder-scoll-style.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/publicJs.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css" type="text/css"></link>
        <title>日报系统-角色管理-角色列表</title>
        
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
                    $("#role").find("option[value='${searchEmp.role.id}']").attr("selected",true);
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
                <div class="business">
                    <h3>角色管理>>角色列表</h3>
                    <form action="${pageContext.request.contextPath }/listRole.action" method="post" class="searchtypeheight">
                    <span>
                        <label>角色名包含 ：</label>
                        <s:textfield name="searchRole.name" id="profmarket_name" placeholder="请输入您要查询的关键字"></s:textfield>
                    </span>
                    <span><input type="submit" value="搜索" id="btn"/></span>
                    </form>
                    <table id="tableId" width="100%" border="0" cellspacing="0" cellpadding="0" class="business_list nooo_wrap">
                        <tr>
                            <td width="5%" height="28" align="center" valign="middle" bgcolor="#dee7ee">
                                <font style="font-weight: bold">序号</font>
                            </td>
                            <td width="10%" height="28" align="center" valign="middle" bgcolor="#dee7ee">
                                <font style="font-weight: bold">角色名称</font>
                            </td>
                            <td width="8%" height="28" align="center" valign="middle" bgcolor="#dee7ee">
                                <font style="font-weight: bold">角色描述</font>
                            </td>
                            <td width="6%" height="28" align="center" valign="middle" bgcolor="#dee7ee">
                                <font style="font-weight: bold">操作</font>
                            </td>
                        </tr>
                        <s:iterator value="#request.list">
                        <tr>
                            <td height="25" align="center">
                                &nbsp;<s:property value="#request.id"/>&nbsp;
                            </td>
                            <td height="20" align="left">
                                <s:property value="#request.name"/>
                                &nbsp;
                            </td>
                            <td height="20" align="left">
                                <s:property value="#request.descr"/>&nbsp;
                            </td>
                            <td height="20" align="center">
                                <span><a href="editRole?role.id=<s:property value='#request.id'/>&pageNum.page=${pageNum.page}&searchRole.name=${searchRole.name}" title="编辑"><img src="${pageContext.request.contextPath }/images/edit-1.png" width="16" height="16" /></a></span>
                                <span><a href="javascript:if(confirm('确定删除吗？')){window.location.href='${pageContext.request.contextPath }/deleteRole?role.id=<s:property value='#request.id'/>&pageNum.page=${pageNum.page}&searchRole.name=${searchRole.name}';}" title="删除"><img src="${pageContext.request.contextPath }/images/del.png" width="16" height="16" /></a></span>
                            </td>
                        </tr>
                    </s:iterator>
                    </table>
                </div>
                <div class="next" style="display:${pageNum.totalCount==0?'none':'' }">
                            共${pageNum.totalPage}页/${pageNum.totalCount }条记录 
                            <c:choose>
                                <c:when test="${pageNum.page>1}">
                                    <a href="${pageContext.request.contextPath }/listRole.action?pageNum.page=${pageNum.page-1}&searchRole.name=${searchRole.name}">上一页</a>
                                </c:when>
                                <c:otherwise>
                                    <span disabled="true">上一页</span>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${pageNum.page<=1}">
                                    <span disabled="true">首页</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath }/listRole.action?pageNum.page=${pageNum.page-1}&searchRole.name=${searchRole.name}">首页</a>
                                </c:otherwise>
                            </c:choose>
                            
                            <s:iterator var="i" begin="pageNum.page/10*10==0?pageNum.page/10*10+1:pageNum.page/10*10" end="(pageNum.page/10+1)*10>pageNum.totalPage?pageNum.totalPage:(pageNum.page/10+1)*10">
                                <s:if test="pageNum.page==#i"><span style="color:#cd0000;" disabled="true"><s:property value="#i"/></span></s:if>
                                <s:else><a href="${pageContext.request.contextPath }/listRole.action?pageNum.page=${pageNum.page-1}&searchRole.name=${searchRole.name}"><s:property value="#i"/></a></s:else>
                            </s:iterator>
                            <%-- 页代数 --%>
                            <c:choose>
                                <c:when test="${pageNum.page>=pageNum.totalPage}">
                                    <span disabled="true">尾页</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath }/listRole.action?pageNum.page=${pageNum.totalPage}&searchRole.name=${searchRole.name}">尾页</a>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${pageNum.page<pageNum.totalPage}">
                                    <a href="${pageContext.request.contextPath }/listRole.action?pageNum.page=${pageNum.page+1}&searchRole.name=${searchRole.name}">下一页</a>
                                </c:when>
                                <c:otherwise>
                                    <span disabled="true">下一页</span>
                                </c:otherwise>
                            </c:choose>
                    </div>
                    <div class="clear"></div>
                    <div style="color:#CD0000; font-size:14px;text-align:center;">${mssage }</div>
                </div>
            </div>
        </div>
    </body>
</html>