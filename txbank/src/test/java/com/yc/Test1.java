package com.yc;

import com.yc.biz.AccountBiz;
import com.yc.configs.Config;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@Log4j2
//以上两个注解相当于  ApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
public class Test1 { // JUnit4 可以不继承TestCase

    //可以在这里完成 DI 操作  被spring托管
    @Autowired
    private AccountBiz accountBiz;

    //单元测试用例
    @Test
    public void testAddAccount() {
        accountBiz.addAccount(1, 56);
    }

    //引入断言 方便测试
    @Test
    public void testAdd() {
        int x = 3, y = 7;
        Assert.assertEquals(10, x + y);
    }
}
