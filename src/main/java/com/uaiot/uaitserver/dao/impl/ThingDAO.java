package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.Thing;


@Service
public class ThingDAO extends DAO<Thing> {
	
	@Override
	public String getNameTable() {
		return "Thing";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

}
