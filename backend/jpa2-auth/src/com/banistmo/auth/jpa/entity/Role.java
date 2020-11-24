package com.banistmo.auth.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "auth_roles")
public class Role {

	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_ROLES_SEQ")
	//@SequenceGenerator(name = "AUTH_ROLES_SEQ", sequenceName = "AUTH_ROLES_SEQ", allocationSize = 1)
	@Id
	@Column(name = "id",nullable = false)
	private long id;

	@Column(name = "rolename", nullable = false, precision = 20)
	private String rolename;

	@Column(name = "description", nullable = false, precision = 45)
	private String description;

	public Role() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoleModel [id=" + id + ", rolename=" + rolename + ", description=" + description + "]";
	}
}
