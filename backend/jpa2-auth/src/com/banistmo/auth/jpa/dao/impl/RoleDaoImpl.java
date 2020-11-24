package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IRoleDao;
import com.banistmo.auth.jpa.entity.Role;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("roleDao")
public class RoleDaoImpl implements IRoleDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from Role").getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insertRole(Role roleModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.persist(roleModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void updateRole(Role roleModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.merge(roleModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void deleteRole(Role roleModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin(); 
			if (em.contains(roleModel))
				em.remove(roleModel);
			else
				em.remove(em.merge(roleModel));
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public Role getByRolename(String rolename) throws Exception {

		EntityManager em = null;
		Role roleModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			roleModel = (Role) em.createQuery("from Role c where c.rolename LIKE :custName")
					.setParameter("custName", rolename).getSingleResult();
			return roleModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Role getById(long id) throws Exception {

		EntityManager em = null;
		Role roleModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			roleModel = (Role) em.createQuery("from Role c where c.id LIKE :id").setParameter("id", id)
					.getSingleResult();
			return roleModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String getTest() throws Exception {
		return "Test Successful role!";
	}
}
