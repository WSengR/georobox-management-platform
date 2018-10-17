package com.geostar.georobox.management.module.plugmanage.model;

import javax.persistence.*;

@Table(name = "RB_PLUG_AND_P")
public class PlugAndPermission {
    @Column(name = "PLUG_ID")
    private String plugId;

    @Column(name = "PERMISSION_ID")
    private String permissionId;

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
}