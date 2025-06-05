package com.powersales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.powersales.common.Result;
import com.powersales.dto.AccountQuery;
import com.powersales.dto.BatchAccountCreateRequest;
import com.powersales.dto.batchUpdatePwd;
import com.powersales.entity.Account;

public interface AccountService extends IService<Account> {
    Account login(String username, String password);
    Result<?> batchCreateAccounts(BatchAccountCreateRequest request);

    Result<?> createAccount(Account request);

    Result<?> listAccounts(AccountQuery request);

    Result<?> batchUpdatePwd(batchUpdatePwd request);
}
