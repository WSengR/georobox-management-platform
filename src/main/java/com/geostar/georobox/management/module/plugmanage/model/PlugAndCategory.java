package com.geostar.georobox.management.module.plugmanage.model;

import javax.persistence.*;

@Table(name = "RB_PLUG_AND_C")
public class PlugAndCategory {
    @Column(name = "PLUG_ID")
    private String plugId;

    @Column(name = "CATEGORY_ID")
    private String categoryId;

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
     * @return CATEGORY_ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}