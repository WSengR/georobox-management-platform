package com.geostar.georobox.management.module.plugmanage.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.plugmanage.dao.PlugBeanMapper;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugExample;
import com.geostar.georobox.management.module.plugmanage.model.PlugBean;
import com.geostar.georobox.management.module.plugmanage.model.UserPlugBean;
import com.geostar.georobox.management.module.plugmanage.service.PlugService;

import tk.mybatis.mapper.entity.Example;

@Service
public class PlugServiceImpl implements PlugService {
	protected static Logger logger = LoggerFactory.getLogger(PlugServiceImpl.class);
	@Autowired
	private PlugBeanMapper plugBeanMapper;
	@Autowired
	private PlugPermissionServiceImpl permissionServiceImpl;
	@Autowired
	private PlugCategoryServiceImpl categoryServiceImpl;
	@Autowired
	private PlugAuditServiceImpl auditServiceImpl;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private PlugExample plugExample;
	@Autowired
	private RbFileUtils rbfileUtils;

	@Override
	public int savePlug(PlugBean plugBean) {
		return plugBeanMapper.insertSelective(plugBean);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RbResultBean deletePlug(String plugId) {
		if (auditServiceImpl.haveAuditPlugByPlugId(plugId)) {
			return RbResultBean.getError("该插件已在审核队列中，请先删除审核中的该插件!");
		}
		permissionServiceImpl.deletePAPByPlugId(plugId);
		categoryServiceImpl.deletePACByPlugId(plugId);
		PlugBean plugBean = plugBeanMapper.selectByPrimaryKey(plugId);
		int deleteByPrimaryKey = plugBeanMapper.deleteByPrimaryKey(plugId);
		if (deleteByPrimaryKey > 0 && plugBean != null) {
			rbfileUtils.deleteServerFileToRecycle(plugBean.getPlugUrl());
			rbfileUtils.deleteServerFileToRecycle(plugBean.getPlugIcon());
		}
		return RbResultBean.getResultBack(deleteByPrimaryKey);
	}

	@Override
	public PlugBean selectPlug(String plugId) {
		return plugBeanMapper.selectByPrimaryKey(plugId);
	}

	@Override
	public PlugBean selectResultPlus(String plugId) {
		PlugBean selectPlug = selectPlug(plugId);
		selectPlug.setPermissions(permissionServiceImpl.selectPAPByPlugId(plugId));
		selectPlug.setCategorys(categoryServiceImpl.selectPACByPlugId(plugId));
		return selectPlug;
	}

	@Override
	public int updatePlug(PlugBean plugBean) {
		return plugBeanMapper.updateByPrimaryKeySelective(plugBean);
	}

	@Override
	public List<PlugBean> selectPlugList(RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		Example example = plugExample.plugFilter(rbParm);
		example.orderBy("plugSort");
		return plugBeanMapper.selectByExample(example);
	}

	/**
	 * 上下架
	 */
	@Override
	public int outAndUpPlug(String plugId) {
		PlugBean selectPlug = selectPlug(plugId);
		if (selectPlug.getPlugIsDown() > 0) {
			selectPlug.setPlugDownDate(new Date());
			selectPlug.setPlugIsDown((short) 0);
		} else {
			selectPlug.setPlugUpDate(new Date());
			selectPlug.setPlugIsDown((short) 1);
		}
		int updatePlug = updatePlug(selectPlug);
		return updatePlug;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int movePlusUp(String plugId) {
		PlugBean selectPlug = selectPlug(plugId);
		Long plugSort = selectPlug.getPlugSort();
		PlugBean selectUpPlug = plugBeanMapper.selectUpPlug(selectPlug.getPlugSort());
		if (selectUpPlug == null)
			return 0;
		selectPlug.setPlugSort(selectUpPlug.getPlugSort());
		selectUpPlug.setPlugSort(plugSort);
		updatePlug(selectPlug);
		updatePlug(selectUpPlug);
		return 1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int movePlusDown(String plugId) {
		PlugBean selectPlug = selectPlug(plugId);
		Long plugSort = selectPlug.getPlugSort();
		PlugBean selectDownPlug = plugBeanMapper.selectDownPlug(selectPlug.getPlugSort());
		if (selectDownPlug == null)
			return 0;
		selectPlug.setPlugSort(selectDownPlug.getPlugSort());
		selectDownPlug.setPlugSort(plugSort);
		updatePlug(selectPlug);
		updatePlug(selectDownPlug);
		return 1;
	}

	@Override
	public int selectPlugCount(RbParm rbParm) {
		Example example = plugExample.plugFilter(rbParm);
		example.orderBy("plugSort").desc();
		return plugBeanMapper.selectCountByExample(example);
	}

	@Override
	public List<PlugBean> selectPlugByPermission(String permission) {

		return null;
	}

	/**
	 * 多个权限分号分割
	 */
	@Override
	public List<UserPlugBean> selectUserPlugByPermission(String permission) {
		return plugBeanMapper.selectUserPlugBean(permission);
	}

	@Override
	public List<UserPlugBean> selectUserUsingPlug(String userId, String permission) {
		List<UserPlugBean> userUsingPlugBean = plugBeanMapper.userUsingPlugBean(userId);
		if(!StringUtils.isEmpty(permission)) {
			List<UserPlugBean> selectUserAddPlugBean = plugBeanMapper.selectUserAddPlugBean(permission);
			if (selectUserAddPlugBean != null && selectUserAddPlugBean.size() > 0) {
				userUsingPlugBean.addAll(selectUserAddPlugBean);
			}
		}

		return userUsingPlugBean;
	}

}
