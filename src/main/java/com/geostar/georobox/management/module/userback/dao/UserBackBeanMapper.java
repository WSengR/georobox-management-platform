package com.geostar.georobox.management.module.userback.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.userback.model.UserBackBean;

public interface UserBackBeanMapper extends RbBaseMapper<UserBackBean> {
	
	/**
	 * 根据ID查找文件地址
	 * @param id
	 * @return
	 */
	@Select("SELECT INFO_BACK_URL FROM RB_USERBACK WHERE id = #{id}")
	public String getFileName(String id);
	
}