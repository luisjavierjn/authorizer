package com.banistmo.auth.jpa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auth_users")
public class User {
	
	@Id
	@Column(name="id",nullable=false)
	private long id;
	
	@Column(name="username",nullable=false,precision=40)
	private String username;
	
	@Column(name="phone_number",precision=40)
	private String phone_number;
	
	@Column(name="email",precision=150)
	private String email;

	@Column(name="area",precision=40)
	private String area;
	
	@Column(name="status",nullable=false)
	private long status;
	
	@Column(name="authorized")
	private long authorized;
	
	@Column(name="fk_roles",nullable=false)
	private long fk_roles;
	
	@Column(name="creator_user")
	private long creator_user;
	
	@Column(name="modifier_user")
	private long modifier_user;
	
	@Column(name="creation_date")
	private Date creation_date;
	
	@Column(name="update_date")
	private Date update_date;
	
	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getAuthorized() {
		return authorized;
	}

	public void setAuthorized(long authorized) {
		this.authorized = authorized;
	}

	public long getFk_roles() {
		return fk_roles;
	}

	public void setFk_roles(long fk_roles) {
		this.fk_roles = fk_roles;
	}

	public long getCreator_user() {
		return creator_user;
	}

	public void setCreator_user(long creator_user) {
		this.creator_user = creator_user;
	}

	public long getModifier_user() {
		return modifier_user;
	}

	public void setModifier_user(long modifier_user) {
		this.modifier_user = modifier_user;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + 
				", username=" + username + 
				", phone_number=" + phone_number + 
				", email=" + email + 
				", area=" + area + 
				", status=" + status + 
				", authorized=" + authorized + 
				", fk_roles=" + fk_roles +
				", creator_user=" + creator_user +
				", modifier_user=" + modifier_user +
				", creation_date=" + creation_date +
				", update_date=" + update_date +
				"]";
	}		
}
