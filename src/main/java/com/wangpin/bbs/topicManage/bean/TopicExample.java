package com.wangpin.bbs.topicManage.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TopicExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIsNull() {
            addCriterion("owner_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIsNotNull() {
            addCriterion("owner_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdEqualTo(Integer value) {
            addCriterion("owner_id =", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotEqualTo(Integer value) {
            addCriterion("owner_id <>", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThan(Integer value) {
            addCriterion("owner_id >", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("owner_id >=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThan(Integer value) {
            addCriterion("owner_id <", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("owner_id <=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIn(List<Integer> values) {
            addCriterion("owner_id in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotIn(List<Integer> values) {
            addCriterion("owner_id not in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdBetween(Integer value1, Integer value2) {
            addCriterion("owner_id between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("owner_id not between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andReplyNumIsNull() {
            addCriterion("reply_num is null");
            return (Criteria) this;
        }

        public Criteria andReplyNumIsNotNull() {
            addCriterion("reply_num is not null");
            return (Criteria) this;
        }

        public Criteria andReplyNumEqualTo(Integer value) {
            addCriterion("reply_num =", value, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumNotEqualTo(Integer value) {
            addCriterion("reply_num <>", value, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumGreaterThan(Integer value) {
            addCriterion("reply_num >", value, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_num >=", value, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumLessThan(Integer value) {
            addCriterion("reply_num <", value, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumLessThanOrEqualTo(Integer value) {
            addCriterion("reply_num <=", value, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumIn(List<Integer> values) {
            addCriterion("reply_num in", values, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumNotIn(List<Integer> values) {
            addCriterion("reply_num not in", values, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumBetween(Integer value1, Integer value2) {
            addCriterion("reply_num between", value1, value2, "replyNum");
            return (Criteria) this;
        }

        public Criteria andReplyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_num not between", value1, value2, "replyNum");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeIsNull() {
            addCriterion("last_reply_time is null");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeIsNotNull() {
            addCriterion("last_reply_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeEqualTo(Date value) {
            addCriterion("last_reply_time =", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeNotEqualTo(Date value) {
            addCriterion("last_reply_time <>", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeGreaterThan(Date value) {
            addCriterion("last_reply_time >", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_reply_time >=", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeLessThan(Date value) {
            addCriterion("last_reply_time <", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_reply_time <=", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeIn(List<Date> values) {
            addCriterion("last_reply_time in", values, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeNotIn(List<Date> values) {
            addCriterion("last_reply_time not in", values, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeBetween(Date value1, Date value2) {
            addCriterion("last_reply_time between", value1, value2, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_reply_time not between", value1, value2, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNull() {
            addCriterion("module_id is null");
            return (Criteria) this;
        }

        public Criteria andModuleIdIsNotNull() {
            addCriterion("module_id is not null");
            return (Criteria) this;
        }

        public Criteria andModuleIdEqualTo(Integer value) {
            addCriterion("module_id =", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotEqualTo(Integer value) {
            addCriterion("module_id <>", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThan(Integer value) {
            addCriterion("module_id >", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("module_id >=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThan(Integer value) {
            addCriterion("module_id <", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("module_id <=", value, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdIn(List<Integer> values) {
            addCriterion("module_id in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotIn(List<Integer> values) {
            addCriterion("module_id not in", values, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("module_id between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("module_id not between", value1, value2, "moduleId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdIsNull() {
            addCriterion("last_reply_id is null");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdIsNotNull() {
            addCriterion("last_reply_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdEqualTo(Integer value) {
            addCriterion("last_reply_id =", value, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdNotEqualTo(Integer value) {
            addCriterion("last_reply_id <>", value, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdGreaterThan(Integer value) {
            addCriterion("last_reply_id >", value, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_reply_id >=", value, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdLessThan(Integer value) {
            addCriterion("last_reply_id <", value, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_reply_id <=", value, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdIn(List<Integer> values) {
            addCriterion("last_reply_id in", values, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdNotIn(List<Integer> values) {
            addCriterion("last_reply_id not in", values, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdBetween(Integer value1, Integer value2) {
            addCriterion("last_reply_id between", value1, value2, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andLastReplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_reply_id not between", value1, value2, "lastReplyId");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNull() {
            addCriterion("owner_name is null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNotNull() {
            addCriterion("owner_name is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameEqualTo(String value) {
            addCriterion("owner_name =", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotEqualTo(String value) {
            addCriterion("owner_name <>", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThan(String value) {
            addCriterion("owner_name >", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("owner_name >=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThan(String value) {
            addCriterion("owner_name <", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("owner_name <=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLike(String value) {
            addCriterion("owner_name like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotLike(String value) {
            addCriterion("owner_name not like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIn(List<String> values) {
            addCriterion("owner_name in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotIn(List<String> values) {
            addCriterion("owner_name not in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameBetween(String value1, String value2) {
            addCriterion("owner_name between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotBetween(String value1, String value2) {
            addCriterion("owner_name not between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameIsNull() {
            addCriterion("last_reply_name is null");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameIsNotNull() {
            addCriterion("last_reply_name is not null");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameEqualTo(String value) {
            addCriterion("last_reply_name =", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameNotEqualTo(String value) {
            addCriterion("last_reply_name <>", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameGreaterThan(String value) {
            addCriterion("last_reply_name >", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameGreaterThanOrEqualTo(String value) {
            addCriterion("last_reply_name >=", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameLessThan(String value) {
            addCriterion("last_reply_name <", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameLessThanOrEqualTo(String value) {
            addCriterion("last_reply_name <=", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameLike(String value) {
            addCriterion("last_reply_name like", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameNotLike(String value) {
            addCriterion("last_reply_name not like", value, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameIn(List<String> values) {
            addCriterion("last_reply_name in", values, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameNotIn(List<String> values) {
            addCriterion("last_reply_name not in", values, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameBetween(String value1, String value2) {
            addCriterion("last_reply_name between", value1, value2, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andLastReplyNameNotBetween(String value1, String value2) {
            addCriterion("last_reply_name not between", value1, value2, "lastReplyName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNull() {
            addCriterion("module_name is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("module_name is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("module_name =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("module_name <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("module_name >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("module_name >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("module_name <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("module_name <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("module_name like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("module_name not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("module_name in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("module_name not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("module_name between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("module_name not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andTopicStateIsNull() {
            addCriterion("topic_state is null");
            return (Criteria) this;
        }

        public Criteria andTopicStateIsNotNull() {
            addCriterion("topic_state is not null");
            return (Criteria) this;
        }

        public Criteria andTopicStateEqualTo(String value) {
            addCriterion("topic_state =", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateNotEqualTo(String value) {
            addCriterion("topic_state <>", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateGreaterThan(String value) {
            addCriterion("topic_state >", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateGreaterThanOrEqualTo(String value) {
            addCriterion("topic_state >=", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateLessThan(String value) {
            addCriterion("topic_state <", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateLessThanOrEqualTo(String value) {
            addCriterion("topic_state <=", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateLike(String value) {
            addCriterion("topic_state like", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateNotLike(String value) {
            addCriterion("topic_state not like", value, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateIn(List<String> values) {
            addCriterion("topic_state in", values, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateNotIn(List<String> values) {
            addCriterion("topic_state not in", values, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateBetween(String value1, String value2) {
            addCriterion("topic_state between", value1, value2, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopicStateNotBetween(String value1, String value2) {
            addCriterion("topic_state not between", value1, value2, "topicState");
            return (Criteria) this;
        }

        public Criteria andTopIsNull() {
            addCriterion("`top` is null");
            return (Criteria) this;
        }

        public Criteria andTopIsNotNull() {
            addCriterion("`top` is not null");
            return (Criteria) this;
        }

        public Criteria andTopEqualTo(Integer value) {
            addCriterion("`top` =", value, "top");
            return (Criteria) this;
        }

        public Criteria andTopNotEqualTo(Integer value) {
            addCriterion("`top` <>", value, "top");
            return (Criteria) this;
        }

        public Criteria andTopGreaterThan(Integer value) {
            addCriterion("`top` >", value, "top");
            return (Criteria) this;
        }

        public Criteria andTopGreaterThanOrEqualTo(Integer value) {
            addCriterion("`top` >=", value, "top");
            return (Criteria) this;
        }

        public Criteria andTopLessThan(Integer value) {
            addCriterion("`top` <", value, "top");
            return (Criteria) this;
        }

        public Criteria andTopLessThanOrEqualTo(Integer value) {
            addCriterion("`top` <=", value, "top");
            return (Criteria) this;
        }

        public Criteria andTopIn(List<Integer> values) {
            addCriterion("`top` in", values, "top");
            return (Criteria) this;
        }

        public Criteria andTopNotIn(List<Integer> values) {
            addCriterion("`top` not in", values, "top");
            return (Criteria) this;
        }

        public Criteria andTopBetween(Integer value1, Integer value2) {
            addCriterion("`top` between", value1, value2, "top");
            return (Criteria) this;
        }

        public Criteria andTopNotBetween(Integer value1, Integer value2) {
            addCriterion("`top` not between", value1, value2, "top");
            return (Criteria) this;
        }

        public Criteria andEndIsNull() {
            addCriterion("`end` is null");
            return (Criteria) this;
        }

        public Criteria andEndIsNotNull() {
            addCriterion("`end` is not null");
            return (Criteria) this;
        }

        public Criteria andEndEqualTo(Integer value) {
            addCriterion("`end` =", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotEqualTo(Integer value) {
            addCriterion("`end` <>", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThan(Integer value) {
            addCriterion("`end` >", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("`end` >=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThan(Integer value) {
            addCriterion("`end` <", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThanOrEqualTo(Integer value) {
            addCriterion("`end` <=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndIn(List<Integer> values) {
            addCriterion("`end` in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotIn(List<Integer> values) {
            addCriterion("`end` not in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndBetween(Integer value1, Integer value2) {
            addCriterion("`end` between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotBetween(Integer value1, Integer value2) {
            addCriterion("`end` not between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andEssenceIsNull() {
            addCriterion("essence is null");
            return (Criteria) this;
        }

        public Criteria andEssenceIsNotNull() {
            addCriterion("essence is not null");
            return (Criteria) this;
        }

        public Criteria andEssenceEqualTo(Integer value) {
            addCriterion("essence =", value, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceNotEqualTo(Integer value) {
            addCriterion("essence <>", value, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceGreaterThan(Integer value) {
            addCriterion("essence >", value, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("essence >=", value, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceLessThan(Integer value) {
            addCriterion("essence <", value, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceLessThanOrEqualTo(Integer value) {
            addCriterion("essence <=", value, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceIn(List<Integer> values) {
            addCriterion("essence in", values, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceNotIn(List<Integer> values) {
            addCriterion("essence not in", values, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceBetween(Integer value1, Integer value2) {
            addCriterion("essence between", value1, value2, "essence");
            return (Criteria) this;
        }

        public Criteria andEssenceNotBetween(Integer value1, Integer value2) {
            addCriterion("essence not between", value1, value2, "essence");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNull() {
            addCriterion("read_num is null");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNotNull() {
            addCriterion("read_num is not null");
            return (Criteria) this;
        }

        public Criteria andReadNumEqualTo(Integer value) {
            addCriterion("read_num =", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotEqualTo(Integer value) {
            addCriterion("read_num <>", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThan(Integer value) {
            addCriterion("read_num >", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_num >=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThan(Integer value) {
            addCriterion("read_num <", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThanOrEqualTo(Integer value) {
            addCriterion("read_num <=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumIn(List<Integer> values) {
            addCriterion("read_num in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotIn(List<Integer> values) {
            addCriterion("read_num not in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumBetween(Integer value1, Integer value2) {
            addCriterion("read_num between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("read_num not between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andGoldIsNull() {
            addCriterion("gold is null");
            return (Criteria) this;
        }

        public Criteria andGoldIsNotNull() {
            addCriterion("gold is not null");
            return (Criteria) this;
        }

        public Criteria andGoldEqualTo(Integer value) {
            addCriterion("gold =", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotEqualTo(Integer value) {
            addCriterion("gold <>", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldGreaterThan(Integer value) {
            addCriterion("gold >", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("gold >=", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldLessThan(Integer value) {
            addCriterion("gold <", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldLessThanOrEqualTo(Integer value) {
            addCriterion("gold <=", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldIn(List<Integer> values) {
            addCriterion("gold in", values, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotIn(List<Integer> values) {
            addCriterion("gold not in", values, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldBetween(Integer value1, Integer value2) {
            addCriterion("gold between", value1, value2, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("gold not between", value1, value2, "gold");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}