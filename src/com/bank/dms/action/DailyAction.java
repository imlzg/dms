package com.bank.dms.action;

import java.sql.Timestamp;

import org.apache.struts2.ServletActionContext;

import com.bank.dms.biz.DailyBIZ;
import com.bank.dms.entity.Daily;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.SearchDaily;
import com.bank.dms.utils.DayUtil;

public class DailyAction extends BaseAction {
    
    private Page pageNum;
    
    private Daily daily;
    
    private SearchDaily searchDaily;
    
    private DailyBIZ dailyBIZ;

    public String save(){
        if(daily != null){
            daily.setSubdate(new Timestamp(System.currentTimeMillis()));
        }
        try {
            Employee emp = new Employee();
            Employee userdetails = (Employee) ServletActionContext.getRequest().getSession().getAttribute("userdetails");
            emp.setId(userdetails.getId());
            daily.setEmployee(emp);
            dailyBIZ.save(daily);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String edit(){
        daily = dailyBIZ.get(daily.getId());
        return SUCCESS;
    }
    
    public String doEdit(){
        dailyBIZ.update(daily);
        return SUCCESS;
    }

    public String delete(){
        dailyBIZ.delete(daily.getId());
        return SUCCESS;
    }

    public String list(){
        //
        ServletActionContext.getRequest().setAttribute("submitted", dailyBIZ.mitted(daily, true));
        //
        ServletActionContext.getRequest().setAttribute("uncommitted", dailyBIZ.mitted(daily, false));
        return SUCCESS;
    }
    
    public String previewDaily(){
        if(searchDaily == null){
            searchDaily = new SearchDaily();
            searchDaily.setDailydate(DayUtil.getDate());
            Employee userdetails = (Employee) ServletActionContext.getRequest().getSession().getAttribute("userdetails");
            searchDaily.setEmpid(userdetails.getId());
        }
        daily = dailyBIZ.previewDaily(searchDaily);
        return SUCCESS;
    }
    
    public String reviewDaily(){
        daily = dailyBIZ.get(daily.getId());
        return SUCCESS;
    }
    
    public String doReviewDaily(){
        try {
            dailyBIZ.update(daily);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public Page getPageNum() {
        return pageNum;
    }

    public void setPageNum(Page pageNum) {
        this.pageNum = pageNum;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public SearchDaily getSearchDaily() {
        return searchDaily;
    }

    public void setSearchDaily(SearchDaily searchDaily) {
        this.searchDaily = searchDaily;
    }

    public void setDailyBIZ(DailyBIZ dailyBIZ) {
        this.dailyBIZ = dailyBIZ;
    }

    
}
