/**
 *
 */
package com.banistmo.auth.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.banistmo.auth.jpa.util.Constant;

public class SGEntityManagerFactory {

	private static SGEntityManagerFactory instance;
	protected static volatile EntityManager em;
	private static EntityManagerFactory emFactorySesion;

	private SGEntityManagerFactory() throws Exception {
		try {
			emFactorySesion = Persistence.createEntityManagerFactory(Constant.PERSISTENCE_ID);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public static SGEntityManagerFactory getInstance() throws Exception {
		if (instance == null) {
			instance = new SGEntityManagerFactory();
		}
		return instance;
	}

	public EntityManager getManager(int type) {
		EntityManager em = null;
		em = getEntityManager(emFactorySesion);
		return em;
	}

	private EntityManager getEntityManager(EntityManagerFactory emFactory) {
		if (emFactory.isOpen()) {
			return emFactory.createEntityManager();
		} else {
			emFactory = Persistence.createEntityManagerFactory(Constant.PERSISTENCE_ID);
			return emFactory.createEntityManager();
		}
	}
}
