package com.banistmo.auth.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IAutomJobsHistDao;
import com.banistmo.auth.jpa.entity.AutomJobsHistModel;

@RestController
@RequestMapping("/api/automjobs")
public class AutomJobsController {

	@Autowired
	@Qualifier("automJobsHistDao")
	private IAutomJobsHistDao automJobsHistDao;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getallautomjobshist", method=RequestMethod.GET)
	public List<AutomJobsHistModel> getAllAutomJobsHist() {
		try {
			return this.automJobsHistDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/getautomjobshistbyid", method=RequestMethod.GET)
	public AutomJobsHistModel getAutomJobsHistById(@RequestParam long id) {
		try {
			return this.automJobsHistDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getautomjobshistbydaterange", method=RequestMethod.GET)
	public List<AutomJobsHistModel> getAutomJobsHistByDateRange(@RequestParam String dateStart, @RequestParam String dateEnd) {
		try {
			return this.automJobsHistDao.getByDateRange(dateStart,dateEnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
