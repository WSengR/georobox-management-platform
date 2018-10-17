package com.geostar.georobox.management.module.score.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.score.model.ScoreBean;

public interface ScoreService {
	
	public int saveScore(ScoreBean scoreBean);
	
	public List<ScoreBean> queryScoreList(ScoreBean scoreBean, RbParm rbParm);
	
	public int deleteScore(String id);
	
	public int getCount(ScoreBean scoreBean, RbParm rbParm);

	public String getImageUrl(String id);

}
