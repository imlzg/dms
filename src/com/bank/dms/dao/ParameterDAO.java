package com.bank.dms.dao;

import java.util.List;

import com.bank.dms.entity.Parameter;

public interface ParameterDAO {
	public void saveOrUpdate(Parameter p);
	public List<Parameter> find();
	public List<Parameter> find(String s);
    
    public void delete(Integer id);
    
    public List<Parameter> childMenus(Integer id);
}
