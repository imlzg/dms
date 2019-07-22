package com.bank.dms.entity;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private Integer id;
	private String content;
	private Date dateTime;
	private String type;

	private Employee employee;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
