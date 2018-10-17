package com.geostar.georobox.management.module.openfire.dao.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.igniterealtime.restclient.entity.MUCRoomEntities;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.common.utils.RbJsonUtils;
import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.openfire.model.ChartRoomBean;
import com.geostar.georobox.management.module.openfire.model.DeptmentBean;
import com.geostar.georobox.management.module.openfire.model.DeptmentResultBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomSpeakBean;
import com.geostar.georobox.management.module.openfire.model.ImUser;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;
import com.geostar.georobox.management.module.openfire.model.UcenterGetAllUserListResult;
import com.geostar.georobox.management.module.openfire.model.push.Group;
import com.geostar.georobox.management.module.openfire.model.push.GroupPushMsgData;
import com.geostar.georobox.management.module.openfire.model.push.GroupPushMsgData.Action;
import com.geostar.georobox.management.module.openfire.model.push.InviteMarkBean;
import com.geostar.georobox.management.module.openfire.model.push.PushMessage;
import com.geostar.georobox.management.module.openfire.model.push.PushMessage.PushType;
import com.geostar.georobox.management.module.openfire.model.requst.QuestCheckInviteParam;
import com.geostar.georobox.management.module.openfire.model.requst.QuestInviteChartParam;
import com.geostar.georobox.management.module.openfire.service.impl.ImChartRoomServiceImpl;

@Configuration
public class ImRestApiClient extends ImHttpRestApi {

	@Autowired
	private ImChartRoomServiceImpl imChartRoomServiceImpl;
	@Autowired
	private SQLHelper sqlHelper;

	/**
	 * 添加群成员
	 * 
	 * @param roomName
	 * @param jid
	 * @return
	 */
	public boolean updateChatRoomUser(String interfaces, List<ImUserNikeNameBean> imUserNikeNameBeans) {
		boolean status = true;
		for (ImUserNikeNameBean imUserNikeNameBean : imUserNikeNameBeans) {
			status = updateChatRoomUser(interfaces, imUserNikeNameBean.getRoomId(), imUserNikeNameBean.getUserId());
		}
		return status;
	}

	/**
	 * 创建聊天室
	 * 
	 * @param naturalName
	 * @param roomName
	 * @param description
	 * @param maxUsers    默认为0无限制
	 * @param owners
	 */
	public boolean createChatRoom(String naturalName, String roomName, String description, int maxUsers,
			List<String> owners, List<String> admins, List<String> members) {
		MUCRoomEntity mucRoomEntity = new MUCRoomEntity(naturalName, roomName, description);
		mucRoomEntity.setMaxUsers(maxUsers);
		mucRoomEntity.setPersistent(true);
		mucRoomEntity.setPublicRoom(true);
		mucRoomEntity.setRegistrationEnabled(true);
		mucRoomEntity.setCanChangeNickname(true);
		mucRoomEntity.setLogEnabled(true);
		mucRoomEntity.setOwners(owners);
		mucRoomEntity.setAdmins(admins);
		mucRoomEntity.setMembers(members);
		mucRoomEntity.setMembersOnly(true);
		mucRoomEntity.setModerated(true);
		mucRoomEntity.setCanOccupantsInvite(true);
		List<String> broadcastRoles = new ArrayList<>();
		broadcastRoles.add("moderator");
		broadcastRoles.add("participant");
		mucRoomEntity.setBroadcastPresenceRoles(broadcastRoles);
		return createChatRoom(mucRoomEntity);
	}

	/**
	 * 创建房间，单独添加群成员（当需要添加的群成员过多时可使用该方式）
	 * 
	 * @param naturalName
	 * @param roomName
	 * @param description
	 * @param members
	 * @return
	 */
	public boolean createRoomAddMember(String naturalName, String roomName, String description, List<String> members) {
		boolean create = createChatRoom(naturalName, roomName, description, 0, null, null, null);
		for (String imUserId : members) {
			addMember(roomName, imUserId);
		}
		return create;
	}

