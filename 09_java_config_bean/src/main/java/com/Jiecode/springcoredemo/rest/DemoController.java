package com.Jiecode.springcoredemo.rest;

import com.Jiecode.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;  // Define a private field for the dependency

    // Constructor for dependency injection (no need for @Autowired if only one constructor)
    public DemoController(@Qualifier("swimCoach") Coach theCoach) {
        System.out.println("in constructor " + getClass().getSimpleName());
        this.myCoach = theCoach;
    }

    // HTTP GET method to handle the request
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();  // Call the method on Coach
    }
}