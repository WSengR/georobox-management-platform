package com.geostar.georobox.management.module.configmanage.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_CONFIG")
public class ConfigManageBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "URL")
    private String url;

    @Column(name = "PACKAGENAME")
    private String packagename;

    @Column(name = "NATIVEPATH")
    private String nativepath;

    @Column(name = "VERSION")
    private String version;

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
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return PACKAGENAME
     */
    public String getPackagename() {
        return packagename;
    }

    /**
     * @param packagename
     */
    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    /**
     * @return NATIVEPATH
     */
    public String getNativepath() {
        return nativepath;
    }

    /**
     * @param nativepath
     */
    public void setNativepath(String nativepath) {
        this.nativepath = nativepath;
    }

    /**
     * @return VERSION
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
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