package com.geostar.georobox.management.module.score.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.score.dao.ScoreBeanMapper;
import com.geostar.georobox.management.module.score.dao.provider.ScoreExample;
import com.geostar.georobox.management.module.score.model.ScoreBean;
import com.geostar.georobox.management.module.score.service.ScoreService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private ScoreBeanMapper scoreBeanMapper;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private ScoreExample scoreExample;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveScore(ScoreBean scoreBean) {
		int insert = scoreBeanMapper.insertSelective(scoreBean);
		return insert;
	}
	
	@Override
	public List<ScoreBean> queryScoreList(ScoreBean scoreBean, RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		Example scoreFilter = scoreExample.getScoreFilter(scoreBean, rbParm);
		scoreFilter.orderBy("datetime").desc();
		List<ScoreBean> scoreBeans = scoreBeanMapper.selectByExample(scoreFilter);
		return scoreBeans;
	}
	
	@Override
	public int getCount(ScoreBean scoreBean, RbParm rbParm) {
		Example scoreFilter = scoreExample.getScoreFilter(scoreBean, rbParm);
		int selectCountByExample = scoreBeanMapper.selectCountByExample(scoreFilter);
		return selectCountByExample;
	}
	
	@Override
	public int deleteScore(String id) {
		int insert = scoreBeanMapper.deleteByPrimaryKey(id);
		return insert;
	}
	
	/**
	 * 根据主键获取评分图片地址 
	 */
	@Override
	public String getImageUrl(String id) {
		String fileName = scoreBeanMapper.getImageUrl(id);
		return fileName;
	}
	

}
