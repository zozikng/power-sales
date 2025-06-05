package com.powersales.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powersales.entity.Account;
import com.powersales.entity.OperationLog;
import com.powersales.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.powersales.aspect.OperationAnno)")
    public void operationLogPointcut() {}

    @Around("operationLogPointcut() && @annotation(OperationAnno)")
    public Object logOperation(ProceedingJoinPoint joinPoint, OperationAnno OperationAnno) throws Throwable {
        Object result;
        String accountId = null;
        String username = null;

        try {
            // 获取当前登录用户（从 SecurityContextHolder 中）
            Account loginAccount = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            accountId = loginAccount.getId();
            username = loginAccount.getUsername();
        } catch (Exception ignored) {}

        // 获取操作方法参数
        Object[] args = joinPoint.getArgs();
        String detailJson = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            detailJson = objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            detailJson = "参数解析失败";
        }

        // 执行目标方法
        result = joinPoint.proceed();

        // 保存日志
        OperationLog logEntry = new OperationLog ();
        operationLogService.save(logEntry);

        return result;
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasText(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
