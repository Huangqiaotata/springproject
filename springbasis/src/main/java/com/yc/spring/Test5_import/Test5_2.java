package com.yc.spring.Test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test5_2 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig2.class);

        String[] names = ac.getBeanDefinitionNames();
        for (String n : names) {
            System.out.println(n);
            //com.yc.spring.Test5_import.Apple
            //com.yc.spring.Test3_factoryBean.Pear
        }
    }
}
