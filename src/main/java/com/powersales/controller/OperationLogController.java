package com.powersales.controller;

import com.powersales.common.Result;
import com.powersales.entity.OperationLog;
import com.powersales.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationLogController {
    @Autowired
    private OperationLogService operationLogService;

    @GetMapping
    public Result<List<OperationLog>> list() { return Result.success(operationLogService.list()); }
}
