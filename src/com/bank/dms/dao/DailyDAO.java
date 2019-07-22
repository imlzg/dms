package com.bank.dms.dao;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Daily;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.SearchDaily;

public interface DailyDAO {

    public abstract Daily get(Serializable id);

    public abstract Serializable save(Daily daily);

    public abstract void update(Daily daily);

    public abstract void saveOrUpdate(Daily daily);

    public abstract void delete(final Serializable id);

    public abstract List<Daily> find(SearchDaily daily, Page page);

    public abstract int count(SearchDaily daily);
    
    public abstract Daily previewDaily(SearchDaily daily);
    
    public abstract List<Object[]> mitted(Daily daily, boolean flag);
    
    public abstract Daily yesterdayDaily(Serializable id);
    
    public abstract Daily todayDaily(Serializable id);

}