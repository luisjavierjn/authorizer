package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.OperationParam;

public interface IOperationParamDao {

	public List<OperationParam> getAll() throws Exception;	
	
	public OperationParam getById(long id) throws Exception;
	
	public OperationParam getByCode(String code) throws Exception;
	
	public OperationParam getByName(String name) throws Exception;
	
	public List<OperationParam> getByCodeList(List<String> codes) throws Exception;

	public void updateOperationParam(OperationParam operationParamModel) throws Exception;
}
