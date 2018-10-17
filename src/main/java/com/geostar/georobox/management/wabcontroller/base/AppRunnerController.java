package com.geostar.georobox.management.wabcontroller.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.DBConnectionInfo;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.maintv.model.NavConfigBean;
import com.geostar.georobox.management.module.maintv.service.impl.NavConfigServiceImpl;

/**
 * 公共文件上传接口 描述：
 * 
 * @author wangsr
 * @date 2018年9月13日
 */
@RestController
@RequestMapping("/AppRunner")
public class AppRunnerController {

	@Autowired
	NavConfigServiceImpl navConfigService;

	protected static Logger logger = LoggerFactory.getLogger(AppRunnerController.class);

	@RequestMapping("/initdb")
	public RbResultBean initdb() {
		boolean selectTableExist = navConfigService.selectTableExist("RB_NAV_CONGFIG");
		String initString = "初始化：...";
		if (!selectTableExist) {
			boolean createDefultTable = DBConnectionInfo.getInstence().createDefultTable();
			initString += "【创建默认数据库..." + createDefultTable + "】.....";
		} else {
			initString += "【默认数据库已存在】....";
		}

		List<NavConfigBean> queryNavConfigList = navConfigService.queryNavConfigList(new NavConfigBean());
		if (queryNavConfigList == null || queryNavConfigList.size() <= 0) {
			boolean insertDefultData = DBConnectionInfo.getInstence().insertDefultData();
			initString += "【插入数据库默认数据..." + insertDefultData + "】";
		} else {
			initString += "【默认数据已插入】";
		}
		logger.info(initString);
		return RbResultBean.getSuccess(initString);
	}

}
