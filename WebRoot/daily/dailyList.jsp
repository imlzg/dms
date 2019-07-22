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
        <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
        <title>日报系统-日报管理-我的日报</title>
        
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
                        $("#role").append($option);
                    });
                    $("#role").find("option[value='${searchDaily.empid}']").attr("selected",true);
                }
            });
			WdatePicker({eCont: 'calender', onpicked: function(dp) {
				//alert('你选择的日期是:'+dp.cal.getDateStr());
                
                $("#dailyDate").html('日报日期：');
                $("#content1").html('');
                $("#status").html('');
                $("#reviewcontent1").html('');
                        
                var url = '${pageContext.request.contextPath }/jsonaction_previewDaily?searchDaily.empid=${userdetails.id }&searchDaily.dailydate='+dp.cal.getDateStr();
                $.ajax( {
                    type : 'post',
                    url : url,
                    dataType : 'json',
                    success : function(data) {
                    if(data == null){
                        alert('您还没有填写'+dp.cal.getDateStr()+'的日报');
                    }
                        $("#dailyDate").html('日报日期：'+data.dailydate);
                        $("#content1").html(data.content);
                        $("#status").html(data.status!=''&&data.status==1?'通过':'未通过');
                        $("#reviewcontent1").html(data.reviewcontent);
                    }
                });
			}});
        });

        function toDays(day){
            $("#dailyDate").html('日报日期：');
            $("#content1").html('');
            $("#status").html('');
            $("#reviewcontent1").html('');
                    
            var url = '${pageContext.request.contextPath }/jsonaction_previewDaily?searchDaily.empid=${userdetails.id }&searchDaily.dailydate='+day;
            $.ajax( {
                type : 'post',
                url : url,
                dataType : 'json',
                success : function(data) {
                    $("#dailyDate").html('日报日期：'+data.dailydate);
                    $("#content1").html(data.content);
                    $("#status").html(data.status!=''&&data.status==1?'通过':'未通过');
                    $("#reviewcontent1").html(data.reviewcontent);
                }
            });
        }
        </script>
        <style>
        .area_resize{
            resize:none;
            overflow-y: scroll;
        }
        </style>
    </head>
    <body>
        <h2>我的日报</h2>
        <div style="margin-top: 5px;"></div>
		<div id="calender"></div>
        <div style="margin-top: 10px; margin-left: 98px;"><button class="btn_weight" onclick="toDays('<s:date name="@com.bank.dms.utils.DayUtil@getDay()" format="yyyy-MM-dd"/>')">今天</button></div>
        <br/>
        <div id="dailyDate">
            日报日期：${daily.dailydate }
            <%--
            <input id="dailyDate" class="W_input" value="${daily.dailydate }" readonly="readonly"/>
             --%>
        </div>
        <br/>
        <div>日报内容：</div>
        <div style="margin-top: 10px;" id="content1">${daily.content }
            <%--
            <textarea id="content1" class="area_resize" rows="5" cols="38" readonly="readonly">${daily.content }</textarea>
             --%>
        </div>
        <br/>
        <div>
            批复状态：
            <span id="status">
            <s:if test="daily != null">
            ${daily.status!=''&&daily.status==1?'通过':'未通过' }
            </s:if>
            </span>
            <div style="float: left;margin-right: 100px;">批复内容：${daily.reviewcontent }</div>
        </div>
        <div style="margin-top: 10px;" id="reviewcontent1">
            <%--
            <textarea id="reviewcontent1" class="area_resize" rows="5" cols="38" readonly="readonly">${daily.reviewcontent }</textarea>
             --%>
        </div>
    </body>
</html>