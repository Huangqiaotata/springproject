package com.yc.spring.Test3_factoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 配置类
public class AppConfigTest3 {

    @Bean  //FruitFactoryBean 被托管
    public FruitFactoryBean ffb() { //此处的 ffb 对应一个产品
        return new FruitFactoryBean();
    }
}
