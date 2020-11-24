package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.DataTransaction;
import com.banistmo.auth.web.dto.DataTransactionFilter;

public interface IDataTransactionDao {

	public List<DataTransaction> getAll() throws Exception;

	public List<DataTransaction> getByName(String name) throws Exception;

	public DataTransaction getById(String id) throws Exception;

	public List<DataTransaction> getFilteredData(DataTransactionFilter dataTransactionFilter) throws Exception;

}