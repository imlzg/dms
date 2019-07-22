package com.bank.dms.dao;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Project;
import com.bank.dms.entity.ProjectEmp;

public interface ProjectDAO {

    public abstract List<Object[]> projects();

    public abstract Project get(Serializable id);

    public abstract Serializable save(Project role);

    public abstract void update(Project role);

    public abstract void delete(final Serializable id);

    public abstract List<Project> find(final Project role);

    public abstract int count(final Project role);
    
    public abstract List<Object[]> proEmps(Integer deptid, Integer proid);
    
    public abstract List<Employee> listProemps(Integer proid);
    
    public abstract void deleteProEmp(ProjectEmp pe);
    
    public abstract Serializable save(ProjectEmp pe);

    public abstract List<Project> myProject(Integer empid);
}