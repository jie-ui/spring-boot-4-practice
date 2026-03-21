package com.jiecode.aopdemo.aspect;

import com.jiecode.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;
import java.util.List;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* com.jiecode.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

    }

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


    //add @AFTERtHORWING
    @AfterThrowing(
            pointcut = "execution(* com.jiecode.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingAddAccountAdvice(JoinPoint theJoinPoint,Throwable theExc) {

        //print out whcih method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThorwing on method: " + method);
        //log the exception

        System.out.println("\n=====>>>The Executing is: " + theExc.getMessage());

    }

    @Around("execution(* com.jiecode.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint pjp) throws  Throwable{
        //print out method we are advising on
        String method = pjp.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        //get begin timestamp

         long start = System.currentTimeMillis();
        //now lt's execure the method
Object result = pjp.proceed();
        //get end timestamp
long end = System.currentTimeMillis();
        //compute duration and display it
long time = end - start;
System.out.println("\n=====> duration:"+ time/1000.0 + "seconds");
        return result;

    }

}
