package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.Coordinator;
import com.uaiot.uaitserver.models.Log;


@Service
public class LogDAO extends DAO<Log> {
	
	@Override
	public String getNameTable() {
		return "Log";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

	public List<Coordinate> positionCurrentByThing(long thingImei) {
		String hql = "from " + getNameTable() + "where id=" + thingImei + " group by id";
		
	}
	
	public List<Coordinate> positionCurrentByTypeThing(int typeThingId) {
		String hql = "from " + getNameTable() + "where typeThingId=" + typeThingId + " group by id";
	}
}
