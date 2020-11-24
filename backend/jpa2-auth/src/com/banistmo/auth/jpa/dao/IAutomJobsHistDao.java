package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.AutomJobsHistModel;

public interface IAutomJobsHistDao {

	public List<AutomJobsHistModel> getAll() throws Exception;
	
	public AutomJobsHistModel getById(long id) throws Exception;
	
	public List<AutomJobsHistModel> getByDateRange(String dateStart, String dateEnd) throws Exception;
}
