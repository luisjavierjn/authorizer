package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.Role;

public interface IRoleDao {

	public List<Role> getAll() throws Exception;

	public void insertRole(Role roleModel) throws Exception;

	public void updateRole(Role roleModel) throws Exception;

	public void deleteRole(Role roleModel) throws Exception;

	public Role getByRolename(String rolename) throws Exception;

	public Role getById(long id) throws Exception;
	
	public String getTest() throws Exception;
}
