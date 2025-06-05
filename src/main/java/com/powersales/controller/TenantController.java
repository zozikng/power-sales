package com.powersales.controller;

import com.powersales.common.Result;
import com.powersales.dto.TenantQuery;
import com.powersales.entity.Tenant;
import com.powersales.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @GetMapping("list")
    public Result<List<Tenant>> list() { return Result.success(tenantService.list()); }

    @PostMapping("page")
    public Result<?> listAccounts(@RequestBody TenantQuery request)
    {
        return tenantService.pageList(request);
    }

    @PostMapping("add")
    public Result<Boolean> add(@RequestBody Tenant tenant) { return Result.success(tenantService.save(tenant)); }

    @PutMapping("update")
    public Result<Boolean> update(@RequestBody Tenant tenant) { return Result.success(tenantService.updateById(tenant)); }

    @DeleteMapping("delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) { return Result.success(tenantService.removeById(id)); }
}
