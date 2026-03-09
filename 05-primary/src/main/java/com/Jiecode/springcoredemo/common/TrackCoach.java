package com.Jiecode.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
//since there are mulitple implementations
//we are making TrackCoach as the PRIMARY coach
@Primary
public class TrackCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return" Run a hard 5k";
    }
}
