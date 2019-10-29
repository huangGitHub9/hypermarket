package cn.xu.dygl.account.service.impl;

import cn.xu.dygl.account.dao.AccountDao;
import cn.xu.dygl.account.entity.Account;
import cn.xu.dygl.account.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Transactional
    @Override
    public void update(Account form) {
        accountDao.update(form);
    }

    @Override
    public Account findAccountById(String accountId) {
        return accountDao.findAccountById(accountId);
    }
}
