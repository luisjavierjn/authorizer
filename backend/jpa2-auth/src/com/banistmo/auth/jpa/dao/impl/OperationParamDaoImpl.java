package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IOperationParamDao;
import com.banistmo.auth.jpa.entity.OperationParam;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("operationParamDao")
public class OperationParamDaoImpl implements IOperationParamDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<OperationParam> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from OperationParam").getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public OperationParam getById(long id) throws Exception {

		EntityManager em = null;
		OperationParam operationParamModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			operationParamModel = (OperationParam) em.createQuery("from OperationParam c where c.id LIKE :id")
					.setParameter("id", id)
					.getSingleResult();
			return operationParamModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public OperationParam getByCode(String code) throws Exception {

		EntityManager em = null;
		OperationParam operationParamModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			operationParamModel = (OperationParam) em.createQuery("from OperationParam c where c.code LIKE :code")
					.setParameter("code", code)
					.getSingleResult();
			return operationParamModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public OperationParam getByName(String name) throws Exception {

		EntityManager em = null;
		OperationParam operationParamModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			operationParamModel = (OperationParam) em.createQuery("from OperationParam c where c.name LIKE :name")
					.setParameter("name", name)
					.getSingleResult();
			return operationParamModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OperationParam> getByCodeList(List<String> codes) throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from OperationParam c where c.code in (:ids)")
					.setParameter("ids", codes)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void updateOperationParam(OperationParam operationParamModel) throws Exception {
		
		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.merge(operationParamModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}	
}
