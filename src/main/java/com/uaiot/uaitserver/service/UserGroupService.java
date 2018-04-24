package com.uaiot.uaitserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.dao.impl.UserGroupDAO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.UserGroup;

@Service
public class UserGroupService implements ServiceServer<UserGroup>{

	@Autowired
	private UserGroupDAO dao;
	
	@Override
	public void insert(UserGroup t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(UserGroup t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(UserGroup t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<UserGroup> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public UserGroup get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, UserGroup.class);
	}

	public UserGroup findById(int id) throws DAOException, PermissionException {
		
		HashMap<String, Object> pk = new HashMap<String, Object>();
		pk.put("id", id);
		
		return this.get(pk);
	}
}
