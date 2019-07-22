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

import com.bank.dms.dao.RoleDAO;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.Role;

public class RoleDAOImp extends HibernateDaoSupport implements RoleDAO {

    public List<Object[]> roles() {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Object[]>>() {

            @SuppressWarnings("unchecked")
            public List<Object[]> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                SQLQuery query = session.createSQLQuery("SELECT role_.id, role_.name FROM dms_role role_");
                return query.list();
            }
        });
    }
    
    public Role get(Serializable id) {
        return this.getHibernateTemplate().get(Role.class, id);
    }
    
    public Serializable save(Role role) {
        return this.getHibernateTemplate().save(role);
    }
    
    public void update(Role role) {
        this.getHibernateTemplate().update(role);
    }
    
    public void delete(final Serializable id) {
        this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery sqlquery = session.createSQLQuery("DELETE dms_role role_ WHERE role_.id=?");
                sqlquery.setParameter(0, id);
                sqlquery.executeUpdate();
                return null;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    public List<Role> find(final Role role, final Page page) {
        List<Role> list = null;
        try {
            list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Role>>() {

                public List<Role> doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("FROM Role role_ where 1=1");
                    
                    if(role != null){
                        if(role.getName() != null && !role.getName().equals("")){
                            hql.append(" AND role_.name like :roleName");
                        }
                    }
                    hql.append(" ORDER BY role_.id DESC");
                    
                    Query query = session.createQuery(hql.toString());
//                    query.setFirstResult(page.getSize()*(page.getPage()-1));
//                    query.setMaxResults(page.getSize());
                    
                    if(role != null){
                        if(role.getName() != null && !role.getName().equals("")){
                            query.setParameter("roleName", "%" + role.getName() + "%");
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
    
    public int count(final Role role){
        int count = 0;
        try {
            count = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {

                public Integer doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer sql = new StringBuffer("SELECT COUNT(1) FROM dms_role role_ WHERE 1=1");
                    
                    if(role != null){
                        if(role.getName() != null && !role.getName().equals("")){
                            sql.append(" AND role_.name like :roleName");
                        }
                    }
                    
                    SQLQuery query = session.createSQLQuery(sql.toString());
                    
                    if(role != null){
                        if(role.getName() != null && !role.getName().equals("")){
                            query.setParameter("roleName", "%" + role.getName() + "%");
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

}
