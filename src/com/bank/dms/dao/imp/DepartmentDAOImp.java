package com.bank.dms.dao.imp;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dms.dao.DepartmentDAO;
import com.bank.dms.entity.Department;

public class DepartmentDAOImp extends HibernateDaoSupport implements DepartmentDAO {
	public Department get(Serializable id) {
		return this.getHibernateTemplate().get(Department.class, id);
	}
	public Serializable save(Department d) {
		return this.getHibernateTemplate().save(d);
	}
	public void update(Department d) {
		this.getHibernateTemplate().update(d);
	}
	public void saveOrUpdate(Department d) {
		this.getHibernateTemplate().merge(d);
	}
	public void delete(Department d) {
		this.getHibernateTemplate().delete(d);
	}
	public List<Department> find() {
		return this.getHibernateTemplate().loadAll(Department.class);
	}
	public List<Department> find(String sql) {
		return this.getHibernateTemplate().find(sql);
	}
	public List<Object[]> depts(){
	    return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Object[]>>() {

            public List<Object[]> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                SQLQuery query = session.createSQLQuery("SELECT dept_.id, dept_.name FROM dms_department dept_");
                return query.list();
            }
        });
	}
}
