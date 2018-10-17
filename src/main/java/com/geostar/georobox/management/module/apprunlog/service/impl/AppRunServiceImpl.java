package com.geostar.georobox.management.module.apprunlog.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.apprunlog.dao.AppRunConfigBeanMapper;
import com.geostar.georobox.management.module.apprunlog.dao.provider.AppConfigExample;
import com.geostar.georobox.management.module.apprunlog.model.AppRunConfigBean;
import com.geostar.georobox.management.module.apprunlog.model.RunStateModeCountBean;
import com.geostar.georobox.management.module.apprunlog.service.AppRunService;

import tk.mybatis.mapper.entity.Example;

@Service
public class AppRunServiceImpl implements AppRunService {
	protected static Logger logger = LoggerFactory.getLogger(AppRunServiceImpl.class);
	@Autowired
	private AppRunConfigBeanMapper appRunConfigBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private AppConfigExample appConfigExample;

	@Override
	public void GetAppStatistics(RbParm baseRequestParmBean) {

	}
	/**
	 * 插入保存
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(AppRunConfigBean appRunConfigBean) {
		appRunConfigBean.setDatetime(new Date());
		int insert = appRunConfigBeanMapper.insertSelective(appRunConfigBean);
		return insert;
	}
	/**
	 * 查询列表，分页查询
	 */
	@Override
	public List<AppRunConfigBean> queryAppRunConfigList(AppRunConfigBean appRunConfigBean, RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		Example appConfigFilter = appConfigExample.getAppConfigFilter(appRunConfigBean, rbParm);
		// 通过datetime倒序排列
		appConfigFilter.orderBy("datetime").desc();
		logger.info("【appConfigFilter】=");
		List<AppRunConfigBean> runConfigBeans = appRunConfigBeanMapper.selectByExample(appConfigFilter);
		return runConfigBeans;
	}

	@Override
	public long count(AppRunConfigBean appRunConfigBean, RbParm rbParm) {
		Example appConfigFilter = appConfigExample.getAppConfigFilter(appRunConfigBean, rbParm);
		int selectCountByExample = appRunConfigBeanMapper.selectCountByExample(appConfigFilter);
		return selectCountByExample;
	}

	@Override
	public List<DataInfoBean> getRunChartData() {
		return appRunConfigBeanMapper.getRunChartData();
	}
	/**
	 * 查询User饼状图
	 */
	@Override
	public List<DataInfoBean> getUserChartData(RbParm rbParm) {
		return appRunConfigBeanMapper.getUserChartData(rbParm);
	}
	
	/**
	 * 查询插件饼状图
	 */
	@Override
	public List<DataInfoBean> getPlusChartData(RbParm rbParm) {
		return appRunConfigBeanMapper.getPlusChartData(rbParm);
	}
	/**
	 * 查询Package饼状图
	 */
	@Override
	public List<DataInfoBean> getPackageChartData(RbParm rbParm) {
		return appRunConfigBeanMapper.getPackageChartData(rbParm);
	}

	@Override
	public long getBetweenTimeCount(RbParm rbParm) {
		return appRunConfigBeanMapper.betweenTimeCount(rbParm);
	}
	
	/**
	 * 获取饼状图
	 */
	@Override
	public RunStateModeCountBean getAppStatistics(RbParm rbParm) {
		RunStateModeCountBean runStateModeCountBean = new RunStateModeCountBean();
		runStateModeCountBean.setRunNum(getBetweenTimeCount(rbParm));
		runStateModeCountBean.setPlugCountDatas(getPlusChartData(rbParm));
		runStateModeCountBean.setRunCountDatas(getRunChartData());
		runStateModeCountBean.setPackageCountDatas(getPackageChartData(rbParm));
		runStateModeCountBean.setUserCountDatas(getUserChartData(rbParm));
		return runStateModeCountBean;
	}

}
