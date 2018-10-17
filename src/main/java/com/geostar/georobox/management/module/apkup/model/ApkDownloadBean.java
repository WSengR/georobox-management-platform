package com.geostar.georobox.management.module.apkup.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_APK_DOWNLOAD")
public class ApkDownloadBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "VERSION_ID")
    private String versionId;

    @Column(name = "USER_INFO")
    private String userInfo;

    @Column(name = "MODE_INFO")
    private String modeInfo;

    @Column(name = "OTHER_INFO")
    private String otherInfo;

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
     * @return VERSION_ID
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * @param versionId
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    /**
     * @return USER_INFO
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return MODE_INFO
     */
    public String getModeInfo() {
        return modeInfo;
    }

    /**
     * @param modeInfo
     */
    public void setModeInfo(String modeInfo) {
        this.modeInfo = modeInfo;
    }

    /**
     * @return OTHER_INFO
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * @param otherInfo
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
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