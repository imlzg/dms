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

import com.bank.dms.dao.DailyDAO;
import com.bank.dms.entity.Daily;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.SearchDaily;
import com.bank.dms.utils.DayUtil;


public class DailyDAOImp extends HibernateDaoSupport implements DailyDAO {
	public Daily get(Serializable id) {
		return this.getHibernateTemplate().get(Daily.class, id);
	}
	public Serializable save(Daily daily) {
		return this.getHibernateTemplate().save(daily);
	}
	public void update(Daily daily) {
		this.getHibernateTemplate().update(daily);
	}
	public void saveOrUpdate(Daily daily) {
		this.getHibernateTemplate().saveOrUpdate(daily);
	}
	public void delete(final Serializable id) {
	    this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery sqlquery = session.createSQLQuery("DELETE dms_daily daily_ WHERE daily_.id=?");
                sqlquery.setParameter(0, id);
                sqlquery.executeUpdate();
                return null;
            }
        });
	}
	@SuppressWarnings("unchecked")
    public List<Daily> find(final SearchDaily daily, final Page page) {
	    List<Daily> list = null;
	    try {
            list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Daily>>() {

                public List<Daily> doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("FROM Daily daily_ left join fetch daily_.employee employee_ left join fetch daily_.reviewer reviewer_");
                    
                    if(daily != null){
                        hql.append(" WHERE 1=1");
                        if(daily.getDeptid() != null && !"".equals(daily.getDeptid())){
                            hql.append(" AND employee_.department.id=:deptid");
                        }
                        if(daily.getEmpid() != null && !"".equals(daily.getEmpid())){
                            hql.append(" AND employee_.id=:empid");
                        }
                    }
                    hql.append(" ORDER BY daily_.subdate DESC");
                    
                    Query query = session.createQuery(hql.toString());
                    query.setFirstResult(page.getSize()*(page.getPage()-1));
                    query.setMaxResults(page.getSize());
                    
                    if(daily != null){
                        if(daily.getDeptid() != null && !"".equals(daily.getDeptid())){
                            query.setParameter("deptid", daily.getDeptid());
                        }
                        if(daily.getEmpid() != null && !"".equals(daily.getEmpid())){
                            query.setParameter("empid", daily.getEmpid());
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
	public int count(final SearchDaily daily){
	    int count = 0;
	    try {
            count = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {

                public Integer doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer sql = new StringBuffer("SELECT COUNT(daily0_.id) from dms_daily daily0_, DMS_EMPLOYEE employee1_, DMS_EMPLOYEE employee2_ where daily0_.EMPLOYEE=employee1_.ID(+) and daily0_.REVIEWER=employee2_.ID(+)");
                    
                    if(daily != null){
                        if(daily.getDeptid() != null && !"".equals(daily.getDeptid())){
                            sql.append(" AND employee1_.department=:deptid");
                        }
                        if(daily.getEmpid() != null && !"".equals(daily.getEmpid())){
                            sql.append(" AND employee1_.id=:empid");
                        }
                    }
                    
                    SQLQuery query = session.createSQLQuery(sql.toString());
                    
                    if(daily != null){
                        if(daily.getDeptid() != null && !"".equals(daily.getDeptid())){
                            query.setParameter("deptid", daily.getDeptid());
                        }
                        if(daily.getEmpid() != null && !"".equals(daily.getEmpid())){
                            query.setParameter("empid", daily.getEmpid());
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
    public Daily previewDaily(final SearchDaily daily) {
        Daily daily1 = null;
        try {
            daily1 = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Daily>() {

                public Daily doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("SELECT daily_.* FROM dms_daily daily_ WHERE daily_.employee=:empid AND daily_.dailydate=:dailydate");
                    hql.append(" ORDER BY daily_.id DESC,daily_.subdate DESC");
                    SQLQuery query = session.createSQLQuery(hql.toString());
                    query.addEntity(Daily.class);
                    
                    if(daily != null){
                        if(daily.getDailydate() != null && !"".equals(daily.getDailydate())){
                            query.setParameter("dailydate", daily.getDailydate());
                        }
                        if(daily.getEmpid() != null && !"".equals(daily.getEmpid())){
                            query.setParameter("empid", daily.getEmpid());
                        }
                    }
                    List<Daily> list = query.list();
                    if(list.size() > 0){
                        return list.get(0);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return daily1;
    }
    public List<Object[]> mitted(final Daily daily, final boolean flag) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<Object[]>>() {

            @SuppressWarnings("unchecked")
            public List<Object[]> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                StringBuffer sql = new StringBuffer();
                if(flag){
//                    sql.append("SELECT  * FROM dms_employee emp_ WHERE emp_.id in (select daily_.employee from dms_daily daily_ where daily_.dailydate=?)");
                    sql.append("SELECT  emp_.name, daily_.id, daily_.status FROM dms_employee emp_ LEFT JOIN dms_daily daily_ ON emp_.id=daily_.employee WHERE emp_.id IN (SELECT daily_.employee FROM dms_daily daily_ WHERE daily_.dailydate=?) AND daily_.dailydate=?");
                }else{
//                    sql.append("SELECT  * FROM dms_employee emp_ WHERE emp_.id not in (select daily_.employee from dms_daily daily_ where daily_.dailydate=?)");
                    sql.append("SELECT DISTINCT emp_.name, emp_.id FROM dms_employee emp_ LEFT JOIN dms_daily daily_ ON emp_.id=daily_.employee WHERE emp_.id not in (select daily_.employee from dms_daily daily_ where daily_.dailydate=?)");
                }
                SQLQuery query = session.createSQLQuery(sql.toString());
                String dailydate = null;
                if(daily == null || "".equals(daily.getDailydate())){
                    dailydate = DayUtil.getDate();
                }else{
                    dailydate = daily.getDailydate();
                }
                query.setParameter(0, dailydate);
                if(flag){
                    query.setParameter(1, dailydate);
                }
                return query.list();
            }
        });
    }
    public Daily yesterdayDaily(final Serializable id) {
        Daily daily = null;
        try {
            daily = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Daily>() {

                public Daily doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("SELECT * FROM dms_daily daily_  WHERE daily_.dailydate=to_char(sysdate-1,'yyyy-MM-dd') AND daily_.employee=?");
                    
                    SQLQuery query = session.createSQLQuery(hql.toString());
                    query.addEntity(Daily.class);
                    query.setParameter(0, id);
                    
                    List<Daily> list = query.list();
                    if(list.size() > 0){
                        return list.get(0);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return daily;
    }
    public Daily todayDaily(final Serializable id) {
        Daily daily = null;
        try {
            daily = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Daily>() {

                public Daily doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    StringBuffer hql = new StringBuffer("SELECT * FROM dms_daily daily_  WHERE daily_.dailydate=to_char(sysdate,'yyyy-MM-dd') AND daily_.employee=?");
                    
                    SQLQuery query = session.createSQLQuery(hql.toString());
                    query.addEntity(Daily.class);
                    query.setParameter(0, id);
                    
                    List<Daily> list = query.list();
                    if(list.size() > 0){
                        return list.get(0);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return daily;
    }
}
