package com.geostar.georobox.management.module.apprunlog.dao.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbSQLProvider;
/**
 * 
 * 	描述：复杂接口提供者
 * 
 * @author  wangsr  
 * @date    2018年9月11日
 */
public class RunConfingProvider extends RbSQLProvider {
	protected static Logger logger = LoggerFactory.getLogger(RunConfingProvider.class);
	
	public String getUserChartData(RbParm rbParm) {

		String sql = " select INFO_USER as name,count(INFO_USER) as value from RB_RUN_CONFIG where 1=1 ";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		sql += "and INFO_USER!='null'  group by INFO_USER  ORDER BY value desc";
		logger.info("getUserChartData sql = " + sql);
		return sql;
	}
	
	public String getPlusChartData(RbParm rbParm) {

		String sql = " select APP_NAME as name,count(APP_NAME) as value from RB_RUN_CONFIG Where 1=1 ";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		sql += "and APP_NAME!='null' group by APP_NAME  ORDER BY value desc ";
		logger.info("getPlusChartData SQL = " + sql);
		return sql;
	}
	
	public String getPackageChartData(RbParm rbParm) {

		String sql = "select APP_PACKAGE as name,count(APP_PACKAGE) as value from RB_RUN_CONFIG where 1=1";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		sql += "and APP_PACKAGE!='null'  group by APP_PACKAGE  ORDER BY value desc";
		logger.info("getPackageChartData SQL = " + sql);
		return sql;
	}
	
	public String getBetweenTimeCount(RbParm rbParm) {
		String sql = "SELECT COUNT(*)as count FROM RB_RUN_CONFIG  where 1=1";
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		logger.info("getBetweenTimeCount SQL = " + sql);
		return sql;
	}
	
	
}
