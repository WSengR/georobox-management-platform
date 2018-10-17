package com.geostar.georobox.management.wabcontroller.errorlog;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.module.errorlog.model.ErrorLogBean;
import com.geostar.georobox.management.module.errorlog.model.ErrorMostCountBean;
import com.geostar.georobox.management.module.errorlog.service.impl.ErrorLogServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("errorlogrun")
public class ErrorLogController {
	protected static Logger logger = LoggerFactory.getLogger(ErrorLogController.class);
	@Autowired
	private ErrorLogServiceImpl errorLogService;
	@Autowired
	private RbFileUtils rbfileUtils;
	
	/**
	 * 获取错误日志列表
	 * @param errorLogBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetErrorLogListServlet")
	public ListLimitBean GetErrorLogListServlet(ErrorLogBean errorLogBean,RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ErrorLogBean> queryErrorLogList = errorLogService.queryErrorLog(errorLogBean, rbParm);
		listLimitBean.setData(queryErrorLogList);
		int count = errorLogService.getCount(errorLogBean, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 上传错误日志信息
	 * @param errorLogBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/UpErrorLogInfoServlet")
	public RbResultBean UpErrorLogInfoServlet(ErrorLogBean errorLogBean) throws Exception {
		errorLogBean.setDatetime(new Date());
		int num = errorLogService.saveErrorLog(errorLogBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 修改完成状态
	 * @param errorLogBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ChangErrorCompletedServlet")
	public RbResultBean ChangErrorCompletedServlet(ErrorLogBean errorLogBean) throws Exception {
		int num = errorLogService.changeIsCompleted(errorLogBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 删除逾期错误日志
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteOverdueErrorLog")
	public RbResultBean DeleteOverdueErrorLog() throws Exception {
		List<String> errorLogBeans = errorLogService.getOutlastErrorLog();
		if (errorLogBeans != null && errorLogBeans.size() > 0) {
			for (int i = 0; i < errorLogBeans.size(); i++) {
				if(StringUtils.isEmpty(errorLogBeans.get(i))) {
	        		continue;
	        	}
				String ids = errorLogBeans.get(i);
				String files = errorLogService.getFileName(ids);
				int num = errorLogService.deleteErrorLog(ids);
				if(num>0) {
					rbfileUtils.deleteServerFile(files);
	        	}		
			}
		}
		return RbResultBean.getSuccess(1);
	}
	
	/**
	 * 获取高频错误日志信息
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetPhoneMoreInfoServlet")
	public ListLimitBean GetPhoneMoreInfoServlet(RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		//获取时间段错误日志总数
		long allCount = errorLogService.getBetweenTimeCount(rbParm);
		//获取高频用户
		List<DataInfoBean> userDataInfo = errorLogService.getUserChartData(rbParm);
		//获取高频设备
		List<DataInfoBean> modeDataInfo = errorLogService.getModeChartData(rbParm);
		//获取近期错误日志（12天）
		List<DataInfoBean> errorLogDataInfo = errorLogService.getErrorLogChartData();
		ErrorMostCountBean errorMostCountBeans = new ErrorMostCountBean();
		errorMostCountBeans.setErrCount(allCount);
		errorMostCountBeans.setMostUserDatas(userDataInfo);
		errorMostCountBeans.setMostModeDatas(modeDataInfo);
		errorMostCountBeans.setDateChartDatas(errorLogDataInfo);
		listLimitBean.setData(errorMostCountBeans);
		return listLimitBean;
	}
	
}
