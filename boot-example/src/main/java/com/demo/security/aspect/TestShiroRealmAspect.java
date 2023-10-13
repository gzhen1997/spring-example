package com.demo.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @auther gzhen
 * @date 2023-10-13  11:17
 * @description
 */
//@Component
//@Aspect
//@EnableAspectJAutoProxy(exposeProxy = true)
public class TestShiroRealmAspect {

    @Pointcut("execution(public * com.demo.security.ShiroRealm.checkUserTokenIsEffect(..))")
    public void pointcut(){}


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        System.out.println("aop 执行了");
        return proceed;
    }
}
