package com.geostar.georobox.management.module.configmanage.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.configmanage.model.ConfigManageBean;

public interface ConfigManageBeanMapper extends RbBaseMapper<ConfigManageBean> {
	
	@Select("SELECT URL FROM RB_CONFIG WHERE id = #{id}")
	public String getFileName(String id);

}