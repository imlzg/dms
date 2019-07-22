package com.bank.dms.biz;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Department;

public interface DepartmentBIZ {
	public Department get(Serializable id);
	public void save(Department d);
	public void saveOrUpdate(Department d);
	public List<Department> find();
	public List<Object[]> depts();
	public void delete(Serializable id);
}