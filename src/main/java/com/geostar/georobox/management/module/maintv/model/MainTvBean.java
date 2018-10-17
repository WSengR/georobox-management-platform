package com.geostar.georobox.management.module.maintv.model;

import java.util.List;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.bean.ListLimitBean;

public class MainTvBean {
	private List<DataInfoBean> errorDataInfo;
	private long loginNum;
	private long errorNum;
	private long userNum;
	private List<DataInfoBean> plugNumChart;
	private ListLimitBean listLimitBean;
	public List<DataInfoBean> getErrorDataInfo() {
		return errorDataInfo;
	}
	public void setErrorDataInfo(List<DataInfoBean> errorDataInfo) {
		this.errorDataInfo = errorDataInfo;
	}
	public long getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(long loginNum) {
		this.loginNum = loginNum;
	}
	public long getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(long errorNum) {
		this.errorNum = errorNum;
	}
	public long getUserNum() {
		return userNum;
	}
	public void setUserNum(long userNum) {
		this.userNum = userNum;
	}
	public List<DataInfoBean> getPlugNumChart() {
		return plugNumChart;
	}
	public void setPlugNumChart(List<DataInfoBean> plugNumChart) {
		this.plugNumChart = plugNumChart;
	}
	public ListLimitBean getListLimitBean() {
		return listLimitBean;
	}
	public void setListLimitBean(ListLimitBean listLimitBean) {
		this.listLimitBean = listLimitBean;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MainTvBean [errorDataInfo=");
		builder.append(errorDataInfo);
		builder.append(", loginNum=");
		builder.append(loginNum);
		builder.append(", errorNum=");
		builder.append(errorNum);
		builder.append(", userNum=");
		builder.append(userNum);
		builder.append(", plugNumChart=");
		builder.append(plugNumChart);
		builder.append(", listLimitBean=");
		builder.append(listLimitBean);
		builder.append("]");
		return builder.toString();
	}


	
}
