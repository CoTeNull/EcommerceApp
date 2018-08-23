package cn.cote.pojo;

public class Commodity {
    private Integer commodityId;

    private String commodityImg;

    private String commodityName;

    private Double commodityPrives;

    private Integer commodityMasterId;

    private String commodityMessage;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg == null ? null : commodityImg.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Double getCommodityPrives() {
        return commodityPrives;
    }

    public void setCommodityPrives(Double commodityPrives) {
        this.commodityPrives = commodityPrives;
    }

    public Integer getCommodityMasterId() {
        return commodityMasterId;
    }

    public void setCommodityMasterId(Integer commodityMasterId) {
        this.commodityMasterId = commodityMasterId;
    }

    public String getCommodityMessage() {
        return commodityMessage;
    }

    public void setCommodityMessage(String commodityMessage) {
        this.commodityMessage = commodityMessage == null ? null : commodityMessage.trim();
    }
}