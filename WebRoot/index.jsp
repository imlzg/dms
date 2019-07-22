<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>DMS日报系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <STYLE type="text/css">
    .btn-revise{
        width:70px;
        height:20px;
        line-height:20px;
        text-align:center;
        float:left;
        color:#0079c3;
        background:#f2f6ff;
        border:1px solid #0080C0;
        cursor:pointer;
    }
    .btn-revise:hover{
        text-align:center;
        line-height:19px;
        float:left;
        color:#fff;
        color:#fff;
        background:#0080C0;
        border:1px solid #0080C0;
        cursor:pointer;
    }
    </STYLE>
  </head>
  
  <body>
    <h3><sec:authentication property="name"/></h3>
    <div style="margin-top: 5px;"><s:property value="@com.bank.dms.utils.DayUtil@getHourDay()"/>好！</div>
    <div style="margin-top: 5px;"><s:date name="@com.bank.dms.utils.DayUtil@getDay()" format="yyyy年MM月dd日"/></div>
    <div style="margin-top: 20px;">
    <s:if test="#request.todayDaily==null">
        <font size="5">您今日还没填写日报哦！</font>
        <div style="margin-left: 320px; margin-top: -20px;">
            <span class="btn-revise">
                <a href="${pageContext.request.contextPath }/daily/dailyReg.jsp" onclick="selectTag(3,'日报'),setjs(3)">去写日报</a>
            </span>
        </div>
    </s:if>
    <s:else>
        <font size="5">您今日已填写日报！</font>
    </s:else>
    </div>
    <div style="margin-top: 35px;">
        昨日日报批复：
        ${yesterdayDaily.status==1?'通过':'' }
        ${yesterdayDaily.status==-1?'重写':'' }
        ${yesterdayDaily != null && (yesterdayDaily.status==0 || yesterdayDaily.status==null)?'未通过':'' }
        ${yesterdayDaily==null || yesterdayDaily==''?'昨日没有填写日报':'' }
    </div>
    <div></div>
    <div style="margin-top: 8px;">${yesterdayDaily.content }</div>
    <div style="margin-top: 40px;">
        <h2>我参与的项目：</h2>
    </div>
    <div>
        <div style="margin-top: 10px;"></div>
        <table id="center">
            <tr>
                <th width="9%" height="28">
                    <font style="font-weight: bold">项目名称</font>
                </th>
                <th width="9%" height="28">
                    <font style="font-weight: bold">开始时间</font>
                </th>
                <th width="9%" height="28">
                    <font style="font-weight: bold">结束时间</font>
                </th>
                <th width="9%" height="28">
                    <font style="font-weight: bold">负责人</font>
               </th>
            </tr>
            <s:iterator value="#request.myProject" status="i">
                <s:if test="#request.i.index%2==0">
                    <tr>
                </s:if>
                <s:else>
                    <tr class="alt">
                </s:else>
                    <td align="left">${name }</td>
                    <td align="left"><s:date name="startdate" format="yyyy年MM月dd日"/></td>
                    <td align="left"><s:date name="enddate" format="yyyy年MM月dd日"/></td>
                    <td align="left">${employee.name }</td>
                </tr>
            </s:iterator>
        </table>
    </div>
  </body>
</html>
