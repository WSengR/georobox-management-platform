package com.geostar.georobox.management.module.logmanage.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_LOG")
public class LogManageBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "LOG_TEXT")
    private String logText;

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
     * @return FILE_URL
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return FILE_NAME
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return LOG_TEXT
     */
    public String getLogText() {
        return logText;
    }

    /**
     * @param logText
     */
    public void setLogText(String logText) {
        this.logText = logText;
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