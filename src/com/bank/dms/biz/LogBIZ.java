package com.bank.dms.biz;

import java.io.Serializable;
import java.util.List;

import com.bank.dms.entity.Log;

public interface LogBIZ {
	public Log get(Serializable id);
	public void save(Log log);
	public void delete(Log log);
	public List<Log> find();
	public List<Log> find(String sql);
}
