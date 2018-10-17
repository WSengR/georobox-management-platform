package com.geostar.georobox.management.module.serverurl.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.serverurl.model.ServerUrlBean;

public interface ServerUrlService {
	
	public int saveServerUrl(ServerUrlBean serverUrlBean);
	
	public List<ServerUrlBean> queryServerUrlList(ServerUrlBean serverUrlBean,RbParm rbParm);
	
	public int deleteServerUrl(String id);
	
	public int getCount();
	
	public int changeServerUrl(ServerUrlBean serverUrlBean);

}
