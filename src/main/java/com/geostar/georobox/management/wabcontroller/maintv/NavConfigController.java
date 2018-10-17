package com.geostar.georobox.management.wabcontroller.maintv;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.maintv.model.MainNavBean;
import com.geostar.georobox.management.module.maintv.model.NavConfigBean;
import com.geostar.georobox.management.module.maintv.service.impl.NavConfigServiceImpl;


@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("navconfigrun")
public class NavConfigController {
	protected static Logger logger = LoggerFactory.getLogger(NavConfigController.class);
	@Autowired
	private NavConfigServiceImpl navConfigService;

	/**
	 * 获取左侧导航列表
	 * 
	 * @param navConfigBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetMainMenuServlet")
	public MainNavBean GetMainMenuServlet(NavConfigBean navConfigBean) throws Exception {
		List<NavConfigBean> queryServerUrlList = navConfigService.queryNavConfigList(navConfigBean);
		MainNavBean mainNavBean = new MainNavBean();
		mainNavBean.setContentManagement(queryServerUrlList);
		return mainNavBean;
	}

	/**
	 * 添加左侧导航
	 * 
	 * @param navConfigBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/PostAddMenuServlet")
	public RbResultBean PostAddMenuServlet(NavConfigBean navConfigBean) throws Exception {
		navConfigBean.setDatetime(new Date());
		int num = navConfigService.saveNavConfig(navConfigBean);
		return RbResultBean.getSuccess(num);
	}

	/**
	 * 删除左侧导航
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteMenuServlet")
	public RbResultBean DeleteMenuServlet(String id) throws Exception {
		int num = navConfigService.deleteNavConfig(id);
		return RbResultBean.getSuccess(num);
	}

	/**
	 * 修改导航状态
	 * 
	 * @param navConfigBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ChangMenuStateServlet")
	public RbResultBean ChangMenuStateServlet(NavConfigBean navConfigBean) throws Exception {
		int num = navConfigService.changeNavConfig(navConfigBean);
		return RbResultBean.getSuccess(num);
	}
}
