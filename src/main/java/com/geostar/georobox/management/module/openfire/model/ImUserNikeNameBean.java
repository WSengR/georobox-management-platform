package com.geostar.georobox.management.module.openfire.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "TB_ZHOUSI_IM_USER_NIKENAME")
public class ImUserNikeNameBean {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Id
    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "USER_NIKENAME")
    @JsonProperty("userNickName")
    private String userNikename;

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
     * @return ROOM_ID
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * @return USER_NIKENAME
     */
    public String getUserNikename() {
        return userNikename;
    }

    /**
     * @param userNikename
     */
    public void setUserNikename(String userNikename) {
        this.userNikename = userNikename;
    }
}