package com.yc.spring.Test3_factoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 创建工厂bean
 */
public class Test {
    public static void main(String[] args) {
        //1、创建容器
        //容器启动  FruitFactoryBean   pear
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigTest3.class);//工厂被创建
        Object obj = ac.getBean("ffb"); //  ffb -> pear
        System.out.println(obj.hashCode());
        //懒加载
        Object object = ac.getBean("&ffb");// &ffb -> 工厂bean
        System.out.println(object);

        obj = ac.getBean("ffb");
        System.out.println(obj.hashCode());

        //3、获取spring容器中所有被托管的bean
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String bn : beanNames) {
            System.out.println(bn);
        }
    }
}
