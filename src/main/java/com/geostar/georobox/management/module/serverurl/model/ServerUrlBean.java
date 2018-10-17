package com.geostar.georobox.management.module.serverurl.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_SERVER_INFO")
public class ServerUrlBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "SERVER_NAME")
    private String serverName;

    @Column(name = "SERVER_URL")
    private String serverUrl;

    @Column(name = "SERVER_DIS")
    private String serverDis;

    @Column(name = "IS_OPEN")
    private Short isOpen;

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
     * @return SERVER_NAME
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return SERVER_URL
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * @param serverUrl
     */
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * @return SERVER_DIS
     */
    public String getServerDis() {
        return serverDis;
    }

    /**
     * @param serverDis
     */
    public void setServerDis(String serverDis) {
        this.serverDis = serverDis;
    }

    /**
     * @return IS_OPEN
     */
    public Short getIsOpen() {
        return isOpen;
    }

    /**
     * @param isOpen
     */
    public void setIsOpen(Short isOpen) {
        this.isOpen = isOpen;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ServerUrlBean [id=" + id + ", serverName=" + serverName + ", serverUrl=" + serverUrl + ", serverDis="
				+ serverDis + ", isOpen=" + isOpen + ", datetime=" + datetime + "]");
		return builder.toString();
	}
    
    
}