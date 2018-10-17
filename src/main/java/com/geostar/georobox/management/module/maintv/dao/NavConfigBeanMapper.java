package com.geostar.georobox.management.module.maintv.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.maintv.model.NavConfigBean;

public interface NavConfigBeanMapper extends RbBaseMapper<NavConfigBean> {
	
	   @Select("select count(*) from user_tables where table_name = #{tableName}")
	   int selectTableExist(String tableName);
}