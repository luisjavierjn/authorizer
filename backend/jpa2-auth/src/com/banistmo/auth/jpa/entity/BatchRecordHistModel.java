package com.banistmo.auth.jpa.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="batch_record_hist")
public class BatchRecordHistModel {

	@Id
	@Column(name="id",nullable=false)
	private long id;

	@Column(name="name",nullable=false,precision=30)
	private String name;
	
	@Column(name="wr_date",precision=6)
	private Timestamp wr_date; 
	
	@Column(name="file_type",precision=10)
	private String file_type;	

	public BatchRecordHistModel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getWr_date() {
		return wr_date;
	}

	public void setWr_date(Timestamp wr_date) {
		this.wr_date = wr_date;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}	
}
