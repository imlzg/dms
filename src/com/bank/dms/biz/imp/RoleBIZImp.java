package com.bank.dms.biz.imp;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.biz.RoleBIZ;
import com.bank.dms.dao.RoleDAO;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.Role;

public class RoleBIZImp implements RoleBIZ {
    
    private RoleDAO roleDAO;

    public List<Object[]> roles() {
        return roleDAO.roles();
    }
    
    public Role get(Serializable id){
        return roleDAO.get(id);
    }
    
    public Serializable save(Role role){
        return roleDAO.save(role);
    }
    
    public void update(Role role){
        roleDAO.update(role);
    }
    
    public void delete(Serializable id){
        roleDAO.delete(id);
    }
    
    public List<Role> find(Role role, Page page){
        return roleDAO.find(role, page);
    }
    
    public int count(Role role){
        return roleDAO.count(role);
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

}
