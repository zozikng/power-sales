package com.powersales.dto;

import lombok.Data;

/**
 * @description:
 * @program: powerSales
 * @author: zzk
 * @created: 2025/06/05 20:55
 */
@Data
public class AccountQuery {
    int page=1;
    int size=10;
    Long tenantId;
    String tenantName;
    String userName;
    String role;
    String roleName;
}
