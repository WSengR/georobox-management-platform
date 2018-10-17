package com.geostar.georobox.management.module.friend.dao.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.geostar.georobox.management.common.bean.RbParm;
import com.geostar.georobox.management.common.utils.RbSQLProvider;
import com.geostar.georobox.management.module.friend.model.quest.QuestFriendGetItemParam;

/**
 * 
 * 描述：复杂接口提供者
 * 
 * @author wangsr
 * @date 2018年9月11日
 */
public class FriendProvider extends RbSQLProvider {
	protected static Logger logger = LoggerFactory.getLogger(FriendProvider.class);

	public String selectFriendItem(QuestFriendGetItemParam param, RbParm rbParm) {
		String sql = "SELECT * FROM RB_FRIEND_ITEM WHERE 1=1 ";

		if (!StringUtils.isEmpty(param.getUserId())) {
			sql += " AND USER_ID = '" + param.getUserId() + "' ";
		}
		if (!StringUtils.isEmpty(param.getLastItemTime())) {
			sql += " AND CREATETIME < '" + param.getLastItemTime() + "' ";
		}
		sql += andBetweenDate(rbParm.startTime, rbParm.endTime);
		sql += " ORDER BY CREATETIME desc";

		logger.info("selectFriendItem sql = " + sql);
		return sql;

	}

}
