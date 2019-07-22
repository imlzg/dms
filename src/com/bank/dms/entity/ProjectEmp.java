package com.bank.dms.entity;

public class ProjectEmp implements java.io.Serializable {
    
    private Integer projectid;
    private Integer empid;
    
    public ProjectEmp() {
    }

    public ProjectEmp(Integer projectid, Integer empid) {
        this.projectid = projectid;
        this.empid = empid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

}
