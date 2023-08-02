package com.yc.cglibproxy;

import com.yc.jdkproxy.JdkProxyTool;
import com.yc.staticproxy.OrderBiz;
import com.yc.staticproxy.OrderBizImpl;

public class Test3 {
    public static void main(String[] args) {
        CglibProxyTool cglibProxyTool = new CglibProxyTool(new OrderBizImpl());
        OrderBiz orderBiz = (OrderBiz) cglibProxyTool.createProxy();
        System.out.println("生成代理类对象" + orderBiz.toString());  //与jdk代理类似 每调用一次 调用一次 intercept() 方法

        //每调用一次目标类  进入一次 invoke() 方法
        orderBiz.findOrder();
        orderBiz.addOrder(3,876);
    }
}
