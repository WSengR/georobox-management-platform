package com.geostar.georobox.management.module.openfire.model.requst;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geostar.georobox.management.common.utils.RbJsonUtils;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;

public class QuestUpdateRoomParam {
	private String naturalName;
	private String roomName;
	private String changeUser;

	public String getNaturalName() {
		return naturalName;
	}

	public void setNaturalName(String naturalName) {
		this.naturalName = naturalName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getChangeUser() {
		return changeUser;
	}

	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}

	@JsonIgnore
	public ImUserNikeNameBean getImRoomChangeUser() {
		return RbJsonUtils.jsonToClass(changeUser, ImUserNikeNameBean.class);
	}

}
