package cn.cote.pojo;

import java.util.Date;

public class Deal {
    private Integer dealId;

    private Integer dealUserId;

    private Integer dealShopId;

    private Date dealTime;

    private String dealCode;

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public Integer getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Integer dealUserId) {
        this.dealUserId = dealUserId;
    }

    public Integer getDealShopId() {
        return dealShopId;
    }

    public void setDealShopId(Integer dealShopId) {
        this.dealShopId = dealShopId;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealCode() {
        return dealCode;
    }

    public void setDealCode(String dealCode) {
        this.dealCode = dealCode == null ? null : dealCode.trim();
    }
}