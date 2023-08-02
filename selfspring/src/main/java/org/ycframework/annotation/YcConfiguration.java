package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//Documented  文档
//表示这个注解加在哪里  TYPE：类、接口  METHOD：方法  FIELD：属性
@Target(ElementType.TYPE)
//表示这个注解保持到什么时候  SOURCE（源码） CLASS（字节码）  RUNTIME（运行）
@Retention(RetentionPolicy.RUNTIME)
//基础注解  表示可以被托管
@YcComponent
public @interface YcConfiguration {


}
