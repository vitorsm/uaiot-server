package com.uaiot.uaitserver.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.dao.impl.UserDAO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.models.User;

@Service
public class UserService implements ServiceServer<User>{

	@Autowired
	private UserDAO dao;
	
	@Override
	public void insert(User t) throws DAOException, PermissionException {
		
		dao.insert(t);
	}

	@Override
	public void update(User t) throws DAOException, PermissionException {
		
		dao.update(t);
	}

	@Override
	public void delete(User t) throws DAOException, PermissionException {

		dao.delete(t);
	}

	@Override
	public List<User> get(List<Filter> filters) throws DAOException, PermissionException {
		
		return dao.get(filters);
	}

	@Override
	public User get(Map<String, Object> primaryKey) throws DAOException, PermissionException {
		
		return dao.get(primaryKey, User.class);
	}

}
