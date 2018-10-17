package com.geostar.georobox.management.common.bean;

/**
 * 
 * 描述：Robox 基础参数类型
 * 		用于同一请求参数封装
 * @author wangsr
 * @date 2018年9月3日
 */
public class RbParm {
	public String startTime;
	public String endTime;
	public Integer page;
	public Integer limit;
	public String searchKey;


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	public String getSearchKey() {
		return searchKey;
	}


	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RbParm [startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", page=");
		builder.append(page);
		builder.append(", limit=");
		builder.append(limit);
		builder.append(", searchKey=");
		builder.append(searchKey);
		builder.append("]");
		return builder.toString();
	}

}
