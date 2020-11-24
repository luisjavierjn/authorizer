package com.banistmo.auth.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IChannelDao;
import com.banistmo.auth.jpa.entity.Channel;
import com.banistmo.auth.jpa.util.Constant;

@Repository
@Component("channelDao")
public class ChannelDaoImpl implements IChannelDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Channel> getAll() throws Exception {

		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			return em.createQuery("from Channel").getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insertChannel(Channel channelModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.persist(channelModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void updateChannel(Channel channelModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			em.merge(channelModel);
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public void deleteChannel(Channel channelModel) throws Exception {

		EntityManager em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
		EntityTransaction utx = em.getTransaction();
		try {
			utx.begin();
			if (em.contains(channelModel))
				em.remove(channelModel);
			else
				em.remove(em.merge(channelModel));
			utx.commit();
		} catch (Exception e) {
			utx.rollback();
			throw e;
		}
	}

	@Override
	public Channel getByName(String name) throws Exception {

		EntityManager em = null;
		Channel channelModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			channelModel = (Channel) em.createQuery("from Channel c where c.name LIKE :custName")
					.setParameter("custName", name)
					.getSingleResult();
			return channelModel;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Channel getById(long id) throws Exception {

		EntityManager em = null;
		Channel channelModel = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			channelModel = (Channel) em.createQuery("from Channel c where c.id LIKE :id")
					.setParameter("id", id)
					.getSingleResult();
			return channelModel;
		} catch (Exception e) {
			throw e;
		}
	}
}
