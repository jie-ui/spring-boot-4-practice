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


    //define a constrcotor for denpendency injection
    //@Autowired annotation tells spring to inject dependency
    //if you only have one constructor then @Autowired on contrucyor is optional
    @Autowired



    public void DemoController(
            @Qualifier("cricketCoach") Coach theCoach

    ) {
        System.out.println("in constructor " + getClass().getSimpleName());
        this.myCoach = theCoach;

    }


    // it is a composed annotation that acts as a shourtcut
    //for mapping Http Get Requests onto sepcific handler methods
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

}