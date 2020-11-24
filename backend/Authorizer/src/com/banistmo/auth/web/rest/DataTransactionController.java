package com.banistmo.auth.web.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IDataTransactionDao;
import com.banistmo.auth.jpa.dao.IOperationParamDao;
import com.banistmo.auth.jpa.entity.DataTransaction;
import com.banistmo.auth.jpa.entity.OperationParam;
import com.banistmo.auth.jpa.layout.LayoutDataTransaction;
import com.banistmo.auth.util.constants.CVSParameters;
import com.banistmo.auth.util.exportfile.Export;
import com.banistmo.auth.util.exportfile.ExportFactory;
import com.banistmo.auth.web.dto.DataTransactionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/datatransaction")
public class DataTransactionController {

	@Autowired
	@Qualifier("dataTransactionDao")
	private IDataTransactionDao dataTransactionDao;

	@Autowired
	@Qualifier("operationParamDao")
	private IOperationParamDao operationParamDao;

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<DataTransaction> getAll() {
		try {
			return this.dataTransactionDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getbyname", method = RequestMethod.GET)
	public List<DataTransaction> getByName(@RequestParam String name) {
		try {
			return this.dataTransactionDao.getByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public DataTransaction getById(@RequestParam String id) {
		try {
			return this.dataTransactionDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getfiltereddata", method = RequestMethod.POST)
	public List<DataTransaction> getFilterData(@RequestBody DataTransactionFilter dataTransactionFilter) {
		try {
			return this.dataTransactionDao.getFilteredData(dataTransactionFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/generatecvsfile", method = RequestMethod.POST)
	public String generateCVSFile(@RequestBody DataTransactionFilter dataTransactionFilter) {
		try {
			String valores = doGenerateCVSFile(dataTransactionFilter);
			return toJson(valores);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String doGenerateCVSFile(DataTransactionFilter dataTransactionFilter) {
		ArrayList<LayoutDataTransaction> objlistDataTransaction = new ArrayList<LayoutDataTransaction>();
		try {
			OperationParam cvsFile = this.operationParamDao.getByName(CVSParameters.CVS_TEMP_FILE.getParamName());
			OperationParam cvsPath = this.operationParamDao.getByName(CVSParameters.CVS_TEMP_PATH.getParamName());
			Export exportfinaclecsvForAdm;
			List<DataTransaction> filteredData = this.dataTransactionDao.getFilteredData(dataTransactionFilter);
			for (DataTransaction dataTransaction : filteredData) {
				objlistDataTransaction.add(new LayoutDataTransaction(dataTransaction));
			}

			exportfinaclecsvForAdm = ExportFactory.getlayout(objlistDataTransaction,
					cvsPath.getOperation_value().concat(File.separator).concat(cvsFile.getOperation_value()));
			exportfinaclecsvForAdm.toExportCSV();
			return cvsPath.getOperation_value().concat(File.separator).concat(cvsFile.getOperation_value());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String toJson(Object data) {
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(mapper.writeValueAsString(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}

}
