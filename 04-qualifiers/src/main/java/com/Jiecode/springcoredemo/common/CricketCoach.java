package com.Jiecode.springcoredemo.common;


import org.springframework.stereotype.Component;
//@component annotation marks the class as a spring bean
@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice  fast bowling for 15 minutes !!! go!!";
    }
}
