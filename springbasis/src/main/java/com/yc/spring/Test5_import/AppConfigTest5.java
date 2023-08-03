package com.yc.spring.Test5_import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//可导入数组  { 1,2 }
@Import(Apple.class)  //beanid (默认为全路径名)  -> 对象
public class AppConfigTest5 {

}
