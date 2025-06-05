package com.powersales.dto;

import lombok.Data;

// Tenant.java
@Data
public class TenantQuery {

    private String name;              // 租户名称

    private String status;            // 状态: active/inactive
    private String statusDesc;            // 状态描述
    int page=1;
    int size=10;
}
