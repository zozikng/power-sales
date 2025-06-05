package com.powersales.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * 租户数据传输对象
 */
public class TenantDTO {
    @NotBlank(message = "租户名称不能为空")
    @Size(max = 100, message = "名称长度不超过100字符")
    private String name;

    @NotBlank(message = "所属行业不能为空")
    private String industry;

    @NotNull(message = "注册日期不能为空")
    private LocalDate registrationDate;

    @Email(message = "联系邮箱格式错误")
    private String contactEmail;

    @Pattern(regexp = "^\\d{3}-\\d{8}|\\d{4}-\\d{7}$",
             message = "电话格式示例：0571-12345678")
    private String contactPhone;

    // Getters & Setters
}
