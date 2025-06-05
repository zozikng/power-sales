package com.powersales.dto;

import lombok.Data;

/**
 * @description:
 * @program: powerSales
 * @author: zzk
 * @created: 2025/06/05 22:04
 */
@Data
public class LoginResponse {
    private String token;
    private String tenantName;
}
