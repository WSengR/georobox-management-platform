package com.geostar.georobox.management.module.apprunlog.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.apprunlog.model.AppRunConfigBean;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * 描述：插件Example提供者
 * 
 * @author wangsr
 * @date 2018年9月14日
 */
@Configuration
public class AppConfigExample extends RbExample{

	/**
	 * 筛选 模糊查询
	 * 
	 * @param appRunConfigBean
	 * @param rbParm
	 * @return
	 */
	public Example getAppConfigFilter(AppRunConfigBean appRunConfigBean, RbParm rbParm) {
		Example example = new Example(AppRunConfigBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(rbParm.searchKey)) {
			criteria.orLike("appName", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoUser", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoOperation", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoOther", "%" + rbParm.searchKey + "%");
		}
		Example.Criteria criteria2 = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(appRunConfigBean.getAppName())) {
			criteria2.andEqualTo("appName", appRunConfigBean.getAppName());
		}
		if (!StringUtils.isEmptyOrWhitespace(appRunConfigBean.getAppPackage())) {
			criteria2.andEqualTo("appPackage", appRunConfigBean.getAppPackage());
		}
		if (!StringUtils.isEmptyOrWhitespace(appRunConfigBean.getInfoUser())) {
			criteria2.andEqualTo("infoUser", appRunConfigBean.getInfoUser());
		}
		//添加时间范围查询
		criteria2 = addAndBetweenTime(criteria2 ,rbParm);
		example.and(criteria);
		example.and(criteria2);
		return example;
	}

	
}
