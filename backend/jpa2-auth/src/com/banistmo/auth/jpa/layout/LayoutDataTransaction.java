package com.banistmo.auth.jpa.layout;

import org.apache.commons.lang3.StringUtils;

import com.banistmo.auth.jpa.entity.DataTransaction;

public class LayoutDataTransaction {

	private DataTransaction dataTransaction;

	public LayoutDataTransaction(DataTransaction dataTransaction) {
		this.dataTransaction = dataTransaction;
	}

	public String getMerchantIdWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getMerchant())).concat("\'");
	}

	public String getTerminalIdWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getTerminalId())).concat("\'");
	}

	public String getRrnTokenTransWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getRrnTokenTrans())).concat("\'");
	}

	public String getTranDateWrite() {
		return "\'".concat(dataTransaction.getTranDate().toString()).concat("\'");
	}

	public String getChannelTransIdWrite() {
		return "\'".concat(StringUtils.trimToEmpty(Long.toString(dataTransaction.getChannel()))).concat("\'");
	}

	public String getLotNoWrite() {
		return "\'".concat(StringUtils.trimToEmpty(Integer.toString(dataTransaction.getLotNo()))).concat("\'");
	}

	public String getDocumentNoWrite() {
		return "\'".concat(StringUtils.trimToEmpty(Integer.toString(dataTransaction.getDocumentNo()))).concat("\'");
	}

	public String getTranStatusWrite() {
		return "\'".concat(StringUtils.trimToEmpty(Long.toString(dataTransaction.getOperationStatus()))).concat("\'");
	}

	public String getTranAmountWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getTranAmount().toString())).concat("\'");
	}

	public String getTranTaxAmountWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getTranTaxAmount().toString())).concat("\'");
	}

	public String getTranTipAmountWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getTranTipAmount().toString())).concat("\'");
	}

	public String getAcctCustomerWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getAcctCustomer())).concat("\'");
	}

	public String getAcctCountableWrite() {
		return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getAcctCountable())).concat("\'");
	}

	public String getProcessDateWrite() {
		if (dataTransaction.getProcessDate() != null) {
			return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getProcessDate().toString())).concat("\'");
		} else {
			return "\'\'";
		}
	}

	public String getLastUpdate() {
		if (dataTransaction.getLastUpdate() != null) {
			return "\'".concat(StringUtils.trimToEmpty(dataTransaction.getLastUpdate().toString())).concat("\'");
		} else {
			return "\'\'";
		}
	}

}
