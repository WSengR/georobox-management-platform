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
import com.geostar.georobox.management.common.utils.RbFileUtils;
import com.geostar.georobox.management.common.utils.ReadApkUtil;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.plugmanage.dao.PlugAuditBeanMapper;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugExample;
import com.geostar.georobox.management.module.plugmanage.model.PlugAuditBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;
import com.geostar.georobox.management.module.plugmanage.service.PlugAuditService;

import tk.mybatis.mapper.entity.Example;

@Service
public class PlugAuditServiceImpl implements PlugAuditService {
	protected static Logger logger = LoggerFactory.getLogger(PlugAuditServiceImpl.class);
	@Autowired
	private PlugAuditBeanMapper plugAuditBeanMapper;
	@Autowired
	private PlugServiceImpl plugServiceImpl;
	@Autowired
	private PlugPermissionServiceImpl permissionServiceImpl;
	@Autowired
	private PlugCategoryServiceImpl categoryServiceImpl;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private PlugExample plugExample;
	@Autowired
	private RbFileUtils rbFileUtils;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveAuditPlug(PlugAuditBean plugAuditBean) {
		if (StringUtils.isEmpty(plugAuditBean.getPlugId())) {
			plugAuditBean.setPlugSort(sqlHelper.getSystemTep());
		}
		if (plugAuditBean.getPlugUrl().contains(".apk")) {
			String uploadFileAbsolutePath = rbFileUtils.getUploadFileAbsolutePath(plugAuditBean.getPlugUrl());
			String versionCode = ReadApkUtil.getVersionCode(System.currentTimeMillis() + "", uploadFileAbsolutePath);
			plugAuditBean.setPlugVersioncode(versionCode);
		}else {
			plugAuditBean.setPlugVersioncode(System.currentTimeMillis()+"");
		}
		return plugAuditBeanMapper.insertSelective(plugAuditBean);
	}

	@Override
	public int deleteAuditPlug(String plugTempId) {
		return plugAuditBeanMapper.deleteByPrimaryKey(plugTempId);
	}

	@Override
	public int updateAuditPlug(PlugAuditBean plugAuditBean) {
		return plugAuditBeanMapper.updateByPrimaryKeySelective(plugAuditBean);
	}

	@Override
	public PlugAuditBean selectAuditPlug(String plugTempId) {
		PlugAuditBean selectByPrimaryKey = plugAuditBeanMapper.selectByPrimaryKey(plugTempId);
		List<PlugPermissionBean> plugPermissionList = null;
		if (!StringUtils.isEmpty(selectByPrimaryKey.getPlugPermission())) {
			plugPermissionList = permissionServiceImpl.selectPermissionByIds(selectByPrimaryKey.getPlugPermission());
		}
		selectByPrimaryKey.setPlugPermissionList(plugPermissionList);
		return selectByPrimaryKey;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int auditPlug(String plugTempId) {
		// 查询审核插件
		PlugAuditBean plugAuditBean = selectAuditPlug(plugTempId);
		PlugBean plugBean = new PlugBean();
		plugBean.setPlugDetails(plugAuditBean.getPlugDetails());
		plugBean.setPlugIcon(plugAuditBean.getPlugIcon());
		plugBean.setPlugName(plugAuditBean.getPlugName());
		plugBean.setPlugPackage(plugAuditBean.getPlugPackage());
		plugBean.setPlugType(plugAuditBean.getPlugType());
		plugBean.setPlugUrl(plugAuditBean.getPlugUrl());
		plugBean.setPlugVersioncode(plugAuditBean.getPlugVersioncode());
		plugBean.setPlugSort(plugAuditBean.getPlugSort());
		plugBean.setPlugIsDown((short) 1);
		plugBean.setPlugUpDate(new Date());
		plugBean.setPlugNeedinstall(plugAuditBean.getPlugNeedinstall());
		plugBean.setPlugLauncherActivity(plugAuditBean.getPlugLauncherActivity());
		int back = 0;
		if (StringUtils.isEmpty(plugAuditBean.getPlugId())) {
			plugBean.setPlugId(sqlHelper.getUUID());
			back = plugServiceImpl.savePlug(plugBean);
		} else {
			plugBean.setPlugId(plugAuditBean.getPlugId());
			back = plugServiceImpl.updatePlug(plugBean);
		}
		// 权限和分类重新生成
		permissionServiceImpl.savePAPByPermissions(plugBean.getPlugId(), plugAuditBean.getPlugPermission());
		categoryServiceImpl.savePACByCategorys(plugBean.getPlugId(), plugAuditBean.getPlugCategory());
		// 审核完成删除审核列表中的该插件
		plugAuditBeanMapper.deleteByPrimaryKey(plugTempId);
		return back;
	}

	@Override
	public List<PlugAuditBean> selectAuditPlugList(RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		return plugAuditBeanMapper.selectAll();
	}

	@Override
	public int selectPlugCount(RbParm rbParm) {
		Example example = new Example(PlugAuditBean.class);
		return plugAuditBeanMapper.selectCountByExample(example);
	}

	@Override
	public boolean haveAuditPlugByPlugId(String plugId) {
		List<PlugAuditBean> selectByExample = plugAuditBeanMapper
				.selectByExample(plugExample.plugAuditBeanFilter(plugId));
		if (selectByExample != null && selectByExample.size() > 0) {
			return true;
		}
		return false;
	}

}
