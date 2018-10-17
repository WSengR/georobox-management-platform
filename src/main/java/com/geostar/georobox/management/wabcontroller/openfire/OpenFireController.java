package com.geostar.georobox.management.wabcontroller.openfire;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.RbJsonUtils;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.openfire.dao.provider.ImConfig;
import com.geostar.georobox.management.module.openfire.dao.provider.ImRestApiClient;
import com.geostar.georobox.management.module.openfire.model.ChartRoomBean;
import com.geostar.georobox.management.module.openfire.model.ImRemindBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomSpeakBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomStateBean;
import com.geostar.georobox.management.module.openfire.model.ImTopBean;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;
import com.geostar.georobox.management.module.openfire.model.push.BaseGroupMsgData.Action;
import com.geostar.georobox.management.module.openfire.model.push.Group;
import com.geostar.georobox.management.module.openfire.model.push.GroupUpdateMsgData;
import com.geostar.georobox.management.module.openfire.model.push.GroupUpdateMsgData.UpdateAction;
import com.geostar.georobox.management.module.openfire.model.push.PushMessage;
import com.geostar.georobox.management.module.openfire.model.push.PushMessage.PushType;
import com.geostar.georobox.management.module.openfire.model.requst.QuestCheckInviteParam;
import com.geostar.georobox.management.module.openfire.model.requst.QuestCreateRoomParam;
import com.geostar.georobox.management.module.openfire.model.requst.QuestInviteChartParam;
import com.geostar.georobox.management.module.openfire.model.requst.QuestUpdateRoomParam;
import com.geostar.georobox.management.module.openfire.model.requst.QuestUpdateRoomUserParam;
import com.geostar.georobox.management.module.openfire.service.impl.ImChartRoomServiceImpl;

@RestController
@RequestMapping("openfire")
public class OpenFireController {
	protected static Logger logger = LoggerFactory.getLogger(OpenFireController.class);

	@Autowired
	private ImChartRoomServiceImpl imChartRoomServiceImpl;

	@Autowired
	private ImRestApiClient imRestApiClient;

	@Autowired
	private SQLHelper sqlHelper;

	/** ========================= Chart Room 群组 ======================== */
	/**
	 * 创建群组
	 */
	@RequestMapping("/CreateChatRoomServlet")
	public RbResultBean CreateChatRoomServlet(QuestCreateRoomParam questCreateRoomParam) {

		boolean createChatRoom = imRestApiClient.createChatRoom(questCreateRoomParam.getNaturalName(),
				questCreateRoomParam.getRoomName(), questCreateRoomParam.getDescription(),
				questCreateRoomParam.getMaxUsers(),
				imRestApiClient.getListUser(questCreateRoomParam.getImRoomUserOwners(),
						questCreateRoomParam.getRoomName()),
				imRestApiClient.getListUser(questCreateRoomParam.getImRoomUserAdmins(),
						questCreateRoomParam.getRoomName()),
				imRestApiClient.getListUser(questCreateRoomParam.getImRoomUserMembers(),
						questCreateRoomParam.getRoomName()));
		if (!createChatRoom) {
			return RbResultBean.getError("创建群失败");
		}
		ChartRoomBean chartRoomBean = new ChartRoomBean();
		chartRoomBean.setNaturalName(questCreateRoomParam.getNaturalName());
		chartRoomBean.setRoomName(questCreateRoomParam.getRoomName());
		chartRoomBean.setDescription(questCreateRoomParam.getDescription());
		chartRoomBean.setAdmins(questCreateRoomParam.getImRoomUserAdmins());
		chartRoomBean.setOwners(questCreateRoomParam.getImRoomUserOwners());
		chartRoomBean.setMembers(questCreateRoomParam.getImRoomUserMembers());
		chartRoomBean.setCurrentCount();
		imRestApiClient.sendPushChartRoom(questCreateRoomParam.getRoomName(), RbJsonUtils.objectToJson(chartRoomBean));
		return RbResultBean.getSuccess(chartRoomBean);
	}

