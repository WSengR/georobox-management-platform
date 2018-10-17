package com.geostar.georobox.management.module.plugmanage.model;

import javax.persistence.*;

@Table(name = "RB_PLUG_PREMISSION")
public class PlugPermissionBean {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "PERMISSION_ID")
    private String permissionId;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;

    @Column(name = "PERMISSION_DES")
    private String permissionDes;

    /**
     * @return PERMISSION_ID
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return PERMISSION_NAME
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * @return PERMISSION_DES
     */
    public String getPermissionDes() {
        return permissionDes;
    }

    /**
     * @param permissionDes
     */
    public void setPermissionDes(String permissionDes) {
        this.permissionDes = permissionDes;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlugPermissionBean [permissionId=");
		builder.append(permissionId);
		builder.append(", permissionName=");
		builder.append(permissionName);
		builder.append(", permissionDes=");
		builder.append(permissionDes);
		builder.append("]");
		return builder.toString();
	}
}