	/**
	 * 获取用户所有的CharRoom
	 * 
	 * @param userName
	 * @return
	 */
	public List<ChartRoomBean> getChatRoomsForNikeName(String userName) {
		userName = addUserIp(userName);
		MUCRoomEntities chatRooms = getChatRooms();
		List<MUCRoomEntity> mucRooms = chatRooms.getMucRooms();
		List<ChartRoomBean> chartRoomBeans = new ArrayList<>();
		if (mucRooms == null || mucRooms.size() <= 0) {
			return chartRoomBeans;
		}
		for (MUCRoomEntity mucRoomEntity : mucRooms) {

			boolean isHave = false;
			List<String> owners = mucRoomEntity.getOwners();
			List<String> admins = mucRoomEntity.getAdmins();
			List<String> members = mucRoomEntity.getMembers();
			int ownersIndex = checkList(owners, userName);
			int adminsIndex = checkList(admins, userName);
			int membersIndex = checkList(members, userName);
			List<ImUserNikeNameBean> ownersList = new ArrayList<>();
			List<ImUserNikeNameBean> adminsList = new ArrayList<>();
			List<ImUserNikeNameBean> membersList = new ArrayList<>();
			List<ImUserNikeNameBean> allUserNike = null;
			if (ownersIndex != -1) {
				isHave = true;
			}
			if (adminsIndex != -1) {
				isHave = true;
			}
			if (membersIndex != -1) {
				isHave = true;
			}
			if (isHave) {
				allUserNike = imChartRoomServiceImpl.selectUserNick(mucRoomEntity.getRoomName());

				Map<String, String> nikeMap = nikeNamelistToMap(allUserNike);
				ownersList = getRmUserNikeList(nikeMap, owners);
				adminsList = getRmUserNikeList(nikeMap, admins);
				membersList = getRmUserNikeList(nikeMap, members);

				ChartRoomBean chartRoomBean = new ChartRoomBean();
				chartRoomBean.setNaturalName(mucRoomEntity.getNaturalName());
				chartRoomBean.setRoomName(mucRoomEntity.getRoomName());
				chartRoomBean.setDescription(mucRoomEntity.getDescription());
				chartRoomBean.setAdmins(adminsList);
				chartRoomBean.setOwners(ownersList);
				chartRoomBean.setMembers(membersList);
				chartRoomBean.setCurrentCount();
				chartRoomBean.setInviteState(imChartRoomServiceImpl.roomInviteState(mucRoomEntity.getRoomName()));
				chartRoomBean.setCanSpeak(
						imChartRoomServiceImpl.selectCanSpeak(mucRoomEntity.getRoomName(), removeUserIp(userName)));
				chartRoomBeans.add(chartRoomBean);

			}
		}
		return chartRoomBeans;
	}

