package com.geostar.georobox.management.module.openfire.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class DeptmentBean {
	
	private String zorder;
    private List<DeptmentBean> children;
    private String deptid;
    private String createdate;
    private String lv;
    private String pid;
    private String remark;
    private String text;
    private String isdelete;
    private String id;
    private String parentid;
    private String deptname;
    public void setZorder(String zorder) {
         this.zorder = zorder;
     }
     public String getZorder() {
         return zorder;
     }

    public void setChildren(List<DeptmentBean> children) {
         this.children = children;
     }
     public List<DeptmentBean> getChildren() {
         return children;
     }

    public void setDeptid(String deptid) {
         this.deptid = deptid;
     }
     public String getDeptid() {
         return deptid;
     }

    public void setCreatedate(String createdate) {
         this.createdate = createdate;
     }
     public String getCreatedate() {
         return createdate;
     }

    public void setLv(String lv) {
         this.lv = lv;
     }
     public String getLv() {
         return lv;
     }

    public void setPid(String pid) {
         this.pid = pid;
     }
     public String getPid() {
         return pid;
     }

    public void setRemark(String remark) {
         this.remark = remark;
     }
     public String getRemark() {
         return remark;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

    public void setIsdelete(String isdelete) {
         this.isdelete = isdelete;
     }
     public String getIsdelete() {
         return isdelete;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setParentid(String parentid) {
         this.parentid = parentid;
     }
     public String getParentid() {
         return parentid;
     }

    public void setDeptname(String deptname) {
         this.deptname = deptname;
     }
     public String getDeptname() {
         return deptname;
     }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptmentBean [zorder=");
		builder.append(zorder);
		builder.append(", children=");
		builder.append(children);
		builder.append(", deptid=");
		builder.append(deptid);
		builder.append(", createdate=");
		builder.append(createdate);
		builder.append(", lv=");
		builder.append(lv);
		builder.append(", pid=");
		builder.append(pid);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", text=");
		builder.append(text);
		builder.append(", isdelete=");
		builder.append(isdelete);
		builder.append(", id=");
		builder.append(id);
		builder.append(", parentid=");
		builder.append(parentid);
		builder.append(", deptname=");
		builder.append(deptname);
		builder.append("]");
		return builder.toString();
	}
     
     
     
}
