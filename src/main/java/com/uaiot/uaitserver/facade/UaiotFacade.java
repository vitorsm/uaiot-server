package com.uaiot.uaitserver.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dto.PermissionClassDTO;
import com.uaiot.uaitserver.mapper.MapperFacade;
import com.uaiot.uaitserver.service.AlarmService;
import com.uaiot.uaitserver.service.LogService;
import com.uaiot.uaitserver.service.PermissionClassService;
import com.uaiot.uaitserver.service.ThingService;
import com.uaiot.uaitserver.service.TypeThingService;
import com.uaiot.uaitserver.service.UserGroupService;
import com.uaiot.uaitserver.service.UserService;


@Service
@Scope("singleton")
public class UaiotFacade {
	
	@Autowired
	public AlarmService alarmService;
	
	@Autowired
	public LogService logService;
	
	@Autowired
	public MapperFacade map;
	
	@Autowired
	public PermissionClassService permissionClassService;
	
	@Autowired
	public ThingService thingService;
	
	@Autowired
	public TypeThingService typeThingService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserGroupService userGroupService;
	
}
