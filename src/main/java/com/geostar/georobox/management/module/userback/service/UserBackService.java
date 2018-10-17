package com.geostar.georobox.management.module.userback.service;

import java.util.List;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.userback.model.UserBackBean;

public interface UserBackService {
	
	public int saveUserBack(UserBackBean userBackBean);
	
	public List<UserBackBean> queryUserBackList(UserBackBean userBackBean,RbParm rbParm);
	
	public int deleteUserBack(String id);

	public int getCount(UserBackBean userBackBean, RbParm rbParm);

	public String getFileName(String id);
	
}
