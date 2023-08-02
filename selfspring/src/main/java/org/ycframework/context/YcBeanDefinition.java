package org.ycframework.context;

import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.annotation.Target;

/**
 * 对一个 Bean 的特征的包装的类
 * 特征：scope （ singleton / prototype / ... ）
 *      lazy （ true / false ） 懒加载
 *      primary  主实例 | 优先实例  在一个类中有两个类 用autowired 会发生冲突  加 @primary 优先使用
 */
public class YcBeanDefinition {
    private boolean isLazy;
    private String scope = "singleton";
    private boolean isPrimary;
    //...

    private String classInfo; //类的实例信息

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }
}
