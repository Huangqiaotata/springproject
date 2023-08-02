package com.yc.dao;

import org.ycframework.annotation.YcLazy;
import org.ycframework.annotation.YcRepository;
import org.ycframework.annotation.YcScope;

@YcRepository
@YcLazy  //容器初始化时不会实例化
@YcScope(value = "prototype")
public class UserDaoImpl implements UserDao {
    @Override
    public void add(String name) {
        System.out.println("dao add "+name);
    }
}
