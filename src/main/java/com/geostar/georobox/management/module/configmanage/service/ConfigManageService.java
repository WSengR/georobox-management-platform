package com.geostar.georobox.management.module.configmanage.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.configmanage.model.ConfigManageBean;

public interface ConfigManageService {
	
	public int saveConfigManage(ConfigManageBean configManageBean);
	
	public List<ConfigManageBean> queryConfigManageList(ConfigManageBean configManageBean,RbParm rbParm);
	
	public int getCount();
	
	public int changeConfigManage(ConfigManageBean configManageBean);
	
	public int deleteConfigManage(String id);
	
	public String getFileName(String id);
	
	public String getConfigString(List<ConfigManageBean> configMangerBeans, String serverUrl);

	public List<ConfigManageBean> queryConfigManageList();

}
