package com.powersales.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 账户详情返回数据
 */
@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String role;
    private String email;
    private String phone;
    private String status;
    private LocalDateTime createdAt;
    private Long tenantId;
    private String tenantName;  // 扩展租户名称
}
