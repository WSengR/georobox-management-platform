package com.geostar.georobox.management.module.plugmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndCategory;

public interface PlugAndCategoryMapper extends RbBaseMapper<PlugAndCategory> {
	
	@Select("SELECT DISTINCT CATEGORY_ID FROM RB_PLUG_AND_C WHERE PLUG_ID = #{plugId} ")
	List<String> selectPACByPlugId(String plugId);
}