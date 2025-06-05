package com.powersales.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powersales.entity.Tenant;
import lombok.Data;

/**
 * 租户分页查询参数
 */
@Data
public class TenantPageDTO {
    private Integer pageNum = 1;     // 当前页
    private Integer pageSize = 10;   // 每页条数
    private String name;             // 租户名称（模糊查询）
    private String status;           // 状态过滤（active/inactive）

    // 转换为MyBatis Plus分页对象
    public Page<Tenant> toPage() {
        return new Page<>(pageNum, pageSize);
    }
}
