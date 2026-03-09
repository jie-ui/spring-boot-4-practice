package com.Jiecode.springcoredemo.rest;

import com.Jiecode.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private filed for the dependency

    private Coach myCoach;
    private Coach anotherCoach;

    //define a constrcotor for denpendency injection
    //@Autowired annotation tells spring to inject dependency
    //if you only have one constructor then @Autowired on contrucyor is optional
    @Autowired
    //@Qualifier specifies that the bean with the name"baseballcoach"should be injected,helping spring decide which
    //specific coach bean to inject when there are mulitple coach beans avaliable
    //@Qualifier用于在多个相同类型的bean存在时，细化注入过程，明确指定注入哪一个bean。

    /*
    default scope is singleton
    all dependency injections for the bean will reference the Same bean
     */
    public void DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theotherCoach
    ) {
        System.out.println("in constructor " + getClass().getSimpleName());
        this.myCoach = theCoach;
        anotherCoach = theotherCoach;
    }





// it is a composed annotation that acts as a shourtcut
    //for mapping Http Get Requests onto sepcific handler methods
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
/*
check to see if this is the same bean
true or false depending on the bean scope
sigleton:true
Prototype:false
 */
    @GetMapping("/check")
    public String check() {
        return"Comparing beans: myCoach == anotherCoach ,"+ (myCoach == anotherCoach);
    }
}
