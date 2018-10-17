package com.geostar.georobox.management.module.errorlog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.errorlog.dao.provider.ErrorLogProvider;
import com.geostar.georobox.management.module.errorlog.model.ErrorLogBean;

public interface ErrorLogBeanMapper extends RbBaseMapper<ErrorLogBean> {
	
	@Select("SELECT LOG_URL FROM RB_ERROR_LOG WHERE id = #{id}")
	public String getFileName(String id);
	
	@Select("SELECT ID from RB_ERROR_LOG WHERE DATETIME < (SYSDATE-31)")
	public List<String> getOutlastErrorLog();
	
	@Select("SELECT COUNT(ID) AS VALUE,TRUNC(DATETIME, 'DD') AS NAME FROM RB_ERROR_LOG  WHERE DATETIME BETWEEN SYSDATE-30 AND SYSDATE GROUP BY TRUNC(DATETIME, 'DD') ORDER BY NAME")
	 List<DataInfoBean> getErrorLogChartData();
	
	@Select("SELECT COUNT(ID) AS VALUE,TRUNC(DATETIME, 'hh') AS NAME from RB_ERROR_LOG  WHERE 1=1  AND ( DATETIME BETWEEN to_date(#{startTime},'yyyy-mm-dd hh24:mi:ss') and to_date(#{endTime},'yyyy-mm-dd hh24:mi:ss'))  group by trunc(DATETIME, 'hh') ORDER BY NAME")
	 List<DataInfoBean> getTodayErrorLogChartData(String startTime, String endTime);
	
	@SelectProvider(type = ErrorLogProvider.class, method = "getModeChartData")
	 List<DataInfoBean> getModeChartData(RbParm rbParm);
	
	@SelectProvider(type = ErrorLogProvider.class, method = "getUserChartData")
	 List<DataInfoBean> getUserChartData(RbParm rbParm);
	
	@SelectProvider(type = ErrorLogProvider.class, method = "getBetweenTimeCount")
	 long betweenTimeCount(RbParm rbParm);
	
}