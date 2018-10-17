package com.geostar.georobox.management.module.errorlog.dao.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbSQLProvider;

public class ErrorLogProvider extends RbSQLProvider {
	protected static Logger logger = LoggerFactory.getLogger(ErrorLogProvider.class);
	
	/**
	 * 获取高频设备产生错误数量（前三）
	 * @param rbParm
	 * @return
	 */
	public String getModeChartData(RbParm rbParm) {
		String sql = " select * from ( select PHONE_MODE as name,count(PHONE_MODE) as value from RB_ERROR_LOG Where 1=1 ";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		sql += "and PHONE_MODE!='null' group by PHONE_MODE  ORDER BY value desc ) where ROWNUM < 4";
		logger.info("getModeChartData SQL = " + sql);
		return sql;
	}
	
	/**
	 * 获取高频用户产生错误数量（前三）
	 * @param rbParm
	 * @return
	 */
	public String getUserChartData(RbParm rbParm) {
		String sql = " select * from ( select USER_INFO as name,count(USER_INFO) as value from RB_ERROR_LOG where 1=1";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		sql += "and USER_INFO!='null'  group by USER_INFO  ORDER BY value desc ) where ROWNUM < 4";
		logger.info("getUserChartData SQL = " + sql);
		return sql;
	}
	
	/**
	 * 根据时间范围查询错误日志数量
	 * @param rbParm
	 * @return
	 */
	public String getBetweenTimeCount(RbParm rbParm) {
		String sql = "SELECT COUNT(*)as count FROM RB_ERROR_LOG  where 1=1";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		logger.info("getBetweenTimeCount SQL = " + sql);
		return sql;
	}
	
	
}
