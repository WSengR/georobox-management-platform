package com.geostar.georobox.management.module.openfire.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeptmentResultBean {
	@JsonProperty("Status")
	private String status;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Datetime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date datetime;
	
	private Data data;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public int resultSize() {
		if(data != null && data.rows != null) {
			return data.rows.size();
		}
		
		return 0;
	}

	public class Data {
		private boolean isOk;
		
		private List<DeptmentBean> rows;

		public boolean isOk() {
			return isOk;
		}

		public void setOk(boolean isOk) {
			this.isOk = isOk;
		}

		public List<DeptmentBean> getRows() {
			return rows;
		}

		public void setRows(List<DeptmentBean> rows) {
			this.rows = rows;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Data [isOk=");
			builder.append(isOk);
			builder.append(", rows=");
			builder.append(rows);
			builder.append("]");
			return builder.toString();
		}
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptmentResultBean [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", datetime=");
		builder.append(datetime);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
}
