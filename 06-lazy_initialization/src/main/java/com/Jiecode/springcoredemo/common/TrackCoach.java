package com.Jiecode.springcoredemo.common;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
//since we are Not injecting TrackCoach,it is not initialized
@Lazy

public class TrackCoach implements Coach {

    public TrackCoach() {
        System.out.println("in constructor " + getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout() {
        return" Run a hard 5k";
    }
}
