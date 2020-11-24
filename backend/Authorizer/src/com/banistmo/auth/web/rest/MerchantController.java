package com.banistmo.auth.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IMerchantDao;
import com.banistmo.auth.jpa.dao.impl.MerchantDaoImpl;
import com.banistmo.auth.jpa.entity.Merchant;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

	@Autowired
	@Qualifier("merchantDao")
	private IMerchantDao merchantDao;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public List<Merchant> getAll() {
		try {
			return this.merchantDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody Merchant insertMerchant(@RequestBody Merchant merchantModel) {
		try {
			this.merchantDao.insertMerchant(merchantModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantModel;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody Merchant updateMerchant(@RequestBody Merchant merchantModel) {
		try {
			this.merchantDao.updateMerchant(merchantModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merchantModel;
	}	
	
	@RequestMapping(value="/delbyid", method=RequestMethod.DELETE)
	public @ResponseBody Merchant deleteMerchant(@RequestParam String merchant_id) {		
		try {
			Merchant merchantModel;
			merchantModel = this.merchantDao.getById(merchant_id);
			this.merchantDao.deleteMerchant(merchant_id);
			return merchantModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}	
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteMerchant(@RequestBody Merchant merchantModel) {
		try {
			this.merchantDao.deleteMerchant(merchantModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@RequestMapping(value="/getbyname", method=RequestMethod.GET)
	public List<Merchant> getByMerchant_name(@RequestParam String merchant_name) {
		try {
			return this.merchantDao.getByMerchant_name(merchant_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@RequestMapping(value="/getbyid", method=RequestMethod.GET)
	public Merchant getById(@RequestParam String merchant_id) {
		try {
			return this.merchantDao.getById(merchant_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@RequestMapping(value="/getmerchantbyid", method=RequestMethod.GET)
	public List<Merchant> getMerchantById(@RequestParam String merchant_id) {
		try {
			return this.merchantDao.getMerchantById(merchant_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}		
}
