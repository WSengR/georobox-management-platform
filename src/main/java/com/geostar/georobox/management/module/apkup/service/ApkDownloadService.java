package com.geostar.georobox.management.module.apkup.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.apkup.model.ApkDownloadBean;

public interface ApkDownloadService {

	public int saveApkDownload(ApkDownloadBean apkDownloadBean);
	
	public List<ApkDownloadBean> getApkDownloadList(ApkDownloadBean apkDownloadBean, RbParm rbParm, String version);
	
	public int getCount(ApkDownloadBean apkDownloadBean, RbParm rbParm, String version);
		
}
