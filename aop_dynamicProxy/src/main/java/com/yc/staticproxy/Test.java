package com.yc.staticproxy;

public class Test {
    public static void main(String[] args) {
        OrderBiz orderBiz = new StaticProxy(new OrderBizImpl());
        orderBiz.addOrder(1,64);
    }
}
