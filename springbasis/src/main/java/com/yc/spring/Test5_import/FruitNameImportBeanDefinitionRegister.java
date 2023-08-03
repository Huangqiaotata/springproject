package com.yc.spring.Test5_import;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 根据条件  已经加载好了 Pear 再加载 Grape到容器
 */
public class FruitNameImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //先判断是否已经有了Pear加载到容器
        boolean bean = beanDefinitionRegistry.containsBeanDefinition("com.yc.spring.Test3_factoryBean.Pear");
        //如果有 则再创建Grape Bean 加载到容器
        if (bean) {
            RootBeanDefinition d = new RootBeanDefinition(Grape.class);
            //并指定键为  grape
            beanDefinitionRegistry.registerBeanDefinition("grape",d);
        }
    }
}
