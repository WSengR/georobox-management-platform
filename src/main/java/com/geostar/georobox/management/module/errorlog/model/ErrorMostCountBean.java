package com.geostar.georobox.management.module.errorlog.model;

import java.util.ArrayList;
import java.util.List;

import com.geostar.georobox.management.common.bean.DataInfoBean;
import com.geostar.georobox.management.common.utils.DataUtils;


public class ErrorMostCountBean {
	private long errCount;
	private List<DataInfoBean> dateChartDatas;
	private List<DataInfoBean> mostModeDatas;
	private List<DataInfoBean> mostUserDatas;
	public List<DataInfoBean> getDateChartDatas() {
		return dateChartDatas;
	}
	public void setDateChartDatas(List<DataInfoBean> dateChartDatas) {
		this.dateChartDatas = dateChartDatas;
	}
	public List<DataInfoBean> getMostModeDatas() {
		return mostModeDatas;
	}
	public void setMostModeDatas(List<DataInfoBean> mostModeDatas) {
		this.mostModeDatas = mostModeDatas;
	}
	public List<DataInfoBean> getMostUserDatas() {
		return mostUserDatas;
	}
	public void setMostUserDatas(List<DataInfoBean> mostUserDatas) {
		this.mostUserDatas = mostUserDatas;
	}
	
	public long getErrCount() {
		return errCount;
	}
	public void setErrCount(long allCount) {
		this.errCount = allCount;
	}
	public static List<String> getListData(int x){
		List<String> datalis = new ArrayList<String>();
		for (int i = 0; i < x; i++) {
			datalis.add(DataUtils.getCurrentDateAgo(i+1-x));
		}
		return datalis;
	}
	public static void main(String[] args) {
		System.out.println(getListData(7));
	}

}


