package cn.cote.pojo;

public class MyCommodity {
    private Integer commodityId;

    private String commodityImg;

    private String commodityName;

    private Double commodityPrives;

    private String commodityMessage;

    private String userName;

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
        this.commodityImg = commodityImg;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Double getCommodityPrives() {
        return commodityPrives;
    }

    public void setCommodityPrives(Double commodityPrives) {
        this.commodityPrives = commodityPrives;
    }

    public String getCommodityMessage() {
        return commodityMessage;
    }

    public void setCommodityMessage(String commodityMessage) {
        this.commodityMessage = commodityMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
