package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.UserGroup;


@Service
public class UserGroupDAO extends DAO<UserGroup> {
	
	@Override
	public String getNameTable() {
		return "UserGroup";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

}
