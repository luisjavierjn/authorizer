package com.banistmo.auth.web.dto;

public class DataTransactionModel {

	private String MERCHANT_ID;
	private String MERCHANT_NAME;
	private String ACCOUNT_HOGAN_NO;
	private String RUC_NO;
	private String CHECK_DIGIT;
	private String PERCENTAGE_RETENTION;
	private String COMMISSION;
	private String COMMISSION_ITBMS;
	private long CHANNEL;

	public String getMERCHANT_ID() {
		return MERCHANT_ID;
	}

	public void setMERCHANT_ID(String mERCHANT_ID) {
		MERCHANT_ID = mERCHANT_ID;
	}

	public String getMERCHANT_NAME() {
		return MERCHANT_NAME;
	}

	public void setMERCHANT_NAME(String mERCHANT_NAME) {
		MERCHANT_NAME = mERCHANT_NAME;
	}

	public String getACCOUNT_HOGAN_NO() {
		return ACCOUNT_HOGAN_NO;
	}

	public void setACCOUNT_HOGAN_NO(String aCCOUNT_HOGAN_NO) {
		ACCOUNT_HOGAN_NO = aCCOUNT_HOGAN_NO;
	}

	public String getRUC_NO() {
		return RUC_NO;
	}

	public void setRUC_NO(String rUC_NO) {
		RUC_NO = rUC_NO;
	}

	public String getCHECK_DIGIT() {
		return CHECK_DIGIT;
	}

	public void setCHECK_DIGIT(String cHECK_DIGIT) {
		CHECK_DIGIT = cHECK_DIGIT;
	}

	public String getPERCENTAGE_RETENTION() {
		return PERCENTAGE_RETENTION;
	}

	public void setPERCENTAGE_RETENTION(String pERCENTAGE_RETENTION) {
		PERCENTAGE_RETENTION = pERCENTAGE_RETENTION;
	}

	public String getCOMMISSION() {
		return COMMISSION;
	}

	public void setCOMMISSION(String cOMMISSION) {
		COMMISSION = cOMMISSION;
	}

	public String getCOMMISSION_ITBMS() {
		return COMMISSION_ITBMS;
	}

	public void setCOMMISSION_ITBMS(String cOMMISSION_ITBMS) {
		COMMISSION_ITBMS = cOMMISSION_ITBMS;
	}

	public long getCHANNEL() {
		return CHANNEL;
	}

	public void setCHANNEL(long cHANNEL) {
		CHANNEL = cHANNEL;
	}

}
