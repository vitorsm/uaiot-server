package com.uaiot.uaitserver.dto;

import lombok.Data;

@Data
public class ThingDTO {
	
	private long imei;
	private String name;
	private String description;
	private TypeThingDTO typeThing;
	
}
