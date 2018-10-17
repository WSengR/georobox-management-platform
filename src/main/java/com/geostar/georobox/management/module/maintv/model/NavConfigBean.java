package com.geostar.georobox.management.module.maintv.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_NAV_CONGFIG")
public class NavConfigBean {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "HREF")
    private String href;

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
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return ICON
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return HREF
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
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
}