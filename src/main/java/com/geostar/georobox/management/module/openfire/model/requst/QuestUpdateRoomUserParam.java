package com.geostar.georobox.management.module.openfire.model.requst;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geostar.georobox.management.common.utils.RbJsonUtils;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;
import com.geostar.georobox.management.module.openfire.model.push.Group;


public class QuestUpdateRoomUserParam {
	
	private String roomAndUsersJson ;
	private String interfaces ;
	private String groupJson;
	private String deleteType;
	
	public String getRoomAndUsersJson() {
		return roomAndUsersJson;
	}

	public void setRoomAndUsersJson(String roomAndUsersJson) {
		this.roomAndUsersJson = roomAndUsersJson;
	}

	public String getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}

	public String getGroupJson() {
		return groupJson;
	}

	public void setGroupJson(String groupJson) {
		this.groupJson = groupJson;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
	@JsonIgnore
	public List<ImUserNikeNameBean> getRoomAndUserBeans() {
		return RbJsonUtils.jsonToList(roomAndUsersJson, ImUserNikeNameBean.class);
	}

	public Group getGroup() {
		return RbJsonUtils.jsonToClass(groupJson, Group.class);
	}
	
}
