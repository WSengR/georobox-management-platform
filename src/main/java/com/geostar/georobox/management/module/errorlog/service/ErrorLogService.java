package com.geostar.georobox.management.module.errorlog.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.errorlog.model.ErrorLogBean;

public interface ErrorLogService {
	
	public int saveErrorLog(ErrorLogBean errorLogBean);
	
	public List<ErrorLogBean> queryErrorLog(ErrorLogBean errorLogBean,RbParm rbParm);
	
	public int getCount(ErrorLogBean errorLogBean, RbParm rbParm);
	
	public int changeIsCompleted(ErrorLogBean errorLogBean);
	
	public int deleteErrorLog(String id);
	
	public String getFileName(String id);
	
	public List<String> getOutlastErrorLog();
	
	public List<DataInfoBean> getErrorLogChartData();
	
	public long getBetweenTimeCount(RbParm rbParm);
	
	public List<DataInfoBean> getModeChartData(RbParm rbParm);
	
	public List<DataInfoBean> getUserChartData(RbParm rbParm);
	
	public List<DataInfoBean> getTodayErrorLogChartData(String startTime, String endTime);

}
