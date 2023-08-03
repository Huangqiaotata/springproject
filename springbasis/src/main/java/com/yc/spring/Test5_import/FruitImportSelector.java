package com.yc.spring.Test5_import;

import com.yc.spring.Test3_factoryBean.Pear;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class FruitImportSelector implements ImportSelector {
    @Override //回调函数
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //TODO 扫描一些jar包  配置文件  指定第三方的类的路径
        return new String[]{Pear.class.getName()};
    }
}
