package com.geostar.georobox.management.module.openfire.dao.provider;

import java.util.List;

import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;
import com.geostar.georobox.management.module.openfire.model.push.Group;
import com.geostar.georobox.management.module.openfire.model.push.GroupPushMsgData;
import com.geostar.georobox.management.module.openfire.model.push.PushMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



/**
 * @author hanlyjiang on 2018/8/17-15:28.
 * @version 1.0
 */
public class GsonUtils {

	private static Gson gson = new Gson();

	public static Gson getGson() {
		return gson;
	}

	public static PushMessage<GroupPushMsgData> getPushMessage(String invitePushMsg) {
		return gson.fromJson(invitePushMsg, new TypeToken<PushMessage<GroupPushMsgData>>() {
		}.getType());
	}

	public static ImUserNikeNameBean getImRoomUser(String imRoomUserJson) {
		return gson.fromJson(imRoomUserJson, ImUserNikeNameBean.class);
	}

	public static List<ImUserNikeNameBean> getImRoomUsers(String imRoomUserJsons) {
		return gson.fromJson(imRoomUserJsons, new TypeToken<List<ImUserNikeNameBean>>() {
		}.getType());
	}

	public static Group getGroup(String group) {
		return gson.fromJson(group, Group.class);
	}
	
}
