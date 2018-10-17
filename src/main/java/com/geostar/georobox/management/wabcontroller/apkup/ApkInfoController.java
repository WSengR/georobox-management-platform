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
import com.geostar.georobox.management.module.apkup.model.ApkInfoBean;
import com.geostar.georobox.management.module.apkup.service.impl.ApkInfoServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("apkuprun")
public class ApkInfoController {
	protected static Logger logger = LoggerFactory.getLogger(ApkInfoController.class);
	@Autowired
	private ApkInfoServiceImpl apkInfoService;
	
	/**
	 * 获取APK历史版本列表
	 * @param apkInfoBean
	 * @param rbParm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetApkVersionListServlet")
	public ListLimitBean GetApkVersionListServlet(ApkInfoBean apkInfoBean,RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<ApkInfoBean> queryApkInfoList = apkInfoService.getApkInfoList(apkInfoBean, rbParm);
		listLimitBean.setData(queryApkInfoList);
		int count = apkInfoService.getCount(apkInfoBean, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}
	
	/**
	 * 上传APK内容
	 * @param apkInfoBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/UploadApkInfoServlet")
	public RbResultBean UploadApkInfoServlet(ApkInfoBean apkInfoBean) throws Exception {
		apkInfoBean.setDatetime(new Date());
		int num = apkInfoService.saveApkInfo(apkInfoBean);
		return RbResultBean.getSuccess(num);
	}
	
	/**
	 * 删除APK历史版本
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/DeleteApkServlet")
	public RbResultBean DeleteApkServlet(String id) throws Exception {
		int num = apkInfoService.deleteApkInfo(id);
		return RbResultBean.getSuccess(num);
	}
	
}