	/**
	 * @功能描述: 通过字段名称将list转换成Map 以对应的字段的值作为key，对应的对象作为value
	 */
	public Map<String, String> nikeNamelistToMap(List<ImUserNikeNameBean> list) {
		Map<String, String> map = new HashMap<>();
		if (list != null) {
			try {
				for (ImUserNikeNameBean imRoomUserNikeName : list) {
					String key = imRoomUserNikeName.getUserId();
					String value = imRoomUserNikeName.getUserNikename();
					map.put(key, value);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("field can't match the key!");
			}
		}
		return map;
	}

	/**
	 * 用户附上别名
	 * 
	 * @param nikeMap
	 * @param roomUsers
	 * @return
	 */
	public List<ImUserNikeNameBean> getRmUserNikeList(Map<String, String> nikeMap, List<String> roomUsers) {
		List<ImUserNikeNameBean> userNikeList = new ArrayList<>();
		if (roomUsers != null && roomUsers.size() >= 0) {
			for (String ownerName : roomUsers) {
				ImUserNikeNameBean rmUserNikeBean = new ImUserNikeNameBean();
				rmUserNikeBean.setUserId(ownerName);
				/* 设置名片 */
				if (nikeMap != null && nikeMap.size() >= 0) {
					String s = nikeMap.get(ownerName);
					if (s != null) {
						rmUserNikeBean.setUserNikename(s);
					}
				}
				userNikeList.add(rmUserNikeBean);
			}
		}
		return userNikeList;
	}

	/**
	 * 通过ID获取群消息
	 * 
	 * @param roomName
	 */
	public ChartRoomBean getOwnerChatRoom(String roomName, String userId) {
		return toChartRoomBean(getChatRoom(roomName), userId);
	}

	/**
	 * 将MUCRoomEntity 转为ChartRoomBean
	 * 
	 * @param mucRoomEntity
	 * @param userId
	 * @return
	 */
	public ChartRoomBean toChartRoomBean(MUCRoomEntity mucRoomEntity, String userId) {

		List<String> owners = mucRoomEntity.getOwners();
		List<String> admins = mucRoomEntity.getAdmins();
		List<String> members = mucRoomEntity.getMembers();

		List<ImUserNikeNameBean> ownersList = new ArrayList<>();
		List<ImUserNikeNameBean> adminsList = new ArrayList<>();
		List<ImUserNikeNameBean> membersList = new ArrayList<>();
		List<ImUserNikeNameBean> allUserNike = null;
		allUserNike = imChartRoomServiceImpl.selectUserNick(mucRoomEntity.getRoomName());
		Map<String, String> nikeMap = nikeNamelistToMap(allUserNike);
		ownersList = getRmUserNikeList(nikeMap, owners);
		adminsList = getRmUserNikeList(nikeMap, admins);
		membersList = getRmUserNikeList(nikeMap, members);

		ChartRoomBean chartRoomBean = new ChartRoomBean();
		chartRoomBean.setNaturalName(mucRoomEntity.getNaturalName());
		chartRoomBean.setRoomName(mucRoomEntity.getRoomName());
		chartRoomBean.setDescription(mucRoomEntity.getDescription());
		chartRoomBean.setAdmins(adminsList);
		chartRoomBean.setOwners(ownersList);
		chartRoomBean.setMembers(membersList);
		chartRoomBean.setCurrentCount();
		chartRoomBean.setInviteState(imChartRoomServiceImpl.roomInviteState(mucRoomEntity.getRoomName()));
		if (!StringUtils.isEmpty(userId)) {
			chartRoomBean.setCanSpeak(
					imChartRoomServiceImpl.selectCanSpeak(mucRoomEntity.getRoomName(), removeUserIp(userId)));
		}

		return chartRoomBean;
	}

	/**
	 * 根据组织架构 创建默认群组并添加成员
	 */
	public void createDefRoom() {
		List<DeptmentBean> allDeptment = getAllDeptmentResult();
		logger.info(allDeptment.toString());
		for (DeptmentBean deptmentBean : allDeptment) {
			createRoom(deptmentBean);
		}
	}

	/**
	 * 递归创建所有组织的群组
	 * 
	 * @param deptmentBean
	 * @return
	 */
	public Map<String, ImUser> createRoom(DeptmentBean deptmentBean) {
		Map<String, ImUser> users = new HashMap<>();
		List<ImUser> usersFordid = getUsersForDid(deptmentBean.getDeptid());
		Map<String, ImUser> listToMap = imUserListToMap(usersFordid);
		if (listToMap != null && listToMap.size() >= 0) {
			users.putAll(listToMap);
			users.remove("admin");
		}
		if (deptmentBean.getChildren() != null) {
			for (DeptmentBean deptmentBean3 : deptmentBean.getChildren()) {
				Map<String, ImUser> createRoom = createRoom(deptmentBean3);
				if (createRoom != null && createRoom.size() >= 0) {
					users.putAll(createRoom);
				}
			}
		}
		List<String> members = getMembers(users);
		List<ImUserNikeNameBean> roomNickName = getRoomNickName(deptmentBean.getDeptid(), users);
		imChartRoomServiceImpl.saveUserNicks(roomNickName);

		deptmentBean.setText(deptmentBean.getDeptname() + "####部门群");
		if (members == null || members.size() < 600) {
			/* 如果少于600则使用快速添加用户 */
			createChatRoom(deptmentBean.getDeptname(), deptmentBean.getDeptid(), deptmentBean.getText(), 0, null, null,
					members);
		} else {
			/* 如果大于600则一个个添加用户 */
			createRoomAddMember(deptmentBean.getDeptname(), deptmentBean.getDeptid(), deptmentBean.getText(), members);
		}
		return users;
	}

	/**
	 * 格式转换
	 * 
	 * @param map
	 * @return
	 */
	public List<ImUserNikeNameBean> getRoomNickName(String roomName, Map<String, ImUser> map) {
		List<ImUserNikeNameBean> arrayList = new ArrayList<>();
		for (Map.Entry<String, ImUser> entry : map.entrySet()) {
			ImUser imUser = entry.getValue();
			ImUserNikeNameBean imRoomUserNikeName = new ImUserNikeNameBean();
			imRoomUserNikeName.setRoomId(roomName);
			imRoomUserNikeName.setUserId(addUserIp(imUser.getUserId()));
			imRoomUserNikeName.setUserNikename(imUser.getUserName());
			arrayList.add(imRoomUserNikeName);
		}

		ImUserNikeNameBean imRoomUserNikeName = new ImUserNikeNameBean();
		imRoomUserNikeName.setRoomId(roomName);
		imRoomUserNikeName.setUserId(addUserIp("admin"));
		imRoomUserNikeName.setUserNikename("管理员");
		arrayList.add(imRoomUserNikeName);
		return arrayList;
	}

	/**
	 * 格式转换
	 * 
	 * @param map
	 * @return
	 */
	public List<String> getMembers(Map<String, ImUser> map) {
		ArrayList<String> arrayList = new ArrayList<>();
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			arrayList.add(addUserIp(iterator.next()));// 创建时添加默认IP
		}
		return arrayList;
	}

