package com.geostar.georobox.management.module.plugmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.apprunlog.dao.provider.RunConfingProvider;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugProvider;
import com.geostar.georobox.management.module.plugmanage.model.PlugBean;
import com.geostar.georobox.management.module.plugmanage.model.UserPlugBean;

public interface PlugBeanMapper extends RbBaseMapper<PlugBean> {
	@Results(id = "PlugBeanMap", value = { @Result(property = "plugId", column = "PLUG_ID"),
			@Result(property = "plugSort", column = "PLUG_SORT") })
	@Select("SELECT * FROM  (SELECT * FROM RB_PLUG WHERE PLUG_SORT < #{plugSort}  ORDER BY PLUG_SORT DESC)  where ROWNUM = 1")
	public PlugBean selectUpPlug(Long plugSort);

	@ResultMap("PlugBeanMap")
	@Select("SELECT * FROM  (SELECT * FROM RB_PLUG WHERE PLUG_SORT > #{plugSort}  ORDER BY PLUG_SORT)  where ROWNUM = 1 ")
	public PlugBean selectDownPlug(Long plugSort);

	@Results(id = "UserPlugBeanMap", value = { 
			@Result(property = "Name", column = "PLUG_NAME"),
			@Result(property = "Url", column = "PLUG_URL"), 
			@Result(property = "Version", column = "PLUG_VERSIONCODE"),
			@Result(property = "PackageName", column = "PLUG_PACKAGE"),
			@Result(property = "LauncherActivity", column = "PLUS_LAUNCHER_ACTIVITY"), 
			@Result(property = "Icon", column = "PLUG_ICON"),
			@Result(property = "plugType", column = "PLUG_TYPE"),
			@Result(property = "NeedInstall", column = "PLUG_NEEDINSTALL"), 
			@Result(property = "plugId", column = "PLUG_ID"), 
			@Result(property = "type", column = "CATEGORY_NAME"), 
			@Result(property = "typeName", column = "CATEGORY_DES"), 
			@Result(property = "details", column = "PLUG_DETAILS")})
	
	@SelectProvider(type = PlugProvider.class, method = "selectUserPlugBean")
	public List<UserPlugBean> selectUserPlugBean(String permissionName);

	@ResultMap("UserPlugBeanMap")
	@SelectProvider(type = PlugProvider.class, method = "selectUserAddPlugBean")
	public List<UserPlugBean> selectUserAddPlugBean(String permissionName);

	/**
	 *  用户正在使用的插件列表
	 * @param userId
	 * @return
	 */
	@ResultMap("UserPlugBeanMap")
	@Select("SELECT * FROM RB_PLUG_USER "
			+ "LEFT JOIN RB_PLUG ON RB_PLUG_USER.PLUG_ID = RB_PLUG.PLUG_ID "
			+ "LEFT JOIN RB_PLUG_AND_C ON RB_PLUG_AND_C.PLUG_ID = RB_PLUG.PLUG_ID "
			+ "LEFT JOIN RB_PLUG_CATEGORY ON RB_PLUG_AND_C.CATEGORY_ID = RB_PLUG_CATEGORY.CATEGORY_ID "
			+ "WHERE  PLUG_IS_DOWN = 1 AND USER_ID = #{userId}")
	public List<UserPlugBean> userUsingPlugBean(String userId);

	
}