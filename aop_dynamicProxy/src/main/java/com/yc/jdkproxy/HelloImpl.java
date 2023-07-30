package com.yc.jdkproxy;

public class HelloImpl implements HelloI {
    @Override
    public void sayHello() {
        System.out.println("hello,HelloImpl's。。。");
    }

    @Override
    public void showByeBye() {
        System.out.println("byebye,HelloImpl's。。。");
    }
}
