package com.banistmo.auth.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IRoleDao;
import com.banistmo.auth.jpa.entity.Role;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public List<Role> getAll() {
		try {
			return this.roleDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void insertRole(@RequestBody Role roleModel) {
		try {
			this.roleDao.insertRole(roleModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateRole(@RequestBody Role roleModel) {
		try {
			this.roleDao.updateRole(roleModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteRole(@RequestBody Role roleModel) {
		try {
			this.roleDao.deleteRole(roleModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getbyrolename", method=RequestMethod.GET)
	public Role getByRolename(@RequestParam String rolename) {
		try {
			return this.roleDao.getByRolename(rolename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/getbyid", method=RequestMethod.GET)
	public Role getById(@RequestParam long id) {
		try {
			return this.roleDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
