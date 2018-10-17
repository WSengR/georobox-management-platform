package com.geostar.georobox.management.module.openfire.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geostar.georobox.management.common.utils.SQLHelper;
import com.geostar.georobox.management.module.openfire.dao.ImRemindBeanMapper;
import com.geostar.georobox.management.module.openfire.dao.ImRoomSpeakBeanMapper;
import com.geostar.georobox.management.module.openfire.dao.ImRoomStateBeanMapper;
import com.geostar.georobox.management.module.openfire.dao.ImTopBeanMapper;
import com.geostar.georobox.management.module.openfire.dao.ImUserNikeNameBeanMapper;
import com.geostar.georobox.management.module.openfire.dao.provider.ImExample;
import com.geostar.georobox.management.module.openfire.model.ImRemindBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomSpeakBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomStateBean;
import com.geostar.georobox.management.module.openfire.model.ImTopBean;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;
import com.geostar.georobox.management.module.openfire.service.ImChartRoomService;

@Service
public class ImChartRoomServiceImpl implements ImChartRoomService {
	protected static Logger logger = LoggerFactory.getLogger(ImChartRoomServiceImpl.class);

	@Autowired
	private SQLHelper sqlHelper;

	@Autowired
	private ImRemindBeanMapper imRemindBeanMapper;

	@Autowired
	private ImTopBeanMapper imTopBeanMapper;

	@Autowired
	private ImRoomSpeakBeanMapper imRoomSpeakBeanMapper;

	@Autowired
	private ImUserNikeNameBeanMapper imUserNikeNameBeanMapper;

	@Autowired
	private ImRoomStateBeanMapper imRoomStateBeanMapper;

	@Autowired
	private ImExample imExample;

	@Override
	public int saveRemindBean(ImRemindBean imRemindBean) {
		deleteRemindBean(imRemindBean);
		return imRemindBeanMapper.insertSelective(imRemindBean);
	}

	@Override
	public int deleteRemindBean(ImRemindBean imRemindBean) {
		return imRemindBeanMapper.deleteByExample(imExample.deleteImRemindFilter(imRemindBean));
	}

	@Override
	public List<ImRemindBean> selectRemindBeans(ImRemindBean imRemindBean) {
		return imRemindBeanMapper.selectByExample(imExample.selectImRemindFilter(imRemindBean));
	}

	@Override
	public int saveTopBean(ImTopBean imTopBean) {
		deleteTopBean(imTopBean);
		return imTopBeanMapper.insertSelective(imTopBean);
	}

	@Override
	public int deleteTopBean(ImTopBean imTopBean) {
		return imTopBeanMapper.deleteByExample(imExample.deleteImTopBeanFilter(imTopBean));
	}

	@Override
	public List<ImTopBean> selectTopBeans(ImTopBean imTopBean) {
		return imTopBeanMapper.selectByExample(imExample.selectImTopBeanFilter(imTopBean));
	}

	@Override
	public int selectCanSpeak(String userId,String roomId) {
		ImRoomSpeakBean selectByExample = imRoomSpeakBeanMapper
				.selectOneByExample(imExample.selectCanSpeakFilter(userId, roomId));
		if (selectByExample == null) {
			return 2;
		}
		return selectByExample.getCanSpeak();
	}
	
	@Override
	public int saveSpeakBean(ImRoomSpeakBean imRoomSpeakBean) {
		deleteSpeakBean(imRoomSpeakBean);
		int insertSelective = imRoomSpeakBeanMapper.insertSelective(imRoomSpeakBean);
		return insertSelective;
	}
	
	@Override
	public int deleteSpeakBean(ImRoomSpeakBean imRoomSpeakBean) {
		int deleteByExample = imRoomSpeakBeanMapper.deleteByExample(imExample.selectCanSpeakFilter(imRoomSpeakBean.getUserId(), imRoomSpeakBean.getRoomId()));
		return deleteByExample;
	}

	@Override
	public List<ImUserNikeNameBean> selectUserNick(String roomId) {
		return imUserNikeNameBeanMapper.selectByExample(imExample.selectUserNickFilter(roomId));
	}
	@Override
	public int saveUserNick(ImUserNikeNameBean imUserNikeNameBean) {
		return imUserNikeNameBeanMapper.insertSelective(imUserNikeNameBean);
	}

	@Override
	public int saveUserNicks(List<ImUserNikeNameBean> imUserNikeNameBean) {
		if(imUserNikeNameBean==null||imUserNikeNameBean.size()<=0) {
			return 0;
		}
		int x = 0;
		for (ImUserNikeNameBean imUserNikeNameBean2 : imUserNikeNameBean) {
			deleteUserNick(imUserNikeNameBean2);
			 x += saveUserNick(imUserNikeNameBean2);
		}
		return x ;
	}
	
	@Override
	public int deleteUserNick(ImUserNikeNameBean imUserNikeNameBean) {
		return imUserNikeNameBeanMapper.deleteByExample(imExample.deleteUserNickFilter(imUserNikeNameBean));
	}

	@Override
	public ImRoomStateBean selectImRoomState(String roomId) {
		return imRoomStateBeanMapper.selectOneByExample(imExample.selectRoomStateFilter(roomId));
	}

	@Override
	public int deleteImRoomState(String roomId) {
		return imRoomStateBeanMapper.deleteByPrimaryKey(roomId);
	}

	@Override
	public int saveImRoomState(ImRoomStateBean imRoomStateBean) {
		deleteImRoomState(imRoomStateBean.getRoomId());
		return imRoomStateBeanMapper.insertSelective(imRoomStateBean);
	}

	@Override
	public int roomInviteState(String roomId) {
		ImRoomStateBean selectImRoomState = selectImRoomState(roomId);
		if(selectImRoomState == null) {
			return 1;
		}
		return selectImRoomState.getIsInvite();
	}





	

}
