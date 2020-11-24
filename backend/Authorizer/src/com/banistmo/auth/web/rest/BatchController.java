package com.banistmo.auth.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IBatchRecordHistDao;
import com.banistmo.auth.jpa.entity.BatchRecordHistModel;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

	@Autowired
	@Qualifier("batchRecordHistDao")
	private IBatchRecordHistDao batchRecordHistDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getallrecordhist", method=RequestMethod.GET)
	public List<BatchRecordHistModel> getAllRecordHist() {
		try {
			return this.batchRecordHistDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/getrecordhistbyid", method=RequestMethod.GET)
	public BatchRecordHistModel getRecordHistById(@RequestParam long id) {
		try {
			return this.batchRecordHistDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
