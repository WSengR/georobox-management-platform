package com.geostar.georobox.management.module.schedule.service;

import java.util.List;

import com.geostar.georobox.management.module.schedule.model.ScheduleInfoBean;
import com.geostar.georobox.management.module.schedule.model.ScheduleSearch;

public interface ScheduleInfoService {
	
	public int saveScheduleInfo(ScheduleInfoBean scheduleInfoBean);
	
	public List<ScheduleInfoBean> queryScheduleInfo(ScheduleInfoBean scheduleInfoBean,  ScheduleSearch scheduleSearch);
		
	public int deleteScheduleInfo(String messageId);
	
	public int changeScheduleInfo(ScheduleInfoBean scheduleInfoBean);
	
	public ScheduleInfoBean getScheduleJson(String scheduleInfomsg);
	
}
