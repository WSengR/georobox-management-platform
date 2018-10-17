package com.geostar.georobox.management.module.userback.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.userback.model.UserBackBean;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class UserBackExample extends RbExample{
	
	/**
	 * 模糊查询反馈信息
	 * @param logManageBean
	 * @param rbParm
	 * @return
	 */
	public Example getUserbackFilter(UserBackBean userBackBean, RbParm rbParm) {
		Example example = new Example(UserBackBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(rbParm.searchKey)) {
			criteria.orLike("infoUser", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoBack", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoMode", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoAppinfo", "%" + rbParm.searchKey + "%");
			criteria.orLike("infoOther", "%" + rbParm.searchKey + "%");
		}
		Example.Criteria criteria2 = example.createCriteria();
		//添加时间范围查询
		criteria2 = addAndBetweenTime(criteria2 ,rbParm);
		example.and(criteria);
		example.and(criteria2);
		return example;
	}

	
}
