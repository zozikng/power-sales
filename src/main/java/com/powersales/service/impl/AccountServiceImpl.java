package com.powersales.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powersales.common.Result;
import com.powersales.dto.AccountQuery;
import com.powersales.dto.BatchAccountCreateRequest;
import com.powersales.dto.batchUpdatePwd;
import com.powersales.entity.Account;
import com.powersales.entity.LoginLog;
import com.powersales.mapper.AccountMapper;
import com.powersales.mapper.LoginLogMapper;
import com.powersales.mapper.TenantMapper;
import com.powersales.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TenantMapper tenantMapper;


    @Override
    public Account login(String username, String password) {
        Account account = getOne(new LambdaQueryWrapper<Account> ().eq(Account::getUsername, username));
        boolean success = account != null && bCryptPasswordEncoder.matches(password,account.getPassword());
        LoginLog log = new LoginLog();
        log.setAccountId(account != null ? account.getId() : null);
        log.setUsername(username);
        log.setIpAddress("模拟IP");
        log.setUserAgent("模拟UserAgent");
        log.setLoginTime(new Date ());
        log.setSuccess(success);
        loginLogMapper.insert(log);
        return success ? account : null;
    }

    @Override
    public Result<?> batchCreateAccounts(BatchAccountCreateRequest request) {
        List<Account> accountList = new ArrayList<> ();
        for (int i = 0; i < request.getCount(); i++) {
            Account account = new Account();
            account.setId(IdWorker.getIdStr());
            account.setUsername("user_" + System.currentTimeMillis() + "_" + i);
            account.setPassword(bCryptPasswordEncoder.encode("88888888"));
            account.setTenantId(request.getTenantId());
            account.setRole(request.getRole());
            account.setStatus("wait");
            account.setStatusDesc("待审核");
            account.setCreatedAt(new Date ());
            account.setTenantName (request.getTenantName ());
            account.setRoleName (request.getRoleName ());
            accountList.add(account);
        }
        accountList.forEach(accountMapper::insert);
        tenantMapper.updateAccountsCount(request.getTenantId (), request.getCount());
        return Result.success("批量创建成功，总数：" + accountList.size());
    }


    @Override
    public Result<?> createAccount(Account request) {
        request.setId(IdWorker.getIdStr());
        request.setPassword (bCryptPasswordEncoder.encode (request.getPassword ()));
        accountMapper.insert(request);
        tenantMapper.updateAccountsCount(request.getTenantId (), 1);
        return Result.success("账号创建成功");
    }

    @Override
    public Result<?> listAccounts(AccountQuery request) {
        Page<Account> accountPage = new Page<>(request.getPage (), request.getSize ());
        LambdaQueryWrapper<Account> query = Wrappers.lambdaQuery();
        if (request.getTenantId () != null) {
            query.eq(Account::getTenantId, request.getTenantId ());
        }
        if (StringUtils.isBlank (request.getRole ())) {
            query.eq(Account::getRole, request.getRole ());
        }
        if (StringUtils.isNotBlank (request.getRoleName ())){
            query.eq(Account::getRoleName, request.getRoleName ());
        }
        if (StringUtils.isNotBlank (request.getTenantName ())){
            query.like (Account::getTenantName, request.getTenantName ());
        }
        if (StringUtils.isNotBlank (request.getUserName ())){
            query.eq(Account::getUsername, request.getUserName ());
        }
        Page<Account> result = accountMapper.selectPage(accountPage, query);
        return Result.success(result);
    }

    @Override
    public Result<?> batchUpdatePwd(batchUpdatePwd request) {
        request.getIds ().forEach (id -> {
            Account account = accountMapper.selectById (id);

            account.setPassword (bCryptPasswordEncoder.encode (request.getPassword ()));
            accountMapper.updateById (account);
        });
        return Result.success ("密码更新成功");
    }


}
