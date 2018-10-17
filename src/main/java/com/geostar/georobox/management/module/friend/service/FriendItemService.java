package com.geostar.georobox.management.module.friend.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.friend.model.FriendItemBean;
import com.geostar.georobox.management.module.friend.model.quest.QuestFriendGetItemParam;

public interface FriendItemService {
	
	int saveFriendItem(FriendItemBean friendItemBean);
	
	List<FriendItemBean> queryFriendItemList(QuestFriendGetItemParam param, RbParm rbParm);
	
	int deleteFriendItem(String itemId);

	int getCount(QuestFriendGetItemParam param, RbParm rbParm);

	FriendItemBean queryFriendItemForId(String itemId);

}
