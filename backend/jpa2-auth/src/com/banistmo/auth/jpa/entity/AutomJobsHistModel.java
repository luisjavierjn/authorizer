package com.banistmo.auth.jpa.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autom_jobs_hist")
public class AutomJobsHistModel {

	@Id
	@Column(name="id",nullable=false)
	private long id;
	
	@Column(name="ex_date",precision=6)
	private Timestamp ex_date; 	
	
	@Column(name="type",precision=25)
	private String type;

	public AutomJobsHistModel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getEx_date() {
		return ex_date;
	}

	public void setEx_date(Timestamp ex_date) {
		this.ex_date = ex_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}		
		
}
