package com.bank.dms.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class Project implements java.io.Serializable {

    private Integer id;
    private Employee employee;
    private String name;
    private Timestamp startdate;
    private Timestamp enddate;
    private String status;
    private String descr;
    private Set projectEmps = new HashSet(0);
    public Project() {
    }
    public Project(Integer id, Employee employee, String name,
            Timestamp startdate, Timestamp enddate, String status,
            String descr, Set projectEmps) {
        this.id = id;
        this.employee = employee;
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.status = status;
        this.descr = descr;
        this.projectEmps = projectEmps;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Timestamp getStartdate() {
        return startdate;
    }
    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }
    public Timestamp getEnddate() {
        return enddate;
    }
    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public Set getProjectEmps() {
        return projectEmps;
    }
    public void setProjectEmps(Set projectEmps) {
        this.projectEmps = projectEmps;
    }
}