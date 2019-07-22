package com.bank.dms.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bank.dms.biz.LogBIZ;
import com.bank.dms.biz.ParameterBIZ;
import com.bank.dms.biz.imp.LogBIZImp;
import com.bank.dms.entity.Employee;
import com.bank.dms.entity.Log;
import com.bank.dms.entity.Parameter;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class LogInterceptor extends AbstractInterceptor {
	private LogBIZ logBIZ;
	private ParameterBIZ parameterBIZ;
	private List<Parameter> actions;
	private Parameter parameter;
	private Employee user;
	private String action;
	private String url;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		action = invocation.getProxy().getActionName();
		actions = parameterBIZ.find("from Parameter where code='log' and value='"+ action +"'");

		if(null == actions || actions.size()<1) return invocation.invoke();
		parameter = actions.get(0);

//		boolean isLog = false;
//		for (Parameter p : actions) {
//			String act = p.getValue();
//			if(act.equalsIgnoreCase(action)) {
//				isLog = true;
//				parameter = p;
//				break;
//			}
//		}
//		if (! isLog) return invocation.invoke();
		
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		user = (Employee) session.get("userdetails");
		url = queryURL(invocation);

		invocation.addPreResultListener(new PreResultListener() {
			public void beforeResult(ActionInvocation invok, String result) {
				Map<String, Object> session = invok.getInvocationContext().getSession();
				if(null == user) user = (Employee) session.get("userdetails");
				
				log(result);
			}
		});
		return invocation.invoke();
	}

	public void log(String result) {
		Log log = new Log();
		log.setEmployee(user);
		log.setDateTime(new Date());
		//log.setContent("请求 ：" + action + "，结果：" + result + "，路径：" + url);
		System.out.println(parameter.getExpression());
		log.setContent(new Formatter().format(null == parameter.getExpression()? "" : parameter.getExpression(), action, result, url).toString());

		logBIZ.save(log);
	}
	@SuppressWarnings("unchecked")
	public String queryURL(ActionInvocation invok) {
		HttpServletRequest req = ServletActionContext.getRequest();
		String path = req.getContextPath();
		String namespace = invok.getProxy().getNamespace();
		String actionName = invok.getProxy().getActionName();

		StringBuffer url = new StringBuffer(path);
		if(StringUtils.isNotEmpty(namespace)) url.append(namespace);
		if(url.charAt(url.length()-1) != '/') url.append("/");
		if(StringUtils.isNotEmpty(actionName)) url.append(actionName).append("?");
		Map<String, String[]> ps = req.getParameterMap();
		if (null != ps) {
			for(String k : ps.keySet()) {
				String[] vs = ps.get(k);
				for(String v : vs) {
					url.append(k).append("=").append(v).append("&");
				}
			}
		}
		if (url.charAt(url.length()-1)=='?' || url.charAt(url.length()-1)=='&') url.deleteCharAt(url.length()-1);
		return url.toString();
	}

	public LogBIZ getLogBIZ() {
		return logBIZ;
	}
	public void setLogBIZ(LogBIZ logBIZ) {
		this.logBIZ = logBIZ;
	}

	public ParameterBIZ getParameterBIZ() {
		return parameterBIZ;
	}
	public void setParameterBIZ(ParameterBIZ parameterBIZ) {
		this.parameterBIZ = parameterBIZ;
	}

}
