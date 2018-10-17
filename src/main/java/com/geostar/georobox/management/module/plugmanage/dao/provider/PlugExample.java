package com.geostar.georobox.management.module.plugmanage.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndCategory;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndPermission;
import com.geostar.georobox.management.module.plugmanage.model.PlugAuditBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugCategoryBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugUserBean;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * 描述：插件Example提供者
 * 
 * @author wangsr
 * @date 2018年9月14日
 */
@Configuration
public class PlugExample extends RbExample{

	/**
	 * 
	 * @param plugAndPermission
	 * @param rbParm
	 * @return
	 */
	public Example plugAndPermissionFilter(PlugAndPermission plugAndPermission) {
		Example example = new Example(PlugAndPermission.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(plugAndPermission.getPlugId())) {
			criteria.orEqualTo("plugId", plugAndPermission.getPlugId());
		}
//		if (!StringUtils.isEmptyOrWhitespace(plugAndPermission.getPermissionName())) {
//			criteria.orEqualTo("permissionName", plugAndPermission.getPermissionName());
//		}
		example.or(criteria);
		return example;
	}

	/**
	 * 
	 * @param plugAndPermission
	 * @param rbParm
	 * @return
	 */
	public Example plugAndCategoryFilter(PlugAndCategory plugAndCategory) {
		Example example = new Example(PlugAndCategory.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(plugAndCategory.getPlugId())) {
			criteria.orEqualTo("plugId", plugAndCategory.getPlugId());
		}
		if (!StringUtils.isEmptyOrWhitespace(plugAndCategory.getCategoryId())) {
			criteria.orEqualTo("categoryId", plugAndCategory.getCategoryId());
		}
		example.or(criteria);
		return example;
	}
	
	/**
	 * 
	 * @param plugAndPermission
	 * @param rbParm
	 * @return
	 */
	public Example permissionNameFilter(String permissionId,String permissionName) {
		Example example = new Example(PlugPermissionBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(permissionName)) {
			criteria.andEqualTo("permissionName", permissionName);
		}
		if (!StringUtils.isEmptyOrWhitespace(permissionId)) {
			criteria.andNotEqualTo("permissionId", permissionId);
		}
		example.and(criteria);
		return example;
	}

	public Example categoryNameFilter(String categoryId,String categoryName) {
		Example example = new Example(PlugCategoryBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(categoryName)) {
			criteria.andEqualTo("categoryName", categoryName);
		}
		if (!StringUtils.isEmptyOrWhitespace(categoryId)) {
			criteria.andNotEqualTo("categoryId", categoryId);
		}
		example.and(criteria);
		return example;
	}
	
	public Example deletePAPFilter(String plugId,String permissionId) {
		Example example = new Example(PlugAndPermission.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(plugId)) {
			criteria.andEqualTo("plugId", plugId);
		}
		if (!StringUtils.isEmptyOrWhitespace(permissionId)) {
			criteria.andEqualTo("permissionId", permissionId);
		}
		example.and(criteria);
		return example;
	}
	
	public Example deletePACFilter(String plugId,String categoryId) {
		Example example = new Example(PlugAndCategory.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(plugId)) {
			criteria.andEqualTo("plugId", plugId);
		}
		if (!StringUtils.isEmptyOrWhitespace(categoryId)) {
			criteria.andEqualTo("categoryId", categoryId);
		}
		example.and(criteria);
		return example;
	}
	
	
	/**
	 * 插件bean
	 * 
	 * @param plugBean
	 * @param rbParm
	 * @return
	 */
	public Example plugFilter(RbParm rbParm) {
		Example example = new Example(PlugBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(rbParm.searchKey)) {
			criteria.orLike("plugName", "%" + rbParm.searchKey + "%");
			criteria.orLike("plugPackage", "%" + rbParm.searchKey + "%");
			criteria.orLike("plugDetails", "%" + rbParm.searchKey + "%");
		}
		criteria = addAndBetweenTime(criteria, rbParm,"plugUpDate");
		example.and(criteria);
		return example;
	}
	
	/**
	 * 删除用户管理插件
	 * 
	 * @param plugBean
	 * @param rbParm
	 * @return
	 */
	public Example deletePlugUserFilter(String userId,String plugId) {
		Example example = new Example(PlugUserBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("plugId", plugId);
		example.and(criteria);
		return example;
	}
	
	/**========================审核========================*/
	
	
	/**
	 *  获取带有plugId的审核插件
	 */
	public Example plugAuditBeanFilter(String plugId) {
		Example example = new Example(PlugAuditBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("plugId", plugId);
		example.and(criteria);
		return example;
	}
	
	
	
	
	
	
	
	
	
	
	
}
