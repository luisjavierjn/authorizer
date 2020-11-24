package com.banistmo.auth.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banistmo.auth.jpa.dao.IChannelDao;
import com.banistmo.auth.jpa.entity.Channel;

@RestController
@RequestMapping("/api/channels")
public class ChannelsController {

	@Autowired
	@Qualifier("channelDao")
	private IChannelDao channelDao;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public List<Channel> getAll() {
		try {
			return this.channelDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void insertChannel(@RequestBody Channel channelModel) {
		try {
			this.channelDao.insertChannel(channelModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateChannel(@RequestBody Channel channelModel) {
		try {
			this.channelDao.updateChannel(channelModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteChannel(@RequestBody Channel channelModel) {
		try {
			this.channelDao.deleteChannel(channelModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getbyname", method=RequestMethod.GET)
	public Channel getByName(@RequestParam String name) {
		try {
			return this.channelDao.getByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/getbyid", method=RequestMethod.GET)
	public Channel getById(@RequestParam long id) {
		try {
			return this.channelDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
