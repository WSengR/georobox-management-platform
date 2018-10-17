package com.geostar.georobox.management.module.logmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.logmanage.dao.LogManageBeanMapper;
import com.geostar.georobox.management.module.logmanage.dao.provider.LogManageExample;
import com.geostar.georobox.management.module.logmanage.model.LogManageBean;
import com.geostar.georobox.management.module.logmanage.service.LogManageService;

import tk.mybatis.mapper.entity.Example;



@Service
public class LogManageServiceImpl implements LogManageService {
	protected static Logger logger = LoggerFactory.getLogger(LogManageServiceImpl.class);
	@Autowired
	private LogManageBeanMapper logManageBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	
	/**
	 * 添加日志信息
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveLog(LogManageBean logManageBean) {
		int insert = logManageBeanMapper.insertSelective(logManageBean);
		return insert;
	}
	
	/**
	 * 获取日志列表
	 */
	@Override
	public List<LogManageBean> queryLogManageList(LogManageBean logManageBean, RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		Example logManageFilter = LogManageExample.getLogManageFilter(logManageBean, rbParm);	
		logManageFilter.orderBy("datetime").desc();
		List<LogManageBean> logManageBeans = logManageBeanMapper.selectByExample(logManageFilter);
		return logManageBeans;
	}
	
	/**
	 * 获取日志列表数量
	 */
	@Override
	public int getCount(LogManageBean logManageBean, RbParm rbParm) {
		Example logManageFilter = LogManageExample.getLogManageFilter(logManageBean, rbParm);	
		int selectCountByExample = logManageBeanMapper.selectCountByExample(logManageFilter);
		return selectCountByExample;
	}
	
	/**
	 * 根据主键删除日志
	 */
	@Override
	public int deleteLogManage(String id) {
		int insert = logManageBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 根据主键获取日志文件地址 
	 */
	@Override
	public String getFileName(String id) {
		String fileName = logManageBeanMapper.getFileName(id);
		return fileName;
	}

}
