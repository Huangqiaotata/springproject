package com.yc.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取配置文件
 * sqlSessionFactory
 * SqlSession
 */
public class UserMapperTest {
    public static void main(String[] args) throws IOException {
        //1、读取配置文件
        String resource = "mybatis.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //2、获取sqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据工厂获取会话  即 Connection
        SqlSession sqlSession = factory.openSession();
        System.out.println(sqlSession);
        User user = sqlSession.selectOne("com.yc.mapper.UserMapper.findById",1003);
        System.out.println(user);
        //关闭会话
        sqlSession.close();
    }
}
/*
 * 1.创建Maven项目
 * 2.添加依赖  junit,mybatis,mysql,log4j
 * 3.新建数据库db_mybatis  新建表tb_user
 * 4.新建包 com.yc.entity  -->  实体类User  属性名何数据表中字段一样
 * 5.新建包com.yc.mapper  -->  映射接口  XxxMapper  只有抽象方法
 * 6.新建配置文件  com/yc/mapper/UserMapper.xml
 *   编写查询语句
 * 7.resource创建mybatis-config.xml配置文件  配置数据源和映射文件
 * 8.查询方法进行测试
 *   8.1读取配置文件获取输入流
 *   8.2创建工厂构建器调用build(in)  获取SqlSessionFactory
 *   8.3根据工厂获取SqlSession
 *   8.4通过会话执行查询  selectOne()
 *   8.5关闭会话
 * */