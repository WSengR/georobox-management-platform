package com.geostar.georobox.management.module.friend.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.friend.dao.FriendItemBeanMapper;
import com.geostar.georobox.management.module.friend.dao.provider.FriendItemExample;
import com.geostar.georobox.management.module.friend.model.FriendItemBean;
import com.geostar.georobox.management.module.friend.model.quest.QuestFriendGetItemParam;
import com.geostar.georobox.management.module.friend.service.FriendItemService;

import tk.mybatis.mapper.entity.Example;

@Service
public class FriendItemServiceImpl implements FriendItemService {
	
	private static final Logger log = LoggerFactory.getLogger(FriendItemServiceImpl.class);

	@Autowired
	private FriendItemBeanMapper friendItemBeanMapper;
	@Autowired
	private FriendFavorServiceImpl favorServiceImpl;
	@Autowired
	private FriendCommentServiceImpl commentServiceImpl;
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	private FriendItemExample friendItemExample;

	/**
	 * 添加新朋友圈 param friendItemBean 朋友圈对象
	 */
	@Override
	public int saveFriendItem(FriendItemBean friendItemBean) {
		friendItemBean.setDatetime(new Date());
		friendItemBean.setCreatetime(System.currentTimeMillis() + "");
		return friendItemBeanMapper.insertSelective(friendItemBean);
	}

	/**
	 * 获取朋友圈信息 param friendItemBean 朋友圈对象 RbParm rbParm 搜索信息对象
	 */
	@Override
	public List<FriendItemBean> queryFriendItemList(QuestFriendGetItemParam param, RbParm rbParm) {
		sqlHelper.startPage(rbParm);
		return friendItemBeanMapper.selectFriendItemList(param, rbParm);
	}

	/**
	 * 获取朋友圈数量
	 */
	@Override
	public int getCount(QuestFriendGetItemParam param, RbParm rbParm) {
		return friendItemBeanMapper.selectCountByExample(friendItemExample.getFriendItemFilter(param, rbParm));
	}

	/**
	 * 删除朋友圈 param id 朋友圈ID
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int deleteFriendItem(String itemId) {
		favorServiceImpl.deleteFavor(itemId, null);
		commentServiceImpl.delAllComment(itemId);
		int deleteByPrimaryKey = friendItemBeanMapper.deleteByPrimaryKey(itemId);
		return deleteByPrimaryKey;
	}

	/**
	 * 获取朋友圈信息 param friendItemBean 朋友圈对象 RbParm rbParm 搜索信息对象
	 */
	@Override
	public FriendItemBean queryFriendItemForId(String itemId) {
		return friendItemBeanMapper.selectFriendItemByItemId(itemId);
	}

}
