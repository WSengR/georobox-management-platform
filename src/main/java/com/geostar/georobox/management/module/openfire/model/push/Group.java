package com.geostar.georobox.management.module.openfire.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Group {
	@JsonProperty("groupId")
	private String groupId;
	@JsonProperty("groupName")
	private String groupName;
	
	public Group() {
	}

	public Group(String groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Group [groupId=");
		builder.append(groupId);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append("]");
		return builder.toString();
	}
}
