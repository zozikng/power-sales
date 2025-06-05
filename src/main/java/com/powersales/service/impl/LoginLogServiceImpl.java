package com.powersales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powersales.entity.LoginLog;
import com.powersales.mapper.LoginLogMapper;
import com.powersales.service.LoginLogService;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {}
