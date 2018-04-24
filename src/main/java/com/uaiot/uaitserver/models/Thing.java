package com.uaiot.uaitserver.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "thing")
public class Thing {
	
	@Id
	@Column(name = "imei", nullable = false)
	private long imei;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "type_thing_id", nullable = false)
	private TypeThing typeThing;
	
	public Thing() {
	}
	
	public Thing(int imei) {
		this.imei = imei;
	}
}
