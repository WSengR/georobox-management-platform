package com.geostar.georobox.management.module.apkup.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.apkup.model.ApkDownloadBean;

public interface ApkDownloadBeanMapper extends RbBaseMapper<ApkDownloadBean> {
	
	/**
	 * 根据版本信息获取APK下载数量
	 * @param version
	 * @return
	 */
	@Select("SELECT DOWNLOAD_NUM FROM RB_APK_INFO WHERE APK_VERSION = #{version}")
	public long getNum(String version);
	
	/**
	 * 根据下载数量和版本号修改下载次数
	 * @param num
	 * @param version
	 */
	@Select("UPDATE RB_APK_INFO SET DOWNLOAD_NUM = #{num} WHERE APK_VERSION = #{version}")
	public void addNum(long num, String version);
	
}