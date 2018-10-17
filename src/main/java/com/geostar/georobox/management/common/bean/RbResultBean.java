package com.geostar.georobox.management.common.bean;

import java.util.Date;

public class RbResultBean {

	private Integer Status;
	private String Message;
	private Date Datetime = new Date();
	private Object data;

	public RbResultBean() {
		this.setStatus(1);
		this.Message = "请求成功";
	}

	public RbResultBean(Object data) {
		this();
		this.setStatus(1);
		this.data = data;
	}

	public RbResultBean(Integer Status, String Message, Object data) {
		this(data);
		this.setStatus(Status);
		this.Message = Message;
	}

	public RbResultBean setMessage(String Message) {
		this.Message = Message;
		return this;
	}

	public String getMessage() {
		return Message;
	}

	public Object getData() {
		return data;
	}

	public RbResultBean setData(Object data) {
		this.data = data;
		return this;
	}

	public static RbResultBean getSuccess() {
		return new RbResultBean();
	}

	public static RbResultBean getSuccess(Object data) {
		return new RbResultBean(data);
	}

	/**
	 * 更具操作自动返回
	 * 
	 * @param data
	 * @return
	 */
	public static RbResultBean getResultBack(Object data) {
		if (data != null && data instanceof Integer) {
			return new RbResultBean("数据执行  " + data);
		}
		return new RbResultBean(data);
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public static RbResultBean getError() {
		return new RbResultBean(-1, "请求失败", null);
	}

	public static RbResultBean getError(String msg) {
		return new RbResultBean(-1, msg, null);
	}

	public Date getDatetime() {
		return Datetime;
	}

	public void setDatetime(Date datetime) {
		Datetime = datetime;
	}

}
