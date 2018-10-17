package com.geostar.georobox.management.module.plugmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugBean;
import com.geostar.georobox.management.module.plugmanage.model.UserPlugBean;

@Service
public interface PlugService {
	 int savePlug(PlugBean plugBean);

	 RbResultBean deletePlug(String plugId);
	
	 int updatePlug(PlugBean plugBean);
	
	 PlugBean selectPlug(String plugId);
	 
	 PlugBean selectResultPlus(String plugId);
	
	 List<PlugBean> selectPlugList(RbParm rbParm);
	
	 int outAndUpPlug(String plugId);
	 
	 int movePlusUp(String plugId);
	 
	 int movePlusDown(String plugId);
	 
	 int selectPlugCount(RbParm rbParm); 
	 
	 List<PlugBean> selectPlugByPermission(String permission); 
	 
	 List<UserPlugBean> selectUserPlugByPermission(String permission); 
	 
	 List<UserPlugBean> selectUserUsingPlug(String userId,String permission); 
}
