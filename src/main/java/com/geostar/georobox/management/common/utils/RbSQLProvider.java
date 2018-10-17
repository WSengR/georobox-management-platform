package com.geostar.georobox.management.common.utils;

import org.springframework.util.StringUtils;

/**
 * 
 * 描述：sql工具总父类，他的的Provider都继承该父类
 * 
 * @author wangsr
 * @date 2018年9月11日
 */
public class RbSQLProvider {
	/**
	  * 获取两个时间之间的SQL
	 * 
	 * @param startTime 【yyyy-mm-dd】
	 * @param endTime
	 * @return
	 */
	public String andBetweenDate(String startTime, String endTime) {
		String sql = "";
		if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
			sql += " and ( DATETIME between to_date('" + startTime + " 00:00:00','yyyy-mm-dd hh24:mi:ss') and to_date('"+ endTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss'))";
		}
		return sql;
	}

}
