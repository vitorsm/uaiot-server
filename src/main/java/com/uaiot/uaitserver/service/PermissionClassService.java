package com.uaiot.uaitserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.PermissionClass;
import com.uaiot.uaitserver.dao.impl.PermissionClassDAO;


@Service
public class PermissionClassService implements ServiceServer<PermissionClass>{

	@Autowired
	private PermissionClassDAO dao;
	
	@Override
	public void insert(PermissionClass t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(PermissionClass t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(PermissionClass t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<PermissionClass> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public PermissionClass get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, PermissionClass.class);
	}

	public PermissionClass findById(int id) throws DAOException, PermissionException {
		
		HashMap<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}
}