	@RequestMapping("/CreateDefRoomServlet")
	public RbResultBean CreateDefRoomServlet() {
		imRestApiClient.createDefRoom();
		return RbResultBean.getSuccess();
	}

	@RequestMapping("/DeleteChatRoomServlet")
	public RbResultBean DeleteChatRoomServlet(String roomId) {
		boolean deleteChatRoom = imRestApiClient.deleteChatRoom(roomId);
		return RbResultBean.getSuccess(deleteChatRoom);
	}

	@RequestMapping("/UpdateChatRoomServlet")
	public RbResultBean UpdateChatRoomServlet(QuestUpdateRoomParam questUpdateRoomParam) {

		boolean updateChatRoom = imRestApiClient.updateChatRoom(questUpdateRoomParam.getRoomName(),
				questUpdateRoomParam.getNaturalName());
		if (!updateChatRoom) {
			return RbResultBean.getError("更新群信息失败");
		}

		GroupUpdateMsgData groupPushMsgData = new GroupUpdateMsgData();
		groupPushMsgData.setId(sqlHelper.getUUID());
		groupPushMsgData.setUpdateAction(UpdateAction.UPDATE_ROOM_NAME);
		groupPushMsgData.setAction(Action.GROUP_UPDATE);

		List<ImUserNikeNameBean> imRoomUserNikeNames = new ArrayList<>();
		imRoomUserNikeNames.add(questUpdateRoomParam.getImRoomChangeUser());
		groupPushMsgData.setUpdateUser(imRoomUserNikeNames);
		groupPushMsgData
				.setUpdateGroup(new Group(questUpdateRoomParam.getRoomName(), questUpdateRoomParam.getNaturalName()));
		PushMessage<GroupUpdateMsgData> pushMessage = new PushMessage<>(PushType._GROUP, groupPushMsgData);
		imRestApiClient.sendPushChartRoom(questUpdateRoomParam.getRoomName(), RbJsonUtils.objectToJson(pushMessage));
		return RbResultBean.getSuccess("更新群信息成功");
	}

	@RequestMapping("/UpdateChatRoomUser")
	public RbResultBean UpdateChatRoomUser(QuestUpdateRoomUserParam quesParam) {
		List<ImUserNikeNameBean> roomAndUserBeans = quesParam.getRoomAndUserBeans();
		String interfaces = quesParam.getInterfaces();
		boolean updateChatRoomUser = imRestApiClient.updateChatRoomUser(interfaces, roomAndUserBeans);
		if (updateChatRoomUser) {
			if (ImConfig.ROOM_ADD_MEMBER.equals(interfaces) || ImConfig.ROOM_ADD_ADMIN.equals(interfaces)
					|| ImConfig.ROOM_ADD_OWNER.equals(interfaces)) {
				if (roomAndUserBeans != null && roomAndUserBeans.size() > 0) {
					imChartRoomServiceImpl.saveUserNicks(roomAndUserBeans);
					String roomId = roomAndUserBeans.get(0).getRoomId();
					ChartRoomBean chartRoomBean = imRestApiClient.getOwnerChatRoom(roomId, null);
					imRestApiClient.sendPushChartRoom(roomId, RbJsonUtils.objectToJson(chartRoomBean));
				}
			}

			if (ImConfig.ROOM_DELETE_MEMBER.equals(interfaces) || ImConfig.ROOM_DELETE_OWNER.equals(interfaces)
					|| ImConfig.ROOM_DELETE_ADMIN.equals(interfaces)) {
				String groupJson = quesParam.getGroupJson();
				String deleteType = quesParam.getDeleteType();
				if (roomAndUserBeans != null && roomAndUserBeans.size() > 0 && groupJson != null) {
					GroupUpdateMsgData groupPushMsgData = new GroupUpdateMsgData();
					if (deleteType != null && deleteType.equals("EXIT_GROUP")) {
						groupPushMsgData.setUpdateAction(UpdateAction.EXIT_GROUP);
					} else {
						groupPushMsgData.setUpdateAction(UpdateAction.REMOVE_MEMBER);
					}
					groupPushMsgData.setId(sqlHelper.getUUID());
					groupPushMsgData.setAction(Action.GROUP_UPDATE);
					Group group = RbJsonUtils.jsonToClass(groupJson, Group.class);
					groupPushMsgData.setUpdateUser(roomAndUserBeans);
					groupPushMsgData.setUpdateGroup(group);
					PushMessage<GroupUpdateMsgData> pushMessage = new PushMessage<>(PushType._GROUP, groupPushMsgData);
					imRestApiClient.sendPushChartRoom(group.getGroupId(), RbJsonUtils.objectToJson(pushMessage));
					imRestApiClient.sendPushPersons(imRestApiClient.getPushPersion(roomAndUserBeans),
							RbJsonUtils.objectToJson(pushMessage));
				}

			}

		}
		return RbResultBean.getSuccess(roomAndUserBeans);
	}

