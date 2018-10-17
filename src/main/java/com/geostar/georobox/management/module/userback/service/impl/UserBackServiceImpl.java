package com.geostar.georobox.management.module.userback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.userback.dao.UserBackBeanMapper;
import com.geostar.georobox.management.module.userback.dao.provider.UserBackExample;
import com.geostar.georobox.management.module.userback.model.UserBackBean;
import com.geostar.georobox.management.module.userback.service.UserBackService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserBackServiceImpl implements UserBackService {

	@Autowired
	private UserBackBeanMapper userBackBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private UserBackExample userBackExample;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveUserBack(UserBackBean userBackBean) {
		int insert = userBackBeanMapper.insertSelective(userBackBean);
		return insert;
	}
	
	/**
	 * 获取反馈信息列表（包含模糊查询和时间查询）
	 */
	@Override
	public List<UserBackBean> queryUserBackList(UserBackBean userBackBean, RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		Example userBackFilter = userBackExample.getUserbackFilter(userBackBean, rbParm);
		userBackFilter.orderBy("datetime").desc();
		List<UserBackBean> userBackBeans = userBackBeanMapper.selectByExample(userBackFilter);
		return userBackBeans;
	}
	
	/**
	 * 获取用户反馈数量
	 */
	@Override
	public int getCount(UserBackBean userBackBean, RbParm rbParm) {
		Example userBackFilter = userBackExample.getUserbackFilter(userBackBean, rbParm);
		int selectCountByExample = userBackBeanMapper.selectCountByExample(userBackFilter);
		return selectCountByExample;
	}
	
	/**
	 * 删除用户反馈信息
	 */
	@Override
	public int deleteUserBack(String id) {
		int insert = userBackBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 根据主键获取反馈图片地址 
	 */
	@Override
	public String getFileName(String id) {
		String fileName = userBackBeanMapper.getFileName(id);
		return fileName;
	}

}
