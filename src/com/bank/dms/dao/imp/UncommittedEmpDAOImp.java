package com.bank.dms.dao.imp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UncommittedEmpDAOImp {

    private static SessionFactory sessionFactory;

    // 获取当天没有提交过日报的员工email
    public static List<String> getUncommittedNames() {
        Session session = sessionFactory.openSession();
        SQLQuery sqlquery = session
                .createSQLQuery("SELECT emp_.email FROM dms_employee emp_ WHERE emp_.id NOT IN (SELECT daily_.employee FROM dms_daily daily_ WHERE daily_.dailydate=to_char(sysdate-1,'yyyy-MM-dd'))");
        List<String> list = sqlquery.list();
        session.close();
        return list;
    }

    // 获取部门管理人员email以及当天日报考勤
    /*
     * public static Map<String, String> getManagerEmails(){ // sessionFactory =
     * (SessionFactory) new
     * ClassPathXmlApplicationContext("applicationContext.xml"
     * ).getBean("sessionFactory"); Map<String, String> map = new
     * HashMap<String, String>(); Session session =
     * sessionFactory.openSession(); //部门管理者 SQLQuery sqlquery =
     * session.createSQLQuery(
     * "SELECT emp_.id, emp_.email,dept_.id deptid FROM dms_employee emp_, dms_department dept_ WHERE emp_.id= dept_.manager"
     * ); List<Object[]> list = sqlquery.list();
     * 
     * List<String> uncommitteds = null; //未交日报 for (Object[] values : list) {
     * session.clear(); SQLQuery sqlquery2 =session.createSQLQuery(
     * "SELECT DISTINCT emp_.name FROM dms_employee emp_ LEFT JOIN dms_daily daily_ ON emp_.id=daily_.employee WHERE emp_.id not in (select daily_.employee from dms_daily daily_ where daily_.dailydate=to_char(sysdate,'yyyy-MM-dd')) AND emp_.department=:deptManager"
     * ); sqlquery2.setParameter("deptManager", values[2]); uncommitteds =
     * sqlquery2.list(); StringBuffer sb = new StringBuffer("\n未交日报：\n\t");
     * if(uncommitteds != null && uncommitteds.size() > 0){ for (String string2
     * : uncommitteds) { sb.append(string2 + "\t"); } }
     * map.put(values[1].toString(), sb.toString()); }
     * 
     * List<String> submitteds = null; //已交日报 for (Object[] values : list) {
     * session.clear(); SQLQuery sqlquery2 =session.createSQLQuery(
     * "SELECT emp_.name FROM dms_employee emp_ LEFT JOIN dms_daily daily_ ON emp_.id=daily_.employee WHERE emp_.id IN (SELECT daily_.employee FROM dms_daily daily_ WHERE daily_.dailydate=to_char(sysdate,'yyyy-MM-dd')) AND daily_.dailydate=to_char(sysdate,'yyyy-MM-dd') AND emp_.department=:deptManager"
     * ); sqlquery2.setParameter("deptManager", values[2]); submitteds =
     * sqlquery2.list(); StringBuffer sb = new StringBuffer("已交日报：\n\t");
     * if(submitteds != null && submitteds.size() > 0){ for (String string2 :
     * submitteds) { sb.append(string2 + "\t"); } } String value =
     * map.get(values[1]); if(value != null && !"".equals(value))
     * sb.append(value); map.put(values[1].toString(), sb.toString()); }
     * session.close(); return map; }
     */

    // 获取项目负责人email以及当天日报考勤
    public static Map<String, String> getManagerEmails(Object projectid) {
        // sessionFactory = (SessionFactory) new
        // ClassPathXmlApplicationContext("applicationContext.xml").getBean("sessionFactory");
        Map<String, String> map = new HashMap<String, String>();
        Session session = sessionFactory.openSession();
        // 项目负责人
        SQLQuery sqlquery = session
                .createSQLQuery("SELECT DISTINCT emp_.email FROM dms_employee emp_, dms_project project_ WHERE emp_.id= project_.manager AND project_.id=:projectid");
        sqlquery.setParameter("projectid", projectid);
        List<Object> list = sqlquery.list();

        List<String> uncommitteds = null;
        // 未交日报
        for (Object values : list) {
            session.clear();
            SQLQuery sqlquery2 = session
                    .createSQLQuery("SELECT DISTINCT emp_.name FROM dms_project_emp pe_, dms_employee emp_, dms_project project_ WHERE pe_.projectid= pe_.projectid AND pe_.employeeid= emp_.id AND pe_.projectid=:projectid AND emp_.id NOT IN(SELECT DISTINCT emp_.id FROM dms_project_emp pe_, dms_employee emp_, dms_project project_, dms_daily daily_ WHERE pe_.projectid= pe_.projectid AND pe_.employeeid= emp_.id AND daily_.employee= emp_.id AND pe_.projectid=:projectid AND daily_.dailydate=to_char(sysdate,'yyyy-MM-dd'))");
            sqlquery2.setParameter("projectid", projectid);
            uncommitteds = sqlquery2.list();
            StringBuffer sb = new StringBuffer("\n未交日报：\n\t");
            if (uncommitteds != null && uncommitteds.size() > 0) {
                for (String string2 : uncommitteds) {
                    sb.append(string2 + "\t");
                }
            }
            map.put(values.toString(), sb.toString());
        }

        List<String> submitteds = null;
        // 已交日报
        for (Object values : list) {
            session.clear();
            SQLQuery sqlquery2 = session
                    .createSQLQuery("SELECT DISTINCT emp_.name FROM dms_project_emp pe_, dms_employee emp_, dms_project project_, dms_daily daily_ WHERE pe_.projectid= pe_.projectid AND pe_.employeeid= emp_.id AND daily_.employee= emp_.id AND pe_.projectid=:projectid AND daily_.dailydate=to_char(sysdate,'yyyy-MM-dd')");
            sqlquery2.setParameter("projectid", projectid);
            submitteds = sqlquery2.list();
            StringBuffer sb = new StringBuffer("已交日报：\n\t");
            if (submitteds != null && submitteds.size() > 0) {
                for (String string2 : submitteds) {
                    sb.append(string2 + "\t");
                }
            }
            String value = map.get(values);
            if (value != null && !"".equals(value))
                sb.append(value);
            map.put(values.toString(), sb.toString());
        }
        session.close();
        return map;
    }

    public static List<String[]> pdfContent() {
        // sessionFactory = (SessionFactory) new
        // ClassPathXmlApplicationContext("applicationContext.xml").getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        List<String[]> list = null;
        try {
            SQLQuery query = session
                    .createSQLQuery("SELECT project_.name, emp_.name empname, project_.startdate, project_.enddate, project_.id FROM dms_project project_, dms_employee emp_ WHERE project_.manager=emp_.id");
            list = query.list();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public static List<Object[]> dailyContent(Object projectid) {
        // sessionFactory = (SessionFactory) new
        // ClassPathXmlApplicationContext("applicationContext.xml").getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        List<Object[]> list = null;
        try {
            SQLQuery query = session
                    .createSQLQuery("SELECT emp_.name, daily_.content FROM dms_daily daily_, dms_project project_, dms_project_emp pe_, dms_employee emp_ WHERE project_.id= pe_.projectid AND pe_.employeeid= emp_.id AND daily_.employee= emp_.id AND daily_.dailydate=to_char(sysdate,'yyyy-MM-dd') AND project_.id=:projectid");
            query.setParameter("projectid", projectid);
            list = query.list();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    // SELECT emp_.name, daily_.content FROM dms_daily daily_, dms_project
    // project_, dms_project_emp pe_, dms_employee emp_ WHERE project_.id=
    // pe_.projectid AND pe_.employeeid= emp_.id AND daily_.employee= emp_.id
    // AND project_.id=1

    public static void main(String[] args) {
        // List<String> list = UncommittedEmpDAOImp.getUncommittedNames();
        // String[] arr = (String[]) list.toArray(new String[list.size()]);
        // for (String s : arr) {
        // System.out.println(s);
        // }
        // Map<String, String> map = getManagerEmails();
        // for (String key : map.keySet()) {
        // System.out.println(key + "\n" + map.get(key));
        // }
        List<String[]> list = pdfContent();
        for (Object[] s : list) {
            System.out.println(s);
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
