<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><decorator:title /></title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/center.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/tab_center.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <decorator:head />
    <style>
    #header{
        margin-left: 30px;
    }
    .main{
        margin-left: 110px;
        margin-top: 40px;
    }
    .left_tab1{
        position: absolute;
        display: none;
    }
    .left_tab2{
        position: absolute;
        display: block;
    }
    </style>
    
    <script type="text/javascript">
    function selectTag(val,name){
        if(isNaN(val))
            return;
        var url = '${pageContext.request.contextPath }/jsonaction_childMenus.action?val='+val + '&name=' + encodeURI(name);
        $.ajax( {
            type : 'post',
            url : url,
            dataType : 'json',
            success : function(data) {
            	$("#left_tab>tbody>tr>td").remove();
                if(data!=''){
                    //$("#div_tab").addClass('left_tab2').removeClass('left_tab1');
                }else{
                	//$("#div_tab").addClass('left_tab1').removeClass('left_tab2');
                }
                $("#operate").html(name);
                $("#left_menu").html('');
                $.each(data, function(i, list) {
                    if(list.id=='${attr.child_menuID}'){
                        $("#left_menu").append($("<div class=\"center_left_column_3\"><div class=\"fl p5 child_menu_bg\">" + "<a href='${pageContext.request.contextPath }/" + list.expression + "' onclick=\"setjs(" + list.id +")\">" + list.value + "</a>" + "</div></div>"));
                    }else{
                    	$("#left_menu").append($("<div class=\"center_left_column_3\"><div class=\"fl p5\">" + "<a href='${pageContext.request.contextPath }/" + list.expression + "' onclick=\"setjs(" + list.id +")\">" + list.value + "</a>" + "</div></div>"));
                    }
                });
            }
        });
    }
    function setjs(val){
        if(isNaN(val))
            return;
        var url = '${pageContext.request.contextPath }/jsonaction_setJs.action?val='+val;
        $.ajax( {
            type : 'post',
            url : url,
            dataType : 'json',
            success : function(data) {
            }
        });
    }
    </script>
  </head>
  
  <body>
    <div class="center">
<div class="center_content">

    <div class="center_left">
    <div class="center_left_column">
    <img src="${pageContext.request.contextPath }/images/center_left_column.png"/>
    </div>
    
    <div class="center_left_column_font">
    <font><sec:authentication property="name"/></font>
    <font style="margin-left:10px;margin-top:-90px;"><s:property value="@com.bank.dms.utils.DayUtil@getHourDay()"/>好！</font><br/>
    <font style="margin-left:10px;margin-top:200px;"><s:date name="@com.bank.dms.utils.DayUtil@getDay()" format="yyyy年MM月dd日"/></font><br/>
    </div>
    
    <div class="center_left_column_1">
    <div id="operate" class="fw white c pt15">首页</div> 
    </div>
    
    <div id="left_menu" class="center_left_column_2">
    
    
    </div>
    
    </div>
    
    <div class="center_right">
    <div class="center_right_column">
    <div class="fl pt20 pl10 bule_c fw F16">日报系统</div>
    <div style="float: right; margin-top: 30px; margin-right: 10px;"><a href="${pageContext.request.contextPath }/logout.action">注销</a></div>
    </div>
    
    <div class="center_right_column_1">
    
    <map name="Map" id="Map">
  <area shape="rect" coords="618,8,691,26" href="http://www26.53kf.com/webCompany.php?arg=wenshor&amp;style=1" target="_blank" />
</map>
           <div id=con>
           <ul id=tags>
           <s:iterator value="#session.menus">
           <s:if test="id==#session.menuID">
                <li class="selectTag">
           </s:if>
           <s:else>
               <li>
           </s:else><a onclick="selectTag(${id },'${value }')" href="${pageContext.request.contextPath }/<s:property value="expression"/>?val=${id }&name=${value }"><s:property value="value"/></a></li>
           </s:iterator>
            
           </ul>
           <div id=tagContent>
           <div class="tagContent selectTag" id="tagContent0" style="overflow: auto;">
            <decorator:body />
           </DIV>
           </DIV></DIV>
    </div>
    </div>
</div>
</div>
${js }
  </body>
</html>
