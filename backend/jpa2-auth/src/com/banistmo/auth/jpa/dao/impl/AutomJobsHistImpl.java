package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IAutomJobsHistDao;
import com.banistmo.auth.jpa.entity.AutomJobsHistModel;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("automJobsHistDao")
public class AutomJobsHistImpl implements IAutomJobsHistDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<AutomJobsHistModel> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from AutomJobsHistModel").setFirstResult(0).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public AutomJobsHistModel getById(long id) throws Exception {

		EntityManager em = null;
		AutomJobsHistModel automJobsHistModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			automJobsHistModel = (AutomJobsHistModel) em.createQuery("from AutomJobsHistModel c where c.id LIKE :id").setParameter("id", id)
					.getSingleResult();
			return automJobsHistModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AutomJobsHistModel> getByDateRange(String dateStart, String dateEnd) throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from AutomJobsHistModel c where c.ex_date >= to_timestamp(:start,'dd-mm-yyyy') and c.ex_date < to_timestamp(:end,'dd-mm-yyyy')")
					.setParameter("start", dateStart)
					.setParameter("end", dateEnd)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
}
