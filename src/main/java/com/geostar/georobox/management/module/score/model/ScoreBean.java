package com.geostar.georobox.management.module.score.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_SCORE")
public class ScoreBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "USER_INFO")
    private String userInfo;

    @Column(name = "MODE_INFO")
    private String modeInfo;

    @Column(name = "APP_INFO")
    private String appInfo;

    @Column(name = "BODY")
    private String body;

    @Column(name = "STAR_SCORE")
    private String starScore;

    @Column(name = "TAG")
    private String tag;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

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
     * @return APP_INFO
     */
    public String getAppInfo() {
        return appInfo;
    }

    /**
     * @param appInfo
     */
    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    /**
     * @return BODY
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return STAR_SCORE
     */
    public String getStarScore() {
        return starScore;
    }

    /**
     * @param starScore
     */
    public void setStarScore(String starScore) {
        this.starScore = starScore;
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

    /**
     * @return IMAGE_URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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