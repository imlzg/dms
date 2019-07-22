<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ck" uri="http://ckeditor.com"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>日报系统-系统配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<ul>
	<s:iterator value="list">
		<li>
		<form action="<%=basePath %>/saveParameter.action" method="post">
			<s:property value="id"/>
			<input name="parameter.id" type="hidden" value="<s:property value="id" />" />
			<input name="parameter.name" type="text" value="<s:property value="name" />" />
			<input name="parameter.code" type="text" value="<s:property value="code" />" />
			<input name="parameter.value" type="text" value="<s:property value="value" />" />
			<input name="parameter.descr" type="text" value="<s:property value="descr" />" />
			<input type="submit" value="修改" />
		</form>
		</li>
	</s:iterator>
	</ul>
	<form action="<%=basePath %>/saveParameter.action" method="post">
		<input name="parameter.name" type="text"/>
		<input name="parameter.code" type="text"/>
		<input name="parameter.value" type="text"/>
		<input name="parameter.descr" type="text"/>
		<input type="submit" value="提交" />
	</form>
  </body>
</html>
