package com.geostar.georobox.management.module.apkup.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.apkup.model.ApkInfoBean;

public interface ApkInfoService {
	
	public int saveApkInfo(ApkInfoBean apkInfoBean);

	public List<ApkInfoBean> getApkInfoList(ApkInfoBean apkInfoBean, RbParm rbParm);

	public int deleteApkInfo(String id);
	
	public int getCount(ApkInfoBean apkInfoBean, RbParm rbParm);

	public String getApkUrl(String id);

}
