package com.geostar.georobox.management.module.plugmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.plugmanage.dao.provider.PlugExample;
import com.geostar.georobox.management.module.plugmanage.service.PlugCategoryService;
import com.geostar.georobox.management.module.plugmanage.dao.PlugAndCategoryMapper;
import com.geostar.georobox.management.module.plugmanage.dao.PlugCategoryBeanMapper;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndCategory;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndPermission;
import com.geostar.georobox.management.module.plugmanage.model.PlugCategoryBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;

import tk.mybatis.mapper.entity.Example;

@Service
public class PlugCategoryServiceImpl implements PlugCategoryService {
	protected static Logger logger = LoggerFactory.getLogger(PlugCategoryServiceImpl.class);
	@Autowired
	private PlugCategoryBeanMapper plugCategoryBeanMapper;
	@Autowired
	private PlugAndCategoryMapper pacMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private PlugExample plugExample;

	@Override
	public int save(PlugCategoryBean plugCategoryBean) {
		
		PlugCategoryBean selectCategoryByName = selectCategoryByName(plugCategoryBean.getCategoryId(),plugCategoryBean.getCategoryName());
		if (selectCategoryByName != null) {
			return -1;
		}
		return plugCategoryBeanMapper.insertSelective(plugCategoryBean);
	}

	@Override
	public int delete(String categoryId) {
		deletePACByCategoryId(categoryId);
		return plugCategoryBeanMapper.deleteByPrimaryKey(categoryId);
	}

	@Override
	public int update(PlugCategoryBean plugCategoryBean) {
		PlugCategoryBean selectCategoryByName = selectCategoryByName(plugCategoryBean.getCategoryId(),plugCategoryBean.getCategoryName());
		if (selectCategoryByName != null) {
			return -1;
		}
		return plugCategoryBeanMapper.updateByPrimaryKeySelective(plugCategoryBean);
	}

	@Override
	public List<PlugCategoryBean> selectCategoryList(RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		Example example = new Example(PlugCategoryBean.class);
		example.orderBy("categoryName");
		return plugCategoryBeanMapper.selectAll();
	}

	@Override
	public PlugCategoryBean selectCategoryById(String categoryId) {
		return plugCategoryBeanMapper.selectByPrimaryKey(categoryId);
	}

	@Override
	public PlugCategoryBean selectCategoryByName(String categoryId, String categoryName) {
		Example categoryNameFilter = plugExample.categoryNameFilter(categoryId, categoryName);
		return plugCategoryBeanMapper.selectOneByExample(categoryNameFilter);
	}

	@Override
	public int savePAC(PlugAndCategory plugAndCategory) {
		 return pacMapper.insertSelective(plugAndCategory);
	}

	@Override
	public int selectPACCount(PlugAndCategory plugAndCategory) {
		return pacMapper.selectCount(plugAndCategory);
	}

	@Override
	public int deletePACByPlugId(String plugId) {
		return  pacMapper.deleteByExample(plugExample.deletePACFilter(plugId, null));
	}

	@Override
	public int deletePACByCategoryId(String categoryId) {
		return pacMapper.deleteByExample(plugExample.deletePACFilter(null, categoryId));
	}
	
	@Override
	public void savePACByCategorys(String plugId,String categorys) {
		if(StringUtils.isEmpty(plugId)||StringUtils.isEmpty(categorys)) {
			return;
		}
		deletePACByPlugId(plugId);
		if (!StringUtils.isEmpty(categorys)) {
			String[] split = categorys.split(";");
			for (int i = 0; i < split.length; i++) {
				if (StringUtils.isEmpty(split[i]))
					continue;
				PlugAndCategory plugAndCategory = new PlugAndCategory();
				plugAndCategory.setPlugId(plugId);
				plugAndCategory.setCategoryId(split[i]);
				 PlugCategoryBean selectCategoryById = selectCategoryById(plugAndCategory.getCategoryId());
				if (selectCategoryById == null)
					continue;
				savePAC(plugAndCategory);
			}
		}
	}
	
	@Override
	public List<String> selectPACByPlugId(String plugId) {
		return pacMapper.selectPACByPlugId(plugId);
	}

	@Override
	public int selectCategoryCount(RbParm rbParm) {
		return plugCategoryBeanMapper.selectCount(null);
	}

}
