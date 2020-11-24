package com.banistmo.auth.jpa.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.banistmo.auth.jpa.dao.UserSessionDAO;
import com.banistmo.auth.jpa.entity.UserSession;
import com.banistmo.auth.jpa.util.Constant;

public class UserSessionDAOImpl implements UserSessionDAO {

	private static UserSessionDAOImpl instance;

	private UserSessionDAOImpl() {
	}

	public static UserSessionDAOImpl getInstance() {
		if (instance == null) {
			synchronized (UserSessionDAOImpl.class) {
				if (null == instance) {
					instance = new UserSessionDAOImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public synchronized void putSesion(UserSession session) throws Exception {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			em.getTransaction().begin();
			List<UserSession> sessionList = this.findSesionActivaBySesionIdBatch(session.getSesionId(), em);
			for (UserSession userSession : sessionList) {
				try {
					this.deleteSessionBatch(userSession, em);
				} catch (Exception e) {
					throw e;
				}
			}
			em.persist(session);
			em.getTransaction().commit();

		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw ex;
		} finally {
			if (em.isOpen()) {
				em.clear();
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private synchronized List<UserSession> findSesionActivaBySesionIdBatch(String userSession, EntityManager emr)
			throws Exception {
		try {
			String strQuery = "SELECT p FROM UserSession p WHERE p.sesionId = :sesionID ";
			if (!emr.isOpen()) {
				emr = Persistence.createEntityManagerFactory("jpa-enrolamiento").createEntityManager();
			}
			Query query = emr.createQuery(strQuery);
			query.setParameter("sesionID", userSession);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	private void deleteSessionBatch(UserSession userSession, EntityManager emr) throws Exception {
		try {
			if (!emr.isOpen()) {
				emr = Persistence.createEntityManagerFactory("jpa-enrolamiento").createEntityManager();
			}
			emr.remove(userSession);
		} catch (Exception ex) {
			if (emr.getTransaction().isActive()) {
				emr.getTransaction().rollback();
			}
			throw ex;
		}
	}

	@Override
	public synchronized void updateSesion(UserSession sesion,  int isValid) throws Exception {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			List<UserSession> sesiones = this.findSesionActivaBySesionId(em, sesion.getSesionId(), sesion.getSesscookie());
			if (sesiones.size() > 1)
				throw new Exception("Multiplesession Exist for the SesionID:" + sesion.getSesionId());
			else if (sesiones.size() == 0) {
				sesion.setActiveSession(new BigDecimal(isValid));
				sesion.setTimestampStart(new Timestamp(new Date().getTime()));
				em.getTransaction().begin();
				em.persist(sesion);
			} else {
				sesiones.get(0).setActiveSession(new BigDecimal(isValid));
				sesiones.get(0).setTimestampStart(new Timestamp(new Date().getTime()));
				em.getTransaction().begin();
				em.merge(sesiones.get(0));
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw ex;
		} finally {
			if (em != null && em.isOpen()) {
				em.clear();
				em.close();
			}
		}
	}

	@Override
	public synchronized boolean isValidSesion(String userSession, String sessionCookie) throws Exception {
		EntityManager em = null;
		try {
			String strQuery = "SELECT p FROM UserSession p WHERE p.sesionId = :userSession "
					+ "AND p.sesscookie = :sesionCookie ";
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			Query query = em.createQuery(strQuery);
			query.setParameter("userSession", userSession);
			query.setParameter("sesionCookie", sessionCookie);
			UserSession msg = (UserSession) query.getSingleResult();
			if (msg.getActiveSession().intValue() == 1) {
				return true;
			}
			return false;
		} catch (NoResultException e) {
			return false;
		} catch (Exception exc) {
			throw new Exception(exc);
		} finally {
			if (em != null && em.isOpen()) {
				em.clear();
				em.close();
			}
		}
	}

	@Override
	public synchronized UserSession getSession(String userSession, String sesionCookie) throws Exception {
		EntityManager em = null;
		try {
			String strQuery = "SELECT p FROM UserSession p WHERE p.accountUser = :userSession "
					+ "AND p.sesscookie = :sesionCookie ";
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			Query query = em.createQuery(strQuery);
			query.setParameter("userSession", userSession);
			query.setParameter("sesionCookie", sesionCookie);
			UserSession ses = (UserSession) query.getSingleResult();
			return ses;
		} catch (Exception exc) {
			throw new Exception(exc);
		}
	}

	@Override
	public synchronized void deleteSession(UserSession sesion, EntityManager em) throws Exception {
		try {
			em.remove(sesion);
		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw ex;
		}
	}

	public synchronized void deleteSesion(String userid, String sescokkie) {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			em.getTransaction().begin();
			String strQuery = "DELETE FROM UserSession p WHERE p.sesionId = :userid and p.sesscookie = :sescokkie";
			Query query = em.createQuery(strQuery);
			query.setParameter("userid", userid);
			query.setParameter("sescokkie", sescokkie);
			query.executeUpdate();
			em.getTransaction().commit();

		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			ex.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.clear();
				em.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<UserSession> findSesionActivaBySesionId(EntityManager em, String sesionUsuario,
			String sesionCookie) throws Exception {
		try {
			String strQuery = "SELECT p FROM UserSession p WHERE p.sesionId = :sesionID "
					+ "AND p.sesscookie = :sesscookie";
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			Query query = em.createQuery(strQuery);
			query.setParameter("sesionID", sesionUsuario);
			query.setParameter("sesscookie", sesionCookie);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<UserSession> findSesionActiva() throws Exception {
		EntityManager em = null;
		try {
			String strQuery = "SELECT p FROM UserSession p WHERE p.activeSession = 1";
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			Query query = em.createQuery(strQuery);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	private synchronized List<UserSession> findSesionActivaBySesionId(String sesionUsuario) throws Exception {
		EntityManager em = null;
		try {
			String strQuery = "SELECT p FROM UserSession p WHERE p.sesionId = :sesionID ";
			em = SGEntityManagerFactory.getInstance().getManager(Constant.SESSION_TYPE);
			Query query = em.createQuery(strQuery);
			query.setParameter("sesionID", sesionUsuario);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

}
