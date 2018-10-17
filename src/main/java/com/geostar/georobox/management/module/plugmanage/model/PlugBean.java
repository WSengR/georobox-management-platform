package com.geostar.georobox.management.module.plugmanage.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Table(name = "RB_PLUG")
public class PlugBean {
    @Id
    @GeneratedValue(generator = "UUID")
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

    @Column(name = "PLUG_UP_DATE")
    private Date plugUpDate;

    @Column(name = "PLUG_IS_DOWN")
    private Short plugIsDown;

    @Column(name = "PLUG_DOWN_DATE")
    private Date plugDownDate;

    @Column(name = "PLUG_NEEDINSTALL")
    private Short plugNeedinstall;
    
    @Column(name = "PLUS_LAUNCHER_ACTIVITY")
    private String plugLauncherActivity;

	private List<String> categorys;
	
	private List<PlugPermissionBean> permissions;
    
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
     * @return PLUG_UP_DATE
     */
    public Date getPlugUpDate() {
        return plugUpDate;
    }

    /**
     * @param plugUpDate
     */
    public void setPlugUpDate(Date plugUpDate) {
        this.plugUpDate = plugUpDate;
    }

    /**
     * @return PLUG_IS_DOWN
     */
    public Short getPlugIsDown() {
        return plugIsDown;
    }

    /**
     * @param plugIsDown
     */
    public void setPlugIsDown(Short plugIsDown) {
        this.plugIsDown = plugIsDown;
    }

    /**
     * @return PLUG_DOWN_DATE
     */
    public Date getPlugDownDate() {
        return plugDownDate;
    }

    /**
     * @param plugDownDate
     */
    public void setPlugDownDate(Date plugDownDate) {
        this.plugDownDate = plugDownDate;
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
    
    

	public List<String> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}

	public List<PlugPermissionBean> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PlugPermissionBean> permissions) {
		this.permissions = permissions;
	}
	
	

	public String getPlugLauncherActivity() {
		return plugLauncherActivity;
	}

	public void setPlugLauncherActivity(String plugLauncherActivity) {
		this.plugLauncherActivity = plugLauncherActivity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlugBean [plugId=");
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
		builder.append(", plugUpDate=");
		builder.append(plugUpDate);
		builder.append(", plugIsDown=");
		builder.append(plugIsDown);
		builder.append(", plugDownDate=");
		builder.append(plugDownDate);
		builder.append(", plugNeedinstall=");
		builder.append(plugNeedinstall);
		builder.append(", plugLauncherActivity=");
		builder.append(plugLauncherActivity);
		builder.append(", categorys=");
		builder.append(categorys);
		builder.append(", permissions=");
		builder.append(permissions);
		builder.append("]");
		return builder.toString();
	}
}