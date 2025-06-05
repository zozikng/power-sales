package com.powersales.dto;

import lombok.Data;

import java.util.Date;

// Account.java
@Data
public class AccountRequest {

    private String id;          // 账户ID
    private String username;    // 用户名
    private String password;    // 密码
    private String rePassword;    // 密码
    private long tenantId;    // 租户ID
    private String tenantName;    // 租户ID
    private String role;        // 角色: admin/user
    private String roleName;        // 角色: admin/user
    private String email;       // 邮箱
    private String phone;       // 手机号
    private String status;      // 状态: active/inactive
    private String statusDesc;     // 状态描述
    private Date createdAt;     // 创建日期
}