	@RequestMapping("/GetChatRoomServlet")
	public RbResultBean GetChatRoomServlet(String roomId, String userId) {
		ChartRoomBean chatRoom = imRestApiClient.getOwnerChatRoom(roomId, userId);
		return RbResultBean.getSuccess(chatRoom);
	}

	@RequestMapping("/GetChatRoomsServlet")
	public ListLimitBean GetChatRoomsServlet(String roomId, String userId) {
		List<MUCRoomEntity> mucRooms = imRestApiClient.getChatRooms().getMucRooms();
		ListLimitBean listLimitBean = new ListLimitBean();
		listLimitBean.setCount(mucRooms.size());
		listLimitBean.setData(mucRooms);
		return listLimitBean;
	}

	@RequestMapping("/GetChatRoomsTwoServlet")
	public RbResultBean GetChatRoomsTwoServlet(String userId) {
		return RbResultBean.getResultBack(imRestApiClient.getChatRoomsForNikeName(userId));
	}

	/** ========================= 邀请好友入群======================== */
	/**
	 * 设置群是否需要邀请加入
	 */
	@RequestMapping("/SetChatRoomInviteServlet")
	public RbResultBean SetChatRoomInviteServlet(ImRoomStateBean imRoomStateBean) {
		// invite 默认需要邀请，1需要 0不需要
		int saveImRoomState = imChartRoomServiceImpl.saveImRoomState(imRoomStateBean);
		return RbResultBean.getResultBack(saveImRoomState);
	}

	/**
	 * 邀请好友加入
	 */
	@RequestMapping("/InviteChatRoomServlet")
	public RbResultBean InviteChatRoomServlet(QuestInviteChartParam questInviteChartParam) {
		return imRestApiClient.inviteChatRoom(questInviteChartParam);
	}

	/**
	 * 审核
	 */
	@RequestMapping("/CheckInviteChatRoomServlet")
	public RbResultBean CheckInviteChatRoomServlet(QuestCheckInviteParam param) {
		boolean checkInviteChatRoom = imRestApiClient.checkInviteChatRoom(param);
		if (checkInviteChatRoom) {
			return RbResultBean.getSuccess("审核成功");
		} else {
			return RbResultBean.getSuccess("审核成功");
		}
	}

	/** ========================= Remind 消息提醒 ======================== */

	@RequestMapping("/AddImRemindServlet")
	public RbResultBean AddImRemindServlet(@Valid ImRemindBean imRemindBean) {
		return RbResultBean.getResultBack(imChartRoomServiceImpl.saveRemindBean(imRemindBean));
	}

	@RequestMapping("/GetUserRemindListServlet")
	public RbResultBean GetUserRemindListServlet(ImRemindBean imRemindBean) {
		return RbResultBean.getResultBack(imChartRoomServiceImpl.selectRemindBeans(imRemindBean));
	}

