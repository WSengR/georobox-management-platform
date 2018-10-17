package com.geostar.georobox.management.module.plugmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.module.plugmanage.dao.PlugUserBeanMapper;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugExample;
import com.geostar.georobox.management.module.plugmanage.model.PlugUserBean;
import com.geostar.georobox.management.module.plugmanage.service.PlugUserService;

@Service
public class PlugUserServiceImpl implements PlugUserService{
	protected static Logger logger = LoggerFactory.getLogger(PlugServiceImpl.class);
	@Autowired
	private PlugUserBeanMapper plugUserBeanMapper;
	@Autowired
	private PlugExample plugExample;
	@Override
	public int saveUserPlug(PlugUserBean plugUserBean) {
		int x = 0;
		String[] plugIds = plugUserBean.getPlugId().split(";");
		for (String plugid : plugIds) {
			if(!StringUtils.isEmpty(plugid)) {
				x += plugUserBeanMapper.insertSelective(new PlugUserBean(plugid, plugUserBean.getUserId()));
			}
		}
		return x;

	}
	@Override
	public int deleteUserPlug(String userId ,String plugId) {
		int x = 0;
		String[] plugIds = plugId.split(";");
		for (String plugid : plugIds) {
			if(!StringUtils.isEmpty(plugid)) {
				x += plugUserBeanMapper.deleteByExample(plugExample.deletePlugUserFilter(userId, plugid));
			}
		}
		return x;
	}
	
}
