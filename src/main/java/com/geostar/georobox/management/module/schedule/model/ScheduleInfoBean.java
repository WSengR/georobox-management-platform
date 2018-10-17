package com.geostar.georobox.management.module.schedule.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "RB_RC_INFO")
public class ScheduleInfoBean {
    @Id
    @Column(name = "MESSAGE_ID")
    private String messageId;

    @Column(name = "IS_PUBLIC")
    private Short isPublic;

    @Column(name = "TYPE")
    private Short type;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "START_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    @Column(name = "END_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "REMIND")
    private Short remind;

    @Column(name = "REMIND_WAY")
    private Short remindWay;

    @Column(name = "REMIND_AHEAD_TIME")
    private Short remindAheadTime;

    @Column(name = "REMIND_REPEAT_INTERVAL")
    private Short remindRepeatInterval;

    @Column(name = "INFO")
    private String info;

    @Column(name = "CREATE_ID")
    private String createId;

    @Column(name = "CREATE_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @Column(name = "REMIND_REPEAT")
    private Short remindRepeat;

    @Column(name = "REMIND_STATE")
    private Short remindState;

    @Column(name = "EVENT_ID")
    private String eventId;

    @Column(name = "IMPORTANT")
    private Short important;

    @Column(name = "CREATE_NAME")
    private String createName;

    @Column(name = "OPERATION_STATE")
    private Short operationState;

    @Column(name = "ALL_DAY")
    private Short allDay;

    @Column(name = "REMIND_AHEAD_TIME_CODE")
    private String remindAheadTimeCode;

    @Column(name = "START_TIME_BUCKET")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTimeBucket;

    @Column(name = "END_TIME_BUCKET")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTimeBucket;

    @Column(name = "CHANGE_TIME")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date changeTime;

    @Column(name = "CHANGE_TIME_POINT")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date changeTimePoint;
    
	/**
     * @return MESSAGE_ID
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return IS_PUBLIC
     */
    public Short getIsPublic() {
        return isPublic;
    }

    /**
     * @param isPublic
     */
    public void setIsPublic(Short isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * @return TYPE
     */
    public Short getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return START_TIME
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return END_TIME
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return REMIND
     */
    public Short getRemind() {
        return remind;
    }

    /**
     * @param remind
     */
    public void setRemind(Short remind) {
        this.remind = remind;
    }

    /**
     * @return REMIND_WAY
     */
    public Short getRemindWay() {
        return remindWay;
    }

    /**
     * @param remindWay
     */
    public void setRemindWay(Short remindWay) {
        this.remindWay = remindWay;
    }

    /**
     * @return REMIND_AHEAD_TIME
     */
    public Short getRemindAheadTime() {
        return remindAheadTime;
    }

    /**
     * @param remindAheadTime
     */
    public void setRemindAheadTime(Short remindAheadTime) {
        this.remindAheadTime = remindAheadTime;
    }

    /**
     * @return REMIND_REPEAT_INTERVAL
     */
    public Short getRemindRepeatInterval() {
        return remindRepeatInterval;
    }

    /**
     * @param remindRepeatInterval
     */
    public void setRemindRepeatInterval(Short remindRepeatInterval) {
        this.remindRepeatInterval = remindRepeatInterval;
    }

    /**
     * @return INFO
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return CREATE_ID
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * @param createId
     */
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return REMIND_REPEAT
     */
    public Short getRemindRepeat() {
        return remindRepeat;
    }

    /**
     * @param remindRepeat
     */
    public void setRemindRepeat(Short remindRepeat) {
        this.remindRepeat = remindRepeat;
    }

    /**
     * @return REMIND_STATE
     */
    public Short getRemindState() {
        return remindState;
    }

    /**
     * @param remindState
     */
    public void setRemindState(Short remindState) {
        this.remindState = remindState;
    }

    /**
     * @return EVENT_ID
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param eventId
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * @return IMPORTANT
     */
    public Short getImportant() {
        return important;
    }

    /**
     * @param important
     */
    public void setImportant(Short important) {
        this.important = important;
    }

    /**
     * @return CREATE_NAME
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * @param createName
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * @return OPERATION_STATE
     */
    public Short getOperationState() {
        return operationState;
    }

    /**
     * @param operationState
     */
    public void setOperationState(Short operationState) {
        this.operationState = operationState;
    }

    /**
     * @return ALL_DAY
     */
    public Short getAllDay() {
        return allDay;
    }

    /**
     * @param allDay
     */
    public void setAllDay(Short allDay) {
        this.allDay = allDay;
    }

    /**
     * @return REMIND_AHEAD_TIME_CODE
     */
    public String getRemindAheadTimeCode() {
        return remindAheadTimeCode;
    }

    /**
     * @param remindAheadTimeCode
     */
    public void setRemindAheadTimeCode(String remindAheadTimeCode) {
        this.remindAheadTimeCode = remindAheadTimeCode;
    }

    /**
     * @return START_TIME_BUCKET
     */
    public Date getStartTimeBucket() {
        return startTimeBucket;
    }

    /**
     * @param startTimeBucket
     */
    public void setStartTimeBucket(Date startTimeBucket) {
        this.startTimeBucket = startTimeBucket;
    }

    /**
     * @return END_TIME_BUCKET
     */
    public Date getEndTimeBucket() {
        return endTimeBucket;
    }

    /**
     * @param endTimeBucket
     */
    public void setEndTimeBucket(Date endTimeBucket) {
        this.endTimeBucket = endTimeBucket;
    }

    /**
     * @return CHANGE_TIME
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * @param changeTime
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * @return CHANGE_TIME_POINT
     */
    public Date getChangeTimePoint() {
        return changeTimePoint;
    }

    /**
     * @param changeTimePoint
     */
    public void setChangeTimePoint(Date changeTimePoint) {
        this.changeTimePoint = changeTimePoint;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleInfoBean [messageId=");
		builder.append(messageId);
		builder.append(", isPublic=");
		builder.append(isPublic);
		builder.append(", type=");
		builder.append(type);
		builder.append(", title=");
		builder.append(title);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", address=");
		builder.append(address);
		builder.append(", remind=");
		builder.append(remind);
		builder.append(", remindWay=");
		builder.append(remindWay);
		builder.append(", remindAheadTime=");
		builder.append(remindAheadTime);
		builder.append(", remindRepeatInterval=");
		builder.append(remindRepeatInterval);
		builder.append(", info=");
		builder.append(info);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", remindRepeat=");
		builder.append(remindRepeat);
		builder.append(", remindState=");
		builder.append(remindState);
		builder.append(", eventId=");
		builder.append(eventId);
		builder.append(", important=");
		builder.append(important);
		builder.append(", createName=");
		builder.append(createName);
		builder.append(", operationState=");
		builder.append(operationState);
		builder.append(", allDay=");
		builder.append(allDay);
		builder.append(", remindAheadTimeCode=");
		builder.append(remindAheadTimeCode);
		builder.append(", startTimeBucket=");
		builder.append(startTimeBucket);
		builder.append(", endTimeBucket=");
		builder.append(endTimeBucket);
		builder.append(", changeTime=");
		builder.append(changeTime);
		builder.append(", changeTimePoint=");
		builder.append(changeTimePoint);
		builder.append(", getMessageId()=");
		builder.append(getMessageId());
		builder.append(", getIsPublic()=");
		builder.append(getIsPublic());
		builder.append(", getType()=");
		builder.append(getType());
		builder.append(", getTitle()=");
		builder.append(getTitle());
		builder.append(", getStartTime()=");
		builder.append(getStartTime());
		builder.append(", getEndTime()=");
		builder.append(getEndTime());
		builder.append(", getAddress()=");
		builder.append(getAddress());
		builder.append(", getRemind()=");
		builder.append(getRemind());
		builder.append(", getRemindWay()=");
		builder.append(getRemindWay());
		builder.append(", getRemindAheadTime()=");
		builder.append(getRemindAheadTime());
		builder.append(", getRemindRepeatInterval()=");
		builder.append(getRemindRepeatInterval());
		builder.append(", getInfo()=");
		builder.append(getInfo());
		builder.append(", getCreateId()=");
		builder.append(getCreateId());
		builder.append(", getCreateTime()=");
		builder.append(getCreateTime());
		builder.append(", getRemindRepeat()=");
		builder.append(getRemindRepeat());
		builder.append(", getRemindState()=");
		builder.append(getRemindState());
		builder.append(", getEventId()=");
		builder.append(getEventId());
		builder.append(", getImportant()=");
		builder.append(getImportant());
		builder.append(", getCreateName()=");
		builder.append(getCreateName());
		builder.append(", getOperationState()=");
		builder.append(getOperationState());
		builder.append(", getAllDay()=");
		builder.append(getAllDay());
		builder.append(", getRemindAheadTimeCode()=");
		builder.append(getRemindAheadTimeCode());
		builder.append(", getStartTimeBucket()=");
		builder.append(getStartTimeBucket());
		builder.append(", getEndTimeBucket()=");
		builder.append(getEndTimeBucket());
		builder.append(", getChangeTime()=");
		builder.append(getChangeTime());
		builder.append(", getChangeTimePoint()=");
		builder.append(getChangeTimePoint());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
    
    
    
}