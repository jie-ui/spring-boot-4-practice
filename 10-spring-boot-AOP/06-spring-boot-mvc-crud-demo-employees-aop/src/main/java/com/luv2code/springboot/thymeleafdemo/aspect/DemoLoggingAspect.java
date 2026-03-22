package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
    //setup logger

    private Logger myLogger = LoggerFactory.getLogger(getClass().getName());

    //set pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    //do the same for service and Dao
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {}

    //DAO
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage()|| forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    //add @Befire advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        //display method we are calling
        String theMethodName = joinPoint.getSignature().getName();
        myLogger.info("====> in @Before method: " + theMethodName);
        //display the argument to the metohod


        //get the arguments
        Object[] args = joinPoint.getArgs();
        //loop thru dn siplay atgs
        for (Object temparg : args) {
            myLogger.info("====> arg: " + temparg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        //display method we are returning form
        String theMethodName = joinPoint.getSignature().getName();
        myLogger.info("====> in @After method: " + theMethodName);

        //display data returned
        myLogger.info("====> returnValue: " + returnValue);

    }


}
