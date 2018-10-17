package com.geostar.georobox.management.common.bean;

public class ListLimitBean {
	private int code;
	private String massage = "请求成功";
	private Object data;
	private long count;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String msg) {
		this.massage = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogListBean [code=");
		builder.append(code);
		builder.append(", msg=");
		builder.append(massage);
		builder.append(", data=");
		builder.append(data);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
	
}
