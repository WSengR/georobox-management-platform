package com.geostar.georobox.management.module.plugmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.plugmanage.model.PlugAndPermission;
import com.geostar.georobox.management.module.plugmanage.model.PlugPermissionBean;

@Service
public interface PlugPermissionService {
	int save(PlugPermissionBean permissionBean);

	int delete(String permissionName);

	int update(PlugPermissionBean permissionBean);

	List<PlugPermissionBean> selectPermissionList(RbParm rbParm);

	PlugPermissionBean selectPermissionById(String permissionId);
	
	List<PlugPermissionBean> selectPermissionByIds(String permissionIds);

	PlugPermissionBean selectPermissionByName(String permissionId, String permissionName);
	
	int savePAP(PlugAndPermission plugAndPermission);
	
	void savePAPByPermissions(String plugId,String permissions);
	
	int selectPAPCount(PlugAndPermission plugAndPermission);
	
	List<PlugPermissionBean> selectPAPByPlugId(String plugId);

	int deletePAPByPlugId(String plugId);
	
	int deletePAPByPermissionId(String permissionId);
	
	int selectPermissionCount(RbParm rbParm);

}
