package com.jiecode.springboot.demo.demo.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FullRestController {
    //expose "/" that return "HEllo WORld"
    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }
    //expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 4k !";
    }
}
