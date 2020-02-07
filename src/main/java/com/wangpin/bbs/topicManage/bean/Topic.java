package com.wangpin.bbs.topicManage.bean;

import java.util.Date;

public class Topic {
    /**
     * 帖子id
     */
    private Integer id;

    /**
     * 发帖人id
     */
    private Integer ownerId;

    /**
     * 回复数
     */
    private Integer replyNum;

    /**
     * 最后回复时间
     */
    private Date lastReplyTime;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 帖子主题
     */
    private String title;

    /**
     * 板块id
     */
    private Integer moduleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后回复人id
     */
    private Integer lastReplyId;

    /**
     * 发帖人姓名
     */
    private String ownerName;

    /**
     * 最后回复人姓名
     */
    private String lastReplyName;

    /**
     * 板块名称
     */
    private String moduleName;

    /**
     * 帖子状态（删除）
     */
    private String topicState;

    /**
     * 帖子所属板块
     */
    private String topicModule;

    /**
     * 是否置顶
     */
    private Integer top;

    /**
     * 是否结束
     */
    private Integer end;

    /**
     * 是否为精华帖
     */
    private Integer essence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastReplyId() {
        return lastReplyId;
    }

    public void setLastReplyId(Integer lastReplyId) {
        this.lastReplyId = lastReplyId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLastReplyName() {
        return lastReplyName;
    }

    public void setLastReplyName(String lastReplyName) {
        this.lastReplyName = lastReplyName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTopicState() {
        return topicState;
    }

    public void setTopicState(String topicState) {
        this.topicState = topicState;
    }

    public String getTopicModule() {
        return topicModule;
    }

    public void setTopicModule(String topicModule) {
        this.topicModule = topicModule;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getEssence() {
        return essence;
    }

    public void setEssence(Integer essence) {
        this.essence = essence;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", replyNum=").append(replyNum);
        sb.append(", lastReplyTime=").append(lastReplyTime);
        sb.append(", content=").append(content);
        sb.append(", title=").append(title);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastReplyId=").append(lastReplyId);
        sb.append(", ownerName=").append(ownerName);
        sb.append(", lastReplyName=").append(lastReplyName);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", topicState=").append(topicState);
        sb.append(", topicModule=").append(topicModule);
        sb.append(", top=").append(top);
        sb.append(", end=").append(end);
        sb.append(", essence=").append(essence);
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
        Topic other = (Topic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
                && (this.getReplyNum() == null ? other.getReplyNum() == null : this.getReplyNum().equals(other.getReplyNum()))
                && (this.getLastReplyTime() == null ? other.getLastReplyTime() == null : this.getLastReplyTime().equals(other.getLastReplyTime()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getModuleId() == null ? other.getModuleId() == null : this.getModuleId().equals(other.getModuleId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getLastReplyId() == null ? other.getLastReplyId() == null : this.getLastReplyId().equals(other.getLastReplyId()))
                && (this.getOwnerName() == null ? other.getOwnerName() == null : this.getOwnerName().equals(other.getOwnerName()))
                && (this.getLastReplyName() == null ? other.getLastReplyName() == null : this.getLastReplyName().equals(other.getLastReplyName()))
                && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
                && (this.getTopicState() == null ? other.getTopicState() == null : this.getTopicState().equals(other.getTopicState()))
                && (this.getTopicModule() == null ? other.getTopicModule() == null : this.getTopicModule().equals(other.getTopicModule()))
                && (this.getTop() == null ? other.getTop() == null : this.getTop().equals(other.getTop()))
                && (this.getEnd() == null ? other.getEnd() == null : this.getEnd().equals(other.getEnd()))
                && (this.getEssence() == null ? other.getEssence() == null : this.getEssence().equals(other.getEssence()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getReplyNum() == null) ? 0 : getReplyNum().hashCode());
        result = prime * result + ((getLastReplyTime() == null) ? 0 : getLastReplyTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getModuleId() == null) ? 0 : getModuleId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastReplyId() == null) ? 0 : getLastReplyId().hashCode());
        result = prime * result + ((getOwnerName() == null) ? 0 : getOwnerName().hashCode());
        result = prime * result + ((getLastReplyName() == null) ? 0 : getLastReplyName().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getTopicState() == null) ? 0 : getTopicState().hashCode());
        result = prime * result + ((getTopicModule() == null) ? 0 : getTopicModule().hashCode());
        result = prime * result + ((getTop() == null) ? 0 : getTop().hashCode());
        result = prime * result + ((getEnd() == null) ? 0 : getEnd().hashCode());
        result = prime * result + ((getEssence() == null) ? 0 : getEssence().hashCode());
        return result;
    }
}