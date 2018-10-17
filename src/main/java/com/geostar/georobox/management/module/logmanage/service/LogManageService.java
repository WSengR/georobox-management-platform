package com.geostar.georobox.management.module.logmanage.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.logmanage.model.LogManageBean;

public interface LogManageService {
	
	public int saveLog(LogManageBean logManageBean);
	
	public List<LogManageBean> queryLogManageList(LogManageBean logManageBean,RbParm rbParm);
	
	public int getCount(LogManageBean logManageBean, RbParm rbParm);
	
	public int deleteLogManage(String id);
	
	public String getFileName(String id);
}
