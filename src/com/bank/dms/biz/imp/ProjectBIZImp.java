package com.bank.dms.biz.imp;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.biz.ProjectBIZ;
import com.bank.dms.dao.ProjectDAO;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Project;
import com.bank.dms.entity.ProjectEmp;

public class ProjectBIZImp implements ProjectBIZ {
    
    private ProjectDAO projectDAO;
    
    public List<Object[]> projects(){
        return projectDAO.projects();
    }

    public Project get(Serializable id){
        return projectDAO.get(id);
    }

    public Serializable save(Project role){
        return projectDAO.save(role);
    }

    public void update(Project role){
        projectDAO.update(role);
    }

    public void delete(final Serializable id){
        projectDAO.delete(id);
    }

    public List<Project> find(final Project role){
        return projectDAO.find(role);
    }

    public int count(final Project role){
        return projectDAO.count(role);
    }

    public List<Object[]> proEmps(Integer deptid, Integer proid) {
        return projectDAO.proEmps(deptid, proid);
    }

    public List<Employee> listProemps(Integer proid) {
        return projectDAO.listProemps(proid);
    }

    public void deleteProEmp(ProjectEmp pe) {
        projectDAO.deleteProEmp(pe);
    }

    public Serializable save(ProjectEmp pe) {
        return projectDAO.save(pe);
    }

    public List<Project> myProject(Integer empid) {
        return projectDAO.myProject(empid);
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

}
