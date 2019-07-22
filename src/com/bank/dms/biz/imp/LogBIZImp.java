package com.bank.dms.biz.imp;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.biz.LogBIZ;
import com.bank.dms.dao.LogDAO;
import com.bank.dms.entity.Log;

public class LogBIZImp implements LogBIZ {
	private LogDAO logDAO;

	public LogDAO getLogDAO() {
		return logDAO;
	}
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public void delete(Log log) {
		logDAO.delete(log);
	}

	public List<Log> find() {
		return this.logDAO.find();
	}

	public List<Log> find(String sql) {
		return this.logDAO.find(sql);
	}

	public Log get(Serializable id) {
		return this.logDAO.get(id);
	}

	public void save(Log log) {
		logDAO.save(log);
	}

}
