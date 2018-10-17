package com.geostar.georobox.management.module.errorlog.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.errorlog.model.ErrorLogBean;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class ErrorLogExample extends RbExample{
	/**
	 * 根据查询条件筛选内容
	 * @param errorLogBean
	 * @param rbParm
	 * @return
	 */
	public Example getErrorLogFilter(ErrorLogBean errorLogBean, RbParm rbParm) {
		Example example = new Example(ErrorLogBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(errorLogBean.getPhoneMode())) {
			criteria.andEqualTo("phoneMode", errorLogBean.getPhoneMode());
		}
		if (!StringUtils.isEmptyOrWhitespace(errorLogBean.getUserInfo())) {
			criteria.andEqualTo("userInfo", errorLogBean.getUserInfo());
		}
		//添加时间范围查询
		criteria = addAndBetweenTime(criteria ,rbParm);
		example.and(criteria);
		return example;
	}
	
	/**
	 * 根据时间范围查询
	 * @param rbParm
	 * @return
	 */
	public Example getErrorLogFilter(RbParm rbParm) {
		Example example = new Example(ErrorLogBean.class);
		Example.Criteria criteria = example.createCriteria();
		//添加时间范围查询
		criteria = addAndBetweenTime(criteria ,rbParm);
		example.and(criteria);
		return example;
	}

	
}
