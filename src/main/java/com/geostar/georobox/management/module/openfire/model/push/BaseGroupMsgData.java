package com.geostar.georobox.management.module.openfire.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;

/**
 * Create on 2018/9/4
 *
 * @author:胡思龙 Description:推送消息基类
 */
public class BaseGroupMsgData {

    @JsonProperty("id")
    private String id;

    private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @JsonProperty("date")
    private String time = sFormat.format(System.currentTimeMillis());

    @JsonProperty("action")
    private Action action;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * 1，邀请入群消息：包含邀请者，被邀请者，群组信息，推送给管理员和拥有者。
     * 2，入群审核消息：包含邀请者，被邀请者，群组信息，推送给管理员和拥有者。
     * 3，审核通过消息：包含审核者，邀请者，被邀请者，群组信息，推送给其他管理员、拥有者、邀请者、被邀请者。
     * 4，拒绝审核消息：包含审核者，邀请者，被邀请者，群组信息，推送给其他管理员、拥有者、邀请者、被邀请者。
     */
    public enum Action {
        INVITE,

        REVIEW_NEEDED,
        /**
         * 审核通过或者审核拒绝的结果推送
         */
        REVIEW_RESULT,
        /**
         * 群组信息更新了
         */
        GROUP_INFO_UPDATE,
        /**
         * 群组信息更新了
         */
        GROUP_UPDATE,
    }

    public static class User {
        /**
         * 昵称
         */
    	@JsonProperty("userNickName")
        String nickName;
        /**
         *
         */
    	@JsonProperty("userId")
        String userId;
    	@JsonProperty("roomId")
        String groupId;

        /**
         * @param userId   人员id
         * @param nickName 人员昵称
         */
        public User(String userId, String nickName) {
            this.nickName = nickName;
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRoomId() {
            return groupId;
        }

        public void setRoomId(String roomId) {
            this.groupId = roomId;
        }
    }

    
}
