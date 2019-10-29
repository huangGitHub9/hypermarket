package cn.xu.dygl.account.dao;

import cn.xu.dygl.account.entity.Account;

public interface AccountDao {

    public void update(Account form);

    public Account findAccountById(String accountId);


}
