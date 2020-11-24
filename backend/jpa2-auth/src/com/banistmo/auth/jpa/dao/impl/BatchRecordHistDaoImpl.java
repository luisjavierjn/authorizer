package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IBatchRecordHistDao;
import com.banistmo.auth.jpa.entity.BatchRecordHistModel;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("batchRecordHistDao")
public class BatchRecordHistDaoImpl implements IBatchRecordHistDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<BatchRecordHistModel> getAll() throws Exception {
		
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from BatchRecordHistModel c order by c.wr_date desc").getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public BatchRecordHistModel getById(long id) throws Exception {
		
		EntityManager em = null;
		BatchRecordHistModel batchHistModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			batchHistModel = (BatchRecordHistModel) em.createQuery("from BatchRecordHistModel c where c.id LIKE :id").setParameter("id", id)
					.getSingleResult();
			return batchHistModel;
		} catch (Exception e) {
			throw e;
		}	
	}

}
