package com.geostar.georobox.management.module.logmanage.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.logmanage.model.LogManageBean;

public interface LogManageBeanMapper extends RbBaseMapper<LogManageBean> {
	
	@Select("SELECT FILE_URL FROM RB_LOG WHERE id = #{id}")
	public String getFileName(String id);
	
}