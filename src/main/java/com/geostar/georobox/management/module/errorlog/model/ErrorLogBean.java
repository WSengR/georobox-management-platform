package com.geostar.georobox.management.module.errorlog.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_ERROR_LOG")
public class ErrorLogBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "PHONE_MODE")
    private String phoneMode;

    @Column(name = "PHONE_VERSION")
    private String phoneVersion;

    @Column(name = "PHONE_MANUFACTURER")
    private String phoneManufacturer;

    @Column(name = "PHONE_MAC")
    private String phoneMac;

    @Column(name = "CODE_VERSIONNAME")
    private String codeVersionname;

    @Column(name = "CODE_VERSIONCODE")
    private String codeVersioncode;

    @Column(name = "APP_PACKAGENAME")
    private String appPackagename;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "LOG_URL")
    private String logUrl;

    @Column(name = "INFO")
    private String info;

    @Column(name = "USER_INFO")
    private String userInfo;

    @Column(name = "DATETIME")
    private Date datetime;

    @Column(name = "IS_COMPLETED")
    private Short isCompleted;

    @Column(name = "ERROR_INFO")
    private String errorInfo;

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
     * @return PHONE_MODE
     */
    public String getPhoneMode() {
        return phoneMode;
    }

    /**
     * @param phoneMode
     */
    public void setPhoneMode(String phoneMode) {
        this.phoneMode = phoneMode;
    }

    /**
     * @return PHONE_VERSION
     */
    public String getPhoneVersion() {
        return phoneVersion;
    }

    /**
     * @param phoneVersion
     */
    public void setPhoneVersion(String phoneVersion) {
        this.phoneVersion = phoneVersion;
    }

    /**
     * @return PHONE_MANUFACTURER
     */
    public String getPhoneManufacturer() {
        return phoneManufacturer;
    }

    /**
     * @param phoneManufacturer
     */
    public void setPhoneManufacturer(String phoneManufacturer) {
        this.phoneManufacturer = phoneManufacturer;
    }

    /**
     * @return PHONE_MAC
     */
    public String getPhoneMac() {
        return phoneMac;
    }

    /**
     * @param phoneMac
     */
    public void setPhoneMac(String phoneMac) {
        this.phoneMac = phoneMac;
    }

    /**
     * @return CODE_VERSIONNAME
     */
    public String getCodeVersionname() {
        return codeVersionname;
    }

    /**
     * @param codeVersionname
     */
    public void setCodeVersionname(String codeVersionname) {
        this.codeVersionname = codeVersionname;
    }

    /**
     * @return CODE_VERSIONCODE
     */
    public String getCodeVersioncode() {
        return codeVersioncode;
    }

    /**
     * @param codeVersioncode
     */
    public void setCodeVersioncode(String codeVersioncode) {
        this.codeVersioncode = codeVersioncode;
    }

    /**
     * @return APP_PACKAGENAME
     */
    public String getAppPackagename() {
        return appPackagename;
    }

    /**
     * @param appPackagename
     */
    public void setAppPackagename(String appPackagename) {
        this.appPackagename = appPackagename;
    }

    /**
     * @return APP_NAME
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return LOG_URL
     */
    public String getLogUrl() {
        return logUrl;
    }

    /**
     * @param logUrl
     */
    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    /**
     * @return INFO
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
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

    /**
     * @return IS_COMPLETED
     */
    public Short getIsCompleted() {
        return isCompleted;
    }

    /**
     * @param isCompleted
     */
    public void setIsCompleted(Short isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * @return ERROR_INFO
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * @param errorInfo
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}