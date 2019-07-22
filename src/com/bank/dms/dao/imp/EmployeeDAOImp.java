package com.bank.dms.dao.imp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dms.dao.EmployeeDAO;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Page;


public class EmployeeDAOImp extends HibernateDaoSupport implements EmployeeDAO {
	public Employee get(Serializable id) {
		return this.getHibernateTemplate().get(Employee.class, id);
	}
	public Serializable save(Employee emp) {
		return this.getHibernateTemplate().save(emp);
	}
	public void update(Employee emp) {
		this.getHibernateTemplate().update(emp);
	}
	public void saveOrUpdate(Employee emp) {
		this.getHibernateTemplate().saveOrUpdate(emp);
	}
	public void delete(final Serializable id) {
	    this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery sqlquery = session.createSQLQuery("DELETE dms_employee emp_ WHERE emp_.id=?");
                sqlquery.setParameter(0, id);
                sqlquery.executeUpdate();
                return null;
            }
        });
	}
	@SuppressWarnings("unchecked")
    public List<Employee> find(final Employee emp, final Page page) {
	    List<Employee> list = null;
	    try {
            list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Employee>>() {

                public List<Employee> doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("FROM Employee emp_ join fetch emp_.department dept_ join fetch emp_.role role_ where 1=1");
                    
                    if(emp != null){
                        if(emp.getName() != null && !emp.getName().equals("")){
                            hql.append(" AND emp_.name like :empName");
                        }
                        if(emp.getDepartment() != null && emp.getDepartment().getId() != null){
                            hql.append(" AND dept_.id=:deptId");
                        }
                        if(emp.getRole() != null && emp.getRole().getId() != null){
                            hql.append(" AND role_.id=:roleId");
                        }
                    }
                    hql.append(" ORDER BY emp_.id DESC");
                    
                    Query query = session.createQuery(hql.toString());
//                    query.setFirstResult(page.getSize()*(page.getPage()-1));
//                    query.setMaxResults(page.getSize());
                    
                    if(emp != null){
                        if(emp.getName() != null && !emp.getName().equals("")){
                            query.setParameter("empName", "%" + emp.getName() + "%");
                        }
                        if(emp.getDepartment() != null && emp.getDepartment().getId() != null){
                            query.setParameter("deptId", emp.getDepartment().getId());
                        }
                        if(emp.getRole() != null && emp.getRole().getId() != null){
                            query.setParameter("roleId", emp.getRole().getId());
                        }
                    }
                    return query.list();
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return list;
	}
	public int count(final Employee emp){
	    int count = 0;
	    try {
            count = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {

                public Integer doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer sql = new StringBuffer("SELECT COUNT(1) FROM dms_employee employee0_, DMS_DEPARTMENT department1_, DMS_ROLE role2_ where employee0_.DEPARTMENT=department1_.ID and employee0_.ROLE=role2_.ID and 1=1");
                    
                    if(emp != null){
                        if(emp.getName() != null && !emp.getName().equals("")){
                            sql.append(" AND employee0_.name like :empName");
                        }
                        if(emp.getDepartment() != null && emp.getDepartment().getId() != null){
                            sql.append(" AND department1_.id=:deptId");
                        }
                        if(emp.getRole() != null && emp.getRole().getId() != null){
                            sql.append(" AND role2_.id=:roleId");
                        }
                    }
                    
                    SQLQuery query = session.createSQLQuery(sql.toString());
                    
                    if(emp != null){
                        if(emp.getName() != null && !emp.getName().equals("")){
                            query.setParameter("empName", "%" + emp.getName() + "%");
                        }
                        if(emp.getDepartment() != null && emp.getDepartment().getId() != null){
                            query.setParameter("deptId", emp.getDepartment().getId());
                        }
                        if(emp.getRole() != null && emp.getRole().getId() != null){
                            query.setParameter("roleId", emp.getRole().getId());
                        }
                    }
                    return ((BigDecimal) query.uniqueResult()).intValue();
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return count;
	}
    public List<Object[]> emps() {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Object[]>>() {

            @SuppressWarnings("unchecked")
            public List<Object[]> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                SQLQuery query = session.createSQLQuery("SELECT emp_.id, emp_.name FROM dms_employee emp_");
                return query.list();
            }
        });
    }
    public Employee login(final Employee emp) {
        Employee employee = null;
        try {
            employee = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Employee>() {

                public Employee doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("FROM Employee emp_ join fetch emp_.role role_ where 1=1");
                    
                    if(emp != null){
                        if(emp.getName() != null && !emp.getName().equals("")){
                            hql.append(" AND emp_.name=:empName");
                        }
                        if(emp.getPassword() != null && !emp.getPassword().equals("")){
                            hql.append(" AND emp_.password=:password");
                        }
                    }
                    hql.append(" ORDER BY emp_.id DESC");
                    
                    Query query = session.createQuery(hql.toString());
                    
                    if(emp != null){
                        if(emp.getName() != null && !emp.getName().equals("")){
                            query.setParameter("empName", emp.getName());
                        }
                        if(emp.getPassword() != null && !emp.getPassword().equals("")){
                            query.setParameter("password", emp.getPassword());
                        }
                        List<Employee> list =query.list();
                        if(list != null && list.size() > 0){
                            return list.get(0);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return employee;
    }
}
