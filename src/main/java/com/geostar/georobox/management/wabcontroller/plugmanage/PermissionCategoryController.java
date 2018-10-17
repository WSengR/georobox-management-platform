package com.geostar.georobox.management.wabcontroller.plugmanage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.plugmanage.service.PlugAuditService;
import com.geostar.georobox.management.module.plugmanage.service.PlugCategoryService;
import com.geostar.georobox.management.module.plugmanage.service.PlugPermissionService;
import com.geostar.georobox.management.module.plugmanage.service.PlugService;
import com.geostar.georobox.management.module.plugmanage.service.impl.PlugAuditServiceImpl;
import com.geostar.georobox.management.module.plugmanage.service.impl.PlugCategoryServiceImpl;
import com.geostar.georobox.management.module.plugmanage.service.impl.PlugPermissionServiceImpl;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndCategory;
import com.geostar.georobox.management.module.plugmanage.model.PlugCategoryBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;

import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("plug")
public class PermissionCategoryController {
	protected static Logger logger = LoggerFactory.getLogger(PermissionCategoryController.class);
	@Autowired
	private PlugPermissionServiceImpl plugPermissionService;
	@Autowired
	private PlugCategoryServiceImpl plugCategoryService;

	/** ========================= 插件权限 ======================== */
	/**
	 * 添加权限
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/permissionSave")
	public RbResultBean permissionSave(PlugPermissionBean plugPermissionBean) {
		logger.info("【/plugPermissionBean】 " + plugPermissionBean.toString());
		int save = plugPermissionService.save(plugPermissionBean);
		if (save == -1) {
			return RbResultBean.getError("该权限已存在,请重新输入权限名");
		}
		return RbResultBean.getResultBack(save);

	}

	/**
	 * 修改
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/permissionChange")
	public RbResultBean permissionChange(PlugPermissionBean plugPermissionBean) {
		logger.info("【/plugPermissionBean】 " + plugPermissionBean.toString());
		int update = plugPermissionService.update(plugPermissionBean);
		if (update == -1) {
			return RbResultBean.getError("该权限已存在,请重新输入权限名");
		}
		return RbResultBean.getResultBack(update);
	}

	/**
	 * 删除权限
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/permissionDelete")
	public RbResultBean permissionDelete(String permissionId) {
		logger.info("【/plugPermissionBean】 " + permissionId);
		int delete = plugPermissionService.delete(permissionId);
		// 删除所有插件对应的权限
		// 删除所有用户对应的权限
		return RbResultBean.getResultBack(delete);
	}

	/**
	 * 权限列表
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/permissionList")
	public ListLimitBean permissionList(RbParm rbParm) {
		logger.info("【/RbParm】 " + rbParm.toString());
		List<PlugPermissionBean> selectPermissionList = plugPermissionService.selectPermissionList(rbParm);
		ListLimitBean listLimitBean = new ListLimitBean();
		listLimitBean.setData(selectPermissionList);
		listLimitBean.setCount(plugPermissionService.selectPermissionCount(rbParm));
		return listLimitBean;
	}

	/** ========================= 插件分类 ======================== */
	/**
	 * 添加分类
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/categorySave")
	public RbResultBean categorySave(PlugCategoryBean plugCategoryBean) {
		logger.info("【/plugCategoryBean】 " + plugCategoryBean.toString());
		int save = plugCategoryService.save(plugCategoryBean);
		if (save == -1) {
			return RbResultBean.getError("该分类已存在,请重新输入分类名");
		}
		return RbResultBean.getResultBack(save);

	}

	/**
	 * 修改分类
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/categoryChange")
	public RbResultBean categoryChange(PlugCategoryBean plugCategoryBean) {
		logger.info("【/plugPermissionBean】 " + plugCategoryBean.toString());
		int update = plugCategoryService.update(plugCategoryBean);
		if (update == -1) {
			return RbResultBean.getError("该分类已存在,请重新输入分类名");
		}
		return RbResultBean.getResultBack(update);
	}

	/**
	 * 删除权限
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/categoryDelete")
	public RbResultBean categoryDelete(String categoryId) {
		logger.info("【/plugPermissionBean】 " + categoryId);
		int delete = plugCategoryService.delete(categoryId);
		// 删除所有插件对应的权限
		return RbResultBean.getResultBack(delete);
	}

	/**
	 * 权限列表
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/categoryList")
	public ListLimitBean categoryList(RbParm rbParm) {
		logger.info("【/RbParm】 " + rbParm.toString());
		List<PlugCategoryBean> selectCategoryList = plugCategoryService.selectCategoryList(rbParm);
		ListLimitBean listLimitBean = new ListLimitBean();
		listLimitBean.setData(selectCategoryList);
		listLimitBean.setCount(plugCategoryService.selectCategoryCount(rbParm));
		return listLimitBean;
	}
}
