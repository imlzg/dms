package com.bank.dms.biz;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Page;

public interface EmployeeBIZ {

    public abstract Employee get(Serializable id);

    public abstract Serializable save(Employee emp);

    public abstract void update(Employee emp);

    public abstract void saveOrUpdate(Employee emp);

    public abstract void delete(Serializable id);

    public abstract List<Employee> find(Employee emp, Page page);

    public abstract int count(Employee emp);
    
    public abstract List<Object[]> emps();
    
    public abstract Employee login(Employee emp);

}