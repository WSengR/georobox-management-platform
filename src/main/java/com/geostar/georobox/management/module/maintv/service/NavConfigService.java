package com.geostar.georobox.management.module.maintv.service;

import java.util.List;

import com.geostar.georobox.management.module.maintv.model.NavConfigBean;

public interface NavConfigService {
	
	public List<NavConfigBean> queryNavConfigList(NavConfigBean navConfigBean);
	
	public int saveNavConfig(NavConfigBean navConfigBean);
	
	public int deleteNavConfig(String id);
	
	public int changeNavConfig(NavConfigBean navConfigBean);

	public boolean selectTableExist(String tableName);
}
