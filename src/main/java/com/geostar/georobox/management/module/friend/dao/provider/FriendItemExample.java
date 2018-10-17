package com.geostar.georobox.management.module.friend.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.friend.model.FriendCommentBean;
import com.geostar.georobox.management.module.friend.model.FriendFavorBean;
import com.geostar.georobox.management.module.friend.model.FriendItemBean;
import com.geostar.georobox.management.module.friend.model.quest.QuestFriendGetItemParam;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class FriendItemExample extends RbExample{
	/**
	 * 根据查询条件筛选内容
	 * @param friendItemBean
	 * @param rbParm
	 * @return
	 */
	public Example getFriendItemFilter(QuestFriendGetItemParam param, RbParm rbParm) {
		Example example = new Example(FriendItemBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(param.getUserId())) {
			criteria.andEqualTo("userId", param.getUserId());
		}
		
		if (!StringUtils.isEmptyOrWhitespace(param.getLastItemTime())) {
			criteria.andLessThan("createtime", param.getLastItemTime());
		}
		//添加时间范围查询
		criteria = addAndBetweenTime(criteria ,rbParm);
		example.and(criteria);
		example.orderBy("datetime").desc();
		return example;
	}
	
	/**
	 * 根据ITEM_ID和USER_ID查询朋友圈
	 * @param friendItemBean
	 * @return
	 */
	public Example getFriendItemForId(FriendItemBean friendItemBean) {
		Example example = new Example(FriendItemBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(friendItemBean.getItemId())) {
			criteria.andEqualTo("itemId", friendItemBean.getItemId());
		}
		if (!StringUtils.isEmptyOrWhitespace(friendItemBean.getUserId())) {
			criteria.andEqualTo("userId", friendItemBean.getUserId());
		}
		example.and(criteria);
		return example;
	}
	
	
	/**
	 * 根据ITEM_ID和USER_ID查询点赞
	 * 
	 * @param friendFavorBean
	 * @return
	 */
	public Example getFriendFavorId(String itemId, String userId) {
		Example example = new Example(FriendFavorBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(itemId)) {
			criteria.andEqualTo("itemId", itemId);
		}
		if (!StringUtils.isEmptyOrWhitespace(userId)) {
			criteria.andEqualTo("userId",userId);
		}
		example.and(criteria);
		return example;
	}

	public Example getFriendCommentId(String itemId) {
		Example example = new Example(FriendCommentBean.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(itemId)) {
			criteria.andEqualTo("itemId", itemId);
		}
		example.and(criteria);
		return example;
	}
		
}
