package com.geostar.georobox.management.module.document.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.document.model.DocumentBean;

public interface DocumentBeanMapper extends RbBaseMapper<DocumentBean> {
	
	/**
	 * 根据主键获取文档地址
	 * @param id
	 * @return
	 */
	@Select("SELECT FILE_PATH FROM RB_DOCUMENT WHERE id = #{id}")
	public String getFileName(String id);
	
}