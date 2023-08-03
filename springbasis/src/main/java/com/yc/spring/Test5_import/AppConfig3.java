package com.yc.spring.Test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration          //pear                      //grape  当pear托管后 才会被托管
@Import({Apple.class, FruitImportSelector.class, FruitNameImportBeanDefinitionRegister.class})
public class AppConfig3 {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig3.class);
        String[] names = ac.getBeanDefinitionNames();
        for (String n : names) {
            System.out.println(n);
        }
    }
}
