package com.geostar.georobox.management.module.serverurl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.serverurl.dao.ServerUrlBeanMapper;
import com.geostar.georobox.management.module.serverurl.model.ServerUrlBean;
import com.geostar.georobox.management.module.serverurl.service.ServerUrlService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ServerUrlServiceImpl implements ServerUrlService {
	
	@Autowired
	private ServerUrlBeanMapper serverUrlBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	
	/**
	 * 添加新服务器配置信息
	 * param 
	 * serverUrlBean 服务器信息对象
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveServerUrl(ServerUrlBean serverUrlBean) {
		int insert = serverUrlBeanMapper.insertSelective(serverUrlBean);
		return insert;
	}
	
	/**
	 * 获取服务器配置信息
	 * param 
	 * serverUrlBean 服务器信息对象
	 * RbParm rbParm 搜索信息对象
	 */
	@Override
	public List<ServerUrlBean> queryServerUrlList(ServerUrlBean serverUrlBean, RbParm rbParm) {
		// 开始分页
		sqlHelper.startPage(rbParm);
		
		Example example = new Example(ServerUrlBean.class);
		example.orderBy("datetime").desc();
		List<ServerUrlBean> serverUrlBeans = serverUrlBeanMapper.selectByExample(example);
		return serverUrlBeans;
	}
	
	/**
	 * 获取服务器配置信息数量
	 */
	@Override
	public int getCount() {
		Example example = new Example(ServerUrlBean.class);
		int selectCountByExample = serverUrlBeanMapper.selectCountByExample(example);
		return selectCountByExample;
	}
	
	/**
	 * 删除服务器配置信息
	 * param
	 * id 配置信息ID
	 */
	@Override
	public int deleteServerUrl(String id) {
		int insert = serverUrlBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 修改服务器配置信息（根据主键）
	 * param
	 * serverUrlBean 服务器信息对象
	 */
	@Override
	public int changeServerUrl(ServerUrlBean serverUrlBean) {
		int insert = serverUrlBeanMapper.updateByPrimaryKeySelective(serverUrlBean);
		return insert;
	}

}
