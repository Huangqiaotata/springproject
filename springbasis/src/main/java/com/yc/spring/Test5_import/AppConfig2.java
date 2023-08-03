package com.yc.spring.Test5_import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//FruitImportSelector  可以导入多个类 new String[] {}
@Import({Apple.class, FruitImportSelector.class})
public class AppConfig2 {
}
