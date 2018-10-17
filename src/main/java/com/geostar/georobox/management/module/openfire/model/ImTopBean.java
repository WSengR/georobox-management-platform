package com.geostar.georobox.management.module.openfire.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "TB_ZHOUSI_IM_TOP")
public class ImTopBean {
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TOP_TYPE")
    private Short topType;

    @Column(name = "OTHER_USER_ID")
    private String otherUserId;

    @Column(name = "OTHER_ROOM_ID")
    private String otherRoomId;

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
     * @return TOP_TYPE
     */
    public Short getTopType() {
        return topType;
    }

    /**
     * @param topType
     */
    public void setTopType(Short topType) {
        this.topType = topType;
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