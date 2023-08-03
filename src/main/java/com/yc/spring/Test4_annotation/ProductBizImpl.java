package com.yc.spring.Test4_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //此注解表明此类为业务层的类
public class ProductBizImpl implements ProductBiz {
    @Autowired  //由spring 自动地从容器中取出 ProductDao 的一个实现类的对象 注入
    private ProductDao productDao;//业务层依赖dao层 最好依赖接口开发

    @Override
    public void takein() {
        productDao.find();
        int i =1;
        if (i==1){
            productDao.update();
        }else {
            productDao.add();
        }

    }

    @Override
    public void find() {

    }

    public ProductBizImpl() {
        System.out.println("ProductBizImpl。。。");
    }
}