	/** ========================= Top 置顶 ======================== */
	@RequestMapping("/AddImTopServlet")
	public RbResultBean AddImTopServlet(ImTopBean imTopBean) {
		int saveTopBean = imChartRoomServiceImpl.saveTopBean(imTopBean);
		return RbResultBean.getSuccess(saveTopBean > 0 ? "添加置顶成功" : "置顶失败");
	}

	@RequestMapping("/GetUserTopListServlet")
	public RbResultBean GetUserTopListServlet(ImTopBean imTopBean) {
		return RbResultBean.getResultBack(imChartRoomServiceImpl.selectTopBeans(imTopBean));
	}

	@RequestMapping("/DeleteImTopServlet")
	public RbResultBean DeleteImTopServlet(ImTopBean imTopBean) {
		int deleteTopBean = imChartRoomServiceImpl.deleteTopBean(imTopBean);
		return RbResultBean.getSuccess(deleteTopBean > 0 ? "删除置顶" : "置顶失败");
	}

	/** ========================= Speak 申请发言 ======================== */

	@RequestMapping("/SetUserSpeakServlet")
	public RbResultBean SetUserSpeakServlet(ImRoomSpeakBean imRoomSpeakBean) {
		return RbResultBean.getResultBack(imChartRoomServiceImpl.saveSpeakBean(imRoomSpeakBean));
	}

	@RequestMapping("/GetCanSpeakServlet")
	public RbResultBean GetCanSpeakServlet(ImRoomSpeakBean imRoomSpeakBean) {
		int canSpeak = imChartRoomServiceImpl.selectCanSpeak(imRoomSpeakBean.getUserId(), imRoomSpeakBean.getRoomId());
		String msgString = "";
		switch (canSpeak) {
		case 0:
			msgString = "禁止发言";
			break;
		case 1:
			msgString = "可以发言";
			break;
		case 2:
			msgString = "未设置，所在该组织机构中";
			break;
		}
		return new RbResultBean(1, msgString, canSpeak);
	}

	@RequestMapping("/InviteSpeakServlet")
	public RbResultBean InviteSpeakServlet(QuestInviteChartParam questInviteChartParam) {
		return imRestApiClient.inviteChatRoom(questInviteChartParam);
	}

	@RequestMapping("/CheckSpeakServlet")
	public RbResultBean CheckSpeakServlet(QuestCheckInviteParam param) {
		boolean checkSpeak = imRestApiClient.checkSpeak(param);
		if (checkSpeak) {
			return RbResultBean.getResultBack("审核成功");
		} else {
			return RbResultBean.getResultBack("审核失败");
		}
	}

	/** ========================= 发送推送接口 ======================== */

	@RequestMapping("/SendPushMsgServlet")
	public RbResultBean SendPushMsgServlet(String address, String type, String from, String body) {
		return RbResultBean.getResultBack(imRestApiClient.sendPushMsg(address, type, body, from));
	}

	/** ========================= 用户别名 ======================== */
	@RequestMapping("/AddRoomUserNikeNameServlet")
	public RbResultBean AddRoomUserNikeNameServlet(ImUserNikeNameBean imUserNikeNameBean) {
		return RbResultBean.getResultBack(imChartRoomServiceImpl.saveUserNick(imUserNikeNameBean));
	}

	@RequestMapping("/GetAllUserListServlet")
	public RbResultBean GetAllUserListServlet(String userName) {
		return RbResultBean.getResultBack(1);
	}

	@RequestMapping("/SyncAllUserListServlet")
	public RbResultBean SyncAllUserListServlet(String accessToken) {
		return RbResultBean.getResultBack(1);
	}

	@RequestMapping("/GetAllGroupServlet")
	public RbResultBean GetAllGroupServlet(String userName) {
		return RbResultBean.getResultBack(1);
	}
}
