package com.geostar.georobox.management.module.apprunlog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.apprunlog.dao.provider.RunConfingProvider;
import com.geostar.georobox.management.module.apprunlog.model.AppRunConfigBean;

public interface AppRunConfigBeanMapper extends RbBaseMapper<AppRunConfigBean> {

	   /**
	    * 获取近7天运行日志
	    * @return
	    */
	   @Select("SELECT COUNT(ID) AS VALUE,TRUNC(DATETIME, 'DD') AS NAME FROM RB_RUN_CONFIG  WHERE DATETIME BETWEEN SYSDATE-7 AND SYSDATE GROUP BY TRUNC(DATETIME, 'DD') ORDER BY NAME")
	   List<DataInfoBean> getRunChartData();
	   

	   @SelectProvider(type = RunConfingProvider.class, method = "getUserChartData")
	   List<DataInfoBean> getUserChartData(RbParm rbParm);
	   
	   @SelectProvider(type = RunConfingProvider.class, method = "getPlusChartData")
	   List<DataInfoBean> getPlusChartData(RbParm rbParm);
	   
	   @SelectProvider(type = RunConfingProvider.class, method = "getPackageChartData")
	   List<DataInfoBean> getPackageChartData(RbParm rbParm);
	   
	   @SelectProvider(type = RunConfingProvider.class, method = "getBetweenTimeCount")
	   long betweenTimeCount(RbParm rbParm);
	
}