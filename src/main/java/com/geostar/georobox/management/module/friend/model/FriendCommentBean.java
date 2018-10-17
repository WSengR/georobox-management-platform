package com.geostar.georobox.management.module.friend.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "RB_FRIEND_COMMENT")
public class FriendCommentBean {
    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(generator = "UUID")
    private String commentId;

    @Column(name = "ITEM_ID")
    private String itemId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "REPLY_ID")
    private String replyId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATETIME")
    private String createtime;

    @Column(name = "DATETIME")
    private Date datetime;

    /**
     * @return COMMENT_ID
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    /**
     * @return REPLY_ID
     */
    public String getReplyId() {
        return replyId;
    }

    /**
     * @param replyId
     */
    public void setReplyId(String replyId) {
        this.replyId = replyId;
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
}