package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.TypeThing;


@Service
public class TypeThingDAO extends DAO<TypeThing> {
	
	@Override
	public String getNameTable() {
		return "TypeThing";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

}
