package com.powersales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

// LoginLog.java
@Data
@TableName("power_sales_login_log")
public class LoginLog {
    @TableId(type = IdType.AUTO)
    private Long id;          // 日志ID
    private String username;  // 用户名
    private String accountId;  // 租户ID
    private Date loginTime;   // 登录时间
    private String ipAddress;        // 登录IP
    private String status;    // 登录状态: success/failed
    private String userAgent; // 用户代理信息
    private Boolean success; // 是否成功
}
