package com.geostar.georobox.management.module.apkup.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_APK_INFO")
public class ApkInfoBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "APK_NAME")
    private String apkName;

    @Column(name = "APK_VERSION")
    private String apkVersion;

    @Column(name = "APK_VERSION_CODE")
    private String apkVersionCode;

    @Column(name = "APK_DESCRIBE")
    private String apkDescribe;

    @Column(name = "APK_ICON_URL")
    private String apkIconUrl;

    @Column(name = "APK_UPLOAD_INFO")
    private String apkUploadInfo;

    @Column(name = "APK_DESCRIBE_IMAGE")
    private String apkDescribeImage;

    @Column(name = "APK_URL")
    private String apkUrl;

    @Column(name = "APK_PACKAGENAME")
    private String apkPackagename;

    @Column(name = "DOWNLOAD_NUM")
    private Short downloadNum;

    @Column(name = "APK_SIZE")
    private String apkSize;

    @Column(name = "MASTUPLOAD")
    private Short mastupload;

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
     * @return APK_NAME
     */
    public String getApkName() {
        return apkName;
    }

    /**
     * @param apkName
     */
    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    /**
     * @return APK_VERSION
     */
    public String getApkVersion() {
        return apkVersion;
    }

    /**
     * @param apkVersion
     */
    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    /**
     * @return APK_VERSION_CODE
     */
    public String getApkVersionCode() {
        return apkVersionCode;
    }

    /**
     * @param apkVersionCode
     */
    public void setApkVersionCode(String apkVersionCode) {
        this.apkVersionCode = apkVersionCode;
    }

    /**
     * @return APK_DESCRIBE
     */
    public String getApkDescribe() {
        return apkDescribe;
    }

    /**
     * @param apkDescribe
     */
    public void setApkDescribe(String apkDescribe) {
        this.apkDescribe = apkDescribe;
    }

    /**
     * @return APK_ICON_URL
     */
    public String getApkIconUrl() {
        return apkIconUrl;
    }

    /**
     * @param apkIconUrl
     */
    public void setApkIconUrl(String apkIconUrl) {
        this.apkIconUrl = apkIconUrl;
    }

    /**
     * @return APK_UPLOAD_INFO
     */
    public String getApkUploadInfo() {
        return apkUploadInfo;
    }

    /**
     * @param apkUploadInfo
     */
    public void setApkUploadInfo(String apkUploadInfo) {
        this.apkUploadInfo = apkUploadInfo;
    }

    /**
     * @return APK_DESCRIBE_IMAGE
     */
    public String getApkDescribeImage() {
        return apkDescribeImage;
    }

    /**
     * @param apkDescribeImage
     */
    public void setApkDescribeImage(String apkDescribeImage) {
        this.apkDescribeImage = apkDescribeImage;
    }

    /**
     * @return APK_URL
     */
    public String getApkUrl() {
        return apkUrl;
    }

    /**
     * @param apkUrl
     */
    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    /**
     * @return APK_PACKAGENAME
     */
    public String getApkPackagename() {
        return apkPackagename;
    }

    /**
     * @param apkPackagename
     */
    public void setApkPackagename(String apkPackagename) {
        this.apkPackagename = apkPackagename;
    }

    /**
     * @return DOWNLOAD_NUM
     */
    public Short getDownloadNum() {
        return downloadNum;
    }

    /**
     * @param downloadNum
     */
    public void setDownloadNum(Short downloadNum) {
        this.downloadNum = downloadNum;
    }

    /**
     * @return APK_SIZE
     */
    public String getApkSize() {
        return apkSize;
    }

    /**
     * @param apkSize
     */
    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    /**
     * @return MASTUPLOAD
     */
    public Short getMastupload() {
        return mastupload;
    }

    /**
     * @param mastupload
     */
    public void setMastupload(Short mastupload) {
        this.mastupload = mastupload;
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