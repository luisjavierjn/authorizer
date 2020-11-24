package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IUserDao;
import com.banistmo.auth.jpa.entity.User;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("userDao")
public class UserDaoImpl implements IUserDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from User").getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insertUser(User userModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.persist(userModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void updateUser(User userModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin(); 
			em.merge(userModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void deleteUser(User userModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin(); 
			if (em.contains(userModel))
				em.remove(userModel);
			else
				em.remove(em.merge(userModel));
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public User getByUsername(String username) throws Exception {

		EntityManager em = null;
		User userModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			userModel = (User) em.createQuery("from User c where c.username LIKE :custName")
					.setParameter("custName", username).getSingleResult();
			return userModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User getById(long id) throws Exception {

		EntityManager em = null;
		User userModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			userModel = (User) em.createQuery("from User c where c.id LIKE :id")
					.setParameter("id", id)
					.getSingleResult();
			return userModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserById(long id) throws Exception {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from User c where c.id LIKE :id")
					.setParameter("id", id)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserByUsername(String username) throws Exception {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from User c where c.username LIKE :username")
					.setParameter("username", username)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public String getTest() throws Exception {
		return "Test Successful user!";
	}	
}
