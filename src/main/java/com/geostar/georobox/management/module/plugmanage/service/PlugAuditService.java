package com.geostar.georobox.management.module.plugmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.module.plugmanage.model.PlugAuditBean;

@Service
public interface PlugAuditService {
	public int saveAuditPlug(PlugAuditBean plugAuditBean);

	public int deleteAuditPlug(String plugTempId);

	public int updateAuditPlug(PlugAuditBean plugAuditBean);

	/**
	 * 审核插件
	 */
	public int auditPlug(String plugTempId);

	public PlugAuditBean selectAuditPlug(String plugTempId);

	public List<PlugAuditBean> selectAuditPlugList(RbParm rbParm);
	
	public int selectPlugCount(RbParm rbParm);
	
	public boolean haveAuditPlugByPlugId(String plugId);
}