	public Map<String, ImUser> imUserListToMap(List<ImUser> imUsers) {
		if (imUsers == null) {
			return null;
		}
		Map<String, ImUser> map = new HashMap<>();
		for (ImUser imUser : imUsers) {
			map.put(imUser.getUserId().trim(), imUser);
		}
		return map;
	}

	public List<String> getListUser(List<ImUserNikeNameBean> users, String roomName) {

		if (users != null && users.size() > 0) {
			List<String> strings = new ArrayList<>();
			List<ImUserNikeNameBean> imRoomUserNikeNames = new ArrayList<>();
			for (ImUserNikeNameBean rmUserNickBean : users) {
				String userName = addUserIp(rmUserNickBean.getUserId());
				strings.add(userName);
				if (!StringUtils.isEmpty(rmUserNickBean.getUserNikename())) {
					ImUserNikeNameBean imRoomUserNikeName = new ImUserNikeNameBean();
					imRoomUserNikeName.setRoomId(roomName);
					imRoomUserNikeName.setUserId(userName);
					imRoomUserNikeName.setUserNikename(rmUserNickBean.getUserNikename());
					imRoomUserNikeNames.add(imRoomUserNikeName);
				}
			}
			if (imRoomUserNikeNames.size() > 0) {
				imChartRoomServiceImpl.saveUserNicks(imRoomUserNikeNames);
			}
			return strings;
		}
		return null;
	}

	/**
	 * 更具DeptId获取所有用户
	 * 
	 * @param DeptID 如果为空则全部创建
	 * @return
	 */
	public List<ImUser> getUsersForDid(String DeptID) {
		String usersFordidJson = getUsersFordid(DeptID);
		UcenterGetAllUserListResult ucenterGetAllUserListResult = RbJsonUtils.jsonToClass(usersFordidJson,
				UcenterGetAllUserListResult.class);
		return ucenterGetAllUserListResult.getData().getRows();
	}

	/**
	 * 获取所有组织架构
	 * 
	 * @return
	 */
	public List<DeptmentBean> getAllDeptmentResult() {
		String allDeptmentJson = getAllDeptment();
		DeptmentResultBean deptmentResultBean = RbJsonUtils.jsonToClass(allDeptmentJson, DeptmentResultBean.class);
		return deptmentResultBean.getData().getRows();
	}

	public String getPushPersion(List<ImUserNikeNameBean> inviteess) {
		if (inviteess == null || inviteess.size() <= 0) {
			return "";
		}
		boolean isFirst = true;
		String pushPerson = "";
		for (ImUserNikeNameBean imRoomUserNikeName : inviteess) {
			if (isFirst) {
				isFirst = false;
			} else {
				pushPerson += ";";
			}
			pushPerson += removeUserIp(imRoomUserNikeName.getUserId());
		}
		return pushPerson;
	}

