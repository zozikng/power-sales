package com.powersales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.powersales.common.Result;
import com.powersales.dto.TenantQuery;
import com.powersales.entity.Tenant;

public interface TenantService extends IService<Tenant> {
    Result<?> pageList(TenantQuery request);
}
