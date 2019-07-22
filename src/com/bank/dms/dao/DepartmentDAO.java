package com.bank.dms.dao;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Department;

public interface DepartmentDAO {

	public Department get(Serializable id);
	public Serializable save(Department d);
	public void update(Department d);
	public void saveOrUpdate(Department d);
	public void delete(Department d);
	public List<Department> find();
	public List<Department> find(String sql);
	public List<Object[]> depts();
}
