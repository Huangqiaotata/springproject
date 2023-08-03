package com.yc.spring.Test6_conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SystemCondition implements Condition {
    /**
     * 匹配方法 看操作系统  《-  环境  《-  spring容器
     * 容器  回调方法
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //Environment  jdk System.getEnv()  环境变量
        Environment env = context.getEnvironment();

        //系统变量  os.name = Windows
        //命令参数  os.name = Linux
        //程序 (从下往上覆盖)

        env.getProperty("user.dir");
        String osname = env.getProperty("os.name");//取操作系统名字
        env.getProperty("user.home");
        if (osname.contains("Linux")) {
            return true;//配置一个虚拟机参数 -Dos.name=Linux  可得到netConnection
        }
        return false;
    }
}
