package com.geostar.georobox.management.module.score.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.score.model.ScoreBean;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class ScoreExample extends RbExample{
	
	/**
	 * 根据关键字搜索内容
	 * @param scoreBean
	 * @param rbParm
	 * @return
	 */
	public Example getScoreFilter(ScoreBean scoreBean, RbParm rbParm) {
		Example example = new Example(ScoreBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(rbParm.searchKey)) {
			criteria.orLike("userInfo", "%" + rbParm.searchKey + "%");
			criteria.orLike("body", "%" + rbParm.searchKey + "%");
			criteria.orLike("appInfo", "%" + rbParm.searchKey + "%");
		}
		example.and(criteria);
		return example;
	}
	
}
