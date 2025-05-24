package com.financial.transaction.api.service.impl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheKeyAspect {
    @Before("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void beforeCacheable(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();

        // 根据参数和方法名拼接出模拟的key
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key = new StringBuilder(methodName).append("(");
        for (Object arg : args) {
            key.append(arg).append(",");
        }
        key.deleteCharAt(key.length() - 1); // 去掉最后一个逗号
        key.append(")");

        // 打印key
        System.out.println("Cache Key: " + key.toString());
    }
}