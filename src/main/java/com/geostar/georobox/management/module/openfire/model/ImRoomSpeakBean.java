package com.geostar.georobox.management.module.openfire.model;

import javax.persistence.*;

@Table(name = "TB_ZHOUSI_IM_ROOM_SPEAK")
public class ImRoomSpeakBean {
    @Id
    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "CAN_SPEAK")
    private Short canSpeak;

    @Column(name = "USER_ID")
    private String userId;

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
     * @return CAN_SPEAK
     */
    public Short getCanSpeak() {
        return canSpeak;
    }

    /**
     * @param canSpeak
     */
    public void setCanSpeak(Short canSpeak) {
        this.canSpeak = canSpeak;
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
}