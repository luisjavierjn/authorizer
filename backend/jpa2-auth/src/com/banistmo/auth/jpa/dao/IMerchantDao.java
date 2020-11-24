package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.Merchant;

public interface IMerchantDao {

	public List<Merchant> getAll() throws Exception;

	public void insertMerchant(Merchant merchantModel) throws Exception;

	public void updateMerchant(Merchant merchantModel) throws Exception;

	public void deleteMerchant(Merchant merchantModel) throws Exception;

	public List<Merchant> getByMerchant_name(String merchant_name) throws Exception;

	public Merchant getById(String merchant_id) throws Exception;

	public List<Merchant> getMerchantById(String merchant_id) throws Exception;

	public void deleteMerchant(String merchant_id) throws Exception;
}
