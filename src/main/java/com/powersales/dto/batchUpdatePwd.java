package com.powersales.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @program: powerSales
 * @author: zzk
 * @created: 2025/06/05 22:20
 */
@Data
public class batchUpdatePwd {

    private List<String> ids;

    private String password;
}
