package cn.xu.dygl.account.service;

import cn.xu.dygl.account.entity.Account;

public interface AccountService {

    public void update(Account form);

    public Account findAccountById(String accountId);
}
