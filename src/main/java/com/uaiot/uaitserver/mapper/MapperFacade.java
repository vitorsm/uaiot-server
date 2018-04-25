package com.uaiot.uaitserver.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("singleton")
public class MapperFacade {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	
	@Autowired
	public AlarmMapper alarmMapper;
	
	@Autowired
	public CoordinateMapper coordinateMapper;
	
	@Autowired
	public LogMapper logMapper;
	
	@Autowired
	public PermissionClassMapper permissionClassMapper;
	
	@Autowired
	public ThingMapper thingMapper;
	
	@Autowired
	public TypeThingMapper typeThingMapper;
	
	@Autowired
	public UserGroupMapper userGroupMapper;
	
	@Autowired
	public UserMapper userMapper;
}
