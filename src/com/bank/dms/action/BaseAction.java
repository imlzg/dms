package com.bank.dms.action;

import java.util.List;

import com.bank.dms.biz.ParameterBIZ;
import com.bank.dms.entity.Parameter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {
	private ParameterBIZ paramBIZ;
	private List<Parameter> menus;
	private List<Parameter> footers;
	
	public ParameterBIZ getParamBIZ() {
		return paramBIZ;
	}
	public void setParamBIZ(ParameterBIZ paramBIZ) {
		this.paramBIZ = paramBIZ;
	}
	public List<Parameter> getMenus() {
		return menus;
	}
	public void setMenus(List<Parameter> menus) {
		this.menus = menus;
	}

	public List<Parameter> getFooters() {
		return footers;
	}
	public void setFooters(List<Parameter> footers) {
		this.footers = footers;
	}

	public void prepare() throws Exception {
		menus = this.paramBIZ.menus();
		footers = this.paramBIZ.footers();
	}

}
