package com.geostar.georobox.management.module.openfire.dao.provider;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

import com.geostar.georobox.management.common.utils.RbExample;
import com.geostar.georobox.management.module.openfire.model.ImRemindBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomSpeakBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomStateBean;
import com.geostar.georobox.management.module.openfire.model.ImTopBean;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;

import tk.mybatis.mapper.entity.Example;

@Configuration
public class ImExample extends RbExample {

	/** ======================= 消息提醒 ===================== */
	/**
	 * 删除条件查询
	 * 
	 * @param logManageBean
	 * @param rbParm
	 * @return
	 */
	public Example deleteImRemindFilter(ImRemindBean imRemindBean) {
		Example example = new Example(ImRemindBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("type", imRemindBean.getType());
		criteria.andEqualTo("userId", imRemindBean.getUserId());
		if (!StringUtils.isEmptyOrWhitespace(imRemindBean.getOtherUserId())) {
			criteria.andEqualTo("otherUserId", imRemindBean.getOtherUserId());
		}
		if (!StringUtils.isEmptyOrWhitespace(imRemindBean.getOtherRoomId())) {
			criteria.andEqualTo("otherRoomId", imRemindBean.getOtherRoomId());
		}
		return example;
	}

	/**
	 * 
	 * @param ImRemindBean
	 * @param rbParm
	 * @return
	 */
	public Example selectImRemindFilter(ImRemindBean imRemindBean) {
		Example example = new Example(ImRemindBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", imRemindBean.getUserId());
		criteria.andEqualTo("type", imRemindBean.getType());
		criteria.andEqualTo("isRemind", "1");
		if (!StringUtils.isEmptyOrWhitespace(imRemindBean.getOtherUserId())) {
			criteria.andEqualTo("otherUserId", imRemindBean.getOtherUserId());
		}
		if (!StringUtils.isEmptyOrWhitespace(imRemindBean.getOtherRoomId())) {
			criteria.andEqualTo("otherRoomId", imRemindBean.getOtherRoomId());
		}
		example.orderBy("datetime").desc();
		return example;
	}

	/** ======================= 置顶 ===================== */
	/**
	 * 删除条件查询
	 * 
	 * @param logManageBean
	 * @param rbParm
	 * @return
	 */
	public Example deleteImTopBeanFilter(ImTopBean imTopBean) {
		Example example = new Example(ImTopBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("topType", imTopBean.getTopType());
		criteria.andEqualTo("userId", imTopBean.getUserId());
		if (!StringUtils.isEmptyOrWhitespace(imTopBean.getOtherUserId())) {
			criteria.andEqualTo("otherUserId", imTopBean.getOtherUserId());
		}
		if (!StringUtils.isEmptyOrWhitespace(imTopBean.getOtherRoomId())) {
			criteria.andEqualTo("otherRoomId", imTopBean.getOtherRoomId());
		}
		return example;
	}

	/**
	 * 
	 * @param ImRemindBean
	 * @param rbParm
	 * @return
	 */
	public Example selectImTopBeanFilter(ImTopBean imTopBean) {
		Example example = new Example(ImTopBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", imTopBean.getUserId());
		criteria.andEqualTo("topType", imTopBean.getTopType());
		if (!StringUtils.isEmptyOrWhitespace(imTopBean.getOtherUserId())) {
			criteria.andEqualTo("otherUserId", imTopBean.getOtherUserId());
		}
		if (!StringUtils.isEmptyOrWhitespace(imTopBean.getOtherRoomId())) {
			criteria.andEqualTo("otherRoomId", imTopBean.getOtherRoomId());
		}
		example.orderBy("datetime").desc();
		return example;
	}

	/** ======================= 发言 ===================== */

	/**
	 * 
	 * @param ImRemindBean
	 * @param rbParm
	 * @return
	 */
	public Example selectCanSpeakFilter(String userId ,String roomId) {
		Example example = new Example(ImRoomSpeakBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("roomId", roomId);
		return example;
	}

	/** ======================= 群昵称 ===================== */
	/**
	 * 获取群所有的昵称
	 * 
	 * @param ImRemindBean
	 * @param rbParm
	 * @return
	 */
	public Example selectUserNickFilter(String roomId) {
		Example example = new Example(ImUserNikeNameBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("roomId", roomId);
		return example;
	}
	
	/**
	 * 删除条件
	 * 
	 * @param ImRemindBean
	 * @param rbParm
	 * @return
	 */
	public Example deleteUserNickFilter(ImUserNikeNameBean imUserNikeNameBean) {
		Example example = new Example(ImUserNikeNameBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("roomId", imUserNikeNameBean.getRoomId());
		criteria.andEqualTo("userId", imUserNikeNameBean.getUserId());
		return example;
	}
	
	/** ======================= 群状态是否需要审核 ===================== */
	/**
	 * 删除条件
	 * 
	 * @param ImRemindBean
	 * @param rbParm
	 * @return
	 */
	public Example selectRoomStateFilter(String roomId) {
		Example example = new Example(ImRoomStateBean.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("roomId", roomId);
		return example;
	}
	
	
	
}
