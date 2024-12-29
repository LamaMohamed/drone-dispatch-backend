package com.elmenus.drones.shared.logging;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.elmenus..*(..)) && @annotation(LogAnnotation)")
    public Object aroundTraceableMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().toShortString();

        log.info("Starting method: {}", methodName);

        long startTime = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();

            long endTime = System.currentTimeMillis();
            log.info("Completed method: {} in {} ms", methodName, (endTime - startTime));

            return result;
        } catch (Throwable ex) {
            log.error("Exception in method: {} - {}", methodName, ex.getMessage(), ex);
            throw ex;
        }
    }
}
