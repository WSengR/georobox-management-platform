package com.geostar.georobox.management.module.document.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.document.model.DocumentBean;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class DocumentExample extends RbExample{
	
	/**
	 * 根据关键字搜索内容
	 * @param documentBean
	 * @param rbParm
	 * @return
	 */
	public Example getDocumentFilter(DocumentBean documentBean, RbParm rbParm) {
		Example example = new Example(DocumentBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(rbParm.searchKey)) {
			criteria.orLike("fileName", "%" + rbParm.searchKey + "%");
		}
		example.and(criteria);
		return example;
	}
	
}
