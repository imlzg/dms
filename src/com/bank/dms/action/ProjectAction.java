package com.bank.dms.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.bank.dms.biz.ProjectBIZ;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Project;
import com.bank.dms.entity.ProjectEmp;

public class ProjectAction extends BaseAction {
    
    private Project pro;
    
    private ProjectEmp pe;
    
    private String[] ids;
    
    private ProjectBIZ projectBIZ;

    public String save(){
        try {
            projectBIZ.save(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String edit(){
        try {
            pro = projectBIZ.get(pro.getId());
            List<Employee> listProemps = projectBIZ.listProemps(pro.getId());
            ServletActionContext.getRequest().setAttribute("listProemps", listProemps);
            
            List<Object[]> proEmps = projectBIZ.proEmps(null, pro.getId());
            ServletActionContext.getRequest().setAttribute("proEmps", proEmps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String doEdit(){
        projectBIZ.update(pro);
        return SUCCESS;
    }
    
    public String proStatus(){
        pro = projectBIZ.get(pro.getId());
        String status = ServletActionContext.getRequest().getParameter("status");
        pro.setStatus(status);
        projectBIZ.update(pro);
        return SUCCESS;
    }

    public String delete(){
        projectBIZ.delete(pro.getId());
        return SUCCESS;
    }

    public String list(){
        List<Object[]> list = projectBIZ.projects();
        ServletActionContext.getRequest().setAttribute("list", list);
        return SUCCESS;
    }
    
    public String proEmps(){
        try {
            pro = projectBIZ.get(pro.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Employee> listProemps = projectBIZ.listProemps(pro.getId());
        ServletActionContext.getRequest().setAttribute("listProemps", listProemps);
        String deptid = ServletActionContext.getRequest().getParameter(
                "deptid");
        List<Object[]> proEmps = projectBIZ.proEmps(deptid!=null&&!"".equals(deptid) ? Integer.parseInt(deptid):null, pro.getId());
        ServletActionContext.getRequest().setAttribute("proEmps", proEmps);
        ServletActionContext.getRequest().setAttribute("deptid", deptid);
        return SUCCESS;
    }
    
    public String deleteProEmp(){
        try {
            String empid = ServletActionContext.getRequest().getParameter("pe.empid");
            if(empid != null && !"".equals(empid) && pe != null){
                pe.setEmpid(Integer.parseInt(empid));
            }
            projectBIZ.deleteProEmp(pe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String disEmp(){
        try {
            String empid = ServletActionContext.getRequest().getParameter("pe.empid");
            if(empid != null && !"".equals(empid) && pe != null){
                pe.setEmpid(Integer.parseInt(empid));
            }
            projectBIZ.save(pe);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String batchDisEmp(){
        try {
            if(ids != null && !"".equals(ids) && pro != null && pro.getId() != null){
                for (String empid : ids) {
                    projectBIZ.save(new ProjectEmp(pro.getId(), Integer.parseInt(empid)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    
    public Project getPro() {
        return pro;
    }

    public void setPro(Project pro) {
        this.pro = pro;
    }

    public void setProjectBIZ(ProjectBIZ projectBIZ) {
        this.projectBIZ = projectBIZ;
    }

    public void setPe(ProjectEmp pe) {
        this.pe = pe;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
