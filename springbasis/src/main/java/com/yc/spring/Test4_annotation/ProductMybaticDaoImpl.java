package com.yc.spring.Test4_annotation;


import org.springframework.stereotype.Repository;

@Repository //表明这个类被spring托管 这个类是一个dao层的类  spring会将异常转为RunTimeException  减少对业务层的侵入
public class ProductMybaticDaoImpl implements ProductDao {
    @Override
    public void add() {
        System.out.println("ProductMybaticDaoImpl的add方法");
    }

    @Override
    public void find() {
        System.out.println("ProductMybaticDaoImpl的find方法");
    }

    @Override
    public void update() {
        System.out.println("ProductMybaticDaoImpl的update方法");
    }

    public ProductMybaticDaoImpl() {
        System.out.println("ProductMybaticDaoImpl。。。");
    }
}
