package com.yc.test;

import com.yc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 面向接口编程
 */
public class UserMapper2 {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory factory = getFactory("mybatis.xml");
        //向表中插入一条数据
        //2、获取会话对象  默认为手动提交  设置自动提交
        SqlSession sqlSession = factory.openSession(true);
        //3、获取接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null,"aa","fhkf","hbkdsjn","hdskjb",1);
        int result = mapper.add(user);
        System.out.println(result);

//        SqlSession sqlSession = factory.openSession();
//        //获取接口实现类对象
//        //会自动创建一个代理对象  然后代理对象执行增删改查等操作
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = mapper.findById(1003);
//        System.out.println(user);
//        System.out.println(mapper.getClass().getName());
        sqlSession.close();
    }

    public static SqlSessionFactory getFactory(String config) throws IOException {
        SqlSessionFactory factory = null;
        InputStream in = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        return factory;
    }

    /*
    1、接口编程
    原生dao  到  daoImpl
    mybatis
        Mapper --> XxxMapper.xml
    SqlSession  代表和数据库的一次会话
    SqlSession Connection  一样 均为非线程安全的 每次使用时应该获取新的对象 不能放在成员变量里面
    Mapper接口没有实现类 但是Mybatis 对 Mapper 接口生成一个代理对象
        讲接口和xml文件绑定
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    两个重要的文件
        mybatis.xml  数据库连接对象  事务管理器  系统运行环境
        sql映射文件  XxxMapper.xml 保存了每一个sql语句的映射信息
            将sql 从代码中抽取出来
     */
}
