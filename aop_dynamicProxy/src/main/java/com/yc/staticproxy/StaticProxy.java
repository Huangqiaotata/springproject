package com.yc.staticproxy;

/**
 * 静态代理类
 * 将目标类引用进来
 */
public class StaticProxy implements OrderBiz {
    //目标类的引用 利用setXxx 或构造方法注入
    private OrderBiz orderBiz;

    public StaticProxy(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }

    //静态代理 既是切入点表达式 又是联接点
    @Override
    public void addOrder(int pid, int num) {
        //前置增强
        showHello();
        //代理类调用目标类的目标方法
        this.orderBiz.addOrder(pid,num);
        //后置增强
        showBye();
    }

    @Override
    public void findOrder() {
        this.orderBiz.findOrder();
    }

    private void showBye() {
        System.out.println("hello");
    }

    private void showHello() {
        System.out.println("bye");
    }
}
