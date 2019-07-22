package com.bank.dms.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Department implements Serializable {
	private Integer id;
	private String name;
	private String descr;

	private Employee manager;
	private Set<Employee> staffs; //yuangong

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Set<Employee> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Employee> staffs) {
		this.staffs = staffs;
	}
}
