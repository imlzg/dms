package com.bank.dms.biz.imp;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.biz.DepartmentBIZ;
import com.bank.dms.dao.DepartmentDAO;
import com.bank.dms.entity.Department;

public class DepartmentBIZImp implements DepartmentBIZ {
	private DepartmentDAO departmentDAO;
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public Department get(Serializable id) {
		return this.departmentDAO.get(id);
	}
	public void save(Department d) {
		this.departmentDAO.save(d);
	}
	public void saveOrUpdate(Department d) {
		this.departmentDAO.saveOrUpdate(d);
	}
	public List<Department> find() {
		return this.departmentDAO.find();
	}
	public List<Object[]> depts(){
	    return this.departmentDAO.depts();
	}
    public void delete(Serializable id) {
        Department department = departmentDAO.get(id);
        if(department != null){
            departmentDAO.delete(department);
        }
    }
}
