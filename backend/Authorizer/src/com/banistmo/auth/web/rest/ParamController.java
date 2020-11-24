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

import com.banistmo.auth.jpa.dao.IOperationParamDao;
import com.banistmo.auth.jpa.entity.OperationParam;

@RestController
@RequestMapping("/api/param")
public class ParamController {

	@Autowired
	@Qualifier("operationParamDao")
	private IOperationParamDao operationParamDao;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getalloperationparams", method=RequestMethod.GET)
	public List<OperationParam> getAllOperationParams() {
		try {
			return this.operationParamDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getoperationparambyid", method=RequestMethod.GET)
	public OperationParam getOperationParamById(@RequestParam long id) {
		try {
			return this.operationParamDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@RequestMapping(value="/getoperationparambycode", method=RequestMethod.GET)
	public OperationParam getOperationParamByCode(@RequestParam String code) {
		try {
			return this.operationParamDao.getByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getoperationparambyname", method=RequestMethod.GET)
	public OperationParam getOperationParamByName(@RequestParam String name) {
		try {
			return this.operationParamDao.getByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getoperationparamlistbycodes", method=RequestMethod.GET)
	public List<OperationParam> getOperationParamListByCodes(@RequestParam List<String> codes) {
		try {
			return this.operationParamDao.getByCodeList(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/updateoperationparam", method=RequestMethod.PUT)
	public @ResponseBody OperationParam updateMerchant(@RequestBody OperationParam operationParamModel) {
		try {
			this.operationParamDao.updateOperationParam(operationParamModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operationParamModel;
	}	
}
