package com.uaiot.uaitserver.dto;

import lombok.Data;

@Data
public class AlarmDTO {
	
	private int id;
	private String name;
	private String description;
	private float latitude;
	private float longitude;
	private float radius;
	private boolean inside;
	private ThingDTO thing;
	
}
