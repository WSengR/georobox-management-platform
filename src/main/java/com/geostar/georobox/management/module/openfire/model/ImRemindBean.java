package com.geostar.georobox.management.module.openfire.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "TB_ZHOUSI_IM_REMIND")
public class ImRemindBean {
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TYPE")
    private Short type;

    @Column(name = "OTHER_USER_ID")
    private String otherUserId;

    @Column(name = "OTHER_ROOM_ID")
    private String otherRoomId;

    @Column(name = "IS_REMIND")
    private String isRemind;

    @Column(name = "DATETIME")
    private Date datetime = new Date();

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
     * @return TYPE
     */
    public Short getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return OTHER_USER_ID
     */
    public String getOtherUserId() {
        return otherUserId;
    }

    /**
     * @param otherUserId
     */
    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    /**
     * @return OTHER_ROOM_ID
     */
    public String getOtherRoomId() {
        return otherRoomId;
    }

    /**
     * @param otherRoomId
     */
    public void setOtherRoomId(String otherRoomId) {
        this.otherRoomId = otherRoomId;
    }

    /**
     * @return IS_REMIND
     */
    public String getIsRemind() {
        return isRemind;
    }

    /**
     * @param isRemind
     */
    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
    }

    /**
     * @return DATETIME
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}