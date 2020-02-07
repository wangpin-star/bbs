package com.wangpin.bbs.topicManage.bean;

import java.util.Date;
import java.util.List;

public class ReplyVo {
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
     * 父回复
     */
    private Integer fatherId;

    /**
     * 帖子id
     */
    private Integer topicId;

    /**
     * 回复人id
     */
    private Integer toUserId;

    /**
     * 回复时间
     */
    private Date time;

    @Override
    public String toString() {
        return "ReplyVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", fatherId=" + fatherId +
                ", topicId=" + topicId +
                ", toUserId=" + toUserId +
                ", time=" + time +
                ", children=" + children +
                '}';
    }

    /**
     *
     */
    private List<ReplyVo> children;

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

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
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

    public List<ReplyVo> getChildren() {
        return children;
    }

    public void setChildren(List<ReplyVo> children) {
        this.children = children;
    }
}
