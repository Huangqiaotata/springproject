package com.yc.staticproxy;

/**
 * 目标类
 */
public class OrderBizImpl implements OrderBiz {
    @Override
    public void addOrder(int pid, int num) {
        System.out.println("添加了一个订单，订单详情为：" + pid + "\t" + num);
    }

    @Override
    public void findOrder() {
        System.out.println("findOrder......");
    }
}
