package com.uaiot.uaitserver.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "alarm")
public class Alarm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "latitude", nullable = false)
	private float latitude;
	
	@Column(name = "longitude", nullable = false)
	private float longitude;
	
	@Column(name = "radius", nullable = false)
	private float radius;
	
	@Column(name = "inside", nullable = false)
	private boolean inside;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thing_imei", nullable = false, referencedColumnName = "imei")
	private Thing thing;
}
