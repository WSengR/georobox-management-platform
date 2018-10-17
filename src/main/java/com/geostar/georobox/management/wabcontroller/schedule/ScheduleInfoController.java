package com.geostar.georobox.management.wabcontroller.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.schedule.model.ScheduleInfoBean;
import com.geostar.georobox.management.module.schedule.model.ScheduleSearch;
import com.geostar.georobox.management.module.schedule.service.impl.ScheduleInfoServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("schedulerun")
public class ScheduleInfoController {
	protected static Logger logger = LoggerFactory.getLogger(ScheduleInfoController.class);
	@Autowired
	private ScheduleInfoServiceImpl scheduleInfoService;
	@Autowired
	private SQLHelper sqlHelper;

	/**
	 * 获取日程列表
	 * 
	 * @param scheduleInfoBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetScheduleInfoServlet")
	public RbResultBean GetScheduleInfoServlet(ScheduleInfoBean scheduleInfoBean, ScheduleSearch scheduleSearch)
			throws Exception {
		RbResultBean rbResultBean = new RbResultBean();
		List<ScheduleInfoBean> queryScheduleInfoList = scheduleInfoService.queryScheduleInfo(scheduleInfoBean,	scheduleSearch);
		rbResultBean.setData(queryScheduleInfoList);
		return rbResultBean;
	}

	/**
	 * 添加日程
	 * 
	 * @param infoJson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/AddScheduleInfoServlet")
	public RbResultBean AddScheduleInfoServlet(String infoJson) throws Exception {
		ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
		scheduleInfoBean = scheduleInfoService.getScheduleJson(infoJson);
		RbResultBean rbResultBean = new RbResultBean();
		String uuId = sqlHelper.getUUID();
		scheduleInfoBean.setMessageId(uuId);
		scheduleInfoService.saveScheduleInfo(scheduleInfoBean);
		rbResultBean.setData(uuId);
		return rbResultBean;
	}

	/**
	 * 删除日程
	 * 
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DelScheduleInfoServlet")
	public RbResultBean DelScheduleInfoServlet(String messageId) throws Exception {
		int num = scheduleInfoService.deleteScheduleInfo(messageId);
		return RbResultBean.getSuccess(num);
	}

	/**
	 * 修改日程
	 * 
	 * @param infoJson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ChangeScheduleInfoServlet")
	public RbResultBean ChangeScheduleInfoServlet(String infoJson) throws Exception {
		ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
		scheduleInfoBean = scheduleInfoService.getScheduleJson(infoJson);
		scheduleInfoBean.setChangeTime(new Date());
		int num = scheduleInfoService.changeScheduleInfo(scheduleInfoBean);
		return RbResultBean.getSuccess(num);
	}

}
