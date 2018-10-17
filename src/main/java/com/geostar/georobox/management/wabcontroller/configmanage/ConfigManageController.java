package com.geostar.georobox.management.wabcontroller.configmanage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.configmanage.model.ConfigManageBean;
import com.geostar.georobox.management.module.configmanage.service.impl.ConfigManageServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("configmanagerun")
public class ConfigManageController {
	protected static Logger logger = LoggerFactory.getLogger(ConfigManageController.class);
	@Autowired
	private ConfigManageServiceImpl configManageService;

	/**
	 * 获取配置文件列表
	 * 
	 * @param configManageBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetConfigMangerServlet")
	public ListLimitBean GetConfigMangerServlet(ConfigManageBean configManageBean, RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ConfigManageBean> queryConfigManageList = configManageService.queryConfigManageList(configManageBean,
				rbParm);
		listLimitBean.setData(queryConfigManageList);
		int count = configManageService.getCount();
		listLimitBean.setCount(count);
		return listLimitBean;
	}

	/**
	 * 添加配置文件
	 * 
	 * @param configManageBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/AddConfigMangerServlet")
	public RbResultBean AddConfigMangerServlet(ConfigManageBean configManageBean) throws Exception {
		configManageBean.setDatetime(new Date());
		int num = configManageService.saveConfigManage(configManageBean);
		return RbResultBean.getSuccess(num);
	}

	/**
	 * 修改配置文件
	 * 
	 * @param configManageBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ChangConfigFileServlet")
	public RbResultBean ChangConfigFileServlet(ConfigManageBean configManageBean) throws Exception {
		int num = configManageService.changeConfigManage(configManageBean);
		return RbResultBean.getSuccess(num);
	}

	/**
	 * 删除配置文件
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteConfigMangerServlet")
	public RbResultBean DeleteConfigMangerServlet(String id) throws Exception {
		int num = configManageService.deleteConfigManage(id);
		return RbResultBean.getSuccess(num);
	}

	/**
	 * 预览配置文件
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetMainConfigServlet")
	public ListLimitBean GetMainConfigServlet(HttpServletRequest request) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		StringBuffer url = request.getRequestURL();
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
				.append(request.getServletContext().getContextPath()).append("/").toString();
		List<ConfigManageBean> queryConfigManageList = configManageService.queryConfigManageList();
		String mainXML = configManageService.getConfigString(queryConfigManageList, tempContextUrl);
		listLimitBean.setMassage(configManageService.CreateConfig(mainXML));
		listLimitBean.setData(queryConfigManageList);
		int count = configManageService.getCount();
		listLimitBean.setCount(count);
		return listLimitBean;
	}

}
