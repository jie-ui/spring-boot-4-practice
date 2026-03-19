package com.jiecode.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;





@Aspect
@Component
public class MyDemoLoggingAspect {

    //public void com.luv2code.aopdemp.dao.AccountDAO.addAccount olny mathc on addACOUNT

    //"execution(public void add*())") math method starting wiht add in any class

    //mathc method wiht base on return Type
    //@Before("execution(* add*())")

    //Math parameter
    /*
    () no arguments
    (*) any type
    (..)0 or more arguments of any type
     */

    //this i swhere we add all of oru relate advice for logging
    //let's start with an @before advice
    @Pointcut("execution(* com.jiecode.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.jiecode.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.jiecode.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}

    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API analytics");
    }


}
