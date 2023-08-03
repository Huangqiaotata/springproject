package com.yc.spring.Test4_annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//指定spring容器扫描类的路径
@ComponentScan(basePackages = "com.yc.spring.Test4_annotation")
public class AppConfigTest4 {
    //原来是利用 @Bean  来创建Bean  交给spring 托管

}
