package com.banistmo.auth.web.dto;

import java.util.Date;

public class DataTransactionFilter {
	private String merchant;
	private String terminalId;
	private String rrnTokenTrans;
	private Date tranDateFrom;
	private Date tranDateTo;
	private Integer lotNo;

	public DataTransactionFilter() {
		super();
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getRrnTokenTrans() {
		return rrnTokenTrans;
	}

	public void setRrnTokenTrans(String rrnTokenTrans) {
		this.rrnTokenTrans = rrnTokenTrans;
	}

	public Date getTranDateFrom() {
		return tranDateFrom;
	}

	public void setTranDateFrom(Date tranDateFrom) {
		this.tranDateFrom = tranDateFrom;
	}

	public Date getTranDateTo() {
		return tranDateTo;
	}

	public void setTranDateTo(Date tranDateTo) {
		this.tranDateTo = tranDateTo;
	}

	public Integer  getLotNo() {
		return lotNo;
	}

	public void setLotNo(Integer  lotNo) {
		this.lotNo = lotNo;
	}
}
