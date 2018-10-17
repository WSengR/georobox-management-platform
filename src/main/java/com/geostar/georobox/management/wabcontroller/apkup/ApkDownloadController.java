package com.geostar.georobox.management.wabcontroller.apkup;

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
import com.geostar.georobox.management.module.apkup.model.ApkDownloadBean;
import com.geostar.georobox.management.module.apkup.service.impl.ApkDownloadServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("apkuprun")
public class ApkDownloadController {
	protected static Logger logger = LoggerFactory.getLogger(ApkDownloadController.class);
	@Autowired
	private ApkDownloadServiceImpl apkDownloadService;
	
	/**
	 * 获取APK下载记录
	 * @param apkDownloadBean
	 * @param rbParm
	 * @param version
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetApkLoadInfoServlet")
	public ListLimitBean GetApkLoadInfoServlet(ApkDownloadBean apkDownloadBean,RbParm rbParm, String version) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ApkDownloadBean> queryApkDownloadList = apkDownloadService.getApkDownloadList(apkDownloadBean, rbParm, version);
		listLimitBean.setData(queryApkDownloadList);
		int count = apkDownloadService.getCount(apkDownloadBean, rbParm, version);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 上传apk下载信息
	 * @param apkDownloadBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/PostLoadInfoServlet")
	public RbResultBean PostLoadInfoServlet(ApkDownloadBean apkDownloadBean) throws Exception {
		apkDownloadBean.setDatetime(new Date());
		int num = apkDownloadService.saveApkDownload(apkDownloadBean);
		return RbResultBean.getSuccess(num);
	}
	
}
