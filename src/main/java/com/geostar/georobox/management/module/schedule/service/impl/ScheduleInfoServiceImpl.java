package com.geostar.georobox.management.module.schedule.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.utils.RbJsonUtils;
import com.geostar.georobox.management.module.schedule.dao.ScheduleInfoBeanMapper;
import com.geostar.georobox.management.module.schedule.dao.provider.ScheduleInfoExample;
import com.geostar.georobox.management.module.schedule.model.ScheduleInfoBean;
import com.geostar.georobox.management.module.schedule.model.ScheduleSearch;
import com.geostar.georobox.management.module.schedule.service.ScheduleInfoService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {
	protected static Logger logger = LoggerFactory.getLogger(ScheduleInfoServiceImpl.class);
	@Autowired
	private ScheduleInfoBeanMapper scheduleInfoBeanMapper;
	@Autowired
	private ScheduleInfoExample scheduleInfoExample;
	
	/**
	 * 保存日程对象
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveScheduleInfo(ScheduleInfoBean scheduleInfoBean) {
		int insert = scheduleInfoBeanMapper.insertSelective(scheduleInfoBean);
		return insert;
	}
	
	/**
	 * 获取日程列表
	 */
	@Override
	public List<ScheduleInfoBean> queryScheduleInfo(ScheduleInfoBean scheduleInfoBean, ScheduleSearch scheduleSearch) {
		// 开始分页
		//		sqlHelper.startPage(rbParm);
		Example scheduleInfoFilter = scheduleInfoExample.getScheduleInfoFilter(scheduleInfoBean, scheduleSearch);
		scheduleInfoFilter.orderBy("createTime").desc();
		List<ScheduleInfoBean> scheduleInfoBeans = scheduleInfoBeanMapper.selectByExample(scheduleInfoFilter);
		return scheduleInfoBeans;
	}
	
	/**
	 * 根据主键删除日程
	 */
	@Override
	public int deleteScheduleInfo(String messageId) {
		int insert = scheduleInfoBeanMapper.deleteByPrimaryKey(messageId);
		return insert;
	}
	
	/**
	 * 根据主键修改日程
	 */
	@Override
	public int changeScheduleInfo(ScheduleInfoBean scheduleInfoBean) {
		int insert = scheduleInfoBeanMapper.updateByPrimaryKeySelective(scheduleInfoBean);
		return insert;
	}
	
	/**
	 * 转换日程JSON
	 */
	@Override
	public ScheduleInfoBean getScheduleJson(String scheduleInfomsg) {
		return RbJsonUtils.jsonToClass(scheduleInfomsg, ScheduleInfoBean.class);
	}
	
	
}
