package cn.xu.dygl.memPackage.entity;

import java.io.Serializable;

public class MemPackage implements Serializable {

    private String memPackageId;
    private Integer memPackageMonth;
    private Double memPackagePrice;
    private Double memPackageDiscount;//打折策略
    private Double memPackageResPrice;//打折后的最终的价格



    public MemPackage() {
        super();
    }
    public Double getMemPackageResPrice() {
        return memPackageResPrice;
    }

    public void setMemPackageResPrice(Double memPackageResPrice) {
        this.memPackageResPrice = memPackageResPrice;
    }

    public Double getMemPackageDiscount() {
        return memPackageDiscount;
    }
    public void setMemPackageDiscount(Double memPackageDiscount) {
        this.memPackageDiscount = memPackageDiscount;
    }

    public String getMemPackageId() {
        return memPackageId;
    }

    public void setMemPackageId(String memPackageId) {
        this.memPackageId = memPackageId;
    }

    public Integer getMemPackageMonth() {
        return memPackageMonth;
    }

    public void setMemPackageMonth(Integer memPackageMonth) {
        this.memPackageMonth = memPackageMonth;
    }

    public Double getMemPackagePrice() {
        return memPackagePrice;
    }

    public void setMemPackagePrice(Double memPackagePrice) {
        this.memPackagePrice = memPackagePrice;
    }


    @Override
    public String toString() {
        return "MemPackage{" +
                "memPackageId='" + memPackageId + '\'' +
                ", memPackageMonth=" + memPackageMonth +
                ", memPackagePrice=" + memPackagePrice +
                ", memPackageDiscount=" + memPackageDiscount +
                '}';
    }
}
