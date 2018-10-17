package com.geostar.georobox.management.module.schedule.dao.provider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.schedule.model.ScheduleInfoBean;
import com.geostar.georobox.management.module.schedule.model.ScheduleSearch;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class ScheduleInfoExample extends RbExample{
	
	
	public Example.Criteria addAndBetweenTime(Example.Criteria criteria, ScheduleSearch scheduleSearch, String property) {
		if (!StringUtils.isEmptyOrWhitespace(scheduleSearch.startTimeString) && !StringUtils.isEmptyOrWhitespace(scheduleSearch.endTimeString)) {
			try {
				Date startTime = new SimpleDateFormat(DATE_FORMAT).parse(scheduleSearch.startTimeString);
				Date endTime = new SimpleDateFormat(DATE_FORMAT).parse(scheduleSearch.endTimeString);
				if (!StringUtils.isEmptyOrWhitespace(scheduleSearch.getStartTimeString())) {
					criteria.andBetween(property, startTime, endTime);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return criteria;
	}
	/**
	 * 根据条件筛选日程
	 * @param scheduleInfoBean
	 * @param rbParm
	 * @return
	 */
	public Example getScheduleInfoFilter(ScheduleInfoBean scheduleInfoBean, ScheduleSearch scheduleSearch) {
		Example example = new Example(ScheduleInfoBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(scheduleSearch.key)) {
			criteria.orLike("title", "%" + scheduleSearch.key + "%");
		}
		Example.Criteria criteria2 = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(scheduleInfoBean.getMessageId())) {
			criteria2.andEqualTo("messageId", scheduleInfoBean.getMessageId());
		}
		if (!StringUtils.isEmptyOrWhitespace(scheduleInfoBean.getCreateId())) {
			criteria2.andEqualTo("createId", scheduleInfoBean.getCreateId());
		}
		
		criteria2 = addAndBetweenTime(criteria2 ,scheduleSearch, "startTime");
		example.and(criteria);
		example.and(criteria2);
		return example;
	}
	
}
