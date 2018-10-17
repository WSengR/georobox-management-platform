package com.geostar.georobox.management.module.apprunlog.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.apprunlog.model.AppRunConfigBean;
import com.geostar.georobox.management.module.apprunlog.model.RunStateModeCountBean;

public interface AppRunService {
	
	public void GetAppStatistics(RbParm baseRequestParmBean);
	
	public int save(AppRunConfigBean appRunConfigBean);
	
	public List<AppRunConfigBean> queryAppRunConfigList(AppRunConfigBean appRunConfigBean,RbParm rbParm);
	
	public long count(AppRunConfigBean appRunConfigBean, RbParm rbParm);
	
	public List<DataInfoBean> getRunChartData();

	public List<DataInfoBean> getUserChartData(RbParm rbParm);
	
	public List<DataInfoBean> getPlusChartData(RbParm rbParm);
	
	public List<DataInfoBean> getPackageChartData(RbParm rbParm);
	
	public long getBetweenTimeCount(RbParm rbParm);
	
	public RunStateModeCountBean getAppStatistics(RbParm rbParm);
	
}
