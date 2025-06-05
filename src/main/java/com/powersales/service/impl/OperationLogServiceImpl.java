package com.powersales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powersales.entity.OperationLog;
import com.powersales.mapper.OperationLogMapper;
import com.powersales.service.OperationLogService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {}
