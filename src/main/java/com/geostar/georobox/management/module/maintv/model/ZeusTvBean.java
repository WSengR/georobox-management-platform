package com.geostar.georobox.management.module.maintv.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_ZEUS_TV")
public class ZeusTvBean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "ZS_NAME")
    private String zsName;

    @Column(name = "ZS_SERVEURL")
    private String zsServeurl;

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
     * @return ZS_NAME
     */
    public String getZsName() {
        return zsName;
    }

    /**
     * @param zsName
     */
    public void setZsName(String zsName) {
        this.zsName = zsName;
    }

    /**
     * @return ZS_SERVEURL
     */
    public String getZsServeurl() {
        return zsServeurl;
    }

    /**
     * @param zsServeurl
     */
    public void setZsServeurl(String zsServeurl) {
        this.zsServeurl = zsServeurl;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZeusTvBean [id=");
		builder.append(id);
		builder.append(", zsName=");
		builder.append(zsName);
		builder.append(", zsServeurl=");
		builder.append(zsServeurl);
		builder.append(", isOpen=");
		builder.append(isOpen);
		builder.append(", datetime=");
		builder.append(datetime);
		builder.append("]");
		return builder.toString();
	}
    
}