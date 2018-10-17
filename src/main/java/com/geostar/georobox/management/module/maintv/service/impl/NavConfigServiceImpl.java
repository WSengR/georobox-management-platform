package com.geostar.georobox.management.module.maintv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.module.maintv.dao.NavConfigBeanMapper;
import com.geostar.georobox.management.module.maintv.model.NavConfigBean;
import com.geostar.georobox.management.module.maintv.service.NavConfigService;

import tk.mybatis.mapper.entity.Example;

@Service
public class NavConfigServiceImpl implements NavConfigService {

	@Autowired
	private NavConfigBeanMapper navConfigBeanMapper;

	/**
	 * 添加左侧导航内容
	 * 
	 * @param navConfigBean 左侧导航对象
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveNavConfig(NavConfigBean navConfigBean) {
		int insert = navConfigBeanMapper.insertSelective(navConfigBean);
		return insert;
	}

	/**
	 * 获取左侧导航列表
	 * 
	 * @param navConfigBean 左侧导航对象
	 */
	@Override
	public List<NavConfigBean> queryNavConfigList(NavConfigBean navConfigBean) {
		Example example = new Example(NavConfigBean.class);

		Example.Criteria criteria = example.createCriteria();
		Short isOpen = navConfigBean.getIsOpen();
		String isOpenStr = String.valueOf(isOpen);
		if (!StringUtils.isEmptyOrWhitespace(isOpenStr)) {
			criteria.andEqualTo("isOpen", navConfigBean.getIsOpen());
		}
		example.and(criteria);
		example.orderBy("datetime").desc();
		List<NavConfigBean> navConfigBeans = navConfigBeanMapper.selectByExample(example);
		return navConfigBeans;
	}

	/**
	 * 删除左侧导航（根据主键）
	 * 
	 * @param id 左侧导航ID
	 */
	@Override
	public int deleteNavConfig(String id) {
		int insert = navConfigBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}

	/**
	 * 修改左侧导航（根据主键）
	 * 
	 * @param navConfigBean 左侧导航对象
	 */
	@Override
	public int changeNavConfig(NavConfigBean navConfigBean) {
		int insert = navConfigBeanMapper.updateByPrimaryKeySelective(navConfigBean);
		return insert;
	}

	@Override
	public boolean selectTableExist(String tableName) {
		int selectTableExist = navConfigBeanMapper.selectTableExist(tableName);
		return selectTableExist > 0;
	}

}
