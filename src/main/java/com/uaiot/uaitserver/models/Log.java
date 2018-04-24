package com.uaiot.uaitserver.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "log")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	@Column(name = "latitude", nullable = false)
	private float latitude;
	
	@Column(name = "longitude", nullable = false)
	private float longitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thing_imei", nullable = false, referencedColumnName = "imei")
	private Thing thing;
}
