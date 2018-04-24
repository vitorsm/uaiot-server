package com.uaiot.uaitserver.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_group")
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_group_has_permission_class",
			joinColumns = {
					@JoinColumn(name = "user_group_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "permission_class_id")
			}
	)
	private List<PermissionClass> permissionClasses;
	
	
	public UserGroup() {
	}
	
	public UserGroup(int id) {
		this.id = id;
	}
}
