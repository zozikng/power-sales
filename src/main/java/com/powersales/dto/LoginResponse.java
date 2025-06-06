package com.powersales.dto;

import com.powersales.entity.Account;
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
    private Account account;
}
