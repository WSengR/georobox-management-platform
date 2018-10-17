package com.geostar.georobox.management.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * 描述： 插件Example提供者【封装基础模糊查询】
 * 
 * @author wangsr
 * @date 2018年9月14日
 */
public class RbExample {
	protected static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间包装获取时间段的数据【时间范围查询】
	 * 
	 * @param criteria
	 * @param rbParm
	 * @return
	 */
	protected Example.Criteria addAndBetweenTime(Example.Criteria criteria, RbParm rbParm) {
		if (!StringUtils.isEmptyOrWhitespace(rbParm.startTime) && !StringUtils.isEmptyOrWhitespace(rbParm.endTime)) {
			try {
				Date startTime = new SimpleDateFormat(DATE_FORMAT).parse(rbParm.startTime + " 00:00:00");
				Date endTime = new SimpleDateFormat(DATE_FORMAT).parse(rbParm.endTime + " 23:59:59");
				if (!StringUtils.isEmptyOrWhitespace(rbParm.getStartTime())) {
					criteria.andBetween("datetime", startTime, endTime);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return criteria;
	}

	/**
	 * 时间包装获取时间段的数据【时间范围查询】
	 * 
	 * @param criteria
	 * @param rbParm
	 * @return
	 */
	protected Example.Criteria addAndBetweenTime(Example.Criteria criteria, RbParm rbParm, String property) {
		if (!StringUtils.isEmptyOrWhitespace(rbParm.startTime) && !StringUtils.isEmptyOrWhitespace(rbParm.endTime)) {
			try {
				Date startTime = new SimpleDateFormat(DATE_FORMAT).parse(rbParm.startTime + " 00:00:00");
				Date endTime = new SimpleDateFormat(DATE_FORMAT).parse(rbParm.endTime + " 23:59:59");
				if (!StringUtils.isEmptyOrWhitespace(rbParm.getStartTime())) {
					criteria.andBetween(property, startTime, endTime);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return criteria;
	}

}
