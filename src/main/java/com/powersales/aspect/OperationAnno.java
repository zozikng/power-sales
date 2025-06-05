package com.powersales.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationAnno {
    String module();       // 模块名，例如“账户管理”、“租户管理”
    String operation();    // 操作类型，例如“新增”、“删除”
}
