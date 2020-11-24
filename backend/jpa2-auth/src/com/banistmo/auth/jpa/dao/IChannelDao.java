package com.banistmo.auth.jpa.dao;

import java.util.List;

import com.banistmo.auth.jpa.entity.Channel;;

public interface IChannelDao {

	public List<Channel> getAll() throws Exception;
	
	public void insertChannel(Channel channelModel) throws Exception;
	
	public void updateChannel(Channel channelModel) throws Exception;
	
	public void deleteChannel(Channel channelModel) throws Exception;

	public Channel getByName(String name) throws Exception;
	
	public Channel getById(long id) throws Exception;
}