package com.cotech.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.cotech.service.*.save*(..))")
    public void arroundSaveService(ProceedingJoinPoint joinPoint){
        logger.debug("开始录入信息");
        try {
            joinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.debug("失败"+throwable.getMessage());
        }
        logger.debug("成功！");
    }

    @Around("execution(* com.cotech.service.*.update*(..))")
    public void arroundUpdateService(ProceedingJoinPoint joinPoint){
        logger.debug("开始更新信息");
        try {
            joinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.debug("失败"+throwable.getMessage());
        }
        logger.debug("成功！");
    }

}
