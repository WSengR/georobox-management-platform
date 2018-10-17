package com.geostar.georobox.management.module.friend.model;

import javax.persistence.*;

@Table(name = "RB_FRIEND_FAVORTER")
public class FriendFavorBean {
    @Id
    @Column(name = "FAVORTER_ID")
    @GeneratedValue(generator = "UUID")
    private String favorterId;

    @Column(name = "ITEM_ID")
    private String itemId;

    @Column(name = "USER_ID")
    private String userId;

    /**
     * @return FAVORTER_ID
     */
    public String getFavorterId() {
        return favorterId;
    }

    /**
     * @param favorterId
     */
    public void setFavorterId(String favorterId) {
        this.favorterId = favorterId;
    }

    /**
     * @return ITEM_ID
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FriendFavorBean [favorterId=");
		builder.append(favorterId);
		builder.append(", itemId=");
		builder.append(itemId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
}