package com.uaiot.uaitserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.Log;
import com.uaiot.uaitserver.dao.impl.LogDAO;


@Service
public class LogService implements ServiceServer<Log>{

	@Autowired
	private LogDAO dao;
	
	@Override
	public void insert(Log t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(Log t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(Log t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<Log> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public Log get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, Log.class);
	}
	
	public Log findById(int id) throws DAOException, PermissionException {
		
		HashMap<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}
}
