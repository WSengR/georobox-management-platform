package com.geostar.georobox.management.common.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "com.geostar.georobox.management")
@PropertySource(value = "classpath:resource.properties")
public class Resource {

	private String ucenterUrl;
	private String openFireUrl;
	private String openFireToken;

	private String openFireIp;
	private int openFireHost;
	private boolean logDebug;
	private boolean logWrite;
	private String logfolder;

	private String openFirePushApi;

	public String getUcenterUrl() {
		return ucenterUrl;
	}

	public void setUcenterUrl(String ucenterUrl) {
		this.ucenterUrl = ucenterUrl;
	}

	public String getOpenFireUrl() {
		return openFireUrl;
	}

	public void setOpenFireUrl(String openFireUrl) {
		this.openFireUrl = openFireUrl;
	}

	public String getOpenFireToken() {
		return openFireToken;
	}

	public void setOpenFireToken(String openFireToken) {
		this.openFireToken = openFireToken;
	}

	public String getOpenFireIp() {
		return openFireIp;
	}

	public void setOpenFireIp(String openFireIp) {
		this.openFireIp = openFireIp;
	}

	public int getOpenFireHost() {
		return openFireHost;
	}

	public void setOpenFireHost(int openFireHost) {
		this.openFireHost = openFireHost;
	}

	public boolean isLogDebug() {
		return logDebug;
	}

	public void setLogDebug(boolean logDebug) {
		this.logDebug = logDebug;
	}

	public boolean isLogWrite() {
		return logWrite;
	}

	public void setLogWrite(boolean logWrite) {
		this.logWrite = logWrite;
	}

	public String getLogfolder() {
		return logfolder;
	}

	public void setLogfolder(String logfolder) {
		this.logfolder = logfolder;
	}

	public String getOpenFirePushApi() {
		return openFirePushApi;
	}

	public void setOpenFirePushApi(String openFirePushApi) {
		this.openFirePushApi = openFirePushApi;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resource [ucenterUrl=");
		builder.append(ucenterUrl);
		builder.append(", openFireUrl=");
		builder.append(openFireUrl);
		builder.append(", openFireToken=");
		builder.append(openFireToken);
		builder.append(", openFireIp=");
		builder.append(openFireIp);
		builder.append(", openFireHost=");
		builder.append(openFireHost);
		builder.append(", logDebug=");
		builder.append(logDebug);
		builder.append(", logWrite=");
		builder.append(logWrite);
		builder.append(", logfolder=");
		builder.append(logfolder);
		builder.append(", openFirePushApi=");
		builder.append(openFirePushApi);
		builder.append("]");
		return builder.toString();
	}

}
