package com.geostar.georobox.management.wabcontroller.logmanage;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.module.logmanage.model.LogManageBean;
import com.geostar.georobox.management.module.logmanage.service.impl.LogManageServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("logmanagerun")
public class LogManageController {
	protected static Logger logger = LoggerFactory.getLogger(LogManageController.class);
	@Autowired
	private LogManageServiceImpl logManageService;
	@Autowired
	private RbFileUtils rbfileUtils;
	
	/**
	 *  获取日程列表
	 * @param logManageBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetLogInfoServlet")
	public ListLimitBean GetLogInfoServlet(LogManageBean logManageBean,RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<LogManageBean> queryLogManageList = logManageService.queryLogManageList(logManageBean, rbParm);
		listLimitBean.setData(queryLogManageList);
		int count = logManageService.getCount(logManageBean, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 上传日程信息
	 * @param logManageBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/UPLogInfoServlet")
	public RbResultBean UPLogInfoServlet(LogManageBean logManageBean) throws Exception {
		logManageBean.setDatetime(new Date());
		int num = logManageService.saveLog(logManageBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 删除日程信息
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteLogInfoServlet")
	public RbResultBean DeleteLogInfoServlet(String id) throws Exception {
		String[] sourceStrArray = id.split(";");
        for (int i = 0; i < sourceStrArray.length; i++) {
        	if(StringUtils.isEmpty(sourceStrArray[i])) {
        		continue;
        	}
        	String delFileName = logManageService.getFileName(sourceStrArray[i]);
        	int num = logManageService.deleteLogManage(sourceStrArray[i]);
        	if(num>0) {
        		rbfileUtils.deleteServerFile(delFileName);
        	}	
        }
		return RbResultBean.getSuccess(1);
	}
	
}
