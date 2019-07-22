package com.bank.dms.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dms.dao.LogDAO;
import com.bank.dms.entity.Log;

public class LogDAOImp extends HibernateDaoSupport implements LogDAO {

	public Log get(Serializable id) {
		return this.getHibernateTemplate().load(Log.class, id);
	}
	public void save(Log log) {
		this.getHibernateTemplate().merge(log);
	}
	public void delete(Log log) {
		this.getHibernateTemplate().delete(log);
	}
	public List<Log> find() {
		return this.getHibernateTemplate().loadAll(Log.class);
	}
	@SuppressWarnings("unchecked")
	public List<Log> find(String sql) {
		return this.getHibernateTemplate().find(sql);
	}
}
