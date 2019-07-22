package com.bank.dms.entity;

public class SearchDaily {
    private Integer deptid;
    private Integer empid;
    private String dailydate;
    public SearchDaily() {
    }
    public Integer getDeptid() {
        return deptid;
    }
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }
    public Integer getEmpid() {
        return empid;
    }
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }
    public String getDailydate() {
        return dailydate;
    }
    public void setDailydate(String dailydate) {
        this.dailydate = dailydate;
    }
}
