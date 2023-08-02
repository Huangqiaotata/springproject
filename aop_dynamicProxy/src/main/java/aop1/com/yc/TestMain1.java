package aop1.com.yc;

import aop1.Config;
import aop1.com.yc.biz.OrderBiz;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain1 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        //面向接口开发
        OrderBiz orderBiz = applicationContext.getBean(OrderBiz.class);
//        orderBiz.makeOrder(1,556);
//        orderBiz.findOrderID("apple");
//        orderBiz.findOrderID("apple");
//        orderBiz.findOrderID("apple");
//        orderBiz.findOrderID("pear");

        orderBiz.findPID("apple");
//        orderBiz.findPID("apple");
//        orderBiz.findPID("pear");
    }
}
