package com.uaiot.uaitserver.dto;

import lombok.Data;

@Data
public class ThingDTO {
	
	private int imei;
	private String name;
	private String description;
	private TypeThingDTO typeThing;
	
}
