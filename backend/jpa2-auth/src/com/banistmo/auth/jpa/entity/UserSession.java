package com.banistmo.auth.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * The persistent class for the USER_SESSIONS database table.
 *
 */
@Entity
@Table(name = "USER_SESSIONS")
@NamedQuery(name = "UserSession.findAll", query = "SELECT s FROM UserSession s")
public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USER_SESSIONS_PK_GENERATOR", sequenceName = "USER_SESSIONS_S")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SESSIONS_PK_GENERATOR")
	private long pk;

	@Column(name = "CHANNEL")
	private String channel;

	@Column(name = "ACCOUNT_USER")
	private String accountUser;

	@Column(name = "ACTIVE_SESSION")
	private BigDecimal activeSession;

	@Column(name = "SESION_ID")
	private String sesionId;

	@Column(name = "SESSCOOKIE")
	private String sesscookie;

	@Column(name = "TIMESTAMP_START")
	private Timestamp timestampStart;

	public long getPk() {
		return this.pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public String getSesionId() {
		return this.sesionId;
	}

	public void setSesionId(String sesionId) {
		this.sesionId = sesionId;
	}

	public String getSesscookie() {
		return this.sesscookie;
	}

	public void setSesscookie(String sesscookie) {
		this.sesscookie = sesscookie;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}

	public BigDecimal getActiveSession() {
		return activeSession;
	}

	public void setActiveSession(BigDecimal activeSession) {
		this.activeSession = activeSession;
	}

	public Timestamp getTimestampStart() {
		return timestampStart;
	}

	public void setTimestampStart(Timestamp timestampStart) {
		this.timestampStart = timestampStart;
	}

}