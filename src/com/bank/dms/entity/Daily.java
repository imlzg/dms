package com.bank.dms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Daily implements Serializable {
    private Integer id;
    private Employee employee;
    private Employee reviewer;
    private String content;
    private Timestamp subdate;
    private Timestamp reviewdate;
    private String status;
    private String reviewcontent;
    private Integer workload;
    private String dailydate;
    public Daily() {
    }
    public Daily(Integer id, Employee employee, Employee reviewer,
            String content, Timestamp subdate, Timestamp reviewdate,
            String status, String reviewcontent, Integer workload,
            String dailydate) {
        this.id = id;
        this.employee = employee;
        this.reviewer = reviewer;
        this.content = content;
        this.subdate = subdate;
        this.reviewdate = reviewdate;
        this.status = status;
        this.reviewcontent = reviewcontent;
        this.workload = workload;
        this.dailydate = dailydate;
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
    public Employee getReviewer() {
        return reviewer;
    }
    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getSubdate() {
        return subdate;
    }
    public void setSubdate(Timestamp subdate) {
        this.subdate = subdate;
    }
    public Timestamp getReviewdate() {
        return reviewdate;
    }
    public void setReviewdate(Timestamp reviewdate) {
        this.reviewdate = reviewdate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getReviewcontent() {
        return reviewcontent;
    }
    public void setReviewcontent(String reviewcontent) {
        this.reviewcontent = reviewcontent;
    }
    public Integer getWorkload() {
        return workload;
    }
    public void setWorkload(Integer workload) {
        this.workload = workload;
    }
    public String getDailydate() {
        return dailydate;
    }
    public void setDailydate(String dailydate) {
        this.dailydate = dailydate;
    }

}
