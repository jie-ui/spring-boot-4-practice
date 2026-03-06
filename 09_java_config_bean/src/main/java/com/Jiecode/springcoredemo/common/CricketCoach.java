package com.Jiecode.springcoredemo.common;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
//@component annotation marks the class as a spring bean
@Component


public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("in constructor " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice  fast bowling for 15 minutes !!! go!!";
    }
}
