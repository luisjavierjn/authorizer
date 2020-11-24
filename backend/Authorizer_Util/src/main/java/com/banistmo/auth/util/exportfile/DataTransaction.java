package com.banistmo.auth.util.exportfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.banistmo.auth.jpa.layout.LayoutDataTransaction;
import com.csvreader.CsvWriter;

public class DataTransaction implements Export {
	public List<LayoutDataTransaction> listfinacle = null;
	public String outputFile;

	public DataTransaction(ArrayList<LayoutDataTransaction> listfinacle, String outputFile) {
		this.listfinacle = listfinacle;
		this.outputFile = outputFile;
	}

	@Override
	public void toExportCSV() {
		if (!getOutputFile().isEmpty()) {
			boolean alreadyExists = new File(outputFile).exists();
			if (alreadyExists) {
				File archivofinacol = new File(outputFile);
				archivofinacol.delete();
			}
			CsvWriter csvOutput = null;
			try {

				csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

				csvOutput.write("\'MERCHANT_ID\'");
				csvOutput.write("\'TERMINAL_ID\'");
				csvOutput.write("\'RRN_TOKEN_TRANS\'");
				csvOutput.write("\'TRAN_DATE\'");
				csvOutput.write("\'CHANNEL_TRANS_ID\'");
				csvOutput.write("\'LOT_NO\'");
				csvOutput.write("\'DOCUMENT_NO\'");
				csvOutput.write("\'TRAN_STATUS\'");
				csvOutput.write("\'TRAN_AMOUNT\'");
				csvOutput.write("\'TRAN_TAX_AMOUNT\'");
				csvOutput.write("\'TRAN_TIP_AMOUNT\'");
				csvOutput.write("\'ACCT_CUSTOMER\'");
				csvOutput.write("\'ACCT_COUNTABLE\'");
				csvOutput.write("\'PROCESS_DATE\'");
				csvOutput.write("\'LAST_UPDATE\'");
				csvOutput.endRecord();

				for (LayoutDataTransaction dataTransaction : getListfinacle()) {

					csvOutput.write(dataTransaction.getMerchantIdWrite());
					csvOutput.write(dataTransaction.getTerminalIdWrite());
					csvOutput.write(dataTransaction.getRrnTokenTransWrite());
					csvOutput.write(dataTransaction.getTranDateWrite());
					csvOutput.write(dataTransaction.getChannelTransIdWrite());
					csvOutput.write(dataTransaction.getLotNoWrite());
					csvOutput.write(dataTransaction.getDocumentNoWrite());
					csvOutput.write(dataTransaction.getTranStatusWrite());
					csvOutput.write(dataTransaction.getTranAmountWrite());
					csvOutput.write(dataTransaction.getTranTaxAmountWrite());
					csvOutput.write(dataTransaction.getTranTipAmountWrite());
					csvOutput.write(dataTransaction.getAcctCustomerWrite());
					csvOutput.write(dataTransaction.getAcctCountableWrite());
					csvOutput.write(dataTransaction.getProcessDateWrite());
					csvOutput.write(dataTransaction.getLastUpdate());

					csvOutput.endRecord();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				csvOutput.close();

			}
		}

	}

	@Override
	public void toExportXLS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void toExportXLSX() {
		// TODO Auto-generated method stub

	}

	@Override
	public void toExportPDF() {
		// TODO Auto-generated method stub

	}

	@Override
	public void toExportTXT() {
		// TODO Auto-generated method stub

	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public List<LayoutDataTransaction> getListfinacle() {
		return listfinacle;
	}

	public void setListfinacle(List<LayoutDataTransaction> listfinacle) {
		this.listfinacle = listfinacle;
	}
}
