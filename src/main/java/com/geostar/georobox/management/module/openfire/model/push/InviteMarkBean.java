package com.geostar.georobox.management.module.openfire.model.push;

public class InviteMarkBean {
	private int status;
	private String desc;
	
	
	
	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InviteMarkBean [status=");
		builder.append(status);
		builder.append(", desc=");
		builder.append(desc);
		builder.append("]");
		return builder.toString();
	}
	
}
