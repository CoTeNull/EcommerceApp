package cn.cote.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DealExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DealExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andDealIdIsNull() {
            addCriterion("deal_id is null");
            return (Criteria) this;
        }

        public Criteria andDealIdIsNotNull() {
            addCriterion("deal_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealIdEqualTo(Integer value) {
            addCriterion("deal_id =", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdNotEqualTo(Integer value) {
            addCriterion("deal_id <>", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdGreaterThan(Integer value) {
            addCriterion("deal_id >", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal_id >=", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdLessThan(Integer value) {
            addCriterion("deal_id <", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdLessThanOrEqualTo(Integer value) {
            addCriterion("deal_id <=", value, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdIn(List<Integer> values) {
            addCriterion("deal_id in", values, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdNotIn(List<Integer> values) {
            addCriterion("deal_id not in", values, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdBetween(Integer value1, Integer value2) {
            addCriterion("deal_id between", value1, value2, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deal_id not between", value1, value2, "dealId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdIsNull() {
            addCriterion("deal_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDealUserIdIsNotNull() {
            addCriterion("deal_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealUserIdEqualTo(Integer value) {
            addCriterion("deal_user_id =", value, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdNotEqualTo(Integer value) {
            addCriterion("deal_user_id <>", value, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdGreaterThan(Integer value) {
            addCriterion("deal_user_id >", value, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal_user_id >=", value, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdLessThan(Integer value) {
            addCriterion("deal_user_id <", value, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("deal_user_id <=", value, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdIn(List<Integer> values) {
            addCriterion("deal_user_id in", values, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdNotIn(List<Integer> values) {
            addCriterion("deal_user_id not in", values, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdBetween(Integer value1, Integer value2) {
            addCriterion("deal_user_id between", value1, value2, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deal_user_id not between", value1, value2, "dealUserId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdIsNull() {
            addCriterion("deal_shop_id is null");
            return (Criteria) this;
        }

        public Criteria andDealShopIdIsNotNull() {
            addCriterion("deal_shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealShopIdEqualTo(Integer value) {
            addCriterion("deal_shop_id =", value, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdNotEqualTo(Integer value) {
            addCriterion("deal_shop_id <>", value, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdGreaterThan(Integer value) {
            addCriterion("deal_shop_id >", value, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal_shop_id >=", value, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdLessThan(Integer value) {
            addCriterion("deal_shop_id <", value, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("deal_shop_id <=", value, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdIn(List<Integer> values) {
            addCriterion("deal_shop_id in", values, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdNotIn(List<Integer> values) {
            addCriterion("deal_shop_id not in", values, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdBetween(Integer value1, Integer value2) {
            addCriterion("deal_shop_id between", value1, value2, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deal_shop_id not between", value1, value2, "dealShopId");
            return (Criteria) this;
        }

        public Criteria andDealTimeIsNull() {
            addCriterion("deal_time is null");
            return (Criteria) this;
        }

        public Criteria andDealTimeIsNotNull() {
            addCriterion("deal_time is not null");
            return (Criteria) this;
        }

        public Criteria andDealTimeEqualTo(Date value) {
            addCriterion("deal_time =", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotEqualTo(Date value) {
            addCriterion("deal_time <>", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeGreaterThan(Date value) {
            addCriterion("deal_time >", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deal_time >=", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeLessThan(Date value) {
            addCriterion("deal_time <", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeLessThanOrEqualTo(Date value) {
            addCriterion("deal_time <=", value, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeIn(List<Date> values) {
            addCriterion("deal_time in", values, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotIn(List<Date> values) {
            addCriterion("deal_time not in", values, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeBetween(Date value1, Date value2) {
            addCriterion("deal_time between", value1, value2, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealTimeNotBetween(Date value1, Date value2) {
            addCriterion("deal_time not between", value1, value2, "dealTime");
            return (Criteria) this;
        }

        public Criteria andDealCodeIsNull() {
            addCriterion("deal_code is null");
            return (Criteria) this;
        }

        public Criteria andDealCodeIsNotNull() {
            addCriterion("deal_code is not null");
            return (Criteria) this;
        }

        public Criteria andDealCodeEqualTo(String value) {
            addCriterion("deal_code =", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotEqualTo(String value) {
            addCriterion("deal_code <>", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeGreaterThan(String value) {
            addCriterion("deal_code >", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeGreaterThanOrEqualTo(String value) {
            addCriterion("deal_code >=", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeLessThan(String value) {
            addCriterion("deal_code <", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeLessThanOrEqualTo(String value) {
            addCriterion("deal_code <=", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeLike(String value) {
            addCriterion("deal_code like", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotLike(String value) {
            addCriterion("deal_code not like", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeIn(List<String> values) {
            addCriterion("deal_code in", values, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotIn(List<String> values) {
            addCriterion("deal_code not in", values, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeBetween(String value1, String value2) {
            addCriterion("deal_code between", value1, value2, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotBetween(String value1, String value2) {
            addCriterion("deal_code not between", value1, value2, "dealCode");
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