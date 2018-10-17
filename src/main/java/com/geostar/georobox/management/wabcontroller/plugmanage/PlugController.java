package com.geostar.georobox.management.wabcontroller.plugmanage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geostar.georobox.management.common.bean.ListLimitBean;
import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.bean.RbResultBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugAuditBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugMarketBean;
import com.geostar.georobox.management.module.plugmanage.model.PlugUserBean;
import com.geostar.georobox.management.module.plugmanage.model.UserPlugBean;
import com.geostar.georobox.management.module.plugmanage.service.impl.PlugAuditServiceImpl;
import com.geostar.georobox.management.module.plugmanage.service.impl.PlugServiceImpl;
import com.geostar.georobox.management.module.plugmanage.service.impl.PlugUserServiceImpl;

@RestController
@RequestMapping("plug")
public class PlugController {
	protected static Logger logger = LoggerFactory.getLogger(PlugController.class);
	@Autowired
	private PlugServiceImpl plugService;
	@Autowired
	private PlugAuditServiceImpl plugAuditService;
	@Autowired
	private PlugUserServiceImpl plugUserServiceImpl;
	

	/**
	 * 上传插件
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/PlugUpload")
	public RbResultBean PlugUploadServlet(PlugAuditBean plugAuditBean) {
		logger.info("【/plugAuditBean】 " + plugAuditBean.toString());
		int saveAuditPlug = plugAuditService.saveAuditPlug(plugAuditBean);
		return RbResultBean.getResultBack(saveAuditPlug);
	}

	/**
	 * 修改插件
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/PlugChange")
	public RbResultBean PlugChangeServlet(PlugAuditBean plugAuditBean) {
		logger.info("【/plugAuditBean】 " + plugAuditBean.toString());
		int saveAuditPlug = plugAuditService.saveAuditPlug(plugAuditBean);
		return RbResultBean.getSuccess(saveAuditPlug);
	}

	/** ======================审核=================================== */

	/**
	 * 审核插件
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/PlugAudit")
	public RbResultBean PlugAuditServlet(String plugTempId) {
		logger.info("【/plugTempId】 " + plugTempId);
		int auditPlug = plugAuditService.auditPlug(plugTempId);
		return RbResultBean.getResultBack(auditPlug);
	}

	/**
	 * 删除审核的插件
	 */
	@RequestMapping("/AuditPlugDelete")
	public RbResultBean DeleteAuditServlet(String plugTempId) {
		logger.info("【/plugTempId】 " + plugTempId.toString());
		int deleteAuditPlug = plugAuditService.deleteAuditPlug(plugTempId);
		return RbResultBean.getResultBack(deleteAuditPlug);
	}

	/**
	 * 获取审核插件列表
	 */
	@RequestMapping("/AuditPlugList")
	public ListLimitBean AuditPlugList(RbParm rbParm) {
		ListLimitBean listLimitBean = new ListLimitBean();
		logger.info("【/rbParm】 " + rbParm);
		List<PlugAuditBean> selectAuditPlugList = plugAuditService.selectAuditPlugList(rbParm);
		listLimitBean.setData(selectAuditPlugList);
		listLimitBean.setCount(plugAuditService.selectPlugCount(rbParm));
		return listLimitBean;
	}

	/**
	 * 获取审核插件详情
	 */
	@RequestMapping("/AuditPlugDetail")
	public RbResultBean AuditPlugDetail(String plugTempId) {
		logger.info("【/plugTempId】 " + plugTempId);
		PlugAuditBean selectAuditPlug = plugAuditService.selectAuditPlug(plugTempId);
		return RbResultBean.getSuccess(selectAuditPlug);
	}

	/**
	 * 修改审核插件
	 */
	@RequestMapping("/AuditPlugChang")
	public RbResultBean AuditPlugChangServlet(PlugAuditBean plugAuditBean) {
		logger.info("【/plugAuditBean】 " + plugAuditBean.toString());
		int saveAuditPlug = plugAuditService.updateAuditPlug(plugAuditBean);
		return RbResultBean.getResultBack(saveAuditPlug);
	}

