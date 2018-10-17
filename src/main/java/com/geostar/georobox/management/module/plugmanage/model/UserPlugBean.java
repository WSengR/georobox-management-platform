package com.geostar.georobox.management.module.plugmanage.model;

public class UserPlugBean {
	private String plugId;
	// 插件类型1APK2链接3zip
	private int plugType;
	private String Name;
	private String Url;
	private String Version;
	private String PackageName;
	private String LauncherActivity;
	private String Icon;
	private Boolean NeedInstall;
	private String details;
	private String type;
	private String typeName;
	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getPackageName() {
		return PackageName;
	}

	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	public String getLauncherActivity() {
		return LauncherActivity;
	}

	public void setLauncherActivity(String launcherActivity) {
		LauncherActivity = launcherActivity;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}

	public Boolean getNeedInstall() {
		return NeedInstall;
	}

	public void setNeedInstall(Boolean needInstall) {
		NeedInstall = needInstall;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getPlugType() {
		return plugType;
	}

	public void setPlugType(int plugType) {
		this.plugType = plugType;
	}

	public String getPlugId() {
		return plugId;
	}

	public void setPlugId(String plugId) {
		this.plugId = plugId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
