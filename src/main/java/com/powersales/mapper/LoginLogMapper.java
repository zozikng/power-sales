package com.powersales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powersales.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    // 根据用户名查询登录日志
    @Select("SELECT * FROM power_sales_login_log WHERE username = #{username} ORDER BY login_time DESC")
    List<LoginLog> selectByUsername(@Param("username") String username);

    // 根据租户ID查询登录日志
    @Select("SELECT * FROM power_sales_login_log WHERE tenant_id = #{tenantId} ORDER BY login_time DESC")
    List<LoginLog> selectByTenantId(@Param("tenantId") String tenantId);
}
