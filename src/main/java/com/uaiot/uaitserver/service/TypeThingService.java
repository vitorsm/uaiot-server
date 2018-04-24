package com.uaiot.uaitserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.TypeThing;
import com.uaiot.uaitserver.dao.impl.TypeThingDAO;


@Service
public class TypeThingService implements ServiceServer<TypeThing>{

	@Autowired
	private TypeThingDAO dao;
	
	@Override
	public void insert(TypeThing t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(TypeThing t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(TypeThing t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<TypeThing> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public TypeThing get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, TypeThing.class);
	}

	public TypeThing findById(int id) throws DAOException, PermissionException {
		
		HashMap<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}
}
