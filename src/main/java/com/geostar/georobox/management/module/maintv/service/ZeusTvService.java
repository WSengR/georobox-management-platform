package com.geostar.georobox.management.module.maintv.service;

import java.util.List;

import com.geostar.georobox.management.module.maintv.model.ZeusTvBean;

public interface ZeusTvService {
	
	public List<ZeusTvBean> getZeusTvList(ZeusTvBean zeusTvBean);
	
	public int saveZeusTv(ZeusTvBean zeusTvBean);
	
	public int deleteZeusTv(String id);
	
	public int changeZeusTv(ZeusTvBean zeusTvBean);

}
