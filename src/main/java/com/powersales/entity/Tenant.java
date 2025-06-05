package com.powersales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

// Tenant.java
@Data
@TableName("power_sales_tenant")
public class Tenant {
    @TableId(type = IdType.AUTO)
    private Long id;                // 租户ID
    private String name;              // 租户名称
    private String industry;          // 所属行业
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date registrationDate;    // 注册日期
    private String status;            // 状态: active/inactive
    private String statusDesc;            // 状态描述
    private Integer accountsCount;    // 账户数量
    private String description;       // 描述信息
    private String contactPerson;     // 联系人
    private String contactEmail;      // 联系邮箱
    private String contactPhone;      // 联系电话
    private Date createdAt;           // 创建时间

    /**
     * 最大账号数
     */
    @TableField("max_accounts")
    private Integer maxAccounts;

    /**
     * 每日最大交易量
     */
    @TableField("max_daily_transactions")
    private Long maxDailyTransactions;

    /**
     * 存储空间(GB)
     */
    @TableField("storage_space_gb")
    private Integer storageSpaceGb;

    /**
     * API调用限制(每小时)
     */
    @TableField("api_call_limit_per_hour")
    private Integer apiCallLimitPerHour;

}
