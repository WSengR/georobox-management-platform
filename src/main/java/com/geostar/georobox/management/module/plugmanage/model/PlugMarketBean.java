package com.geostar.georobox.management.module.plugmanage.model;

import java.util.List;

public class PlugMarketBean {

	private String type;
	private List<UserPlugBean> plugs;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<UserPlugBean> getPlugs() {
		return plugs;
	}
	public void setPlugs(List<UserPlugBean> plugs) {
		this.plugs = plugs;
	}
	
	
}
