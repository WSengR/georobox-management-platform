package com.geostar.georobox.management.module.errorlog.service.impl;

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
import com.geostar.georobox.management.module.errorlog.dao.ErrorLogBeanMapper;
import com.geostar.georobox.management.module.errorlog.dao.provider.ErrorLogExample;
import com.geostar.georobox.management.module.errorlog.model.ErrorLogBean;
import com.geostar.georobox.management.module.errorlog.service.ErrorLogService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ErrorLogServiceImpl implements ErrorLogService {
	protected static Logger logger = LoggerFactory.getLogger(ErrorLogServiceImpl.class);
	@Autowired
	private ErrorLogBeanMapper errorLogBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private ErrorLogExample errorLogExample;
	
	/**
	 * 保存错误日志对象
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveErrorLog(ErrorLogBean errorLogBean) {
		int insert = errorLogBeanMapper.insertSelective(errorLogBean);
		return insert;
	}
	
	/**
	 * 获取错误日志列表
	 */
	@Override
	public List<ErrorLogBean> queryErrorLog(ErrorLogBean errorLogBean, RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		Example errorLogFilter = errorLogExample.getErrorLogFilter(errorLogBean, rbParm);
		errorLogFilter.orderBy("datetime").desc();
		List<ErrorLogBean> errorLogBeans = errorLogBeanMapper.selectByExample(errorLogFilter);
		return errorLogBeans;
	}
	
	/**
	 * 获取错误日志列表总数
	 */
	@Override
	public int getCount(ErrorLogBean errorLogBean, RbParm rbParm) {
		Example errorLogFilter = errorLogExample.getErrorLogFilter(errorLogBean, rbParm);
		int selectCountByExample = errorLogBeanMapper.selectCountByExample(errorLogFilter);
		return selectCountByExample;
	}
	
	/**
	 * 修改处理状态
	 */
	@Override
	public int changeIsCompleted(ErrorLogBean errorLogBean) {
		int selectCountByExample = errorLogBeanMapper.updateByPrimaryKeySelective(errorLogBean);
		return selectCountByExample;
	}
	
	/**
	 * 根据主键删除错误日志
	 */
	@Override
	public int deleteErrorLog(String id) {
		int insert = errorLogBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 根据主键获取文件名称
	 */
	@Override
	public String getFileName(String id) {
		String fileName = errorLogBeanMapper.getFileName(id);
		return fileName;
	}
	
	/**
	 * 获取超过30天的错误日志内容
	 */
	@Override
	public List<String> getOutlastErrorLog() {
		List<String> errorLogBeans = errorLogBeanMapper.getOutlastErrorLog();
		return errorLogBeans;
	}
	
	/**
	 * 获取近期错误日志信息
	 */
	@Override
	public List<DataInfoBean> getErrorLogChartData() {
		return errorLogBeanMapper.getErrorLogChartData();
	}
	
	/**
	 * 根据时间间隔获取错误日志数量
	 */
	@Override
	public long getBetweenTimeCount(RbParm rbParm) {
		Example errorLogFilter = errorLogExample.getErrorLogFilter(rbParm);
		long count = errorLogBeanMapper.selectCountByExample(errorLogFilter);
		return count;
	}
	
	/**
	 * 根据时间间隔获取高频设备
	 */
	@Override
	public List<DataInfoBean> getModeChartData(RbParm rbParm) {
		return errorLogBeanMapper.getModeChartData(rbParm);
	}
	
	/**
	 * 根据时间间隔获取高频用户
	 */
	@Override
	public List<DataInfoBean> getUserChartData(RbParm rbParm) {
		return errorLogBeanMapper.getUserChartData(rbParm);
	}
	
	@Override
	public List<DataInfoBean> getTodayErrorLogChartData(String startTime, String endTime){
		return errorLogBeanMapper.getTodayErrorLogChartData(startTime, endTime);
	}
	

}
