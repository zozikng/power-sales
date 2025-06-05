package com.powersales.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BatchAccountCreateRequest {

    @Schema(description = "租户ID")
    private long tenantId;

    @Schema(description = "租户ID")
    private String tenantName;

    @Schema(description = "账号数量", example = "1")
    private Integer count;

    @Schema(description = "账号角色", example = "user")
    private String role;

    @Schema(description = "账号角色", example = "user")
    private String roleName;
}
