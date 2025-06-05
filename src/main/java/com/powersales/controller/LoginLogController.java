package com.powersales.controller;

import com.powersales.common.Result;
import com.powersales.entity.LoginLog;
import com.powersales.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logins")
public class LoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    @GetMapping
    public Result<List<LoginLog>> list() { return Result.success(loginLogService.list()); }
}
