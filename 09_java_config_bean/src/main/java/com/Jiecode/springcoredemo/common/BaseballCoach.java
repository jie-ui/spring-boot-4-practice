package com.Jiecode.springcoredemo.common;

import org.springframework.stereotype.Component;


@Component

public class BaseballCoach implements Coach {

    public BaseballCoach() {
        System.out.println("in constructor " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Spring 30 minutes in batting practice";
    }
}
