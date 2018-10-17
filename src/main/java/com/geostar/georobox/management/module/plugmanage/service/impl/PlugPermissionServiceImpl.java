package com.geostar.georobox.management.module.plugmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.plugmanage.dao.PlugAndPermissionMapper;
import com.geostar.georobox.management.module.plugmanage.dao.PlugPermissionBeanMapper;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugExample;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndPermission;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;
import com.geostar.georobox.management.module.plugmanage.service.PlugPermissionService;

import tk.mybatis.mapper.entity.Example;

@Service
public class PlugPermissionServiceImpl implements PlugPermissionService {
	protected static Logger logger = LoggerFactory.getLogger(PlugPermissionServiceImpl.class);

	@Autowired
	private PlugPermissionBeanMapper permissionBeanMapper;
	@Autowired
	private PlugAndPermissionMapper papMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private PlugExample plugExample;

	/**
	 * 保存权限
	 */
	@Override
	public int save(PlugPermissionBean plugPermissionBean) {
		PlugPermissionBean selectPermissionByName = selectPermissionByName(plugPermissionBean.getPermissionId(),
				plugPermissionBean.getPermissionName());
		if (selectPermissionByName != null) {
			return -1;
		}
		return permissionBeanMapper.insertSelective(plugPermissionBean);
	}

	/**
	 * 删除权限【同时删除权限和插件关联条目】
	 */
	@Override
	public int delete(String permissionId) {
		deletePAPByPermissionId(permissionId);
		return permissionBeanMapper.deleteByPrimaryKey(permissionId);
	}

	/**
	 * 更新权限
	 */
	@Override
	public int update(PlugPermissionBean plugPermissionBean) {
		PlugPermissionBean selectPermissionByName = selectPermissionByName(plugPermissionBean.getPermissionId(),
				plugPermissionBean.getPermissionName());
		if (selectPermissionByName != null) {
			return -1;
		}
		return permissionBeanMapper.updateByPrimaryKeySelective(plugPermissionBean);
	}

	@Override
	public List<PlugPermissionBean> selectPermissionList(RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		Example example = new Example(PlugPermissionBean.class);
		example.orderBy("permissionName");
		return permissionBeanMapper.selectAll();
	}

	@Override
	public PlugPermissionBean selectPermissionById(String permissionId) {
		return permissionBeanMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public PlugPermissionBean selectPermissionByName(String permissionId, String permissionName) {
		Example permissionNameFilter = plugExample.permissionNameFilter(permissionId, permissionName);
		return permissionBeanMapper.selectOneByExample(permissionNameFilter);
	}

	@Override
	public int savePAP(PlugAndPermission plugAndPermission) {
		return papMapper.insertSelective(plugAndPermission);
	}

	@Override
	public int selectPAPCount(PlugAndPermission plugAndPermission) {
		return papMapper.selectCount(plugAndPermission);
	}

	@Override
	public int deletePAPByPlugId(String plugId) {
		return papMapper.deleteByExample(plugExample.deletePAPFilter(plugId, null));
	}

	@Override
	public int deletePAPByPermissionId(String permissionId) {
		return papMapper.deleteByExample(plugExample.deletePAPFilter(null, permissionId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePAPByPermissions(String plugId, String permissions) {
		if (StringUtils.isEmpty(plugId) || StringUtils.isEmpty(permissions)) {
			return;
		}
		deletePAPByPlugId(plugId);
		if (!StringUtils.isEmpty(permissions)) {
			String[] split = permissions.split(";");
			for (int i = 0; i < split.length; i++) {
				if (StringUtils.isEmpty(split[i]))
					continue;
				PlugAndPermission plugAndPermission = new PlugAndPermission();
				plugAndPermission.setPlugId(plugId);
				plugAndPermission.setPermissionId(split[i]);
				PlugPermissionBean plugPermissionBean = selectPermissionById(plugAndPermission.getPermissionId());
				if (plugPermissionBean == null)
					continue;
				savePAP(plugAndPermission);
			}
		}
	}

	@Override
	public List<PlugPermissionBean> selectPAPByPlugId(String plugId) {
		return papMapper.selectPAPByPlugId(plugId);
	}

	@Override
	public int selectPermissionCount(RbParm rbParm) {
		return permissionBeanMapper.selectCount(null);
	}

	@Override
	public List<PlugPermissionBean> selectPermissionByIds(String permissionIds) {
		List<PlugPermissionBean> plugPermissionBeans = new ArrayList<>();
		if (!StringUtils.isEmpty(permissionIds)) {
			String[] split = permissionIds.split(";");
			for (int i = 0; i < split.length; i++) {
				if (StringUtils.isEmpty(split[i]))
					continue;
				plugPermissionBeans.add(selectPermissionById(split[i]));
			}
		}
		return plugPermissionBeans;
	}

}
