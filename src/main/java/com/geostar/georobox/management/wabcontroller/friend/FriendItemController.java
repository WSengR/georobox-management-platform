package com.geostar.georobox.management.wabcontroller.friend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.friend.model.FriendCommentBean;
import com.geostar.georobox.management.module.friend.model.FriendFavorBean;
import com.geostar.georobox.management.module.friend.model.FriendItemBean;
import com.geostar.georobox.management.module.friend.model.quest.QuestFriendGetItemParam;
import com.geostar.georobox.management.module.friend.service.impl.FriendCommentServiceImpl;
import com.geostar.georobox.management.module.friend.service.impl.FriendFavorServiceImpl;
import com.geostar.georobox.management.module.friend.service.impl.FriendItemServiceImpl;

@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("friend")
public class FriendItemController {
	protected static Logger logger = LoggerFactory.getLogger(FriendItemController.class);
	@Autowired
	private FriendItemServiceImpl friendItemService;
	@Autowired
	private FriendFavorServiceImpl friendFavorService;
	@Autowired
	private FriendCommentServiceImpl friendCommentService;

	/** =============================朋友圈======================================= */
	@RequestMapping("/GetFriendItemListForFilter")
	public ListLimitBean GetFriendItemListForFilter(QuestFriendGetItemParam param, RbParm rbParm) throws Exception {
		ListLimitBean listLimitBean = new ListLimitBean();
		List<FriendItemBean> queryFriendItemList = friendItemService.queryFriendItemList(param, rbParm);
		listLimitBean.setData(queryFriendItemList);
		int count = friendItemService.getCount(param, rbParm);
		listLimitBean.setCount(count);
		return listLimitBean;
	}

	@RequestMapping("/SendFriendsItem")
	public RbResultBean SendFriendsItemServlet(FriendItemBean friendItemBean) throws Exception {
		int num = friendItemService.saveFriendItem(friendItemBean);
		return RbResultBean.getSuccess(num);
	}

	@RequestMapping("/GetItemDetail")
	public RbResultBean GetItemForIdServlet(String itemId) throws Exception {
		return RbResultBean.getSuccess(friendItemService.queryFriendItemForId(itemId));
	}

	@RequestMapping("/DeleteItem")
	public RbResultBean DeleteItemByItemIdServlet(String itemId) throws Exception {
		return RbResultBean.getSuccess(friendItemService.deleteFriendItem(itemId));
	}

	@RequestMapping("/GetUserPhoto")
	public RbResultBean GetUserPhotoServlet(String itemId) throws Exception {
		return RbResultBean.getSuccess(1);
	}
	
	@RequestMapping("/GetRankItem")
	public RbResultBean GetRankItemServlet(String itemId) throws Exception {
		return RbResultBean.getSuccess(1);
	}

	/** ===========================点赞================================= */
	@RequestMapping("/SendFavort")
	public RbResultBean SendFavortServlet(FriendFavorBean friendFavorBean) throws Exception {
		int num = friendFavorService.friendFavor(friendFavorBean);
		return RbResultBean.getSuccess(num);
	}

	/** ===========================评论================================= */
	@RequestMapping("/SendItemComment")
	public RbResultBean SendItemCommentServlet(FriendCommentBean friendCommentBean) throws Exception {
		int num = friendCommentService.saveFriendComment(friendCommentBean);
		return RbResultBean.getSuccess(num);
	}

	@RequestMapping("/DeleteComment")
	public RbResultBean SendDeleteCommentServlet(String commentId) throws Exception {
		int num = friendCommentService.deleteFriendComment(commentId);
		return RbResultBean.getSuccess(num);
	}

}
