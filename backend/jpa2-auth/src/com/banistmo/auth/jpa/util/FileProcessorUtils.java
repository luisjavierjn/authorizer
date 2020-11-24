package com.banistmo.auth.jpa.util;

import com.banistmo.auth.jpa.entity.Merchant;
import com.banistmo.auth.web.dto.DataTransactionModel;

public class FileProcessorUtils {

	public boolean checkFileRows(String line) {
		String[] values = line.split(Constant.COMMA);
		return (values.length != 10) ? false : true;
	}

	public boolean checkHeaderFile(String line) {
		String[] values = line.split(Constant.COMMA);
		for (int i = 0; i < values.length; i++) {
			if (values[i].equalsIgnoreCase(Constant.headers[i])) {
				System.out.println("IN: " + values[i]);
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public Merchant checkFileContent(String data) throws Exception {
		String[] values = data.split(Constant.COMMA);
//		DataTransactionModel modelo = new DataTransactionModel();
		Merchant modelo = new Merchant();
		modelo.setMerchantId(values[0]);
		modelo.setMerchantName(values[1]);
		modelo.setAccountHoganNo(values[2]);
		modelo.setRucNo(values[3]);
		modelo.setCheckDigit(values[4]);
		modelo.setPercentageRetention(values[5]);
		modelo.setCommission(values[6]);
		modelo.setCommissionItbms(values[7]);
		modelo.setChannel(Integer.parseInt(values[8]));
		modelo.setStatus(Integer.parseInt(values[9]));
		return modelo;
	}

}
