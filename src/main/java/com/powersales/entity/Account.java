package com.powersales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

// Account.java
@Data
@TableName("power_sales_account")
public class Account {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;          // 账户ID
    private String username;    // 用户名
    private String password;    // 密码
    private long tenantId;    // 租户ID
    private String tenantName;    // 租户ID
    private String role;        // 角色: admin/user
    private String roleName;        // 角色: admin/user
    private String email;       // 邮箱
    private String phone;       // 手机号
    private String status;      // 状态: active/inactive
    private String statusDesc;     // 更新日期
    private Date createdAt;     // 创建日期
}
