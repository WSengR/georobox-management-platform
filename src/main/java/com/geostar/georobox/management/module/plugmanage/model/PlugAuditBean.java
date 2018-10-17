package com.geostar.georobox.management.module.plugmanage.model;

import java.util.List;

import javax.persistence.*;

@Table(name = "RB_PLUG_AUDIT")
public class PlugAuditBean {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "PLUG_TEMP_ID")
    private String plugTempId;

    @Column(name = "PLUG_ID")
    private String plugId;

    @Column(name = "PLUG_NAME")
    private String plugName;

    @Column(name = "PLUG_TYPE")
    private Short plugType;

    @Column(name = "PLUG_URL")
    private String plugUrl;

    @Column(name = "PLUG_ICON")
    private String plugIcon;

    @Column(name = "PLUG_PACKAGE")
    private String plugPackage;

    @Column(name = "PLUG_VERSIONCODE")
    private String plugVersioncode;

    @Column(name = "PLUG_DETAILS")
    private String plugDetails;

    @Column(name = "PLUG_SORT")
    private Long plugSort;

    @Column(name = "PLUG_NEEDINSTALL")
    private Short plugNeedinstall;

    @Column(name = "PLUG_PERMISSION")
    private String plugPermission;

    @Column(name = "PLUG_CATEGORY")
    private String plugCategory;

    @Column(name = "PLUS_LAUNCHER_ACTIVITY")
    private String plugLauncherActivity;
    
    private List<PlugPermissionBean> plugPermissionList;
    /**
     * @return PLUG_TEMP_ID
     */
    public String getPlugTempId() {
        return plugTempId;
    }

    /**
     * @param plugTempId
     */
    public void setPlugTempId(String plugTempId) {
        this.plugTempId = plugTempId;
    }

    /**
     * @return PLUG_ID
     */
    public String getPlugId() {
        return plugId;
    }

    /**
     * @param plugId
     */
    public void setPlugId(String plugId) {
        this.plugId = plugId;
    }

    /**
     * @return PLUG_NAME
     */
    public String getPlugName() {
        return plugName;
    }

    /**
     * @param plugName
     */
    public void setPlugName(String plugName) {
        this.plugName = plugName;
    }

    /**
     * @return PLUG_TYPE
     */
    public Short getPlugType() {
        return plugType;
    }

    /**
     * @param plugType
     */
    public void setPlugType(Short plugType) {
        this.plugType = plugType;
    }

    /**
     * @return PLUG_URL
     */
    public String getPlugUrl() {
        return plugUrl;
    }

    /**
     * @param plugUrl
     */
    public void setPlugUrl(String plugUrl) {
        this.plugUrl = plugUrl;
    }

    /**
     * @return PLUG_ICON
     */
    public String getPlugIcon() {
        return plugIcon;
    }

    /**
     * @param plugIcon
     */
    public void setPlugIcon(String plugIcon) {
        this.plugIcon = plugIcon;
    }

    /**
     * @return PLUG_PACKAGE
     */
    public String getPlugPackage() {
        return plugPackage;
    }

    /**
     * @param plugPackage
     */
    public void setPlugPackage(String plugPackage) {
        this.plugPackage = plugPackage;
    }

    /**
     * @return PLUG_VERSIONCODE
     */
    public String getPlugVersioncode() {
        return plugVersioncode;
    }

    /**
     * @param plugVersioncode
     */
    public void setPlugVersioncode(String plugVersioncode) {
        this.plugVersioncode = plugVersioncode;
    }

    /**
     * @return PLUG_DETAILS
     */
    public String getPlugDetails() {
        return plugDetails;
    }

    /**
     * @param plugDetails
     */
    public void setPlugDetails(String plugDetails) {
        this.plugDetails = plugDetails;
    }

    /**
     * @return PLUG_SORT
     */
    public Long getPlugSort() {
        return plugSort;
    }

    /**
     * @param plugSort
     */
    public void setPlugSort(Long plugSort) {
        this.plugSort = plugSort;
    }

    /**
     * @return PLUG_NEEDINSTALL
     */
    public Short getPlugNeedinstall() {
        return plugNeedinstall;
    }

    /**
     * @param plugNeedinstall
     */
    public void setPlugNeedinstall(Short plugNeedinstall) {
        this.plugNeedinstall = plugNeedinstall;
    }

    /**
     * @return PLUG_PERMISSION
     */
    public String getPlugPermission() {
        return plugPermission;
    }

    /**
     * @param plugPermission
     */
    public void setPlugPermission(String plugPermission) {
        this.plugPermission = plugPermission;
    }

    /**
     * @return PLUG_CATEGORY
     */
    public String getPlugCategory() {
        return plugCategory;
    }

    /**
     * @param plugCategory
     */
    public void setPlugCategory(String plugCategory) {
        this.plugCategory = plugCategory;
    }

	public String getPlugLauncherActivity() {
		return plugLauncherActivity;
	}

	public void setPlugLauncherActivity(String plugLauncherActivity) {
		this.plugLauncherActivity = plugLauncherActivity;
	}
	
	

	public List<PlugPermissionBean> getPlugPermissionList() {
		return plugPermissionList;
	}

	public void setPlugPermissionList(List<PlugPermissionBean> plugPermissionList) {
		this.plugPermissionList = plugPermissionList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlugAuditBean [plugTempId=");
		builder.append(plugTempId);
		builder.append(", plugId=");
		builder.append(plugId);
		builder.append(", plugName=");
		builder.append(plugName);
		builder.append(", plugType=");
		builder.append(plugType);
		builder.append(", plugUrl=");
		builder.append(plugUrl);
		builder.append(", plugIcon=");
		builder.append(plugIcon);
		builder.append(", plugPackage=");
		builder.append(plugPackage);
		builder.append(", plugVersioncode=");
		builder.append(plugVersioncode);
		builder.append(", plugDetails=");
		builder.append(plugDetails);
		builder.append(", plugSort=");
		builder.append(plugSort);
		builder.append(", plugNeedinstall=");
		builder.append(plugNeedinstall);
		builder.append(", plugPermission=");
		builder.append(plugPermission);
		builder.append(", plugCategory=");
		builder.append(plugCategory);
		builder.append(", plugLauncherActivity=");
		builder.append(plugLauncherActivity);
		builder.append("]");
		return builder.toString();
	}
}