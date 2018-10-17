package com.geostar.georobox.management.module.openfire.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.geostar.georobox.management.module.openfire.model.ImRemindBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomSpeakBean;
import com.geostar.georobox.management.module.openfire.model.ImRoomStateBean;
import com.geostar.georobox.management.module.openfire.model.ImTopBean;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;

@Service
public interface ImChartRoomService {
	int saveRemindBean(ImRemindBean imRemindBean);
	
	int deleteRemindBean(ImRemindBean imRemindBean); 
	
	List<ImRemindBean> selectRemindBeans(ImRemindBean imRemindBean); 
	
	int saveTopBean(ImTopBean imTopBean);
	
	int deleteTopBean(ImTopBean imTopBean);
	
	List<ImTopBean> selectTopBeans(ImTopBean imTopBean);
	
	int selectCanSpeak(String userId,String roomId);
	
	List<ImUserNikeNameBean> selectUserNick(String roomId);
	
	int saveSpeakBean(ImRoomSpeakBean imRoomSpeakBean);
	
	int deleteSpeakBean(ImRoomSpeakBean imRoomSpeakBean);
	
	int saveUserNick(ImUserNikeNameBean imUserNikeNameBean);
	
	int saveUserNicks(List<ImUserNikeNameBean> imUserNikeNameBean);
	
	int deleteUserNick(ImUserNikeNameBean imUserNikeNameBean);
	
	ImRoomStateBean selectImRoomState(String roomId);
	
	int deleteImRoomState(String roomId);
	
	int saveImRoomState(ImRoomStateBean imRoomStateBean);
	
	int roomInviteState(String roomId);
	
	
	/**========================业务相关===========================*/
	
	
}
