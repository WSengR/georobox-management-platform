package com.geostar.georobox.management.module.maintv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.module.maintv.dao.ZeusTvBeanMapper;
import com.geostar.georobox.management.module.maintv.model.ZeusTvBean;
import com.geostar.georobox.management.module.maintv.service.ZeusTvService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ZeusTvServiceImpl implements ZeusTvService {
	
	@Autowired
	private ZeusTvBeanMapper zeusTvBeanMapper;
	
	/**
	 * 添加宙斯电视机
	 * @param
	 * zeusTvBean 宙斯电视机对象
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveZeusTv(ZeusTvBean zeusTvBean) {
		int insert = zeusTvBeanMapper.insertSelective(zeusTvBean);
		return insert;
	}
	
	/**
	 * 获取宙斯电视机列表
	 * @param
	 */
	@Override
	public List<ZeusTvBean> getZeusTvList(ZeusTvBean zeusTvBean) {
		Example example = new Example(ZeusTvBean.class);

		example.orderBy("datetime").desc();
		List<ZeusTvBean> zeusTvBeans = zeusTvBeanMapper.selectByExample(example);
		return zeusTvBeans;
	}
	
	/**
	 * 删除宙斯电视机（根据主键）
	 * @param
	 * id 宙斯电视机ID
	 */
	@Override
	public int deleteZeusTv(String id) {
		int insert = zeusTvBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 修改宙斯电视机（根据主键）
	 *  @param
	 * zeusTvBean宙斯电视机对象
	 */
	@Override
	public int changeZeusTv(ZeusTvBean zeusTvBean) {
		int insert = zeusTvBeanMapper.updateByPrimaryKeySelective(zeusTvBean);
		return insert;
	}

}
