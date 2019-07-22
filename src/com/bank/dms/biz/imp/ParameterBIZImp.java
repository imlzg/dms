package com.bank.dms.biz.imp;

import java.util.List;

import com.bank.dms.biz.ParameterBIZ;
import com.bank.dms.dao.ParameterDAO;
import com.bank.dms.entity.Parameter;

public class ParameterBIZImp implements ParameterBIZ {
	private ParameterDAO parameterDAO;

	public ParameterDAO getParameterDAO() {
		return parameterDAO;
	}
	public void setParameterDAO(ParameterDAO parameterDAO) {
		this.parameterDAO = parameterDAO;
	}

	public void saveOrUpdate(Parameter p) {
		this.parameterDAO.saveOrUpdate(p);
	}
	public List<Parameter> find() {
		return this.parameterDAO.find();
	}
	public List<Parameter> find(String sql) {
		return this.parameterDAO.find(sql);
	}
	public List<Parameter> findAll() {
		return this.parameterDAO.find("from Parameter order by code, orderBy");
	}

	public List<Parameter> menus() {
//		return this.parameterDAO.find("from Parameter where code='menu'");
	    return this.parameterDAO.find("FROM Parameter para_ WHERE code='menu' ORDER BY para_.orderBy");
	}
	public List<Parameter> footers() {
		return this.parameterDAO.find("from Parameter where code='footer'");
	}
    public void delete(Integer id) {
        parameterDAO.delete(id);
    }
    public List<Parameter> childMenus(Integer id) {
        return parameterDAO.childMenus(id);
    }
}
