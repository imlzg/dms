package com.bank.dms.biz;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Page;
import com.bank.dms.entity.Role;


public interface RoleBIZ {

    public abstract List<Object[]> roles();
    
    public abstract Role get(Serializable id);
    
    public abstract Serializable save(Role role);
    
    public abstract void update(Role role);
    
    public abstract void delete(Serializable id);
    
    public abstract List<Role> find(Role role, Page page);
    
    public abstract int count(Role role);

}