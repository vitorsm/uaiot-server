package com.uaiot.uaitserver.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserGroupDTO {

	private int id;
	private String name;
	private List<PermissionClassDTO> permissionClasses;
	
}
