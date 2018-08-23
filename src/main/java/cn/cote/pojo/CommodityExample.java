package cn.cote.pojo;

import java.util.ArrayList;
import java.util.List;

public class CommodityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;


    protected int startRow = -1;

    protected int endRow = -1;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public CommodityExample() {
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

        public Criteria andCommodityIdIsNull() {
            addCriterion("commodity_id is null");
            return (Criteria) this;
        }

        public Criteria andCommodityIdIsNotNull() {
            addCriterion("commodity_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityIdEqualTo(Integer value) {
            addCriterion("commodity_id =", value, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdNotEqualTo(Integer value) {
            addCriterion("commodity_id <>", value, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdGreaterThan(Integer value) {
            addCriterion("commodity_id >", value, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("commodity_id >=", value, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdLessThan(Integer value) {
            addCriterion("commodity_id <", value, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdLessThanOrEqualTo(Integer value) {
            addCriterion("commodity_id <=", value, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdIn(List<Integer> values) {
            addCriterion("commodity_id in", values, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdNotIn(List<Integer> values) {
            addCriterion("commodity_id not in", values, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdBetween(Integer value1, Integer value2) {
            addCriterion("commodity_id between", value1, value2, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("commodity_id not between", value1, value2, "commodityId");
            return (Criteria) this;
        }

        public Criteria andCommodityImgIsNull() {
            addCriterion("commodity_img is null");
            return (Criteria) this;
        }

        public Criteria andCommodityImgIsNotNull() {
            addCriterion("commodity_img is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityImgEqualTo(String value) {
            addCriterion("commodity_img =", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgNotEqualTo(String value) {
            addCriterion("commodity_img <>", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgGreaterThan(String value) {
            addCriterion("commodity_img >", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_img >=", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgLessThan(String value) {
            addCriterion("commodity_img <", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgLessThanOrEqualTo(String value) {
            addCriterion("commodity_img <=", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgLike(String value) {
            addCriterion("commodity_img like", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgNotLike(String value) {
            addCriterion("commodity_img not like", value, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgIn(List<String> values) {
            addCriterion("commodity_img in", values, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgNotIn(List<String> values) {
            addCriterion("commodity_img not in", values, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgBetween(String value1, String value2) {
            addCriterion("commodity_img between", value1, value2, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityImgNotBetween(String value1, String value2) {
            addCriterion("commodity_img not between", value1, value2, "commodityImg");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIsNull() {
            addCriterion("commodity_name is null");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIsNotNull() {
            addCriterion("commodity_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityNameEqualTo(String value) {
            addCriterion("commodity_name =", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotEqualTo(String value) {
            addCriterion("commodity_name <>", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameGreaterThan(String value) {
            addCriterion("commodity_name >", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_name >=", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLessThan(String value) {
            addCriterion("commodity_name <", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLessThanOrEqualTo(String value) {
            addCriterion("commodity_name <=", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLike(String value) {
            addCriterion("commodity_name like", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotLike(String value) {
            addCriterion("commodity_name not like", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIn(List<String> values) {
            addCriterion("commodity_name in", values, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotIn(List<String> values) {
            addCriterion("commodity_name not in", values, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameBetween(String value1, String value2) {
            addCriterion("commodity_name between", value1, value2, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotBetween(String value1, String value2) {
            addCriterion("commodity_name not between", value1, value2, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesIsNull() {
            addCriterion("commodity_prives is null");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesIsNotNull() {
            addCriterion("commodity_prives is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesEqualTo(Double value) {
            addCriterion("commodity_prives =", value, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesNotEqualTo(Double value) {
            addCriterion("commodity_prives <>", value, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesGreaterThan(Double value) {
            addCriterion("commodity_prives >", value, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesGreaterThanOrEqualTo(Double value) {
            addCriterion("commodity_prives >=", value, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesLessThan(Double value) {
            addCriterion("commodity_prives <", value, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesLessThanOrEqualTo(Double value) {
            addCriterion("commodity_prives <=", value, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesIn(List<Double> values) {
            addCriterion("commodity_prives in", values, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesNotIn(List<Double> values) {
            addCriterion("commodity_prives not in", values, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesBetween(Double value1, Double value2) {
            addCriterion("commodity_prives between", value1, value2, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityPrivesNotBetween(Double value1, Double value2) {
            addCriterion("commodity_prives not between", value1, value2, "commodityPrives");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdIsNull() {
            addCriterion("commodity_master_id is null");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdIsNotNull() {
            addCriterion("commodity_master_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdEqualTo(Integer value) {
            addCriterion("commodity_master_id =", value, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdNotEqualTo(Integer value) {
            addCriterion("commodity_master_id <>", value, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdGreaterThan(Integer value) {
            addCriterion("commodity_master_id >", value, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("commodity_master_id >=", value, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdLessThan(Integer value) {
            addCriterion("commodity_master_id <", value, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdLessThanOrEqualTo(Integer value) {
            addCriterion("commodity_master_id <=", value, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdIn(List<Integer> values) {
            addCriterion("commodity_master_id in", values, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdNotIn(List<Integer> values) {
            addCriterion("commodity_master_id not in", values, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdBetween(Integer value1, Integer value2) {
            addCriterion("commodity_master_id between", value1, value2, "commodityMasterId");
            return (Criteria) this;
        }

        public Criteria andCommodityMasterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("commodity_master_id not between", value1, value2, "commodityMasterId");
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