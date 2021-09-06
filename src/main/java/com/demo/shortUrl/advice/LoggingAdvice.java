package com.demo.shortUrl.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    /*
    @Pointcut(value = "execution(* com.demo.shortUrl.*.*.*(..) )")
    public void applicationPointCut() {
    };

    @Around("applicationPointCut()")
    public Object applicationLogic(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            String className = joinPoint.getTarget().getClass().toString();
            String methodName = joinPoint.getSignature().getName();
            Object[] requestArray = joinPoint.getArgs();
            log.info("Request: Method invoked: " + className + " : " + methodName + " () " + "arguments : " + new ObjectMapper().writeValueAsString(requestArray));
            responseObj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return responseObj;
    }

    @AfterThrowing(
            pointcut = "execution(* com.demo.shortUrl.*.*.*(..) )",
            throwing = "ex")
    public void doRecoveryActions(DataAccessException ex) {
        log.info("Exception : ", ex.toString());
    }
    */

    @Around("@annotation(com.demo.shortUrl.advice.TimeTracker)")
    public Object timeTrack(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            long start = System.currentTimeMillis();
            responseObj = joinPoint.proceed();
            long end = System.currentTimeMillis();
            log.info("Method Name: " + joinPoint.getSignature() + " Time taken to execute: " + (end - start) + "ms");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return responseObj;
    }

}
