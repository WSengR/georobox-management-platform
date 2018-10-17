package com.geostar.georobox.management.wabcontroller.serverurl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.serverurl.model.ServerUrlBean;
import com.geostar.georobox.management.module.serverurl.service.impl.ServerUrlServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("serverurlrun")
public class ServerUrlController {
	protected static Logger logger = LoggerFactory.getLogger(ServerUrlController.class);
	@Autowired
	private ServerUrlServiceImpl serverUrlService;
	
	/**
	 * 获取服务器配置信息
	 * @param serverUrlBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetServerUrlServlet")
	public ListLimitBean GetServerUrlServlet(ServerUrlBean serverUrlBean,RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ServerUrlBean> queryServerUrlList = serverUrlService.queryServerUrlList(serverUrlBean, rbParm);
		listLimitBean.setData(queryServerUrlList);
		int count = serverUrlService.getCount();
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 添加新服务器配置信息
	 * @param serverUrlBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/UplaodServerUrlServlet")
	public RbResultBean UplaodServerUrlServlet(ServerUrlBean serverUrlBean) throws Exception {
		serverUrlBean.setDatetime(new Date());
		int num = serverUrlService.saveServerUrl(serverUrlBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 删除服务器配置信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteServerUrlServlet")
	public RbResultBean DeleteServerUrlServlet(String id) throws Exception {
		int num = serverUrlService.deleteServerUrl(id);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 修改服务器配置信息（根据主键）
	 * @param serverUrlBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ReviseServerUrlServlet")
	public RbResultBean ReviseServerUrlServlet(ServerUrlBean serverUrlBean) throws Exception {
		int num = serverUrlService.changeServerUrl(serverUrlBean);
		return RbResultBean.getSuccess(num);
	}
}
