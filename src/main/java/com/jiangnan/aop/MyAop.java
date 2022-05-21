package com.jiangnan.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAop {

    @Pointcut("execution(* com.jiangnan.service.*.*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            System.out.println("method...start...");
            result = joinPoint.proceed();
            System.out.println("method...end...");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
