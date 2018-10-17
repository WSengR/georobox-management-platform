package com.geostar.georobox.management.module.plugmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndPermission;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;

public interface PlugAndPermissionMapper extends RbBaseMapper<PlugAndPermission> {
	@Results(id = "UserPlugBeanMap", value = { 
			@Result(property = "permissionId", column = "PERMISSION_ID"),
			@Result(property = "permissionName", column = "PERMISSION_NAME"), 
			@Result(property = "permissionDes", column = "PERMISSION_DES")
			})
	@Select("SELECT DISTINCT  * FROM RB_PLUG_AND_P LEFT JOIN RB_PLUG_PREMISSION ON RB_PLUG_AND_P.PERMISSION_ID = RB_PLUG_PREMISSION.PERMISSION_ID WHERE PLUG_ID = #{plugId} ")
	List<PlugPermissionBean> selectPAPByPlugId(String plugId);
}