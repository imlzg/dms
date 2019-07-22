package com.bank.dms.action;

import java.util.List;

import com.bank.dms.biz.ParameterBIZ;
import com.bank.dms.entity.Parameter;

public class ParameterAction extends BaseAction {
	private ParameterBIZ parameterBIZ;
	private Parameter parameter;
	private List<Parameter> list;

	public ParameterBIZ getParameterBIZ() {
		return parameterBIZ;
	}
	public void setParameterBIZ(ParameterBIZ parameterBIZ) {
		this.parameterBIZ = parameterBIZ;
	}
	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	public List<Parameter> getList() {
		return list;
	}
	public void setList(List<Parameter> list) {
		this.list = list;
	}

	public String save() {
		this.getParameterBIZ().saveOrUpdate(parameter);
		return SUCCESS;
	}
	public String list() {
		list = this.getParameterBIZ().find();
		return SUCCESS;
	}
	public String delete(){
	    parameterBIZ.delete(parameter.getId());
	    return SUCCESS;
	}


}
