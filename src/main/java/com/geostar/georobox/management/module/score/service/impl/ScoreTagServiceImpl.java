package com.geostar.georobox.management.module.score.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.module.score.dao.ScoreTagBeanMapper;
import com.geostar.georobox.management.module.score.model.ScoreTagBean;
import com.geostar.georobox.management.module.score.service.ScoreTagService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ScoreTagServiceImpl implements ScoreTagService {
	
	@Autowired
	private ScoreTagBeanMapper scoreTagBeanMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveScoreTag(ScoreTagBean scoreTagBean) {
		int insert = scoreTagBeanMapper.insertSelective(scoreTagBean);
		return insert;
	}
	
	@Override
	public List<ScoreTagBean> queryScoreTagList() {
		Example example = new Example(ScoreTagBean.class);
		List<ScoreTagBean> scoreTagBeans = scoreTagBeanMapper.selectByExample(example);
		return scoreTagBeans;
	}
	
	@Override
	public int getCount() {
		Example example = new Example(ScoreTagBean.class);
		int selectCountByExample = scoreTagBeanMapper.selectCountByExample(example);
		return selectCountByExample;
	}
	
	@Override
	public int deleteScoreTag(String tag) {
		int insert = scoreTagBeanMapper.deleteByPrimaryKey(tag);
		return insert;
	}
	

}
