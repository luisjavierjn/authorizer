package com.banistmo.auth.jpa.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IDataTransactionDao;
import com.banistmo.auth.jpa.entity.DataTransaction;
import com.banistmo.auth.jpa.util.Constant;
import com.banistmo.auth.web.dto.DataTransactionFilter;

@Repository
@Component("dataTransactionDao")
public class DataTransactionDaoImpl implements IDataTransactionDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<DataTransaction> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from DataTransaction order by TRAN_DATE desc")
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DataTransaction> getByName(String name) throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from DataTransaction c where c.dataTransaction_name LIKE :name")
					.setParameter("name", name).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public DataTransaction getById(String id) throws Exception {

		EntityManager em = null;
		DataTransaction dataTransactionModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			dataTransactionModel = (DataTransaction) em
					.createQuery("from DataTransaction c where c.dataTransaction_id LIKE :id").setParameter("id", id)
					.getSingleResult();
			return dataTransactionModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<DataTransaction> getFilteredData(DataTransactionFilter dataTransactionFilter) {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Query query = buildQuery(dataTransactionFilter, em);
		query.setFirstResult(0);
		return query.getResultList();
	}

	private Query buildQuery(DataTransactionFilter dataTransactionFilter, EntityManager em) {
		Query query;
		String filter = StringUtils.EMPTY;
		HashMap<String, Object> testMap = new HashMap<String, Object>(5);

		if (dataTransactionFilter.getMerchant() != null) {
			filter = filter.concat(" merchant like :merchant");
			testMap.put("merchant", dataTransactionFilter.getMerchant());
		}
		if (dataTransactionFilter.getTerminalId() != null) {
			filter = filter.concat(" and terminalId like :terminalId");
			testMap.put("terminalId", dataTransactionFilter.getTerminalId());
		}
		if (dataTransactionFilter.getRrnTokenTrans() != null) {
			filter = filter.concat(" and rrnTokenTrans like :rrnTokenTrans");
			testMap.put("rrnTokenTrans", dataTransactionFilter.getRrnTokenTrans());
		}
		if (dataTransactionFilter.getLotNo() != null) {
			filter = filter.concat(" and lotNo = :lotNo");
			testMap.put("lotNo", dataTransactionFilter.getLotNo());
		}
		if (dataTransactionFilter.getTranDateFrom() != null) {
			filter = filter.concat(" and tranDate >= :tranDateFrom");
			testMap.put("tranDateFrom", dataTransactionFilter.getTranDateFrom());
		}
		if (dataTransactionFilter.getTranDateTo() != null) {
			filter = filter.concat(" and tranDate <= :tranDateTo");
			testMap.put("tranDateTo", dataTransactionFilter.getTranDateTo());
		}

		query = em.createQuery("from DataTransaction where".concat(StringUtils.removeStart(filter, " and")));

		Iterator it = testMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry parameter = (Map.Entry) it.next();
			if (parameter.getValue() instanceof Date) {
				query.setParameter(parameter.getKey().toString(), (Date) parameter.getValue(), TemporalType.TIMESTAMP);
			} else {
				query.setParameter(parameter.getKey().toString(), parameter.getValue());
			}
			it.remove(); // avoids a ConcurrentModificationException
		}
		return query;
	}

}
