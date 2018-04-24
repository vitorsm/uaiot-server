package com.uaiot.uaitserver.service;

import java.util.List;
import java.util.Map;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.exceptions.PermissionException;



public interface ServiceServer<T> {
	
	public void insert(T t) throws DAOException, PermissionException;
	public void update(T t) throws DAOException, PermissionException;
	public void delete(T t) throws DAOException, PermissionException;
	public List<T> get(List<Filter> filters) throws DAOException, PermissionException;
	public T get(Map<String, Object> primaryKey) throws DAOException, PermissionException;
	
}