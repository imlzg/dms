package com.bank.dms.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.dms.biz.DailyBIZ;
import com.bank.dms.biz.EmployeeBIZ;
import com.bank.dms.biz.ProjectBIZ;
import com.bank.dms.entity.Daily;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Project;

public class LoginAction extends BaseAction {
    
    private Employee emp;
    private EmployeeBIZ employeeBIZ;
    private DailyBIZ dailyBIZ;
    private ProjectBIZ projectBIZ;

    public String login(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Employee e = (Employee) request.getSession().getAttribute("userdetails");
        if(e != null){
            emp = e;
        }
        Employee employee = employeeBIZ.login(emp);
        if(employee != null){
            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            if(employee.getRole() != null){
                auths.add(new GrantedAuthorityImpl(employee.getRole().getCode()));
            }
            UserDetails userDetails = new User(employee.getName(), employee.getPassword(), true, true, true, true, auths);
            System.out.println(userDetails.getAuthorities());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            Daily yesterdayDaily = dailyBIZ.yesterdayDaily(employee.getId());
            Daily todayDaily = dailyBIZ.todayDaily(employee.getId());
            List<Project> myProject = projectBIZ.myProject(employee.getId());
            request.setAttribute("yesterdayDaily", yesterdayDaily);
            request.setAttribute("todayDaily", todayDaily);
            request.setAttribute("myProject", myProject);
            request.getSession().setAttribute("userdetails", employee);
            request.getSession().setAttribute("menus", getMenus());
            return SUCCESS;
        }
        return INPUT;
    }
    
    public String logout(){
        try {
            ServletActionContext.getRequest().getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public void setEmployeeBIZ(EmployeeBIZ employeeBIZ) {
        this.employeeBIZ = employeeBIZ;
    }

    public void setDailyBIZ(DailyBIZ dailyBIZ) {
        this.dailyBIZ = dailyBIZ;
    }

    public void setProjectBIZ(ProjectBIZ projectBIZ) {
        this.projectBIZ = projectBIZ;
    }

}
