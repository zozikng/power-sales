package com.powersales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powersales.entity.Tenant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {

    // 根据名称模糊查询租户
    @Select("SELECT * FROM power_sales_tenant WHERE name LIKE CONCAT('%',#{name},'%')")
    List<Tenant> selectByName(@Param("name") String name);

    // 更新租户账户数量
    @Update("UPDATE power_sales_tenant SET accounts_count = accounts_count + #{increment} WHERE id = #{id}")
    int updateAccountsCount(@Param("id") Long id, @Param("increment") int increment);
}
