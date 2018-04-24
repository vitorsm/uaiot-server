package com.uaiot.uaitserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LogDTO {
	
	private int id;
	private Date time;
	private float latitude;
	private float longitude;
	private ThingDTO thing;
}
