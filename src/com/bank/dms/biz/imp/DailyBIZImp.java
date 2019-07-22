package com.bank.dms.biz.imp;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.biz.DailyBIZ;
import com.bank.dms.dao.DailyDAO;
import com.bank.dms.entity.Daily;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.SearchDaily;

public class DailyBIZImp implements DailyBIZ {
	private DailyDAO dailyDAO;
	
    public Daily get(Serializable id){
        return dailyDAO.get(id);
    }

    public Serializable save(Daily daily){
        return dailyDAO.save(daily);
    }

    public void update(Daily daily){
        dailyDAO.update(daily);
    }

    public void saveOrUpdate(Daily daily){
        dailyDAO.saveOrUpdate(daily);
    }

    public void delete(Serializable id){
        dailyDAO.delete(id);
    }

    public List<Daily> find(SearchDaily daily, Page page){
        return dailyDAO.find(daily, page);
    }

    public int count(SearchDaily daily){
        return dailyDAO.count(daily);
    }

    public Daily previewDaily(SearchDaily daily) {
        return dailyDAO.previewDaily(daily);
    }
    
    public List<Object[]> mitted(Daily daily, boolean flag) {
        return dailyDAO.mitted(daily, flag);
    }

    public Daily yesterdayDaily(Serializable id) {
        return dailyDAO.yesterdayDaily(id);
    }

    public Daily todayDaily(Serializable id) {
        return dailyDAO.todayDaily(id);
    }

    public void setDailyDAO(DailyDAO dailyDAO) {
        this.dailyDAO = dailyDAO;
    }

}
