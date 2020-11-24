package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IMerchantDao;
import com.banistmo.auth.jpa.entity.Merchant;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("merchantDao")
public class MerchantDaoImpl implements IMerchantDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Merchant> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from Merchant order by merchantName asc")
					.setFirstResult(0)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insertMerchant(Merchant merchantModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.persist(merchantModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void updateMerchant(Merchant merchantModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.merge(merchantModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void deleteMerchant(Merchant merchantModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			if (em.contains(merchantModel))
				em.remove(merchantModel);
			else
				em.remove(em.merge(merchantModel));
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void deleteMerchant(String merchant_id) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			Query query = em.createQuery("delete from Merchant c where c.merchantId LIKE :merchant_id")
			 .setParameter("merchant_id", merchant_id);
			query.executeUpdate();
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Merchant> getByMerchant_name(String merchant_name) throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from Merchant c where c.merchantName LIKE :merchant_name")
					.setParameter("merchant_name", merchant_name)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Merchant getById(String merchant_id) throws Exception {

		EntityManager em = null;
		Merchant merchantModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			merchantModel = (Merchant) em.createQuery("from Merchant c where c.merchantId LIKE :merchant_id")
					.setParameter("merchant_id", merchant_id)
					.getSingleResult();
			return merchantModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Merchant> getMerchantById(String merchant_id) throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from Merchant c where c.merchantId LIKE :merchant_id")
					.setParameter("merchant_id", merchant_id)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
}
