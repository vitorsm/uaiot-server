package com.uaiot.uaitserver.dto;

import lombok.Data;

@Data
public class ThingDTO {
	
	private long id;
	private String name;
	private String description;
	private TypeThingDTO typeThing;
	
}
