package com.bank.dms.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.bank.dms.biz.ParameterBIZ;
import com.bank.dms.biz.RoleBIZ;
import com.bank.dms.entity.Page;
import com.bank.dms.entity.Parameter;
import com.bank.dms.entity.Role;

public class RoleAction extends BaseAction {
    
    private Page pageNum;
    
    private Role role;
    
    private Role searchRole;
    
    private RoleBIZ roleBIZ;
    
    private ParameterBIZ parameterBIZ;
    
    private List<Parameter> list;

    public String save(){
        roleBIZ.save(role);
        return SUCCESS;
    }

    public String edit(){
        try {
            role = roleBIZ.get(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String doEdit(){
        try {
            roleBIZ.update(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String delete(){
        try {
            roleBIZ.delete(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String list(){
        List<Role> roleList = roleBIZ.find(searchRole, pageNum);
        ServletActionContext.getRequest().setAttribute("roleList", roleList);
        list = parameterBIZ.findAll();
        return SUCCESS;
    }

    public Page getPageNum() {
        return pageNum;
    }

    public void setPageNum(Page pageNum) {
        this.pageNum = pageNum;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getSearchRole() {
        return searchRole;
    }

    public void setSearchRole(Role searchRole) {
        this.searchRole = searchRole;
    }

    public void setRoleBIZ(RoleBIZ roleBIZ) {
        this.roleBIZ = roleBIZ;
    }

    public List<Parameter> getList() {
        return list;
    }

    public void setList(List<Parameter> list) {
        this.list = list;
    }

    public void setParameterBIZ(ParameterBIZ parameterBIZ) {
        this.parameterBIZ = parameterBIZ;
    }

}
