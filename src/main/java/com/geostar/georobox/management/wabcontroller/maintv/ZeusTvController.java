package com.geostar.georobox.management.wabcontroller.maintv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.apprunlog.model.AppRunConfigBean;
import com.geostar.georobox.management.module.apprunlog.service.impl.AppRunServiceImpl;
import com.geostar.georobox.management.module.errorlog.model.ErrorLogBean;
import com.geostar.georobox.management.module.errorlog.service.impl.ErrorLogServiceImpl;
import com.geostar.georobox.management.module.maintv.model.MainTvBean;
import com.geostar.georobox.management.module.maintv.model.ZeusTvBean;
import com.geostar.georobox.management.module.maintv.service.impl.ZeusTvServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("zeustvrun")
public class ZeusTvController {
	protected static Logger logger = LoggerFactory.getLogger(ZeusTvController.class);
	@Autowired
	private ZeusTvServiceImpl ZeusTvServiceI;
	@Autowired
	private ErrorLogServiceImpl errorLogServiceI;
	@Autowired
	private AppRunServiceImpl appRunServiceI;

	@RequestMapping("/GetZSTvListServlet")
	public ListLimitBean GetZSTvListServlet(ZeusTvBean zeusTvBean) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ZeusTvBean> queryServerUrlList = ZeusTvServiceI.getZeusTvList(zeusTvBean);
		listLimitBean.setData(queryServerUrlList);
		return listLimitBean;
	}

	@RequestMapping("/AddZSTvServlet")
	public RbResultBean AddZSTvServlet(ZeusTvBean zeusTvBean) throws Exception {
		zeusTvBean.setDatetime(new Date());
		int num = ZeusTvServiceI.saveZeusTv(zeusTvBean);
		return RbResultBean.getSuccess(num);
	}

	@RequestMapping("/RemoveZSServlet")
	public RbResultBean RemoveZSServlet(String id) throws Exception {
		int num = ZeusTvServiceI.deleteZeusTv(id);
		return RbResultBean.getSuccess(num);
	}

	@RequestMapping("/ZSTestConnetServlet")
	public RbResultBean ZSTestConnetServlet() throws Exception {
		return RbResultBean.getSuccess("SUCCESS");
	}
	
	@RequestMapping("/MainTVServlet")
	public RbResultBean MainTVServlet(RbParm rbParm) throws Exception {
		RbResultBean rbResultBean = new RbResultBean();
		ListLimitBean listLimitBean = new ListLimitBean();
		AppRunConfigBean appRunConfigBean = new AppRunConfigBean();
		MainTvBean mainTvBean = new MainTvBean();
		String TimeFormat = "yyyy-MM-dd";
		String currentDataTime = new SimpleDateFormat(TimeFormat).format(new Date());
		String startDataTime = new SimpleDateFormat(TimeFormat).format(new Date()) + " 00:00:00";
		String endDataTime = new SimpleDateFormat(TimeFormat).format(new Date()) + " 23:59:59";
		rbParm.setStartTime(currentDataTime);
		rbParm.setEndTime(currentDataTime);
		rbParm.setPage(1);
		rbParm.setLimit(20);
		long errorNum = errorLogServiceI.getBetweenTimeCount(rbParm);
		List<AppRunConfigBean> appRunConfigList = appRunServiceI.queryAppRunConfigList(appRunConfigBean, rbParm);
		long loginNum = appRunServiceI.getBetweenTimeCount(rbParm);
		List<DataInfoBean> plugCountDatas = appRunServiceI.getPlusChartData(rbParm);
		long userNum = plugCountDatas.size();
		List<DataInfoBean> errorNumList = errorLogServiceI.getTodayErrorLogChartData(startDataTime, endDataTime);
		mainTvBean.setUserNum(userNum);
		mainTvBean.setErrorNum(errorNum);
		mainTvBean.setLoginNum(loginNum);
		mainTvBean.setErrorDataInfo(errorNumList);
		mainTvBean.setPlugNumChart(plugCountDatas);
		listLimitBean.setData(appRunConfigList);
		mainTvBean.setListLimitBean(listLimitBean);
		rbResultBean.setData(mainTvBean);
		return rbResultBean;
	}

	private String parse(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
