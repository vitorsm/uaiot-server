package com.uaiot.uaitserver.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.Alarm;
import com.uaiot.uaitserver.dao.impl.AlarmDAO;


@Service
public class AlarmService implements ServiceServer<Alarm>{

	@Autowired
	private AlarmDAO dao;
	
	@Override
	public void insert(Alarm t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(Alarm t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(Alarm t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<Alarm> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public User get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, Alarm.class);
	}

}
