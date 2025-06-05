package com.powersales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powersales.common.Result;
import com.powersales.dto.TenantQuery;
import com.powersales.entity.Account;
import com.powersales.entity.Tenant;
import com.powersales.mapper.TenantMapper;
import com.powersales.service.TenantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;
    @Override
    public Result<?> pageList(TenantQuery request) {
        Page<Tenant> tenantPage = new Page<>(request.getPage (), request.getSize ());
        LambdaQueryWrapper<Tenant> query = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank (request.getName ())) {
            query.like (Tenant::getName, request.getName ());
        }
        if (StringUtils.isNotBlank (request.getStatus ())){
            query.eq (Tenant::getStatus, request.getStatus ());
        }
        if (StringUtils.isNotBlank (request.getStatusDesc ())) {
            query.eq (Tenant::getStatusDesc, request.getStatusDesc ());
        }
        Page<Tenant> result = tenantMapper.selectPage(tenantPage, query);
        return Result.success(result);
    }
}
