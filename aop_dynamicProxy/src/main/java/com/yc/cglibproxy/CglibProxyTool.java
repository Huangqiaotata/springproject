package com.yc.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// JdkProxyTool 是 InvocationHandler 的一个实例
public class CglibProxyTool implements MethodInterceptor {
    private Object target; //目标类

    public CglibProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy() {
//        Object proxy = Proxy.newProxyInstance(CglibProxyTool.class.getClassLoader(),target.getClass().getInterfaces(),this);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }

    private void showHello() {
        System.out.println("hello..........");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //以反射的方式读取下面的 @Pointcut 中的切入点表达式  ==》  在spring的底层中 用aspectj 来读取表达式
        if (method.getName().startsWith("add")) {
            showHello();
        }
        //调用目标类的方法
        Object returnValue = method.invoke(target, args);
        return returnValue;
    }
}
