package com.bank.dms.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dms.dao.ParameterDAO;
import com.bank.dms.entity.Parameter;

public class ParameterDAOImp extends HibernateDaoSupport implements ParameterDAO {
	public void saveOrUpdate(Parameter p) {
		this.getHibernateTemplate().merge(p);
	}
	public List<Parameter> find() {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Parameter>>() {

            public List<Parameter> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("SELECT para_.id, para_.name, para_.code, para_.value, para_.descr, para_.expression, para_.orderby FROM dms_parameter para_ WHERE para_.code like 'menu%' ORDER BY para_.code, para_.orderby");
                query.addEntity(Parameter.class);
                return query.list();
            }
        });
//		return this.getHibernateTemplate().loadAll(Parameter.class);
	}
	public List<Parameter> find(String s) {
		return this.getHibernateTemplate().find(s);
	}
    public void delete(final Integer id) {
        getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("DELETE dms_parameter WHERE id=:id");
                query.setParameter("id", id);
                query.executeUpdate();
                return null;
            }
        });
    }
    public List<Parameter> childMenus(final Integer id) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Parameter>>() {

            public List<Parameter> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("SELECT para_.id, para_.name, para_.code, para_.value, para_.descr, para_.expression, para_.orderby FROM dms_parameter para_ WHERE para_.code='menu_'||:id ORDER BY para_.code, para_.orderby");
                query.setParameter("id", id);
                query.addEntity(Parameter.class);
                return query.list();
            }
        });
    }
}
