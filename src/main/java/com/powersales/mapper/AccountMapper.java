package com.powersales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powersales.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// mapper/AccountMapper.java
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    // 根据用户名查询账户
    @Select("SELECT * FROM power_sales_account WHERE username = #{username}")
    Account selectByUsername(@Param("username") String username);

    // 根据租户ID查询账户列表
    @Select("SELECT * FROM power_sales_account WHERE tenant_id = #{tenantId}")
    List<Account> selectByTenantId(@Param("tenantId") String tenantId);

    // 更新账户状态
    @Update("UPDATE power_sales_account SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") String id, @Param("status") String status);
}
