package com.powersales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powersales.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    // 根据操作人查询操作日志
    @Select("SELECT * FROM power_sales_operation_log WHERE operator = #{operator} ORDER BY operation_time DESC")
    List<OperationLog> selectByOperator(@Param("operator") String operator);

    // 根据租户ID查询操作日志
    @Select("SELECT * FROM power_sales_operation_log WHERE tenant_id = #{tenantId} ORDER BY operation_time DESC")
    List<OperationLog> selectByTenantId(@Param("tenantId") String tenantId);
}
