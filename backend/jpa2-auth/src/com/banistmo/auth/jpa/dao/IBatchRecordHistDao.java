package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.BatchRecordHistModel;

public interface IBatchRecordHistDao {

	public List<BatchRecordHistModel> getAll() throws Exception;
	
	public BatchRecordHistModel getById(long id) throws Exception;
}
