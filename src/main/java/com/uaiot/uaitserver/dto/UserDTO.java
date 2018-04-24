package com.uaiot.uaitserver.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private int id;
	private String name;
	private String login;
	private String password;
	private UserGroupDTO userGroup;
}
