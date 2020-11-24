package com.banistmo.auth.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IUserDao;
import com.banistmo.auth.jpa.dao.impl.UserDaoImpl;
import com.banistmo.auth.jpa.entity.User;

import java.util.List;
import oracle.sql.DATE;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public List<User> getAll() {
		try {
			return this.userDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody User insertUser(@RequestBody User userModel) {
		java.util.Date dt = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
		userModel.setCreation_date(sqlDate);
		userModel.setUpdate_date(sqlDate);
		try {
			this.userDao.insertUser(userModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userModel;
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody User updateUser(@RequestBody User userModel) {
		java.util.Date dt = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
		userModel.setUpdate_date(sqlDate);		
		try {
			this.userDao.updateUser(userModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userModel;
	}

	@RequestMapping(value="/delbyid", method=RequestMethod.DELETE)
	public @ResponseBody User deleteUser(@RequestParam long id) {		
		try {
			User userModel;
			userModel = this.userDao.getById(id);
			this.userDao.deleteUser(userModel);
			return userModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteUser(@RequestBody User userModel) {
		try {
			this.userDao.deleteUser(userModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getbyusername", method=RequestMethod.GET)
	public User getByUsername(@RequestParam String username) {
		try {
			return this.userDao.getByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/getbyid", method=RequestMethod.GET)
	public User getById(@RequestParam long id) {
		try {
			return this.userDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getuserbyid", method=RequestMethod.GET)
	public List<User> getUserById(@RequestParam long id) {
		try {
			return this.userDao.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getuserbyusername", method=RequestMethod.GET)
	public List<User> getUserByUsername(@RequestParam String username) {
		try {
			return this.userDao.getUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
