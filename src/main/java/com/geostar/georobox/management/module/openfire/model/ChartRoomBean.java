package com.geostar.georobox.management.module.openfire.model;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChartRoomBean {
	private String roomName;
	private String description;
	private String naturalName;
	private String region;
	private String groupType;
	//默认需要邀请，0不需要  1需要  
	private int inviteState = 1;
	//默认不能发言  0不能  1可以
	private int canSpeak = 0;
	private int currentCount = 0;
	private List<ImUserNikeNameBean> owners;
	private List<ImUserNikeNameBean> admins;
	private List<ImUserNikeNameBean> members;
	
	public int getInviteState() {
		return inviteState;
	}

	public void setInviteState(int inviteState) {
		this.inviteState = inviteState;
	}

	public int getCanSpeak() {
		return canSpeak;
	}

	public void setCanSpeak(int canSpeak) {
		this.canSpeak = canSpeak;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		setDescriptionOther();
	}

	public String getNaturalName() {
		return naturalName;
	}

	public void setNaturalName(String naturalName) {
		this.naturalName = naturalName;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount() {
		if(owners!=null) {
			currentCount+=owners.size();
		}
		if(admins!=null) {
			currentCount+=admins.size();
		}
		if(members!=null) {
			currentCount+= members.size();
		}
	}
	
	
	private void setDescriptionOther() {
		if (StringUtils.isEmpty(description)) {
			return;
		}
		String[] split = description.split("##");
		if (split.length > 0) {
			description = split[0];
		}
		if (split.length > 1) {
			region = split[1];
		}
		if (split.length > 2) {
			groupType = split[2];
		}
	}
	
	public String getRegion() {
		return region;
	}

	public String getGroupType() {
		return groupType;
	}

	public List<ImUserNikeNameBean> getOwners() {
		return owners;
	}

	public void setOwners(List<ImUserNikeNameBean> owners) {
		this.owners = owners;
	}

	public List<ImUserNikeNameBean> getAdmins() {
		return admins;
	}

	public void setAdmins(List<ImUserNikeNameBean> admins) {
		this.admins = admins;
	}

	public List<ImUserNikeNameBean> getMembers() {
		return members;
	}

	public void setMembers(List<ImUserNikeNameBean> members) {
		this.members = members;
	}
	
	/**
	 * 获取裁决者（创建者 + 管理员）
	 * @return
	 */
	@JsonIgnore
	public List<ImUserNikeNameBean> getVerdicts() {
		List<ImUserNikeNameBean> list = new ArrayList<>();
		list.addAll(admins);
		list.addAll(owners);
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChartRoomBean [roomName=");
		builder.append(roomName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", naturalName=");
		builder.append(naturalName);
		builder.append(", region=");
		builder.append(region);
		builder.append(", groupType=");
		builder.append(groupType);
		builder.append(", currentCount=");
		builder.append(currentCount);
		builder.append(", owners=");
		builder.append(owners);
		builder.append(", admins=");
		builder.append(admins);
		builder.append(", members=");
		builder.append(members);
		builder.append("]");
		return builder.toString();
	}

}
