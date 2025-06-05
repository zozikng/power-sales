package com.powersales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

// OperationLog.java
@Data
@TableName("power_sales_operation_log")
public class OperationLog {
    @TableId(type = IdType.AUTO)
    private Long id;            // 日志ID
    private String tenantId;    // 租户ID
    private String operator;    // 操作人
    private String operation;   // 操作描述
    private String method;      // 请求方法
    private String params;      // 请求参数
    private String result;      // 操作结果
    private String errorMsg;    // 错误信息
    private Date operationTime; // 操作时间
    private Long duration;      // 执行时长(ms)
}
