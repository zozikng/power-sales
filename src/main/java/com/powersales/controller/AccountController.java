package com.powersales.controller;

import cn.hutool.core.bean.BeanUtil;
import com.powersales.common.Result;
import com.powersales.dto.*;
import com.powersales.entity.Account;
import com.powersales.service.AccountService;
import com.powersales.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("list")
    public Result<List<Account>> list() {
        return Result.success (accountService.list ());
    }
    // AccountController.java
    @PostMapping("page")
    public Result<?> listAccounts(@RequestBody AccountQuery request)
    {
        return accountService.listAccounts(request);
    }


    @PostMapping("add")
    public Result<?> add(@RequestBody AccountRequest request) {
        if(StringUtils.isBlank (request.getRePassword ())
                ||StringUtils.isBlank (request.getPassword ())
                ||!request.getPassword ().equals (request.getRePassword ())){
            return Result.fail ("密码不一致");
        }
        Account account = new Account ();
        BeanUtil.copyProperties (request,account);
        return accountService.createAccount(account);
    }

    @PutMapping("update")
    public Result<Boolean> update(@RequestBody AccountRequest request) {
        if(StringUtils.isBlank (request.getRePassword ())
                ||StringUtils.isBlank (request.getPassword ())
                ||!request.getPassword ().equals (request.getRePassword ())){
            return Result.fail ("密码不一致");
        }
        Account account = new Account ();
        BeanUtil.copyProperties (request,account);
        request.setPassword (bCryptPasswordEncoder.encode (request.getPassword ()));
        return Result.success (accountService.updateById (account));
    }

    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success (accountService.removeById (id));
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Account account = accountService.login (loginRequest.getUsername (), loginRequest.getPassword ());
        if (account != null) {
            String token = JwtUtil.generateToken (account.getId ());
            LoginResponse loginResponse = new LoginResponse ();
            loginResponse.setToken (token);
            loginResponse.setAccount (account);
            return Result.success (loginResponse);
        }
        return Result.fail ("用户名或密码错误");
    }

    @PostMapping("/batchCreate")
    public Result<?> batchCreate(@RequestBody BatchAccountCreateRequest request) {
        return accountService.batchCreateAccounts (request);
    }
    @PostMapping("/batchUpdatePwd")
    public Result<?> batchUpdatePwd(@RequestBody batchUpdatePwd request ) {
        return accountService.batchUpdatePwd (request);
    }
}

