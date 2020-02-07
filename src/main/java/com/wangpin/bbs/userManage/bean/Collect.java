package com.wangpin.bbs.userManage.bean;

import java.util.Date;

public class Collect {
    private Integer id;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 帖子id
    */
    private Integer topicId;

    /**
    * 收藏时间
    */
    private Date collectTime;

    /**
    * 帖子标题
    */
    private String topicTitle;

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

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", topicId=").append(topicId);
        sb.append(", collectTime=").append(collectTime);
        sb.append(", topicTitle=").append(topicTitle);
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
        Collect other = (Collect) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getCollectTime() == null ? other.getCollectTime() == null : this.getCollectTime().equals(other.getCollectTime()))
            && (this.getTopicTitle() == null ? other.getTopicTitle() == null : this.getTopicTitle().equals(other.getTopicTitle()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getCollectTime() == null) ? 0 : getCollectTime().hashCode());
        result = prime * result + ((getTopicTitle() == null) ? 0 : getTopicTitle().hashCode());
        return result;
    }
}