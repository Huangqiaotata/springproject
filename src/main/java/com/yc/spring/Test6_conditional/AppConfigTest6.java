package com.yc.spring.Test6_conditional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.yc.spring.Test6_conditional"})
public class AppConfigTest6 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigTest6.class);
        String[] names = ac.getBeanDefinitionNames();
        for (String n : names) {
            System.out.println(n);
        }
    }
}
