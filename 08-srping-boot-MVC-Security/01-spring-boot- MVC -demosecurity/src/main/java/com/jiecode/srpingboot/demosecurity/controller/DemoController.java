package com.jiecode.srpingboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome(){
        return"home";
    }
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return"fancy-login";
    }
    //add a reqyest mapping for /leaders
   @GetMapping("/leaders")
    public String showLeader(){
        return"leaders";
    }
    @GetMapping("/systems")
    public String showSystems(){
        return"systems";
    }

    //add requestmapping for /access-denie

    @GetMapping("/access-denied")
    public String showaccessdenied(){
        return"access-denied";
    }
}
