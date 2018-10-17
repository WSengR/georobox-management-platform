package com.geostar.georobox.management.module.openfire.model.push;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geostar.georobox.management.module.openfire.model.ImUserNikeNameBean;

/**
 * Create on 2018/9/4
 *
 * @author:胡思龙 Description:
 */
public class GroupUpdateMsgData extends BaseGroupMsgData {

    @JsonProperty("updateUser")
    private List<ImUserNikeNameBean> updateUser;

    @JsonProperty("updateGroup")
    private Group updateGroup;

    @JsonProperty("updateAction")
    private UpdateAction action;


    public enum UpdateAction{

        REMOVE_MEMBER,

        EXIT_GROUP,

        UPDATE_ROOM_NAME,

    }

    public UpdateAction getUpdateAction() {
        return action;
    }

    public void setUpdateAction(UpdateAction action) {
        this.action = action;
    }

    public List<ImUserNikeNameBean> getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(List<ImUserNikeNameBean> updateUser) {
        this.updateUser = updateUser;
    }

    public Group getUpdateGroup() {
        return updateGroup;
    }

    public void setUpdateGroup(Group updateGroup) {
        this.updateGroup = updateGroup;
    }
}