	/** ======================插件============================ */

	/**
	 * 获取插件列表
	 */
	@RequestMapping("/PlugList")
	public ListLimitBean PlugListServlet(RbParm rbParm) {
		logger.info("【/RbParm】 " + rbParm.toString());
		List<PlugBean> selectPlugList = plugService.selectPlugList(rbParm);
		ListLimitBean listLimitBean = new ListLimitBean();
		listLimitBean.setData(selectPlugList);
		listLimitBean.setCount(plugService.selectPlugCount(rbParm));
		return listLimitBean;
	}

	/**
	 * 获取单个插件详情
	 */
	@RequestMapping("/PlugDetail")
	public RbResultBean PlugDetailServlet(String plugId) {
		logger.info("【/plugId】 " + plugId);
		PlugBean selectPlug = plugService.selectResultPlus(plugId);
		return RbResultBean.getSuccess(selectPlug);
	}

	/**
	 * 删除插件
	 */
	@RequestMapping("/PlugDelete")
	public RbResultBean PlugDeleteServlet(String plugId) {
		logger.info("【/plugId】 " + plugId);
		return plugService.deletePlug(plugId);
	}

	/**
	 * 下架/上架插件(不删除)
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/PlugOutorUp")
	public RbResultBean PlugDownServlet(String plugId) {
		logger.info("【/plugAudplugIditBean】 " + plugId);
		int outAndUpPlug = plugService.outAndUpPlug(plugId);
		return RbResultBean.getResultBack(outAndUpPlug);
	}

	/**
	 * 上下移动
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/PlugMoveDown")
	public RbResultBean PlugMoveDownServlet(String plugId) {
		logger.info("【/plugId】 " + plugId);
		int movePlusDown = plugService.movePlusDown(plugId);
		return RbResultBean.getResultBack(movePlusDown);
	}

	/**
	 * 上下移动
	 * 
	 * @param plugAuditBean
	 */
	@RequestMapping("/PlugMoveUp")
	public RbResultBean PlugMoveUpServlet(String plugId) {
		logger.info("【/plugId】 " + plugId);
		int movePlusUp = plugService.movePlusUp(plugId);
		return RbResultBean.getResultBack(movePlusUp);
	}

	/** ======================面向业务的接口============================ */

	/**
	 * 更具分类和权限获取插件列表
	 */
	@RequestMapping("/GetPlugForPermission")
	public RbResultBean GetPlugForPermissionAndCategoryServlet(String permission) {
		logger.info("【/plugAuditBean】 " + permission);
		List<UserPlugBean> selectUserPlugByPermission = plugService.selectUserPlugByPermission(permission);
		return RbResultBean.getSuccess(selectUserPlugByPermission);
	}
	
	/**
	 * 获取用户正在使用的插件
	 */
	@RequestMapping("/GetPlugForUserUsing")
	public RbResultBean GetUserPlugUsingServlet(String userId,String permission) {
		List<UserPlugBean> userUsingPlugBean = plugService.selectUserUsingPlug(userId,permission);

		return RbResultBean.getSuccess(userUsingPlugBean);
	}
	
	/**
	 *  用户管理，添加插件
	 */
	@RequestMapping("/AddUserPlug")
	public RbResultBean AddUserPlugServlet(PlugUserBean plugUserBean) {
		int saveUserPlug = plugUserServiceImpl.saveUserPlug(plugUserBean);
		return RbResultBean.getResultBack(saveUserPlug);
	}
	
	/**
	 *  用户管理-移除插件
	 */
	@RequestMapping("/RemoveUserPlug")
	public RbResultBean RemoveUserPlugServlet(String userId,String plugId) {
		int deleteUserPlug = plugUserServiceImpl.deleteUserPlug(userId, plugId);
		return RbResultBean.getResultBack(deleteUserPlug);
	}

}
