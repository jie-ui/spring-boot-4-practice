package com.jiecode.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;





@Aspect
@Component
@Order(2)
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
    @Before("com.jiecode.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }


}
