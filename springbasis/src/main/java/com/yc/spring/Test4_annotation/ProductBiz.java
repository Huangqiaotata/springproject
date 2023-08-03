package com.yc.spring.Test4_annotation;

/**
 * 面向应用的业务层
 */
public interface ProductBiz {
    public void takein(); //入库   1、先查询 find() 2、再添加（加数量 update()  加类型 add()）
    public void find();
}
