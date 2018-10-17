package com.geostar.georobox.management.module.score.service;

import java.util.List;

import com.geostar.georobox.management.module.score.model.ScoreTagBean;

public interface ScoreTagService {
	
	public int saveScoreTag(ScoreTagBean scoreTagBean);
	
	public List<ScoreTagBean> queryScoreTagList();
	
	public int deleteScoreTag(String tag);
	
	public int getCount();

}
