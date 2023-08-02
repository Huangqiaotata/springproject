package com.yc.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// JdkProxyTool 是 InvocationHandler 的一个实例
public class JdkProxyTool implements InvocationHandler {
    private Object target; //目标类

    public JdkProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy() {
        //生成 $Proxy0.class
        Object proxy = Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(),target.getClass().getInterfaces(),this);
        return proxy;
    }

    /**
     * 当在主程序中调用生成的 Proxy 中的方法时 会自动回调这个 invoke（） 在这个invoke() 中放入增强 、 切面 这些功能
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //以反射的方式读取下面的 @Pointcut 中的切入点表达式  ==》  在spring的底层中 用aspectj 来读取表达式
        if (method.getName().startsWith("add")) {
            showHello();
        }
        //调用目标类的方法
        Object returnValue = method.invoke(target, args);
        return returnValue;
    }

    private void showHello() {
        System.out.println("hello..........");
    }
}
