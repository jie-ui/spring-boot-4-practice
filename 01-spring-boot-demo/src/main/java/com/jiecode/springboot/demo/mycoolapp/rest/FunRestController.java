package com.jiecode.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    //expose "/" that return "HEllo WORld"
    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }

}
