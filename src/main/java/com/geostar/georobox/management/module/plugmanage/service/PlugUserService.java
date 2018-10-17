package com.geostar.georobox.management.module.plugmanage.service;

import org.springframework.stereotype.Service;

import com.geostar.georobox.management.module.plugmanage.model.PlugUserBean;

@Service
public interface PlugUserService {
	int saveUserPlug(PlugUserBean plugUserBean);
	
	int deleteUserPlug(String userId ,String plugId);
}
