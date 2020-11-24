package com.banistmo.auth.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operation_param")
public class OperationParam {

	@Id
	@Column(name="id",nullable=false)
	private long id;
	
	@Column(name="code",nullable=false,precision=3)
	private String code;
	
	@Column(name="name",nullable=false,precision=30)
	private String name;

	@Column(name="operation_value",nullable=false,precision=200)
	private String operation_value;

	@Column(name="comments",nullable=false,precision=100)
	private String comments;

	public OperationParam() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperation_value() {
		return operation_value;
	}

	public void setOperation_value(String operation_value) {
		this.operation_value = operation_value;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
