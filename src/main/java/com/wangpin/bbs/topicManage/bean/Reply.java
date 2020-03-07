package com.wangpin.bbs.topicManage.bean;

import java.util.Date;

public class Reply {
    /**
     * 回复id
     */
    private Integer id;

    /**
     * 回复人id
     */
    private Integer userId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 帖子id
     */
    private Integer topicId;

    /**
     * 被回复人id
     */
    private Integer toUserId;

    /**
     * 回复时间
     */
    private Date time;

    /**
     * 回复人姓名
     */
    private String userName;

    /**
     * 被回复人姓名
     */
    private String toUserName;

    /**
     * 回复贴的标题
     */
    private String topicTitle;

    /**
     * 是否采纳
     */
    private Integer accept;

    /**
     * 发帖人头像
     */
    private String userImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", topicId=").append(topicId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", time=").append(time);
        sb.append(", userName=").append(userName);
        sb.append(", toUserName=").append(toUserName);
        sb.append(", topicTitle=").append(topicTitle);
        sb.append(", accept=").append(accept);
        sb.append(", userImg=").append(userImg);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Reply other = (Reply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
                && (this.getToUserId() == null ? other.getToUserId() == null : this.getToUserId().equals(other.getToUserId()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getToUserName() == null ? other.getToUserName() == null : this.getToUserName().equals(other.getToUserName()))
                && (this.getTopicTitle() == null ? other.getTopicTitle() == null : this.getTopicTitle().equals(other.getTopicTitle()))
                && (this.getAccept() == null ? other.getAccept() == null : this.getAccept().equals(other.getAccept()))
                && (this.getUserImg() == null ? other.getUserImg() == null : this.getUserImg().equals(other.getUserImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getToUserId() == null) ? 0 : getToUserId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getToUserName() == null) ? 0 : getToUserName().hashCode());
        result = prime * result + ((getTopicTitle() == null) ? 0 : getTopicTitle().hashCode());
        result = prime * result + ((getAccept() == null) ? 0 : getAccept().hashCode());
        result = prime * result + ((getUserImg() == null) ? 0 : getUserImg().hashCode());
        return result;
    }
}