	public RbResultBean inviteChatRoom(QuestInviteChartParam param) {

		int roomInviteState = imChartRoomServiceImpl.roomInviteState(param.getRoomId());

		ImUserNikeNameBean inviter = GsonUtils.getImRoomUser(param.getInvite());
		List<ImUserNikeNameBean> inviteess = GsonUtils.getImRoomUsers(param.getInvitees());
		ChartRoomBean chatRoom = getOwnerChatRoom(param.getRoomId(), null);
		Group group = new Group(chatRoom.getRoomName(), chatRoom.getNaturalName());
		GroupPushMsgData groupPushMsgData = new GroupPushMsgData();
		groupPushMsgData.setInviter(inviter);
		groupPushMsgData.setInvitees(inviteess);
		groupPushMsgData.setGroup(group);
		groupPushMsgData.setId(sqlHelper.getUUID());
		groupPushMsgData.setInspectorMark(param.getInspectorMark());
		boolean back = true;
//		"status":0, // 0 - 无需审核加群成功；1 - 已发送审核请求，请等待管理员审核
//	      "desc":""
		InviteMarkBean inviteMarkBean = new InviteMarkBean();
		try {

			if (roomInviteState == 0) {// 不需要审核
				imChartRoomServiceImpl.saveUserNicks(inviteess);
				updateChatRoomUser(ROOM_ADD_MEMBER, inviteess);
				PushMessage<GroupPushMsgData> pushMessage = new PushMessage<>(PushType._GROUP, groupPushMsgData);
				// 推送给所有群成员
				groupPushMsgData.setAction(Action.GROUP_INFO_UPDATE);
				String sendPushChartRoom = sendPushChartRoom(param.getRoomId(), RbJsonUtils.objectToJson(pushMessage));
				if (StringUtils.isEmpty(sendPushChartRoom)) {
					back = false;
				}
				inviteMarkBean.setDesc("无需审核加群成功");
				inviteMarkBean.setStatus(0);
			} else {
				groupPushMsgData.setAction(Action.INVITE);
				// 推送被邀请人显示状态
				PushMessage<GroupPushMsgData> pushMessage = new PushMessage<>(PushType._GROUP, groupPushMsgData);
				sendPushPersons(getPushPersion(inviteess), RbJsonUtils.objectToJson(pushMessage));

				// 推送审核给管理员
				groupPushMsgData.setAction(Action.REVIEW_NEEDED);
				PushMessage<GroupPushMsgData> pushMessage2 = new PushMessage<>(PushType._GROUP, groupPushMsgData);
				String sendPushPersons = sendPushPersons(getPushPersion(chatRoom.getVerdicts()),
						RbJsonUtils.objectToJson(pushMessage2));
				if (StringUtils.isEmpty(sendPushPersons)) {
					back = false;
				}
				inviteMarkBean.setDesc("已发送审核请求，请等待管理员审核");
				inviteMarkBean.setStatus(1);
			}
		} catch (Exception e) {
			back = false;
		}

		if (back) {
			return RbResultBean.getSuccess(inviteMarkBean);
		}
		return RbResultBean.getError("邀请加入群组 失败,请求错误");

	}

	// 审核接口： 加入群组，发推送（所有人） 发送邀请者
	// roomId
	// 审核人的信息
	// 审核结果
	public boolean checkInviteChatRoom(QuestCheckInviteParam param) {

		ImUserNikeNameBean inspector = GsonUtils.getImRoomUser(param.getInspector());
		PushMessage<GroupPushMsgData> pushMessage = GsonUtils.getPushMessage(param.getInvitePushMsg());
		pushMessage.getData().setInspector(inspector);
		pushMessage.getData().setInspectorMark(param.getInspectorMark());
		Boolean back = true;
		if (param.getResult() == null || param.getResult().equals("false")) {
			pushMessage.getData().setInspectorResult(false);
		} else {
			pushMessage.getData().setInspectorResult(true);
			imChartRoomServiceImpl.saveUserNicks(pushMessage.getData().getInvitees());
			back = updateChatRoomUser(ROOM_ADD_MEMBER, pushMessage.getData().getInvitees());
		}

		pushMessage.getData().setAction(Action.GROUP_INFO_UPDATE);
		String sendPushChartRoom = sendPushChartRoom(param.getRoomId(), RbJsonUtils.objectToJson(pushMessage));
		ChartRoomBean chatRoom = getOwnerChatRoom(param.getRoomId(), null);
		if (StringUtils.isEmpty(sendPushChartRoom)) {
			back = false;
		}

		pushMessage.getData().setAction(Action.REVIEW_RESULT);
		String sendPushPersons = sendPushPersons(getPushPersion(chatRoom.getVerdicts()),
				RbJsonUtils.objectToJson(pushMessage));

		if (StringUtils.isEmpty(sendPushPersons)) {
			back = false;
		}
		return back;
	}

