package com.geostar.georobox.management.module.apkup.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.apkup.model.ApkInfoBean;

public interface ApkInfoBeanMapper extends RbBaseMapper<ApkInfoBean> {
	
	/**
	 * 根据id获取APK地址
	 * @param id
	 * @return
	 */
	@Select("SELECT APK_URL FROM RB_APK_INFO WHERE id = #{id}")
	public String getApkUrl(String id);
	
	/**
	 * 根据id获取APK的version
	 * @param id
	 * @return
	 */
	@Select("SELECT APK_VERSION FROM RB_APK_INFO WHERE id = #{id}")
	public String getVersion(String id);
	
	/**
	 * 根据version删除APK下载历史
	 * @param version
	 */
	@Select("DELETE RB_APK_DOWNLOAD WHERE VERSION_ID = #{version}")
	public void delDownloadList(String version);
	
}