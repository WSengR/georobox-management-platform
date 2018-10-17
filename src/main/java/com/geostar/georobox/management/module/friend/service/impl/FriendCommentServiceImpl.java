package com.geostar.georobox.management.module.friend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.module.friend.dao.FriendCommentBeanMapper;
import com.geostar.georobox.management.module.friend.dao.provider.FriendItemExample;
import com.geostar.georobox.management.module.friend.model.FriendCommentBean;
import com.geostar.georobox.management.module.friend.service.FriendCommentService;

@Service
public class FriendCommentServiceImpl implements FriendCommentService {
	
	@Autowired
	private FriendCommentBeanMapper friendCommentBeanMapper;
	@Autowired
	private FriendItemExample friendItemExample;
	
	/**
	 * 添加评论
	 * param 
	 * friendItemBean 朋友圈对象
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveFriendComment(FriendCommentBean friendCommentBean) {
		friendCommentBean.setDatetime(new Date());
		friendCommentBean.setCreatetime(System.currentTimeMillis() + "");
		return friendCommentBeanMapper.insertSelective(friendCommentBean);
	}
	/**
	 * 删除评论
	 * param
	 * id 评论
	 */
	@Override
	public int deleteFriendComment(String commentId) {
		return friendCommentBeanMapper.deleteByPrimaryKey(commentId);
	}
	
	@Override
	public int delAllComment(String itemId) {
		return friendCommentBeanMapper.deleteByExample(friendItemExample.getFriendCommentId(itemId));
	}

	@Override
	public List<FriendCommentBean> selectFavorForItemId(String itemId) {
		return friendCommentBeanMapper.selectByExample(friendItemExample.getFriendCommentId(itemId));
	}

}
