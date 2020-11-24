package com.banistmo.auth.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchant")
public class Merchant {

	@Id
	@Column(name="merchant_id",nullable=false,precision=15)
	private String merchantId;

	@Column(name="merchant_name",nullable=false,precision=50)
	private String merchantName;

	@Column(name="account_hogan_no",nullable=false,precision=20)
	private String accountHoganNo;

	@Column(name="ruc_no",nullable=false,precision=30)
	private String rucNo;

	@Column(name="check_digit",nullable=false,precision=10)
	private String checkDigit;

	@Column(name="percentage_retention",nullable=false,precision=10)
	private String percentageRetention;

	@Column(name="commission",nullable=false,precision=15)
	private String commission;

	@Column(name="commission_itbms",nullable=false,precision=15)
	private String commissionItbms;

	@Column(name="channel",nullable=false)
	private long channel;

	@Column(name="status")
	private long status;

	public Merchant() {
		super();
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getAccountHoganNo() {
		return accountHoganNo;
	}

	public void setAccountHoganNo(String accountHoganNo) {
		this.accountHoganNo = accountHoganNo;
	}

	public String getRucNo() {
		return rucNo;
	}

	public void setRucNo(String rucNo) {
		this.rucNo = rucNo;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	public String getPercentageRetention() {
		return percentageRetention;
	}

	public void setPercentageRetention(String percentageRetention) {
		this.percentageRetention = percentageRetention;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCommissionItbms() {
		return commissionItbms;
	}

	public void setCommissionItbms(String commissionItbms) {
		this.commissionItbms = commissionItbms;
	}

	public long getChannel() {
		return channel;
	}

	public void setChannel(long channel) {
		this.channel = channel;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
}

