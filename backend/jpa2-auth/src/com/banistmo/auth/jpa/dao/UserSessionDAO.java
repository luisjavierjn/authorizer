package com.banistmo.auth.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.banistmo.auth.jpa.entity.UserSession;

public interface UserSessionDAO {
	/**
	 * Create user session
	 *
	 * @param session
	 * @throws Exception
	 */
	public void putSesion(UserSession session) throws Exception;

	/**
	 * Update the session user
	 *
	 * @param session
	 * @param isValid
	 * @throws Exception
	 */
	public void updateSesion(UserSession session, int isValid) throws Exception;

	/**
	 * Validate session
	 *
	 * @param userSession
	 * @param sessionCookie
	 * @return true if the session is valide
	 * @throws NoResultException
	 * @throws Exception
	 */
	public boolean isValidSesion(String userSession, String sessionCookie) throws NoResultException, Exception;

	/**
	 * Get Session by user and sessionCookie
	 *
	 * @param userSession
	 * @param sessionCookie
	 * @return Entity Session
	 * @throws Exception
	 */
	public UserSession getSession(String userSession, String sessionCookie) throws Exception;

	/**
	 * Search user with active session
	 *
	 * @return List<UserSession>
	 * @throws Exception
	 */
	public List<UserSession> findSesionActiva() throws Exception;

	/**
	 * Found the list of sessionCookies for user
	 *
	 * @param EntityManager
	 * @param sesionUsuario
	 * @param sesionCookie
	 * @return List<UserSession>
	 * @throws Exception
	 */
	public List<UserSession> findSesionActivaBySesionId(EntityManager em, String sesionUsuario, String sesionCookie)
			throws Exception;

	/**
	 * Delete the session of the user
	 *
	 * @param session
	 * @param EntityManager
	 * @throws Exception
	 */
	public void deleteSession(UserSession session, EntityManager em) throws Exception;
}
