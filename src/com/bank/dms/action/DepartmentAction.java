package com.bank.dms.action;

import java.util.List;

import com.bank.dms.biz.DepartmentBIZ;
import com.bank.dms.entity.Department;


public class DepartmentAction extends BaseAction {
	private DepartmentBIZ departmentBIZ;
	private Department department;
	private List<Department> list;

	public DepartmentBIZ getDepartmentBIZ() {
		return departmentBIZ;
	}
	public void setDepartmentBIZ(DepartmentBIZ departmentBIZ) {
		this.departmentBIZ = departmentBIZ;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Department> getList() {
		return list;
	}
	public void setList(List<Department> list) {
		this.list = list;
	}

	
	public String add() {
		return SUCCESS;
	}
	public String edit() {
		department = this.departmentBIZ.get(department.getId());
		return SUCCESS;
	}
	public String save() {
		this.departmentBIZ.saveOrUpdate(department);
		return SUCCESS;
	}
	public String list() {
		list = this.departmentBIZ.find();
		return SUCCESS;
	}
	
	public String delete(){
	    try {
            departmentBIZ.delete(department.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return SUCCESS;
	}
	
	public String doEdit(){
	    try {
            departmentBIZ.saveOrUpdate(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return SUCCESS;
	}
}
