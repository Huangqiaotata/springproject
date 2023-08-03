package com.yc.spring.Test3_factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 水果工厂
 */
public class FruitFactoryBean implements FactoryBean<Pear> {
    @Override
    public Pear getObject() throws Exception {
        //加入逻辑
        return new Pear();
    }

    @Override
    public Class<?> getObjectType() {
        return Pear.class;
    }

    @Override
    public boolean isSingleton() {
        return true; //单例
//        return false;// 不为单例
    }

    public FruitFactoryBean() {
        System.out.println("工厂被创建。。。");
    }
}
