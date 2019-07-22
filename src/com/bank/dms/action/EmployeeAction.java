package com.bank.dms.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.bank.dms.biz.EmployeeBIZ;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.Role;
import com.bank.dms.message.ListHint;

public class EmployeeAction extends BaseAction {
    
    private Page pageNum;
    
    private Employee emp;
    
    private Employee searchEmp;
    
    private EmployeeBIZ employeeBIZ;

    public String save(){
        if(emp != null && emp.getRole() == null){
            emp.setRole(new Role());
            emp.getRole().setId(0);
            emp.setPassword("123456");
        }
        employeeBIZ.save(emp);
        return SUCCESS;
    }

    public String edit(){
        emp = employeeBIZ.get(emp.getId());
        return SUCCESS;
    }
    
    public String doEdit(){
        if(emp != null && emp.getRole() == null){
            emp.setRole(new Role());
            emp.getRole().setId(0);
        }
        employeeBIZ.update(emp);
        return SUCCESS;
    }

    public String delete(){
        employeeBIZ.delete(emp.getId());
        return SUCCESS;
    }

    public String list(){
        int totalCount=employeeBIZ.count(searchEmp);
        int size=pageNum.getSize();
        pageNum.setTotalCount(totalCount);
        if (totalCount!=0) {
            pageNum.setTotalPage(totalCount%size==0?totalCount/size:(totalCount/size)+1);
        }
        int page=pageNum.getPage();
        if (page<=0) {
            pageNum.setPage(1);
        }
        if (page>=pageNum.getTotalPage()) {
            pageNum.setPage(pageNum.getTotalPage());
        }
        if(totalCount == 0){
            ServletActionContext.getRequest().setAttribute("mssage", ListHint.NOTFIND);
        }
        List<Employee> list = employeeBIZ.find(searchEmp, pageNum);
        ServletActionContext.getRequest().setAttribute("list", list);
        return SUCCESS;
    }

    public Page getPageNum() {
        return pageNum;
    }

    public void setPageNum(Page pageNum) {
        this.pageNum = pageNum;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Employee getSearchEmp() {
        return searchEmp;
    }

    public void setSearchEmp(Employee searchEmp) {
        this.searchEmp = searchEmp;
    }

    public void setEmployeeBIZ(EmployeeBIZ employeeBIZ) {
        this.employeeBIZ = employeeBIZ;
    }

}
