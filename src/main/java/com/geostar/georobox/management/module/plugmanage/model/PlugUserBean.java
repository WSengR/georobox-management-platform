package com.geostar.georobox.management.module.plugmanage.model;

import javax.persistence.*;

@Table(name = "RB_PLUG_USER")
public class PlugUserBean {
    @Column(name = "PLUG_ID")
    private String plugId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TAG")
    private String tag;

	public PlugUserBean() {
	}

	public PlugUserBean(String plugId, String userId) {
		this.plugId = plugId;
		this.userId = userId;
	}

	/**
     * @return PERMISSION_ID
     */
    public String getPlugId() {
        return plugId;
    }

    /**
     * @param permissionId
     */
    public void setPlugId(String plugId) {
        this.plugId = plugId;
    }

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return TAG
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}