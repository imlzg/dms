package com.bank.dms.biz;

import java.util.List;

import com.bank.dms.entity.Parameter;

public interface ParameterBIZ {
	public void saveOrUpdate(Parameter p);
	public List<Parameter> find();
	public List<Parameter> find(String sql);
	public List<Parameter> findAll();
	public List<Parameter> menus();
	public List<Parameter> footers();
	
	public void delete(Integer id);
	
	public List<Parameter> childMenus(Integer id);
}
