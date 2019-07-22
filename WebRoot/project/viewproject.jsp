<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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
        <title>日报系统-日报管理-查看项目</title>
        
        <script>
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
                        $("#empid").append($option);
                    });
                    $("#empid").find("option[value='${pro.employee.id}']").attr("selected",true);
                }
            });
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
                    $("#dept").find("option[value='${deptid}']").attr("selected",true);
                }
            });

            $("#dept").change(function(){
                $("#proEmpsForm").submit();
            });

            $("#sub").click(function(){
                var $proname = $("#proname").val();
                if($proname == ''){
                    //$("#dailyDateTip").html("日报日不能为空");
                    return false;
                }
                $("#pronameTip").html("");

                var $startdate = $("#startdate").val();
                if($startdate == ''){
                    //$("#startdateTip").html("日报内容不能为空");
                    return false;
                }
                $("#startdateTip").html("");

                var $enddate = $("#enddate").val();
                if($enddate == ''){
                    //$("#enddateTip").html("工作量不能为空");
                    return false;
                }
                if($enddate<$startdate){
                    $("#enddateTip").html("项目结束时间必须大于开始时间");
                    return false;
                }
                if($("#empid").val() == ''){
                	$("#empidTip").html("请选择项目负责人");
                    return false;
                }
                $("#enddateTip").html("");
            });
        });

        function checkedAll(val,thisObj){
            var $inpche = $("#"+val+">tbody>tr>td>input");
            var size = $inpche.length;
            for(var i = 0; i < size; i++){
            	$inpche[i].checked=thisObj.checked;
            }
        }
        </script>
        
    </head>
    <body>
        <h2>查看项目</h2>
        <br/>
        <c:if test="${pro.employee.id == userdetails.id || userdetails.role.code == 'ROLE_DIRECTOR'}">
        <form action="${pageContext.request.contextPath }/doEditProject">
        <div>
            <b class="x_color">*</b>项目名称：
            <s:hidden name="pro.id"></s:hidden>
            <s:textfield cssClass="W_input" name="pro.name" theme="simple" id="proname" placeholder="项目名称，必填项"></s:textfield>
            <span id="pronameTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div>
        <b class="x_color">*</b>开始时间：
        <%--
            <s:textfield cssClass="W_input" name="pro.startdate" theme="simple" id="startdate" readonly="readonly" onfocus="WdatePicker({onpicked:function(){return true;}});"></s:textfield>
         --%>
            <input class="W_input" name="pro.startdate" value='<s:date name="pro.startdate" format="yyyy-MM-dd"/>' id="startdate" readonly="readonly" onfocus="WdatePicker({onpicked:function(){return true;}});" placeholder="开始时间，必填项"/>
            <span id="startdateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <div>
        <b class="x_color">*</b>结束时间：
        <%--
            <s:textfield cssClass="W_input" name="pro.enddate" theme="simple" id="enddate" onfocus="WdatePicker({onpicked:function(){return true;}});"></s:textfield>
         --%>
            <input class="W_input" name="pro.enddate" value='<s:date name="pro.enddate" format="yyyy-MM-dd"/>' id="enddate" readonly="readonly" onfocus="WdatePicker({onpicked:function(){return true;}});" placeholder="结束时间，必填项"/>
            <span id="enddateTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        <sec:authorize ifAnyGranted="ROLE_DIRECTOR">
        <div>
        &nbsp;&nbsp;<b class="x_color">*</b>负责人：
        <%--
            <s:textfield cssClass="W_input" name="pro.employee.id" theme="simple" onkeyup="value=value.replace(/[^\d]/g,'')" id="workload"></s:textfield>
         --%>
            <select class="W_input input_left" name="pro.employee.id" id="empid">
                        <option value="">请选择</option>
                    </select>
            <span id="empidTip" style="font-size: 12px; color: #CD0000;"></span>
        </div>
        <br/>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_DIRECTOR">
            <s:hidden name="pro.employee.id"></s:hidden>
            
            <div>
            &nbsp;&nbsp;<b class="x_color">*</b>负责人：
            <s:property value="pro.employee.name"/>
            </div>
            <br/>
            
        </sec:authorize>
            <b class="x_color">&nbsp;</b>描述信息：<span id="contentTip" style="font-size: 12px; color: #CD0000;"></span>
        <div style="margin-top: -10px; margin-left: 80px;">
            <s:textarea name="pro.descr" theme="simple" id="descr1" cols="45" rows="8" cssStyle="resize: none;"></s:textarea>
        </div>
        
        <div style="width: 340px;"><button style="margin-top:8px;" class="btn_weight" id="sub">更新</button></div>
        </form>
        </c:if>
        
        <c:if test="${!(pro.employee.id == userdetails.id || userdetails.role.code == 'ROLE_DIRECTOR')}">
        <div>
            <b class="x_color">&nbsp;</b>项目名称：${pro.name }
        </div>
        <br/>
        <div>
        <b class="x_color">&nbsp;</b>开始时间：<s:date name="pro.startdate" format="yyyy-MM-dd"/>
        </div>
        <br/>
        <div>
        <b class="x_color">&nbsp;</b>结束时间：<s:date name="pro.enddate" format="yyyy-MM-dd"/>
        </div>
        <br/>
            <b class="x_color">&nbsp;</b>描述信息：
        <div style="margin-top: 10px; margin-left: 13px;">
            ${pro.descr }
        </div>
        </c:if>
        
        
        <c:if test="${pro.employee.id == userdetails.id || userdetails.role.code == 'ROLE_DIRECTOR'}">
        <div style="margin-top: 15px;"></div>
        <hr />
        <div style="width: 540px;">
        <form action="${pageContext.request.contextPath }/proStatus" style="width: 120px;">
            <s:hidden name="pro.id"></s:hidden>
            <s:hidden name="status" value="1"></s:hidden>
            <button style="margin-top:20px;" class="btn_weight">开始项目</button>
        </form>
        <form action="${pageContext.request.contextPath }/proStatus" style="margin-top: -45px;">
            <s:hidden name="pro.id"></s:hidden>
            <s:hidden name="status" value="-1"></s:hidden>
            <button style="margin-left:180px; margin-top: 15px;" class="btn_weight">关闭项目</button>
        </form>
        </div>
        </c:if>
        <div style="margin-top: 20px;"></div>
        <hr />
        <div style="margin-top: 15px;"></div>
        <h2>项目人员：</h2>
        <a name="project_delEmp" id="project_delEmp"></a>
        <div style="margin-top: 10px;"></div>
        <div>
            <table id="center" style="width: 580px;">
            <tr>
                <th width="20%" height="28">
                    <font style="font-weight: bold">姓名</font>
                </th>
                <th width="20%" height="28">
                    <font style="font-weight: bold">部门</font>
                </th>
                <th width="20%" height="28">
                    <font style="font-weight: bold">电话</font>
                </th>
                <th width="30%" height="28">
                    <font style="font-weight: bold">邮箱</font>
                </th>
                <c:if test="${pro.employee.id == userdetails.id || userdetails.role.code == 'ROLE_DIRECTOR'}">
                <th width="10%" height="28">
                    <font style="font-weight: bold">操作</font>
                </th>
                </c:if>
            </tr>
            <s:iterator value="#request.listProemps" var="emp" status="i">
            <s:if test="#request.i.index%2==0">
                <tr>
            </s:if>
            <s:else>
                <tr class="alt">
            </s:else>
                <td align="left">${emp.name }&nbsp;</td>
                <td align="left">${emp.department.name }&nbsp;</td>
                <td align="left">${emp.phone }&nbsp;</td>
                <td align="left">${emp.email }&nbsp;</td>
                <c:if test="${pro.employee.id == userdetails.id || userdetails.role.code == 'ROLE_DIRECTOR'}">
                <td align="left"><a href="${pageContext.request.contextPath }/deleteProEmp?pe.empid=${emp.id }&pe.projectid=${pro.id }&pro.id=${pro.id}#project_delEmp">删除</a></td>
                </c:if>
                
            <s:hidden name="pro.id"></s:hidden>
                
            </tr>
            </s:iterator>
            </table>
        </div>
        <div style="margin-top: 15px;"></div>
    <c:if test="${pro.employee.id == userdetails.id || userdetails.role.code == 'ROLE_DIRECTOR'}">
        <hr />
        <div style="margin-top: 15px;"></div>
        <h2>添加人员：</h2>
        <a name="project_addEmp" id="project_addEmp"></a>
        <div style="margin-top: 15px;"></div>
        <div>
        <form action="${pageContext.request.contextPath }/listProEmps#project_addEmp" id="proEmpsForm">
            部门：
            <select class="W_input input_left" name="deptid" id="dept">
                <option value="">请选择</option>
            </select>
            <s:hidden name="pro.id"></s:hidden>
            <span id="workloadTip" style="font-size: 12px; color: #CD0000;"></span>
            <span id="deptTip" style="font-size: 12px; color: #CD0000;"></span>
            <%--
            <input type="submit" value="查看" style="margin-left: 80px;" class="btn_weight"/>
             --%>
        </form>
        </div>
        <div style="margin-top: 15px;"></div>
        <form action="${pageContext.request.contextPath }/batchDisEmp#project_addEmp">
        <div>
            <s:hidden name="pro.id"></s:hidden>
        <table id="center" style="width: 580px;">
            <tr>
                <th width="20%" height="28">
                    <font style="font-weight: bold"><input type="checkbox" onclick="checkedAll('center',this)"/></font>
                </th>
                <th width="20%" height="28">
                    <font style="font-weight: bold">姓名</font>
                </th>
                <th width="20%" height="28">
                    <font style="font-weight: bold">电话</font>
                </th>
                <th width="30%" height="28">
                    <font style="font-weight: bold">邮箱</font>
                </th>
                <th width="10%" height="28">
                    <font style="font-weight: bold">操作</font>
                </th>
            </tr>
            <s:iterator value="#request.proEmps" var="emp" status="i">
            <s:if test="#request.i.index%2==0">
                <tr>
            </s:if>
            <s:else>
                <tr class="alt">
            </s:else>
                <td align="left"><input type="checkbox" name="ids" value="${emp[0] }"/></td>
                <td align="left">${emp[1] }&nbsp;</td>
                <td align="left">${emp[2] }&nbsp;</td>
                <td align="left">${emp[3] }&nbsp;</td>
                <td align="left"><a href="${pageContext.request.contextPath }/disEmp?pe.empid=${emp[0] }&pe.projectid=${pro.id }&pro.id=${pro.id}#project_addEmp">添加</a></td>
            </tr>
            </s:iterator>
            </table>
        </div>
        <s:if test="pro.employee.id==#session.userdetails.id">
        <div style="width: 340px;"><button style="margin-left:490px; margin-top:20px; margin-bottom: 10px;" class="btn_weight">添加</button></div>
        </s:if>
        </form>
    </c:if>
    </body>
</html>