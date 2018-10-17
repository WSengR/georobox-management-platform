package com.geostar.georobox.management.module.schedule.model;

public class ScheduleSearch {
	public String startTimeString;
	public String endTimeString;
	public String key;
	public String getStartTimeString() {
		return startTimeString;
	}
	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleSearch [startTimeString=");
		builder.append(startTimeString);
		builder.append(", endTimeString=");
		builder.append(endTimeString);
		builder.append(", key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}

	

}
