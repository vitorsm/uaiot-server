package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.User;


@Service
public class UserDAO extends DAO<User> {
	
	@Override
	public String getNameTable() {
		return "User";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

}
