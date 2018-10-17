package com.geostar.georobox.management.module.openfire.dao.provider;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.MUCRoomEntities;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.bean.Resource;
import com.geostar.georobox.management.common.utils.RestHttpUtils;

@Configuration
public class ImHttpRestApi extends RestHttpUtils implements ImConfig {

	protected static Logger logger = LoggerFactory.getLogger(ImHttpRestApi.class);

	@Autowired
	private Resource resource;

	public static final String DEF_FROM = "admin";

	/**
	 * 获取所有用户
	 * 
	 * @param AccessToken
	 * @return
	 */
	@Deprecated
	public String getAllUsers(String AccessToken) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("Method", "GetAllUserList");
		params.add("ReturnFormat", "JSON");
		params.add("AccessToken", AccessToken);
		return sendPostRequest(resource.getUcenterUrl() + "/gwapi", params);
	}

	/**
	 * 更具DeptId获取所有用户
	 * 
	 * @param DeptID 如果为空则全部创建
	 * @return
	 */
	public String getUsersFordid(String DeptID) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("Method", "GetAllUserList");
		params.add("ReturnFormat", "JSON");
		params.add("DeptID", DeptID);
		return sendPostRequest(resource.getUcenterUrl() + "/gwapi", params);
	}

	/**
	 * 获取所有组织架构
	 * 
	 * @return
	 */
	public String getAllDeptment() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("Method", "GetAllDeptmentList");
		params.add("ReturnFormat", "JSON");
		return sendPostRequest(resource.getUcenterUrl() + "/gwapi", params);
	}

	/**
	 * 发送推送
	 * 
	 * @param address
	 * @param type
	 * @param body
	 * @param from
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String sendPushMsg(String address, String type, String body, String from) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("address", address);
		params.add("type", type);
		params.add("body", body);
		if (StringUtils.isEmpty(from)) {
			params.add("from", DEF_FROM);
		} else {
			params.add("from", from);
		}
		params.add("subject", getSubject(type));
		return sendPostRequest(
				resource.getOpenFireUrl() + ":" + resource.getOpenFireHost() + resource.getOpenFirePushApi(), params);
	}

	public String sendPushChartRoom(String roomId, String body) {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("address", roomId);
//		params.add("type", SENDMSG_MUC);
//		params.add("body", body);
//		params.add("from", DEF_FROM);
//		params.add("subject", getSubject(SENDMSG_MUC));
		logger.info("群推送： roomId = " + roomId + " body = " + body);
		return sendPushMsg(roomId, SENDMSG_MUC, body, null);
	}

	public String sendPushPersons(String persons, String body) {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("address", persons);
//		params.add("type", SENDMSG_PERSION);
//		params.add("body", body);
//		params.add("from", DEF_FROM);
//		params.add("subject", getSubject(SENDMSG_PERSION));
		logger.info("个推送： persons = " + persons + " body = " + body);
		return sendPushMsg(persons, SENDMSG_PERSION, body, null);
	}

	public String getSubject(String key) {
		String subject = null;
		switch (key) {
		case SENDMSG_PERSION:
			subject = "person push";
			break;
		case SENDMSG_ALL:
			subject = "all push";
			break;
		case SENDMSG_MUC:
			subject = "chartroom push";
			break;
		case SENDMSG_GROUP:
			subject = "group push";
			break;
		case SENDMSG_MULTI:
			subject = "multi push";
			break;
		}
		return subject;
	}

	/** ====================== RestApiClient ================================ */

	private RestApiClient restApiClient;

	/**
	 * 获取一个opfire连接客户端
	 * 
	 * @return
	 */
	protected RestApiClient getRestApiClient() {
		if (restApiClient == null) {
			restApiClient = new RestApiClient(resource.getOpenFireUrl(), resource.getOpenFireHost(),
					new AuthenticationToken(resource.getOpenFireToken()), 500000);

		}
		logger.info(restApiClient.toString());
		return restApiClient;
	}

	/**
	 * 获取所有的群组
	 */
	public MUCRoomEntities getChatRooms() {
		return getRestApiClient().getChatRooms();
	}

	/**
	 * 通过ID获取群消息
	 * 
	 * @param roomName
	 */
	public MUCRoomEntity getChatRoom(String roomName) {
		try {
			return getRestApiClient().getChatRoom(roomName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 修改群名称
	 * 
	 * @param roomName
	 * @param newNaturalName
	 * @return
	 */
	public boolean updateChatRoom(String roomName, String newNaturalName) {
		RestApiClient client = getRestApiClient();
		MUCRoomEntity chatRoom = client.getChatRoom(roomName);
		chatRoom.setNaturalName(newNaturalName);
		Response updateChatRoom = client.updateChatRoom(chatRoom);
		if (updateChatRoom != null && updateChatRoom.getStatus() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 添加群成员
	 * 
	 * @param roomName
	 * @param jid
	 * @return
	 */
	public boolean updateChatRoomUser(String interfaces, String roomName, String userId) {
		Response response = null;
		switch (interfaces) {
		case ROOM_ADD_MEMBER:
			response = getRestApiClient().addMember(roomName, addUserIp(userId));
			break;
		case ROOM_DELETE_MEMBER:
			response = getRestApiClient().deleteMember(roomName, addUserIp(userId));
			break;
		case ROOM_ADD_ADMIN:
			response = getRestApiClient().addAdmin(roomName, addUserIp(userId));
			break;
		case ROOM_DELETE_ADMIN:
			response = getRestApiClient().deleteAdmin(roomName, addUserIp(userId));
			break;
		case ROOM_ADD_OWNER:
			response = getRestApiClient().addOwner(roomName, addUserIp(userId));
			break;
		case ROOM_DELETE_OWNER:
			response = getRestApiClient().deleteOwner(roomName, addUserIp(userId));
			break;
		default:
			return false;
		}
		if (response != null && response.getStatus() == 201 || response.getStatus() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 添加用户
	 * 
	 * @param roomName
	 * @param userId
	 * @return
	 */
	public boolean addMember(String roomName, String userId) {
		return updateChatRoomUser(ROOM_ADD_MEMBER, roomName, userId);
	}

	/**
	 * 添加用户
	 * 
	 * @param roomName
	 * @param userId
	 * @return
	 */
	public boolean createChatRoom(MUCRoomEntity mucRoomEntity) {
		Response createChatRoom = getRestApiClient().createChatRoom(mucRoomEntity);
		if (createChatRoom != null && createChatRoom.getStatus() == 201) {
			return true;
		}
		return false;
	}

	/**
	 * 通过RoomId删除群
	 * 
	 * @param roomName
	 * @return
	 */
	public boolean deleteChatRoom(String roomName) {
		Response response = getRestApiClient().deleteChatRoom(roomName);
		if (response != null && response.getStatus() == 201 || response.getStatus() == 200) {
			return true;
		}
		return false;
	}

	/** =============================工具方法=================================== */

	/**
	 * 拼接@192.169.36.89
	 * 
	 * @param userId
	 * @return
	 */
	public String addUserIp(String userId) {
		if (!StringUtils.contains(userId, resource.getOpenFireIp())) {
			return new StringBuffer(userId).append("@"+resource.getOpenFireIp()).toString();
		}
		return userId;
	}

	/**
	 * 移除@192.169.36.89
	 * @param userId
	 * @return
	 */
	public String removeUserIp(String userId) {
		if (StringUtils.isEmpty(userId)) {
			return "";
		}
		if (StringUtils.contains(userId, resource.getOpenFireIp())) {
			userId = userId.replace("@"+resource.getOpenFireIp(), "");
		}
		return userId;
	}

	public static int checkList(List<String> list, String key) {
		int index = list.indexOf(key);
		return index;
	}

}
