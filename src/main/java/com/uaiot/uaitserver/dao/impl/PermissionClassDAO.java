package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.PermissionClass;


@Service
public class PermissionClassDAO extends DAO<PermissionClass> {
	
	@Override
	public String getNameTable() {
		return "PermissionClass";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

}
