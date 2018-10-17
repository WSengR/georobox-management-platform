package com.geostar.georobox.management.module.logmanage.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.logmanage.model.LogManageBean;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class LogManageExample extends RbExample{
	
	/**
	 * 模糊查询日志内容
	 * @param logManageBean
	 * @param rbParm
	 * @return
	 */
	public static Example getLogManageFilter(LogManageBean logManageBean, RbParm rbParm) {
		Example example = new Example(LogManageBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(rbParm.searchKey)) {
			criteria.orLike("fileName", "%" + rbParm.searchKey + "%");
			criteria.orLike("logText", "%" + rbParm.searchKey + "%");
		}
		return example;
	}

	
}
