package com.geostar.georobox.management.module.openfire.model;

import javax.persistence.*;

@Table(name = "TB_ZHOUSI_IM_ROOM_STATE")
public class ImRoomStateBean {
    @Id
    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "IS_INVITE")
    private Short isInvite;

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
     * @return IS_INVITE
     */
    public Short getIsInvite() {
        return isInvite;
    }

    /**
     * @param isInvite
     */
    public void setIsInvite(Short isInvite) {
        this.isInvite = isInvite;
    }
}