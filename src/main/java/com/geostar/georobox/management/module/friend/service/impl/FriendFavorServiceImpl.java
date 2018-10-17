package com.geostar.georobox.management.module.friend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geostar.georobox.management.module.friend.dao.FriendFavorBeanMapper;
import com.geostar.georobox.management.module.friend.dao.provider.FriendItemExample;
import com.geostar.georobox.management.module.friend.model.FriendFavorBean;
import com.geostar.georobox.management.module.friend.service.FriendFavorService;

@Service
public class FriendFavorServiceImpl implements FriendFavorService {

	@Autowired
	private FriendFavorBeanMapper friendFavorBeanMapper;
	@Autowired
	private FriendItemExample friendItemExample;

	@Override
	public int friendFavor(FriendFavorBean friendFavorBean) {
		FriendFavorBean selectOneByExample = friendFavorBeanMapper.selectOneByExample(
				friendItemExample.getFriendFavorId(friendFavorBean.getItemId(), friendFavorBean.getUserId()));
		if (selectOneByExample != null) {
			return deleteFavor(selectOneByExample.getItemId(), selectOneByExample.getUserId());
		} else {
			return friendFavorBeanMapper.insert(friendFavorBean);
		}
	}

	@Override
	public int deleteFavor(String itemId, String userId) {
		return friendFavorBeanMapper.deleteByExample(friendItemExample.getFriendFavorId(itemId, userId));
	}

	@Override
	public List<FriendFavorBean> selectFavorForItemId(String itemId) {
		return friendFavorBeanMapper.selectByExample(friendItemExample.getFriendFavorId(itemId, null));
	}

}
