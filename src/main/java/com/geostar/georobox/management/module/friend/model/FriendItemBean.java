package com.geostar.georobox.management.module.friend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Table(name = "RB_FRIEND_ITEM")
public class FriendItemBean {
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(generator = "UUID")
    private String itemId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ITEM_TYPE")
    private String itemType;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PHOTO_URL")
    private String photoUrl;

    @Column(name = "PHOTO_TEMP_URL")
    private String photoTempUrl;

    @Column(name = "VIDEO_IMG_URL")
    private String videoImgUrl;

    @Column(name = "VIDEO_URL")
    private String videoUrl;

    @Column(name = "LOCTION")
    private String loction;

    @Column(name = "CREATETIME")
    private String createtime;

    @Column(name = "DATETIME")
    private Date datetime;
    
	private List<FriendFavorBean> favorters;
	private List<FriendCommentBean> comments;


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

    /**
     * @return ITEM_TYPE
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return PHOTO_URL
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return PHOTO_TEMP_URL
     */
    public String getPhotoTempUrl() {
        return photoTempUrl;
    }

    /**
     * @param photoTempUrl
     */
    public void setPhotoTempUrl(String photoTempUrl) {
        this.photoTempUrl = photoTempUrl;
    }

    /**
     * @return VIDEO_IMG_URL
     */
    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    /**
     * @param videoImgUrl
     */
    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    /**
     * @return VIDEO_URL
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return LOCTION
     */
    public String getLoction() {
        return loction;
    }

    /**
     * @param loction
     */
    public void setLoction(String loction) {
        this.loction = loction;
    }

    /**
     * @return CREATETIME
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * @return DATETIME
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

	public List<FriendFavorBean> getFavorters() {
		return favorters;
	}

	public void setFavorters(List<FriendFavorBean> favorters) {
		this.favorters = favorters;
	}

	public List<FriendCommentBean> getComments() {
		return comments;
	}

	public void setComments(List<FriendCommentBean> comments) {
		this.comments = comments;
	}
    
}