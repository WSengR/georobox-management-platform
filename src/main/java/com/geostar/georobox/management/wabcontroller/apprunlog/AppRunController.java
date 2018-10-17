package com.geostar.georobox.management.wabcontroller.apprunlog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.apprunlog.model.AppRunConfigBean;
import com.geostar.georobox.management.module.apprunlog.model.RunStateModeCountBean;
import com.geostar.georobox.management.module.apprunlog.service.impl.AppRunServiceImpl;

@RestController 
@RequestMapping("apprun")
public class AppRunController {
	protected static Logger logger = LoggerFactory.getLogger(AppRunController.class);
	@Autowired
	private AppRunServiceImpl appRunService;
	/**
	  * 获取列表【模糊筛选】
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetRunStateListServlet")
	public ListLimitBean GetRunStateListServlet(AppRunConfigBean appRunConfigBean, RbParm rbParm) throws Exception {
		logger.info("【/AppRunConfigBean】 " + appRunConfigBean.toString());
		logger.info("【/RbParm】 " + rbParm.toString());
		ListLimitBean listLimitBean = new ListLimitBean();
		List<AppRunConfigBean> queryAppRunConfigList = appRunService.queryAppRunConfigList(appRunConfigBean, rbParm);
		listLimitBean.setData(queryAppRunConfigList);
		listLimitBean.setCount(appRunService.count(appRunConfigBean, rbParm));
		return listLimitBean;
	}

	/**
	 * 获取App饼状图分析
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetAppStatisticsServlet")
	public RbResultBean GetAppStatisticsServlet(RbParm rbParm) throws Exception {
		logger.info("【/RbParm】 " + rbParm.toString());
		RunStateModeCountBean appStatistics = appRunService.getAppStatistics(rbParm);
		return RbResultBean.getSuccess(appStatistics);
	}
	
	/**
	 * 插入运行状态信息
	 * @param appRunConfigBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/PostRunStateInfoServlet")
	public RbResultBean PostRunStateInfoServlet(AppRunConfigBean appRunConfigBean) throws Exception {
		logger.info("【/AppRunConfigBean】 " + appRunConfigBean.toString());
		int save = appRunService.save(appRunConfigBean);
		return RbResultBean.getSuccess(save);
	}

}
