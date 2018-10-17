package com.geostar.georobox.management.module.score.dao;

import org.apache.ibatis.annotations.Select;

import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.score.model.ScoreBean;

public interface ScoreBeanMapper extends RbBaseMapper<ScoreBean> {
	
	/**
	 * 根据ID查找图片地址
	 * @param id
	 * @return
	 */
	@Select("SELECT IMAGE_URL FROM RB_SCORE WHERE id = #{id}")
	public String getImageUrl(String id);

}