	/**
	 * 审核是否发言
	 * 
	 * @param roomId
	 * @param invitePushMsg
	 * @param inspectorJson
	 * @param result
	 * @param inspectorMark
	 * @return
	 */
	public boolean checkSpeak(QuestCheckInviteParam param) {

		ImUserNikeNameBean inspector = GsonUtils.getImRoomUser(param.getInspector());
		PushMessage<GroupPushMsgData> pushMessage =  GsonUtils.getPushMessage(param.getInvitePushMsg());
		pushMessage.getData().setInspector(inspector);
		pushMessage.getData().setInspectorMark(param.getInspectorMark());
		pushMessage.getData().setInspectorResult(param.getResult().equals("1"));
		ImRoomSpeakBean imRoomSpeak = new ImRoomSpeakBean();
		imRoomSpeak.setCanSpeak(Short.valueOf(param.getResult()));
		imRoomSpeak.setRoomId(param.getRoomId());
		imRoomSpeak.setUserId(pushMessage.getData().getInviter().getUserId());

		pushMessage.getData().setAction(Action.SPEAK_REQUEST_RESULT);
		List<ImUserNikeNameBean> invitees = pushMessage.getData().getInvitees();
		String sendPushPersons = sendPushPersons(getPushPersion(invitees), RbJsonUtils.objectToJson(pushMessage));

		pushMessage.getData().setAction(Action.SPEAK_REVIEW_RESULT);
		ImUserNikeNameBean inviter = pushMessage.getData().getInviter();
		sendPushPersons = sendPushPersons(inviter.getUserId(), RbJsonUtils.objectToJson(pushMessage));

		return !StringUtils.isEmpty(sendPushPersons);
	}

	/**
	 * 申请发言
	 * 
	 * @param roomId
	 * @param invite
	 * @param invitees
	 * @return
	 */
	public RbResultBean inviteSpeak(QuestInviteChartParam param) {
		ImUserNikeNameBean proposer = GsonUtils.getImRoomUser(param.getInvite());
		List<ImUserNikeNameBean> admins = GsonUtils.getImRoomUsers(param.getInvitees());
	
		ChartRoomBean chatRoom = getOwnerChatRoom(param.getRoomId(), null);
		Group group = new Group(chatRoom.getRoomName(), chatRoom.getNaturalName());
		GroupPushMsgData groupPushMsgData = new GroupPushMsgData();
		groupPushMsgData.setInviter(proposer);
		groupPushMsgData.setInvitees(admins);
		groupPushMsgData.setGroup(group);
		groupPushMsgData.setId(sqlHelper.getUUID());
		groupPushMsgData.setInspectorMark(param.getInspectorMark());
		boolean back = true;
//			"status":0, // 0 - 无需审核加群成功；1 - 已发送审核请求，请等待管理员审核
//		    "desc":""
		InviteMarkBean inviteMarkBean = new InviteMarkBean();
		try {
			// 推送审核给审核人员
			groupPushMsgData.setAction(Action.SPEAK_REQUEST);
			PushMessage<GroupPushMsgData> pushMessage2 = new PushMessage<>(PushType._GROUP, groupPushMsgData);
			String sendPushPersons = sendPushPersons(getPushPersion(admins), RbJsonUtils.objectToJson(pushMessage2));
			if (StringUtils.isEmpty(sendPushPersons)) {
				back = false;
			}
			inviteMarkBean.setDesc("已发送审核请求，请等待管理员审核");
			inviteMarkBean.setStatus(1);
		} catch (Exception e) {
			back = false;
		}

		if (back) {
			return RbResultBean.getSuccess(inviteMarkBean);
		}
		return RbResultBean.getError("申请发言失败,请求错误");
	}

}
