package com.geostar.georobox.management.module.userback.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_USERBACK")
public class UserBackBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "INFO_USER")
    private String infoUser;

    @Column(name = "INFO_BACK")
    private String infoBack;

    @Column(name = "INFO_BACK_URL")
    private String infoBackUrl;

    @Column(name = "INFO_MODE")
    private String infoMode;

    @Column(name = "INFO_APPINFO")
    private String infoAppinfo;

    @Column(name = "INFO_OTHER")
    private String infoOther;

    @Column(name = "DATETIME")
    private Date datetime;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return INFO_USER
     */
    public String getInfoUser() {
        return infoUser;
    }

    /**
     * @param infoUser
     */
    public void setInfoUser(String infoUser) {
        this.infoUser = infoUser;
    }

    /**
     * @return INFO_BACK
     */
    public String getInfoBack() {
        return infoBack;
    }

    /**
     * @param infoBack
     */
    public void setInfoBack(String infoBack) {
        this.infoBack = infoBack;
    }

    /**
     * @return INFO_BACK_URL
     */
    public String getInfoBackUrl() {
        return infoBackUrl;
    }

    /**
     * @param infoBackUrl
     */
    public void setInfoBackUrl(String infoBackUrl) {
        this.infoBackUrl = infoBackUrl;
    }

    /**
     * @return INFO_MODE
     */
    public String getInfoMode() {
        return infoMode;
    }

    /**
     * @param infoMode
     */
    public void setInfoMode(String infoMode) {
        this.infoMode = infoMode;
    }

    /**
     * @return INFO_APPINFO
     */
    public String getInfoAppinfo() {
        return infoAppinfo;
    }

    /**
     * @param infoAppinfo
     */
    public void setInfoAppinfo(String infoAppinfo) {
        this.infoAppinfo = infoAppinfo;
    }

    /**
     * @return INFO_OTHER
     */
    public String getInfoOther() {
        return infoOther;
    }

    /**
     * @param infoOther
     */
    public void setInfoOther(String infoOther) {
        this.infoOther = infoOther;
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