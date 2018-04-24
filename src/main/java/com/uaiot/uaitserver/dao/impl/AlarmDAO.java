package com.uaiot.uaitserver.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAO;
import com.uaiot.uaitserver.models.Alarm;


@Service
public class AlarmDAO extends DAO<Alarm> {
	
	@Override
	public String getNameTable() {
		return "Alarm";
	}

	@Override
	public List<String> getPrimaryKeys() {
		
		return Arrays.asList("id");
	}

}
