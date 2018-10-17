package com.geostar.georobox.management.module.openfire.model.push;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;

/**
 * 群组相关操作的推送消息
 *
 * @author hanlyjiang on 2018/8/20-10:39.
 * @version 1.0
 */
public class GroupPushMsgData {

    private GroupPushMsgData(Builder builder) {
        action = builder.action;
        inviter = builder.inviter;
        invitees = builder.invitees;
        inspector = builder.inspector;
        group = builder.group;
        extraInfo = builder.extraInfo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(GroupPushMsgData copy) {
        Builder builder = new Builder();
        builder.action = copy.getAction();
        builder.inviter = copy.getInviter();
        builder.invitees = copy.getInvitees();
        builder.inspector = copy.getInspector();
        builder.group = copy.getGroup();
        builder.extraInfo = copy.getExtraInfo();
        return builder;
    }


    /**
     * 1，邀请入群消息：包含邀请者，被邀请者，群组信息，推送给管理员和拥有者。
     * 2，入群审核消息：包含邀请者，被邀请者，群组信息，推送给管理员和拥有者。
     * 3，审核通过消息：包含审核者，邀请者，被邀请者，群组信息，推送给其他管理员、拥有者、邀请者、被邀请者。
     * 4，拒绝审核消息：包含审核者，邀请者，被邀请者，群组信息，推送给其他管理员、拥有者、邀请者、被邀请者。
     */
    public enum Action {
    	/**
    	 * 邀请入群
    	 */
        INVITE,
        /**
         * 入群审核
         */
        REVIEW_NEEDED,
        /**
         * 审核结果  ： 其他管理员，审核者，邀请者
         */
        REVIEW_RESULT,
        /**
         * 群组信息更新了
         */
        GROUP_INFO_UPDATE,
        /**
         * 申请发言
         */
        SPEAK_REQUEST,
        /**
         * 申请发言的结果
         */
        SPEAK_REQUEST_RESULT,
        /**
         * 推送给用户
         */
       SPEAK_REVIEW_RESULT
    }

    @JsonProperty("action")
    private Action action;

    /**
     * 邀请人
     */
    @JsonProperty("inviter")
    private ImUserNikeNameBean inviter;

    /**
     * 被邀请人
     */
    @JsonProperty("invitees")
	private List<ImUserNikeNameBean> invitees;

    /**
     * 审核者
     */
    @JsonProperty("inspector")
    private ImUserNikeNameBean inspector;
    
    private boolean  inspectorResult;
    
    private String inspectorMark;

    private String id;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
     * 邀请到某个群的群信息
     */
    @JsonProperty("group")
    private Group group;

    public boolean isInspectorResult() {
		return inspectorResult;
	}

	public void setInspectorResult(boolean inspectorResult) {
		this.inspectorResult = inspectorResult;
	}

	public String getInspectorMark() {
		return inspectorMark;
	}

	public void setInspectorMark(String inspectorMark) {
		this.inspectorMark = inspectorMark;
	}

	/**
     * 附加信息
     */
    @JsonProperty("extraInfo")
    private String extraInfo;

    public GroupPushMsgData() {
    }


    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public ImUserNikeNameBean getInviter() {
        return inviter;
    }

    public void setInviter(ImUserNikeNameBean inviter) {
        this.inviter = inviter;
    }

    public List<ImUserNikeNameBean> getInvitees() {
        return invitees;
    }

    public void setInvitees(List<ImUserNikeNameBean> invitees) {
        this.invitees = invitees;
    }

    public ImUserNikeNameBean getInspector() {
        return inspector;
    }

    public void setInspector(ImUserNikeNameBean inspector) {
        this.inspector = inspector;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public static final class Builder {
        private Action action;
        private ImUserNikeNameBean inviter;
        private List<ImUserNikeNameBean> invitees;
        private ImUserNikeNameBean inspector;
        private Group group;
        private String extraInfo;

        private Builder() {
        }

        public Builder action(Action action) {
            this.action = action;
            return this;
        }

        public Builder inviter(ImUserNikeNameBean inviter) {
            this.inviter = inviter;
            return this;
        }

        public Builder invitees(List<ImUserNikeNameBean> invitees) {
            this.invitees = invitees;
            return this;
        }

        public Builder inspector(ImUserNikeNameBean inspector) {
            this.inspector = inspector;
            return this;
        }

        public Builder group(Group group) {
            this.group = group;
            return this;
        }

        public Builder extraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
            return this;
        }

        public GroupPushMsgData build() {
            return new GroupPushMsgData(this);
        }
    }
}
