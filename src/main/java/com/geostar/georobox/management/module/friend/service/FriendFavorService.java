package com.geostar.georobox.management.module.friend.service;

import java.util.List;

import com.geostar.georobox.management.module.friend.model.FriendFavorBean;

public interface FriendFavorService {
	
	int friendFavor(FriendFavorBean friendFavorBean);

	int deleteFavor(String itemId, String userId);
	
	List<FriendFavorBean> selectFavorForItemId(String itemId);
	
}
