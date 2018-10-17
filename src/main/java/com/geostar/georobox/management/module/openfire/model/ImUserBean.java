package com.geostar.georobox.management.module.openfire.model;

import javax.persistence.*;

@Table(name = "TB_ZHOUSI_IM_USER")
public class ImUserBean {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "TELPHONE")
    private String telphone;

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "ZORDER")
    private String zorder;

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return GENDER
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return TELPHONE
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * @param telphone
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * @return DEPT_ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return DEPT_NAME
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
     * @return ZORDER
     */
    public String getZorder() {
        return zorder;
    }

    /**
     * @param zorder
     */
    public void setZorder(String zorder) {
        this.zorder = zorder;
    }
}