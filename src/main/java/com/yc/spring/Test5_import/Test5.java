package com.yc.spring.Test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test5 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigTest5.class);

        String[] names = ac.getBeanDefinitionNames();
        for (String n : names) {
            System.out.println(n);
        }
        //第一种
//        Apple apple = (Apple) ac.getBean("com.yc.spring.Test5_import.Apple");
//        System.out.println(apple);
        //第二种
        Apple apple = (Apple) ac.getBean(Apple.class);//强依赖关系
        System.out.println(apple);
    }
}
