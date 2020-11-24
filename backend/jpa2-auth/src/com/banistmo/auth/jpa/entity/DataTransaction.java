package com.banistmo.auth.jpa.entity;

// Generated Sep 16, 2016 5:09:22 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//FIXME se deben reestablecer las relaciones con las otras entidades merchant channel, operationStatus

@Entity
@Table(name = "DATA_TRANSACTION")
public class DataTransaction {

	/**
	 *
	 */
	private BigDecimal id;
	private String merchant;
	private long channel;
	private long operationStatus;
	private String terminalId;
	private String rrnTokenTrans;
	private Timestamp tranDate;
	private int lotNo;
	private int documentNo;
	private BigDecimal tranAmount;
	private BigDecimal tranTaxAmount;
	private BigDecimal tranTipAmount;
	private String acctCustomer;
	private String acctCountable;
	private Timestamp processDate;
	private Timestamp lastUpdate;

	public DataTransaction() {
		super();
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 25, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "MERCHANT_ID", nullable = false, precision = 15)
	public String getMerchant() {
		return this.merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	@Column(name = "CHANNEL_TRANS_ID", nullable = false)
	public long getChannel() {
		return this.channel;
	}

	public void setChannel(long channel) {
		this.channel = channel;
	}

	@Column(name = "TRAN_STATUS", nullable = false)
	public long getOperationStatus() {
		return this.operationStatus;
	}

	public void setOperationStatus(long operationStatus) {
		this.operationStatus = operationStatus;
	}

	@Column(name = "TERMINAL_ID", nullable = false, length = 12)
	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "RRN_TOKEN_TRANS", nullable = false, length = 12)
	public String getRrnTokenTrans() {
		return this.rrnTokenTrans;
	}

	public void setRrnTokenTrans(String rrnTokenTrans) {
		this.rrnTokenTrans = rrnTokenTrans;
	}

	@Column(name = "TRAN_DATE", nullable = false)
	public Timestamp getTranDate() {
		return tranDate;
	}

	public void setTranDate(Timestamp tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "LOT_NO", nullable = false, precision = 6, scale = 0)
	public int getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(int lotNo) {
		this.lotNo = lotNo;
	}

	@Column(name = "DOCUMENT_NO", nullable = false, precision = 6, scale = 0)
	public int getDocumentNo() {
		return this.documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}

	@Column(name = "TRAN_AMOUNT", nullable = false, precision = 10)
	public BigDecimal getTranAmount() {
		return this.tranAmount;
	}

	public void setTranAmount(BigDecimal tranAmount) {
		this.tranAmount = tranAmount;
	}

	@Column(name = "TRAN_TAX_AMOUNT", nullable = false, precision = 10)
	public BigDecimal getTranTaxAmount() {
		return this.tranTaxAmount;
	}

	public void setTranTaxAmount(BigDecimal tranTaxAmount) {
		this.tranTaxAmount = tranTaxAmount;
	}

	@Column(name = "TRAN_TIP_AMOUNT", nullable = false, precision = 10)
	public BigDecimal getTranTipAmount() {
		return this.tranTipAmount;
	}

	public void setTranTipAmount(BigDecimal tranTipAmount) {
		this.tranTipAmount = tranTipAmount;
	}

	@Column(name = "ACCT_CUSTOMER", nullable = false, length = 20)
	public String getAcctCustomer() {
		return this.acctCustomer;
	}

	public void setAcctCustomer(String acctCustomer) {
		this.acctCustomer = acctCustomer;
	}

	@Column(name = "ACCT_COUNTABLE", nullable = false, length = 20)
	public String getAcctCountable() {
		return this.acctCountable;
	}

	public void setAcctCountable(String acctCountable) {
		this.acctCountable = acctCountable;
	}

	@Column(name = "PROCESS_DATE", nullable = false)
	public Timestamp getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	@Column(name = "LAST_UPDATE")
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
