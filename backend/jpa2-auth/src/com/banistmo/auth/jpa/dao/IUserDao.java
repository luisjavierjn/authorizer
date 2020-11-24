package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.User;

public interface IUserDao { 
	
	public List<User> getAll() throws Exception;
	
	public void insertUser(User userModel) throws Exception;
	
	public void updateUser(User userModel) throws Exception;
	
	public void deleteUser(User userModel) throws Exception;

	public User getByUsername(String username) throws Exception;	
	
	public User getById(long id) throws Exception;	
	
	public List<User> getUserById(long id) throws Exception;
	
	public List<User> getUserByUsername(String username) throws Exception;
	
	public String getTest() throws Exception;
}
