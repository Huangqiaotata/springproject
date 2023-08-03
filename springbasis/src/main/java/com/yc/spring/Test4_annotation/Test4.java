package com.yc.spring.Test4_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test4 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigTest4.class);

        ProductBiz pb = (ProductBiz)ac.getBean("productBizImpl"); //beanid 的约定是：类名首字母小写
        pb.takein();
    }
}
