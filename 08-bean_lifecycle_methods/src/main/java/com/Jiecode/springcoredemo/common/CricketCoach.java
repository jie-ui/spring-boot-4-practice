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
// define our init method
    // 用于进行bean初始化后的额外设置或启动操作
    //比如连接数据库连接的初始化，日志配置
    @PostConstruct
    public void doMystartupStuff() {
        System.out.println("in doMystartupStuff " + getClass().getSimpleName());
    }


// define our destroy method
    //用于清理资源，关闭连接，释放占用的内存等操作，如关闭文件，数据库连接，清楚缓存
@PreDestroy
public void doMycleanupStuff() {
    System.out.println("in doMycleanupStuff " + getClass().getSimpleName());
}

    @Override
    public String getDailyWorkout() {
        return "Practice  fast bowling for 15 minutes !!! go!!";
    }
}
