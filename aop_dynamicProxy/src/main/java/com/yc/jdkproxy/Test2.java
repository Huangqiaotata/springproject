package com.yc.jdkproxy;

import com.yc.staticproxy.OrderBiz;
import com.yc.staticproxy.OrderBizImpl;

public class Test2 {
    public static void main(String[] args) {
        //设置代理生成的字节码 保存到当前目录
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        JdkProxyTool jdkProxyTool = new JdkProxyTool(new OrderBizImpl());
        OrderBiz orderBiz = (OrderBiz) jdkProxyTool.createProxy();
        System.out.println("生成代理类对象" + orderBiz.toString());

        //每调用一次目标类  进入一次 invoke() 方法
        orderBiz.findOrder();
        orderBiz.addOrder(3,876);
    }
}
