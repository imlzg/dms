package com.bank.dms.biz.imp;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.biz.EmployeeBIZ;
import com.bank.dms.dao.EmployeeDAO;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Page;

public class EmployeeBIZImp implements EmployeeBIZ {
	private EmployeeDAO employeeDAO;
	
    public Employee get(Serializable id){
        return employeeDAO.get(id);
    }

    public Serializable save(Employee emp){
        return employeeDAO.save(emp);
    }

    public void update(Employee emp){
        employeeDAO.update(emp);
    }

    public void saveOrUpdate(Employee emp){
        employeeDAO.saveOrUpdate(emp);
    }

    public void delete(Serializable id){
        employeeDAO.delete(id);
    }

    public List<Employee> find(Employee emp, Page page){
        return employeeDAO.find(emp, page);
    }

    public int count(Employee emp){
        return employeeDAO.count(emp);
    }

    public List<Object[]> emps() {
        return employeeDAO.emps();
    }

    public Employee login(Employee emp) {
        return employeeDAO.login(emp);
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

}
