package com.geostar.georobox.management.module.plugmanage.dao.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbSQLProvider;

/**
 * 
 * 描述：复杂接口提供者
 * 
 * @author wangsr
 * @date 2018年9月11日
 */
public class PlugProvider extends RbSQLProvider {
	protected static Logger logger = LoggerFactory.getLogger(PlugProvider.class);

	public String selectUserPlugBean(String permissionName) {

		String[] permissions = permissionName.split(";");
		String wheresql = " WHERE ";
		boolean isFirst = true;
		for (String string : permissions) {
			if (!StringUtils.isEmpty(string)) {
				if (!isFirst) {
					wheresql += " OR ";
				}
				wheresql += "PERMISSION_NAME = '" + string + "'";
				isFirst = false;
			}
		}

		String sql = "SELECT DISTINCT * FROM  "
				+ "( SELECT PLUG_ID FROM RB_PLUG_AND_P  WHERE PERMISSION_ID IN (SELECT PERMISSION_ID FROM RB_PLUG_PREMISSION "
				+ wheresql + ") ) temp " + "LEFT JOIN RB_PLUG ON temp.PLUG_ID = RB_PLUG.PLUG_ID "
				+ "LEFT JOIN RB_PLUG_AND_C  ON  temp.PLUG_ID = RB_PLUG_AND_C.PLUG_ID "
				+ "LEFT JOIN RB_PLUG_CATEGORY ON RB_PLUG_AND_C.CATEGORY_ID = RB_PLUG_CATEGORY.CATEGORY_ID "
				+ "WHERE PLUG_IS_DOWN = 1 AND CATEGORY_DES != '日常应用'  ORDER BY PLUG_SORT";

		return sql;
	}

	public String selectUserAddPlugBean(String permissionName) {

		String[] permissions = permissionName.split(";");
		String wheresql = " WHERE ";
		boolean isFirst = true;
		for (String string : permissions) {
			if (!StringUtils.isEmpty(string)) {
				if (!isFirst) {
					wheresql += " OR ";
				}
				wheresql += "PERMISSION_NAME = '" + string + "'";
				isFirst = false;
			}
		}

		String sql = "SELECT DISTINCT * FROM  "
				+ "( SELECT PLUG_ID FROM RB_PLUG_AND_P  WHERE PERMISSION_ID IN (SELECT PERMISSION_ID FROM RB_PLUG_PREMISSION "
				+ wheresql + ") ) temp " + "LEFT JOIN RB_PLUG ON temp.PLUG_ID = RB_PLUG.PLUG_ID "
				+ "LEFT JOIN RB_PLUG_AND_C  ON  temp.PLUG_ID = RB_PLUG_AND_C.PLUG_ID "
				+ "LEFT JOIN RB_PLUG_CATEGORY ON RB_PLUG_AND_C.CATEGORY_ID = RB_PLUG_CATEGORY.CATEGORY_ID "
				+ "WHERE PLUG_IS_DOWN = 1 AND CATEGORY_DES = '日常应用'  ORDER BY PLUG_SORT";

		return sql;
	}

//	public static void main(String[] args) {
//		System.out.println(selectUserPlugBean("grid_officer;super_administrator"));
//	}
//	

}
