package com.geostar.georobox.management.module.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbBaseMapper;
import com.geostar.georobox.management.module.friend.dao.provider.FriendProvider;
import com.geostar.georobox.management.module.friend.model.FriendCommentBean;
import com.geostar.georobox.management.module.friend.model.FriendFavorBean;
import com.geostar.georobox.management.module.friend.model.FriendItemBean;
import com.geostar.georobox.management.module.friend.model.quest.QuestFriendGetItemParam;

public interface FriendItemBeanMapper extends RbBaseMapper<FriendItemBean> {

	@Results(id = "friendItemBeanMap", value = { @Result(property = "itemId", column = "ITEM_ID"),
			@Result(property = "userId", column = "USER_ID"), 
			@Result(property = "itemType", column = "ITEM_TYPE"),
			@Result(property = "content", column = "CONTENT"),
			@Result(property = "photoUrl", column = "PHOTO_URL"),
			@Result(property = "photoTempUrl", column = "PHOTO_TEMP_URL"),
			@Result(property = "videoImgUrl", column = "VIDEO_IMG_URL"),
			@Result(property = "videoUrl", column = "VIDEO_URL"),
			@Result(property = "loction", column = "LOCTION"),
			@Result(property = "createtime", column = "CREATETIME"),
			@Result(property = "datetime", column = "DATETIME"),
			@Result(property = "comments", column = "ITEM_ID",
			one = @One(select = "com.geostar.georobox.management.module.friend.dao.FriendItemBeanMapper.selectCommentByItemId")),
			@Result(property = "favorters", column = "ITEM_ID",
			one = @One(select = "com.geostar.georobox.management.module.friend.dao.FriendItemBeanMapper.selectFavorByItemId"))
			})
	@SelectProvider(type = FriendProvider.class, method = "selectFriendItem")
	public List<FriendItemBean> selectFriendItemList(QuestFriendGetItemParam param, RbParm rbParm);
	
	@Select("SELECT * FROM RB_FRIEND_ITEM WHERE ITEM_ID = #{itemId} ")
	@ResultMap("friendItemBeanMap")//必须在Select下面
	public FriendItemBean selectFriendItemByItemId(String itemId);

	@Results(id = "favorBeanMap", value = { 
			@Result(property = "favorterId", column = "FAVORTER_ID"),
			@Result(property = "itemId", column = "ITEM_ID"),
			@Result(property = "userId", column = "USER_ID")
			})
	@Select("SELECT * FROM RB_FRIEND_FAVORTER WHERE ITEM_ID = #{itemId} ")
	public List<FriendFavorBean> selectFavorByItemId(String itemId);
	
	
	@Results(id = "commentBeanMap", value = { 
			@Result(property = "commentId", column = "COMMENT_ID"),
			@Result(property = "itemId", column = "ITEM_ID"),
			@Result(property = "userId", column = "USER_ID"),
			@Result(property = "replyId", column = "REPLY_ID"),
			@Result(property = "content", column = "CONTENT"),
			@Result(property = "createtime", column = "CREATETIME"),
			@Result(property = "datetime", column = "DATETIME")
			})
	@Select("SELECT * FROM RB_FRIEND_COMMENT WHERE ITEM_ID = #{itemId} ")
	public List<FriendCommentBean> selectCommentByItemId(String itemId);

}