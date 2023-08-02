package com.yc.service;

import com.yc.dao.UserDao;
import org.ycframework.annotation.YcResource;
import org.ycframework.annotation.YcService;


@YcService(value = "ub")
public class UserBizImpl implements UserBiz {

    @YcResource(name = "userDaoImpl")
    private UserDao userDao;//面对接口的实现  上面的注解为具体的实现类

    @Override
    public void add(String name) {
        userDao.add(name);
    }
}
