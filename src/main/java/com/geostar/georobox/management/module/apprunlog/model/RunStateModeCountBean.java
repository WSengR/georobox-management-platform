package com.geostar.georobox.management.module.apprunlog.model;

import java.util.List;

import com.geostar.georobox.management.common.bean.DataInfoBean;


/**  
 * com.robox.logsystem.bean.runstate
 * Description:  
 * @author WangSR 
 * @date 2018年4月12日  
 */
public class RunStateModeCountBean {
	private long runNum;
	private List<DataInfoBean> runCountDatas;
	private List<DataInfoBean> plugCountDatas;
	private List<DataInfoBean> packageCountDatas;
	private List<DataInfoBean> userCountDatas;
	public List<DataInfoBean> getRunCountDatas() {
		return runCountDatas;
	}
	public void setRunCountDatas(List<DataInfoBean> runCountDatas) {
		this.runCountDatas = runCountDatas;
	}
	public List<DataInfoBean> getPlugCountDatas() {
		return plugCountDatas;
	}
	public void setPlugCountDatas(List<DataInfoBean> plugCountDatas) {
		this.plugCountDatas = plugCountDatas;
	}
	
	public long getRunNum() {
		return runNum;
	}
	public void setRunNum(long runNum) {
		this.runNum = runNum;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunStateModeCountBean [runNum=");
		builder.append(runNum);
		builder.append(", runCountDatas=");
		builder.append(runCountDatas);
		builder.append(", plugCountDatas=");
		builder.append(plugCountDatas);
		builder.append(", packageCountDatas=");
		builder.append(packageCountDatas);
		builder.append(", userCountDatas=");
		builder.append(userCountDatas);
		builder.append("]");
		return builder.toString();
	}
	public List<DataInfoBean> getPackageCountDatas() {
		return packageCountDatas;
	}
	public void setPackageCountDatas(List<DataInfoBean> packageCountDatas) {
		this.packageCountDatas = packageCountDatas;
	}
	public List<DataInfoBean> getUserCountDatas() {
		return userCountDatas;
	}
	public void setUserCountDatas(List<DataInfoBean> userCountDatas) {
		this.userCountDatas = userCountDatas;
	}

}
