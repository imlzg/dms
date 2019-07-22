package com.bank.dms.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.http.client.ClientProtocolException;
import org.apache.struts2.ServletActionContext;

import com.bank.dms.biz.DailyBIZ;
import com.bank.dms.biz.DepartmentBIZ;
import com.bank.dms.biz.EmployeeBIZ;
import com.bank.dms.biz.RoleBIZ;
import com.bank.dms.entity.Daily;
import com.bank.dms.entity.Parameter;
import com.bank.dms.entity.SearchDaily;
import com.bank.dms.job.DidaTask;
import com.opensymphony.xwork2.ActionContext;

public class JSONAction extends BaseAction {
    
    private DepartmentBIZ departmentBIZ;
    
    private RoleBIZ roleBIZ;
    
    private EmployeeBIZ employeeBIZ;
    
    private DidaTask task;
    
    private DailyBIZ dailyBIZ;
    
    private SearchDaily searchDaily;
    
    public String allDept() throws Exception{
        try {
            List<Object[]> depts = departmentBIZ.depts();
            JSONArray jsonArray = JSONArray.fromObject(depts);
            HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonArray);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public String allRoel() throws Exception{
        try {
            List<Object[]> depts = roleBIZ.roles();
            JSONArray jsonArray = JSONArray.fromObject(depts);
            HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonArray);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public String allEmployee() throws Exception{
        try {
            List<Object[]> depts = employeeBIZ.emps();
            JSONArray jsonArray = JSONArray.fromObject(depts);
            HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonArray);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public String setJs(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String js = "<script type='text/javascript'>selectTag('" + request.getParameter("val") + "');</script>";
        request.getSession().setAttribute("js", js);
        return NONE;
    }
    
    public String childMenus(){
        try {
            String val = ServletActionContext.getRequest().getParameter("val");
            String name = ServletActionContext.getRequest().getParameter("name");
            if(val.matches("[0-9]*")){
                List<Parameter> childMenus = getParamBIZ().childMenus(Integer.parseInt(val.trim()));
                JSONArray jsonArray = JSONArray.fromObject(childMenus);
                HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonArray);
                String js = "<script type='text/javascript'>selectTag(" + val + ", '" + name + "');</script>";
                ServletActionContext.getRequest().getSession().setAttribute("js", js);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return NONE;
    }
    
    public String task(){
        String name = ServletActionContext.getRequest().getParameter("name");
        try {
            task.rtx("你好！你还没有提交今天的日报！", name);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }
    
    public String previewDaily(){
        if(searchDaily != null){
            Daily daily = dailyBIZ.previewDaily(searchDaily);
            List<Daily> list = new ArrayList<Daily>();
            list.add(daily);
            try {
                JsonConfig jsonConfig = new JsonConfig();  
                jsonConfig.setExcludes(new String[]{"employee", "reviewer"}); 
                JSONObject json = JSONObject.fromObject(daily, jsonConfig); 
                HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return NONE;
    }
    
    public void setDepartmentBIZ(DepartmentBIZ departmentBIZ) {
        this.departmentBIZ = departmentBIZ;
    }

    public void setRoleBIZ(RoleBIZ roleBIZ) {
        this.roleBIZ = roleBIZ;
    }

    public void setEmployeeBIZ(EmployeeBIZ employeeBIZ) {
        this.employeeBIZ = employeeBIZ;
    }

    public void setTask(DidaTask task) {
        this.task = task;
    }

    public SearchDaily getSearchDaily() {
        return searchDaily;
    }

    public void setSearchDaily(SearchDaily searchDaily) {
        this.searchDaily = searchDaily;
    }

    public void setDailyBIZ(DailyBIZ dailyBIZ) {
        this.dailyBIZ = dailyBIZ;
    }

}
