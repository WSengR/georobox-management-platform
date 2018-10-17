package com.geostar.georobox.management.module.openfire.model.requst;

import java.util.List;

import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.utils.RbJsonUtils;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;

public class QuestCreateRoomParam {
	private String naturalName;
	private String roomName;
	private String description;
	private int maxUsers = 0;
	private String region;
	private String groupType;
	private String owners;
	private String admins;
	private String members;

	private List<ImUserNikeNameBean> imRoomUserOwners;
	private List<ImUserNikeNameBean> imRoomUserAdmins;
	private List<ImUserNikeNameBean> imRoomUserMembers;

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

	public String getDescription() {

		return description + getRegion() + getGroupType();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(int maxUsers) {
		this.maxUsers = maxUsers;
	}

	public String getRegion() {
		if (region == null) {
			region = "##";
		}
		return "##" + region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getGroupType() {
		if (groupType == null) {
			groupType = "##";
		}
		return "##" + groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getOwners() {
		return owners;
	}

	public void setOwners(String owners) {
		this.owners = owners;
	}

	public String getAdmins() {
		return admins;
	}

	public void setAdmins(String admins) {
		this.admins = admins;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public List<ImUserNikeNameBean> getImRoomUserOwners() {
		if (StringUtils.isEmpty(owners)) {
			return null;
		}
		return RbJsonUtils.jsonToList(owners, ImUserNikeNameBean.class);
	}

	public List<ImUserNikeNameBean> getImRoomUserAdmins() {
		if (StringUtils.isEmpty(admins)) {
			return null;
		}
		return RbJsonUtils.jsonToList(admins, ImUserNikeNameBean.class);
	}

	public List<ImUserNikeNameBean> getImRoomUserMembers() {
		if (StringUtils.isEmpty(members)) {
			return null;
		}
		return RbJsonUtils.jsonToList(members, ImUserNikeNameBean.class);
	}

}
