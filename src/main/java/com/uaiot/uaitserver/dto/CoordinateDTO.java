package com.uaiot.uaitserver.dto;

import lombok.Data;

@Data
public class CoordinateDTO {
	private ThingDTO thing;
	private float latitude;
	private float longitude;
}
