package com.Jiecode.springcoredemo.config;

import com.Jiecode.springcoredemo.common.SwimCoach;
import com.Jiecode.springcoredemo.common.Coach;  // Ensure Coach is properly imported
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Use @Configuration instead of @Configurable
public class SportConfig {

    // Define the @Bean method to configure the bean
    @Bean

    public Coach swimCoach() {
        return new SwimCoach();  // Return a SwimCoach object as a Coach
    }
}
//使用configuration + @bean
/*
用于标记配置类，表明这个类是spring配置类，并且可以包含spring bean的定义
用于手动配置一个bean，在方法中返回一个实例，并注册到spring，用qulifiere在controller中注入
 */