package com.geostar.georobox.management.module.friend.service;

import java.util.List;

import com.geostar.georobox.management.module.friend.model.FriendCommentBean;

public interface FriendCommentService {
	
	int saveFriendComment(FriendCommentBean friendCommentBean);

	int deleteFriendComment(String id);

	int delAllComment(String itemId);

	List<FriendCommentBean> selectFavorForItemId(String itemId);
	
}
