package cn.xu.dygl.user.entity;

/**
 * @author xupenghao@163.com
 * @date 2018-05-18 15:09
 */
public class Consume {

    private String customeTime;
    private String customePrice;

    public String getCustomeTime() {
        return customeTime;
    }

    public void setCustomeTime(String customeTime) {
        this.customeTime = customeTime;
    }

    public String getCustomePrice() {
        return customePrice;
    }

    public void setCustomePrice(String customePrice) {
        this.customePrice = customePrice;
    }

    @Override
    public String toString() {
        return "Consume{" +
                "customeTime='" + customeTime + '\'' +
                ", customePrice='" + customePrice + '\'' +
                '}';
    }
}
