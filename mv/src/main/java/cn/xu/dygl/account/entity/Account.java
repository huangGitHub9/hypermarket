package cn.xu.dygl.account.entity;

import java.io.Serializable;

public class Account implements Serializable {

    private String accountId ;
    private Double accountBalance;

    public Account() {
        super();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
