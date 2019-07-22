package com.bank.dms.dao.imp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dms.dao.ProjectDAO;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Project;
import com.bank.dms.entity.ProjectEmp;


public class ProjectDAOImp extends HibernateDaoSupport implements ProjectDAO {

    public List<Object[]> projects() {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Object[]>>() {

            @SuppressWarnings("unchecked")
            public List<Object[]> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Employee userdetails = (Employee) ServletActionContext.getRequest().getSession().getAttribute("userdetails");
                SQLQuery query = null;
                if(userdetails != null && userdetails.getRole() != null && userdetails.getRole().getCode().equals("ROLE_EMPLOYEE")){
                    query = session.createSQLQuery("SELECT DISTINCT project_.id, project_.name FROM dms_project  project_, dms_project_emp pe_, dms_employee emp_ WHERE project_.id= pe_.projectid AND emp_.id= pe_.employeeid AND (emp_.id=" + userdetails.getId() + " OR project_.manager=" + userdetails.getId() + ")");
                }else{
                    query = session.createSQLQuery("SELECT DISTINCT project_.id, project_.name FROM dms_project project_");
                }
                return query.list();
            }
        });
    }
    
    public Project get(final Serializable id) {
        Project pro = null;
        try {
            pro = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Project>() {

                public Project doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("FROM Project project_ left join fetch project_.employee emp_ where project_.id=:id");
//                    StringBuffer hql = new StringBuffer("FROM Employee emp_ join fetch emp_.department dept_ join fetch emp_.role role_ where 1=1");
                    Query query = session.createQuery(hql.toString());
                    query.setParameter("id", id);
                    return (Project) query.uniqueResult();
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pro;
    }
    
    public Serializable save(Project role) {
        return this.getHibernateTemplate().save(role);
    }
    
    public void update(Project role) {
        this.getHibernateTemplate().update(role);
    }
    
    public void delete(final Serializable id) {
        this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery sqlquery = session.createSQLQuery("DELETE dms_project project_ WHERE project_.id=?");
                sqlquery.setParameter(0, id);
                sqlquery.executeUpdate();
                return null;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    public List<Project> find(final Project role) {
        List<Project> list = null;
        try {
            list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Project>>() {

                public List<Project> doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("FROM Project role_ where 1=1");
                    
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
    
    public int count(final Project role){
        int count = 0;
        try {
            count = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {

                public Integer doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer sql = new StringBuffer("SELECT COUNT(1) FROM dms_project role_ WHERE 1=1");
                    
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

    public List<Object[]> proEmps(final Integer deptid, final Integer proid) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Object[]>>() {

            @SuppressWarnings("unchecked")
            public List<Object[]> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer("SELECT emp_.id, emp_.name, emp_.phone, emp_.email FROM dms_employee emp_ WHERE emp_.id not in (SELECT pe_.employeeid FROM dms_project_emp pe_ WHERE pe_.projectid=:proid)");
                if(deptid != null){
                    sql.append(" AND emp_.department=:deptid");
                }
                SQLQuery query = session.createSQLQuery(sql.toString());
                query.setParameter("proid", proid);
                if(deptid != null){
                    query.setParameter("deptid", deptid);
                }
                return query.list();
            }
        });
    }

    public List<Employee> listProemps(final Integer proid) {
        List<Employee> list = null;
        try {
            list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Employee>>() {

                public List<Employee> doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    
                    StringBuffer hql = new StringBuffer("SELECT emp_.* FROM dms_employee emp_, dms_project_emp proemp_, dms_project pro_ WHERE emp_.id= proemp_.employeeid AND pro_.id= proemp_.projectid");
                    
                    if(proid != null){
                        hql.append(" AND pro_.id=:proid");
                    }
                    
                    hql.append(" ORDER BY emp_.id DESC");
                    
                    SQLQuery query = session.createSQLQuery(hql.toString());
                    query.addEntity(Employee.class);
                        if(proid != null){
                            query.setParameter("proid", proid);
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

    public void deleteProEmp(final ProjectEmp pe) {
        this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery sqlquery = session.createSQLQuery("DELETE dms_project_emp proemp_ WHERE proemp_.projectid=? and proemp_.employeeid=?");
                sqlquery.setParameter(0, pe.getProjectid());
                sqlquery.setParameter(1, pe.getEmpid());
                sqlquery.executeUpdate();
                return null;
            }
        });
    }

    public Serializable save(final ProjectEmp pe) {
        this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery sqlquery = session.createSQLQuery("INSERT INTO dms_project_emp(projectid, employeeid) VALUES(?,?)");
                if(pe != null){
                    sqlquery.setParameter(0, pe.getProjectid());
                    sqlquery.setParameter(1, pe.getEmpid());
                }
                sqlquery.executeUpdate();
                return null;
            }
        });
        return null;
    }

    public List<Project> myProject(final Integer empid) {
        List<Project> list = null;
        try {
            list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Project>>() {

                public List<Project> doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    /*StringBuffer hql = new StringBuffer("FROM Project project_ join FETCH project_.employee emp_ WHERE emp_.id=:empid");
                    
                    hql.append(" ORDER BY project_.id DESC");
                    
                    Query query = session.createQuery(hql.toString());
                    
                    query.setParameter("empid", empid);*/
                    SQLQuery query = session.createSQLQuery("SELECT DISTINCT project_.* FROM dms_project_emp pe_, dms_employee emp_, dms_project project_ "
                            + "WHERE pe_.employeeid=emp_.id AND pe_.projectid= project_.id AND emp_.id=?"
                            +"ORDER BY project_.id desc");
                    query.addEntity(Project.class);
                    query.setParameter(0, empid);
                    
                    return query.list();
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

}
