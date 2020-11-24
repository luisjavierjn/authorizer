package com.banistmo.auth.util.exportfile;

public interface Export {
	public void toExportCSV();
	public void toExportXLS();
	public void toExportXLSX();
	public void toExportPDF();
	public void toExportTXT()throws Exception;
}
