package com.uaiot.uaitserver.models;

import lombok.Data;

@Data
public class Coordinate {
	private Thing thing;
	private float latitude;
	private float longitude;
}
