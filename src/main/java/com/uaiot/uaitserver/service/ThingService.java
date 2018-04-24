package com.uaiot.uaitserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.Thing;
import com.uaiot.uaitserver.dao.impl.ThingDAO;


@Service
public class ThingService implements ServiceServer<Thing>{

	@Autowired
	private ThingDAO dao;
	
	@Override
	public void insert(Thing t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(Thing t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(Thing t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<Thing> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public Thing get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, Thing.class);
	}

	public Thing findById(int id) throws DAOException, PermissionException {
		
		HashMap<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}
}
