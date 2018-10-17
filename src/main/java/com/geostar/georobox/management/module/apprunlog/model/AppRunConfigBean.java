package com.geostar.georobox.management.module.apprunlog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "RB_RUN_CONFIG")
public class AppRunConfigBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "APP_PACKAGE")
    private String appPackage;

    @Column(name = "CLASS")
    private String classString;

    @Column(name = "INFO_MODE")
    private String infoMode;

    @Column(name = "INFO_USER")
    private String infoUser;

    @Column(name = "INFO_OPERATION")
    private String infoOperation;

    @Column(name = "INFO_OTHER")
    private String infoOther;

    @Column(name = "MODE_VERISON")
    private String modeVerison;

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
     * @return APP_PACKAGE
     */
    public String getAppPackage() {
        return appPackage;
    }

    /**
     * @param appPackage
     */
    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * @return CLASS
     */
    public String getClassString() {
        return classString;
    }

    /**
     * @param class
     */
    public void setClassString(String classString) {
        this.classString = classString;
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
     * @return INFO_OPERATION
     */
    public String getInfoOperation() {
        return infoOperation;
    }

    /**
     * @param infoOperation
     */
    public void setInfoOperation(String infoOperation) {
        this.infoOperation = infoOperation;
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
     * @return MODE_VERISON
     */
    public String getModeVerison() {
        return modeVerison;
    }

    /**
     * @param modeVerison
     */
    public void setModeVerison(String modeVerison) {
        this.modeVerison = modeVerison;
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
		builder.append("AppRunConfigBean [id=");
		builder.append(id);
		builder.append(", appName=");
		builder.append(appName);
		builder.append(", appPackage=");
		builder.append(appPackage);
		builder.append(", classString=");
		builder.append(classString);
		builder.append(", infoMode=");
		builder.append(infoMode);
		builder.append(", infoUser=");
		builder.append(infoUser);
		builder.append(", infoOperation=");
		builder.append(infoOperation);
		builder.append(", infoOther=");
		builder.append(infoOther);
		builder.append(", modeVerison=");
		builder.append(modeVerison);
		builder.append(", datetime=");
		builder.append(datetime);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}